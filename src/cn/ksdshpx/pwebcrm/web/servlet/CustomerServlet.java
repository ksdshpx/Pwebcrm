package cn.ksdshpx.pwebcrm.web.servlet;

import cn.itcast.servlet.BaseServlet;
import cn.ksdshpx.pcrmweb.service.CustomerService;

import javax.servlet.annotation.WebServlet;

/**
* Web层
* @author peng.x
* @date 2018年9月23日 上午11:33:31
*/
@WebServlet("/CustomerServlet")
public class CustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();
}
