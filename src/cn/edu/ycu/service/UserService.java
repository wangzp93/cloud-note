package cn.edu.ycu.service;

import cn.edu.ycu.entity.NoteResult;

public interface UserService {
	public  NoteResult checkLogin(String username, String password);
	public NoteResult checkRegist(String username, String password, String nickname);
	public NoteResult changePassword(String userId, String last_password, String new_password);
}
