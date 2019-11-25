<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update foods</title>
<style type="text/css">
#p{
   padding-left:80px;
   padding-bottom:10px;
}
body{
   background:#EDEFF0;
}
.form{
   float:left;
   position:relative;
   left:50%;
   clear:both;
}
.form-center{
   margin-top:200px;
   height:300px;
   width:500px;
   background:#FFFFFF;
   position:relative;
   left:-50%;
   display:table-cell;
   vertical-align:middle;
}
.dh1{
  margin-top:0px;
  background:#EDEFF0;
}
.h1{
background: #EEE url(data:image/gif;base64,iVBORw0KGgoAAAANSUhEUgAAAAQAAAAECAIAAAAmkwkpAAAAHklEQVQImWNkYGBgYGD4//8/A5wF5SBYyAr+//8PAPOCFO0Q2zq7AAAAAElFTkSuQmCC) repeat;
text-shadow: 5px -5px black, 4px -4px white;
font-weight: bold;
-webkit-text-fill-color: transparent;
-webkit-background-clip: text;
margin:0px;
padding-bottom:25px;
}
.df2{
  width:850px;
  height:650px;
}
.fd{
 padding-top:10px;
 padding-bottom:10px;
 padding-left:40px;
}
.ff{
   float:left;
   position:relative;
   left:58%;
   clear:both;
}
.f{
   margin-top:200px;
   width:700px;
   background:#FFFFFF;
   position:relative;
   left:-50%;
   display:table-cell;
   vertical-align:middle;
}
span{
 font-weight:bold;
 padding-right:20px;
}
input[type=text],input[type=password]{
  width:20%;
  height:30px;
}
input[type=submit]{
  width:100px;
  height:40px;
  background-color:#009900;
  color:white;
  border:1px;
  padding-left:10px;
  margin-left:150px;
}
input[type=submit]:hover {
    background-color:#007700;
}
#bimg{
  display:block;
  margin-top:20px;
  margin-left:20px;
  
}
#img{
  display:block;
  padding-bottom:20px;
  margin-left:100px;
}
#submit{
  width:150px;
  margin-left:280px;
}
#ft{
  margin-left:21px;
}
#fp{
  margin-left:5px;
}
#fa{
  margin-left:80px;
}
.h1{
  margin-left:300px;
}
</style>
</head>
<body>
    <div class="h1">
        <h1>update foods</h1>
    </div>
	<div class="ff">
		<form action="${pageContext.request.contextPath}/servlet/foods" method="post" enctype="multipart/form-data">
			<input type="hidden" name="op" value="update">
			<input type="hidden" name="id" value="${foods.id }">
			<input type="hidden" name="yuanLaiDeImg" value="${foods.img }">
			<div class="f">
			     <div class="fimg">
				    <span id="bimg">图片：</span>
				    <div id="img">
						<img alt="${foods.name }" width="200" src="${pageContext.request.contextPath}/${foods.img }">
						<input type="file" name="img">
				    </div>
			     </div>
			     <div class="fd">
	               <span>名称:</span>
	               <input type="text" name="name" value="${foods.name }" id="ft"><br>
		         </div>
			     <div class="fd">
			           <span>价格:</span>
			           <input type="text" name="price" value="${foods.price }" id="ft"><br>
			     </div>
			     <div class="fd">
			           <span>评价:</span>
			           <input type="text" name="evaluate" value="${foods.evaluate }" id="ft"><br>	
			     </div>
			     <div class="fd"> 
			         <span>配送费:</span>
			         <input type="text" name="psf" value="${foods.psf }" id="fp"><br>
			     </div>
			     <div class="fd">
			         <span>窗口号 :</span>
			         <input type="text" name="shop" value="${foods.shop }" ><br>
			     </div>
			     <div class="fd">
			         <span>简介:</span><br>
			         <textarea rows="5" cols="50" name="intro" id="fa">${foods.intro }</textarea><br>
			     </div>
			     <div class="fd" id="submit">
			         <input type="submit" value="修改食物">
			     </div>
			</div>
		</form>
	</div>
</body>
</html>
