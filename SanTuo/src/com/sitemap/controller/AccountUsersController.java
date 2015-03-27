package com.sitemap.controller;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import net.loyin.jFinal.anatation.RouteBind;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.sitemap.interceptor.LoginInterceptor;
import com.sitemap.util.TimeUtil;
import com.test.test;
@RouteBind(path="/au")
@Before(LoginInterceptor.class)
public class AccountUsersController extends BaseController {
	
	public AccountUsersController(){
		this.tableName=st_userInfor;
	}

	public void index(){
		if(getCid()==0){
			setAttr("project",Db.find("select * from st_project "));
			setAttr("role",Db.find("select * from st_role "));
		}else {
			setAttr("project",Db.find("select * from st_project where cid=?",getCid()));
			setAttr("role",Db.find("select * from st_role where cid=?",getCid()));
		}
		render("/account_manager.jsp");
	}
	
	public void show() {
		Page<Record>p;
		if(getCid()==0){
			p=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName+" where id != 1");

		}else{
			p=Db.paginate(getParaToInt("page"), getParaToInt("rows"), "select *", "from " + tableName+" where pid in(select id from st_project where cid =?)",getCid());

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
	
	public void addAccount(){
		try {
			for(int i=0;i<getParaToInt("number");i++){
				Integer id=Db.findFirst("select userSequ.nextval as id from DUAL").getBigDecimal("id").intValue();
				Db.save(tableName, new Record().set("id", id).set("lname", "100"+id).set("pwd","100"+id)
						.set("uname", "小蜜蜂0"+id)
						.set("createtime", new Date(TimeUtil.getNewDate().getTime()))
						.set("rid", getPara("rid")).set("gid", getPara("gid")).set("pid", getPara("pid")));
			}
			renderJson(true);
		} catch (Exception e) {
			renderJson(false);
			e.printStackTrace();
		}
	}
   
	
	public void isExist(){
			if(Db.queryBigDecimal("select count(1) from st_userinfor where lname =? ",getPara("lname")).intValue()>0){
				renderJson(true);
			}else{
				renderJson(false);
			}
	}
	//用户名重复
	public void isExistU(){
		if(Db.queryBigDecimal("select count(1) from st_userinfor where uname =? ",getPara("uname")).intValue()>0){
			renderJson(true);
		}else{
			renderJson(false);
		}
}
	
	/**
	 * 查询组
	 */
	public void zhu() {
		if(StringUtils.isEmpty(getPara("pid"))){
			renderJson(Db.find("select * from " + st_group));
		}else{
			renderJson(Db.find("select * from st_group where pid= ?",getPara("pid")));
		}
		
	}

	/**
	 * 角色
	 */
	public void juese() {
		if(StringUtils.isEmpty(getPara("cid"))){
			renderJson(Db.find("select * from st_role"));
		}else{
			renderJson(Db.find("select * from st_role where cid= ?",getPara("cid")));
		}
	}
	/**
	 * 添加组
	 */
	public void addGroup(){
		Integer id=Db.queryBigDecimal("select groupSequ.nextval from dual").intValue();
		Db.save(st_group,getRecord().set("id",id));
		renderJson(id);
	}
	public void delGroup(){
		Db.deleteById(st_group,getPara("gid"));
		Db.deleteById(st_userInfor, "gid", getPara("gid"));
		renderJson(true);
	}
	
	/**
	 * 查询公司
	 */
	public void queryCompany(){
		renderJson(Db.find("select id,name from st_company"));
	}
	/**
	 * 查询公司下的项目
	 */
	public void queryProject(){
		renderJson(Db.find("select id,name from st_project where cid = ?",getPara("cid")));
	}
	/**
	 * 查询项目下的组
	 */
	public void queryGroup(){
		renderJson(Db.find("select id,name from st_group where pid=?",getPara("pid")));
	}
	public void queryUserForGroup(){
		List<Map<String, Object>> rs=new ArrayList<Map<String, Object>>();
		List<Record> groups=Db.find("select id,name from st_group where pid =? ",getPara("pid"));
		for(Record g:groups){
			Map<String, Object> gm = new HashMap<String, Object>();
			gm.put("group", g.getStr("name"));
			gm.put("user", Db.find("select * from st_userinfor where gid =?",g.get("id")));
			rs.add(gm);
		}
		renderJson(rs);
	}
	public void queryUserForTree(){
			renderJson(queryUserForProjectAndTree(getCid()));
	}
	
	
	public List<Record> queryUserForProjectAndTree(Object id){
		List<Record> data=new LinkedList<Record>();
		List<Record> ps=Db.find("select id,name from st_project where cid = ?",id);
		for(Record p:ps){//遍历查询组
			List<Record> gs=Db.find("select id,name from st_group where pid=?",p.get("id"));
			p.set("id", p.get("id")+"pp").set("pid",0);
				for(Record g:gs){//遍历查询用户
					List<Record> us=Db.find("select id,uname as name from st_userinfor where gid=?",g.get("id"));
					g.set("id", g.get("id")+"gg").set("pid", p.get("id"));
					for(Record u:us){
						u.set("pid", g.get("id"));
					}
					data.addAll(us);
				}
				data.addAll(gs);
		}
		data.addAll(ps);
		return data;
	}
}
