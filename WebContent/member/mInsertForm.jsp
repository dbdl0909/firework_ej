<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	
	<title>미니몰</title>	
	<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
	<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js" type="text/javascript"></script>
</head>

<body>
<jsp:include page="/module/headerSub.jsp"  flush="false" />
<div id="container" >
	<div class="content" >
		<div id="joinWarp">
			<form action="${pageContext.request.contextPath}/Min/mInsertPro.mo" method="post">			
				<table border="1">
				<tr>
					<td><span class="loginTitleTxt">아이디</span></td>
					<td><input type="text" name="m_id" size="20" placeholder="아이디"></td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">암호</span></td>
					<td><input type="password" name="m_pw" size="20" placeholder="비밀번호"></td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">권한</span></td>					
					<td>
						<label for="m_level">구매자</label><input type="radio" name="m_level" size="20" value="구매자">
						<label for="m_level">판매자</label><input type="radio" name="m_level" size="20" value="판매자">
					</td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">이름</span></td>
					<td><input type="text" name="m_name" size="20" placeholder="이름"></td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">이메일</span></td>
					<td><input type="text" name="m_email" size="20" placeholder="이메일"></td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">주소</span></td>
					<td><input type="text" name="m_addr" size="20" placeholder="주소"></td>
				<tr>
				<tr>
					<td colspan="4"><input type="submit" value="회원가입버튼"></td>
				</tr>
				</table>
			</form>
		</div>	
	</div>
</div>
<%@ include file = "/module/footer.jsp" %>
</body> 
</html>