package com.elm.service;

import java.sql.SQLException;
import java.util.List;

import com.elm.bean.User;

public interface UserService {
	User login(String username,String pwd) throws SQLException;
	User regist(User user) throws SQLException;
	int insert(User user) throws SQLException;
	int deleteUserById(Integer id) throws SQLException;
	int update(User user) throws SQLException;
	User findUserById(Integer id)throws SQLException;
	List<User> selectAll()throws SQLException;
	List<User> selectByCondition(User user)throws SQLException;
}
