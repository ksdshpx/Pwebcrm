package cn.ksdshpx.pwebcrm.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;
import cn.ksdshpx.pcrmweb.service.CustomerService;
import cn.ksdshpx.pwebcrm.domain.Customer;

/**
 * Web层
 * 
 * @author peng.x
 * @date 2018年9月23日 上午11:33:31
 */
public class CustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

	public String add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.封装表单数据到Customer
		Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		// 2.补充字段：cid
		customer.setCid(CommonUtils.uuid());
		// 3.调用service的add方法
		customerService.add(customer);
		// 4.保存成功信息到request域中
		request.setAttribute("msg", "恭喜，添加客户成功！");
		// 5.转发到msg.jsp
		return "f:/msg.jsp";
	}
}
