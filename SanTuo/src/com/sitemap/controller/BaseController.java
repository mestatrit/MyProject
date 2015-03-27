package com.sitemap.controller;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import oracle.sql.DATE;
import org.apache.commons.lang.StringUtils;
import com.alibaba.fastjson.JSON;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sitemap.interceptor.LoginInterceptor;
import com.sitemap.util.TimeUtil;

/**
 * 基础Controller
 * 
 * @author 刘声凤 2012-9-3 下午10:37:28
 */
public abstract class BaseController extends Controller {
	protected int pageSize = 10;
	protected String tableName;
	public static String st_company = "st_company";
	public static String st_project = "st_project";
	public static String st_userInfor = "st_userInfor";
	public static String st_group = "st_group";
	public static String st_func = "st_func";
	public static String st_role_func = "st_role_func";
	public static String st_role = "st_role";
	public static String st_chat = "st_chat";
	public static String st_gps = "st_gps";
	public static String st_gps_history = "st_gps_history";
	public static String st_reward = "st_reward";
	public static String st_task = "st_task";
	public static String st_task_content = "st_task_content";
	public static String st_leave_Electric = "st_leave_Electric";
	public static String st_car_Apply = "st_car_apply";
	public static String st_Report = "st_report";
	public static String st_Review = "st_review";
	public static String st_waring = "st_waring";
	public static String st_waring_people = "st_waring_people";
	public static String st_waring_history = "st_waring_history";
	public static String st_task_over="st_task_over";

	@Override
	public void render(String view) {
		super.render(view);
	}

	public int getUid(){
		if(getSessionAttr("user")==null){
			return 0;
		}else{
			return ((Record)getSessionAttr("user")).getBigDecimal("id").intValue();
		}
	}
	
	public Date getDate(){
		return new Date(TimeUtil.getNewDate().getTime());
	}
	
	public int getCid(){
		if(getSessionAttr("user")==null){
			return 0;
		}else{
			return ((Record)getSessionAttr("user")).getBigDecimal("cid").intValue();
		}
		
	}
	
	public void col() {
		try {
			if (getPara("oper").equals("add")) {
				this.save();
			} else if (getPara("oper").equals("del")) {
				this.del();
			} else if (getPara("oper").equals("edit")) {
				this.edit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		renderJson(true);
	}
	
	public void save(){
		try {
			Db.save(tableName,getRecord().remove("oper").set("id", "userSequ.nextval").set("createtime", new Date(TimeUtil.getNewDate().getTime())));
		} catch (Exception e) {
			Db.save(tableName,getRecord().remove("oper").set("id", "userSequ.nextval"));
		}
	}
	public void del(){
		for(String id:getPara("id").split(",")){
			Db.deleteById(tableName, id);
		}
	}
	public void edit(){
		Db.update(tableName,getRecord().remove("oper"));
	}

	public void show() {
		Page<Record>p=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName);
		for(Record r:p.getList()){
			try {
				r.set("CREATETIME",r.get("CREATETIME").toString().substring(0,19));
			} catch (Exception e) {
			}
			try {
				r.set("STARTTIME",r.get("STARTTIME").toString().substring(0,19));
				r.set("ENDTIME",r.get("ENDTIME").toString().substring(0,19));
			} catch (Exception e) {
			}
		}
		renderJson(p);
	}

	/**
	 * @return 页面传回来的值
	 */
	public Record getRecord() {
		Record record = new Record();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Entry<String, String[]> set : getParaMap().entrySet()) {
			if (set.getKey().toLowerCase().contains("time")) {
				if (StringUtils.isEmpty(set.getValue()[0])) {
					map.put(set.getKey(), "");
				} else {
					map.put(set.getKey(), TimeUtil.getSqlDate(set.getValue()[0]));
				}
			} else {
				map.put(set.getKey(), set.getValue()[0]);
			}
		}
		record.setColumns(map);
		return record;
	}

	public void goShow(String url) {
		String id = this.getPara("id");
		if (StringUtils.isNotEmpty(id)) {
			setAttr(tableName, Db.findFirst("select * from " + tableName + " where id = ? ", id));
		}
		render(url);
	}
	public void renderJsonTime(Object o){
		renderJson(o);
	}
	public String getUUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public void goUrl() {
		redirect(getRequest().getSession().getServletContext().getContextPath() + "/" + getPara("url"));
	}
}
