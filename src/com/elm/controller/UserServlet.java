package com.elm.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

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


@MultipartConfig
@WebServlet(urlPatterns="/servlet/user")
public class UserServlet extends HttpServlet {
	private UserService userService = new UserServiceImpl();
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch (op) {
		case "regist":
			try {
				regist(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "add":
			try {
				add(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				list(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "findById":
			try {
				findById(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "update":
			try {
				update(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "delete":
			try {
				delete(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "search":
			try {
				search(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "logout":
			logout(request, response);
			break;
		case "dingDan":
			try {
				dingDan(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	private void dingDan(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		OrderService orderService = new OrderServiceImpl();
		FoodsService foodsService = new FoodsServiceImpl();
		SenderService senderService = new SenderServiceImpl();
		
		if (request.getSession().getAttribute("orderId") == null) {
			request.setAttribute("aim", "0");
			request.getRequestDispatcher("/auth/user/myorder.jsp").forward(request, response);
			return;
		}
		if (request.getSession().getAttribute("orderId") != null) {
			int orderId = (int) request.getSession().getAttribute("orderId");
			Order order = orderService.findById(orderId);
			Foods foods = foodsService.selectFoodsById(order.getFid());
			
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
			String img = foods.getImg();
			int sid = order.getSid();
			
			Sender sender = senderService.findseById(sid);
			if (sid == 0) {
				request.setAttribute("aim", "1");//1代表未接单
				request.setAttribute("name", name);
				request.setAttribute("addr", foodsAddr);
				request.getRequestDispatcher("/auth/user/myorder.jsp").forward(request, response);
				return;
			}
			
			String senderName = sender.getUsername();
			String tel = sender.getTel();
			
			request.setAttribute("amount", order.getAmount());
			request.setAttribute("state", order.getState());
			request.setAttribute("orderId", orderId);
			request.setAttribute("name", name);
			request.setAttribute("img", img);
			request.setAttribute("senderName", senderName);
			request.setAttribute("tel", tel);
			request.setAttribute("addr", foodsAddr);
						
			request.setAttribute("aim", "2");//2代表骑手正在送单
			request.getRequestDispatcher("/auth/user/myorder.jsp").forward(request, response);
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("user", null);
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath+"/login.jsp");
	}
	
	private void search(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String idStr = req.getParameter("id");
		Integer id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		String username = req.getParameter("username");
		String tel = req.getParameter("tel");
		String addr = req.getParameter("addr");
		
		User user = new User();
		
		user.setId(id);
		user.setUsername(username);
		user.setTel(tel);
		user.setAddr(addr);
		
		List<User> userList = userService.selectByCondition(user);
		
		HttpSession session = req.getSession();
		session.setAttribute("userList", userList);
		req.getRequestDispatcher("/auth/gly/userlist.jsp").forward(req, resp);
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String idStr = req.getParameter("id");
		Integer id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		
		int res = userService.deleteUserById(id);
		if(res == 1) {
			req.setAttribute("aim", "servlet/user?op=list");
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		}
	}

	private void update(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		String idStr = req.getParameter("id");
		Integer id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			 id = Integer.parseInt(idStr);
		}
		String username = req.getParameter("username");
		String nickname = req.getParameter("nickname");
		String tel = req.getParameter("tel");
		String addr = req.getParameter("addr");
		String img = "";
		
		User user1 = userService.findUserById(id);
		
		String fileName = uploadImg(req);
		if(fileName == null) {
			img = req.getParameter("OldImg");
		}else {
			img = "upload/user/" + fileName;
		}
		
		System.out.println("-------" + img);
		//更新时密码默认值是原密码，所以传过来时需要再次加密
		User user = new User(id, nickname, username, user1.getPassword(), img, tel, addr);
		
		int res = userService.update(user);
		
		if(res == 1 && req.getParameter("who").equals("user")) {
			req.setAttribute("aim", "main.jsp");
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		}else if(res == 1 && req.getParameter("who").equals("gly")){
			req.setAttribute("aim", "auth/gly/userlist.jsp");
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		}else {
			req.setAttribute("aim", "auth/user/edit.jsp");
			req.getRequestDispatcher("/failed.jsp").forward(req, resp);
		}
	}

	private void findById(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String idStr = req.getParameter("id");
		int id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		
		User user = userService.findUserById(id);
		
		if(user != null && req.getParameter("who").equals("user")) {//一级一级传递身份信息
			req.setAttribute("user", user);
			req.getRequestDispatcher("/auth/user/edit.jsp").forward(req, resp);
		}else {
			req.setAttribute("user", user);
			req.getRequestDispatcher("/auth/gly/edit.jsp").forward(req, resp);
		}
	}

	private void list(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		List<User> userList = userService.selectAll();
		HttpSession session = req.getSession();
		session.setAttribute("userList", userList);
		req.getRequestDispatcher("/auth/gly/userlist.jsp").forward(req, resp);
	}

	private void add(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		String username = req.getParameter("username");
		String nickname = req.getParameter("nickname");
		String password = req.getParameter("password");
		String tel = req.getParameter("tel");
		String addr = req.getParameter("addr");
		
		String fileName = uploadImg(req);
		String img = "upload/user/"+fileName;
		
		User user = new User(username, nickname, password, img, tel, addr);
		System.out.println(user);
		int res = userService.insert(user);
		
		if(res == 1) {
			req.setAttribute("aim", "servlet/user?op=list");
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		}else {
			req.setAttribute("aim", "auth/gly/useradd.jsp");
			req.getRequestDispatcher("/failed.jsp").forward(req, resp);
		}
	}

	private void regist(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		HttpSession session = request.getSession();
		
		String nickname = request.getParameter("nickname");
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		
		String fileName = uploadImg(request);
		String img = "upload/user/" + fileName;
		
		User user = new User(nickname, username, MD5.encode(password1), img, tel, addr);
		
		if(!(password1 != null && password1.equals(password2))) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/userregist.jsp");
			return;
		}
		
		User regist = userService.regist(user);
		
		if(user != null) {
		    session.setAttribute("password", password2);
		    regist.setPassword(password2);//用户更新信息时用
			request.setAttribute("currentUser", regist);//登录时把该用户的信息传过去
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	private String uploadImg(HttpServletRequest req) throws IOException, ServletException {
		// 存储路径
		String savePath = req.getServletContext().getRealPath("/");
		savePath = savePath + "upload\\user";

		System.out.println("存储路径" + savePath);

		File dir = new File(savePath);
		if (!dir.exists())
			dir.mkdirs();

		// 获取上传的文件集合
		Collection<Part> parts = req.getParts();
		// 上传单个文件
		String fileName = "";
		if (parts.size() > 0) {
			Part part = req.getPart("img");
			System.out.println(part);
			String header = part.getHeader("content-disposition");
			System.out.println("文件名" + header);
			// 获取文件名
			if (header.lastIndexOf(".") < 0) {
				return null;
				//如果用户在更改的时候没有上传照片，就不会再上传照片了
			}
			fileName = getFileName(header);
			// 把文件写到指定路径
			part.write(savePath + File.separator + fileName);
		}
		System.out.println("ok");
		return fileName;
	}

	private String getFileName(String header) {
		//给上传的图片产生一个随机的名字
		
		String suffix = header.substring(header.lastIndexOf('.'), header.length() - 1);
		System.out.println(suffix);

		return UUID.randomUUID().toString() + suffix;
	}

}
