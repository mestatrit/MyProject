package com.sitemap.system;
/**
 * 权限控制类
 */
public class Authority {
	public Integer cId = null;
	public Integer pId = null;
	/**
	 * 是否能登陆
	 */
	public boolean login=true;
	
	/**
	 * 
	 * 账户管理
	 */
	public boolean account;
	/**
	 * 公司管理
	 */
	public boolean account_company;
	/**
	 * 项目管理
	 */
	public boolean account_project;
	/**
	 * 角色管理
	 */
	public boolean account_role;
	/**
	 * 用户管理
	 */
	public boolean account_users;
	/**
	 * 外场人员管理
	 */
	public boolean outuser;
	/**
	 * 任务管理
	 */
	public boolean task;
	/**
	 * 奖励管理
	 */
	public boolean task_reward;
	public boolean taks_count;
	public boolean taks_set;
	/**
	 * 留电信息
	 */
	public boolean electric;
	public boolean electric_record;
	public boolean electric_count;
	/**
	 * 用车申请
	 */
	public boolean car;
	/**
	 * 文件上报
	 */
	public boolean report;
	/**
	 * 文件审阅
	 */
	public boolean approve;
	/**
	 * 预警管理
	 */
	public boolean waring;

	public Authority(Integer id) {
		this.cId = id;
	}

	/**
	 * 创建所有权限
	 */
	public Authority(boolean b) {
		this.account = b;
		this.account_company = b;
		this.account_project = b;
		this.account_role = b;
		this.account_users = b;
		this.outuser = b;
		this.task = b;
		this.task_reward = b;
		this.taks_count = b;
		this.taks_set = b;
		this.electric = b;
		this.electric_record = b;
		this.electric_count = b;
		this.car = b;
		this.report = b;
		this.approve = b;
		this.waring = b;
	}

	public Authority(Integer cId, Integer pId, boolean account, boolean account_company, boolean account_project,
			boolean account_role, boolean account_users, boolean outuser, boolean task, boolean task_reward,
			boolean taks_count, boolean taks_set, boolean electric, boolean electric_record, boolean electric_count,
			boolean car, boolean report, boolean approve, boolean waring) {
		super();
		this.cId = cId;
		this.pId = pId;
		this.account = account;
		this.account_company = account_company;
		this.account_project = account_project;
		this.account_role = account_role;
		this.account_users = account_users;
		this.outuser = outuser;
		this.task = task;
		this.task_reward = task_reward;
		this.taks_count = taks_count;
		this.taks_set = taks_set;
		this.electric = electric;
		this.electric_record = electric_record;
		this.electric_count = electric_count;
		this.car = car;
		this.report = report;
		this.approve = approve;
		this.waring = waring;
	}

	public Integer getcId() {
		return cId;
	}

	public void setcId(Integer cId) {
		this.cId = cId;
	}

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public boolean isAccount() {
		return account;
	}

	public void setAccount(boolean account) {
		this.account = account;
	}

	public boolean isOutuser() {
		return outuser;
	}

	public void setOutuser(boolean outuser) {
		this.outuser = outuser;
	}

	public boolean isCar() {
		return car;
	}

	public void setCar(boolean car) {
		this.car = car;
	}

	public boolean isReport() {
		return report;
	}

	public void setReport(boolean report) {
		this.report = report;
	}

	public boolean isElectric() {
		return electric;
	}

	public void setElectric(boolean electric) {
		this.electric = electric;
	}

	public boolean isWaring() {
		return waring;
	}

	public void setWaring(boolean waring) {
		this.waring = waring;
	}

	public boolean isAccount_company() {
		return account_company;
	}

	public void setAccount_company(boolean account_company) {
		this.account_company = account_company;
	}

	public boolean isAccount_project() {
		return account_project;
	}

	public void setAccount_project(boolean account_project) {
		this.account_project = account_project;
	}

	public boolean isAccount_role() {
		return account_role;
	}

	public void setAccount_role(boolean account_role) {
		this.account_role = account_role;
	}

	public boolean isAccount_users() {
		return account_users;
	}

	public void setAccount_users(boolean account_users) {
		this.account_users = account_users;
	}

	public boolean isTask() {
		return task;
	}

	public void setTask(boolean task) {
		this.task = task;
	}

	public boolean isTask_reward() {
		return task_reward;
	}

	public void setTask_reward(boolean task_reward) {
		this.task_reward = task_reward;
	}

	public boolean isTaks_count() {
		return taks_count;
	}

	public void setTaks_count(boolean taks_count) {
		this.taks_count = taks_count;
	}

	public boolean isTaks_set() {
		return taks_set;
	}

	public void setTaks_set(boolean taks_set) {
		this.taks_set = taks_set;
	}

	public boolean isElectric_record() {
		return electric_record;
	}

	public void setElectric_record(boolean electric_record) {
		this.electric_record = electric_record;
	}

	public boolean isElectric_count() {
		return electric_count;
	}

	public void setElectric_count(boolean electric_count) {
		this.electric_count = electric_count;
	}

	public boolean isApprove() {
		return approve;
	}

	public void setApprove(boolean approve) {
		this.approve = approve;
	}
}
