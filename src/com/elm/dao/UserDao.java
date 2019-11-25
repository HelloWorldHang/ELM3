package com.elm.dao;

import java.sql.SQLException;
import java.util.List;

import com.elm.bean.User;

public interface UserDao {
	int insertUser(User user) throws SQLException;
	int updateUser(User user) throws SQLException;
	int deleteUserById(Integer id) throws SQLException;
	User selectUserById(Integer id) throws SQLException;
	List<User> selectAllUser() throws SQLException;
	User selectUserByUserameAndPasword(String username, String password) throws SQLException;
	List<User> selectByCondition(User user)throws SQLException;
	

}
