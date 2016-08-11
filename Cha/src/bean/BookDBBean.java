package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public int insertBook(BookDateBean book) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"insert into book(book_no,id,car_id,rent_date,return_date,total_price,state,state_date,reg_date,carowner) "
				+ "values(seq_book_book_no.nextval,?,?,?,?,?,?,sysdate,sysdate,?)");
			pstmt.setString(1, book.getId());
			pstmt.setString(2, book.getCar_id());
			pstmt.setString(3, book.getRent_date());
			pstmt.setString(4, book.getReturn_date());
			pstmt.setInt(5, book.getTotal_price());
			pstmt.setString(6, book.getState());
			pstmt.setString(7, book.getCarOwner());
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
			pstmt = conn.prepareStatement("update book set state='2' where book_no=?");
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
	
	public List getBookList() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List bookList = new ArrayList();
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"(select book_no,b.id,b.car_id,rent_date,return_date,total_price,b.state,state_date,"
				+ "b.reg_date,l.agency_no,agency_name,l.car_no,car_name,color,options "
				+ "from book b, car_list l, car c, agency a "
				+ "where b.car_id=l.car_id and l.car_no=c.car_no and l.agency_no=a.agency_no)"
				+ "UNION"
				+ "(select book_no,b.id,b.car_id,rent_date,return_date,total_price,b.state,"
				+ "b.state_date,b.reg_date,u.agency_no,a.agency_name,m.car_no,color,options,c.car_name "
				+ "from book b, user_car_list u, mycar_register m, car c, agency a "
				+ "where b.car_id=u.car_id and u.id=m.id and u.mycar_name=m.mycar_name "
				+ "and u.agency_no=a.agency_no and m.car_no=c.car_no)"
				+ "ORDER BY REG_DATE DESC");
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
	
	public List getBookList(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List bookList = new ArrayList();
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"(select book_no,b.id,b.car_id,rent_date,return_date,total_price,b.state,state_date,"
				+ "b.reg_date,l.agency_no,agency_name,l.car_no,car_name,color,options "
				+ "from book b, car_list l, car c, agency a "
				+ "where id=? and b.car_id=l.car_id and l.car_no=c.car_no and l.agency_no=a.agency_no)"
				+ "UNION"
				+ "(select book_no,b.id,b.car_id,rent_date,return_date,total_price,b.state,"
				+ "b.state_date,b.reg_date,u.agency_no,a.agency_name,m.car_no,color,options,c.car_name "
				+ "from book b, user_car_list u, mycar_register m, car c, agency a "
				+ "where b.id=? and b.car_id=u.car_id and u.id=m.id and u.mycar_name=m.mycar_name "
				+ "and u.agency_no=a.agency_no and m.car_no=c.car_no)"
				+ "ORDER BY REG_DATE DESC");
			pstmt.setString(1, id);
			pstmt.setString(2, id);
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
	
}
