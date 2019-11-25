<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改页面</title>
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
   left:58%;
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
}
input[type=submit]{
  width:125px;
  height:40px;
  background-color:#009900;
  color:white;
  border:1px;
}
input[type=submit]:hover {
    background-color:#007700;
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
.nav{
   height:50px;
   width:100%;
}
li{
	list-style:none;  
	font:20px “宋体”;
	font-weight:lighter;
	float:left;
	padding-top:16px;
	padding-right:30px;
}
hr{
  height:1px;
  width:95%;
  border:none;
  border-top:2px solid #EDEFF0;
}
</style>
</head>
<body>
      <div class="form">
	     <div class="form-center">
	           <div class="dh1"><h1 class="h1">个人设置</h1></div>
	               <div class="df2">
	                    <div class="nav">
				           <ul>
									<li>
									   <div class="n">个人信息</div>
									</li>	
						   </ul>
						</div>
				        <hr>
				        <div class="ff">
						    <form action="${pageContext.request.contextPath }/servlet/user" method="post" enctype="multipart/form-data">
							     <input type="hidden" name="op" value="update"/><br>
							     <input type="hidden" name="id" value="${user.id }">
							     <input type="hidden" name="who" value="user">
							     <input type="hidden" name="returnPage" value="${user.id }">
							     <div class="f">
							         <div class="fimg">
								         <span id="bimg">头像：</span>
								         <div id="img">
								            <img alt="haha" id="p" width="150px" height="150px" src="${pageContext.request.contextPath }/${user.img }"/><br>
								         </div>
									     <input type="hidden" name="OldImg" value="${user.img }" /><br>
									     <input type="file" name="img"/><br>
							         </div>
								     <div class="fd">
							               <span>帐户:</span>
							               <input type="text" name="username" value="${user.username }"/>
								     </div>
								     <div class="fd">
								           <span>昵称:</span>
								           <input type="text" name="nickname" value="${user.nickname }"/><br>
								     </div>
								     <div class="fd"> 
								         <span>手机:</span>
								         <input type="text" name="tel" value="${user.tel }"/><br>
								     </div>
								     <div class="fd">
								         <span>地址:</span>
								         <input type="text" name="addr" value="${user.addr }"/><br>
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