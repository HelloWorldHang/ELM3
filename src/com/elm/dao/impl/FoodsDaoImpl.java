package com.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elm.bean.Foods;
import com.elm.dao.FoodsDao;
import com.elm.util.DBUtil;

public class FoodsDaoImpl implements FoodsDao {

	@Override
	public int insertFoods(Foods foods) throws SQLException {
		Connection conn = DBUtil.getConnection();
		
		String sql = "insert into tb_foods values(null,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, foods.getName());
		ps.setFloat(2, foods.getPrice());
		ps.setString(3, foods.getImg());
		ps.setInt(4, foods.getSale());
		ps.setString(5, foods.getIntro());
		ps.setFloat(6, foods.getEvaluate());
		ps.setFloat(7, foods.getPsf());
		ps.setInt(8, foods.getShop());
		
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;

	}

	@Override
	public int updateFoods(Foods foods) throws SQLException {
		Connection conn = DBUtil.getConnection();

		String sql = "update tb_foods set name=?,price=?,img=?,sale=?,intro=?,evaluate=?,psf=?,shop=? where id=? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setString(1, foods.getName());
		ps.setFloat(2, foods.getPrice());
		ps.setString(3, foods.getImg());
		ps.setInt(4, foods.getSale());
		ps.setString(5, foods.getIntro());
		ps.setFloat(6, foods.getEvaluate());
		ps.setInt(9, foods.getId());
		ps.setFloat(7, foods.getPsf());
		ps.setInt(8, foods.getShop());
		
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public int deleteFoodsById(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();

		String sql = "delete from tb_foods where id=? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);
		
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public Foods selectFoodsById(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();

		String sql = "select * from tb_foods  where id=? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			String img = rs.getString("img");
			int sale = rs.getInt("sale");
			String intro = rs.getString("intro");
			float evaluate = rs.getFloat("evaluate");
			float psf = rs.getFloat("psf");
			int shop = rs.getInt("shop");
			return new Foods(id, name, price, img, sale, intro, evaluate, psf, shop);
		}
		DBUtil.free(rs, ps, conn);
		return null;
	}

	@Override
	public List<Foods> selectAllFoods() throws SQLException {
		List<Foods> res = new ArrayList<>();
		Connection conn = DBUtil.getConnection();

		String sql = "select * from tb_foods order by id desc";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			String img = rs.getString("img");
			int sale= rs.getInt("sale");
			String intro = rs.getString("intro");
			float evaluate = rs.getFloat("evaluate");
			float psf = rs.getFloat("psf");
			int shop = rs.getInt("shop");
			Foods foods= new Foods(id, name, price, img, sale, intro, evaluate, psf, shop);
			res.add(foods);
		}
		DBUtil.free(rs, ps, conn);
		return res;
	}

	@Override
	public List<Foods> selectByCondition(Foods foods) throws SQLException {
		List<Foods> res = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		
		StringBuffer sql = new StringBuffer("select * from tb_foods where 1=1 ");
		
		List<Object> args = new ArrayList<>();
		
		if (foods.getShop() > 0 && foods.getShop() <= 200) {
			sql.append("and shop > ? and shop <= ? ");
			int shop = foods.getShop();
			if (shop >0 && shop <= 100) {
				args.add(0);
				args.add(100);
			}
			if (shop >100 && shop <= 150) {
				args.add(100);
				args.add(150);
			}
			if (shop >150 && shop <= 200) {
				args.add(150);
				args.add(200);
			}
		}
		if (foods.getId() != null && foods.getId()> 0) {
			sql.append("and id = ? ");
			args.add(foods.getId());
		}
		if (foods.getName() != null && foods.getName().trim().length()>0) {
			sql.append("and name like ? ");
			args.add("%" + foods.getName() + "%");
		}
		if (foods.getLow() != null && foods.getLow()>0) {
			sql.append("and price >= ? ");
			args.add(foods.getLow());
		}
		if (foods.getHigh() != null && foods.getHigh()>0) {
			sql.append("and price <= ? ");
			args.add(foods.getHigh());
		}
		if (foods.getIntro() != null && foods.getIntro().trim().length()>0) {
			sql.append("and intro like ? ");
			args.add("%" + foods.getIntro() + "%");
		}
		System.out.println(sql);
		
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		for(int i = 0; i < args.size(); i++) {
			ps.setObject(i+1, args.get(i));
		}
		System.out.println(ps);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
		    float price = rs.getFloat("price");
			String img = rs.getString("img");
			int sale = rs.getInt("sale");
			String intro = rs.getString("intro");
			float evaluate = rs.getFloat("evaluate");
			float psf = rs.getFloat("psf");
			int shop = rs.getInt("shop");
			
			Foods f = new Foods(id, name, price, img,sale,intro,evaluate,psf,shop);
			res.add(f);
		}
		return res;
	}

	@Override
	public List<Foods> selectByShop(int shop) throws SQLException {
		Connection conn = DBUtil.getConnection();
		List<Foods> foodsList = new ArrayList<>();
		
		String sql = "select * from tb_foods  where shop>? and shop<=? ";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		if (shop >0 && shop <= 100) {
			ps.setInt(1, 0);
			ps.setInt(2, 100);
		}
		if (shop >100 && shop <= 150) {
			ps.setInt(1, 100);
			ps.setInt(2, 150);
		}
		if (shop >150 && shop <= 200) {
			ps.setInt(1, 150);
			ps.setInt(2, 200);
		}
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			String img = rs.getString("img");
			int sale = rs.getInt("sale");
			String intro = rs.getString("intro");
			float evaluate = rs.getFloat("evaluate");
			float psf = rs.getFloat("psf");
			Foods foods =  new Foods(id, name, price, img, sale, intro, evaluate, psf, shop);
			foodsList.add(foods);
		}
		DBUtil.free(rs, ps, conn);
		return foodsList;
	}

	@Override
	public List<Foods> selectFoodsByShunxu(int a) throws SQLException {
		Connection conn = DBUtil.getConnection();
		List<Foods> foodsList = new ArrayList<>();
		StringBuffer sql = new StringBuffer("select * from tb_foods where 1=1 ");
		if(a == 1) {
			sql.append("order by evaluate DESC ");//通过好评度降序排列
		}
		if(a == 4) {
			sql.append("order by evaluate ");//通过好评度降序排列
		}
		if(a == 2) {
			sql.append("order by price DESC");//通过价格降序排列
		}
		if(a == 5) {
			sql.append("order by price ");//通过价格降序排列
		}
		if(a == 3) {
			sql.append("order by sale DESC");//通过销量降序排列
		}
		if(a == 6) {
			sql.append("order by sale ");//通过销量降序排列
		}
		System.out.println(sql);
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			float price = rs.getFloat("price");
			String img = rs.getString("img");
			int sale = rs.getInt("sale");
			String intro = rs.getString("intro");
			float evaluate = rs.getFloat("evaluate");
			float psf = rs.getFloat("psf");
			int shop = rs.getInt("shop");
			Foods foods =  new Foods(id, name, price, img, sale, intro, evaluate, psf, shop);
			foodsList.add(foods);
		}
		DBUtil.free(rs, ps, conn);
		return foodsList;
	}

	

}
