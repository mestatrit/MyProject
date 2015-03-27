package com.sitemap.controller;
import net.loyin.jFinal.anatation.RouteBind;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.sitemap.interceptor.LoginInterceptor;
import com.sitemap.interceptor.Tr;
@RouteBind(path="/ar")
@Before(LoginInterceptor.class)
public class AccountRoleController extends BaseController {
	
	public AccountRoleController(){
		this.tableName=st_role;
	}
	
	public void index(){
		render("/account_role.jsp");
	}
	
	public void edit(){
		Db.update("update st_role set name =? where id=? and cid=?",getPara("NAME"),getPara("ID"),getPara("CID"));
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
	
	public void del(){
		for(String id:getPara("id").split(",")){
			if(Integer.parseInt(id)<5){
				renderJson(false);
				break;
			}
			Db.update("delete from st_role where id =? cid=?",id,getPara("cid"));
		}
	}
	/**
	 * 保存对应公司权限
	 */
	@Before(Tr.class)
	public void saveAuthority(){
		Db.update("delete from st_role_func where cid=? and rid=?",getCid(),getPara("rid"));
		for(String fid:getPara("fid").split(",")){
			Db.save(st_role_func, new Record().set("cid", getCid()).set("id", "role_funcSequ.nextval")
					.set("rid", getPara("rid")).set("fid", Integer.parseInt(fid)));
		}
		renderJson(true);
	}
}
