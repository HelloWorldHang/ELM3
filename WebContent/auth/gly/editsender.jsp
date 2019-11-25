<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改骑手</title>
</head>
<body>
<form action="${pageContext.request.contextPath }/servlet/sender" method="post" enctype="multipart/form-data">
		<input type="hidden" name="op" value="update"/><br>
		<input type="hidden" name="who" value="gly"/><br>
	    <input type="hidden" name="id" value="${sender.id }">
		姓名：<input type="text" name="username" value="${sender.username }"><br>
		联系电话：<input type="text" name="tel" value="${sender.tel }"/><br>
		照片：<input type="file" name="img" value="${sender.img }"/><br>
		身份证号：<input type="text" name="idcard" value="${sender.idcard }"/><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>