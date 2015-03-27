package com.sitemap.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sitemap.interceptor.LoginInterceptor;
import net.loyin.jFinal.anatation.RouteBind;

@RouteBind (path="/warn")
@Before(LoginInterceptor.class)
public class WaringHistoryController extends BaseController {
	public WaringHistoryController(){
		this.tableName=st_waring_history;
	}
	public void col(){
		try {
			if (getPara("oper").equals("add")) {
				System.out.print(getRecord());
				Db.save(tableName, getRecord().remove("oper").set("id", "waring_historySequ.nextval").set("userid",1));
			} else if (getPara("oper").equals("del")) {
				Db.deleteById(tableName, getPara("id"));
			} else if (getPara("oper").equals("edit")) {
				Db.update(tableName, getRecord().remove("oper"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		renderJson(true);
	}
	
	public void addwarn(){
		Integer id=Db.findFirst("select waring_historySequ.nextval as id from DUAL").getBigDecimal("id").intValue();
		Db.save(st_waring, getRecord().remove("otherid").set("id", id)
				.set("cid", getCid()).set("drawid", getUid()));
		for(String uid:getPara("otherid").split(",")){
			Db.save(st_waring_people, new Record().set("id", "waring_historySequ.nextval")
					.set("userid", uid).set("wid", id));
		}
		renderJson(true);
	}
}
