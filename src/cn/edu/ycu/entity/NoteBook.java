package cn.edu.ycu.entity;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class NoteBook implements Serializable{
	private String ycu_notebook_id;
	private String ycu_user_id;
	private String ycu_notebook_type_id;
	private String ycu_notebook_name;
	private String ycu_notebook_desc;
	private Timestamp ycu_notebook_createtime;
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
	public String getYcu_notebook_type_id() {
		return ycu_notebook_type_id;
	}
	public void setYcu_notebook_type_id(String ycuNotebookTypeId) {
		ycu_notebook_type_id = ycuNotebookTypeId;
	}
	public String getYcu_notebook_name() {
		return ycu_notebook_name;
	}
	public void setYcu_notebook_name(String ycuNotebookName) {
		ycu_notebook_name = ycuNotebookName;
	}
	public String getYcu_notebook_desc() {
		return ycu_notebook_desc;
	}
	public void setYcu_notebook_desc(String ycuNotebookDesc) {
		ycu_notebook_desc = ycuNotebookDesc;
	}
	public Timestamp getYcu_notebook_createtime() {
		return ycu_notebook_createtime;
	}
	public void setYcu_notebook_createtime(Timestamp ycuNotebookCreatetime) {
		ycu_notebook_createtime = ycuNotebookCreatetime;
	}
}
