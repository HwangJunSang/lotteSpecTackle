<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/test2019/css/js_reset.css" type="text/css"/>	
	
	<style>
		#header {
			text-align: right;
			font-size : 50px;
		}
		#content {
			text-align: left;
			font-size : 50px;
		}
		div {
			width : 100%;
		}
	</style>
	
	<script>
		function notLogin() {
			alert("로그인이 필요한 서비스입니다.");
		}
	</script>
</head>
<body>
	<div id="header">
		<a href="mv_lotte_Login.do">로그인</a>&nbsp;
		<a href="mv_lotte_Join.do">회원가입</a>
	</div>
	
	<div id="content">
		회의실 예약      <c:choose>
					  <c:when test="${param.message == 'OK' }">
				      <button href="mv_mettingRoom.do">회의실 에약하기</button>
					  </c:when>
					    
					  <c:otherwise>
					  <button onclick="notLogin();">회의실 에약하기</button>
					  </c:otherwise>
				  </c:choose><br/>
		부가 서비스1  <button>click</button><br/>
		부가 서비스2  <button>click</button><br/>
		부가 서비스3  <button>click</button><br/>
		부가 서비스4  <button>click</button><br/>
		부가 서비스5  <button>click</button>
	</div>
	

	
</body>
</html>