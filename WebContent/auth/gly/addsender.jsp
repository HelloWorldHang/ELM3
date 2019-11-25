<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加骑手</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/servlet/sender?op=add" method="post" enctype="multipart/form-data">
		姓名：<input type="text" name="username"/><br>
		密码：<input type="password" name="password"/><br>
		联系电话：<input type="text" name="tel"/><br>
		照片：<input type="file" name="img"/><br>
		身份证号：<input type="text" name="idcard"/><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>