package cn.edu.ycu.entity;

public class SearchBean {
	private String noteTitle;
	private String userId;
	private String noteType;
	private int begin;
	private int count;
	public SearchBean(){
		this.count = 5;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNoteType() {
		return noteType;
	}
	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
