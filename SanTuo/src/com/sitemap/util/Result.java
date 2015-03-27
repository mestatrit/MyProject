package com.sitemap.util;

public class Result {
	private String msg;//返回信息
	private int type;
	private String idVal;
	private boolean status;//判断是否成功
	private Object content;//返回具体参数
	public Result(){
		status=false;
	}
	public Result(boolean judge){
		this.status=judge;
	}
	public Result(String msg){
		this.status=false;
		this.msg=msg;
	}
	public Result(int type,String msg){
		this.type=type;
		this.status=false;
		this.msg=msg;
	}
	
	public Result(boolean judge,String msg){
		this.status=judge;
		this.msg=msg;
	}
	
	public Result(String msg, boolean judge, Object content) {
		this.msg = msg;
		this.status = judge;
		this.content = content;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean judge) {
		this.status = judge;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIdVal() {
		return idVal;
	}
	public void setIdVal(String idVal) {
		this.idVal = idVal;
	}
}
