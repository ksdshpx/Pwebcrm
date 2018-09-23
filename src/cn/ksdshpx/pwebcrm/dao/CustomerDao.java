package cn.ksdshpx.pwebcrm.dao;
/**
  * 持久层
 * @author peng.x
 * @date 2018年9月23日 上午11:15:07
 */

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.jdbc.TxQueryRunner;
import cn.ksdshpx.pwebcrm.domain.Customer;

public class CustomerDao {
	private QueryRunner qr = new TxQueryRunner();

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
}
