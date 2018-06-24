package cn.edu.ycu.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Share implements Serializable{
	private String ycu_share_id;
	private String ycu_user_id;
	private String ycu_note_id;
	private int ycu_share_up;
	private int ycu_share_down;
	private String ycu_share_title;
	private String ycu_share_body;
	public String getYcu_share_id() {
		return ycu_share_id;
	}
	public void setYcu_share_id(String ycuShareId) {
		ycu_share_id = ycuShareId;
	}
	public String getYcu_user_id() {
		return ycu_user_id;
	}
	public void setYcu_user_id(String ycuUserId) {
		ycu_user_id = ycuUserId;
	}
	public String getYcu_note_id() {
		return ycu_note_id;
	}
	public void setYcu_note_id(String ycuNoteId) {
		ycu_note_id = ycuNoteId;
	}
	public int getYcu_share_up() {
		return ycu_share_up;
	}
	public void setYcu_share_up(int ycuShareUp) {
		ycu_share_up = ycuShareUp;
	}
	public int getYcu_share_down() {
		return ycu_share_down;
	}
	public void setYcu_share_down(int ycuShareDown) {
		ycu_share_down = ycuShareDown;
	}
	public String getYcu_share_title() {
		return ycu_share_title;
	}
	public void setYcu_share_title(String ycuShareTitle) {
		ycu_share_title = ycuShareTitle;
	}
	public String getYcu_share_body() {
		return ycu_share_body;
	}
	public void setYcu_share_body(String ycuShareBody) {
		ycu_share_body = ycuShareBody;
	}
}
