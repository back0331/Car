package paymentDTO;


import java.sql.Timestamp;

public class PayListDataBean {
	private String pg;
	private String pay_method;
	private String merchant_uid;
	private int pay_total_price;//
/*	private String name;//
	
	private String email;//
	private int point;//
	private String phone;//
	private String address;//
	private String zipcode;//
	private int point_using;
	private int total_price2;*/
	private Timestamp reg_date;
	
	private static PayListDataBean instance = new PayListDataBean();
	
	public static PayListDataBean getInstance(){
		return instance;
	}
	
	public String getPg() {
		return pg;
	}
	public void setPg(String pg) {
		this.pg = pg;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public String getMerchant_uid() {
		return merchant_uid;
	}
	public void setMerchant_uid(String merchant_uid) {
		this.merchant_uid = merchant_uid;
	}
/*	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTotal_Price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public void setAmount(int total_price) {
		this.total_price = total_price;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public int getPoint_using() {
	
	
	
		return point_using;
	}
	public void setPoint_using(int point_using) {
		this.point_using = point_using;
	}*/
/*	public int getTotal_price2() {
		return total_price2;
	}
	public void setTotal_price2(int total_price2) {
		this.total_price2 = total_price2;
	}*/
/*	public int getTotal_price() {
		return total_price;
	}*/
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public int getPay_Total_price() {
		return pay_total_price;
	}

	public void setPay_Total_price(int total_price) {
		this.pay_total_price = total_price;
	}	
	
	
}
