<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%> 
	<!--地图容器对象-->
    <div id="mapcontain" class="mapcontain"></div> 
    <!--地图工具栏-->
    <div class="map_top_tool">
    	<!--城市选择-->
    	<div class="switchCity" title="修改城市">
        	<div class="switchCityPic"></div>
        	<div id="switchCityContent" class="switchCityContent"></div>
        </div>
        <div style="position: absolute; left:100px;top:0px;height:42px;line-height: 42px;">
        	        <span style="color:red;">画定报警区域范围:请单击右边的图片开始在地图上画定报警范围</span>
	            	<img style="margin-top: 5px;cursor: pointer;" src="imageTuBiao/polygon.png" id="drawpicture" onclick="drawduobianxing();" alt="单击图片可以在地图上画出预警提醒范围圈" />
        			<img style="margin-top: 5px;cursor: pointer;" src="imageTuBiao/rect.png" id="drawpicturejuxing" onclick="drawjuxing();" alt="单击图片可以在地图上画出预警提醒范围圈" />
         </div>
        <!---工具按钮-->
        <div class="mapTool">
        
            <div class="tool"></div>
         
        </div>
        <!--城市切换下拉框-->
        <div id="switchCity_mune" class="switchCity_mune">
			<div class="switchCity_mune_top">
            	<div class="switchCity_mune_top_title">城市列表</div>
                <div class="switchCity_mune_top_colse" title="关闭"></div>
            </div>
            <div id="switchCity_mune_list" class="switchCity_mune_list"></div>          
        </div>
        <!--工具栏的下拉弹出框-->
        <div class="mapTool_tool_mune">
        	<div id="mapTool_tool_mune_child_dis" class="mapTool_tool_mune_child" title="测量距离">测距</div>
            <div id="mapTool_tool_mune_child_area" class="mapTool_tool_mune_child" title="测量面积">侧面</div>
            <div id="mapTool_tool_mune_child_clear" class="mapTool_tool_mune_child" title="清除地图标记">清除</div>
            <!--<div id="mapTool_tool_mune_child_out" class="mapTool_tool_mune_child" title="拉框放大地图">放大</div>
              <div id="mapTool_tool_mune_child_in" class="mapTool_tool_mune_child" title="拉框缩小地图">缩小</div>-->
        </div>
        <!--隐藏左侧按钮-->
      
    </div> 
    <!--右侧数据列表-->
    <div id="map_right_data_list" class="map_right_data_list">
    	<div class="map_right_data_list_title" lang="0" title="点击隐藏数据列表"></div>
        <div class="map_right_data_list_content"></div>
        <div class="map_right_data_list_tempDiv"></div>
        <div class="map_right_data_list_title_other" lang="0" title="点击隐藏数据列表"></div>
        <div class="map_right_data_list_content_other"></div>
    </div>
    <!--隐藏右侧数据列表-->
    <div class="hide_right_div" title="收起右栏" lang="0"></div>