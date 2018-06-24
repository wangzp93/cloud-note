package cn.edu.ycu.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.ycu.dao.UserDao;
import cn.edu.ycu.entity.NoteResult;
import cn.edu.ycu.entity.User;
import cn.edu.ycu.entity.UserBean;
import cn.edu.ycu.service.UserService;
import cn.edu.ycu.util.NoteUtil;


@Service("userService")
public class UserImpl implements UserService{
	@Resource
	private UserDao userDao;
	/**
	 * 检查登录(non-Javadoc)
	 * @see cn.edu.ycu.service.UserService#checkLogin(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly = true)
	public NoteResult checkLogin(String username, String password) {
		NoteResult result = new NoteResult();
		UserBean userBean = new UserBean();
		userBean.setUserName(username);
		User user = userDao.findUser(userBean);
		if(user == null){
			result.setStatus(1);
			result.setMsg("该用户不存在");
			return result;
		}
		if(!user.getYcu_user_password().equals(NoteUtil.md5(password))){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}
	/**
	 * 检查注册(non-Javadoc)
	 * @see cn.edu.ycu.service.UserService#checkRegist(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult checkRegist(String username, String password, String nickname) {
		NoteResult result = new NoteResult();
		UserBean userBean = new UserBean();
		userBean.setUserName(username);
		if(userDao.findUser(userBean) != null){
			result.setStatus(1);
			result.setMsg("该用户已存在");
			return result;
		}
		User user = new User();
		user.setYcu_user_id(NoteUtil.createId());
		user.setYcu_user_name(username);
		user.setYcu_user_password(NoteUtil.md5(password));
		user.setYcu_user_token(nickname);
		userDao.saveUser(user);
		result.setStatus(0);
		result.setMsg("恭喜您，注册成功!");
		return result;
	}
	/**
	 * 修改密码(non-Javadoc)
	 * @see cn.edu.ycu.service.UserService#changePassword(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult changePassword(String userId, String last_password, String new_password) {
		NoteResult result = new NoteResult();
		UserBean userBean = new UserBean();
		userBean.setUserId(userId);
		User user = userDao.findUser(userBean);
		if(user == null){
			result.setStatus(1);
			return result;
		}
		if(!user.getYcu_user_password().equals(NoteUtil.md5(last_password))){
			result.setStatus(2);
			result.setMsg("原密码错误");
			return result;
		}
		user.setYcu_user_password(NoteUtil.md5(new_password));
		userDao.updateUser(user);
		result.setStatus(0);
		result.setMsg("修改密码成功，请重新登录!");
		return result;
	}
}
