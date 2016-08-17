package paymentDTO;
import java.sql.*;
import java.util.*;

import paymentAction.jdbcUtil;
public class PayListDBBean {
	//BoardDBBean bd = BoardDBBean.getInstance()
	private static PayListDBBean instance = new PayListDBBean();
	
	public static PayListDBBean getInstance(){
		return instance;
	}
	
	private PayListDBBean(){
	}
	
	private Connection getConnection() throws Exception{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","scott","ora1234");
	}
	
	public String insertPaymentInfo(PayListDataBean pldb, UserListDataBean uldb, BookDataBean bdb, int book_no2) throws Exception{
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
				jdbcUtil.close(pstmt);
				sql="insert into pay_list values(pay_list_sequence.nextval,?,?,?,?,?,?,0,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, book_no);//number
				pstmt.setString(2, uldb.getName());//varchar2
				pstmt.setString(3, pldb.getPay_method());//varchar2
				pstmt.setInt(4, pldb.getPay_Total_price());
				pstmt.setString(5, uldb.getEmail());//varchar2
				pstmt.setString(6, pldb.getPg());//varchar2
				pstmt.setTimestamp(7, pldb.getReg_date());//date
				int check = pstmt.executeUpdate();
				
				if(check >= 1){
					System.out.println("insert문 성공");
					return "/Payment/IamportTest.jsp?book_no="+book_no;
				}
			}else{
				System.out.println("select문 실패");
				System.out.println(book_no);
			}
		}catch(Exception e){
			System.out.println("예외");
			e.printStackTrace();
			return "/Payment/Fail.jsp";
		}finally{
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
			jdbcUtil.close(rs);
		}
		return "/Payment/Fail.jsp";
	}
	
	public ArrayList GetPaymentInfo(UserListDataBean uldb, BookDataBean bdb) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ArrayList paymentinfolist = new ArrayList();
		ResultSet rs = null;
		try{
			conn = getConnection();
			String sql = "select total_price from book where book_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 1);//원래는 bdb.getBook_no()임
			rs = pstmt.executeQuery();
			if(rs.next()){
				paymentinfolist.add(rs.getInt(1));
				System.out.println("user의 book_no으로 total_price받아옴.");
				sql = "select email, name, phone, address, zipcode, point from user_list where id=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "java1");//원래는 uldb.getId()
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
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
			jdbcUtil.close(rs);
		}
		
		return paymentinfolist;
	}

	public int insertImp_uid(String imp_uid, int book_no) throws Throwable {
		// TODO Auto-generated method stub
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		int check = 0;
		try{
			conn = getConnection();
			sql = "update pay_list set imp_uid=? where book_no=?";
			pstmt.setString(1, imp_uid);
			pstmt.setInt(2, book_no);
			pstmt = conn.prepareStatement(sql);
			check = pstmt.executeUpdate();
			if(check>0){
				return check;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
}
	
