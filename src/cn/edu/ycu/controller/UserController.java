package cn.edu.ycu.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	//µÇÂ¼
	@RequestMapping("/login.do")
	@ResponseBody
	public NoteResult login(String username, String password){
		return userService.checkLogin(username, password);
	}
	//×¢²á
	@RequestMapping("/regist.do")
	@ResponseBody
	public NoteResult regist(String username, String password, String nickname){
		return userService.checkRegist(username, password, nickname);
	}
	//¸ÄÃÜ
	@RequestMapping("/changePassword.do")
	@ResponseBody
	public NoteResult changePassword(String userId, String last_password, String new_password){
		return userService.changePassword(userId, last_password, new_password);
	}
}
