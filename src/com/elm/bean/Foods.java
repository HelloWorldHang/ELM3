package com.elm.bean;

public class Foods {
	private Integer id;
	private String name;
	private float price;
	private String img;
	private int sale;
	private String intro;
	private float evaluate;
	private float psf;
	private int shop;
	
	public Foods() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Foods(String name, float price, String img, int sale, String intro, float evaluate, float psf, int shop) {
		super();
		this.name = name;
		this.price = price;
		this.img = img;
		this.sale = sale;
		this.intro = intro;
		this.evaluate = evaluate;
		this.psf = psf;
		this.shop = shop;
	}
	public Foods(Integer id, String name, float price, String img, int sale, String intro, float evaluate, float psf, int shop) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.img = img;
		this.sale = sale;
		this.intro = intro;
		this.evaluate = evaluate;
		this.psf = psf;
		this.shop = shop;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getSale() {
		return sale;
	}
	public void setSale(int sale) {
		this.sale = sale;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public float getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(float evaluate) {
		this.evaluate = evaluate;
	}
	
	public float getPsf() {
		return psf;
	}
	public void setPsf(float psf) {
		this.psf = psf;
	}
	

	public int getShop() {
		return shop;
	}
	public void setShop(int shop) {
		this.shop = shop;
	}


	//////////////////////////////
	private Float low;
	private Float high;

	public Float getLow() {
		return low;
	}
	public void setLow(Float low) {
		this.low = low;
	}
	public Float getHigh() {
		return high;
	}
	public void setHigh(Float high) {
		this.high = high;
	}
	@Override
	public String toString() {
		return "Foods [id=" + id + ", name=" + name + ", price=" + price + ", img=" + img + ", sale=" + sale
				+ ", intro=" + intro + ", evaluate=" + evaluate + ", psf=" + psf + ", shop=" + shop + "]";
	}
	
	
	
}
