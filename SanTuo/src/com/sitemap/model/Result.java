package com.sitemap.model;

public class Result {
	private String info;
	private boolean success=false;
	
	public Result(){
		this.success=false;
	}
	public Result(boolean b){
		this.success=b;
	}
	public Result(String info){
		this.info=info;
	}
	
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
