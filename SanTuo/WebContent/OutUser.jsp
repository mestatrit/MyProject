<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
	<%@page import="java.net.InetAddress"%>
<!DOCTYPE html>
<html >
<head>
<meta charset="utf-8" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<%@include file="../common/public.jsp"%>
<script type="text/javascript" src="/App/js/monitor.js"></script>
<style type="text/css">
.group{
width: 200px;
height: 175px;
float: left;
margin: 10px; 
border:1px solid #c4e3f4; 
}
.groupImg{
margin:auto;
margin-left:16px;
margin-top:13px;    
cursor: pointer; 
}
.tubiao{
height:100%;
width: 28%;
float: right;
background-color: #c4e3f4;
}
</style>
</head>
<body style="background-color: white;">
							<input type="hidden" id="username" value="${user.columns['UNAME']}">
							<input type="hidden" id="userid" value="${user.columns['ID']}">
							<input type="hidden" id="gid" value="${gid}">
							<%-- <div style="width: 100%;height: 30px;background-color: #00CCCC ">
									<span  style="float: left;height: 100%;font-size: 20px;margin-left: 10px;">项目:</span>	 
								  	<div style="float: left;">  
								  		<select id="pid" onchange="change(this)" class="">
										 	<c:forEach var="t" items="${project}">
								   					 <option  value="${t.id}"> ${t.name}</option> 
								  			 </c:forEach>
										</select>	
								  	</div>	  
								  	<span  style="float: left;height: 100%;font-size: 20px;margin-left: 2px;">==>>小组:</span>	 
								  	<div style="float: left;">
										<select onchange="queryUserForGroup(this)" id="gid"  class="">
										 
										</select>	
									</div>	 
							</div> --%>
					<div id="groupDiv" style="width: 100%;overflow: auto;">
							<!-- <div class="group">   
									<div style="height:100%;width: 70%;float: left; ">
										<div style="float: left;width: 100%;height: 40%;"> 
											<table style="width: 100%;height: 100%">
												<tr>
													<td><img style="width: 40%;height: 100%;margin-left: 30px" alt="" src="image/74.png"></td>
													<td style="color: red;font-size: 20px">ss</td>
												</tr>
											</table>
										</div>
										<div style="height: 49%;float: left;font-size: 20px;margin-left: 15px">
										<br>
											姓名:<span>ss</span>
											<br>
											电话:<span>ss</span>
										</div>
									</div>
									<div style="height:100%;width: 28%;float: right;">
										<img class="groupImg"  alt="" src="image/1.png">
										<img class="groupImg"  alt="" src="image/2.png">
										<img class="groupImg"  alt="" src="image/3.png">
										<img class="groupImg"  alt="" src="image/4.png">
									</div>
							</div> -->
					</div>		 

								
</body>

<script type="text/javascript">
function queryUserForGroup(id){
	$.ajax({
				type : "POST",
				url :getval("root")+"/outuser/queryUserForGroup",
				async:false,
				data :{gid:id},
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
					html="";
					$.each(data,function(i,o) {   
						html+=groupDiv(o.ID,o.UNAME,o.PHONE,'00'+i);
					});
				$("#groupDiv").html(html);
				}
			});
}

/* function change(o){
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
	
} */

function groupDiv(id,name,phone,index){
	return '<div class="group">'+
	'			<div style="height:100%;width: 70%;float: left; ">'+
	'			<div style="float: left;width: 100%;height: 40%;"> '+
	'				<table style="width: 100%;height: 100%">'+
	'					<tr>'+
	'						<td><img style="width: 40%;height: 100%;margin-left: 30px" alt="" src="image/74.png"></td>'+
	'						<td style="color: #438eb8;font-size: 20px">'+index+'</td>'+
	'					</tr>'+
	'				</table>'+
	'			</div>'+
	'			<div style="height: 49%;float: left;font-size: 20px;margin-left: 15px">'+
	'			<br>'+
	'				姓名:<span style="font-size: 12px;">'+name+'</span>'+
	'				<br>'+
	'				电话:<span style="font-size: 12px;">'+phone+'</span>'+
	'			</div>'+
	'		</div>'+
	'		<div class="tubiao" style="">'+
	'			<img  onclick="goUrl(\'colshow.jsp?id='+id+'\')" class="groupImg"  alt="定位" src="image/3.png">'+
	'			<img  onclick="openCall(\''+getval("username")+'\',\''+getval("userid")+'\',\''+id+'\')"  class="groupImg"  alt="聊天" src="image/1.png">'+
	'			<img  onclick="openWin(\''+getval("username")+'\',\''+getval("userid")+'\',\''+id+'\',\'<%=InetAddress.getLocalHost().getHostAddress()%>\')"  class="groupImg"  alt="视频" src="image/2.png">'+
	'			<img  onclick="_openTalkWin(\''+getval("username")+'\',\''+getval("userid")+'\',\''+id+'\',\'<%=InetAddress.getLocalHost().getHostAddress()%>\')"  class="groupImg"  alt="语音" src="image/4.png">'+
	'		</div>'+
	'		</div>';
			}
function openCall(name,uid,sid){
	var n=encodeURI(encodeURI(name));
	showModalDialog('/SanTuo/call/call?uname='+n+'&userid='+uid+'&sendid='+sid,'','dialogWidth:1000px;dialogHeight:550px;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
}

function openWin(name,uid,sid,serverhost){
	openMonWin("From:"+name,500,500,serverhost,9005,sid,uid);
}
function _openTalkWin(name,uid,sid,serverhost){
	openTalkWin("From:"+name,500,500,serverhost,9005,sid,uid);
}
			
			
$(function(){
	 queryUserForGroup(getUrlParam("gid"));
});
</script>
</html>
