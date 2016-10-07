<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>미니몰</title>
<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/jquery.brickfolio.min.css"  rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js" type="text/javascript"></script>
<script src="js/jquery.brickfolio.js"></script>
<script>
	$(document).ready(function(){
		 $('.brickfolio').brickfolio();
	});
</script>
</head>
<body>
<%@ include file = "/module/headerMain.jsp" %>
<div style="width:100%; height:700px;">
	<iframe src="${pageContext.request.contextPath}/Nex-master/slider/transition/random/index.html" style="width:100%; height:100%;"></iframe>
</div>
<div id="container"> 
	<div class="content">
		<div class="demo-wrapper">
			<div class="brickfolio">
				<c:set var="listChk" value="${goodsList}" />
				<c:choose>
					<c:when test="${listChk eq null}">
						<div>
							<p>등록된 상품이 없습니다.</p>
						</div>
					</c:when>
					<c:when test="${listChk ne null}">
						<c:forEach var="goods" items="${goodsList}" varStatus="status">
							<!-- 기본적으로 a태그는 div를 감쌀 수 없지만 html5에 와서 허용이 된다. -->
							<a href="${pageContext.request.contextPath}/Gdetail/goodsDetailAction.go?gCode=${goods.g_code}">
								<div class="bf-item">
									<img src="data:image/${gImageType[status.index]};base64, ${b64[status.index]}" alt="image not found">
									<h4>${goods.g_name}</h4>
									<p>${goods.g_price}<br>${goods.g_sangse}</p>
								</div>
							</a>
						</c:forEach>
					</c:when>
				</c:choose>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/module/footer.jsp" />
</body> 
</html>