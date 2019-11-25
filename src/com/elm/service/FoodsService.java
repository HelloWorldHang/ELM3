package com.elm.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.elm.bean.Foods;
public interface FoodsService {
	List<Foods> selectAll()throws SQLException;
	
	int insert(Foods foods)throws SQLException;
	
	int deleteById(int id)throws SQLException;
	
	int update(Foods foods)throws SQLException;

	Foods findById(int id)throws SQLException;
	
	Foods selectFoodsById(Integer id) throws SQLException;

	List<Foods> selectByCondition(Foods foods)throws SQLException;
	
	List<Foods> selectByShop(int shop)throws SQLException;

	List<Foods> selectFoodsByShunxu(int a)throws SQLException;

	void deleteByIdFromCart(int fid, HttpServletRequest request);
}
