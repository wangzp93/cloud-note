package cn.edu.ycu.entity;

public class ShareBean {
	private String shareId;
	private String shareTitle;
	private int begin;
	private int count;
	public ShareBean() {
		super();
		this.count = 5;
	}
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
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
