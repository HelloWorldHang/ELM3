package com.elm.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.elm.bean.User;
import com.elm.dao.UserDao;
import com.elm.dao.impl.UserDaoImpl;
import com.elm.service.UserService;

public class UserServiceImpl implements UserService{
	UserDao userDao = new UserDaoImpl();
	@Override
	public User login(String username, String pwd) throws SQLException {
		return userDao.selectUserByUserameAndPasword(username, pwd);
	}

	@Override
	public User regist(User user) throws SQLException {
		int res = userDao.insertUser(user);
		if (res == 1) {
			return user;
		}else {
			return null;
		}
	}

	@Override
	public int insert(User user) throws SQLException {
		return userDao.insertUser(user);
	}

	@Override
	public int deleteUserById(Integer id) throws SQLException {
		
		return userDao.deleteUserById(id);
	}

	@Override
	public int update(User user) throws SQLException {
		return userDao.updateUser(user);
	}

	@Override
	public User findUserById(Integer id) throws SQLException {
		return userDao.selectUserById(id);
	}

	@Override
	public List<User> selectAll() throws SQLException {
		return userDao.selectAllUser();
	}

	@Override
	public List<User> selectByCondition(User user) throws SQLException {
		return userDao.selectByCondition(user);
	}

}
