package cn.edu.ycu.dao;

import cn.edu.ycu.entity.User;
import cn.edu.ycu.entity.UserBean;

public interface UserDao {
	public User findUser(UserBean userBean);
	public int updateUser(User user);
	public int saveUser(User user);
}
