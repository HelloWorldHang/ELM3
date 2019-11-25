<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>确认订单</title>
<script language="JavaScript">
            function startTime()   
            {   
                var today=new Date();//定义日期对象   
                var yyyy = today.getFullYear();//通过日期对象的getFullYear()方法返回年    
                var MM = today.getMonth()+1;//通过日期对象的getMonth()方法返回年    
                var dd = today.getDate();//通过日期对象的getDate()方法返回年     
                var hh=today.getHours();//通过日期对象的getHours方法返回小时   
                var mm=today.getMinutes();//通过日期对象的getMinutes方法返回分钟   
                var ss=today.getSeconds();//通过日期对象的getSeconds方法返回秒   
                // 如果分钟或小时的值小于10，则在其值前加0，比如如果时间是下午3点20分9秒的话，则显示15：20：09   
                MM=checkTime(MM);
                dd=checkTime(dd);
                mm=checkTime(mm);   
                ss=checkTime(ss);    
                var day; //用于保存星期（getDay()方法得到星期编号）
                if(today.getDay()==0)   day   =   "星期日 " 
                if(today.getDay()==1)   day   =   "星期一 " 
                if(today.getDay()==2)   day   =   "星期二 " 
                if(today.getDay()==3)   day   =   "星期三 " 
                if(today.getDay()==4)   day   =   "星期四 " 
                if(today.getDay()==5)   day   =   "星期五 " 
                if(today.getDay()==6)   day   =   "星期六 " 
                document.getElementById('nowDateTimeSpan').innerHTML=yyyy+"-"+MM +"-"+ dd +" " + hh+":"+mm+":"+ss+"   " + day;   
                setTimeout('startTime()',1000);//每一秒中重新加载startTime()方法 
            }   

            function checkTime(i)   
            {   
                if (i<10){
                    i="0" + i;
                }   
                  return i;
            }  
        </script>
<style>
table,tr,th,td{
	border: 1px solid green;
	text-align:center;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
    background-color: #333;
}

li {
    float: left;
    border-right:1px solid #bbb;
}

li:last-child {
    border-right: none;
}

li a {
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
}

li a:hover:not(.active) {
    background-color: #111;
}

.active {
    background-color: #4CAF50;
}
#confirm{
			width:400px;
			height:280px;
			position:absolute;
			left: 50%;
			top: 50%;
			margin-left:-400px;
			margin-top:-140px;
			border:1px;
			background-color:;	
			align:center;
}
#form{
			width:300px;
			height:160px;
			position:relative;
   			left:50%;
  			top:50%;
  			margin-left:-150px;
  			margin-top:-100px;
}
#con{
    margin-top:15px;
    font-weight:bold;
    font-size:large;
    font-color:4CAF50;
    margin-right:2px solid #ddd;
    margin-bottom:10px;
}
#unote{
    width:780px;
    border-collapse: collapse;
}
#fnote{
    width:100%;

}
#tishi{
    float:left;
    colspan:3;
}
#tijiao{
    float:right;
    margin-top:20px;
    margin-right:20px;
}
</style>
</head>
<body onload="startTime()">
	<h3>
			<img alt="头像" width="80" src="${pageContext.request.contextPath }/${sessionScope.user.img }">
			亲爱的顾客： ${sessionScope.user.username }，欢迎您！西小师提醒您，该用餐啦！ 
			<a href="${pageContext.request.contextPath }/servlet/user?op=findById&id=${user.id }&&who=user">修改个人资料</a>
			<a href="${pageContext.request.contextPath }/servlet/cart?op=show">我的美食车</a>
			<a href="${pageContext.request.contextPath }/servlet/user?op=dingDan">我的订单</a>
	</h3> 
		<hr>
		<ul>	
  			<li><a class="active" href="${pageContext.request.contextPath }/servlet/main">返回主页</a></li>
  			<li><a href="${pageContext.request.contextPath }/servlet/foods?op=canTing&shop=1">西苑餐厅</a></li>
 			<li><a href="${pageContext.request.contextPath }/servlet/foods?op=canTing&shop=2">东苑餐厅</a></li>
  			<li><a href="${pageContext.request.contextPath }/servlet/foods?op=canTing&shop=3">清真餐厅</a></li>
  			<li><a> 当前时间：<font color="#4CAF50"><span id="nowDateTimeSpan"></span></font></a></li>
  			<li style="float:right;"><a href="${pageContext.request.contextPath }/servlet/user?op=logout">退出登陆</a></li>
		</ul>
<div id="con"><img alt="ding" src="../upload/jpg/ding.jpg">&nbsp&nbsp&nbsp确认订单</div>
<div>
  <div>
	<form action="${pageContext.request.contextPath}/servlet/buy" method="post">
	  
		<input type="hidden" name="fid" value="${requestScope.fid }">
		<input type="hidden" name="op" value="buy">
		<input type="hidden" name="amount" value="${requestScope.amount }">
		<table id="unote">
		    <tr style="border:0"><td id="tishi" style="border:0">请确认您的信息，以便西小师骑手为你 准确送达</td></tr>
		    <tr style="height:20px"></tr>
			<tr style="border:0">
				<td style="border:0">姓名:<input type="text" name="username" value="${requestScope.username }"></td>
				<td style="border:0">电话:<input type="text" name="utel" value="${requestScope.tel }"/></td>
				<td style="border:0">地址:<input type="text" name="addr" value="${requestScope.addr }"/><br></td>
			</tr>
		</table>
		<div id="con">您选购了</div>
		 <table id="fnote" cellspacing="0">
		    <tr>
		       <th>图片</th>
		       <th>餐厅</th>
		       <th>名称</th>
		       <th>数量</th>
		       <th>备注</th>
		    </tr>
		    <tr>
				<td><img alt="${requestScope.name }" width="110" height="100" src="${pageContext.request.contextPath}/${requestScope.img }"></td>
				<td>${requestScope.foodsAddr }</td>
				<td>${requestScope.name }</td>
				<td><input type="text" name="amount" value="${requestScope.amount }"/></td>
				<td>在此写下你的备注:<br><textarea rows="5" cols="40" name="note"></textarea></td>
			</tr>
		 </table>
		    <input type="submit" value="提交订单" id="tijiao">
	 </form>
   </div>
</div>
	   
</body>
</html>