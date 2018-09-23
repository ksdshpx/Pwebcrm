package cn.ksdshpx.pcrmweb.service;

import cn.ksdshpx.pwebcrm.dao.CustomerDao;
import cn.ksdshpx.pwebcrm.domain.Customer;

/**
  * 业务逻辑层
 * @author peng.x
 * @date 2018年9月23日 上午11:17:31
 */
public class CustomerService {
	private CustomerDao customerDao = new CustomerDao();
	public void add(Customer customer) {
		customerDao.add(customer);
	}
}
