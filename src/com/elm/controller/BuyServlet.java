package com.elm.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elm.bean.Foods;
import com.elm.bean.Order;
import com.elm.service.FoodsService;
import com.elm.service.OrderService;
import com.elm.service.UserService;
import com.elm.service.impl.FoodsServiceImpl;
import com.elm.service.impl.OrderServiceImpl;
import com.elm.service.impl.UserServiceImpl;


@WebServlet(urlPatterns="/servlet/buy")
public class BuyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    OrderService orderService = new OrderServiceImpl();
    FoodsService foodsService = new FoodsServiceImpl();
    UserService userService = new UserServiceImpl();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch (op) {
		case "buy":
			//用户点击提交订单时执行此操作，加入一条订单信息到数据库
			try {
				buy(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}


	private void buy(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String fidStr = request.getParameter("fid");
		String amountStr = request.getParameter("amount");
		
		int amount = 0;
		if (amountStr != null && amountStr.trim().length()>0) {
			amount = Integer.parseInt(amountStr);
		}
		int fid = 0;
		if (fidStr != null && fidStr.trim().length()>0) {
			fid = Integer.parseInt(fidStr);
		}
		//食物销量加一
		Foods foods = foodsService.selectFoodsById(fid);
		Foods foods2 = new Foods(foods.getId(),foods.getName(),foods.getPrice(),foods.getImg(),foods.getSale()+1,foods.getIntro(),foods.getEvaluate(),foods.getPsf(),foods.getShop());
		int res = foodsService.update(foods2);
		if (res == 1) {
			System.out.println("update sale success");
		}
		//买完后将该食物从美食车中删除
		foodsService.deleteByIdFromCart(fid, request);
		//获取相关信息存入订单列表
		String utel = request.getParameter("utel");
		String addr = request.getParameter("addr");
		String note = request.getParameter("note");
		Order order = new Order(null,fid,0,note,addr,utel,"未接单",amount);
		int orderId = orderService.insertinto(order);
		request.getSession().setAttribute("orderId", orderId);
		request.getRequestDispatcher("/auth/user/buysuccess.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
