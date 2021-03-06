package cn.ksdshpx.pwebcrm.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;
import cn.ksdshpx.pcrmweb.service.CustomerService;
import cn.ksdshpx.pwebcrm.domain.Customer;
import cn.ksdshpx.pwebcrm.domain.PageBean;

/**
 * Web层
 * 
 * @author peng.x
 * @date 2018年9月23日 上午11:33:31
 */
public class CustomerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private CustomerService customerService = new CustomerService();

	/**
	 * 添加客户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
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

	/**
	 * 查询所有客户
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.获取页面传递的当前页pageNow
		Integer pageNow = getPageNow(request);
		// 2.给定每页记录数pageSize
		Integer pageSize = 10;
		// 3.使用pageNow和pageSize调用service方法，得到PageBean，保存到request域中
		PageBean<Customer> pageBean = customerService.findAll(pageNow, pageSize);
		request.setAttribute("pageBean", pageBean);
		// 4.转发到list.jsp
		return "f:/list.jsp";
	}

	/**
	 * 获取当前页 如果pageNow参数不存在，则pageNow=1,如果pageNow参数存在，转换成Integer类型即可
	 * 
	 * @param request
	 * @return
	 */
	private Integer getPageNow(HttpServletRequest request) {
		String pageNow = request.getParameter("pageNow");
		if (pageNow == null || pageNow.trim().isEmpty()) {
			return 1;
		}
		return Integer.parseInt(pageNow);
	}

	/**
	 * 预加载客户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String preEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到cid
		String cid = request.getParameter("cid");
		// 2.调用service方法，返回Customer对象
		Customer customer = customerService.load(cid);
		// 3.将返回的Customer对象放入request域中
		request.setAttribute("customer", customer);
		// 4.转发到edit.jsp
		return "f:/edit.jsp";
	}

	/**
	 * 编辑客户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.封装表单数据到Customer对象
		Customer customer = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		customer.setCid(request.getParameter("cid"));
		// 2.调用service方法，返回Customer对象
		customerService.edit(customer);
		// 3.保存编辑成功信息到request域中
		request.setAttribute("msg", "编辑客户信息成功！");
		// 4.转发到edit.jsp
		return "f:/msg.jsp";
	}

	/**
	 * 删除客户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到cid
		String cid = request.getParameter("cid");
		// 2.调用service方法，删除Customer对象
		customerService.delete(cid);
		// 3.保存删除成功信息到request域中
		request.setAttribute("msg", "编辑客户信息成功！");
		// 4.转发到msg.jsp
		return "f:/msg.jsp";
	}

	/**
	 * 多条件组合查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String queryByCretiaria(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.封装表单数据到Customer对象
		Customer cretiaria = CommonUtils.toBean(request.getParameterMap(), Customer.class);
		// 2.调用service方法，根据条件对象查询
		List<Customer> customerList = customerService.queryByCretiaria(cretiaria);
		// 3.保存查询结果到request域中
		request.setAttribute("customerList", customerList);
		// 4.转发到list.jsp
		return "f:/list.jsp";
	}
}
