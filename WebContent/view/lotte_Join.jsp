<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="/test2019/css/js_reset.css" type="text/css"/>	
	<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
	<script>
		function notTrue() {
			alert("유효성 검사에 통과 해야만 가입이 가능하니다. 사번을 다시 확인해 주세요.");
		}
		
		function Validation_check() {
			var str = $("input[name=lotte_id]").val();
			var copyStr = str;
			document.location.href = "Validation_check.do?lotte_id=" + str;
			$("input[name=lotte_id]").val(copyStr);
		}
		
	</script>
</head>
<body>
	<div id="content">
		<table width="500" cellpadding="0" cellspacing="0" border="1">
			<form action="s0hjscob01u0.do" method="post">
				<tr>
					<td>ID(사번 입력)</td>
					<td><input type="text" name="lotte_id"></td>
					<td><button type="button" onclick="Validation_check();">유효성 검사</button></td>
					<c:choose>
						<c:when test="${param.message == 'OK' }">
				    	<td><span style="color:blue;">통과함.</span></td>
						</c:when>
					    
					    <c:when test="${param.message == 'NO' }">
					    <td><span style="color:blue;">실패함.</span></td>
						</c:when>
					    
						<c:otherwise>
				    	<td><span style="color:red;">검사 필수</span></td>
						</c:otherwise>
				  	</c:choose><br/>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td><input type="text" name="lotte_pw"></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="lotte_email"></td>
				</tr>
				<tr>
					<td>이름</td>
					<td><input type="text" name="lotte_name"></td>
				</tr>		
				<tr>
					<c:choose>
						<c:when test="${param.message == 'OK' }">
				    	<td><input type="submit" value="가입 신청"><br/></td>
						</c:when>
					    
						<c:otherwise>
						<td><button type="button" onclick="notTrue();">가입 신청</button></td>
						</c:otherwise>
				  	</c:choose><br/>
				    <td><button type="button" onclick="location.href='mv_lotte_Main.do' ">메인 페이지로 이동</button></td>	
					<td><button type="button" onclick="location.href='mv_lotte_Login.do' ">로그인 페이지로 이동</button></td>
				</tr>
			</form>
		</table>
	</div>
</body>
</html>