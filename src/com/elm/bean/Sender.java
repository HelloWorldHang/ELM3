package com.elm.bean;

public class Sender {
	private Integer id;
	private String username;
	private String password;
	private String tel;
	private String img;
	private String idcard;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public Sender(Integer id, String username, String password, String tel, String img, String idcard) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.img = img;
		this.idcard = idcard;
	}
	public Sender(String username, String password, String tel, String img, String idcard) {
		super();
		this.username = username;
		this.password = password;
		this.tel = tel;
		this.img = img;
		this.idcard = idcard;
	}
	public Sender(Integer id, String username, String tel, String img, String idcard) {
		super();
		this.id = id;
		this.username = username;
		this.tel = tel;
		this.img = img;
		this.idcard = idcard;
	}
	@Override
	public String toString() {
		return "Sender [id=" + id + ", username=" + username + ", password=" + password + ", tel=" + tel + ", img="
				+ img + ", idcard=" + idcard + "]";
	}
	public Sender() {
		super();
	}
	
	
}
