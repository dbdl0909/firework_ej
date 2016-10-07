<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				$('#gName').blur(function() {
					if($('#gName').val() == '') {
						$('#gNameHelper').text('상품 이름을 입력해주세요');
					} else {
						$('#gNameHelper').text('');
					}
				});
				$('#gCate').blur(function() {
					if($('#gCate').val() == '') {
						$('#gCateHelper').text('상품 카테고리를 입력해주세요');
					} else {
						$('#gCateHelper').text('');
					}
				});
				$('#gPrice').blur(function() {
					if($('#gPrice').val() == '' || isNaN($('#gPrice').val())) {
						$('#gPriceHelper').text('상품 가격은 숫자만 입력해주세요');
					} else {
						$('#gPriceHelper').text('');
					}
				});
				$('#gSangse').blur(function() {
					if($('#gSangse').val() == '') {
						$('#gSangse').val('상품 상세정보를 입력해주세요');
					}
				});
				$('#gSangse').click(function() {
					$('#gSangse').val('');
				})
				
				$('#addBtn').click(function() {
					if($('#gName').val() == '') {
						$('#gNameHelper').text('상품 이름을 입력해주세요');
					} else if($('#gCate').val() == '') {
						$('#gCateHelper').text('상품 카테고리를 입력해주세요');
					} else if($('#gPrice').val() == '' || isNaN($('#gPrice').val())) {
						$('#gPriceHelper').text('상품 가격은 숫자만 입력해주세요');
					} else if($('#gSangse').val() == '') {
						$('#gSangse').val('상품 상세정보를 입력해주세요');
					} else if($('#myImage').val() == '') {
						$('#myImageHelper').text('이미지를 추가해주세요');
					} else {
						$('#insertForm').submit();
					}
				})
				
			});
		</script>
	</head>
	<body>	
	<jsp:include page="/module/headerSub.jsp" />
	<div id="container">
		<div class="content">
			<div class="boardArea">
				<div class="boardWrap">
					<form action="${pageContext.request.contextPath}/Gin/goodsInsertPro.go" id="insertForm" method="post" enctype="multipart/form-data">
						<table class="basic">
							<tr>
								<td>판매자아이디</td>
								<td>
									<input type="hidden" name="gId" size="20" value="${sessionScope.loginId}">
									${sessionScope.loginId}
								</td>
							</tr>
							<tr>
								<td>상품명</td>
								<td>
									<input type="text" name="gName" id="gName" size="20">
									<span id="gNameHelper"></span>
								</td>
							</tr>
							<tr>
								<td>카테고리</td>
								<td>
									<input type="text" name="gCate" id="gCate" size="20">
									<span id="gCateHelper"></span>
								</td>
							</tr>
							<tr>
								<td>가격</td>
								<td>
									<input type="text" name="gPrice" id="gPrice" size="20">
									<span id="gPriceHelper"></span>
								</td>
							</tr>
							<tr>
								<td>상품상세설명</td>
								<td>
									<textarea name="gSangse" id="gSangse" rows="10" cols="30"></textarea>
								</td>
							</tr>
							<tr>
								<td>사진</td>
								<td>
									<input type="file" name="myImage" id="myImage" accept="image/*" />
									<span id="myImageHelper"></span>
								</td>
							</tr>
							<tr>
								<td colspan="4"><input type="button" id="addBtn" value="상품등록버튼"></td>
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