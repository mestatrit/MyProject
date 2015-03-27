package com.sitemap.controller;
import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sitemap.util.TimeUtil;

public class CallController extends BaseController {
	public CallController(){
		this.tableName="st_call";
	}
	public void queryInfo() {
		try {
			List<Record> rs=Db.find("select * from st_call where userid=? and sendid=? and callstatu= 0", getPara("uid"),
					getPara("sid"));
			for(Record r:rs){
				try {
					oracle.sql.TIMESTAMP time=r.get("CALLDATE");
					r.set("CALLDATE", time.timestampValue());
				} catch (Exception e) {
					
				}
			}
			renderJson(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void lookInfo() {
		Db.update("update st_call set callstatu =1 where callid =?", getPara("id"));
	}
	
	public void call(){
		setAttr("pram", getRecord());
		render("/call.jsp");
	}

	public void insert() {
		try {
			String uname=getName(getPara("userid"));
			String sname=getName(getPara("sendid"));
			System.out.println(uname+sname);
			Db.save(tableName,"callid",getRecord().set("callid","callSequ.nextval")
						.set("sendusername", sname).set("USERSENDTOPEOPLE", uname).set("CALLDATE",TimeUtil.getNewDate()).set("callstatu", 0));
			renderJson(true);
		} catch (Exception e) {
			e.printStackTrace();
			renderJson(false);
		}
	}
	
	public String getName(String id){
		return Db.queryStr("select uname from st_userinfor where id = ?",id);
	}
}
