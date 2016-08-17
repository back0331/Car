package bean1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class UserCarListDBBean {
	private static UserCarListDBBean instance = new UserCarListDBBean();
	public static UserCarListDBBean getInstance(){
		return instance;
	}
	private UserCarListDBBean(){}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	public void setState() throws Exception{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			int count = stmt.executeUpdate("update user_car_list set state='0'");
			System.out.println("set0:::" + count);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
	}
	
	public void setStateBook(List bookUnavailableCarList) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String car_id;
		
		try {
			conn = getConnection();
			String sql = "update user_car_list set state='1' where car_id=?";
			for(int i = 0; i < bookUnavailableCarList.size(); i++){
				car_id=(String)bookUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				int count = pstmt.executeUpdate();
				System.out.println("set1:::" + count);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public List getAvailableUserCarList(int agency_no, String rent_date, String return_date) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int car_no;
		List<CarListJoinCarDataBean> availableUserCarList = new ArrayList<CarListJoinCarDataBean>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"select distinct no from"
				+ "(select u.car_id,u.id,u.mycar_name,u.agency_no,u.startdate,u.enddate,u.state,"
				+ "m.car_no no,m.carnumber,m.color,m.options,c.car_name,c.car_type,c.car_brand,c.price "
				+ "from user_car_list u, mycar_register m, car c "
				+ "where u.id=m.id and u.mycar_name=m.mycar_name and m.car_no=c.car_no) "
				+ "where state='0' and agency_no=? and startdate<=? and enddate>=?");
			pstmt.setInt(1, agency_no);
			pstmt.setString(2, rent_date);
			pstmt.setString(3, return_date);
			rs = pstmt.executeQuery();
			while(rs.next()){
				car_no = rs.getInt(1);
				pstmt = conn.prepareStatement(
					"select car_id,no,agency_no,carnumber,color,options,car_name,car_type,car_brand,price "
					+ "from(select u.car_id,u.id,u.mycar_name,u.agency_no,u.startdate,u.enddate,u.state,"
					+ "m.car_no no,m.carnumber,m.color,m.options,c.car_name,c.car_type,c.car_brand,c.price "
					+ "from user_car_list u, mycar_register m, car c "
					+ "where u.id=m.id and u.mycar_name=m.mycar_name and m.car_no=c.car_no) "
					+ "where no=? and state='0' and agency_no=? and startdate<=? and enddate>=?");
				pstmt.setInt(1, car_no);
				pstmt.setInt(2, agency_no);
				pstmt.setString(3, rent_date);
				pstmt.setString(4, return_date);
				rs2 = pstmt.executeQuery();
				if(rs2.next()){
					CarListJoinCarDataBean list = new CarListJoinCarDataBean();
					list.setCar_id(rs2.getString("car_id"));
					list.setCar_no(rs2.getInt("no"));
					list.setAgency_no(rs2.getInt("agency_no"));
					list.setCarNumber(rs2.getString("carnumber"));
					list.setColor(rs2.getString("color"));
					list.setOptions(rs2.getString("options"));
					list.setCar_name(rs2.getString("car_name"));
					list.setCar_type(rs2.getString("car_type"));
					list.setCar_brand(rs2.getString("car_brand"));
					list.setPrice(rs2.getInt("price"));
					
					availableUserCarList.add(list);
					
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return availableUserCarList;
	}
}
