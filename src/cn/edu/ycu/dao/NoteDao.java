package cn.edu.ycu.dao;

import java.util.List;

import cn.edu.ycu.entity.Note;
import cn.edu.ycu.entity.NoteBean;
import cn.edu.ycu.entity.SearchBean;

public interface NoteDao {
	public List<Note> findNoteList(NoteBean noteBean);
	public Note findNote(NoteBean noteBean);
	public int updateNote(Note note);
	public int saveNote(Note note);
	public int deleteNotes(NoteBean noteBean);
	public List<Note> findLike(SearchBean searchBean);
}
