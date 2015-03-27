package com.sitemap.controller;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import net.loyin.jFinal.anatation.RouteBind;
@RouteBind(path="/le")
public class LeaveElectricController extends BaseController{
	public LeaveElectricController() {
		this.tableName=st_leave_Electric;
	}
	
	public void show() {
		Page<Record>p;
		if(getCid()==0){
			p=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName);

		}else{
			p=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName+" where userid in(select id from st_project where cid =?)",getCid());

		}
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
}
