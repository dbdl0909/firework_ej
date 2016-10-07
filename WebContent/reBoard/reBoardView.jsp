<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>미니몰</title>
	<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
	<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
	<c:if test="${requestScope.loginChk==1}">
		<script>
			alert('비밀번호가 일치하지 않습니다.');
		</script>
	<c:set var="loginChk" />
	</c:if>
</head>
<body>
<jsp:include page="/module/headerSub.jsp" />
<div id="container">
	<div class="content">
		<!-- 게시판 수정 -->
		<table class="basic">
			<tr align="center" valign="middle">
				<td colspan="5">문의하기</td>
			</tr>
			
			<tr>
				<td style="font-family:돋음; font-size:12" height="16">
					<div align="center">제 목&nbsp;&nbsp;</div>
				</td>
				
				<td style="font-family:돋음; font-size:12">
				${rebDto.reb_subject}
				</td>
				
			</tr>	
			
			<tr bgcolor="cccccc">
				<td colspan="2" style="height:1px;">
				</td>
			</tr>
			
			<tr>
				<td style="font-family:돋음; font-size:12">
					<div align="center">내 용</div>
				</td>
				<td style="font-family:돋음; font-size:12">
					<table border=0 width=490 height=250 style="table-layout:fixed">
						<tr>
							<td valign=top style="font-family:돋음; font-size:12">
							${rebDto.reb_content}
							</td>
						</tr>
					</table>
				</td>
			</tr>	
			<tr bgcolor="cccccc">
				<td colspan="2" style="height:1px;"></td>
			</tr>
			<tr><td colspan="2">&nbsp;</td></tr>
			
			<tr align="center" valign="middle">
				<td colspan="5">
					<font size=2>
					<a href="${pageContext.request.contextPath}/Reb/rebReplyView.reb?num=${rebDto.reb_no}">
					[답변]
					</a>&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/Reb/rebQnaModify.reb?num=${rebDto.reb_no}&id=${rebDto.m_id}">
					[수정]
					</a>&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/Reb/rebDeleteForm.reb?num=${rebDto.reb_no}&id=${rebDto.m_id}">
					[삭제]
					</a>&nbsp;&nbsp;
					<a href="${pageContext.request.contextPath}/RebQna/rebList.reb">[목록]</a>&nbsp;&nbsp;
					</font>
				</td>
			</tr>
		</table>
		<!-- 게시판 수정 -->
	</div>
</div>
<jsp:include page = "/module/footer.jsp" />
</body>
</html>