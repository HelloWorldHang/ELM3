<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改骑手</title>
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
}
.ff{
   float:left;
   position:relative;
   left:55%;
   clear:both;
}
.f{
   margin-top:200px;
   width:500px;
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
  width:70%;
  height:30px;
  border-radius:7px;
}
input[type=submit]{
  width:125px;
  height:40px;
  background-color:#009900;
  color:white;
  border:0px;
  border-radius:7px;
}
input[type=submit]:hover {
    background-color:#007700;
}
#nav{
   
}
#bimg{
  display:block;
  margin-top:0px;
}
#img{
  display:block;
}
#submit{
  width:150px;
  margin-left:280px;
}
</style>
</head>
<body>
      <div class="form">
	     <div class="form-center">
	           <div class="dh1"><h1 class="h1">个人设置</h1></div>
	               <div class="df2">
				        <div class="nav">基本信息</div>
				        <hr>
				        <div class="ff">
						    <form action="${pageContext.request.contextPath }/servlet/sender" method="post" enctype="multipart/form-data">
								<input type="hidden" name="op" value="update"/><br>
								<input type="hidden" name="who" value="sender"/><br>
							    <input type="hidden" name="id" value="${sender.id }">
							     <div class="f">
							         <div class="fimg">
								         <span id="bimg">头像：</span>
								         <div id="img">
								            <input type="file" name="img" value="${sender.img }"/><br>
								         </div>
							         </div>
								     <div class="fd">
							               <span>姓名:</span>
							               <input type="text" name="username" value="${sender.username }"><br>
								     </div>
								     <div class="fd">
								           <span>密码:</span>
								           <input type="password" name="password" value="${sender.password }"/><br>
								     </div>
								     <div class="fd"> 
								         <span>手机:</span>
								         <input type="text" name="tel" value="${sender.tel }"/><br>
								     </div>
								     <div class="fd">
								         <span>身份证号:</span>
								         <input type="text" name="idcard" value="${sender.idcard }"/><br>
								     </div>
								     <div class="fd" id="submit">
								         <input type="submit" value="保存">
								     </div>
								  </div>
							</form>
						</div>
				  </div>
		</div>
	</div>
</body>
</html>