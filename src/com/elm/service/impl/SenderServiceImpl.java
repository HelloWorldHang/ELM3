package com.elm.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.elm.bean.Sender;
import com.elm.dao.SenderDao;
import com.elm.dao.impl.SenderDaoImpl;
import com.elm.service.SenderService;

public class SenderServiceImpl implements SenderService{
	SenderDao senderDao = new SenderDaoImpl();
	@Override
	public int insertSender(Sender sender) throws SQLException {
		return senderDao.insertSender(sender);
	}

	@Override
	public int updateSender(Sender Sender) throws SQLException {
		return senderDao.updateSender(Sender);
	}

	@Override
	public int deleteSenderById(Integer id) throws SQLException {
		return senderDao.deleteSenderById(id);
	}

	@Override
	public List<Sender> selectAllSender() throws SQLException {
		return senderDao.selectAllSender();
	}

	@Override
	public Sender selectSenderById(Integer id) throws SQLException {
		return senderDao.selectSenderById(id);
	}

	@Override
	public Sender selectSenderByUsernameAndPassword(String username, String password) throws SQLException {
		return senderDao.selectSenderByUsernameAndPassword(username, password);
	}

	@Override
	public List<Sender> selectByCondition(Sender sender) throws SQLException {
		return senderDao.selectByCondition(sender);
	}

	@Override
	public Sender login(String nickname, String password) throws SQLException {
		return senderDao.selectSenderByUsernameAndPassword(nickname, password);
	}

	@Override
	public Sender regist(Sender sender) throws SQLException {
		int res =  senderDao.insertSender(sender);
		if(res == 1)
			return sender;
		else
			return null;
	}

	@Override
	public Sender findseById(int id) throws SQLException {
		return senderDao.selectSenderById(id);
	}
	
}
