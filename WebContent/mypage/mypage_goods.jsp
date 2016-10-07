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
			<dl id="leftMenu" class="">
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
						<dd><a href="${pageContext.request.contextPath}/Oli/orderListOne.oo">order</a></dd>
						<dd><a href="">게시글 관리</a></dd>
					</c:otherwise>
				</c:choose>
				
			</dl>
		</div>
		
		<div id="myPageContent">			
			<table class="basic">
				<tr>
					<th>번호</th><th>상품코드</th><th>상품명</th><th>카테고리</th><th>가격</th>
					<th>상세내용</th><th>등록날짜</th><th>수정</th><th>삭제</th>
				</tr>
				<c:forEach var="goods" items="${goodsList}" varStatus="status">
					<tr>
						<td>${status.count}</td>
						<td>${goods.g_code}</td>
						<td>${goods.g_name}</td>
						<td>${goods.g_cate}</td>
						<td>${goods.g_price}</td>
						<td>${goods.g_sangse}</td>
						<td>${goods.g_date}</td>
						<td>
							<a href="${pageContext.request.contextPath}/Gup/goodsUpdateForm.go?gCode=${goods.g_code}">수정</a>
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/Gdel/goodsDeleteAction.go?gCode=${goods.g_code}">삭제</a>
						</td>
					</tr>
				</c:forEach>
			</table>
			<a href="${pageContext.request.contextPath}/Gin/goodsInsertForm.go">상품 등록</a>
			<div id="pagingWrap" class="clearFix">
				<div id="pagingContent">	
					<c:if test="${page >= 1}">
						<c:choose>
							<c:when test="${page == startPage}">
								<span> [이전] </span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Glist/goodsSellerList.go?page=${page-1}"> [이전] </a>&nbsp;</span>
							</c:otherwise>
						</c:choose>
					</c:if>
					<c:forEach begin="${startPage}" end="${endPage}" varStatus="status">
						<c:choose>
							<c:when test="${status.count == page}">
								<span>[${status.count}]</span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Glist/goodsSellerList.go?page=${status.count}">[${status.count}]</a></span>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:if test="${page <= endPage}">
						<c:choose>
							<c:when test="${page == endPage}">
								<span> [다음] </span>
							</c:when>
							<c:otherwise>
								<span><a href="${pageContext.request.contextPath}/Glist/goodsSellerList.go?page=${page+1}"> [다음] </a></span>
							</c:otherwise>
						</c:choose>	
					</c:if>							
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="/module/footer.jsp" />
</body>
</html>