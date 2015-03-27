<%@page import="com.sitemap.util.TimeTool"%> 
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'colshow.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	<link rel="stylesheet"  href="mapcss/ControlsUtil.css" />
	 <!--画点、线、面、圆工具样式文件-->
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" /> 
    <!--弹出框样式文件-->
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
    <!--路况-->
    <link href="http://api.map.baidu.com/library/TrafficControl/1.4/src/TrafficControl_min.css" rel="stylesheet" type="text/css" />
    <script src="mapjs/jquery-1.7.1.js"></script> 
    <script src="mapjs/MapUtil.js"></script>
    <script src="mapjs/pageUtil.js"></script>
      <!--城市列表工具-->
    <script type="text/javascript" src="http://api.map.baidu.com/library/CityList/1.2/src/CityList_min.js"></script>
    <!--画点、线、面、圆工具JS文件-->
    <script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
    <!--弹出框JS文件-->    
    <script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
    <!--路况-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/TrafficControl/1.2/src/TrafficControl_min.js"></script>
 	<script src="My97DatePicker/WdatePicker.js"></script>
 	<script type="text/javascript">
	 	$(function(){
	 		//baojing();
	 		loadMap(null);
	 		showGuiJi();
	 		
	 	});
 	 	
 	</script>
<script> 
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}
	function showGuiJi(){
       		var userid=getUrlParam("id");
  			var starttime=$("#starttime").val();
  			var endtime=$("#endtime").val();
  			var polyline;  //创建折线
  			$.ajax({
						type : "POST",
						url :"/SanTuo/map/queryGPS",
						async:false,
						data :{userid:userid,starttime:starttime,endtime:endtime},
						contentType : 'application/x-www-form-urlencoded;charset=utf-8',
						success : function(data) {
							var array=[];
							$.each(data,function(i,o) {   
								 array.push(new BMap.Point(o.LNG,o.LAT));
							  	 if(i==0){
							  	  locationPointForSample(o.UNAME,o.LNG,o.LAT,3,null);
							  	 }else if(i==data.length-1) {
							  	  locationPointForSample(o.UNAME,o.LNG,o.LAT,4,null);
							  	 }else{
								  locationPointForSample(o.UNAME,to.LNG,o.LAT,1,null);
								 } 
							});
							 polyline=  new BMap.Polyline(array, {strokeColor:"red", strokeWeight:3, strokeOpacity:2});
							 map.addOverlay(polyline);
							 map.setViewport(array); 
						}
					});
	}
</script>
  </head>
  <body>
  		<input type="hidden" value="" id="userid">
   	<table width="100%" height="100%" border="1" align="center" cellpadding="0" cellspacing="0" style="border: 1px solid #15a9c7;">
  	<tr style="border: 1px solid #d1e0ea;">
	  <td style="border: 1px solid #d1e0ea;" width="35%" align="left" height="21px" bordercolor="#d1e0ea" >
	  <br/><span style="color:#15a9c7">开始时间：</span><input type="text" id="starttime" name="starttime" class="Wdate"
						style="cursor: pointer;border-color:#15a9c7; width: 206px" readonly="readonly" value="<%=TimeTool.dataToStr(TimeTool.addMinutes(new Date(), -10), null)%>" size=21
						onClick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})"/>  
	  <span style="color:#15a9c7">结束时间：</span><input type="text" id="endtime" name="endtime" class="Wdate"
						style="cursor: pointer;border-color:#15a9c7; width: 206px" readonly="readonly" value="<%=TimeTool.getTimeForMat(null) %>" size=21
						onClick="WdatePicker({startDate:'%y-%M-01',dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true}) "  />
	 	 <input type="button" value="查询" style="border:0px; background:#15a9c7;color:white;font-size: 16px;" onclick="showGuiJi();"/>
	  	<input type="button" style="float:right;border: 0px;background-color:#15a9c7;font-size: 16px;color:white" value="返回" onclick="javaScript:history.go(-1)"/></td>
  	</tr>
            	<tr align="center">
        	<td style="border:0px solid #15a9c7;" height="600" colspan="4"> 
        	 <div style="position:relative; width: 100%;height: 600px;border:0px solid #15a9c7">
        	 		<!--  地图布局开始  -->
                    <%@ include file="map.jsp"%> 
        	 </div>
        </td>
        </tr>
    </table>

</body>
</html>
