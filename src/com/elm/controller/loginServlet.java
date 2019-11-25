package com.elm.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.elm.bean.Foods;
import com.elm.bean.Order;
import com.elm.bean.Sender;
import com.elm.bean.User;
import com.elm.service.FoodsService;
import com.elm.service.OrderService;
import com.elm.service.SenderService;
import com.elm.service.UserService;
import com.elm.service.impl.FoodsServiceImpl;
import com.elm.service.impl.OrderServiceImpl;
import com.elm.service.impl.SenderServiceImpl;
import com.elm.service.impl.UserServiceImpl;
import com.elm.util.MD5;

@WebServlet(urlPatterns="/servlet/login")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private FoodsService foodsService = new FoodsServiceImpl();
	private SenderService senderService = new SenderServiceImpl();
	private OrderService orderService = new OrderServiceImpl();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String piccode = (String)session.getAttribute("piccode");
		String checkcode = request.getParameter("checkcode");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		session.setAttribute("type", type);
		switch (type) {
		case "user":
			System.out.println("user");
			try {
				user(request, response, session, piccode, checkcode, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "sender":
			System.out.println("sender");
			try {
				sender(request, response, session, piccode, checkcode, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "guanliyuan":
			System.out.println("guanliyuan");
			guanliyuan(request, response, piccode, checkcode, username, password);
			break;
		default:
			break;
		}
	}

	private void guanliyuan(HttpServletRequest request, HttpServletResponse response, String piccode, String checkcode,
			String username, String password) throws ServletException, IOException {
		if(piccode.equalsIgnoreCase(checkcode) && password.equals("123") && username.equals("王二")) {
			request.getSession().setAttribute("gly", "gly");
			request.getRequestDispatcher("/auth/gly/glymain.jsp").forward(request, response);
			return;
		}else {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/login.jsp");
		}
	}

	private void sender(HttpServletRequest request, HttpServletResponse response, HttpSession session, String piccode,
			String checkcode, String username, String password) throws SQLException, ServletException, IOException {
		Sender sender = senderService.login(username, MD5.encode(password));
		System.out.println(sender);
		if(sender != null && piccode.equalsIgnoreCase(checkcode)) {
			session.setAttribute("sender", sender);
			List<Order> orderList = orderService.selectAll();
			request.setAttribute("orderList", orderList);
			request.getRequestDispatcher("/servlet/order?op=dingDanList").forward(request, response);
		}else {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/login.jsp");
		}
	}

	private void user(HttpServletRequest request, HttpServletResponse response, HttpSession session, String piccode,
			String checkcode, String username, String password) throws SQLException, ServletException, IOException {
		User user = userService.login(username, MD5.encode(password));
		if(user != null && piccode.equalsIgnoreCase(checkcode)) {
			session.setAttribute("user", user);
			List<Foods> foodsAll = foodsService.selectAll();
			request.getSession().setAttribute("foodsList", foodsAll);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}else {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/login.jsp");
		}
	}

}
