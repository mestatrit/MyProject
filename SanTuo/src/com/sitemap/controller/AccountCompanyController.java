package com.sitemap.controller;
import java.sql.Date;
import net.loyin.jFinal.anatation.RouteBind;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sitemap.interceptor.LoginInterceptor;
import com.sitemap.util.TimeUtil;

@RouteBind(path = "/ac")
@Before(LoginInterceptor.class)
public class AccountCompanyController extends BaseController {
	public AccountCompanyController() {
		this.tableName = st_company;
	}
	
	public void queryCompany(){
		renderJson(Db.find("select id,name from st_company"));
	}
	
	
	public void show() {
		Page<Record>p;
		if(getCid()==0){
			p=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName);

		}else{
			p=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName+" where cid =?",getCid());

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
	
	
	
	public void save(){
		
	/*	if(StringUtils.isNotEmpty(getPara("starttime"))){
			if(StringUtils.isNotEmpty(getPara("endtime"))){
				if(TimeUtil.getTimestamp(getPara("starttime")).getTime()>TimeUtil.getTimestamp(getPara("endtime")).getTime()){
					JsonResultUtils.outJson(new Result("1开始时间请小于结束时间"), getResponse());
				}
				return;
			}else{
				JsonResultUtils.outJson(new Result("2结束时间不能为空"), getResponse());
				return;
			}
		}else{
			if(StringUtils.isNotEmpty(getPara("starttime"))){
				if(TimeUtil.getTimestamp(getPara("starttime")).getTime()>TimeUtil.getTimestamp(getPara("endtime")).getTime()){
					JsonResultUtils.outJson(new Result("3开始时间请小于结束时间"), getResponse());
					return;
				}
			}else{
				JsonResultUtils.outJson(new Result("4开始时间不能为空"), getResponse());
				return;
			}
		}*/
		
		Integer id=Db.findFirst("select companySequ.nextval as id from DUAL").getBigDecimal("id").intValue();
		Db.save(tableName,getRecord().remove("oper").set("id", id).set("createtime", new Date(TimeUtil.getNewDate().getTime())));
		Db.save(st_role, new Record().set("type", 1).set("cid", id).set("name", "小蜜蜂").set("id", "roleSequ.nextval"));
		Db.save(st_role, new Record().set("type", 2).set("cid", id).set("name", "蜂头").set("id", "roleSequ.nextval"));
		Db.save(st_role, new Record().set("type", 3).set("cid", id).set("name", "司机").set("id", "roleSequ.nextval"));
		Integer rid=Db.findFirst("select roleSequ.nextval as id from DUAL").getBigDecimal("id").intValue();
		Db.save(st_role, new Record().set("type", 4).set("cid", id).set("name", "管理员").set("id", rid));
			for(int j=1;j<18;j++){
				Db.save(st_role_func, new Record().set("id", "role_funcSequ.nextval").set("rid", rid)
						.set("cid", id).set("fid", j));
			}
	}
}
