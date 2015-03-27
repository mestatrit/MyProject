<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    		<div class="breadcrumbs" id="breadcrumbs">
				<div style="float: left;margin-left: 20px;">实时动态：</div>
				<div style="float: left;height: 20px"> 
					<marquee direction="left" scrollamount="3" onmouseover="this.stop"
						onmouseout="this.start" style="position:relative;">
					 	<span 
							style="color: black;font-size: 18px;position:relative;cursor: pointer;">当前小蜜蜂任务完成数量总数:<font
							color="red"><span id="SumOrder">0</span>
						</font>
						</span>
					</marquee>
				</div>
				<div style="float: right; font-size: 18px">登录用户 ：<i style="color: blue;"> ${user.columns["UNAME"]}</i></div>
			</div>