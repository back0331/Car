package bean;
import java.sql.*;
import java.util.*;

import jdbc.JdbcUtil;
public class PayListDBBean {
	//BoardDBBean bd = BoardDBBean.getInstance()
	private static PayListDBBean instance = new PayListDBBean();
	
	public static PayListDBBean getInstance(){
		return instance;
	}
	
	private PayListDBBean(){
	}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	public String insertPaymentInfo(PayListDataBean pdb, UserListDataBean uldb, BookDataBean bdb) throws Exception{
		System.out.println("insertPaymentInfo()실행");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int book_no = 0;
		try{
			conn = getConnection();
			String sql = "select book_no from book where id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "java");//varchar2
			rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("select문 성공");
				book_no = rs.getInt(1);
				JdbcUtil.close(pstmt);
				sql="insert into pay_list values(pay_list_sequence.nextval,?,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, book_no);//number
				pstmt.setString(2, uldb.getName());//varchar2
				pstmt.setString(3, pdb.getPay_method());//varchar2
				pstmt.setInt(4, pdb.getPay_Total_price());
				pstmt.setString(5, uldb.getEmail());//varchar2
				pstmt.setString(6, pdb.getPg());//varchar2
				pstmt.setTimestamp(7, pdb.getReg_date());//date
				int check = pstmt.executeUpdate();
				if(check >= 1){
					System.out.println("insert문 성공");
					return "/payment/IamportTest.jsp";
				}
			}else{
				System.out.println("select문 실패");
				System.out.println(book_no);
			}
		}catch(Exception e){
			System.out.println("예외");
			e.printStackTrace();
			return "/payment/Fail.jsp";
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
		}
		return "/payment/Fail.jsp";
	}
	
	public ArrayList GetPaymentInfo(BookDataBean bdb) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList paymentinfolist = new ArrayList();
		ResultSet rs = null;
		try{
			conn = getConnection();
			String sql = "select total_price from book where book_no=?";
			pstmt = conn.prepareStatement(sql);
			int book_no = bdb.getBook_no();
			pstmt.setInt(1, book_no);//원래는 bdb.getBook_no()임
			rs = pstmt.executeQuery();
			if(rs.next()){
				paymentinfolist.add(rs.getInt(1));
				System.out.println("user의 book_no으로 total_price받아옴.");
				sql = "select email, name, phone, address, zipcode, point from user_list where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bdb.getId());
				rs = pstmt.executeQuery();
				if(rs.next()){
					System.out.println("book의 id로 user정보받아옴.");
					for(int i=1; i<6; i++){
						paymentinfolist.add(rs.getString(i));
					}
					paymentinfolist.add(rs.getInt(6));					
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
		}
		
		return paymentinfolist;
	}
	
}
	
