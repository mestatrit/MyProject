<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
<head>
<meta charset="utf-8" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<%@include file="../common/public.jsp"%>
<style type="text/css">

</style>
</head>
<body style="background-color: white;">

	<div class="modal fade "  id="myModal" tabindex="-1"
		style="z-index: 99999" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg"> 
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">审阅</h4>
				</div>
				<div class="modal-body">
					<div class="form-horizontal">
						<span id="content" style="width: 90%;margin: auto;">
						
						</span>
						<div id="reviews">
					
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<input type="hidden" id="reportid">
					<div id="btn_hideen">
					<button type="button" onclick="shenpi(2)" class="btn btn-default" data-dismiss="modal">驳回</button>
					<button type="button" onclick="shenpi(1)" class="btn btn-primary">通过</button>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>


	<table id="grid-table"></table>

							<div id="grid-pager"></div>

								
</body>
<script src="${root}/page/report_review.js"></script>
<script type="text/javascript">
//审阅 2驳回1通过
function shenpi(type){
	$.ajax({
				type : "POST",
				url :getval("root")+"/report/shenpi",
				async:false,
				data :{type:type,id:getval("reportid")},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					if(data){
						alert("成功");
					}else{
						alert("异常");
					}
					window.location.reload();
				}
			});
}

function shenyue(id){
	$("#btn_hideen").show();
	$("#reportid").val(id);
	$.ajax({
				type : "POST",
				url :getval("root")+"/report/review_show",
				async:false,
				data :{id:id},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
				$("#content").html(data.content);
				var html="";
				$.each(data.reviews,function(i,o) {
					var st='';
					if(o.STATUS==1){
						st='通过'
					}else if(o.STATUS==2){
						st="驳回";
					}else{
						st="未审批";
					}
					html+="<hr><h4>审批人："+o.UNAME+"<br> 审批时间: ";
					try {
						html+=o.CREATETIME.substring(0,19)
					} catch (e) {
					}
					html+=" <br>状态 : "+st+"<h4>";
				});
				$("#reviews").html(html);
				if(data.isre){
					$("#btn_hideen").hide();
				}
				}
			});
	
	$("#myModal").modal('show');
}
</script>
</html>
