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
               		随机生成小蜜蜂
            </h4>
         </div>
         <div class="modal-body">
         <div class="form-horizontal">
      	<form id="addForm">
  				<div class="form-group">
   						<label  class="col-sm-2 control-label">数量</label>
    					<div class="col-sm-10">
     						 <input id="number" name="number" class="form-control"  placeholder="请输入数量">
    					</div>
  				</div>
  				<div class="form-group">
   						<label  class="col-sm-2 control-label">角色</label>
    					<div class="col-sm-10">
     						 	<select name="rid" onclick="change(this)" class="form-control" placeholder="请选择类型"> 
								   <c:forEach var="t" items="${role}">
								    <option  value="${t.id}"> ${t.name}</option> 
								   </c:forEach>
								</select>
    					</div>
  				</div>
  				<div class="form-group">
   						<label  class="col-sm-2 control-label">项目</label>
    					<div class="col-sm-10">
     						 	<select name="pid" onclick="change(this)" class="form-control" placeholder="请选择类型"> 
								   <c:forEach var="t" items="${project}">
								    <option  value="${t.id}"> ${t.name}</option> 
								   </c:forEach>
								</select>
    					</div>
  				</div>
  				<div class="form-group">
   						<label  class="col-sm-2 control-label">组</label>
    					<div class="col-sm-10">
    						<select id="gid" name="gid"  class="form-control" placeholder="请选择类型"> 
								  
							</select>
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
            <button type="button" onclick="addAccount()" class="btn btn-primary">
            	生成
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>


							<table id="grid-table"></table>

							<div id="grid-pager"></div>

								
</body>
<script src="page/account_manager.js"></script>
<script type="text/javascript">
function addAccount(){
	$.ajax({
				type : "POST",
				url : getval("root")+"/au/addAccount",
				async:false,
				data :$(addForm).serialize(),
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					if(data){
						$("#grid-table").jqGrid().trigger('reloadGrid');
						$("#myModal").modal('toggle');
					}else{
						alert("添加异常");
					}
				}
			});
}
function change(o){
	$.ajax({
				type : "POST",
				url :getval("root")+"/au/zhu",
				async:false,
				data :{pid:$(o).val()},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					var html="";
					$.each(data,function(i,o) {   
						html+=' <option  value="'+o.ID+'">'+o.NAME+'</option> ';
					});
					$("#gid").html(html);
				}
			});
	
}
</script>
</html>
