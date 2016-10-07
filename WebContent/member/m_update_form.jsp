<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%@ page import = "com.minimall.dto.MemberDto" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="main.css" />

<style>
table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
</style>


</head>

<body>
 



<%
MemberDto m = (MemberDto)request.getAttribute("m");

%>
<form action="<%= request.getContextPath() %>/Mupdate/m_update_pro.ksmart" method="post">
<table border="1">
<tr>
	<td>아이디</td>
	<td>
<input type="hidden" name="ora_id" size="20" value="<%= m.getm_id() %>">	
		<%= m.getm_id() %>
		
<%-- 
<input type="text" name="m_id" size="20" value="<%= dbid %>" readonly> 
--%>
	
	</td>
<tr> 
<tr>
	<td>암호</td>
	<td><input type="text" name="ora_pw" size="20" value="<%= m.getm_pw() %>"></td>
<tr>
<tr>
	<td>권한</td>
	<td><input type="text" name="ora_level" size="20" value="<%= m.getm_level() %>"></td>
<tr>
<tr>
	<td>이름</td>
	<td><input type="text" name="ora_name" size="20" value="<%= m.getm_name() %>"></td>
<tr>
<tr>
	<td>이메일</td>
	<td><input type="text" name="ora_email" size="20" value="<%= m.getm_email() %>"></td>
<tr>
<tr>
	<td>주소</td>
	<td><input type="text" name="ora_email" size="20" value="<%= m.getm_addr() %>"></td>
<tr>
<tr>
	<td colspan="4"><input type="submit" value="회원수정버튼"></td>
</tr>
</table>
</form>


</body>
</html>






