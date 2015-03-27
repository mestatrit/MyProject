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
   						<label  class="col-sm-2 control-label" style="cursor: pointer;">地域</label>
    					<div id="diyudiv" class="col-sm-10 " style="height: 310px;overflow: auto;">
    							 <div style="width: 100%"> 
	    							<table id="diyuTable">
	    								
	    							</table>

    							 </div>
    					</div>
  				</div> 
           	   <br>
	         
	        </div>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button type="button" onclick="area_set()" class="btn btn-primary">
            		保存
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
							<input type="hidden" id="area_id">
							<table id="grid-table"></table>

							<div id="grid-pager"></div>

								
</body>
<script src="page/account_company.js"></script>
<script src="js/area.js"></script>
<script type="text/javascript">
$(function (){
	queryDY(getval("root")+"/diyu",diyuTable);
});
function area_set(){
	$("#myModal").modal('toggle');
	$("#AREA").val(area_getCity);
	$("#"+getval("area_id")).val(area_getCity);
}
</script>
</html>
