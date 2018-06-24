package cn.edu.ycu.service;

import cn.edu.ycu.entity.NoteResult;

public interface NoteService {
	public NoteResult loadNotes(String bookId, String userId, String noteType);
	public NoteResult loadNoteBody(String noteId, String userId);
	public NoteResult changeNoteBody(String noteId, String noteTitle, String noteBody);
	public NoteResult addNote(String userId, String bookId, String noteTitle);
	public NoteResult moveAndReplayNote(String newBookId, String noteId);
	public NoteResult shareNote(String noteId);
	public NoteResult delShare(NoteResult result);
	public NoteResult removeNote(String noteId);
	public NoteResult loadRollbackNote(String userId);
	public NoteResult search(String userId, String noteTitle, int page);
	public NoteResult deleteNotes(String noteId, NoteResult result);
}
