package com.elm.dao;

import java.sql.SQLException;
import java.util.List;

import com.elm.bean.Sender;

public interface SenderDao {
	
	int insertSender(Sender sender) throws SQLException;


	int updateSender(Sender Sender) throws SQLException;


	int deleteSenderById(Integer id) throws SQLException;

	List<Sender> selectAllSender() throws SQLException;

	Sender selectSenderById(Integer id) throws SQLException;

	Sender selectSenderByUsernameAndPassword(String username,String password)throws SQLException;

	List<Sender> selectByCondition(Sender Sender)throws SQLException;
}
