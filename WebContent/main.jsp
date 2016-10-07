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
				<!-- 기본적으로 a태그는 div를 감쌀 수 없지만 html5에 와서 허용이 된다. -->
				<a href="javascript:void(0)">
					<div class="bf-item">
						<img src="img/main2.jpg">
						<h4>Quare attende, quaeso</h4>
						<p>Quare attende, quaeso. Sed plane dicit quod intellegit. Non semper, inquam; Nos commodius agimus. Illa tamen simplicia, vestra versuta. Moriatur, inquit. Et nemo nimium beatus est; Haec dicuntur inconstantissime.</p>
					</div>
				</a>
				<a href="javascript:void(0)">
					<div class="bf-item">
						<img src="img/main6.jpg">
						<h4>Bonum incolumis acies</h4>
						<p>Bonum incolumis acies: misera caecitas. Istam voluptatem, inquit, Epicurus ignorat? Confecta res esset. Iam contemni non poteris. Ecce aliud simile dissimile. Audeo dicere, inquit.</p>
					</div>
				</a>
				<a href="javascript:void(0)">
					<div class="bf-item">
						<img src="img/main7.jpg">
						<h4>Bonum incolumis acies</h4>
						<p>Bonum incolumis acies: misera caecitas. Istam voluptatem, inquit, Epicurus ignorat? Confecta res esset. Iam contemni non poteris. Ecce aliud simile dissimile. Audeo dicere, inquit.</p>
					</div>
				</a>
				<a href="javascript:void(0)">
					<div class="bf-item">
						<img src="img/main8.jpg">
						<h4>Bonum incolumis acies</h4>
						<p>Bonum incolumis acies: misera caecitas. Istam voluptatem, inquit, Epicurus ignorat? Confecta res esset. Iam contemni non poteris. Ecce aliud simile dissimile. Audeo dicere, inquit.</p>
					</div>
				</a>
				<a href="javascript:void(0)">
				<div class="bf-item">
					<img src="img/main9.jpg">
					<h4>Bonum incolumis acies</h4>
					<p>Bonum incolumis acies: misera caecitas. Istam voluptatem, inquit, Epicurus ignorat? Confecta res esset. Iam contemni non poteris. Ecce aliud simile dissimile. Audeo dicere, inquit.</p>
				</div>
				<a href="javascript:void(0)">
					<div class="bf-item">
						<img src="img/main1.jpg">
						<h4>Lorem ipsum dolor sit</h4>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Eadem nunc mea adversum te oratio est. Itaque his sapiens semper vacabit. Tamen a proposito, inquam, aberramus. Prave, nequiter, turpiter cenabat;</p>
					</div>
				</a>
				<a href="javascript:void(0)">				
				<div class="bf-item">
					<img src="img/main3.jpg">
					<h4>Duo Reges</h4>
					<p>Duo Reges: constructio interrete. Tu quidem reddes; Nihilo magis. Haec igitur Epicuri non probo, inquam.</p>
				</div>
				</a>
				<a href="javascript:void(0)">
					<div class="bf-item">
						<img src="img/main4.jpg">
						<h4>Vide, quaeso, rectumne</h4>
						<p>Vide, quaeso, rectumne sit. Nihil ad rem! Ne sit sane; Tibi hoc incredibile, quod beatissimum. Falli igitur possumus. Easdemne res?</p>
					</div>
				</a>
				<a href="javascript:void(0)">								
					<div class="bf-item">
						<img src="img/main5.jpg">
						<h4>Bonum incolumis acies</h4>
						<p>Bonum incolumis acies: misera caecitas. Istam voluptatem, inquit, Epicurus ignorat? Confecta res esset. Iam contemni non poteris. Ecce aliud simile dissimile. Audeo dicere, inquit.</p>
					</div>
				</a>	
			</div>
		</div>
	</div>
</div>
<jsp:include page="/module/footer.jsp" />
</body> 
</html>