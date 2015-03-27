
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
        <!---工具按钮-->
        <div class="mapTool">
        	<!-- <div class="fullScreen"></div>
        	<div class="exitFullScreen"></div> -->
            <div class="tool"></div>
            <div class="lukuang" lang="0"></div>
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
            <div id="mapTool_tool_mune_child_area" class="mapTool_tool_mune_child" title="测量面积">测面</div>
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