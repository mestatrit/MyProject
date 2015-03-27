// JavaScript Document
var isIe=(document.all)?true:false; 
$(function(){
	//document.getElementsByTagName('body')[0].scroll="no";   //影藏浏览器滚动条
    document.oncontextmenu=new Function("return true;");   //禁用右键菜单 
	//设置公共的鼠标移动样式
	$("div[id^='MouseStyle_']").mouseover(function(){
		$(this).css("color","red");	
	});
	$("div[id^='MouseStyle_']").mouseout(function(){
		$(this).css("color","#000000");	
	});
	//地图工具栏鼠标样式
	$(".fullScreen").mouseover(function(){
		$(this).css("background","url(images/map/quanping_over.png) no-repeat");	
	});
	$(".fullScreen").mouseout(function(){
		$(this).css("background","url(images/map/quanping.png) no-repeat");	
	});
	$(".exitFullScreen").mouseover(function(){
		$(this).css("background","url(images/map/exitquanping_over.png) no-repeat");	
	});
	$(".exitFullScreen").mouseout(function(){
		$(this).css("background","url(images/map/exitquanping.png) no-repeat");	
	});
	$(".tool").mouseover(function(){
		$(".mapTool_tool_mune").css("display","block");
		//$(this).css("background","url(images/map/gongju_over.png) no-repeat");	
	});
	$(".tool").mouseout(function(){
		$(".mapTool_tool_mune").css("display","none");
		//$(this).css("background","url(images/map/gongju.png) no-repeat");	
	});
	$(".lukuang").mouseover(function(){ 
		$(this).css("background","url(images/map/lukuang_over.png) no-repeat");	
	});
	$(".lukuang").mouseout(function(){ 
		if(parseInt($(this).attr("lang"))==1) return false;
		$(this).css("background","url(images/map/lukuang.png) no-repeat");	
	}); 
	$(".lukuang").click(function(){
		openOrCloseTraffic(this); //打开/关闭交通路况	
	});
	/*地图工具下拉菜单样式*/
	$(".mapTool_tool_mune").mouseover(function(){
		$(".mapTool_tool_mune").css("display","block"); 
	});
	$(".mapTool_tool_mune").mouseout(function(){
		$(".mapTool_tool_mune").css("display","none"); 
	});
	/*地图工具下拉菜单中的子菜单样式*/
	$(".mapTool_tool_mune_child").mouseover(function(){
		$(".mapTool_tool_mune").css("display","block"); 
		$(this).css("background","#F5F5F5"); 
	});
	$(".mapTool_tool_mune_child").mouseout(function(){
		$(this).css("background","#fff"); 
	}); 
	/*城市切换样式*/
	$(".switchCity").mouseover(function(){
		$(".switchCityContent").css("color","blue"); 
		$(".switchCityPic").css("background","url(images/map/city_over.png) no-repeat");	
	});
	$(".switchCity").mouseout(function(){ 
		$(".switchCityContent").css("color","#333");
		$(".switchCityPic").css("background","url(images/map/city.png) no-repeat");	
	});
	$(".switchCity").click(function(){ 
		$(".switchCity_mune").css("display","block");	
	});
	/*城市列表菜单关闭按钮事件*/
	$(".switchCity_mune_top_colse").click(function(){
		$(".switchCity_mune").css("display","none");	
	});
	/*测距点击事件*/
	$("#mapTool_tool_mune_child_dis").click(function(){ 
		distance.open();  //开启鼠标测距
		//distance.close();  //关闭鼠标测距大
	});
	/*侧面积*/
	$("#mapTool_tool_mune_child_area").click(function(){
		drawRect(3,false,1);      
	});
	/*清除地图标记*/
	$("#mapTool_tool_mune_child_clear").click(function(){
		map.clearOverlays();
	});
	/*拉框放大*/
	/*$("#mapTool_tool_mune_child_out").click(function(){
		openOrCloseRectangleZoom(0);
	});*/
	/*拉框缩小*/
	/*$("#mapTool_tool_mune_child_in").click(function(){
		openOrCloseRectangleZoom(1); 
	});*/
	/**收藏本站**/
	$("#MouseStyle_head_shoucang").click(function(){
		addfavor();
	});
	/**安全退出**/
	$("#MouseStyle_head_exit").click(function(){
		exitPage();
	});
	/*隐藏左侧按钮样式*/ 
	$(".hide_left_div").mouseover(function(){  
		if(parseInt($(this).attr("lang"))==0){
			$(this).css("background","url(images/map/left_over.png) no-repeat");	
		}else{
			$(this).css("background","url(images/map/right_over.png) no-repeat");	
		}
	});
	$(".hide_left_div").mouseout(function(){
		if(parseInt($(this).attr("lang"))==0){
			$(this).css("background","url(images/map/left.png) no-repeat");	
		}else{
			$(this).css("background","url(images/map/right.png) no-repeat");	
		}
	});
	$(".hide_left_div").click(function(){ 
		if(parseInt($(this).attr("lang"))==0){ 
			$(this).css("background","url(images/map/right.png) no-repeat");	
			$(this).attr({"lang":"1","title":"显示左栏"});
			try{
				$(".index_middle_two_param_child_table_td_left").css("display","none");
				$(".index_middle_two_param_child_table_td_right").css("width","1200px");
			}catch(e){}
			try{
				$(".index_middle_three_param_child_table_td_left").css("display","none");
				$(".index_middle_three_param_child_table_td_right").css("width","1200px");
			}catch(e){}
		}else{
			$(this).css("background","url(images/map/left.png) no-repeat");	
			$(this).attr({"lang":"0","title":"收起左栏"});
			try{
				$(".index_middle_two_param_child_table_td_right").css("width","900px");
				$(".index_middle_two_param_child_table_td_left").css("display","block");
			}catch(e){}
			try{
				$(".index_middle_three_param_child_table_td_right").css("width","900px");
				$(".index_middle_three_param_child_table_td_left").css("display","block");
			}catch(e){}
		} 
	});
	/*右侧数据列表隐藏/显示按钮*/
	$(".hide_right_div").mouseover(function(){
		if(parseInt($(this).attr("lang"))==0){
			$(this).css("background","url(images/house/left_over.png) no-repeat");	
		}else{
			$(this).css("background","url(images/house/right_over.png) no-repeat");	
		}
	});
	$(".hide_right_div").mouseout(function(){
		if(parseInt($(this).attr("lang"))==0){
			$(this).css("background","url(images/house/left.png) no-repeat");	
		}else{
			$(this).css("background","url(images/house/right.png) no-repeat");	
		}
	});
	$(".hide_right_div").click(function(){
		if(parseInt($(this).attr("lang"))==0){
			//隐藏右侧数据列表页面
			$(this).css("background","url(images/house/right.png) no-repeat");	
			$(this).attr({"lang":"1","title":"显示右栏"});
			hideOrShowDiv("map_right_data_list","hide",null);
			$(this).css("right","0px");
		}else{
			//隐藏右侧数据列表页面
			$(this).attr({"lang":"0","title":"收起右栏"});
			$(this).css("background","url(images/house/left.png) no-repeat");	
			hideOrShowDiv("map_right_data_list","show",function(){ 
				$(".hide_right_div").css("right","250px");
			});
		}
	});
	/*全屏按钮事件*/
	$(".fullScreen").click(function(){
		$(".hide_left_div").attr({"lang":"0","title":"收起左栏"});
		$(".hide_left_div").css({"background":"url(images/map/left.png) no-repeat","display":"none"});	
		$(".total_head").css("display","none");
		$(".total_bottom").css("display","none");
		try{
			$(".index_middle_two_param_child_table_td_left").css("display","none");
			$(".index_middle_two_param").css({"margin-top":"0px","height":"99.85%"});
			$(".index_middle_two_param_child").css("width","100%");
			$(".index_middle_two_param_child_table_td_right").css("width","100%");
		}catch(e){}	
		try{
			$(".house_details_two").css("display","none");
			$(".index_middle_three_param_child_table_td_left").css("display","none");
			$(".index_middle_three_param").css({"margin-top":"0px","height":"99.85%"});
			$(".index_middle_three_param_child").css("width","100%");
			$(".index_middle_three_param_child_table_td_right").css("width","100%");
		}catch(e){}
		$(this).css("display","none");
		$(".exitFullScreen").css("display","block");
	});
	/*退出全屏按钮*/
	$(".exitFullScreen").click(function(){
		$(".hide_left_div").attr({"lang":"0","title":"收起左栏"});
		$(".hide_left_div").css({"background":"url(images/map/left.png) no-repeat","display":"block"});	
		$(".total_head").css("display","block");
		$(".total_bottom").css("display","block");
		try{
			$(".index_middle_two_param_child_table_td_left").css("display","block");
			$(".index_middle_two_param").css({"margin-top":"15px","height":"600px"});
			$(".index_middle_two_param_child").css("width","1200px");
			$(".index_middle_two_param_child_table_td_right").css("width","900px");
		}catch(e){}
		try{
			$(".house_details_two").css("display","block");
			$(".index_middle_three_param_child_table_td_left").css("display","block");
			$(".index_middle_three_param").css({"margin-top":"15px","height":"600px"});
			$(".index_middle_three_param_child").css("width","1200px");
			$(".index_middle_three_param_child_table_td_right").css("width","900px");
		}catch(e){}
		$(this).css("display","none");
		$(".fullScreen").css("display","block");
	});
	//首页（拓客分析）
	$(".total_head_child_two_content_title_1").click(function(){
		toPage("index.jsp");
	});
}) 
  
	//控制回到顶部图标的显示与否
window.onscroll = function () {  
	var lala = document.documentElement.scrollTop || document.body.scrollTop;   
	if(lala=="0"){
		$("#go2top").css("display","none");
	}else{
		$("#go2top").css("display","block");
	}
};

//页面跳转
function toPage(url){
	window.location.href=url;
}

//判断是否以某一字符开头
function startWith(str1,str2){   
	if(str1==null||str1==""||str1.length==0||str2.length>str1.length)   
	  return false;   
	if(str1.substr(0,str2.length)==str2)   
	  return true;   
	else   
	  return false;   
	return true;   
}
 
//判断是否为整数
function isInt(number){
	var reg=/[^\d]/;
	if (reg.test(number))
		return false;
	return true;
}
	
//检测是否是邮箱格式
function isEmail(str){
	return /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(str);
}

//是否是手机
function isMobile (str){
	return /^1{1}[^0124679][0-9]{9}$/.test(str);
}

//是否是浮点型
function isFloat(str){
	var reg = /^(-|\+)?\d+(\.)?\d*$/;
	return reg.test(str);
}

/*
*让背景渐渐变暗 
* obj ---背景层对象
* endInt ---变暗效果的参数
**/
function showBackground(obj,endInt) { 
	if(isIe) {  
		obj.filters.alpha.opacity+=1;  
		if(obj.filters.alpha.opacity<endInt) { 
			setTimeout(function(){showBackground(obj,endInt)},5); 
		} 
	}else{ 
		var al=parseFloat($(obj).css("opacity"));
		if(isFloat(al)){
			al+=0.01; 
			$(obj).css("opacity",al);
		}else{
			al=parseFloat($(obj).css("-moz-opacity"));
			al+=0.01; 
			$(obj).css("-moz-opacity",al);
		} 
		if(al<(endInt/100)) {
			setTimeout(function(){showBackground(obj,endInt)},5);
		} 
	} 
}
	 
//创建背景覆盖层
function createBackground(id,bWidth,bHeight){
	$(window).scrollTop(0); 
	//创建弹出层的背景div 
	var backg=document.createElement("div"); 
	//设置背景div的id
	backg.id=id; 
	//样式字符串
	var styleStr="position:fixed;top:0px;left:0px;background:#003973;width:"+bWidth+"px;height:"+bHeight+"px;z-index:50000000";
	//给背景div设置样式
	backg.style.cssText=styleStr;
	//将背景div加入到body中
	document.body.appendChild(backg);
	showBackground(backg,50);
}

//关闭弹出层
function closeBackground(id){
	$("#"+id).remove(); 
}

//隐藏div
function hideDivUtil(id){
	var ids=id.split(",");
	for(var i=0;i<ids.length;i++){
		$("#"+ids[i]).css("display","none");
	}
}

//获得当前的event对象
function getWindowEvent(){
	var theEvent = null;
	if(window.event){
		theEvent=window.event;
	}else{
		theEvent=arguments.callee.caller.arguments[0];
	}
	return theEvent;
}

//退出系统(关闭浏览器)
function exitPage() {
	if(!confirm("您确定要退出拓客分析系统吗?")) return false;
    window.location="login.jsp";
}


//收藏本站
function addfavor() {
	var url="";
	if(confirm("网站名称：拓客分析系统\n网址："+url+"\n确定添加收藏?")){
		var ua = navigator.userAgent.toLowerCase();
		if(ua.indexOf("msie 8")>-1){
			external.AddToFavoritesBar(url,title,'拓客分析系统');//IE8
		}else{
			try {
				window.external.addFavorite(url, title);
			} catch(e) {
				try {
					window.sidebar.addPanel(title, url, "");//firefox
				} catch(e) {
					alert("加入收藏失败，请使用Ctrl+D进行添加");
				}
			}
		}
	}
	return false;
}

//动态隐藏/显示一个div（左右变化）
function hideOrShowDiv(classID,flag,callFunc){
	$("."+classID).animate({width: flag, opacity: flag}, 'normal',function(){ 
		if(flag=="show"){
			$("."+classID).show();
		}else{
			$("."+classID).hide();
		}
		if(callFunc!=null) callFunc(flag);
	});
}


//加载背景loading层
function loadBeijingLoading(showValue){
	//开启加载背景层
	var bWidth=parseInt(document.documentElement.clientWidth);
	var bHeight=parseInt(document.documentElement.clientHeight);
	createBackground('objBackgroup',bWidth,bHeight);
	$(".bagroundUpDivAlertDiv_td_div").html(showValue);
	$(".bagroundUpDivAlertDiv").css("display","block");	
}
   