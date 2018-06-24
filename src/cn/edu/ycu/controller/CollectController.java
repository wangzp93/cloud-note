package cn.edu.ycu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.service.CollectService;

@Controller
@RequestMapping("/collect")
public class CollectController {
	@Resource
	private CollectService collectService;
	//添加收藏
	@RequestMapping("/addCollect.do")
	@ResponseBody
	public NoteResult addCollect(HttpServletRequest request){
		try{
			NoteResult result = (NoteResult)request.getAttribute("result");
//			Share share = (Share)request.getAttribute("share");
//			String userId = (String)request.getAttribute("userId");
			return collectService.addCollect(result);
		}finally{
			request.removeAttribute("result");
		}
	}
	//加载收藏列表
	@RequestMapping("/loadCollects.do")
	@ResponseBody
	public NoteResult loadCollects(String userId){
		return collectService.loadCollects(userId);
	}
	//加载收藏内容
	@RequestMapping("/loadCollectBody.do")
	@ResponseBody
	public NoteResult loadCollectBody(String collectId){
		return collectService.loadCollectBody(collectId);
	}
	//删除收藏
	@RequestMapping("/deleteCollect.do")
	@ResponseBody
	public NoteResult delCollect(String collectId){
		return collectService.delCollect(collectId);
	}
}
