<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理列表</title>
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
	}
	.table{
	    margin-top:15px;
	}
body {
    margin: 0;
}

ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 25%;
    background-color: #f1f1f1;
    position: fixed;
    height: 100%;
    overflow: auto;
}

li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

li a.active {
    background-color: #4CAF50;
    color: white;
}

li a:hover:not(.active) {
    background-color: #555;
    color: white;
}
</style>
</head>
<body onload="startTime()">
<ul>
  <li><h3><a class="active" >欢迎您！管理员：王二</a></h3></li>
  <li><a href="${pageContext.request.contextPath }/auth/gly/glymain.jsp">管理主页</a></li>
  <li><a href="${pageContext.request.contextPath }/servlet/user?op=list">用户管理</a></li>
  <li><a href="${pageContext.request.contextPath }/servlet/foods?op=foodsList">美食管理</a></li>
  <li><a href="${pageContext.request.contextPath }/servlet/sender?op=list">骑手管理</a></li>
  <li style="float:down;"><a href="${pageContext.request.contextPath }/servlet/user?op=logout">退出登陆</a></li>
</ul>

<div style="margin-left:25%;padding:1px 16px;height:500px;">
	<a href="${pageContext.request.contextPath }/auth/gly/adduser.jsp"><input type="submit" value="添加用户"/></a><br>
	<form action="${pageContext.request.contextPath }/servlet/user" method="post">
		<input type="hidden" name="op" value="search"/>
			编号:<input type="text" name="id"/>
  			用户名：<input type="text" name="username"/>
			联系电话:<input type="text" name="tel"/>
			地址:<input type="text" name="addr"/>
		<input type="submit" value="查询"/>
	</form><br>
	<table width="100%" cellspacing="0">
         <tr>
            <th>编号</th>
			<th>用户名</th>
			<th>昵称</th>
			<th>头像</th>
			<th>电话</th>
			<th>地址</th>
			<th colspan="2">操作</th>
         </tr>
        
         <c:forEach items="${sessionScope.userList }" var="users">
         <tr>
	            <td>${users.id }</td>
	            <td>${users.username }</td>
	            <td>${users.nickname }</td>
	            <td>
	                <img alt="users" width="80px" height="80px" src="${pageContext.request.contextPath }/${users.img }"/>
	            </td>
	            <td>${users.tel }</td>
	            <td>${users.addr }</td>
	            <td>
				    <a href="${pageContext.request.contextPath }/servlet/user?op=findById&id=${users.id}&who=gly">修改</a>
			    </td>
			    <td>
				    <a href="${pageContext.request.contextPath }/servlet/user?op=delete&id=${users.id}">删除</a>
			    </td>
         </tr>
         </c:forEach>
     </table>
</div>

</body>
</html>