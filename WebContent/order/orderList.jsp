<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/jquery.brickfolio.min.css"  rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js" type="text/javascript"></script>
<script>
	$(document).ready(function() {
		
		$('#moneyChkAll').click(function() {
			$('.moneyChk').prop('checked', this.checked);
		});
		
		$('#chkBtn').click(function() {
			$('#chkForm').submit();
		});
				
	});
</script>
</head>
<body>
<jsp:include page="/module/headerSub.jsp" />
<div id="container"> 
	<div class="content">
		<form id="chkForm" action="${pageContext.request.contextPath}/Ochk/orderCheckPro.oo" method="post">
			전체 확인 <input type="checkbox" id="moneyChkAll">	
			<table class="basic">
				<tr>
					<td>주문 번호</td><td>상품 이름</td><td>구매자 아이디</td><td>판매자 아이디</td><td>구매자 이름</td><td>배송지</td>
					<td>주문 날짜</td><td>상품 코드</td><td>가격</td><td>수량</td><td>합계</td><td>주문 상태</td><td>입금 확인</td>
				</tr>
				<c:forEach var="order" items="${orderList}">
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
						<td>
							<c:set var="ochk" value="${order.o_state}"></c:set>
							<c:if test="${ochk eq '입금예정'}">
								<input type="checkbox" class="moneyChk" name="moneyChk" value="${order.o_no}">	
							</c:if>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan=13>
						<input type="button" value="확인" id="chkBtn"/>
					</td>
				</tr>
			</table>
			<div id="pagingWrap" class="clearFix">
				<div id="pagingContent">	
					<c:if test="${page >= 1}">
						<c:choose>
							<c:when test="${page == startPage}">
								<span> [이전] </span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Oli/orderList.oo?page=${page-1}"> [이전] </a>&nbsp;</span>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:forEach begin="${startPage}" end="${endPage}" varStatus="status">
						<c:choose>
							<c:when test="${status.count == page}">
								<span>[${status.count}]</span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Oli/orderList.oo?page=${status.count}">[${status.count}]</a></span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page <= endPage}">
						<c:choose>
							<c:when test="${page == endPage}">
								<span> [다음] </span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Oli/orderList.oo?page=${page+1}"> [다음] </a></span>
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