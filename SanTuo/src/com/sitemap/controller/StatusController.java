package com.sitemap.controller;

import java.util.HashMap;
import java.util.Map;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.sitemap.interceptor.LoginInterceptor;
@Before(LoginInterceptor.class)
public class StatusController extends BaseController{
	
	public void query(){
		Map<String, Object> parm = new HashMap<String, Object>();
		parm.put("r", queryReview());
		parm.put("w", queryWarning());
		renderJson(parm);
	}
	
	//查询需要审阅的数量
	public int queryReview(){
		return	Db.queryBigDecimal("select count(1) from st_review a,st_report b "
				+ "where a.userid =? and a.status =0 and a.rid=b.id and b.status=1",getUid()).intValue();
	}
	
	public int queryWarning(){
		return Db.queryBigDecimal("select count(1) from st_waring_history a,st_userinfor b where a.status =0"
				+ " and a.userid=b.id and b.pid in(select id from st_project where cid = ? )",10).intValue();
	}
	
	public void queryCall(){
		renderJson(Db.queryBigDecimal("select count(1) from st_review where userid =? and status =0",getUid()));
	}
	
}
