package cn.edu.ycu.dao;

import java.util.List;

import cn.edu.ycu.entity.NoteBook;
import cn.edu.ycu.entity.NoteBookBean;


public interface NoteBookDao {
	public List<NoteBook> findBookList(NoteBookBean bookBean);
	public NoteBook findBook(NoteBookBean bookBean);
	public int updateById(NoteBook book);
	public int saveBook(NoteBook book);
	public int deleteBook(NoteBookBean bookBean);
}
