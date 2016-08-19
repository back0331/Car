package bean;
import java.sql.*;
import java.util.*;
import jdbc.JdbcUtil;
import bean.BookDataBean;
import bean.PayListDataBean;
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
				JdbcUtil.close(pstmt);
				sql="insert into pay_list values(pay_list_sequence.nextval,?,?,?,?,?,?,0,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, book_no);//number
				pstmt.setString(2, uldb.getName());//varchar2
				pstmt.setString(3, pldb.getPay_method());//varchar2
				pstmt.setInt(4, pldb.getPay_total_price());
				pstmt.setString(5, uldb.getEmail());//varchar2
				pstmt.setString(6, pldb.getPg());//varchar2
				pstmt.setTimestamp(7, pldb.getReg_date());//date
				int check = pstmt.executeUpdate();
				
				if(check >= 1){
					System.out.println("insert문 성공");
					return "/payment/IamportTest.jsp?book_no="+book_no;
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
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
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
			pstmt.setInt(1, 1);
			rs = pstmt.executeQuery();
			if(rs.next()){
				paymentinfolist.add(rs.getInt(1));//book테이블의 결제금액 받아옴.
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
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
			JdbcUtil.close(rs);
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
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, imp_uid);
			pstmt.setInt(2, book_no);
			check = pstmt.executeUpdate();
			if(check>0){
				return check;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
		//check값이 0이면 Fail.jsp로 감.
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
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
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
			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
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
			
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
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
			JdbcUtil.close(conn);
			
		}
		return null;
	}
}
	
