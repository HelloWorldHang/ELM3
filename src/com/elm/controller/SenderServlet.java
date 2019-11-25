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
import com.elm.service.FoodsService;
import com.elm.service.OrderService;
import com.elm.service.SenderService;
import com.elm.service.impl.FoodsServiceImpl;
import com.elm.service.impl.OrderServiceImpl;
import com.elm.service.impl.SenderServiceImpl;
import com.elm.util.MD5;
@MultipartConfig
@WebServlet(urlPatterns="/servlet/sender")
public class SenderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SenderService senderService = new SenderServiceImpl();
	OrderService orderService = new OrderServiceImpl();
	FoodsService foodsService = new FoodsServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String op = request.getParameter("op");
		switch (op) {
		case "regist":
			System.out.println("regist");
			try {
				regist(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		case "search":
			try {
				search(request, response);
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
		case "logout":
			logout(request, response);
			break;
		case "jieDan":
			try {
				jieDan(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "main":
			try {
				main(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

	private void main(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Order> orderList = orderService.selectAll();
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/servlet/order").forward(request, response);
	}

	private void jieDan(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = 0;
		if (idStr != null && idStr.trim().length()>0) {
			id = Integer.parseInt(idStr);
		}
		Order order = orderService.findById(id);

		int fid = order.getFid();//通过食物的ID获取食物的信息
		Foods foods = foodsService.findById(fid);
		int foodsAddrInt = foods.getShop();
		String foodsAddr = "";
		if (foodsAddrInt > 0 && foodsAddrInt <= 100) {
			foodsAddr = "西苑餐厅" + foodsAddrInt + "号窗口";
		}else if(foodsAddrInt > 100 && foodsAddrInt <= 150){
			foodsAddr = "东苑餐厅" + foodsAddrInt + "号窗口";
		}else {
			foodsAddr = "清真餐厅" + foodsAddrInt + "号窗口";
		}
		
		Sender sender = (Sender) request.getSession().getAttribute("sender");
		Integer sid = sender.getId();
		
		String state = "已接单";
		int res = orderService.updateState(id, state, sid);
		//接单后把该
		if (res == 1) {
			request.setAttribute("foodsAddr",foodsAddr);
			request.setAttribute("addr", order.getAddr());
			request.setAttribute("tel", order.getUtel());
			request.getRequestDispatcher("/auth/sender/jieDan.jsp").forward(request, response);
		}
	}
	
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().setAttribute("sender", null);
		String contextPath = request.getContextPath();
		response.sendRedirect(contextPath+"/login.jsp");
	}

	private void regist(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String tel = request.getParameter("tel");
		String idcard = request.getParameter("idcard");
		
		String fileName = uploadImg(request);
		String img = "/upload/sender/" + fileName;
		Sender sender = new Sender(username, MD5.encode(password1), tel, img, idcard);
		
		if(!(password1 != null && password1.equalsIgnoreCase(password2))) {
			String contextPath = request.getContextPath();
			response.sendRedirect(contextPath + "/senderregist.jsp");
			return;
		}
		Sender regist = senderService.regist(sender);
		if(sender != null) {
			regist.setPassword(password2);
			request.setAttribute("currentUser", regist);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String idStr = request.getParameter("id");
		Integer id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			 id = Integer.parseInt(idStr);
		}
		String username = request.getParameter("username");
		String tel = request.getParameter("tel");
		String idcard = request.getParameter("idcard");
		String img = "";
		Sender sender1 = senderService.findseById(id);
		String fileName = uploadImg(request);
		if(fileName == null) {
			img = request.getParameter("OldImg");
		}else {
			img = "/upload/sender/" + fileName;
		}
		
		Sender sender = new Sender(id, username, sender1.getPassword(), tel, img, idcard);
		
		int res = senderService.updateSender(sender);
		
		if(res == 1 && request.getParameter("who").equals("sender")) {
			request.setAttribute("aim", "servlet/sender?op=main");//若是骑手修改，成功后到骑手主页
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else if(res == 1 && request.getParameter("who").equals("gly")){
			request.setAttribute("aim", "servlet/sender?op=list");
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else {
			request.setAttribute("aim", "auth/sender/edit.jsp");
			request.getRequestDispatcher("/failed.jsp").forward(request, response);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String idStr = request.getParameter("id");
		Integer id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		
		String username = request.getParameter("username");
		String tel = request.getParameter("tel");
		
		Sender sender = new Sender();
		
		sender.setId(id);
		sender.setUsername(username);
		sender.setTel(tel);
		
		List<Sender> senderList = senderService.selectByCondition(sender);
		
		HttpSession session = request.getSession();
		session.setAttribute("senderList", senderList);
		request.getRequestDispatcher("/auth/gly/senderlist.jsp").forward(request, response);
	}

	private void findById(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String idStr = request.getParameter("id");
		int id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		Sender sender = senderService.findseById(id);
		if(sender != null && request.getParameter("who").equals("sender")) {
			request.setAttribute("sender", sender);
			request.getRequestDispatcher("/auth/sender/edit.jsp").forward(request, response);
		}else {
			request.setAttribute("sender", sender);
			request.getRequestDispatcher("/auth/gly/editsender.jsp").forward(request, response);
		}
	}

	private void list(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		List<Sender> senderList = senderService.selectAllSender();
		request.setAttribute("senderList", senderList);
		senderList.forEach(System.out::println);
		request.getRequestDispatcher("/auth/gly/senderlist.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, SQLException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String tel = request.getParameter("tel");
		String idcard = request.getParameter("idcard");
		String fileName = uploadImg(request);
		String img = "/upload/sender/"+fileName;
		
		Sender sender = new Sender(username, MD5.encode(password), tel, img, idcard);
		int res = senderService.insertSender(sender);
		System.out.println(res);
		if(res == 1) {
			request.setAttribute("aim", "servlet/sender?op=list");
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}else {
			request.setAttribute("aim", "auth/gly/addsender.jsp");
			request.getRequestDispatcher("/failed.jsp").forward(request, response);
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		String idStr = request.getParameter("id");
		Integer id = 0;
		if(idStr != null && idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		
		int res = senderService.deleteSenderById(id);
		if(res == 1) {
			request.setAttribute("aim", "servlet/sender?op=list");
			request.getRequestDispatcher("/success.jsp").forward(request, response);
		}
	}
	private String uploadImg(HttpServletRequest request) throws IOException, ServletException {
		String savePath = request.getServletContext().getRealPath("/");
		savePath = savePath + "upload\\sender";
		System.out.println("储存路径" + savePath);
		File dir = new File(savePath);
		if(!dir.exists()) 
			dir.mkdirs();
		Collection<Part> parts = request.getParts();
		String fileName="";
		if(parts.size() > 0) {
			Part part = request.getPart("img");
			System.out.println(part);
			String header = part.getHeader("content-disposition");
			System.out.println("文件名" + header);
			if(header.lastIndexOf(".") < 0) {
				System.out.println("null--------");
				return null;
			}
			fileName = getFileName(header);
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
