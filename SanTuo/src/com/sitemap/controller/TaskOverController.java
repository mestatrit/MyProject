package com.sitemap.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sitemap.interceptor.LoginInterceptor;
@Before(LoginInterceptor.class)
public class TaskOverController extends BaseController{
	public TaskOverController() {
		this.tableName=st_task_over;
	}
	public void show(){
		if(getCid()==0){
			Page<Record>p=Db.paginate(getParaToInt("page"), getParaToInt("rows"),
					"select b.*,a.TITLE, a.TASKDATA ,c.name as tname,c.address,c.STARTTIME,c.ENDTIME ",
					"from ST_TASK_CONTENT a,ST_TASK_OVER b,st_task c where a.ID=b.CID and c.id=a.tid");
			renderJson(p);
		}else{
			Page<Record>p=Db.paginate(getParaToInt("page"), getParaToInt("rows"),
					"select b.*,a.TITLE, a.TASKDATA ,c.name as tname,c.address,c.STARTTIME,c.ENDTIME ",
					"from ST_TASK_CONTENT a,ST_TASK_OVER b,st_task c where a.ID=b.CID and c.id=a.tid and a.TID in(select id from ST_TASK where cID = ?)",getCid());
			renderJson(p);
		}
	}
}
