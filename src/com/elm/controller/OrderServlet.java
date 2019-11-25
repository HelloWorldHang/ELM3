package com.elm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.elm.bean.DingDan;
import com.elm.bean.Foods;
import com.elm.bean.Order;
import com.elm.dao.SenderDao;
import com.elm.dao.impl.SenderDaoImpl;
import com.elm.service.FoodsService;
import com.elm.service.OrderService;
import com.elm.service.impl.FoodsServiceImpl;
import com.elm.service.impl.OrderServiceImpl;


@WebServlet(urlPatterns="/servlet/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    OrderService orderService = new OrderServiceImpl();   
	FoodsService foodsService = new FoodsServiceImpl();
	SenderDao senderDao = new SenderDaoImpl();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch (op) {
		case "dingDanList":
			try {
				dingDanList(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "search"://按条件查找订单
			//管理员对订单进行管理
			break;
		default:
			System.out.println("执行了servlet/order中的默认方法");
			break;
		}
		
	}


	private void dingDanList(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Order> orderList = orderService.selectAll();
		System.out.println(orderList);
		List<DingDan> dingDanList = new ArrayList<>();
		for(Order order : orderList) {
			int amount = order.getAmount();
			System.out.println(amount);
			Integer id = order.getId();
			Foods foods = foodsService.selectFoodsById(order.getFid());
			System.out.println(foods);
			String name = foods.getName();
			int foodsAddrInt = foods.getShop();
			String foodsAddr = "";
			if (foodsAddrInt > 0 && foodsAddrInt <= 100) {
				foodsAddr = "西苑餐厅" + foodsAddrInt + "号窗口";
			}else if(foodsAddrInt > 100 && foodsAddrInt <= 150){
				foodsAddr = "东苑餐厅" + foodsAddrInt + "号窗口";
			}else {
				foodsAddr = "清真餐厅" + foodsAddrInt + "号窗口";
			}
			float psf = foods.getPsf();
			DingDan dingDan = new DingDan(id,name,amount,foodsAddr,order.getAddr(),order.getUtel(),order.getNote(),psf,order.getState());
			System.out.println(dingDan.getAmount());
			dingDanList.add(dingDan);
		}
		//在sender.jsp中遍历该订单
		request.setAttribute("dingDanList", dingDanList);
		request.getRequestDispatcher("/sender.jsp").forward(request, response);
	}


}
