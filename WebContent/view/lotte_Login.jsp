<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/test2019/css/js_reset.css" type="text/css"/>
</head>
<body>
	<div id="content">
		<table width="500" cellpadding="0" cellspacing="0" border="1">
			<form action="login.do" method="post">
				<tr>
					<td>ID</td>
					<td><input type="text" name="lotte_id"></td>
				</tr>
				<tr>
					<td>패스워드</td>
					<td><input type="text" name="lotte_password"></td>
				</tr>
				<tr>
					<td><input type="submit" value="로그인"><br/></td>
					<td><button type="button" onclick="location.href='mv_lotte_Main.do' ">메인 페이지로 이동</button></td>
					<td><button type="button" onclick="location.href='mv_lotte_Join.do' ">회원가입</button></td>
				</tr>	
				<tr>
					<td>
						<c:choose>
							<c:when test="${param.message == 'OK' }">
					    	<td><span style="color:blue;">로그인 성공</span></td>
							</c:when>
							
							<c:when test="${param.message == 'NO' }">
					    	<td><span style="color:blue;">로그인 실패</span></td>
							</c:when>
						    
							<c:otherwise>
					    	<td><span style="color:black;">로그인을 해주세요</span></td>
							</c:otherwise>
					  	</c:choose><br/>
					</td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>