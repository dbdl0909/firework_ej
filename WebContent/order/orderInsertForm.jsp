<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
	
	var price = parseInt($('#gPrice').val());
	var count = parseInt($('#oCount').val());	
	
	$('#buyBtn').click(function(){
		if("<c:out value='${sessionScope.loginId}'/>" == ""){
			
			alert('로그인 후 이용해주세요.')
			$('#loginWrap').show();
		}else{
			$('.orderForm').submit();
		}
	});	
	
	$('#upCount').click(function(){
		
		var count = parseInt($('#oCount').val());	
		$('#oCount').val(count+1);
		$('#oTotal').val(price * (count+1));
		
	});
	
	$('#downCount').click(function(){
		
		if($('#oCount').val() > 1){
			
			var count = parseInt($('#oCount').val());
			$('#oCount').val(count-1);
			$('#oTotal').val(price * (count-1));
		}
		
	});
	
	$('#oCount').focusout(function(){
		
		var count = parseInt($('#oCount').val());
		$('#oTotal').val(price * count);

	});
	
	
	
});
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/jquery.brickfolio.min.css"  rel="stylesheet" >
</head>
<body>
	<form class="orderForm" action="${pageContext.request.contextPath}/Oin/orderInsertPro.oo" method="post">		
		<table class="basic orderForm">
			<tr>
				<td class="orderFormTitle">상품명</td>
				<td>
					<input type="hidden" name="gName" id="gName" value="${goods.g_name}">
					${goods.g_name}
				</td>
			</tr>
			<tr>
				<td class="orderFormTitle">판매자</td>
				<td>
					<input type="hidden" name="gId" id="gId" value="${goods.g_id}">
					${goods.g_id}
				</td>
			</tr>
			<tr>
				<td class="orderFormTitle">가격</td>
				<td>
					<input type="hidden" name="gPrice" id="gPrice" value="${goods.g_price}">
					${goods.g_price}
				</td>
			</tr>
			<tr>
				<td class="orderFormTitle">수량</td>
				<td>
					<input name="oCount" id="oCount" value="1" >
					<span id="upCount">▲ </span> <span id="downCount"> ▼ </span><span id="count"></span>
				</td>
			</tr>
			<tr>
				<td class="orderFormTitle">합계</td>
				<td>
					<input name="oTotal" id="oTotal" value="${goods.g_price}" readonly="readonly">원
				</td>
			</tr>
			<tr>
				<td class="orderFormTitle">
					원산지
				</td>
				<td>
					한국
				</td>
			</tr>
			<tr>
				<td class="orderFormTitle">
					배송
				</td>
				<td>
					<ul>						
					<li>1개당15,000원 </li>
					<li>상품수령시 결제(착불)</li>
					</ul>
				</td>
			</tr>
			<tr>	
				<td></td>
				<td>									
					<input type = "hidden" name="gCode" value="${goods.g_code}">
					<input type = "hidden" name="mId" value="${sessionScope.loginId}">
					<div id="buyBtn">구매</div>
				</td>
			</tr>			
		</table>
	</form>		
</body>
</html>