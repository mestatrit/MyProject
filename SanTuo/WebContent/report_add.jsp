<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% pageContext.setAttribute("root", request.getContextPath()); %>
<!DOCTYPE html>
<html >
<head>
<meta charset="utf-8" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<link rel="stylesheet" href="${root}/assets/css/chosen.css" />
<link href="${root}/assets/css/bootstrap.min.css" rel="stylesheet" />
<script src="${root}/assets/js/jquery-2.0.3.min.js"></script>
<link rel="stylesheet" href="${root}/assets/css/jquery-ui-1.10.3.full.min.css" />
<script src="${root}/assets/js/bootstrap.min.js"></script> 
<script src="${root}/assets/js/chosen.jquery.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${root}/ueditor/ueditor.all.min.js"> </script>
<style type="text/css">
.glyphicon{
top: 2px;
margin-left: 5px;
}
.titlediv{
height: 40px;
line-height: 24px;
color: #FFF;
background: none repeat scroll 0% 0% #307ECC;
padding: 0px;
font-size: 15px;
}
.titlet{
float: left;
margin: 8px;
}
</style>
</head>

<body style="background-color: white;">

<div class="titlediv">
	<span class="titlet">上报</span>
</div>
<br>
<div class="row" style="width: 90%">
<form role="form"  class="form-horizontal" method="post" style="width:100%;height: 100%;"  id ="reportFrom" action="${root}/report/save">
					<input type="hidden" name="pid" value="${userid}">
					<input type="hidden" name="cid" value="${cid}">
					//<input type="hidden" id="people"  name="people">
					<div class="form-group">
   							<label  class="col-sm-2 control-label">主题 :</label>
    						<div class="col-sm-10">
     							 <input name="title"  class="form-control"  placeholder="请输入主题">
    						</div>
  					</div>
  					<div class="form-group">
   							<label  class="col-sm-2 control-label">审核人 :</label>
    						<div class="col-sm-10">
     							<select multiple="multiple"  style="" class="width-60 chosen-select form-control" id="peoples" data-placeholder="添 加 审 批 人">
     							<c:forEach var="p" items="${peoples}">
										<option value="${p.ID}">${p.UNAME}</option>		
								</c:forEach>				
								</select>
    						</div>
  					</div>
  					<div class="form-group">
   							<label  class="col-sm-2 control-label">审核级别 :</label>
    						<div class="col-sm-10">
    							<label class="radio-inline">
     								<input  type="radio" name="rtype" checked="checked" value="2">同级
     							</label>
    							<label class="radio-inline">
     								<input  type="radio" name="rtype"  value="1">逐级
     							</label>
    						</div>
  					</div>
  					<div class="form-group">
   							<label  class="col-sm-2 control-label">正文 :</label>
    						<div  class="col-sm-10">
    						 	<script id="editor" name="content" type="text/plain" style="width:100%;height:240px;">再此填写或粘贴指标的相关文件正文</script>					
    						 
    						</div> 
    				</div>
    				<div class="form-group">
   							<label  class="col-sm-2 control-label">备注 :</label>
    						<div  class="col-sm-10">
								<textarea name="remark" class="form-control" rows="2" cols="20"></textarea>    						 
    						</div>
    				</div>			
    				<div class="form-group">
   							<label  class="col-sm-2 control-label"></label>
    						<div  class="col-sm-8 ">
    						</div>
    						<div  class="col-sm-1 ">
    						 	 <button type="button" onclick="clear()" class="btn btn-default ">清空</button>
    						</div>
    						<div  class="col-sm-1 ">
    						 	 <button type="button" onclick="saveFrom()" class="btn btn-info ">发送</button>
    						</div>
    				</div>		

</form>
</div>

</body>
<script type="text/javascript">
function saveFrom(){
	$("#people").val($("#peoples").val());
	$.ajax({
				type : "POST",
				url : "/SanTuo/report/save",
				async:false,
				data :$("#reportFrom").serialize(),
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					if(data){
						alert("发送成功");
						$('#reportFrom')[0].reset();
						ue.reset();
					}
				}
			}); 
}

function clear(){
	 ue.execCommand('cleardoc');
}

var ue = UE.getEditor('editor',{
	autoClearinitialContent :true
}); 

$(".chosen-select").chosen(); 
$('#chosen-multiple-style').on('click', function(e){
	var target = $(e.target).find('input[type=radio]');
	var which = parseInt(target.val());
	if(which == 2) $('#form-field-select-4').addClass('tag-input-style');
	 else $('#form-field-select-4').removeClass('tag-input-style');
});

</script>
</html>
