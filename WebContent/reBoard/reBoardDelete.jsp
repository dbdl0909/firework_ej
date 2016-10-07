<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>상품평등록</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>미니몰</title>
	<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >

</head>
<body>
<jsp:include page="/module/headerSub.jsp" />
<div id="container">
	<div class="content">

<form name="deleteForm" action="${pageContext.request.contextPath}/Reb/rebDeleteAction.reb" method="post">
<table border=1>
<tr>
	<td>
		<font size=2>글 비밀번호 : </font>
	</td>
	<td>
		<input type="password" name="reb_pass"/>
		<input type="hidden" name="num" value="${num}"/>
		<input type="hidden" name="id" value="${id}"/>
	</td>
</tr>
<tr>
	<td colspan=2 align=center>
		<a href="javascript:deleteForm.submit()">삭제</a>
		&nbsp;&nbsp;
		<a href="javascript:history.go(-1)">돌아가기</a>
	</td>
</tr>
</table>
</form>
	</div>
</div>
<jsp:include page = "/module/footer.jsp" />
</body>
</html>