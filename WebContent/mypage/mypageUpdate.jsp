<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>미니몰</title>
<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js" type="text/javascript"></script>
<script>
	$(document).ready(function(){
		
		$('#updateBtn').click(function(){
			
			alert('변경되었습니다.');
				
		});
			
		});

</script>
</head>
<body>
<jsp:include page="/module/headerSub.jsp" />
<div id="container">
	<div class="content clearFix">
		<div id="leftMenuWrap" >
			<dl id="leftMenu" class="clearFix">
				<dt>마이페이지</dt>
				<c:choose>
					<c:when test="${sessionScope.loginLevel == '관리자'}">
						<dd><a href="${pageContext.request.contextPath}/Mli/mList.mo">Member</a></dd>
						<dd><a href="${pageContext.request.contextPath}/Glist/goodsAdminList.go">승인대기</a></dd>
						<dd><a href="">게시글 관리</a></dd>
					</c:when>
					<c:when test="${sessionScope.loginLevel == '판매자'}">
						<dd><a href="${pageContext.request.contextPath}/Mup/mUpdateForm.mo">개인정보수정</a></dd>
						<dd><a href="${pageContext.request.contextPath}/Glist/goodsSellerList.go">등록상품</a></dd>
						<dd><a href="">게시글 관리</a></dd>
					</c:when>
					<c:otherwise>
						<dd><a href="${pageContext.request.contextPath}/Mup/mUpdateForm.mo">개인정보수정</a></dd>
						<dd><a href="${pageContext.request.contextPath}/Oli/orderListOne.oo">주문 리스트</a></dd>
						<dd><a href="">게시글 관리</a></dd>
					</c:otherwise>
				</c:choose>
				<dd></dd>
			</dl>
		</div>		
		<div id="myPageContent">
			<div id="joinWarp">
			<form action="${pageContext.request.contextPath}/Mup/mUpdatePro.mo" method="post">			
				<table border="1">
				<tr>
					<td><span class="loginTitleTxt">아이디</span></td>
					<td><input type="text" name="m_id" size="20" value="${m.m_id}" readonly="readonly"></td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">암호</span></td>
					<td><input type="password" name="m_pw" size="20"  value="${m.m_pw}"></td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">권한</span></td>					
					<td>
						<c:if test="${m.m_level} == '구매자'">
							<label for="m_level">구매자</label><input type="radio" name="m_level" size="20" value="구매자" checked="checked">
							<label for="m_level">판매자</label><input type="radio" name="m_level" size="20" value="판매자">
						</c:if>
						<label for="m_level">구매자</label><input type="radio" name="m_level" size="20" value="구매자" >
						<label for="m_level">판매자</label><input type="radio" name="m_level" size="20" value="판매자" checked="checked">					
					</td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">이름</span></td>
					<td><input type="text" name="m_name" size="20"  value="${m.m_name}"></td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">이메일</span></td>
					<td><input type="text" name="m_email" size="20"  value="${m.m_email}"></td>
				<tr>
				<tr>
					<td><span class="loginTitleTxt">주소</span></td>
					<td><input type="text" name="m_addr" size="20"  value="${m.m_addr}"></td>
				<tr>
				<tr>
					<td colspan="4"><input id="updateBtn" type="submit" value="정보수정"></td>
				</tr>
				</table>
			</form>
		</div>
		</div>
	</div>
</div>
<jsp:include page="/module/footer.jsp" />
</body>
</html>