<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加用户</title>
</head>
<body>
	<form action="${pageContext.request.contextPath }/servlet/user?op=add" method="post" enctype="multipart/form-data">
	     username:<input type="text" name="username"/><br>
	     nickname:<input type="text" name="nickname"/><br>
	     password:<input type="password" name="password"/><br>
	     tel:<input type="text" name="tel"/><br>
	     addr:<input type="text" name="addr"/><br>
	     img<input type="file" name="img"/><br>
	     <input type="submit" value="提交">
	</form>

</body>
</html>