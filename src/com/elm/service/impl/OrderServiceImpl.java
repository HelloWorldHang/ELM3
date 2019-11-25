package com.elm.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.elm.bean.Order;
import com.elm.dao.OrderDao;
import com.elm.dao.impl.OrderDaoImpl;
import com.elm.service.OrderService;

public class OrderServiceImpl implements OrderService{
	OrderDao orderDao = new OrderDaoImpl();
	@Override
	public int insertinto(Order order) throws SQLException {
		return orderDao.insertinto(order);
	}

	@Override
	public int delete(int id) throws SQLException {
		return orderDao.delete(id);
	}

	@Override
	public List<Order> selectAll() throws SQLException {
		return orderDao.selectAll();
	}

	@Override
	public int updateState(int id,String state,int sid) throws SQLException {
		return orderDao.updateState(id,state,sid);
	}

	@Override
	public Order findById(int id) throws SQLException {
		return orderDao.findById(id);
	}

}
