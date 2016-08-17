package paymentListDTO;
import java.sql.*;
import java.util.*;

import paymentListAction.jdbcUtil;
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
	
	public List getPaymentList(PayListDataBean pldb) throws Throwable{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList payList = new ArrayList();
		
		try{
			conn=getConnection();
			sql = "select rownum,b.id,p.name,p.pay_total_price,p.pay_method,p.pg,p.email,"
				+ "p.book_no,p.reg_date from book b, pay_list p where b.book_no=p.book_no "
				+ "order by b.reg_date desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			do{
				rs.next();
				pldb.setNo(rs.getInt("rownum"));
				pldb.setName(rs.getString("name"));
				pldb.setPay_total_price(rs.getInt("pay_total_price"));
				pldb.setPay_method(rs.getString("pay_method"));
				pldb.setPg(rs.getString("pg"));
				pldb.setEmail(rs.getString("email"));
				pldb.setBook_no(rs.getInt("book_no"));
				pldb.setReg_date(rs.getTimestamp("reg_date"));
				
				payList.add(pldb);
			}while(rs.next());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		
		return payList;
	}
	
	
	public List getIdList(BookDataBean bdb) throws Throwable{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ArrayList idList = new ArrayList();
		
		try{
			conn=getConnection();
			sql = "select rownum,b.id,p.name,p.pay_total_price,p.pay_method,p.pg,p.email,"
				+ "p.book_no from book b, pay_list p where b.book_no=p.book_no "
				+ "order by b.reg_date desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			do{
				rs.next();
				bdb.setId(rs.getString("id"));
				idList.add(bdb);
			}while(rs.next());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		
		return idList;
	}
	public String getImp_uid(int book_no) throws Throwable{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String imp_uid = "";
		String sql = "";
		
		try{
			conn = getConnection();
			sql = "select imp_uid from pay_list where book_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, book_no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				imp_uid = rs.getString("imp_uid");
			}else{
				imp_uid = "결제 정보가 온전치 않습니다. 해당 카드사에 문의 바랍니다.";
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			jdbcUtil.close(pstmt);
			jdbcUtil.close(conn);
		}
		return imp_uid;
	}
	public String deletePayment(int book_no) throws Throwable{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try{
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			jdbcUtil.close(conn);
			
		}
		return null;
	}
}
	
