package com.sitemap.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
/**
 * 登陆拦截器
 */
public class LoginInterceptor implements Interceptor{
	public void intercept(ActionInvocation ai) {
		Controller controller=ai.getController();
		if(controller.getAttr("user")==null){
			controller.redirect(controller.getRequest().getServletContext().getContextPath()+"/tiaozhuan.jsp");
		}else{
			ai.invoke();
		}
	}
}
