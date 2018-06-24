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
	 * ����¼(non-Javadoc)
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
			result.setMsg("���û�������");
			return result;
		}
		if(!user.getYcu_user_password().equals(NoteUtil.md5(password))){
			result.setStatus(2);
			result.setMsg("�������");
			return result;
		}
		result.setStatus(0);
		result.setMsg("��¼�ɹ�");
		result.setData(user);
		return result;
	}
	/**
	 * ���ע��(non-Javadoc)
	 * @see cn.edu.ycu.service.UserService#checkRegist(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Transactional
	public NoteResult checkRegist(String username, String password, String nickname) {
		NoteResult result = new NoteResult();
		UserBean userBean = new UserBean();
		userBean.setUserName(username);
		if(userDao.findUser(userBean) != null){
			result.setStatus(1);
			result.setMsg("���û��Ѵ���");
			return result;
		}
		User user = new User();
		user.setYcu_user_id(NoteUtil.createId());
		user.setYcu_user_name(username);
		user.setYcu_user_password(NoteUtil.md5(password));
		user.setYcu_user_token(nickname);
		userDao.saveUser(user);
		result.setStatus(0);
		result.setMsg("��ϲ����ע��ɹ�!");
		return result;
	}
	/**
	 * �޸�����(non-Javadoc)
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
			result.setMsg("ԭ�������");
			return result;
		}
		user.setYcu_user_password(NoteUtil.md5(new_password));
		userDao.updateUser(user);
		result.setStatus(0);
		result.setMsg("�޸�����ɹ��������µ�¼!");
		return result;
	}
}
