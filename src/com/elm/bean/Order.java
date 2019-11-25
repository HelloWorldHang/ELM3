package com.elm.bean;

public class Order {
	private Integer id;
	private int fid;
	private int sid;
	private String note;
	private String addr;
	private String utel;
	private String state;
	private int amount;
	
	
	public Order(int fid, int sid, String note, String addr, String utel, String state,int amount) {
		super();
		this.fid = fid;
		this.sid = sid;
		this.note = note;
		this.addr = addr;
		this.utel = utel;
		this.state = state;
		this.amount = amount;
	}
	public Order(Integer id, int fid, int sid, String note, String addr, String utel, String state,int amount) {
		super();
		this.id = id;
		this.fid = fid;
		this.sid = sid;
		this.note = note;
		this.addr = addr;
		this.utel = utel;
		this.state = state;
		this.amount = amount;
	}
	public Order() {
		super();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getUtel() {
		return utel;
	}
	public void setUtel(String utel) {
		this.utel = utel;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", fid=" + fid + ", sid=" + sid + ", note=" + note + ", addr=" + addr + ", utel="
				+ utel + ", state=" + state + ", amount=" + amount + "]";
	}	
	
}
