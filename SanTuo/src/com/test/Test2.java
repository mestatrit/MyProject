package com.test;
import java.sql.SQLException;
import java.sql.Timestamp;
import net.loyin.jFinal.plugin.AutoTableBindPlugin;
import net.loyin.jFinal.plugin.TableNameStyle;
import net.loyin.util.PropertiesContent;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.dialect.OracleDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.sitemap.util.TimeUtil;

public class Test2 {
	public Test2() {
		DruidPlugin druidPlugin = new DruidPlugin(PropertiesContent.get("jdbc.url"),
				PropertiesContent.get("jdbc.username"), PropertiesContent.get("jdbc.password"),
				PropertiesContent.get("jdbc.driver"));
		druidPlugin.start();
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setDialect(new OracleDialect());
		arp.start();
		AutoTableBindPlugin autoTableBindPlugin = new AutoTableBindPlugin(druidPlugin, TableNameStyle.LOWER);
		autoTableBindPlugin.setShowSql(true);
		autoTableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory());
		autoTableBindPlugin.start();
	}

	public static void main(String[] args) throws SQLException {
		Test2 t = new Test2();
		//System.out.println(Db.find("select city from T_ST_DIS_CITY group by city"));
//		for (int i = 0; i < 11; i++) {
//			Db.save("st_userinfor", new Record().set("id", "userSequ.nextval").set("lname", "s").set("pwd", "s").set("rid", "0").set("gid", "0").set("createtime",TimeUtil.getNewDate()));
//
//		}
		int a=Db.queryBigDecimal("select count(1) from st_waring_history a,st_userinfor b where a.status =0"
				+ " and a.userid=b.id and b.pid in(select id from st_project where cid = ? )",10).intValue();
				System.out.println(a);
}

}
