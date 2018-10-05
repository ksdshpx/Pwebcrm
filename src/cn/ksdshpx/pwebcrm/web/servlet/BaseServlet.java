package cn.ksdshpx.pwebcrm.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Create with IntelliJ IDEA
 * Create by peng.x
 * Date: 2018/8/31
 * Time: 10:20
 * Description:抽取BaseServlet
 */
public class BaseServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
    	resp.setContentType("text/html;charset=utf-8");
        String methodName = req.getParameter("method");
        try {
            Class clazz = Class.forName(this.getClass().getName());
            Method method = clazz.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            String retVal = (String) method.invoke(this, req, resp);
            if (retVal == null || retVal == "") {
                return;
            }
            if (retVal.contains(":")) {
                int index = retVal.indexOf(":");
                String prefix = retVal.substring(0, index);
                String path = retVal.substring(index + 1);
                if ("r".equalsIgnoreCase(prefix)) {//重定向
                    resp.sendRedirect(req.getContextPath() + path);
                } else if ("f".equalsIgnoreCase(prefix)) {
                    req.getRequestDispatcher(path).forward(req, resp);
                } else {
                    throw new RuntimeException("系统暂时不支持该种类型前缀的业务！");
                }
            } else {
                //不包含冒号默认为转发
                req.getRequestDispatcher(retVal).forward(req, resp);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
