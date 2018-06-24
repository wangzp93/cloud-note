package cn.edu.ycu.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Collect implements Serializable{
	private String ycu_collect_id;
	private String ycu_collect_title;
	private String ycu_collect_body;
	private String ycu_user_id;
	public String getYcu_collect_id() {
		return ycu_collect_id;
	}
	public void setYcu_collect_id(String ycuCollectId) {
		ycu_collect_id = ycuCollectId;
	}
	public String getYcu_collect_title() {
		return ycu_collect_title;
	}
	public void setYcu_collect_title(String ycuCollectTitle) {
		ycu_collect_title = ycuCollectTitle;
	}
	public String getYcu_collect_body() {
		return ycu_collect_body;
	}
	public void setYcu_collect_body(String ycuCollectBody) {
		ycu_collect_body = ycuCollectBody;
	}
	public String getYcu_user_id() {
		return ycu_user_id;
	}
	public void setYcu_user_id(String ycuUserId) {
		ycu_user_id = ycuUserId;
	}
}
