package com.elm.service;

import java.sql.SQLException;
import java.util.List;

import com.elm.bean.Order;

public interface OrderService {
	int insertinto(Order order) throws SQLException;
	
	int delete(int id) throws SQLException;
	
	List<Order> selectAll() throws SQLException;
	
	int updateState(int id, String state, int sid) throws SQLException;

	Order findById(int id)throws SQLException;
}
