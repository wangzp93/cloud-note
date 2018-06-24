package cn.edu.ycu.entity;

public class NoteBean{
	private String noteId;
	private String bookId;
	private String userId;
	private String noteTitle;
	private String typeId;
//	private List<String> noteIds;
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNoteTitle() {
		return noteTitle;
	}
	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
//	public List<String> getNoteIds() {
//		return noteIds;
//	}
//	public void setNoteIds(List<String> noteIds) {
//		this.noteIds = noteIds;
//	}
}
