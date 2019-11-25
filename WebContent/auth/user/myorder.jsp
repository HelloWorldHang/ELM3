<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<style type="text/css">
table,tr,th,td{
		border: 1px solid green;
		text-align:center;
	}
</style>
</head>
<body>
<c:if test="${aim == '2'}">
	<h2>西师骑手：${requestScope.senderName }正在为你送单!</h2>
	<table width="100%">
		<tr>
			<th>编号</th>
			<th>美食名称</th>
			<th>购买数量</th>
			<th>图片</th>
			<th>美食地址</th>
			<th>订单状态</th>
			<th>骑手电话</th>
		</tr>
			<tr>
				<td>${requestScope.orderId }</td>
				<td>${requestScope.name }</td>
				<td>${requestScope.amount }</td>
				<td>
					<img alt="foods" width="180"  src="${pageContext.request.contextPath }/${requestScope.img }">
				</td>
				<td>${requestScope.addr }</td>
				<td>${requestScope.state }</td>
				<td>${requestScope.tel }</td>
		   </tr>	
	</table>
</c:if>

<c:if test="${aim == '1' }">
	<h1>你在${requestScope.addr }订的${requestScope.name }还没有骑手接单，请耐心等待！</h1>
</c:if>

<c:if test="${aim == '0' }">
	<h1>你还没有下单赶快选购食物吧！</h1>
</c:if>
</body>
</html>