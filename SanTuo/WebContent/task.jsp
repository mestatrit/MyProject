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
               		选择项目 ： <select style="" onchange="queryUG()" id="project_t">
   							
   						</select>
            </h4>
         </div>
         <div class="modal-body">
         <div class="form-horizontal">
      	<form id="fsForm">
  				<div class="form-group">
  						<label  class="col-sm-2 control-label" style="cursor: pointer;"></label>
    					<div id="userdiv" class="col-sm-10" style="height: 310px;overflow: auto;">
    							 <div style="width: 100%"> 
	    							<table id="usertable">
	    								
	    							</table>

    							 </div>
    					</div>
  				</div>
  			</form> 
           	   <br>
	         
	        </div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" onclick="pasong()" class="btn btn-primary">
            		发送
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
							<input type="hidden" id="cid">
							<input type="hidden" id="fsid">
							<table id="grid-table"></table>

							<div id="grid-pager"></div>

								
</body>
<script src="page/task.js"></script>
<script src="js/userForGroup.js"></script>
<script type="text/javascript">
$(function (){
	$.ajax({
				type : "POST",
				url :getval('root')+"/queryProject",
				async:false,
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					var html="";
					$.each(data,function(i,o) {   
						html+='<option value="'+o.ID+'">'+o.NAME+'</option>';
					});
					$("#project_t").html(html);
				}
			});
		queryUG();
});

function queryUG(){
	queryDY(getval("root")+"/au/queryUserForGroup?pid="+$("#project_t").val());
}

function pasong(){
	$.ajax({
				type : "POST",
				url :getval("root")+"/task/pasong",
				async:false,
				data :{id:$("#fsid").val(),userid:area_getCity()},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					alert("派送成功");
					$("#myModal").modal('hide');
				}
			});
}

function fasong(id){
	$("#fsid").val(id);
	$.ajax({
				type : "POST",
				url :getval("root")+"/task/alluer",
				async:false,
				data :{id:id},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					$.each(data,function(i,o) {   
						$("#areaddis"+o.ID).attr("disabled",true);
					});
				}
			});
	$("#myModal").modal('show');
}
</script>
</html>
