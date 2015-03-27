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
<div class="modal fade" id="myModal" tabindex="-1" style="z-index: 99999" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               		选择地域
            </h4>
         </div>
         <div class="modal-body">
         <div class="form-horizontal">
  				<div class="form-group">
   						<label  class="col-sm-3 control-label" style="cursor: pointer;">权限:</label>
   						<input type="hidden" id="qxrid">
    					<div id="qxdiv" class="col-sm-9 " style="height: 310px;overflow: auto;">
    						<div><label>  <input type="checkbox"> 啊啊啊  </label></div>
    					</div>
  				</div> 
           	   <br>
	         
	        </div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" onclick="saveqx()" class="btn btn-primary">
            		保存
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>


							<table id="grid-table"></table>

							<div id="grid-pager"></div>
</body>
<script src="page/account_role.js"></script>

<script type="text/javascript">
$(function(){
	$.ajax({
				type : "POST",
				url :getval("root")+"/queryAuthority",
				async:false,
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					var html="";
					$.each(data,function(i,o) {   
						html+='<div style="width:49%;float:left"><label><input id="qx'+o.ID+'" value="'+o.ID+'" type="checkbox">&nbsp;&nbsp;'+o.NAME+' </label></div>';
					});
					$("#qxdiv").html(html);
				}
			});
	//$("#grid-table").jqGrid('setGridParam',{postData:{cid:getval("cid")}});
	
});
//保存权限
function saveqx(){
	var fid='';
	$.each($("input[id^=qx]"),function(i,o) {   
		if($(o).is(":checked") )fid+=$(o).val()+",";
	});
	if(fid.length>0)fid=fid.substring(0, fid.length-1);
	$.ajax({
				type : "POST",
				url :getval("root")+"/ar/saveAuthority",
				async:false,
				data :{rid:$("#qxrid").val(),fid:fid},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					alert("成功");
					$("#myModal").modal('hide');
				}
			});
}

function qxCheck(id){
	$("#myModal").modal('show');
	$("#qxrid").val(id);
	$("input[id^=qx]").prop("checked",false);
	$.ajax({
		type : "POST",
		url :getval("root")+"/queryAuthority",
		data:{rid:id},
		async:false,
		contentType : 'application/x-www-form-urlencoded;charset=utf-8',
		success : function(data) {
			$.each(data,function(i,o) {  
				$("#qx"+o.ID).prop("checked",true);
			});
		}
	});
}
</script>
</html>
