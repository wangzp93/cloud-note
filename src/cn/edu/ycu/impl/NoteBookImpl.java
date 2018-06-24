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
	 * ����δɾ�������б�(non-Javadoc)
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
	 * �޸������(non-Javadoc)
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
		result.setMsg("���������ɹ�!");
		return result;
	}
	/**
	 * ��ӷ���(non-Javadoc)
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
			result.setMsg("���ࡰ" + bookName + "���Ѵ���!");
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
			result.setMsg("���������Ψһ!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ӷ���ɹ�!");
		result.setData(bookId);
		return result;
	}
	/**
	 * ɾ�����(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteBookService#deleteBook(java.lang.String)
	 */
	@Transactional
	public NoteResult deleteBook(String bookId) {
		NoteResult result = new NoteResult();
		NoteBookBean bookBean = new NoteBookBean();
		bookBean.setBookId(bookId);
		if(noteBookDao.findBook(bookBean) == null){
			result.setStatus(1);
			result.setMsg("����𲻴���!");
			return result;
		}
		if(noteBookDao.deleteBook(bookBean) != 1){
			result.setStatus(2);
			result.setMsg("ɾ�����Ψһ!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("ɾ���ɹ�!");
		result.setData(bookId);
		return result;
	}
}
