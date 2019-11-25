package com.elm.bean;

public class CartItem {
		private Integer id;
		private int shop;
		private String img;
		private String name;
		private float price;
		private float psf;
		private Integer amount;
		private float total;
		public CartItem() {
			super();
		}
		public CartItem(int shop, String img, String name, float price, float psf, Integer amount, float total) {
			super();
			this.shop = shop;
			this.img = img;
			this.name = name;
			this.price = price;
			this.psf = psf;
			this.amount = amount;
			this.total = total;
		}
		public CartItem(Integer id, int shop, String img, String name, float price, float psf, Integer amount,
				float total) {
			super();
			this.id = id;
			this.shop = shop;
			this.img = img;
			this.name = name;
			this.price = price;
			this.psf = psf;
			this.amount = amount;
			this.total = total;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public int getShop() {
			return shop;
		}
		public void setShop(int shop) {
			this.shop = shop;
		}
		public String getImg() {
			return img;
		}
		public void setImg(String img) {
			this.img = img;
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
		public float getPsf() {
			return psf;
		}
		public void setPsf(float psf) {
			this.psf = psf;
		}
		public Integer getAmount() {
			return amount;
		}
		public void setAmount(Integer amount) {
			this.amount = amount;
		}
		public float getTotal() {
			return total;
		}
		public void setTotal(float total) {
			this.total = total;
		}
		@Override
		public String toString() {
			return "cartItem [id=" + id + ", shop=" + shop + ", img=" + img + ", name=" + name + ", price=" + price
					+ ", psf=" + psf + ", amount=" + amount + ", total=" + total + "]";
		}
		
}