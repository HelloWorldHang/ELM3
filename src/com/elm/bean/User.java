package com.elm.bean;

public class User {
	private Integer id;
	private String nickname;
	private String username;
	private String password;
	private String img;
	private String tel;
	private String addr;
	public User() {
		super();
	}
	public User(String nickname, String username, String password, String img, String tel, String addr) {
		super();
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.img = img;
		this.tel = tel;
		this.addr = addr;
	}
	public User(Integer id, String nickname, String username, String password, String img, String tel, String addr) {
		super();
		this.id = id;
		this.nickname = nickname;
		this.username = username;
		this.password = password;
		this.img = img;
		this.tel = tel;
		this.addr = addr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "user [id=" + id + ", nickname=" + nickname + ", username=" + username + ", password=" + password
				+ ", img=" + img + ", tel=" + tel + ", addr=" + addr + "]";
	}
	
	

}
