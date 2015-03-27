package com.sitemap.listener;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.sitemap.util.Tasks;
/**
 * 自动更新，自动发短信，自动提交到期任务的监听器
 */
public class AutoUpdateData implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Running  -------------- contextDestroyed(): " + "定时任务消除");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			// 定时任务
			Tasks.startDayForTime(new TimerTask() {
				public void run() {
					try {
						//update();
					} catch (Exception e) {
					}
				};
			}, "23:59:59", 12);
			
			
		} catch (Exception e) {
			System.out.println("Running  : contextInitialized(): " + "定时任务开启失败!!!!");
			e.printStackTrace();
		}
	}
	

}
