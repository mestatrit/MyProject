<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<%@include file="../common/public.jsp"%>

</head>
<body style="background-color: white;">

				<div class="zTreeDemoBackground left">
							<ul id="treeDemo" class="ztree"></ul>
				</div>
				<button onclick="show()">show</button>
</body>

<script type="text/javascript">
var treeObj;
	var list = new Array();
	function getTreeData(id, pId, name,iconOpen,iconClose) {
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.iconOpen=iconOpen;
		this.iconClose=iconClose;
	}
	function getTreeData2(id,pid,name){
		this.id=id;
		this.pId=pid;
		this.name=name;
		return this;
	}
	
	var setting = {
			check: {
				enable: true,
				chkboxType:{ "Y" : "ps", "N" : "ps" }
			},
			data: {
				simpleData: {
					enable: true,
				}
			}
		};
		var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1",},
			{ id:11, pId:1, name:"随意勾选 1-1" },
			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			{ id:12, pId:1, name:"随意勾选 1-2"},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2"},
			{ id:21, pId:2, name:"随意勾选 2-1"},
			{ id:22, pId:2, name:"随意勾选 2-2"},
			{ id:221, pId:22, name:"随意勾选 2-2-1"},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:11, pId:1, name:"随意勾选 2-3"}
		];
		console.log(zNodes);
		$(document).ready(function(){
			$.ajax({
				type : "POST",
				url :getval('root')+"/au/queryUserForTree",
				async:false,
				contentType : 'application/x-www-form-urlencoded;charset=utf-8',
				success : function(data) {
						var list = new Array();
					$.each(data,function(i,o) {
						list.push(new getTreeData(o.ID,o.PID,o.NAME));
					});
					treeObj = $.fn.zTree.init($("#treeDemo"), setting,list);
					
				}
			});
			//$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		//var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		
		
		function show(){
			var nodes = treeObj.getCheckedNodes(true);
			$.each(nodes,function(i,o) {   
				if(o.level==2){
				
				}
			});
		}
</script>
</html>
