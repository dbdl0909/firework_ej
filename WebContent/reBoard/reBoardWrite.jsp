<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>미니몰</title>
	<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
	<script language="javascript">
	function addboard(){
		boardform.submit();
	}
	</script>
</head>
<body>
<jsp:include page="/module/headerSub.jsp" />
<div id="container">
	<div class="content">
		<!-- 게시판 등록 -->
		<form action="${pageContext.request.contextPath}/Reb/rebAddAction.reb" method="post" name="boardform">	
		<input type="hidden" name="mId" size="20" value="${sessionScope.loginId}">
		<table class="basic">
			<tr align="center" valign="middle">
				<td colspan="5">문의하기</td>
			</tr>
			<tr>
				<td style="font-family:돋음; font-size:12" height="16">
					<div align="center">상품평</div>
				</td>
				<td>
					<select name="reb_category" style="font-family:돋음; font-size:12; width:97px;">
						<option value="매우좋음">매우좋음</option>
						<option value="종음">종음</option>
						<option value="보통">보통</option>
						<option value="나쁨">나쁨</option>
						<option value="매우나쁨">매우나쁨</option>
					</select>
				</td>
				
				<td align="right" style="font-family:돋음; font-size:12" height="16">
					<input name="reb_secret" type="checkbox" value="y">
					비밀글
				</td>
			</tr>
			<tr>
				<td style="font-family:돋음; font-size:12" height="16">
					<div align="center">제 목</div>
				</td>
				<td colspan="2">
					<input name="reb_subject" type="text" size="50" maxlength="100" value="" />
				</td>
			</tr>
			<tr>
				<td style="font-family:돋음; font-size:12">
					<div align="center">내 용</div>
				</td>
				<td colspan="2">
					<textarea name="reb_content" cols="67" rows="15"></textarea>
				</td>
			</tr>
			<tr bgcolor="cccccc">
				<td colspan="2" style="height:1px;">
				</td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			<tr align="center" valign="middle">
				<td colspan="5">
					<a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp;
					<a href="javascript:history.go(-1)">[뒤로]</a>
				</td>
			</tr>
		</table>
		</form>
		<!-- 게시판 등록 -->
	</div>
</div>
<jsp:include page = "/module/footer.jsp" />
</body>
</html>