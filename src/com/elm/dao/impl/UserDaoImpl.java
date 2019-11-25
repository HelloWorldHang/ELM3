package com.elm.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.elm.bean.User;
import com.elm.dao.UserDao;
import com.elm.util.DBUtil;

public  class UserDaoImpl implements UserDao {

	@Override
	public int insertUser(User user) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into tb_user values(null,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, user.getNickname());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getTel());
		ps.setString(5, user.getAddr());
		ps.setString(6, user.getImg());
		int rs = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return rs;
	}

	@Override
	public int updateUser(User user) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "update tb_user set nickname =?, username=?, password = ?, img = ?, tel = ?,addr = ? where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, user.getNickname());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getImg());
		ps.setString(5, user.getTel());
		ps.setString(6, user.getAddr());
		
		ps.setInt(7, user.getId());
		
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public int deleteUserById(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "delete from tb_user where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, id);
		int res = ps.executeUpdate();
		DBUtil.free(null, ps, conn);
		return res;
	}

	@Override
	public List<User> selectAllUser() throws SQLException {
		List<User> allUser = new ArrayList<>();
		Connection conn = DBUtil.getConnection();
		String sql = "select *from tb_user";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String nickname = rs.getString("nickname");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String img = rs.getString("img");
			String tel = rs.getString("tel");
			String addr = rs.getString("addr");
			
			
			User user = new User(id, nickname, username, password, img, tel, addr);
			allUser.add(user);
		}
		return allUser;
	}

	@Override
	public User selectUserById(Integer id) throws SQLException {
		Connection conn = DBUtil.getConnection();
		String sql = "select *from tb_user where id = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setInt(1, id);
		
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			String nickname = rs.getString("nickname");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String img = rs.getString("img");
			String tel = rs.getString("tel");
			String addr = rs.getString("addr");
			
			return new User(id, nickname, username, password, img,tel, addr);
		}	
		
		DBUtil.free(null, ps, conn);
		
		return null;
	}

	@Override
	public User selectUserByUserameAndPasword(String username, String password) throws SQLException {
		User user = null;
		Connection conn = DBUtil.getConnection();
		String sql = "select *from tb_user where username = ? and password = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);

		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			int id = rs.getInt("id");
			String nickname = rs.getString("nickname");
			String img = rs.getString("img");
			String tel = rs.getString("tel");
			String addr = rs.getString("addr");
			
			user = new User(id, nickname, username, password, img, tel, addr);
		}

		DBUtil.free(null, ps, conn);
		return user;
	}

	@Override
	public List<User> selectByCondition(User user) throws SQLException {
		List<User> res = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		
		StringBuffer sql = new StringBuffer("select * from tb_user where 1=1 ");
		
		List<Object> args = new ArrayList<>();
		
		if (user.getId() != 0 && user.getId()> 0) {
			sql.append("and id = ? ");
			args.add(user.getId());
		}
		if (user.getNickname() != null && user.getNickname().trim().length()>0) {
			sql.append("and nickname like ? ");
			args.add("%" + user.getNickname() + "%");
		}
		if (user.getUsername() != null && user.getUsername().trim().length()>0) {
			sql.append("and username = ? ");
			args.add(user.getUsername());
		}
		if (user.getTel()!=null && user.getTel().trim().length()> 0) {
			sql.append("and tel like ? ");
			args.add("%" + user.getTel() + "%");
		}
		if (user.getAddr() != null && user.getAddr().trim().length() > 0) {
			sql.append("and addr like ? ");
			args.add("%" + user.getAddr() + "%");
		}
		System.out.println(sql);
		
		PreparedStatement ps = connection.prepareStatement(sql.toString());
		for(int i = 0; i < args.size(); i++) {
			ps.setObject(i+1, args.get(i));
		}
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String nickname = rs.getString("nickname");
			String password = rs.getString("password");
			String img = rs.getString("img");
			String tel = rs.getString("tel");
			String addr = rs.getString("addr");
			
			User u = new User(id, nickname, username, password, img, tel, addr);
			res.add(u);
		}
		return res;
	}
	
	


}
