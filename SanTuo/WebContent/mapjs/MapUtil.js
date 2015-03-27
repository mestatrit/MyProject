// JavaScript Document	 
document.writeln("<script src='http://api.map.baidu.com/api?v=2.0&ak=Y1Hnz8WZaIDuiBjZFL4khydo'></script>");
//测距离
document.writeln("<script src='http://api.map.baidu.com/library/DistanceTool/1.2/src/DistanceTool_min.js'></script>");
//拉框放大api
document.writeln("<script src='http://api.map.baidu.com/library/RectangleZoom/1.2/src/RectangleZoom_min.js'></script>"); 
//工具
document.writeln("<script src='http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js'></script>"); 
var cx=121.564372;
var cy=31.090694;
var zoom=11; 
var map=null;  //地图对象
var distance =null; // 测距对象  
var traffic = null;  //交通路况对象
var drawingManager=null;// 画面工具
var searchInfoWindow =null;//创建检索信息窗口对象 
window.onerror=function(){return true;}
//初始化地图
function loadMap(mapLoadOver){
	cityname=$("#cityname").val();
	provincename=$("#provincename").val();
	map = new BMap.Map("mapcontain");    // 创建Map实例
	if(cityname!="null" && cityname!="" && typeof(cityname)!='undefined'){ 
		map.centerAndZoom(cityname, zoom);  // 初始化地图,设置中心点坐标和地图级别
		$(".switchCityContent").html(cityname);
	}else if(provincename!="null" && provincename!="" && typeof(provincename)!='undefined'){ 
		map.centerAndZoom(provincename, zoom);  // 初始化地图,设置中心点坐标和地图级别
		$(".switchCityContent").html(provincename);
	}else{
		map.centerAndZoom(new BMap.Point(cx, cy), zoom);  // 初始化地图,设置中心点坐标和地图级别
		$(".switchCityContent").html("上海市");
	} 
	//map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	//map.setCurrentCity("上海市");          // 设置地图显示的城市 此项是必须设置的(似乎无用)
	map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
	//右下角，鹰眼
	var overViewOpen = new BMap.OverviewMapControl({isOpen:false, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});
	map.addControl(overViewOpen);
	map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT})); // 左下角，添加比例尺
	/*****************缩放平移控件*****************/ 
	var NavigCtrl = new BMap.NavigationControl(); 
	NavigCtrl.setOffset(new BMap.Size(20, 60));
	map.addControl(NavigCtrl);
	/***********地图切换控件*************/
	var mapTypeCtrl=new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});
	mapTypeCtrl.setOffset(new BMap.Size(20, 60));
	map.addControl(mapTypeCtrl);  //右上角，默认地图控件
	/************************/
	/****************构造全景控件*********/
	/*var stCtrl = new BMap.PanoramaControl();
	stCtrl.setOffset(new BMap.Size(20, 100));
	map.addControl(stCtrl);*/
	/************************/
	addMapRightMenu();//添加地图右键菜单
	loadCityList();//加载城市列表
	//创建测距对象 
	distance = new BMapLib.DistanceTool(map);  
	// 创建交通流量图层实例  
	//traffic = new BMap.TrafficLayer(); 
	//注册地图加在完成事件
	if(mapLoadOver!=null) map.addEventListener("tilesloaded",mapLoadOver); 	
	//路况
	traffic=new BMapLib.TrafficControl({
		showPanel: true //是否显示路况提示面板
	});      
	map.addControl(traffic);
	//traffic.setAnchor(BMAP_ANCHOR_BOTTOM_RIGHT);
	//traffic.setOffset(new BMap.Size(165, 27));  //按钮位置
	traffic.setPopOffset(new BMap.Size(168, 20));  //弹出面板的位置
}
 
 
//添加地图右键菜单
function addMapRightMenu(){
	var menu = new BMap.ContextMenu();
	var txtMenuItem = [
		{
			text:'放大地图',
			callback:function(){map.zoomIn();},
			opts:{width :60}
		},
		{
			text:'缩小地图',
			callback:function(){map.zoomOut();},
			opts:{width :60}
		},
		{
			text:'清除标记',
			callback:function(){map.clearOverlays();},
			opts:{width :60}
		}
	];
	for(var i=0; i < txtMenuItem.length; i++){
		menu.addItem(new BMap.MenuItem(txtMenuItem[i].text,txtMenuItem[i].callback,txtMenuItem[i].opts));
	}
	map.addContextMenu(menu);
}

//加载城市列表
function loadCityList(){
	// 创建CityList对象，并放在switchCity_mune_list节点内
	var myCl = new BMapLib.CityList({container : "switchCity_mune_list", map : map}); 
	// 给城市点击时，添加相关操作
	myCl.addEventListener("cityclick", function(e) {
	 	// 修改当前城市显示
	 	document.getElementById("switchCityContent").innerHTML = e.name;
	 	// 点击后隐藏城市列表
	 	document.getElementById("switchCity_mune").style.display = "none";
	});
}

//拉框放大或者缩小地图
function openOrCloseRectangleZoom(flag){  
	var myDrag =null;
	if(parseInt(flag)==0)
		//创建拉框放大/缩小地图对象
		myDrag=new BMapLib.RectangleZoom(map, {
				followText: "拉框放大地图",
				zoomType:flag  // 0:放大  1：缩小
		});
	else{
		//创建拉框放大/缩小地图对象
		myDrag=new BMapLib.RectangleZoom(map, {
				followText: "拉框缩小地图",
				zoomType:flag  // 0:放大  1：缩小
		});
	}
	myDrag.open();  //开启拉框放大/缩小
	//myDrag.close();  //关闭拉框放大/缩小
}


//开启/关闭交通路况图层
function openOrCloseTraffic(obj){  
	if(parseInt($(obj).attr("lang"))==0){
		$(obj).attr("lang","1");
		$(obj).css("background","url(images/map/lukuang_over.png) no-repeat");	
		//map.addTileLayer(traffic);  //显示实时路况
		traffic.show();
	}else{
		$(obj).attr("lang","0");
		$(obj).css("background","url(images/map/lukuang.png) no-repeat");	
		//map.removeTileLayer(traffic);  //隐藏实时路况
		traffic.hide();
	} 
} 
 
//画点/四边形/线/圆工具
function drawRect(type,rect,pageFlag){
	var styleOptions = {
        strokeColor:"blue",    //边线颜色。
        //fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 2,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.5,	   //边线透明度，取值范围0 - 1。
        //fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    } 
	var myIcon = new BMap.Icon("images/4.png", new BMap.Size(24,24));
	var pointOptions = {
       icon:myIcon,
	   title:"拖动可改变位置"
    }
	 //实例化鼠标绘制工具
	 //if(drawingManager==null){
		//map.clearOverlays(); //清除地图上所有标记
		drawingManager = new BMapLib.DrawingManager(map, {
			isOpen: true, //是否开启绘制模式
			enableDrawingTool: false, //是否显示工具栏
			drawingToolOptions: {
				anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
				offset: new BMap.Size(5, 5), //偏离值
				scale: 0.8 //工具栏缩放比例
			},
			markerOptions: pointOptions,//点的样式
			circleOptions: styleOptions, //圆的样式
			polylineOptions: styleOptions, //线的样式
			polygonOptions: styleOptions, //多边形的样式
			rectangleOptions: styleOptions //矩形的样式
		}); 
	//} 
	type=parseInt(type);
	switch(type){
		case 1:  //圆
			drawingManager.setDrawingMode(BMAP_DRAWING_CIRCLE);
			break;
		case 2:// 矩形
			drawingManager.setDrawingMode(BMAP_DRAWING_RECTANGLE);
			break;	
		case 3: //多边形 
			drawingManager.setDrawingMode(BMAP_DRAWING_POLYGON);
			break;	
		case 4: //线
			drawingManager.setDrawingMode(BMAP_DRAWING_POLYLINE);
			break;		
		default: // 点
			drawingManager.setDrawingMode(BMAP_DRAWING_MARKER);
			break;	 
	} 
	drawingManager.open();
	 //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', function(e){ 
		drawingManager.close();  
		var overlays = [];
		overlays.push(e.overlay);
		if(overlays[0] instanceof BMap.Marker){  //点
			//删除之前 
	 		window.frames["iframe_left_type"].deleteBeforePointOvlay(); 
			//alert("点=="+overlays[0].getPosition().lng);
			overlays[0].enableDragging();//可拖拽
			//设置拖动的事件 
			overlays[0].addEventListener("dragend",function(e){
				var ppt=e.target;
				mapClick(ppt);
			});
			//marker.disableDragging();// 不可拖拽
			mapClick(overlays[0]);
		}else if(overlays[0] instanceof BMap.Circle){  //圆形
			//alert("圆形==="+overlays[0].getRadius()+"=="+overlays[0].getCenter().lng+"=="+overlays[0].getCenter().lat); 	
			getInfoD(overlays[0].getCenter().lng,overlays[0].getCenter().lat,overlays[0].getRadius()); 
		}else if(overlays[0] instanceof BMap.Polyline){ //线
			getInfoX(overlays[0].getPath()[0].lng,overlays[0].getPath()[0].lat); 
		}else if(overlays[0] instanceof BMap.Polygon){ //画四边形/多边形
			if(!rect){  //多边形
				if(parseInt(pageFlag)==2){
					var pointsS=overlays[0].getPath();
					var pointsStr="",pointsStr_f="";
					for(var i=0;i<pointsS.length;i++){
						pointsStr=pointsStr+pointsStr_f+pointsS[i].lng+","+pointsS[i].lat;
						pointsStr_f=";";
					}
					getRectInforDuouianXing(pointsStr);
				}else{
					//计算面积
					var area = BMapLib.GeoUtils.getPolygonArea(overlays[0]);
					area=area/Math.pow(10,6);
					area=area.toFixed(2);
					//返回矩形区域的西南角。 
					//var SouthWest=overlays[0].getBounds().getSouthWest();
					//返回矩形区域的东北角。
					//var NorthEast=overlays[0].getBounds().getNorthEast();
					var cpoint=overlays[0].getBounds().getCenter();
					var labelText="总面积：<font color='red'><b>"+area+"</b>平方公里</font>";
					locationLabel(cpoint,labelText); 
				}
			}else{
				var pointsS=overlays[0].getPath();
				var pointsStr="",pointsStr_f="";
				for(var i=0;i<pointsS.length;i++){
					pointsStr=pointsStr+pointsStr_f+pointsS[i].lng+","+pointsS[i].lat;
					pointsStr_f=";";
				}
				getRectInfor(pointsStr);
			}
		}
	});	
}

 //逆向地址解析
 function mapClick(marker){
	 	var pt=marker.getPosition();
		var geoc = new BMap.Geocoder();     
		geoc.getLocation(pt, function(rs){
			var addComp = rs.addressComponents;
			var address="";
			if(addComp.province==addComp.city){
				address=addComp.city + "" + addComp.district + "" + addComp.street + "" + addComp.streetNumber;
			}else{
				address=addComp.province + "" + addComp.city + "" + addComp.district + "" + addComp.street + "" + addComp.streetNumber;
			}
			window.frames["iframe_left_type"].showAddressToText(address,pt.lng,pt.lat,marker); 
		});  
  }

//定位一个label
function locationLabel(point,labelText){
	var opts = {
	  position : point,    // 指定文本标注所在的地理位置
	  offset   : new BMap.Size(30, -30)    //设置文本偏移量
	}
	var label = new BMap.Label(labelText, opts);  // 创建文本标注对象
	label.setStyle({
		 color : "#000",
		 fontSize : "12px",
		 height : "20px",
		 lineHeight : "20px",
		 fontFamily:"微软雅黑"
	 });
	map.addOverlay(label);
}
 
 //创建一个icon对象
 function createIcon(flag){
	var icon = null;
	if(parseInt(flag)==1){
		icon=new BMap.Icon("images/map/point.png", new BMap.Size(19,25));
	}else if(parseInt(flag)==2){
		icon=new BMap.Icon("images/map/point_over.png", new BMap.Size(19,25));
	}else if(parseInt(flag)==3){
		icon=new BMap.Icon("imageTuBiao/03.png", new BMap.Size(28,35));
	}else if(parseInt(flag)==4){
		icon=new BMap.Icon("imageTuBiao/end_05.png", new BMap.Size(28,35));
	}else{
		icon=new BMap.Icon("imageTuBiao/118.png", new BMap.Size(10,10));
	}
	return icon; 
 }
 
 //创建一个弹出信息窗口
 function createInforWin(title,content){
	 var opts = {
			width : 300,     // 信息窗口宽度
			height: 120,     // 信息窗口高度
			title : "<span style='height:30px;font-size:18px;font-weight:400;color:#82C8DB'>"+title+"</span>" , // 信息窗口标题
			enableMessage:false,//设置允许信息窗发送短息
			offset: new BMap.Size(0, -10)
	};
	var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
	return infoWindow;
 }
 
  
 //创建一个弹出信息窗口
 function createInforWinSample(title,content){
	 var opts = { 
			title : "<span style='height:30px;font-size:18px;font-weight:400;color:#82C8DB'>"+title+"</span>" , // 信息窗口标题
			enableMessage:false,//设置允许信息窗发送短息
			offset: new BMap.Size(0, -10)
	};
	var infoWindow = new BMap.InfoWindow(content,opts);  // 创建信息窗口对象 
	return infoWindow;
 }
 
 //定位一个点(复杂)
 function locationPoint(dataID,pt,name,content,iconFlag,clickFunc,mouseoverFunc,mouseoutFunc){
	var icon=createIcon(iconFlag); 
	var marker = new BMap.Marker(pt,{icon:icon,title:name});  // 创建图标和标注 
	map.addOverlay(marker); // 将标注添加到地图中
 	map.setCenter(pt);  //地图定位
	//设置点击事件
	if(clickFunc!=null) clickFunc(dataID,marker);
	if(mouseoverFunc!=null) mouseoverFunc(name,content,marker);  //设置mouseover事件
	if(mouseoutFunc!=null) marker.addEventListener("mouseout",mouseoutFunc); //设置mouseout事件
	return marker;
 }
 
 //定位一个点(简单)
 function locationPointForSample(name,x,y,iconFlag,content){
	 var icon=createIcon(iconFlag); 
	 var pt=new BMap.Point(x,y); 
	 var marker = new BMap.Marker(pt,{icon:icon,title:name});  // 创建图标和标注 
	 map.addOverlay(marker); // 将标注添加到地图中 
	//设置点击事件
	 marker.addEventListener("click",function(e){ 
		var infor=createInforWinSample(name,content);
		marker.openInfoWindow(infor);
	})
	 //map.setCenter(pt);  //地图定位
	 var label = new BMap.Label(name,{offset:new BMap.Size(20,-10)});
	 label.setStyle({
		 border:"1px solid blue",
		 color : "blue",
		 fontSize : "12px",
		 height : "15px", 
		 lineHeight : "15px"
	 });
	 marker.setLabel(label);
 }
 
 //定位一个圆
 function locationOval(x,y,length){
	var point = new BMap.Point(x, y);
	var circle = new BMap.Circle(point,length,{strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5}); //创建圆
	map.addOverlay(circle); // 将标注添加到地图中
	map.centerAndZoom(point,15);  //地图定位
	return circle;
 }
 
 //定位一个圆
 function locationOvalDouble(x,y,length){
	var point = new BMap.Point(x, y);
	var circle = new BMap.Circle(point,length,{strokeColor:"red", strokeWeight:2, strokeOpacity:0.5}); //创建圆
	map.addOverlay(circle); // 将标注添加到地图中
	map.centerAndZoom(point,15);  //地图定位
	return circle;
 }
 
 function removeOvelay(obj){
	map.removeOverlay(obj);
} 

/**
     * 得到圆的内接正方形bounds
     * @param {Point} centerPoi 圆形范围的圆心
     * @param {Number} r 圆形范围的半径
     * @return 无返回值   
     */ 
    function getSquareBounds(centerPoi,r){
        var a = Math.sqrt(2) * r; //正方形边长
      
        mPoi = getMecator(centerPoi);
        var x0 = mPoi.x, y0 = mPoi.y;
     
        var x1 = x0 + a / 2 , y1 = y0 + a / 2;//东北点
        var x2 = x0 - a / 2 , y2 = y0 - a / 2;//西南点
        
        var ne = getPoi(new BMap.Pixel(x1, y1)), sw = getPoi(new BMap.Pixel(x2, y2));
        return new BMap.Bounds(sw, ne);        
        
    }
    //根据球面坐标获得平面坐标。
    function getMecator(poi){
        return map.getMapType().getProjection().lngLatToPoint(poi);
    }
    //根据平面坐标获得球面坐标。
    function getPoi(mecator){
        return map.getMapType().getProjection().pointToLngLat(mecator);
    }
   