package com.elm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elm.bean.CartItem;
import com.elm.bean.Foods;
import com.elm.bean.User;
import com.elm.service.FoodsService;
import com.elm.service.impl.FoodsServiceImpl;

@MultipartConfig
@WebServlet(urlPatterns="/servlet/cart")
public class CartServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private FoodsService foodsService = new FoodsServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("op");
		System.out.println(op);
		switch (op) {
		case "addcar":
			try {
				addCar(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "show":
			req.getRequestDispatcher("/car.jsp").forward(req, resp);
			break;
		case "delete":
			delete(req, resp);
			break;
		case "buy":
			//在购物车中点击购买执行此操作，并没有购买，只是跳转到购买页面并把相应的信息带过去
			try {
				buy(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void buy(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		String username = user.getUsername();
		String amountStr = req.getParameter("amount");
		
		int amount = 0;
		if (amountStr != null && amountStr.trim().length()>0) {
			amount = Integer.parseInt(amountStr);
		}
		String fidStr = req.getParameter("fid");
		int fid = 0;
		if (fidStr != null && fidStr.trim().length()>0) {
			fid = Integer.parseInt(fidStr);
		}
		
		Foods foods = foodsService.findById(fid);
		String name = foods.getName();
		String img = foods.getImg();
		String addr = user.getAddr();
		String tel = user.getTel();
		int foodsAddrInt = foods.getShop();
		String foodsAddr = "";
		if (foodsAddrInt > 0 && foodsAddrInt <= 100) {
			foodsAddr = "西苑餐厅" + foodsAddrInt + "号窗口";
		}else if(foodsAddrInt > 100 && foodsAddrInt <= 150){
			foodsAddr = "东苑餐厅" + foodsAddrInt + "号窗口";
		}else {
			foodsAddr = "清真餐厅" + foodsAddrInt + "号窗口";
		}
		
		req.setAttribute("amount", amount);
		req.setAttribute("fid", fid);
		req.setAttribute("name", name);
		req.setAttribute("img", img);
		req.setAttribute("addr", addr);
		req.setAttribute("tel", tel);
		req.setAttribute("foodsAddr", foodsAddr);
		req.setAttribute("username", username);
		
		req.getRequestDispatcher("/auth/user/buy.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String idStr = req.getParameter("id");
		Integer id = 0;
		if (idStr != null && idStr.trim().length() > 0) {
		     id = Integer.parseInt(idStr);
		}
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked") 
		Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
		System.out.println(cart.toString());
		//获取map对象中value值的迭代器
		Iterator<Integer> iterator = cart.keySet().iterator();
		while(iterator.hasNext()) {
			Integer key = iterator.next();
			if(key == id) {
				iterator.remove();
			}
		}
		System.out.println(cart.toString());
		//如果cart不为空，返回购物车页面，否则返回主页面
		if(cart.size() > 0) {
			session.setAttribute("cart", cart);
			req.setAttribute("aim", "car.jsp");
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		}else {
			req.setAttribute("aim", "servlet/main");
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		}
		
	}

	private void addCar(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String idStr = req.getParameter("id");
		Integer id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		String amountStr = req.getParameter("amount");
		int amount = 0;
		if(amountStr != null && amountStr.trim().length() > 0) {
			amount = Integer.parseInt(amountStr);
		}
		 
		Foods food = foodsService.findById(id);
		
		CartItem cartItem = new CartItem(food.getId(),food.getShop(),food.getImg(),food.getName(),food.getPrice(),food.getPsf(),amount,(amount*food.getPrice()+food.getPsf()));
		System.out.println(cartItem);
		HttpSession session = req.getSession();
		
		if(session.getAttribute("cart") == null) {
			Map<Integer, CartItem> cart = new HashMap<>();
			cart.put(id, cartItem);
			session.setAttribute("cart", cart);
		}else {
			@SuppressWarnings("unchecked")
			Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
			if(cart.containsKey(id)) {
				CartItem cartItem2 = cart.get(id);
				int t = cartItem2.getAmount() + amount;
				cartItem2.setAmount(t);
				cartItem2.setTotal(t*cartItem2.getPrice()+cartItem2.getPsf());
				cart.put(id, cartItem2);
				session.setAttribute("cart", cart);
			}else {
				cart.put(id, cartItem);
				session.setAttribute("cart", cart);
			}
		}
		req.setAttribute("aim", "servlet/cart?op=show");
		req.getRequestDispatcher("/success.jsp").forward(req, resp);
	}
	
}
