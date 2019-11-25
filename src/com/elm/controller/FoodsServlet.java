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
import javax.servlet.http.Part;

import com.elm.bean.Foods;
import com.elm.service.FoodsService;
import com.elm.service.impl.FoodsServiceImpl;
@MultipartConfig
@WebServlet(urlPatterns="/servlet/foods")
public class FoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    FoodsService foodsService = new FoodsServiceImpl();
    Foods foods = new Foods();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doPost(req, resp);
    }
    
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String op = req.getParameter("op");
		System.out.println(op);
		switch (op) {
		case "add":
			try {
				add(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "foodsList":
			try {
				foodsList(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "delete":
			try {
				delete(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "findById":
			try {
				findById(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "update":
			try {
				update(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "search":
			try {
				search(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "canTing":
			try {
				canTing(req, resp);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "shunxu":
			System.out.println("shunxv----------------");
			try {
				shunxu(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			System.out.println("Execute FoodsServlet default");
		}
	}

	private void shunxu(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String aStr = req.getParameter("a");
		int a = 0;
		if(aStr != null && aStr.trim().length() > 0 ) {
			a = Integer.parseInt(aStr);
		}
		List<Foods> foodsList = foodsService.selectFoodsByShunxu(a);
		req.setAttribute("foodsList", foodsList);
		System.out.println(foodsList);
		
		System.out.println("shunxv------------");
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
	}

	private void canTing(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String shopStr = req.getParameter("shop");
		int shop = 0;
		if (shopStr != null && shopStr.trim().length()>0) {
			shop = Integer.parseInt(shopStr);
		}
		List<Foods> foodsList = foodsService.selectByShop(shop);
		//请求转发到list页面
		req.setAttribute("aim", shop);
		req.setAttribute("foodsList", foodsList);
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
	}

	private void search(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String idStr = req.getParameter("id");
		Integer id =0;
		if (idStr != null && idStr.trim().length()>0) {
			id = Integer.parseInt(idStr);
		}
		String name = req.getParameter("name");
		String lowStr = req.getParameter("low");
		String highStr = req.getParameter("high");
		String intro = req.getParameter("intro");
		String shopStr = req.getParameter("shop");
		int shop = 0;
		float low = 0;
		float high = 0;
		if (shopStr != null && shopStr.trim().length()>0) {
			shop = Integer.parseInt(shopStr);
		}
		if (lowStr != null & lowStr.trim().length() > 0) {
			low = Float.parseFloat(lowStr);
		}
		if (highStr != null & highStr.trim().length() > 0) {
			high = Float.parseFloat(highStr);
		}
		
		Foods foods = new Foods();
		foods.setId(id);
		foods.setName(name);
		foods.setLow(low);
		foods.setHigh(high);
		foods.setIntro(intro);
		foods.setShop(shop);
		
		List<Foods> foodsList = foodsService.selectByCondition(foods);
		foodsList.forEach(System.out::println);
		req.setAttribute("foodsList", foodsList);
		String returnPage = req.getParameter("returnPage");
		req.getRequestDispatcher(returnPage).forward(req, resp);
	}

	private void update(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		String idStr = req.getParameter("id");
		int id = 0;
		if (idStr != null & idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		String name = req.getParameter("name");
		String priceStr = req.getParameter("price");
		float price = 0;
		if (priceStr != null && priceStr.trim().length() > 0) {
			price = Float.parseFloat(priceStr);
		}
		String intro = req.getParameter("intro");
		int sale = 0;
		String fileName = uploadImg(req);
		String img = "";
		if (fileName == null) {//若用户没有更改图片则用原来的
			img = req.getParameter("yuanLaiDeImg");
		}else {
			img = "upload/foods/" + fileName;
		}
		String evaluateStr = req.getParameter("evaluate");
		float evaluate = 0;
		if (evaluateStr != null && evaluateStr.trim().length()>0) {
			evaluate = Float.parseFloat(evaluateStr);
		}
		String psfStr = req.getParameter("psf");
		float psf = 0;
		if (psfStr != null && psfStr.trim().length() > 0) {
			psf = Float.parseFloat(psfStr);
		}
		String shopStr = req.getParameter("shop");
		int shop = 0;
		if (shopStr != null && shopStr.trim().length()>0) {
			shop =  Integer.parseInt(shopStr);
		}
		Foods foods = new Foods(id, name, price, img, sale, intro, evaluate, psf, shop);
		req.setAttribute("foods", foods);
		int res = foodsService.update(foods);
		if (res == 1) {
			//修改成功，跳转到list页面
			req.setAttribute("aim", "servlet/foods?op=foodsList");
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		}else {
			// 失败到失败页面，2s后跳转到更新页面
			req.setAttribute("aim", "servlet/goods?op=findById&id="+id);
			req.getRequestDispatcher("/failed.jsp").forward(req, resp);
		}
	}

	private void findById(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String idStr = req.getParameter("id");
		int id = 0;
		if (idStr != null & idStr.trim().length() > 0) {
			id = Integer.parseInt(idStr);
		}
		Foods foods = foodsService.findById(id);
		System.out.println(foods);
		if (foods != null) {
			req.setAttribute("foods", foods);//把查找到的信息放到request中
			//请求转发到更新页面，并把原来的信息带上
			req.getRequestDispatcher("/auth/foods/edit.jsp").forward(req, resp);
		}
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		String idStr = req.getParameter("id");
		if (idStr != null && idStr.trim().length() > 0) {
			Integer id = Integer.parseInt(idStr);
			int res = foodsService.deleteById(id);
			if (res == 1) {
				// 删除成功跳转到成功页面，后跳转到list页面
				req.setAttribute("aim", "servlet/foods?op=foodsList");
				req.getRequestDispatcher("/success.jsp").forward(req, resp);
			}else {
				req.setAttribute("aim", "servlet/foods?op=foodsList");
				req.getRequestDispatcher("/failed.jsp").forward(req, resp);
			}
		}
	}

	private void foodsList(HttpServletRequest req, HttpServletResponse resp)
			throws SQLException, ServletException, IOException {
		System.out.println("--------foodList");
		List<Foods> foodsList = foodsService.selectAll();
		req.setAttribute("foodsList", foodsList);
		// 请求转发到list页面展示商品
		req.getRequestDispatcher("/auth/foods/list.jsp").forward(req, resp);
	}

	private void add(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, SQLException {
		String name = req.getParameter("name");
		String priceStr = req.getParameter("price");
		float price = 0;
		if (priceStr != null && priceStr.trim().length()>0) {
			price = Float.parseFloat(priceStr);
		}
		String fileName = uploadImg(req);
		String img = "upload/foods/" + fileName;
		int sale = 0;
		String intro = req.getParameter("intro");
		String psfStr = req.getParameter("psf");
		float psf = 0;
		if (psfStr != null && psfStr.trim().length()>0) {
			psf = Float.parseFloat(psfStr);
		}
		String evaluateStr = req.getParameter("evaluate");
		float evaluate = 0;
		if (evaluateStr != null && evaluateStr.trim().length()>0) {
			evaluate = Float.parseFloat(evaluateStr);
		}
		String shopStr = req.getParameter("shop");
		int shop = 0;
		if (shopStr != null && shopStr.trim().length()>0) {
			shop =  Integer.parseInt(shopStr);
		}
		Foods foods = new Foods(null,name,price,img,sale,intro,evaluate,psf,shop);
		int res = foodsService.insert(foods);
		if (res == 1) {
			// 添加成功后跳转到成功页面，后请求服务器到list页面
			req.setAttribute("aim", "servlet/foods?op=foodsList");
			req.getRequestDispatcher("/success.jsp").forward(req, resp);
		}else {
			// 失败到失败页面，2s后跳转到添加页面
			req.setAttribute("aim", "auth/foods/add.jsp");
			req.getRequestDispatcher("/failed.jsp").forward(req, resp);
		}
	}

	
	private String uploadImg(HttpServletRequest req) throws IOException, ServletException {
		String savePath = req.getServletContext().getRealPath("/");
		savePath = savePath + "upload\\foods";
		System.out.println(savePath);

		File dir = new File(savePath);
		if (!dir.exists())
			dir.mkdirs();

		// 获取上传的文件集合
		Collection<Part> parts = req.getParts();
		// 上传单个文件
		String fileName = "";
		if (parts.size() > 0) {
			Part part = req.getPart("img");
			String header = part.getHeader("content-disposition");
			System.out.println(header);
			if (header.lastIndexOf('.') < 0) {
				return null;
			}
			// 获取文件名
			fileName = getFileName(header);
			// 把文件写到指定路径
			part.write(savePath + File.separator + fileName);
		}
		System.out.println("ok");
		return fileName;
	}

	private String getFileName(String header) {//获取文件的格式并给文件一个随机名
		String suffix = header.substring(header.lastIndexOf('.'), header.length() - 1);
		System.out.println(suffix);

		return UUID.randomUUID().toString() + suffix;
	}


}
