package bean;

import java.sql.Timestamp;

public class ReviewBoardDataBean {
	private int article_no;
	private int book_no;
	private String id;
	private String article_subject;
	private String article_content;
	private String password;
	private Timestamp reg_date;
	
	public int getArticle_no() {
		return article_no;
	}
	public void setArticle_no(int article_no) {
		this.article_no = article_no;
	}
	public int getBook_no() {
		return book_no;
	}
	public void setBook_no(int book_no) {
		this.book_no = book_no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getArticle_subject() {
		return article_subject;
	}
	public void setArticle_subject(String article_subject) {
		this.article_subject = article_subject;
	}
	public String getArticle_content() {
		return article_content;
	}
	public void setArticle_content(String article_content) {
		this.article_content = article_content;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
}