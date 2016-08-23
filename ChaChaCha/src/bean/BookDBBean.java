package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

import jdbc.JdbcUtil;
public class BookDBBean {

	private static BookDBBean instance = new BookDBBean();
	public static BookDBBean getInstance(){
		return instance;
	}
	private BookDBBean(){}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	public List<String> getUnavailableCarList(String rent_date, String return_date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> car_idList = new ArrayList<String>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select car_id from book where rent_date<=? and return_date>=? and (state='0' or state='1')");
			pstmt.setString(1, return_date);
			pstmt.setString(2, rent_date);
			rs = pstmt.executeQuery();
			while(rs.next()){
				car_idList.add(rs.getString(1));
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return car_idList;
	}
	
	public int insertBook(BookDataBean book) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"insert into book(book_no,id,car_id,carowner,rent_date,return_date,total_price,state,state_date,reg_date,review) "
				+ "values(seq_book_book_no.nextval,?,?,?,?,?,?,?,sysdate,sysdate,'no')");
			pstmt.setString(1, book.getId());
			pstmt.setString(2, book.getCar_id());
			pstmt.setString(3, book.getCarOwner());
			pstmt.setString(4, book.getRent_date());
			pstmt.setString(5, book.getReturn_date());
			pstmt.setInt(6, book.getTotal_price());
			pstmt.setString(7, book.getState());
			result = pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	//예약취소
	public int deleteBook(int book_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update book set state='2',state_date=sysdate where book_no=?");
			pstmt.setInt(1, book_no);
			result = pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	public List getBookList(int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List bookList = new ArrayList();
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from "
							+ "(select rownum r, book_no,id,car_id,TO_CHAR(rent_date,'YYYY-MM-DD') rent_date,"
							+ "TO_CHAR(return_date,'YYYY-MM-DD') return_date,total_price,state,state_date,"
							+ "reg_date,agency_no,agency_name,car_no,color,options,car_name,review  from("
							+ "(select book_no,b.id,b.car_id, rent_date,return_date,total_price,b.state,state_date,"
							+ "b.reg_date,l.agency_no,agency_name,l.car_no,color,options,car_name,review  "
							+ "from book b, car_list l, car c, agency a "
							+ "where b.car_id=l.car_id and l.car_no=c.car_no and l.agency_no=a.agency_no) "
							+ "UNION "
							+ "(select book_no,b.id,b.car_id, rent_date,return_date,total_price,b.state,"
							+ "b.state_date,b.reg_date,u.agency_no,a.agency_name,m.car_no,color,options,c.car_name,review  "
							+ "from book b, user_car_list u, mycar_register m, car c, agency a "
							+ "where b.car_id=u.car_id and u.id=m.id and u.mycar_name=m.mycar_name "
							+ "and u.agency_no=a.agency_no and m.car_no=c.car_no) "
							+ "UNION "
							+ "(select book_no,b.id,b.car_id, rent_date,return_date,total_price,b.state,"
							+ "b.state_date,b.reg_date,e.agency_no,a.agency_name,m.car_no,color,options,c.car_name,review  "
							+ "from book b, expired_user_car_list e, mycar_register m, car c, agency a "
							+ "where b.id='test' and b.car_id=e.car_id and e.id=m.id and e.mycar_name=m.mycar_name "
							+ "and e.agency_no=a.agency_no and m.car_no=c.car_no)"
							+ ") ORDER BY REG_DATE desc ) where r<=? and r>=?");
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BookListDataBean book = new BookListDataBean();
				book.setBook_no(rs.getInt("book_no"));
				book.setId(rs.getString("id"));
				book.setCar_id(rs.getString("car_id"));
				book.setRent_date(rs.getString("rent_date"));
				book.setReturn_date(rs.getString("return_date"));
				book.setTotal_price(rs.getInt("total_price"));
				book.setState(rs.getString("state"));
				book.setReg_date(rs.getString("reg_date"));
				book.setCar_no(rs.getInt("car_no"));
				book.setAgency_no(rs.getInt("agency_no"));
				book.setAgency_name(rs.getString("agency_name"));
				book.setCar_name(rs.getString("car_name"));
				book.setColor(rs.getString("color"));
				book.setOptions(rs.getString("options"));
				book.setReview(rs.getString("review"));
				
				bookList.add(book);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return bookList;
	}
	
	public List getBookList(String id, int start, int end) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List bookList = new ArrayList();
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"select * from "
				+ "(select rownum r, book_no,id,car_id,TO_CHAR(rent_date,'YYYY-MM-DD') rent_date,"
				+ "TO_CHAR(return_date,'YYYY-MM-DD') return_date,total_price,state,state_date,"
				+ "reg_date,agency_no,agency_name,car_no,color,options,car_name,review from("
				+ "(select book_no,b.id,b.car_id, rent_date,return_date,total_price,b.state,state_date,"
				+ "b.reg_date,l.agency_no,agency_name,l.car_no,color,options,car_name,review  "
				+ "from book b, car_list l, car c, agency a "
				+ "where id=? and b.car_id=l.car_id and l.car_no=c.car_no and l.agency_no=a.agency_no) "
				+ "UNION "
				+ "(select book_no,b.id,b.car_id, rent_date,return_date,total_price,b.state,"
				+ "b.state_date,b.reg_date,u.agency_no,a.agency_name,m.car_no,color,options,c.car_name,review  "
				+ "from book b, user_car_list u, mycar_register m, car c, agency a "
				+ "where b.id=? and b.car_id=u.car_id and u.id=m.id and u.mycar_name=m.mycar_name "
				+ "and u.agency_no=a.agency_no and m.car_no=c.car_no) "
				+ "UNION "
				+ "(select book_no,b.id,b.car_id, rent_date,return_date,total_price,b.state,"
				+ "b.state_date,b.reg_date,e.agency_no,a.agency_name,m.car_no,color,options,c.car_name,review  "
				+ "from book b, expired_user_car_list e, mycar_register m, car c, agency a "
				+ "where b.id=? and b.car_id=e.car_id and e.id=m.id and e.mycar_name=m.mycar_name "
				+ "and e.agency_no=a.agency_no and m.car_no=c.car_no)"
				+ ") ORDER BY REG_DATE desc ) where r<=? and r>=?");
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.setString(3, id);
			pstmt.setInt(4, start);
			pstmt.setInt(5, end);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BookListDataBean book = new BookListDataBean();
				book.setBook_no(rs.getInt("book_no"));
				book.setId(rs.getString("id"));
				book.setCar_id(rs.getString("car_id"));
				book.setRent_date(rs.getString("rent_date"));
				book.setReturn_date(rs.getString("return_date"));
				book.setTotal_price(rs.getInt("total_price"));
				book.setState(rs.getString("state"));
				book.setReg_date(rs.getString("reg_date"));
				book.setCar_no(rs.getInt("car_no"));
				book.setAgency_no(rs.getInt("agency_no"));
				book.setAgency_name(rs.getString("agency_name"));
				book.setCar_name(rs.getString("car_name"));
				book.setColor(rs.getString("color"));
				book.setOptions(rs.getString("options"));
				book.setState_date(rs.getString("state_date"));
				book.setReview(rs.getString("review"));
				bookList.add(book);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return bookList;
	}
	
	public BookDataBean getBook(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BookDataBean book = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from book where id=? order by reg_date desc");
			rs = pstmt.executeQuery();
			if(rs.next()){
				book = new BookDataBean();
				book.setBook_no(rs.getInt("book_no"));
				book.setId(rs.getString("id"));
				book.setCar_id(rs.getString("car_id"));
				book.setRent_date(rs.getString("rent_date"));
				book.setReturn_date(rs.getString("return_date"));
				book.setTotal_price(rs.getInt("total_price"));
				book.setState(rs.getString("state"));
				book.setState_date(rs.getString("state_date"));
				book.setReg_date(rs.getString("reg_date"));
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		
		return book;
	}
	
	public int getTotalBookCount(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select count(*) from book where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			total = rs.getInt(1);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return total;
	}
	public int getTotalBookCount() throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		int total = 0;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(*) from book");
			rs.next();
			total = rs.getInt(1);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
		return total;
	}
	
	//고객차 예약리스트 목록을 가져오기 위한 
	public List getUserCarBookList(String car_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List userCarBookList = new ArrayList();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select TO_CHAR(reg_date,'YYYY-MM-DD') reg_date,book_no,id,"
					+ "TO_CHAR(rent_date,'YYYY-MM-DD') rent_date,TO_CHAR(return_date,'YYYY-MM-DD') return_date,"
					+ " state from book where car_id=? order by reg_date desc");
			pstmt.setString(1, car_id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BookDataBean book = new BookDataBean();
				book.setReg_date(rs.getString("reg_date"));
				book.setBook_no(rs.getInt("book_no"));
				book.setId(rs.getString("id"));
				book.setRent_date(rs.getString("rent_date"));
				book.setReturn_date(rs.getString("return_date"));
				book.setState(rs.getString("state"));
				
				userCarBookList.add(book);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return userCarBookList;
	}
	
	//해당 아이디로 오늘날짜 기준 예약내역이 존재하는지 체크
	public int checkBookList(String id, String today) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"select * from book where id=? and return_date>=? and (state='0' or state='1')");
			pstmt.setString(1, id);
			pstmt.setString(2, today);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = 1;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	public void updateBookReview(int book_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("update book set review='yes' where book_no=?");
			pstmt.setInt(1, book_no);
			pstmt.executeQuery();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
}
