package cn.edu.ycu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.service.NoteBookService;

@Controller
@RequestMapping("/book")
public class NoteBookController {
	@Resource
	private NoteBookService bookService;
	//���ط����б�
	@RequestMapping("/loadBooks.do")
	@ResponseBody
	public NoteResult loadBooks(String userId){
		return bookService.loadBooks(userId);
	}
	//����������
	@RequestMapping("/renameNoteBook.do")
	@ResponseBody
	public NoteResult renameNoteBook(String bookId, String newBookName){
		return bookService.renameBook(bookId, newBookName);
	}
	//������
	@RequestMapping("/addNoteBook.do")
	@ResponseBody
	public NoteResult addNoteBook(String userId, String bookName){
		return bookService.addBook(userId, bookName);
	}
	//ɾ�����
	@RequestMapping("/deleteBook.do")
	public String deleteBook(HttpServletRequest request, String bookId){
		NoteResult result = bookService.deleteBook(bookId);
		/*RedirectAttributes attr = new RedirectAttributesModelMap();
		if(result.getStatus() == 0){
			attr.addAttribute("bookId", bookId);
		}
		return "redirect:../note/deleteNotes.do";*/
		request.setAttribute("result", result);
		return "../note/deleteNotes.do";
	}
}
