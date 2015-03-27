<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">

<div class="modal fade" id="addGroupModal" tabindex="-1" style="z-index: 99999" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close"  data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
               		添加组
            </h4>
         </div>
         <div class="modal-body">
         <div class="form-horizontal">
         <form id="addGroupForm">
         		<input type="hidden" name="pid" id="pid">
  				<div class="form-group">
   						<label  class="col-sm-2 control-label" style="cursor: pointer;">组名</label>
    					<div class="col-sm-10">
    						<input class="form-control" id="groupName" name="name" type="text" >
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
            <button type="button" onclick="saveGroup()" class="btn btn-primary">
            		保存
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>


    		<div class="navbar navbar-default" id="navbar">
			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							善拓后台管理系统
						
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->
		
				<div class="navbar-header pull-right" role="navigation">
				
				<a href="#" onclick="goifm('report_review.jsp')" class="navbar-brand">
					<small> 审阅:<span id="shenyue" >0</span></small>
				</a>
				<a href="#" onclick="goifm('waring_history.jsp')" class="navbar-brand">
					<small> 报警:<span id="warning" >0</span></small>
				</a>
				
				<a href="#" class="navbar-brand">
					<small><button onclick="logout()" class="btn btn-info">退出</button> </small>
				</a>	
				</div><!-- /.navbar-header -->
			</div>
		</div>

		<div class="main-container" id="main-container">
		
			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">

					<ul id="ulList" class="nav nav-list">
						<li id="qx1" class="open">
							<a href="#"  class="dropdown-toggle">
								<i class="glyphicon glyphicon-cog"></i><span class="menu-text">账号管理 </span>
								<b class="arrow icon-angle-down"></b>
							</a>
							<ul  class="submenu">
								<li id="qx2" ><a href="#" onclick="goifm('account_company.jsp')"><i class="icon-double-angle-right"></i>公司管理</a></li>
								<li id="qx3"><a href="#" onclick="goifm('account_project.jsp')"><i class="icon-double-angle-right"></i>项目管理</a></li>
								<li id="qx4"><a href="#" onclick="goifm('ar')"><i class="icon-double-angle-right"></i>角色管理</a></li>
								<li id="qx5"><a href="#" onclick="goifm('au')"><i class="icon-double-angle-right"></i>用户管理</a></li>
							</ul>
						</li>
						<li id="qx6"> 
							<a href="#"  class="dropdown-toggle">
								<i class="icon-group" ></i><span class="menu-text">外场人员管理</span>
								<b class="arrow icon-angle-down"></b>
							</a>
							<div id="tree" style="display: none;">
							</div>
							
						</li>
						<li id="qx7">
							<a href="#" class="dropdown-toggle"> 
								<i class="glyphicon glyphicon-edit"></i><span class="menu-text">任务管理 </span>
								<b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
								<li id="qx8"><a href="#" onclick="goifm('task_rewoard.jsp')" ><i class="icon-double-angle-right"></i>奖励措施</a></li>
								<li id="qx9"><a href="#" onclick="goifm('task.jsp')"><i class="icon-double-angle-right"></i>任务设定</a></li>
								<li id="qx10"><a href="#" onclick="goifm('task_over.jsp')"><i class="icon-double-angle-right"></i>任务统计</a></li>
							</ul>
						</li>
						<li  id="qx11">
							<a href="#" onclick="goifm('leave_electric.jsp')"  class="dropdown-toggle">
								<i class="glyphicon glyphicon-phone-alt"></i><span class="menu-text">留电管理 </span>
							</a>
						</li>
						<li  id="qx14">
							<a href="#" onclick="goifm('car_apply.jsp')"  class="">
								<i class="icon-dashboard"></i><span class="menu-text">用车申请 </span>
							</a>
						</li>
						<li  id="qx16">
							<a href="#" onclick="goifm('report_review.jsp')" class="">
								<i class="glyphicon glyphicon-check"></i><span class="menu-text">审阅</span>
							</a>
						</li>
						<li id="qx15" class="">
							<a href="#" onclick="goifm('report')" class="">
								<i class="icon-hdd"></i><span class="menu-text">上报 </span>
							</a>
						</li>
						<li  id="qx17">
							<a href="#" class="dropdown-toggle">
								<i class="glyphicon glyphicon-record"></i><span class="menu-text">预警 </span>
								<b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu"> 
								<li><a href="#" onclick="goifm('warning.jsp')" ><i class="icon-double-angle-right"></i>划定预警圈</a></li>
								<li><a href="#" onclick="goifm('waring_history.jsp')"><i class="icon-double-angle-right"></i>报警历史记录</a></li>
							</ul>
						</li>
					</ul>


					<div class="sidebar-collapse" id="">
						<i class="icon-double-angle-left"></i>  
					</div>
				</div>
			</div>
		</div>
		
		
		<script type="text/javascript">
		         var b=true;
							$(function(){
								loadTree();
								setInterval(sansuo, 500);
								queryStatus();
								setAuthority();
							//	setInterval(queryStatus, 5000);
							});
							//权限
							function setAuthority(){
								$("li[id^=qx]").hide();
								$.ajax({
											type : "POST",
											url :getval('root')+"/user/queryAuthority",
											async:false,
											contentType : 'application/x-www-form-urlencoded;charset=utf-8',
											success : function(data) {
												$.each(data,function(i,o) {   
												$("#qx"+o.FID).show();
												});
											}
										});
							}
							//状态
							function queryStatus(){
								$.ajax({
									type : "POST",
									url :getval("root")+"/status/query",
									async:false,
									data :{},
									contentType : 'application/x-www-form-urlencoded;charset=utf-8',
									success : function(data) {
										if(data.r>0){
										$("#shenyue").html(data.r);
										$("#shenyue").addClass("sansuo");
										}else{
											$("#shenyue").removeClass("sansuo");
										}
										if(data.w>0){
											$("#warning").html(data.w);
											$("#warning").addClass("sansuo");
											}else{
												$("#warning").removeClass("sansuo");
											}
										
									}
								});
							}
							
							function sansuo() {
								if (b) {
									b = false;
									$(".sansuo").css("color", "blue");
								} else {
									b = true;
									$(".sansuo").css("color", "red");
								}
							}
							
							function logout(){
								window.location.href=getval("root")+'/logout';
							}
							//加载树结构
							function loadTree(){
								$.ajax({
									type : "POST",
									url :getval("root")+"/outuser/queryTree",
									async:false,
									data :{cid:10},
									contentType : 'application/x-www-form-urlencoded;charset=utf-8',
									success : function(data) {
										$('#tree').treeview({data: data}); 
									}
								});
							}
							
							function getGtoupHTml(id,name){
								return 	'<li style="" data-nodeid="2" class="list-group-item node-tree"><span class="indent"></span><span class="indent"></span><span class="expand-collapse glyphicon"></span><span class="icon glyphicon glyphicon-menu-hamburger"></span><span id="group'+id+'" onclick="goifm(\'OutUser.jsp?gid='+id+'\')">'+name+'&nbsp;&nbsp;<span title="删除组" onclick="delGroup(\''+id+'\')" class="glyphicon glyphicon-minus-sign"></span></span></li>';
							}
							
							function delGroup(id){
								if(confirm("删除后组下账号一并删除")){
									 $.ajax({
												type : "POST",
												url :getval("root")+"/au/delGroup",
												async:false,
												data :{gid:id},
												contentType : 'application/x-www-form-urlencoded;charset=utf-8',
												success : function(data) {
													loadTree();
												}
											}); 
											
								}
							}
							
							function saveGroup(){
								$.ajax({
											type : "POST",
											url :getval("root")+"/au/addGroup",
											async:false,
											data :$("#addGroupForm").serialize(),
											contentType : 'application/x-www-form-urlencoded;charset=utf-8',
											success : function(data) {
												loadTree();
												$("#addGroupModal").modal('toggle');
											}
										});
							}
							function addGroup(id){
										$("#pid").val(id);
										$("#addGroupModal").modal('show');
							}
						</script>