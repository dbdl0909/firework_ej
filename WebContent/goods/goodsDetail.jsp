<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/reset.css"  rel="stylesheet" >
<link href="${pageContext.request.contextPath}/css/style.css"  rel="stylesheet" >
<script src="${pageContext.request.contextPath}/js/jquery-2.1.4.js" type="text/javascript"></script>
<script>
	var qnaUrl = "${pageContext.request.contextPath}/Qna/QnaGcodeList.qn?gCode=${goods.g_code}";
	var rebUrl = "${pageContext.request.contextPath}/Reb/rebList.reb";
	$(document).ready(function(){
		$.get(qnaUrl, {page:1}, function(data){

			$('#list').html(data);	
		
		});
		
		
		
		$.get(rebUrl, {page:1}, function(data){
			
			$('#list2').html(data);	
		
		});
		
		$('.goodsDetailTapBtn').eq(0).addClass('on');
		
		$('.goodsDetailTapContent').eq(0).show();
		
		$('.goodsDetailTapBtn').click(function(){
			
			var tapIdx = $('.goodsDetailTapBtn').index(this);	
			
			$('.goodsDetailTapContent').hide();
			
			$('.goodsDetailTapContent').each(function(){
				
				var contentIdx = $('.goodsDetailTapContent').index(this);
				
				if(contentIdx == tapIdx){
					
					$(this).show();
					
				}
				
			});
			
			$('.goodsDetailTapBtn').removeClass('on')
			
			if(!($(this).hasClass('on'))){
				
				$(this).addClass('on');
				
			}			 
			
		});
		
	});	
	
	function getPage(no){
		$.get(qnaUrl, {page:no}, function(data){
			
			$('#list').html(data);	
			
		});
		
	}
	
	function getNextBlock(no){
		
		$.get(qnaUrl, {page:no}, function(data){
			
			$('#list').html(data);	
			
		});
		
	}
	

	
	function getPage2(no){
		$.get(rebUrl, {page:no}, function(data){
			
			$('#list2').html(data);	
			
		});
		
	}
	
	function getNextBlock2(no){
		
		$.get(rebUrl, {page:no}, function(data){
			
			$('#list2').html(data);	
			
		});
		
	}
	
	

</script>
</head>
<body>
<jsp:include page="/module/headerSub.jsp" />
<div id="container"> 
	<div class="content">
		<div id="detailContent">
			<div id="detailImgArea">
				<img src="data:image/${gImageType};base64, ${b64}" alt="image not found" width="100%" height="100%"/>
			</div>
			<div id="orderForm">
				<jsp:include page="/order/orderInsertForm.jsp" />
			</div>
		</div>
		<table class="basic tapTable">
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<tr>
				<th class="goodsDetailTapBtn">상품정보</th>
				<th class="goodsDetailTapBtn">상품리뷰</th>
				<th class="goodsDetailTapBtn">상품Q&A</th>
				<th class="goodsDetailTapBtn">판매자/반품/교환정보</th>
			</tr>
			<tr class="goodsDetailTapContent">
				<td colspan="4">
					<div>
						${goods.g_sangse}
					</div>
				</td>
			</tr>
			<tr class="goodsDetailTapContent">
				<td colspan="4">
					<div id="list2">
						
					</div>
				</td>
			</tr>
			<tr class="goodsDetailTapContent">
				<td colspan="4">
					<div id="list">
						
					</div>
				</td>
			</tr>
			<tr class="goodsDetailTapContent">
				<td colspan="4">
					<div id="sellerInfo">
						<img src="../img/sellerInfo.jpg" />
					</div>
				</td>
			</tr>
		</table>
	</div>
</div>	
<jsp:include page="/module/footer.jsp" />	
</body>
</html>