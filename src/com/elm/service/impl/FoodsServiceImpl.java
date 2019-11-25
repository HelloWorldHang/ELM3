package com.elm.service.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.elm.bean.CartItem;
import com.elm.bean.Foods;
import com.elm.dao.FoodsDao;
import com.elm.dao.impl.FoodsDaoImpl;
import com.elm.service.FoodsService;

public class FoodsServiceImpl implements FoodsService{
	FoodsDao foodsDao = new FoodsDaoImpl();
	@Override
	public List<Foods> selectAll() throws SQLException {
		return foodsDao.selectAllFoods();
	}

	@Override
	public int insert(Foods foods) throws SQLException {
		return foodsDao.insertFoods(foods);
	}

	@Override
	public int deleteById(int id) throws SQLException {
		return foodsDao.deleteFoodsById(id);
	}

	@Override
	public int update(Foods foods) throws SQLException {
		return foodsDao.updateFoods(foods);
	}

	@Override
	public Foods findById(int id) throws SQLException {
		return foodsDao.selectFoodsById(id);
	}

	@Override
	public List<Foods> selectByCondition(Foods foods) throws SQLException {
		return foodsDao.selectByCondition(foods);
	}

	@Override
	public List<Foods> selectByShop(int shop) throws SQLException {
		return foodsDao.selectByShop(shop);
	}

	@Override
	public Foods selectFoodsById(Integer id) throws SQLException {
		return foodsDao.selectFoodsById(id);
	}

	@Override
	public List<Foods> selectFoodsByShunxu(int a) throws SQLException {
		return foodsDao.selectFoodsByShunxu(a);
	}

	@Override
	public void deleteByIdFromCart(int fid,HttpServletRequest req) {
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked") 
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		System.out.println(cart.toString());
		//获取map对象中value值的迭代器
		Iterator<Integer> iterator = cart.keySet().iterator();
		while(iterator.hasNext()) {
			Integer key = iterator.next();
			if(key == fid) {
				iterator.remove();
			}
		}
		System.out.println(cart.toString());
	}

}
