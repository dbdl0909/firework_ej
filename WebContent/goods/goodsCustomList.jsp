<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>미니몰</title>
		<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
		<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
	</head>
	<body>	
		<jsp:include page="/module/headerSub.jsp" />
		<div id="container">
			<div class="content">
				<div id="customSortWarp" class="clearFix">
					<h3>SHOP</h3>
					<ul id="sortList">
						<li><a href="${pageContext.request.contextPath}/Glist/goodsCustomList.go?row=up" style="text-decoration: none">높은가격 <span>▲</span></a></li>
						<li><a href="${pageContext.request.contextPath}/Glist/goodsCustomList.go?row=down" style="text-decoration: none">낮은가격 <span>▼</span></a></li>
						<li><a href="${pageContext.request.contextPath}/Glist/goodsCustomList.go?row=new" style="text-decoration: none">최신순</a></li>
					</ul>
				</div>	
					<c:set var="listChk" value="${goodsList}" />
					<c:choose>
						<c:when test="${listChk eq null}">
							<div>
								<p>등록된 상품이 없습니다.</p>
							</div>
						</c:when>
						<c:when test="${listChk ne null}">
							<ul id="customListWrap" class="clearFix">
								<c:forEach var="goods" items="${goodsList}" varStatus="status">
									<li>
										<a href="${pageContext.request.contextPath}/Gdetail/goodsDetailAction.go?gCode=${goods.g_code}">
											<%-- <span>${status.count}</span> --%>
											<p><img id="customImage" src="data:image/${gImageType[status.index]};base64, ${b64[status.index]}" alt="image not found" /></p>
											<p><span>상품명 : </span>${goods.g_name}</p>
											<p><span>판매자 : </span>${goods.g_id}</p>
											<p><span>가격 : </span>&#8361; ${goods.g_price}</p>
										</a>
									</li>
								</c:forEach>
							</ul>
							<div id="pagingWrap" class="clearFix">
								<div id="pagingContent">
									<c:if test="${page >= 1}">
										<c:choose>
											<c:when test="${page == startPage}">
												<span> [이전] </span>
											</c:when>
											<c:otherwise>
												<span><a href="${pageContext.request.contextPath}/Glist/goodsCustomList.go?page=${page-1}"> [이전] </a>&nbsp;</span>
											</c:otherwise>
										</c:choose>
									</c:if>
									<c:forEach begin="${startPage}" end="${endPage}" varStatus="status">
										<c:choose>
											<c:when test="${status.count == page}">
												<span>[${status.count}]</span>
											</c:when>
											<c:otherwise>
												<span><a href="${pageContext.request.contextPath}/Glist/goodsCustomList.go?page=${status.count}">[${status.count}]</a></span>
											</c:otherwise>
										</c:choose>
									</c:forEach>
									<c:if test="${page <= endPage}">
										<c:choose>
											<c:when test="${page == endPage}">
												<span> [다음] </span>
											</c:when>
											<c:otherwise>
												<span><a href="${pageContext.request.contextPath}/Glist/goodsCustomList.go?page=${page+1}"> [다음] </a></span>
											</c:otherwise>
										</c:choose>	
									</c:if>
								</div>
							</div>	
						</c:when>
					</c:choose>
				</div>
			</div>
		<jsp:include page="/module/footer.jsp" />
	</body>
</html>