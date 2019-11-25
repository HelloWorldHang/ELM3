package com.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elm.bean.Sender;
import com.elm.dao.SenderDao;
import com.elm.util.DBUtil;
public class SenderDaoImpl implements SenderDao{

	@Override
	public int insertSender(Sender sender) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into tb_sender (id,username,password,tel,img,idcard) values(null,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, sender.getUsername());
		ps.setString(2, sender.getPassword());
		ps.setString(3, sender.getTel());
		ps.setString(4, sender.getImg());
		ps.setString(5, sender.getIdcard());
		
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public int updateSender(Sender Sender) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "update tb_sender set username=?,password=?,tel=?,img=?,idcard=? where id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, Sender.getUsername());
		ps.setString(2, Sender.getPassword());
		ps.setString(3, Sender.getTel());
		ps.setString(4, Sender.getImg());
		ps.setString(5, Sender.getIdcard());
		ps.setInt(6, Sender.getId());
		
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public int deleteSenderById(Integer id) throws SQLException {
		
		Connection conn = DBUtil.getConnection();
		String sql = "delete from tb_sender where id= ?";
		PreparedStatement ps = conn.prepareStatement(sql);

		ps.setInt(1, id);
		
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public List<Sender> selectAllSender() throws SQLException {
		List<Sender> res = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tb_sender";
		PreparedStatement ps = conn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Integer id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String tel = rs.getString("tel");
			String img = rs.getString("img");
			String idcard = rs.getString("idcard");
			Sender Sender = new Sender(id, username, password, tel, img, idcard);
			res.add(Sender);
		}

		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public Sender selectSenderById(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tb_sender where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			String username = rs.getString("username");
			String password = rs.getString("password");
			String tel = rs.getString("tel");
			String img = rs.getString("img");
			String idcard = rs.getString("idcard");
			return new Sender(id, username, password, tel, img, idcard);
		}

		DBUtil.free(null, ps, conn);
		return null;
	}

	

	@Override
	public List<Sender> selectByCondition(Sender sender) throws SQLException {
		
		List<Sender> list = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		StringBuffer sql =new StringBuffer( "select * from tb_sender where 1=1 ");
		List<Object> args = new ArrayList<>();
		//动态SQL
		if (sender.getUsername() != null && sender.getUsername().trim().length() > 0) {
			sql.append("and username like ?");
			args.add("%" + sender.getUsername() + "%");
		}
		if (sender.getId()!=null && sender.getId() >0) {
			sql.append(" and id=?");
			args.add(sender.getId());
		}
		if (sender.getTel()!=null && sender.getTel().trim().length() > 0) {
			sql.append(" and tel like ?");
			args.add("%" + sender.getTel() + "%");
		}
		System.out.println(sql);
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		for(int i = 0; i < args.size(); i++) {
			ps.setObject(i+1, args.get(i));
		}
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String tel = rs.getString("tel");
			String img = rs.getString("img");
			String idcard = rs.getString("idcard");
			Sender u = new Sender(id,username,tel,img,idcard);
			list.add(u);
		}
		return list;
	}

	@Override
	public Sender selectSenderByUsernameAndPassword(String username, String password) throws SQLException {
		Sender sender = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select * from tb_sender where username=? and password=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			Integer id = rs.getInt("id");
			String tel = rs.getString("tel");
			String img = rs.getString("img");
			String idcard = rs.getString("idcard");
			sender = new Sender(id, username, password, tel, img, idcard);
		}
		
		DBUtil.free(null, ps, conn);
		return sender;
	}

	

}
