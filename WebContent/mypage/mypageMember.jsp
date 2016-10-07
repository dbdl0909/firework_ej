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
		
		$('.delMemberBtn').click(function(){
			
			var btnIdx = $('.delMemberBtn').index(this);
			
			$('#memberId').each(function(i){				
				
				
			});
			
		})
		
	})
</script>
</head>
<body>
<jsp:include page="/module/headerSub.jsp" />
<div id="container">
	<div class="content clearFix">
		<div id="leftMenuWrap">
			<dl id="leftMenu">
				<dt>마이페이지</dt>
				<c:choose>
					<c:when test="${sessionScope.loginLevel == '관리자'}">
						<dd><a href="${pageContext.request.contextPath}/Mli/mList.mo">Member</a></dd>
						<dd><a href="${pageContext.request.contextPath}/Glist/goodsAdminList.go">승인대기</a></dd>
						<dd><a href="${pageContext.request.contextPath}/Oli/orderList.oo">입금확인</a></dd>
						<dd><a>게시글 관리</a></dd>
					</c:when>
					<c:when test="${sessionScope.loginLevel == '판매자'}">
						<dd><a href="${pageContext.request.contextPath}/Mup/mUpdateForm.mo">개인정보수정</a></dd>
						<dd><a href="${pageContext.request.contextPath}/Glist/goodsSellerList.go">등록상품</a></dd>
						<dd><a>게시글 관리</a></dd>
					</c:when>
					<c:otherwise>
						<dd><a href="${pageContext.request.contextPath}/Mup/mUpdateForm.mo">개인정보수정</a></dd>
						<dd><a href="${pageContext.request.contextPath}/Oli/orderListOne.oo">주문 리스트</a></dd>
						<dd><a>게시글 관리</a></dd>
					</c:otherwise>
				</c:choose>
				<dd></dd>
			</dl>
		</div>
		<div id="myPageContent">
			<table class="basic">
				<tr>
					<td>이름</td><td>아이디</td><td>권한</td><td>이름</td><td>이메
					일</td><td>가입날짜</td><td>주소</td><td>수정</td><td>삭제</td>
				</tr>		
				<c:forEach var="member" items="${alm}">		
					<tr>
						<td id="memberId">${member.m_id}</td>
						<td>${member.m_pw}</td>
						<td>${member.m_level}</td>
						<td>${member.m_name}</td>
						<td>${member.m_email}</td>
						<td>${member.m_date}</td>
						<td>${member.m_addr}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>
<jsp:include page="/module/footer.jsp" />
</body>
</html>