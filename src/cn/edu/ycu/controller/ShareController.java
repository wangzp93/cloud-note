package cn.edu.ycu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.service.ShareService;

@Controller
@RequestMapping("/share")
public class ShareController {
	@Resource
	private ShareService shareService;
	//添加分享
	@RequestMapping("/addShare.do")
	@ResponseBody
	public NoteResult addShare(HttpServletRequest request){
		try{
			NoteResult result = (NoteResult)request.getAttribute("result");
			return shareService.addShare(result);
		}finally{
			request.removeAttribute("result");
		}
//		if(request.getAttribute("note") != null){
//			Note note = (Note)request.getAttribute("note");
//			return shareService.addShare(note);
//		}
//		NoteResult result = new NoteResult();
//		result.setStatus(1);
//		result.setMsg("该笔记已分享!");
//		return result;
	}
	//加载分享列表
	@RequestMapping("/loadShares.do")
	@ResponseBody
	public NoteResult loadShares(String shareTitle, int page){
		if("".equals(shareTitle)){
			return shareService.loadShares(null, page);
		}
		return shareService.loadShares(shareTitle, page);
	}
	//加载分享内容
	@RequestMapping("/loadShareBody.do")
	@ResponseBody
	public NoteResult loadShareBody(String shareId){
		return shareService.loadShareBody(shareId, null);
	}
	//添加收藏
	@RequestMapping("/addCollect.do")
	public String addCollect(HttpServletRequest request, String shareId, String userId){
		NoteResult result = shareService.loadShareBody(shareId, userId);
//		request.setAttribute("share", result.getData());
//		request.setAttribute("userId", userId);
		request.setAttribute("result", result);
		return "../collect/addCollect.do";
	}
	//删除分享
	@RequestMapping("/delShare.do")
	public String delShare(HttpServletRequest request, String shareId){
		NoteResult result = shareService.delShare(shareId);
		request.setAttribute("result", result);
		return "../note/delShare.do";
	}
}
