<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		
		$('.delLink').click(function(){
			var idx = $('.delLink').index(this);
			$('.hiddenTd').eq(idx).css('display', 'block');
			$('#hiddenTh').css('display', 'block');
			$('.delLink').css('display', 'none');
			
		});
		
		$('.delLink2').click(function(){
			$('#delForm').submit();
				
		});				
		
	});
		
	
</script>
</head>
<c:if test="${requestScope.loginChk==1}">
<script>
	alert('비밀번호가 일치하지 않습니다.');
</script>
<c:set var="loginChk" />
</c:if>
<%-- <div id="delWarp">
	<div id="loginContent">
		<span id="closeDelWrap">
			X
		</span>
		<div id="loginArea">		
			<h2>
				본인 확인을 위해 비밀번호를 입력해 주세요
			</h2>
			<form id="delForm" action="${pageContext.request.contextPath}/Odel/orderDeletePro.oo?oNo=${order.o_no}" method="post">
			<table>
			<tr>
				<td><label for="mPw">비밀번호</label></td>
				<td><input type="password" name="mPw" id="mPw"/></td>
				<td><span></span></td>
			</tr>	
			<tr>
				<td colspan="2">
					<div id="delSubmit" style="cursor:pointer;">
						주문취소
					</div>
				</td>
			</tr>				
			</table>	
			</form>		
		</div>
	</div>
</div>
</head> --%>
<body>
<jsp:include page="/module/headerSub.jsp"/>
<div id="container"> 
	<div class="content">
		<form id="delForm" action="${pageContext.request.contextPath}/Odel/orderDeletePro.oo" method="post">
			<table class="basic">
				<tr>
					<th>주문 번호</th><th>상품 이름</th><th>구매자 아이디</th><th>판매자 아이디</th><th>구매자 이름</th><th>배송지</th>
					<th>주문 날짜</th><th>상품 코드</th><th>가격</th><th>수량</th><th>합계</th><th>주문 상태</th><th>주문 취소</th>
				</tr>
				<c:forEach var="order" items="${orderListOne}">
					<tr>
						<td>${order.o_no}</td>
						<td>${order.g_name}</td>
						<td>${order.m_id}</td>
						<td>${order.g_id}</td>
						<td>${order.m_name}</td>
						<td>${order.m_addr}</td>
						<td>${order.o_date}</td>
						<td>${order.g_code}</td>
						<td>${order.g_price}</td>
						<td>${order.o_count}</td>
						<td>${order.o_total}</td>
						<td>${order.o_state}</td>					
						<td class="hiddenTd" style="display:none;">
							<input type="text" name="oNo" value="${order.o_no}" hidden="hidden" />
							비밀번호 확인 : <input type="password" id="delPw" name="delPw" size="10"> <input type="button" class="delLink2" value="취소확인">
						</td>
						<td><input type="button" class="delLink" value="주문취소"></td>							
					</tr>
				</c:forEach>
			</table>
			<div id="pagingWrap" class="clearFix">
				<div id="pagingContent">	
					<c:if test="${page >= 1}">
						<c:choose>
							<c:when test="${page == startPage}">
								<span> [이전] </span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Oli/orderListOne.oo?page=${page-1}"> [이전] </a>&nbsp;</span>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:forEach begin="${startPage}" end="${endPage}" varStatus="status">
						<c:choose>
							<c:when test="${status.count == page}">
								<span>[${status.count}]</span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Oli/orderListOne.oo?page=${status.count}">[${status.count}]</a></span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page <= endPage}">
						<c:choose>
							<c:when test="${page == endPage}">
								<span> [다음] </span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Oli/orderListOne.oo?page=${page+1}"> [다음] </a></span>
							</c:otherwise>
						</c:choose>	
					</c:if>							
				</div>
			</div>
		</form>	
	</div>
</div>	
<jsp:include page="/module/footer.jsp" />
</body>
</html>