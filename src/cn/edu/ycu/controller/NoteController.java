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
	//笔记列表
	@RequestMapping("/loadNotes.do")
	@ResponseBody
	public NoteResult loadNotes(String bookId, String userId, String noteType){
		return noteService.loadNotes(bookId, userId, noteType);
	}
	//笔记内容
	@RequestMapping("loadNoteBody.do")
	@ResponseBody
	public NoteResult loadNoteBody(String noteId, String userId){
		return noteService.loadNoteBody(noteId, userId);
	}
	//修改笔记
	@RequestMapping("/changeNoteBody.do")
	@ResponseBody
	public NoteResult changeNoteBody(String noteId, String noteTitle, String noteBody){
		return noteService.changeNoteBody(noteId, noteTitle, noteBody);
	}
	//添加笔记
	@RequestMapping("/addNote.do")
	@ResponseBody
	public NoteResult addNote(String userId, String bookId, String noteTitle){
		return noteService.addNote(userId, bookId, noteTitle);
	}
	//移动或还原笔记
	@RequestMapping("/moveNote.do")
	@ResponseBody
	public NoteResult moveAndReplayNote(String newBookId, String noteId){
		return noteService.moveAndReplayNote(newBookId, noteId);
	}
	//分享笔记
	@RequestMapping("/shareNote.do")
//	@ResponseBody
	public String shareNote(HttpServletRequest request, String noteId){
		NoteResult result = noteService.shareNote(noteId);
		request.setAttribute("result", result);
		return "../share/addShare.do";
	}
	//删除分享
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
	//移入回收站
	@RequestMapping("/removeNote.do")
	@ResponseBody
	public NoteResult removeNote(String noteId){
		return noteService.removeNote(noteId);
	}
	//回收站列表
	@RequestMapping("/loadRollbackNote.do")
	@ResponseBody
	public NoteResult loadRollbackNote(String userId){
		return noteService.loadRollbackNote(userId);
	}
	//搜索
	@RequestMapping("/search.do")
	@ResponseBody
	public NoteResult search(String userId, String noteTitle, int page){
		return noteService.search(userId, noteTitle, page);
	}
	//彻底删除笔记
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
//			result.setMsg("类别已删除，笔记删除失败!");
//			return result;
//		}
//		String bookId = (String) request.getAttribute("bookId");
	}
}
