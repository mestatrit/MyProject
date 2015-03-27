package com.sitemap.system;
import java.util.ArrayList;
import java.util.List;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;

public class TreeList {
	public String text;
	public String icon;
	public String href;
	public List<TreeList> nodes;

	public TreeList(){
		
	}
	public TreeList(String txt,Integer type,int id){
		if (type == 2) {
			text="<span onclick='goifm(\"OutUser.jsp?gid="+id+"\")'>" + txt + "</span>&nbsp;&nbsp;<span id='group"+id+"' title='删除组' onclick='delGroup(\""+id+"\")' class='glyphicon glyphicon-minus-sign'></span>";
			icon="glyphicon glyphicon-menu-hamburger";
		} else if (type == 1) {
			text=txt+"&nbsp;&nbsp;<span title='添加组' id='project"+id+"' onclick='addGroup(\""+id+"\")' class='icon-plus-sign red'></span> &nbsp;</span>";
			icon="glyphicon glyphicon-th-list";
		} else {
			text=txt;
			icon="glyphicon glyphicon-euro";
		}
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}


	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public List<TreeList> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeList> nodes) {
		this.nodes = nodes;
	}

	public String getContent(String txt, Integer type) {
		if (type == 2) {
			return "<span onclick='goifm()'>" + txt + "</span>";
		} else if (type == 1) {
			return "<span>" + txt + "<span class='icon-plus-sign red'></span> &nbsp;<span class='icon-plus-sign black'></span></span>";
		} else {
			return "<span>" + txt + "</span>";
		}
	}
	
	public static List<TreeList> getProjectTree(Integer cid){
		List<TreeList> ts = new ArrayList<TreeList>();
		List<Record> ps = Db.find("select id,name from st_project where cid = ?",cid);
		for (Record p : ps) {
			TreeList tree = new TreeList(p.getStr("name"), 1, p.getBigDecimal("id").intValue());
			List<TreeList> gs = new ArrayList<TreeList>();
			for (Record g : Db.find("select id,name from st_group where pid= ? ", p.get("id"))) {
				TreeList tg = new TreeList(g.getStr("name"), 2, g.getBigDecimal("id").intValue());
				gs.add(tg);
			}
			tree.setNodes(gs);
			ts.add(tree);
		}
		return ts;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
}
