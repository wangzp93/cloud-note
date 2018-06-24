package cn.edu.ycu.service;

import cn.edu.ycu.entity.NoteResult;

public interface NoteBookService {
	public NoteResult loadBooks(String userId);
	public NoteResult renameBook(String bookId, String newBookName);
	public NoteResult addBook(String userId, String bookName);
	public NoteResult deleteBook(String bookId);
}
