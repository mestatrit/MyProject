<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
<meta charset="utf-8" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<%@include file="../common/public.jsp"%>
<style type="text/css">
.list-group {
	padding-left: 0;
	margin-bottom: 0px;
}
</style>
</head>

<body>
<c:if test="${user==null}">
			<script type="text/javascript">
				goUrl('login.jsp');
			</script>
</c:if>

<%@include file="../common/main.jsp"%>
		<div class="main-content">
			<%@include file="../common/head.jsp"%>
			
			<div id="iframediv" style="width: 100%;" >
				<iframe id="iframe" width="100%" height="100%;" name="iframe" style="" 	scrolling="auto" frameborder="0" src=""> </iframe> 
			</div>
				
		</div>
</body>
<script type="text/javascript">
var heigth = $(window).height();
var width = $(window).width();
$("#iframediv").height(heigth -100);
//ui-jqgrid-title
</script>
</html>
