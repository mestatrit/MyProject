<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@include file="../common/public2.jsp"%>
</head>

<body style="background-color:black;background-image: url('images/2.jpg');"> 
	<div style="height: 150px"></div>    
	<div class="panel" style="margin: auto;width: 380px;height: 180px;background-color: white;">   
			<div style="margin: auto;width: 80%;">  
			<form id="loginForm" action="" class="form-horizontal">  
						<div style="height: 20px;"></div>
						<div class="input-group">
 							 <span class="input-group-addon" id="basic-addon1"><li class="glyphicon glyphicon-user"></li></span>
  							<input type="text" class="form-control" placeholder="请输入登陆账号" id="lname" name="lname">
						</div>
						<div style="height: 20px;"></div>
						<div class="input-group">
 							 <span class="input-group-addon" id="basic-addon1"><li class="glyphicon glyphicon-lock"></li></span>
  							<input type="password" class="form-control" placeholder="请输入登陆密码" id="pass" name="pass">
						</div>
			</form>
			</div>
			 	<div style="width: 95%">
			 		<div style="height: 20px"></div>
                    <span class="pull-right"><input type="button" onclick="login()" class="btn btn-primary" value="Login" /></span>
                </div>
	</div>
</body>

<script type="text/javascript">
	function login(){
		$.ajax({
					type : "POST",
					url : getval("root")+"/user/login",
					async:false,
					data :$("#loginForm").serialize(),
					contentType : 'application/x-www-form-urlencoded;charset=utf-8',
					success : function(data) {
						if(data.success){
							goUrl(getval("root")+"/index.jsp")
						}else{
							alert(data.info)
						}
					}
				});
	}
</script>
</html>