package cn.edu.ycu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {
	@Resource
	private NoteService noteService;
	//�ʼ��б�
	@RequestMapping("/loadNotes.do")
	@ResponseBody
	public NoteResult loadNotes(String bookId, String userId, String noteType){
		return noteService.loadNotes(bookId, userId, noteType);
	}
	//�ʼ�����
	@RequestMapping("loadNoteBody.do")
	@ResponseBody
	public NoteResult loadNoteBody(String noteId, String userId){
		return noteService.loadNoteBody(noteId, userId);
	}
	//�޸ıʼ�
	@RequestMapping("/changeNoteBody.do")
	@ResponseBody
	public NoteResult changeNoteBody(String noteId, String noteTitle, String noteBody){
		return noteService.changeNoteBody(noteId, noteTitle, noteBody);
	}
	//��ӱʼ�
	@RequestMapping("/addNote.do")
	@ResponseBody
	public NoteResult addNote(String userId, String bookId, String noteTitle){
		return noteService.addNote(userId, bookId, noteTitle);
	}
	//�ƶ���ԭ�ʼ�
	@RequestMapping("/moveNote.do")
	@ResponseBody
	public NoteResult moveAndReplayNote(String newBookId, String noteId){
		return noteService.moveAndReplayNote(newBookId, noteId);
	}
	//����ʼ�
	@RequestMapping("/shareNote.do")
//	@ResponseBody
	public String shareNote(HttpServletRequest request, String noteId){
		NoteResult result = noteService.shareNote(noteId);
		request.setAttribute("result", result);
		return "../share/addShare.do";
	}
	//ɾ������
	@RequestMapping("/delShare.do")
	@ResponseBody
	public NoteResult delShare(HttpServletRequest request){
		try{
			NoteResult result = (NoteResult)request.getAttribute("result");
			return noteService.delShare(result);
		}finally{
			request.removeAttribute("result");
		}
	}
	//�������վ
	@RequestMapping("/removeNote.do")
	@ResponseBody
	public NoteResult removeNote(String noteId){
		return noteService.removeNote(noteId);
	}
	//����վ�б�
	@RequestMapping("/loadRollbackNote.do")
	@ResponseBody
	public NoteResult loadRollbackNote(String userId){
		return noteService.loadRollbackNote(userId);
	}
	//����
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult search(String userId, String noteTitle, int page){
		return noteService.search(userId, noteTitle, page);
	}
	//����ɾ���ʼ�
	@RequestMapping("/deleteNotes.do")
	@ResponseBody
	public NoteResult deleteNotes(String noteId, HttpServletRequest request){
		NoteResult result = null;
		if(request != null){
			try{
				result = (NoteResult)request.getAttribute("result");
			}finally{
				request.removeAttribute("result");
			}
		}
		return noteService.deleteNotes(noteId, result);
//		if(noteId == null && result == null){
//			result.setStatus(3);
//			result.setMsg("�����ɾ�����ʼ�ɾ��ʧ��!");
//			return result;
//		}
//		String bookId = (String) request.getAttribute("bookId");
	}
}
