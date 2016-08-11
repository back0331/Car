package bean1;

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
			pstmt = conn.prepareStatement("select car_id from book where rent_date<=? and return_date>=?");
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
}
