package cn.edu.ycu.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ycu.dao.NoteBookDao;
import cn.edu.ycu.entity.NoteBook;
import cn.edu.ycu.entity.NoteBookBean;
import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.service.NoteBookService;
import cn.edu.ycu.util.NoteUtil;


@Service("bookService")
public class NoteBookImpl implements NoteBookService{
	@Resource
	private NoteBookDao noteBookDao;
	/**
	 * 加载未删除分类列表(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteBookService#loadBooks(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public NoteResult loadBooks(String userId) {
		NoteResult result = new NoteResult();
		NoteBookBean bookBean = new NoteBookBean();
		bookBean.setUserId(userId);
		List<NoteBook> books = noteBookDao.findBookList(bookBean);
		result.setStatus(0);
		result.setData(books);
		return result;
	}
	/**
	 * 修改类别名(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteBookService#renameBook(java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult renameBook(String bookId, String newBookName) {
		NoteResult result = new NoteResult();
		NoteBook book = new NoteBook();
		book.setYcu_notebook_id(bookId);
		book.setYcu_notebook_name(newBookName);
		if(noteBookDao.updateById(book) != 1){
			result.setStatus(1);
			return result;
		}
		result.setStatus(0);
		result.setMsg("重命名类别成功!");
		return result;
	}
	/**
	 * 添加分类(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteBookService#addBook(java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult addBook(String userId, String bookName) {
		NoteResult result = new NoteResult();
		NoteBookBean bookBean = new NoteBookBean();
		bookBean.setUserId(userId);
		bookBean.setBookName(bookName);
		bookBean.setTypeId(NoteUtil.DELETE_N);
		if(noteBookDao.findBook(bookBean) != null){
			result.setStatus(1);
			result.setMsg("分类“" + bookName + "”已存在!");
			return result;
		}
		NoteBook book = new NoteBook();
		String bookId = NoteUtil.createId();
		book.setYcu_user_id(userId);
		book.setYcu_notebook_id(bookId);
		book.setYcu_notebook_name(bookName);
		book.setYcu_notebook_type_id(NoteUtil.DELETE_N);
		book.setYcu_notebook_createtime(new Timestamp(System.currentTimeMillis()));
		if(noteBookDao.saveBook(book) != 1){
			result.setStatus(2);
			result.setMsg("添加条数不唯一!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("添加分类成功!");
		result.setData(bookId);
		return result;
	}
	/**
	 * 删除类别(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteBookService#deleteBook(java.lang.String)
	 */
	@Transactional
	public NoteResult deleteBook(String bookId) {
		NoteResult result = new NoteResult();
		NoteBookBean bookBean = new NoteBookBean();
		bookBean.setBookId(bookId);
		if(noteBookDao.findBook(bookBean) == null){
			result.setStatus(1);
			result.setMsg("该类别不存在!");
			return result;
		}
		if(noteBookDao.deleteBook(bookBean) != 1){
			result.setStatus(2);
			result.setMsg("删除类别不唯一!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("删除成功!");
		result.setData(bookId);
		return result;
	}
}
