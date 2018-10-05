package cn.ksdshpx.pwebcrm.dao;
/**
  * 持久层
 * @author peng.x
 * @date 2018年9月23日 上午11:15:07
 */

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.itcast.utils.CommonUtils;
import cn.ksdshpx.pwebcrm.domain.Customer;
import cn.ksdshpx.pwebcrm.domain.PageBean;

public class CustomerDao {
	private static QueryRunner qr = new TxQueryRunner();

	/**
	 * 添加用户
	 * 
	 * @param customer
	 */
	public void add(Customer customer) {
		try {
			String sql = "INSERT INTO t_customer(cid,cname,gender,birthday,cellphone,email,description) values(?,?,?,?,?,?,?)";
			Object[] params = { customer.getCid(), customer.getCname(), customer.getGender(), customer.getBirthday(),
					customer.getCellphone(), customer.getEmail(), customer.getDescription() };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 查询所有客户
	 * 
	 * @return
	 */
	public PageBean<Customer> findAll(Integer pageNow, Integer pageSize) {
		try {
			// 1.创建pageBean对象
			PageBean<Customer> pageBean = new PageBean<>();
			// 2.设置pageBean的pageNow以及pageSize
			pageBean.setPageNow(pageNow);
			pageBean.setPageSize(pageSize);
			// 3.得到rowCount
			String sql = "SELECT COUNT(*) FROM t_customer";
			Number rowCount = (Number) qr.query(sql, new ScalarHandler());
			pageBean.setRowCount(rowCount.intValue());
			// 4.得到beanList
			sql = "SELECT * FROM t_customer  ORDER BY cname LIMIT ?,?";
			List<Customer> beanList = qr.query(sql, new BeanListHandler<Customer>(Customer.class),
					(pageNow - 1) * pageSize, pageSize);
			pageBean.setBeanList(beanList);
			return pageBean;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 根据cid查询客户信息
	 * 
	 * @param cid
	 * @return
	 */
	public Customer load(String cid) {
		try {
			String sql = "SELECT * FROM t_customer WHERE cid = ?";
			Object[] params = { cid };
			return qr.query(sql, new BeanHandler<Customer>(Customer.class), params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 编辑客户信息
	 * 
	 * @param customer
	 */
	public void edit(Customer customer) {
		try {
			String sql = "UPDATE t_customer SET cname = ?,gender = ?,birthday = ?,cellphone = ?,email = ?,description = ? WHERE cid = ?";
			Object[] params = { customer.getCname(), customer.getGender(), customer.getBirthday(),
					customer.getCellphone(), customer.getEmail(), customer.getDescription(), customer.getCid() };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 删除客户信息
	 * 
	 * @param cid
	 */
	public void delete(String cid) {
		try {
			String sql = "DELETE FROM t_customer WHERE cid = ?";
			Object[] params = { cid };
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws Exception {
		for (int i = 1; i <= 300; i++) {
			String sql = "INSERT INTO t_customer(cid,cname,gender,birthday,cellphone,email,description) values(?,?,?,?,?,?,?)";
			Object[] params = { CommonUtils.uuid(), "sfit_" + i, i % 2 == 0 ? "男" : "女", "20180202", "1396266" + i,
					"sfit_" + i + "@163.com", "我是sfit_" + i };
			qr.update(sql, params);
		}
	}

	/**
	 * 多条件组合查询
	 * 
	 * @param cretiaria
	 * @param pageSize
	 * @param pageNow
	 * @return
	 */
	public PageBean<Customer> queryByCretiaria(Customer cretiaria, Integer pageNow, Integer pageSize) {
		PageBean<Customer> pageBean = null;
		try {
			// 1.创建PageBean对象，设置已有属性pageNow和pageSize
			pageBean = new PageBean<>();
			pageBean.setPageNow(pageNow);
			pageBean.setPageSize(pageSize);
			// 2.得到rowCount
			StringBuilder baseSql = new StringBuilder("SELECT COUNT(*) FROM t_customer");
			StringBuilder whereSql = new StringBuilder(" WHERE 1=1");
			ArrayList<Object> params = new ArrayList<>();
			String cname = cretiaria.getCname();
			String gender = cretiaria.getGender();
			String cellphone = cretiaria.getCellphone();
			String email = cretiaria.getEmail();
			if (!(cname == null) && !(cname == "")) {
				whereSql.append(" and cname like ?");
				params.add("%" + cname + "%");
			}
			if (!(gender == null) && !(gender == "")) {
				whereSql.append(" and gender=?");
				params.add(gender);
			}
			if (!(cellphone == null) && !(cellphone == "")) {
				whereSql.append(" and cellphone like ?");
				params.add("%" + cellphone + "%");
			}
			if (!(email == null) && !(email == "")) {
				whereSql.append(" and email like ?");
				params.add("%" + email + "%");
			}
			Number rowCountByNumber = (Number) qr.query(baseSql.append(whereSql).toString(), new ScalarHandler(),
					params.toArray());
			Integer rowCount = rowCountByNumber.intValue();
			pageBean.setRowCount(rowCount);
			// 3.得到beanList
			StringBuilder sql = new StringBuilder("SELECT * FROM t_customer");
			StringBuilder limitSql = new StringBuilder(" LIMIT ?,?");
			params.add((pageNow - 1) * pageSize);
			params.add(pageSize);
			List<Customer> beanList = qr.query(sql.append(whereSql).append(limitSql).toString(),
					new BeanListHandler<Customer>(Customer.class), params.toArray());
			pageBean.setBeanList(beanList);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pageBean;
	}
}
