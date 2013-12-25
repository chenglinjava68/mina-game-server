<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Server Status</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	
  </head>
  
  <body>
    
    <table border="1" cellspacing="0">
    	<tr>
    		<td colspan="8">Server Status</td>
    	</tr>
    	<tr>
    		<td>IoSession</td>
    		<td colspan="7">Player</td>
    	</tr>
    	<tr>
    		<td>sessionId</td>
    		<td>昵称</td>
    		<td>性别</td>
    		<td>等级</td>
    		<td>注册日期</td>
    		<td>在线时长</td>
    		<td>职业</td>
    		<td>用户名</td>
    	</tr>
    	<c:forEach var="obj" items="${onlinePlayers}" varStatus="status">
   		<tr>
   			<td>${obj.key.id}</td>
   			<td>${obj.value.nickName }</td>
   			<td>${obj.value.sex==true?"男":"女" }</td>
   			<td>${obj.value.level }</td>
   			<td>${obj.value.regDate }</td>
   			<td>${obj.value._onlineTime }</td>
   			
   			<td>${obj.value.job.type }</td>
   			<td>${obj.value.user.username }</td>
   		</tr>
    	</c:forEach>
    </table>
  </body>
</html>
