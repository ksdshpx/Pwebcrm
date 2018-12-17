<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>客户列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<h3 align="center">客户列表</h3>
	<table border="1" width="70%" align="center">
		<tr>
			<th>客户姓名</th>
			<th>性别</th>
			<th>生日</th>
			<th>手机</th>
			<th>邮箱</th>
			<th>描述</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${requestScope.pageBean.beanList}" var="customer">
			<tr>
				<td>${customer.cname}</td>
				<td>${customer.gender}</td>
				<td>${customer.birthday}</td>
				<td>${customer.cellphone}</td>
				<td>${customer.email}</td>
				<td>${customer.description}</td>
				<td><a
					href="<c:url value='/CustomerServlet?method=preEdit&cid=${customer.cid}'/>">编辑</a>
					<a href="<c:url value='/CustomerServlet?method=delete&cid=${customer.cid}'/>">删除</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<center>
		第${pageBean.pageNow}页/共${pageBean.pageCount}页
		<a href="<c:url value='/CustomerServlet?method=findAll&pageNow=1'/>">首页</a>
		<c:if test="${pageBean.pageNow > 1}">
			<a href="<c:url value='/CustomerServlet?method=findAll&pageNow=${pageBean.pageNow-1}'/>">上一页</a>
		</c:if>
		<%--分页页码计算 --%>
		<c:choose>
			<%--总页数不满10页，从1开始全部显示 --%>
			<c:when test="${pageBean.pageCount <= 10}">
				<c:set var="begin" value="1"/>
				<c:set var="end" value="${pageBean.pageCount}"/>
			</c:when>
			<%--总页数大于10页 --%>
			<c:otherwise>
				<c:set var="begin" value="${pageBean.pageNow - 5}"/>
				<c:set var="end" value="${pageBean.pageNow + 4}"/>
				<%--头溢出 --%>
				<c:if test="${begin < 1}">
					<c:set var="begin" value="1"/>
					<c:set var="end" value="10"/>
				</c:if>
				<%--尾溢出 --%>
				<c:if test="${end > pageBean.pageCount}">
					<c:set var="begin" value="${pageBean.pageCount - 9}"/>
					<c:set var="end" value="${pageBean.pageCount}"/>
				</c:if>
			</c:otherwise>
		</c:choose>
		<c:forEach var="i" begin="${begin}" end="${end}">
			<c:choose>
				<c:when test="${i eq pageBean.pageNow}">
					[${i}]
				</c:when>
				<c:otherwise>
					<a href="<c:url value='/CustomerServlet?method=findAll&pageNow=${i}'/>">[${i}]</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageBean.pageNow < pageBean.pageCount}">
			<a href="<c:url value='/CustomerServlet?method=findAll&pageNow=${pageBean.pageNow+1}'/>">下一页</a>
		</c:if>
		<a href="<c:url value='/CustomerServlet?method=findAll&pageNow=${pageBean.pageCount}'/>">尾页</a>
	</center>
</body>
</html>
