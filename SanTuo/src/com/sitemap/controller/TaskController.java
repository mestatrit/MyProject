package com.sitemap.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sitemap.interceptor.LoginInterceptor;
@Before(LoginInterceptor.class)
public class TaskController extends BaseController {
	public TaskController() {
		System.out.println("Running =====> TaskController.TaskController()  : ");
		this.tableName = st_task;
	}
	
	public void index(){
		//setAttr("project", Db.find("select * from st_project where cid=?",getSessionAttr("cid")));
		setAttr("project", Db.find("select * from st_project"));
		render("/task.jsp");
	}
	
	public void save(){
		Record record=getRecord().remove("oper");
		Integer id=Db.findFirst("select taskSequ.nextval as id from DUAL").getBigDecimal("id").intValue();
		System.out.println(getRecord());
		Db.save(tableName, getRecord().remove("oper").remove("paidan").remove("liudian").remove("daifang").set("id", id).set("cid", getCid()));
		Db.save(st_task_content,new Record().set("id", "task_contentSequ.nextval").set("tid", id).set("ttype", 1)
				.set("title", "派单").set("taskdata", record.get("paidan")==null?0:record.get("paidan")));
		Db.save(st_task_content,new Record().set("id", "task_contentSequ.nextval").set("tid", id).set("ttype", 2)
				.set("title", "留电").set("taskdata", record.get("liudian")==null?0:record.get("liudian")));
		Db.save(st_task_content,new Record().set("id", "task_contentSequ.nextval").set("tid", id).set("ttype", 3)
				.set("title", "代访").set("taskdata", record.get("daifang")==null?0:record.get("daifang")));
		
	}
	
	public void edit(){
		Record record=getRecord().remove("oper");
		Db.update(tableName, getRecord().remove("oper").remove("paidan").remove("liudian").remove("daifang"));
		System.out.println(getPara("liudian"));
		Db.update("update st_task_content set taskdata =? where tid=? and ttype=2",record.get("liudian"),getPara("id"));
		Db.update("update st_task_content set taskdata =? where tid=? and ttype=3",record.get("daifang"),getPara("id"));
		Db.update("update st_task_content set taskdata =? where tid=? and ttype=1",record.get("paidan"),getPara("id"));
	}
	
	public void show() {
		Page<Record> rs;
		if(getCid()==0){
			rs=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName);

		}else{
			rs=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName+" where cid=?",getCid());

		}
			 for(Record r:rs.getList()){
				 r.set("PAIDAN", Db.queryFirst("select taskdata from ST_TASK_CONTENT where tid=? and ttype=1",r.get("id")));
				 r.set("LIUDIAN", Db.queryFirst("select taskdata from ST_TASK_CONTENT where tid=? and ttype=2",r.get("id")));
				 r.set("DAIFANG", Db.queryFirst("select taskdata from ST_TASK_CONTENT where tid=? and ttype=3",r.get("id")));
			 }
			 for(Record r:rs.getList()){
					try {
						r.set("STARTTIME",r.get("STARTTIME").toString().substring(0,19));
						r.set("ENDTIME",r.get("ENDTIME").toString().substring(0,19));
					} catch (Exception e) {
					}
			 }
		renderJson(rs);
	}
	//派送任务
	public void pasong() {
		for (Record t : Db.find("select * from st_task_content where tid=?", getPara("id"))) {
			if (t.getBigDecimal("taskdata").intValue() > 0) {
				for (String id : getPara("userid").split(",")) {
					Db.save(st_task_over,new Record().set("id", "task_overSequ.nextval").set("pid", id)
						.set("status", 0).set("cid", t.get("id")).set("taskOverData", 0)
						.set("uname", Db.queryStr("select uname from st_userinfor where id=?",id)));
				}
			}
		}
		renderJson(true);
	}
	public void alluer(){
		renderJson(Db.find("select id from st_userinfor where id in"
				+ "(select pid from st_task_over where cid in"
				+ "(select id from st_task_content where tid =?))",getPara("id")));
	}
}
