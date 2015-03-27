<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
<meta charset="utf-8" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<%@include file="../common/public.jsp"%>
</head>
<body style="background-color: white;">
							<input type="hidden" id="cid">
							<table id="grid-table"></table>

							<div id="grid-pager"></div>

								
</body>
<script src="page/task_reward.js"></script>
<script type="text/javascript">
$(function(){
	$("#grid-table").jqGrid('setGridParam',{postData:{cid:getval("cid")}});
});
</script>
</html>
