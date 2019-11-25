<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改页面</title>
<style type="text/css">
#p{
   padding-top:20px;
   padding-left:80px;
   padding-bottom:10px;
}
</style>
</head>
<body>
<form action="${pageContext.request.contextPath }/servlet/user" method="post" enctype="multipart/form-data">
	     <input type="hidden" name="op" value="update"/><br>
	     <input type="hidden" name="id" value="${user.id }">
	     <input type="hidden" name="who" value="gly">
	     username:<input type="text" name="username" value="${user.username }"/><br>
	     nickname:<input type="text" name="nickname" value="${user.nickname }"/><br>
	     tel:<input type="text" name="tel" value="${user.tel }"/><br>
	     addr:<input type="text" name="addr" value="${user.addr }"/><br>
	     <img alt="haha" id="p" width="80px" height="80px" src="${pageContext.request.contextPath }/${user.img }"/><br>
	     <input type="hidden" name="OldImg" value="${user.img }" /><br>
	     img<input type="file" name="img"/><br>
	     <input type="submit" value="提交">
	</form>
</body>
</html>