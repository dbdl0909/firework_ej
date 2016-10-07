<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.awt.image.BufferedImage" %>
<%@ page import ="java.io.ByteArrayInputStream" %>
<%@ page import ="javax.imageio.ImageIO" %>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>미니몰</title>
		<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
		<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js" type="text/javascript"></script>
		<script>
			$(document).ready(function() {
				$('#agreeCheckAll').click(function() {
					//체크박스 눌렀을때 승인을 전체 체크하기 위한 코드
					$('.agreeChange').prop('checked', this.checked);
				});
				
				$('#chkBtn').click(function() {
					if($('.agreeChange:checked').length > 0) {
						$('#chkForm').submit();
					}
				});
			});
		</script>
	</head>
	<body>
	<jsp:include page="/module/headerSub.jsp" />
	<div id="container">
		<div class="content">
			<div>
				전체 승인<input type="checkbox" id="agreeCheckAll"/>
			</div>
			<form action="${pageContext.request.contextPath}/Gchk/GoodsAdminChk.go" method="post" id="chkForm">
				<table class="basic">
					<tr>
						<th>번호</th><th>상품코드</th><th>상품명</th><th>판매자아이디</th><th>카테고리</th>
						<th>가격</th><th>상세내용</th><th>등록날짜</th><th>승인여부</th><th>승인체크</th>
					</tr>
					<c:forEach var="goods" items="${goodsList}" varStatus="status">
						<tr>
							<td>${status.count}</td>
							<td>${goods.g_code}</td>
							<td>${goods.g_name}</td>
							<td>${goods.g_id}</td>
							<td>${goods.g_cate}</td>
							<td>${goods.g_price}</td>
							<td>${goods.g_sangse}</td>
							<td>${goods.g_date}</td>
							<td>${goods.g_agree}</td>
							<td>
								<c:set var="chk" value="${goods.g_agree}o" />
								<c:if test="${chk eq 'No'}">
			    					<input type="checkbox" class="agreeChange" name="agreeChange" class="agreeChange" value="${goods.g_code}"/>
			    					<%-- <input type="hidden" value="${goods.g_code}" id="hiddenGcode"/> --%>
			    					<%-- <c:set var="gCodeArray" value="${goods.g_code}" scope="request"/> --%>
			    					<%-- <c:out value="${gCodeArray}"/> --%>
								</c:if>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan=10>
							<input type="button" value="승인" id="chkBtn"/>
							<span id="chkHelper"></span>
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
									<span><a href="${pageContext.request.contextPath}/Glist/goodsAdminList.go?page=${page-1}"> [이전] </a>&nbsp;</span>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:forEach begin="${startPage}" end="${endPage}" varStatus="status">
							<c:choose>
								<c:when test="${status.count == page}">
									<span>[${status.count}]</span>
								</c:when>
								<c:otherwise>
									<span><a href="${pageContext.request.contextPath}/Glist/goodsAdminList.go?page=${status.count}">[${status.count}]</a></span>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${page <= endPage}">
							<c:choose>
								<c:when test="${page == endPage}">
									<span> [다음] </span>
								</c:when>
								<c:otherwise>
									<span><a href="${pageContext.request.contextPath}/Glist/goodsAdminList.go?page=${page+1}"> [다음] </a></span>
								</c:otherwise>
							</c:choose>	
						</c:if>							
					</div>
				</div>
			</form>			
		</div>
	</div>
	<jsp:include page = "/module/footer.jsp" />
	</body>
</html>