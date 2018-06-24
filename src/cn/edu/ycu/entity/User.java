package cn.edu.ycu.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	private String ycu_user_id;
	private String ycu_user_name;
	private String ycu_user_password;
	private String ycu_user_token;
	private String ycu_user_desc;
	public String getYcu_user_id() {
		return ycu_user_id;
	}
	public void setYcu_user_id(String ycuUserId) {
		ycu_user_id = ycuUserId;
	}
	public String getYcu_user_name() {
		return ycu_user_name;
	}
	public void setYcu_user_name(String ycuUserName) {
		ycu_user_name = ycuUserName;
	}
	public String getYcu_user_password() {
		return ycu_user_password;
	}
	public void setYcu_user_password(String ycuUserPassword) {
		ycu_user_password = ycuUserPassword;
	}
	public String getYcu_user_token() {
		return ycu_user_token;
	}
	public void setYcu_user_token(String ycuUserToken) {
		ycu_user_token = ycuUserToken;
	}
	public String getYcu_user_desc() {
		return ycu_user_desc;
	}
	public void setYcu_user_desc(String ycuUserDesc) {
		ycu_user_desc = ycuUserDesc;
	}
}
