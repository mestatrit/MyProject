package com.sitemap.controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sitemap.interceptor.LoginInterceptor;
import com.sitemap.system.User;
import com.sitemap.util.TimeUtil;
@Before(LoginInterceptor.class)
public class ReportController extends BaseController{
	public void index(){
		String sql="select a.id,a.uname from ST_USERINFOR a LEFT JOIN ST_ROLE b ON (a.RID=b.ID)"
				+ " where b.CID=? and b.ID in (select rid from ST_ROLE_FUNC where FID =16 and cid=?)";
		setAttr("peoples", Db.find(sql,getCid(),getCid()));
		render("/report_add.jsp");
	}
	
	public void review(){
		
		setAttr("r", getRecord());
		render("/report_edit.jsp");
	}
	
	public void review_show(){
		Map<String, Object> parm = new HashMap<String, Object>();
		Record record=Db.findFirst("select * from st_report  where id = ?",getPara("id"));
		parm.put("content", record.get("content"));
		parm.put("reviews",	Db.find("select a.createtime,a.status,b.uname from st_review a,st_userinfor b where a.userid=b.id and a.rid=?",getPara("id")));
		if(record.getBigDecimal("status").intValue()==2){//已经驳回
			parm.put("isre",true);
		}else{
			if(Db.queryBigDecimal("select count(1) from st_review where status > 0 and userid = ? and rid = ?"
					,getUid(),getPara("id")).intValue()>0){
				//审批过
				parm.put("isre",true);
			}else{
				parm.put("isre",false);
			}
		}
		
		renderJson(parm);
	}
	
	public void shenpi(){
		Record record=Db.findFirst("select id,rtype,peopledou from st_report where id=?",getPara("id"));
		try {
			if(record.getBigDecimal("rtype").intValue()==2){//同级
				if(getParaToInt("type")==1){//审阅通过
					Db.update("update st_review set createtime =?,status =1 where rid = ? and userid = ? "
							,getDate(),getPara("id"),getUid());
					//如果全部同意，更新上报表
					if(Db.queryBigDecimal("select count(1) from st_review where rid = ? and status !=1"
							,getPara("id")).intValue()==0){
						Db.update("update st_report set status =2 id= ? ",getPara("id"));
					}
				}else{
					Db.update("update st_review set createtime =?,status =2 where rid = ? and userid = ? "
							,getDate(),getPara("id"),getUid());
					Db.update("update st_report set status =2 where id= ? ",getPara("id"));
				}
			}else{//逐级
				if(getParaToInt("type")==1){//审阅通过
					Db.update("update st_review set createtime =?,status =1 where rid = ? and userid = ? "
							,getDate(),getPara("id"),getUid());
					String u=record.getStr("peopledou");
					int index=u.indexOf("_");
					Db.update(st_Report, record.set("peopledou", u.substring(index+1)));
					Db.save(st_Review, new Record().set("id", "").set("userid", u.substring(0, index))
							.set("status", 0).set("createtime", getDate()));
				}else{
					Db.update("update st_review set createtime =?,status =2 where rid = ? and userid = ? "
							,getDate(),getPara("id"),getUid());
					Db.update("update st_report set status =2 where id= ? ",getPara("id"));
				}
			}
			renderJson(true);
		} catch (Exception e) {
			renderJson(false);
			e.printStackTrace();
		}
	}
	
	public void showReview(){
		Page<Record>p=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select a.id,a.people,a.rtype,a.title,a.remark,a.countNumber,a.createtime,a.status,b.uname ", 
				"from st_report a,st_userinfor b where a.pid=b.id and a.id in "
				+ "(select rid from st_review where userid =? and status !=0)",getUid());
		for(Record r:p.getList()){
			try {
				r.set("CREATETIME",r.get("CREATETIME").toString().substring(0,19));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		renderJson(p);
	}
	
	public void save(){
		Record record=getRecord();
		Integer id=Db.findFirst("select reportSequ.nextval as id from DUAL").getBigDecimal("id").intValue();
		record.set("id", id).set("createtime", new Date(TimeUtil.getNewDate().getTime()));
		System.out.println(getPara("people"));
		record.set("countnumber",getPara("people").split(",").length);
		record.set("peopleDou", getPara("people").replaceAll(",", "_"));
		record.set("people", getPara("people").replaceAll(",", "_"));
		Db.save(st_Report, record.set("pid", getUid()).set("status", 1));
		if(getParaToInt("rtype")==1){//逐级审批
			Db.save(st_Review, new Record().set("id", "ReviewSequ.nextval").set("rid", id)
					.set("userid", getPara("people").split(",")[0]).set("status", 0));
		}else{//同级审批
			for(String u:getPara("people").split(",")){
				Db.save(st_Review, new Record().set("id", "ReviewSequ.nextval").set("rid", id)
						.set("userid",u).set("status", 0));
			}
		}
		renderJson(true);
	}
	
	
	public void edit(){
		
	}
	
}
