package com.sitemap.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sun.org.apache.xpath.internal.functions.Function;



/**
 * 公用的控制
 * 一般调用常用ajax
 * CommonController
 */
public class CommonController extends BaseController {
	public void index() {

		if(getSessionAttr("user")!=null){
			System.out.println("");
			redirect(getRequest().getServletContext().getContextPath()+"/index.jsp");
		}else{
			System.out.println("没有session");
			redirect(getRequest().getServletContext().getContextPath()+"/tiaozhuan.jsp");
		}
	}
	//返回地域
	public void diyu() {
//		if (getPara("id").equals("0")) {
//			renderJson(Db.find("select city from T_ST_DIS_CITY group by city"));
//		} else {
//			renderJson(Db.find("select * from T_ST_DIS_CITY where city =? and dis !=?", getPara("id"), getPara("id")));
//		}
		List<Map<String, Object>> rs=new ArrayList<Map<String, Object>>();
		for(Record r:Db.find("select city from T_ST_DIS_CITY group by city")){
			Map<String, Object> city = new HashMap<String, Object>();
			List<String> dis=new ArrayList<String>();
			city.put("city", r.getStr("city"));
			List<Record> rss=Db.find("select * from T_ST_DIS_CITY where city =? and dis !=?",r.getStr("city"),r.getStr("city"));
			for(Record s:rss){
				dis.add(s.getStr("dis"));
			}
			city.put("dis", dis);
			rs.add(city);
		}
		renderJson(rs);
	}
	
	public void logout(){
		removeSessionAttr("user");
		render("/login.jsp");
	}
	
	public void queryAuthority(){
		if(StringUtils.isNotEmpty(getPara("rid"))){
			renderJson(Db.find("select *from st_role_func where rid=? and cid=? and id !=2",getPara("rid"),getCid()));
		}else{
			renderJson(Db.find("select * from st_func and id != 2"));
		}
	}
	
	public void queryProject(){
		if(getCid()==0){
			renderJson(Db.find("select * from st_project "));
		}else{
			renderJson(Db.find("select * from st_project where cid = ?",getCid()));
		}
	}
	
}
