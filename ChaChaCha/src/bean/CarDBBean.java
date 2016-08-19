package bean;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class CarDBBean {
	
	private static CarDBBean instance = new CarDBBean();
	public static CarDBBean getInstance(){
		return instance;
	}
	private CarDBBean(){}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	public List getCarTypes() throws Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List list = new ArrayList();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select distinct car_type from car");
			while(rs.next()){
				String type = rs.getString(1);
				list.add(type);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
		return list;
	}
	
	public List getCars(String car_type) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CarDataBean> list = new ArrayList<CarDataBean>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from car where car_type=?");
			pstmt.setString(1, car_type);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CarDataBean car = new CarDataBean();
				car.setCar_no(rs.getInt("car_no"));
				car.setCar_name(rs.getString("car_name"));
				car.setCar_type(rs.getString("car_type"));
				car.setCar_brand(rs.getString("car_brand"));
				car.setPrice(rs.getInt("price"));
				list.add(car);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return list;
	}
}
