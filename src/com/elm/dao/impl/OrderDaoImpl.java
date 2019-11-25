package com.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elm.bean.Order;
import com.elm.dao.OrderDao;
import com.elm.util.DBUtil;

public class OrderDaoImpl implements OrderDao{

	@Override
	public int insertinto(Order order) throws SQLException {
		Connection conn = DBUtil.getConnection();
		
		String sql = "insert into tb_order values(null,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, order.getFid());
		ps.setInt(2, order.getSid());
		ps.setString(3, order.getNote());
		ps.setString(4, order.getAddr());
		ps.setString(5, order.getUtel());
		ps.setString(6, order.getState());
		ps.setInt(7, order.getAmount());
		
		ps.executeUpdate();
		//下面有简单的方法（SELECT LAST_INSERT_ID();）
		String sql2 = "SELECT LAST_INSERT_ID() STH";
		PreparedStatement ps2 = conn.prepareStatement(sql2);
		ResultSet rs = ps2.executeQuery();
		if(rs.next()) {
			return rs.getInt("sth");
		}
		if(ps != null)
			ps.close();
		DBUtil.free(rs, ps2, conn);
		return 0;
	}

	@Override
	public int delete(int id) throws SQLException {
		
		Connection conn = DBUtil.getConnection();

		String sql = "delete from tb_order where id=? ";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);
		
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public List<Order> selectAll() throws SQLException {

		List<Order> res = new ArrayList<>();
		Connection conn = DBUtil.getConnection();

		String sql = "select * from tb_order order by id desc";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			int id = rs.getInt("id");
			int fid = rs.getInt("fid");
			int sid = rs.getInt("sid");
			String note = rs.getString("note");
			String addr = rs.getString("addr");
			String utel = rs.getString("utel");
			String state = rs.getString("state");
			int amount = rs.getInt("amount");
			Order order= new Order(id, fid, sid, note, addr, utel, state,amount);
			res.add(order);
		}
		DBUtil.free(rs, ps, conn);
		return res;
	}
	//更新销量
	@Override
	public int updateState(int id, String state, int sid) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "update tb_order set state=? ,sid=? where id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, state);
		ps.setInt(2, sid);
		ps.setInt(3, id);
		int res = ps.executeUpdate();
		return res;
	}

	@Override
	public Order findById(int id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tb_order where id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int fid = rs.getInt("fid");
			int sid = rs.getInt("sid");
			int amount = rs.getInt("amount");
			String note = rs.getString("note");
			String addr = rs.getString("addr");
			String utel = rs.getString("utel");
			String state = rs.getString("state");
			return new Order(id, fid, sid, note, addr, utel, state, amount);
		}
		DBUtil.free(rs, ps, conn);
		return null;
	}

}
