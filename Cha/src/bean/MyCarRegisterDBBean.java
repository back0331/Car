package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class MyCarRegisterDBBean {
	private static MyCarRegisterDBBean instance = new MyCarRegisterDBBean();
	public static MyCarRegisterDBBean getInstance(){
		return instance;
	}
	private MyCarRegisterDBBean(){}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	/*public List getMyCarRegisterList(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<MyCarRegisterDataBean> list = new ArrayList<MyCarRegisterDataBean>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from mycar_register where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				MyCarRegisterDataBean myCar = new MyCarRegisterDataBean();
				myCar.setId(rs.getString("id"));
				myCar.setMycar_name(rs.getString("mycar_name"));
				myCar.setCar_no(rs.getInt("car_no"));
				myCar.setCarNumber(rs.getString("carnumber"));
				myCar.setColor(rs.getString("color"));
				myCar.setOptions(rs.getString("options"));			
				list.add(myCar);		
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return list;
	}*/
	
	public List getMyCarNames(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> names = new ArrayList<String>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select mycar_name from mycar_register where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				names.add(rs.getString(1));	
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return names;
	}
	
	public MyCarRegisterDataBean getMycarByName(String id, String mycar_name) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MyCarRegisterDataBean myCar = new MyCarRegisterDataBean();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from mycar_register where id=? and mycar_name=?");
			pstmt.setString(1, id);
			pstmt.setString(2, mycar_name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				myCar.setId(rs.getString("id"));
				myCar.setMycar_name(rs.getString("mycar_name"));
				myCar.setCar_no(rs.getInt("car_no"));
				myCar.setCarNumber(rs.getString("carnumber"));
				myCar.setColor(rs.getString("color"));
				myCar.setOptions(rs.getString("options"));

			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return myCar;
	}
	
	public int checkMycarByName(String id, String mycar_name) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int check = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from mycar_register where id=? and mycar_name=?");
			pstmt.setString(1, id);
			pstmt.setString(2, mycar_name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				check = 1;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return check;
	}
	
	public int insertMyCarRegister(MyCarRegisterDataBean myCar) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try{
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into mycar_register values(?,?,?,?,?,?)");
			pstmt.setString(1, myCar.getId());
			pstmt.setString(2, myCar.getMycar_name());
			pstmt.setInt(3, myCar.getCar_no());
			pstmt.setString(4, myCar.getCarNumber());
			pstmt.setString(5, myCar.getColor());
			pstmt.setString(6, myCar.getOptions());
			result = pstmt.executeUpdate();
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
}
