package paymentListDTO;


import java.sql.Timestamp;

public class PayListDataBean {
	private int no;
	private int book_no;
	private String name;
	private String email;
	private String pg;
	private String pay_method;
	private int pay_total_price;
	private String imp_uid;
	private Timestamp reg_date;
/*	private BookDataBean book=new BookDataBean();
*/	
/*	public BookDataBean getBook() {
		return book;
	}

	public void setBook(BookDataBean book) {
		this.book = book;
	}*/

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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
/*	public int getTotal_Price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public void setAmount(int total_price) {
		this.total_price = total_price;
	}*/
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/*		public int getPoint() {
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
	public String getImp_uid() {
		return imp_uid;
	}
	public void setImp_uid(String imp_uid) {
		this.imp_uid = imp_uid;
	}



	public int getPay_total_price() {
		return pay_total_price;
	}

	public void setPay_total_price(int pay_total_price) {
		this.pay_total_price = pay_total_price;
	}

	public int getBook_no() {
		return book_no;
	}

	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}	
	
	
}
