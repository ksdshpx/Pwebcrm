package cn.ksdshpx.pcrmweb.service;

import java.util.List;

import cn.ksdshpx.pwebcrm.dao.CustomerDao;
import cn.ksdshpx.pwebcrm.domain.Customer;
import cn.ksdshpx.pwebcrm.domain.PageBean;

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
	public PageBean<Customer> findAll(Integer pageNow, Integer pageSize) {
		return customerDao.findAll(pageNow, pageSize);
	}

	/**
	 * 根据cid加载客户信息
	 * 
	 * @param cid
	 * @return
	 */
	public Customer load(String cid) {
		return customerDao.load(cid);
	}

	/**
	 * 编辑客户信息
	 * 
	 * @param customer
	 */
	public void edit(Customer customer) {
		customerDao.edit(customer);
	}

	/**
	 * 删除客户信息
	 * 
	 * @param cid
	 */
	public void delete(String cid) {
		customerDao.delete(cid);
	}

	/**
	 * 多条件组合查询
	 * 
	 * @param cretiaria
	 * @return
	 */
	public List<Customer> queryByCretiaria(Customer cretiaria) {
		return customerDao.queryByCretiaria(cretiaria);
	}
}
