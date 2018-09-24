package cn.ksdshpx.pwebcrm.dao;
/**
  * 持久层
 * @author peng.x
 * @date 2018年9月23日 上午11:15:07
 */

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import cn.ksdshpx.pwebcrm.domain.Customer;

public class CustomerDao {
	private QueryRunner qr = new TxQueryRunner();

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
	public List<Customer> findAll() {
		try {
			String sql = "SELECT * FROM t_customer";
			return qr.query(sql, new BeanListHandler<Customer>(Customer.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
