package cn.edu.ycu.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Note implements Serializable{
	private String ycu_note_id;
	private String ycu_notebook_id;
	private String ycu_user_id;
	private String ycu_note_status_id;
	private String ycu_note_type_id;
	private String ycu_note_title;
	private String ycu_note_body;
	private Long ycu_note_create_time;
	private Long ycu_note_last_modify_time;
	public String getYcu_note_id() {
		return ycu_note_id;
	}
	public void setYcu_note_id(String ycuNoteId) {
		ycu_note_id = ycuNoteId;
	}
	public String getYcu_notebook_id() {
		return ycu_notebook_id;
	}
	public void setYcu_notebook_id(String ycuNotebookId) {
		ycu_notebook_id = ycuNotebookId;
	}
	public String getYcu_user_id() {
		return ycu_user_id;
	}
	public void setYcu_user_id(String ycuUserId) {
		ycu_user_id = ycuUserId;
	}
	public String getYcu_note_status_id() {
		return ycu_note_status_id;
	}
	public void setYcu_note_status_id(String ycuNoteStatusId) {
		ycu_note_status_id = ycuNoteStatusId;
	}
	public String getYcu_note_type_id() {
		return ycu_note_type_id;
	}
	public void setYcu_note_type_id(String ycuNoteTypeId) {
		ycu_note_type_id = ycuNoteTypeId;
	}
	public String getYcu_note_title() {
		return ycu_note_title;
	}
	public void setYcu_note_title(String ycuNoteTitle) {
		ycu_note_title = ycuNoteTitle;
	}
	public String getYcu_note_body() {
		return ycu_note_body;
	}
	public void setYcu_note_body(String ycuNoteBody) {
		ycu_note_body = ycuNoteBody;
	}
	public Long getYcu_note_create_time() {
		return ycu_note_create_time;
	}
	public void setYcu_note_create_time(Long ycuNoteCreateTime) {
		ycu_note_create_time = ycuNoteCreateTime;
	}
	public Long getYcu_note_last_modify_time() {
		return ycu_note_last_modify_time;
	}
	public void setYcu_note_last_modify_time(Long ycuNoteLastModifyTime) {
		ycu_note_last_modify_time = ycuNoteLastModifyTime;
	}
}
