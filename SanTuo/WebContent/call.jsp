<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../common/public2.jsp"%>
<style type="text/css">
 .chat-body{  
    overflow-y:scroll;
    height:420px;
    padding:0px;
}

.list-body{
    overflow-y:scroll;
    height:450px;
    padding:0px;
}

.msg-list-body{
    margin:8px;
}

.msg-wrap{
    margin-top: 0px;
    margin-bottom: 8px;
    padding: 0px;
}

.msg-content{
    margin-top: 14px;
    padding: 8px;
    padding-bottom: 4px;
    background-color:#f5f5f5;
    border:1px solid #ccc;
    border-radius: 4px;
    word-break:break-all;
}

.img-icon{
    width: 64px;
    height: 64px;
    border:2px solid #ccc;
    border-radius: 4px;
}

.msg-head{
    z-index:100;
}

.msg-name{
    margin-left: 8px;
}

.msg-time{
    margin-left: 8px;
}

.list-table{
    margin-top: -1px;
    margin-bottom: 0px;
}

.emotion-panel{
    position:fixed;
    display:none;
    z-index:200;
}
</style>

</head>
<body style="">
	<!-- 聊天内容 -->
	<div class="panel panel-default">
		<div class="panel-heading">
			<span class="glyphicon glyphicon-earphone"></span> &nbsp;聊天内容
		</div>
		<div class="panel-body chat-body">
			<div id="msgContent" class="msg-list-body">

				<!-- 聊天内容 -->	
			</div> 
		</div>
	</div>

	<!-- 输入框 -->  
	<div class="input-group input-group-lg" style="width: 90%;margin: auto;">
	<input type="hidden" id="uname" value="${pram.uname}">
		<form id="msgForm" action="" onsubmit="return false;">
			<input type="hidden" value="${pram.userid}" id="userid" name="userid">
			<input type="hidden" value="${pram.sendid}" id="sendid" name="sendid">
			<div class="form-group">
				<div class="col-sm-10"> 
					<input class="form-control" onkeydown="kepudown13()" id="msgText" placeholder="请输入聊天内容" type="text" name="callinfo">
				</div>
				<div class="col-sm-2">
					 <span class="input-group-btn">
							<button class="btn btn-default " type="button"  noclick="addMsg()">
							发送<span class="glyphicon glyphicon-send"></span>
							</button>
					</span>
				</div>
			</div>
		</form>
	</div>
</body>

<script type="text/javascript"> 
var myname;
var uname;
$(function(){ 
	setInterval(queryData, 5000); 
});

$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		addMsg();
	}
});

	function addMsg(){
		//showModalDialog('http://127.0.0.1:8888/Tuoke/index2.jsp','','dialogWidth:1000px;dialogHeight:700px;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
		$.ajax({
					type : "POST",
					url :getval("root")+"/call/insert",
					async:false,
					data :$("#msgForm").serializeArray(),
					contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					success : function(data) {
						var uname=decodeURI($("#uname").val());
						$("#msgContent").append(getRedcontent(uname,newTime(),$("#msgText").val()));
						$("#msgText").val("");
					}
				});
	}
	function queryData(){
		 $.ajax({
					type : "POST",
					url : "/Tuoke/call/queryInfo",
					async:false,
					data :{uid:getval("sendid"),sid:getval("userid")},
					contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					success : function(data) {
						//取数据
						$.each(data,function(i,o) { 
							$("#msgContent").append(getInfocontent(o.SENDUSERNAME,o.CALLDATE,o.CALLINFO));
							lookInfo(o.CALLID);
						});
					}
				}); 
	
	}

	function lookInfo(id){
		$.ajax({
					type : "POST",
					url : "/Tuoke/call/lookInfo",
					async:false,
					data :{id:id},
					contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					success : function(data) {
					}
				});
	}

	function getRedcontent(name,time,msg){
		window.scrollTo(0,document.body.scrollHeight);
		return '<div class="clearfix msg-wrap">	<div class="msg-head">'
		+'<span class="msg-name label label-danger pull-left"><span '
		+' class="glyphicon glyphicon-user">'+name+'</span>&nbsp;&nbsp;</span><span' 
		+' class="msg-time label label-default pull-left"><span'
		+' class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;'+time+'</span>'
		+'</div><div class="msg-content">'+msg+'</div>'
		+'</div>';
	}	
	
	function getInfocontent(name,time,msg){
		return '<div class="clearfix msg-wrap">	<div class="msg-head">'
		+'<span class="msg-name label label-primary pull-left"><span'
		+' class="glyphicon glyphicon-user"></span>&nbsp;&nbsp;'+name+'</span><span'
		+' class="msg-time label label-default pull-left"><span'
		+' class="glyphicon glyphicon-time"></span>&nbsp;&nbsp;'+time+'</span>'
		+'</div><div class="msg-content">'+msg+'</div>'
		+'</div>';
	}	
	
	function newTime(){
		var date=new Date();
		return date.getHours() + ":" + date.getMinutes() + ":"+ date.getSeconds();
	}
</script>
</html>