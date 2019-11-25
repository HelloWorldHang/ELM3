<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>add foods</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/servlet/foods" method="post" enctype="multipart/form-data">
		<input type="hidden" name="op" value="add">
		name:<input type="text" name="name"><br>
		price:<input type="text" name="price"><br>
		img:<input type="file" name="img"><br>
		intro:<textarea rows="5" cols="100" name="intro"></textarea><br>
		evalute:<input type="text" name="evalute"><br>	
		psf:<input type="text" name="psf"><br>	
		shop:<input type="text" name="shop"><br>	
		<input type="submit" value="添加美食">	
	</form>
</body>
</html>