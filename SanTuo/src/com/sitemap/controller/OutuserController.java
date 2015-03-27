package com.sitemap.controller;
import java.util.ArrayList;
import java.util.List;
import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.sitemap.interceptor.LoginInterceptor;
import com.sitemap.system.TreeList;
@Before(LoginInterceptor.class)
public class OutuserController extends BaseController {
	public OutuserController() {
		this.tableName = st_userInfor;
	}

	public void index() {
		setAttr("project", Db.find("select * from st_project"));
		render("/OutUser.jsp");
	}

	public void queryUserForGroup() {
		renderJson(Db.find("select * from st_userInfor a where a.gid=?", getPara("gid")));
	}

	public void queryTree() {
		List<TreeList> ts = new ArrayList<TreeList>();
		if(getCid()==0){
			for(Record c:Db.find("select id,name from st_company")){
				TreeList tree = new TreeList(c.getStr("name"), 0, 0);
				tree.setNodes(TreeList.getProjectTree(c.getBigDecimal("id").intValue()));
				ts.add(tree);
			}
		}else{
			ts=TreeList.getProjectTree(getCid());
		}
		renderJson(ts);
	}
}
