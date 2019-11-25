package com.elm.bean;

public class DingDan {
	private int id;
	private String foodsName;
	private int amount;
	private String foodsAddr;
	private String userAddr;
	private String userTel;
	private String note;
	private float psf;
	private String state;
	public DingDan(int id, String foodsName, int amount, String foodsAddr, String userAddr, String userTel, String note, float psf,
			String state) {
		super();
		this.id = id;
		this.foodsName = foodsName;
		this.amount = amount;
		this.foodsAddr = foodsAddr;
		this.userAddr = userAddr;
		this.userTel = userTel;
		this.note = note;
		this.psf = psf;
		this.state = state;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFoodsName() {
		return foodsName;
	}
	public void setFoodsName(String foodsName) {
		this.foodsName = foodsName;
	}
	public String getFoodsAddr() {
		return foodsAddr;
	}
	public void setFoodsAddr(String foodsAddr) {
		this.foodsAddr = foodsAddr;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public float getPsf() {
		return psf;
	}
	public void setPsf(float psf) {
		this.psf = psf;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "DingDan [id=" + id + ", foodsName=" + foodsName + ", amount=" + amount + ", foodsAddr=" + foodsAddr
				+ ", userAddr=" + userAddr + ", userTel=" + userTel + ", note=" + note + ", psf=" + psf + ", state="
				+ state + "]";
	}
	
}
