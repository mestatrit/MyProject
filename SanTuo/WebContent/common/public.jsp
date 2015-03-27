<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% pageContext.setAttribute("root", request.getContextPath()); %>
<title>拓客管理系统</title>
<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="expires" CONTENT="0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<input type="hidden" id="root" value="/SanTuo">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${root}/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${root}/assets/css/ace.min.css" />
		<link href="${root}/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${root}/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${root}/assets/css/jquery-ui-1.10.3.full.min.css" />
		<link rel="stylesheet" href="${root}/assets/css/datepicker.css" />
		<link rel="stylesheet" href="${root}/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${root}/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${root}/assets/css/ace-skins.min.css" />
		<script src="${root}/assets/js/jquery-2.0.3.min.js"></script>
		<script src="${root}/assets/js/bootstrap.min.js"></script>
		<script src="${root}/assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="${root}/assets/js/ace.min.js"></script>
		<script src="${root}/assets/js/ace-extra.min.js"></script>
		<script src="${root}/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${root}/assets/js/jqGrid/i18n/grid.locale-en.js"></script>
		<script src="${root}/assets/js/fuelux/data/fuelux.tree-sampledata.js"></script>
		<script src="${root}/assets/js/ace-elements.min.js"></script>
	<%-- 	<script src="${root}/js/bootstrap-datepicker.js"></script> --%>
		<script src="${root}/js/bootstrap-treeview.js"></script>
		<script src="${root}/js/common.js"></script>
		<link rel="stylesheet" href="${root}/js/ztree/css/zTreeStyle/zTreeStyle.css" />
		<script type="text/javascript" src="${root}/js/ztree/jquery.ztree.all-3.5.js"></script>
		