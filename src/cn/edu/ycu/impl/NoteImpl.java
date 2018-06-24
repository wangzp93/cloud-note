package cn.edu.ycu.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ycu.dao.NoteDao;
import cn.edu.ycu.entity.Note;
import cn.edu.ycu.entity.NoteBean;
import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.entity.SearchBean;
import cn.edu.ycu.service.NoteService;
import cn.edu.ycu.util.NoteUtil;


@Service("noteService")
public class NoteImpl implements NoteService
{
	@Resource
	private NoteDao noteDao;
	/**
	 * ����δɾ���ʼ��б�(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#loadNotes(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public NoteResult loadNotes(String bookId, String userId, String noteType) {
		NoteResult result = new NoteResult();
		NoteBean noteBean = new NoteBean();
		noteBean.setBookId(bookId);
		noteBean.setUserId(userId);
		noteBean.setTypeId(noteType);
		List<Note> notes = noteDao.findNoteList(noteBean);
		if(notes == null){
			result.setStatus(1);
			return result;
		}
		result.setStatus(0);
		result.setData(notes);
		return result;
	}
	/**
	 * ���رʼ�����(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#loadNoteBody(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public NoteResult loadNoteBody(String noteId, String userId) {
		NoteResult result = new NoteResult();
		NoteBean noteBean = new NoteBean();
		noteBean.setNoteId(noteId);
		noteBean.setUserId(userId);
		Note note = noteDao.findNote(noteBean);
		if(note == null){
			result.setStatus(1);
			return result;
		}
		result.setStatus(0);
		result.setData(note);
		return result;
	}
	/**
	 * �޸ıʼ�����(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#changeNoteBody(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult changeNoteBody(String noteId, String noteTitle, String noteBody) {
		NoteResult result = new NoteResult();
		NoteBean noteBean = new NoteBean();
		noteBean.setNoteId(noteId);
		Note note = noteDao.findNote(noteBean);
		if(note == null){
			result.setStatus(1);
			return result;
		}
		note.setYcu_note_title(noteTitle);
		note.setYcu_note_body(noteBody);
		note.setYcu_note_last_modify_time(System.currentTimeMillis());
		noteDao.updateNote(note); 
		result.setStatus(0);
		result.setMsg("����ɹ�!");
		return result;
	}
	/**
	 * ��ӱʼ�(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#addNote(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult addNote(String userId, String bookId, String noteTitle) {
		NoteResult result = new NoteResult();
		NoteBean noteBean = new NoteBean();
		noteBean.setUserId(userId);
		noteBean.setBookId(bookId);
		noteBean.setNoteTitle(noteTitle);
		noteBean.setTypeId(NoteUtil.DELETE_N);
		if(noteDao.findNote(noteBean) != null){
			result.setStatus(1);
			result.setMsg("�ʼǡ�" + noteTitle + "���Ѵ���!");
			return result;
		}
		Note note = new Note();
		String noteId = NoteUtil.createId();
		Long time = System.currentTimeMillis();
		note.setYcu_user_id(userId);
		note.setYcu_notebook_id(bookId);
		note.setYcu_note_id(noteId);
		note.setYcu_note_title(noteTitle);
		note.setYcu_note_body("");
		note.setYcu_note_create_time(time);
		note.setYcu_note_last_modify_time(time);
		note.setYcu_note_type_id(NoteUtil.DELETE_N);
		note.setYcu_note_status_id(NoteUtil.SHARE_N);
		if(noteDao.saveNote(note) != 1){
			result.setStatus(2);
			result.setMsg("���������Ψһ!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��ӱʼǳɹ�!");
		result.setData(noteId);
		return result;
	}
	/**
	 * �ƶ��ʼ�(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#moveNote(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult moveAndReplayNote(String newBookId, String noteId) {
		NoteResult result = new NoteResult();
		NoteBean noteBean = new NoteBean();
		noteBean.setNoteId(noteId);
		if(noteDao.findNote(noteBean) == null){
			result.setStatus(1);
			result.setMsg("�ñʼǲ�����!");
			return result;
		}
		Note note = new Note();
		note.setYcu_note_id(noteId);
		note.setYcu_notebook_id(newBookId);
		note.setYcu_note_type_id(NoteUtil.DELETE_N);
		if(noteDao.updateNote(note) != 1){
			result.setStatus(2);
			result.setMsg("������Ŀ��Ψһ !");
			return result;
		}
		result.setStatus(0);
		result.setMsg("�ƶ��ʼǳɹ�!");
		return result;
	}
	/**
	 * ����ʼ�(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#shareNote(java.lang.String)
	 */
	@Transactional
	public NoteResult shareNote(String noteId) {
		NoteResult result = new NoteResult();
		NoteBean noteBean = new NoteBean();
		noteBean.setNoteId(noteId);
		if(NoteUtil.SHARE_Y.equals(noteDao.findNote(noteBean).getYcu_note_status_id())){
			result.setStatus(1);
			result.setMsg("�ñʼ��ѷ���!");
			return result;
		}
		Note note = new Note();
		note.setYcu_note_id(noteId);
		note.setYcu_note_status_id(NoteUtil.SHARE_Y);
		if(noteDao.updateNote(note) != 1){
			result.setStatus(2);
			result.setMsg("������Ŀ��Ψһ!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("����ɹ�!");
		result.setData(noteDao.findNote(noteBean));
		return result;
	}
	/**
	 * ɾ������(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#delShare(cn.edu.ycu.entity.NoteResult)
	 */
	@Transactional
	public NoteResult delShare(NoteResult result){
		if(result.getStatus() == 0){
			NoteBean noteBean = new NoteBean();
			noteBean.setNoteId((String)result.getData());
			Note note = noteDao.findNote(noteBean);
			if(note != null){
				note.setYcu_note_status_id(NoteUtil.SHARE_N);
				if(noteDao.updateNote(note) == 1){
					return result;
				}
				result.setMsg("�޸ķ���״̬��ʧ��!");
			}
		}
		return result;
	}
	/**
	 * �ʼ��������վ(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#deleteNote(java.lang.String)
	 */
	@Transactional
	public NoteResult removeNote(String noteId) {
		NoteResult result = new NoteResult();
		NoteBean noteBean = new NoteBean();
		noteBean.setNoteId(noteId);
		noteBean.setTypeId(NoteUtil.DELETE_N);
		if(noteDao.findNote(noteBean) == null){
			result.setStatus(1);
			return result;
		}
		Note note = new Note();
		note.setYcu_note_id(noteId);
		note.setYcu_note_type_id(NoteUtil.DELETE_Y);
		if(noteDao.updateNote(note) != 1){
			result.setStatus(2);
			result.setMsg("������Ŀ��Ψһ!");
			return result;
		}
		result.setStatus(0);
		result.setMsg("ɾ���ʼǳɹ�");
		return result;
	}
	/**
	 * ���ػ���վ(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#loadRollbackNote(java.lang.String)
	 */
	@Transactional(readOnly = true)
	public NoteResult loadRollbackNote(String userId) {
		NoteResult result = new NoteResult();
		NoteBean noteBean = new NoteBean();
		noteBean.setUserId(userId);
		noteBean.setTypeId(NoteUtil.DELETE_Y);
		List<Note> rollbackNotes = noteDao.findNoteList(noteBean);
		result.setStatus(0);
		result.setData(rollbackNotes);
		return result;
	}
	/**
	 * ����(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#search(java.lang.String, java.lang.String, int)
	 */
	@Transactional(readOnly = true)
	public NoteResult search(String userId, String noteTitle, int page) {
		NoteResult result = new NoteResult();
		SearchBean searchBean = new SearchBean();
		int count = searchBean.getCount();
		int begin = (page - 1) * count;
		searchBean.setUserId(userId);
		searchBean.setNoteType(NoteUtil.DELETE_N);
		searchBean.setNoteTitle("%" + noteTitle + "%");
		searchBean.setBegin(begin);
		searchBean.setCount(count);
		List<Note> searchList = noteDao.findLike(searchBean);
//		System.out.println(searchList);
		if(searchList.size() == 0){
			result.setStatus(1);
			result.setMsg("�Ѽ���ȫ����������!");
			return result;
		}
		result.setStatus(0);
		result.setData(searchList);
		return result;
	}
	/**
	 * ɾ���ʼ�(һ�������)(non-Javadoc)
	 * @see cn.edu.ycu.service.NoteService#deleteNotes(java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult deleteNotes(String noteId, NoteResult result) {
		NoteBean noteBean = new NoteBean();
		if(result == null){
			result = new NoteResult();
			noteBean.setNoteId(noteId);
			if(noteDao.deleteNotes(noteBean) != 1){
				result.setStatus(1);
				result.setMsg("ɾ���ʼǲ�Ψһ!");
				return result;
			}
		}else{
			String bookId = (String)result.getData();
			noteBean.setBookId(bookId);
			if(noteDao.deleteNotes(noteBean) < 0){
				result.setStatus(3);
				result.setMsg("���ɾ���ɹ����ʼ�ɾ��ʧ��!");
				result.setData(null);
				return result;
			}
		}
		result.setStatus(0);
		result.setData(null);
		return result;
//		if(noteId == null && bookId == null){
//			result.setStatus(2);
//			result.setMsg("����Ŀ������!");
//			return result;
//		}else if(bookId != null){
//			
//		}else if(noteId != null){
//			
//		}
//		result.setStatus(0);
//		NoteResult result = new NoteResult();
//		NoteBean noteBean = new NoteBean();
//		if(noteId == null && bookId == null){
//			result.setStatus(2);
//			result.setMsg("����Ŀ������!");
//			return result;
//		}else if(bookId != null){
//			noteBean.setBookId(bookId);
//			if(noteDao.findNoteList(noteBean) != null){
//				noteDao.deleteNotes(noteBean);
//			}
//			result.setStatus(0);
//			return result;
//		}else if(noteId != null){
//			noteBean.setNoteId(noteId);
//			if(noteDao.deleteNotes(noteBean) < 1){
//				result.setStatus(1);
//				result.setMsg("ɾ����Ŀ��Ψһ!");
//				return result;
//			}
//		}
//		result.setStatus(0);
//		return result;
	}
	
}
