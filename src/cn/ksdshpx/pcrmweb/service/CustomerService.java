package cn.ksdshpx.pcrmweb.service;

import java.util.List;

import cn.ksdshpx.pwebcrm.dao.CustomerDao;
import cn.ksdshpx.pwebcrm.domain.Customer;

/**
 * 业务逻辑层
 * 
 * @author peng.x
 * @date 2018年9月23日 上午11:17:31
 */
public class CustomerService {
	private CustomerDao customerDao = new CustomerDao();

	/**
	 * 添加客户
	 * 
	 * @param customer
	 */
	public void add(Customer customer) {
		customerDao.add(customer);
	}

	/**
	 * 查询所有客户
	 * 
	 * @return
	 */
	public List<Customer> findAll() {
		return customerDao.findAll();
	}
}
