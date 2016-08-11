package bean;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sun.jndi.url.corbaname.corbanameURLContextFactory;

import jdbc.JdbcUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CarListDBBean {

	private static CarListDBBean instance = new CarListDBBean();
	public static CarListDBBean getInstance(){
		return instance;
	}
	private CarListDBBean(){}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	//하나도 묶고 rollback?
	/*public void setState() throws Exception{
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			int count = stmt.executeUpdate("update car_list set state='0'");
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
			String sql = "update car_list set state='1' where car_id=?";
			for(int i = 0; i < bookUnavailableCarList.size(); i++){
				car_id=(String)bookUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				int count = pstmt.executeUpdate();
				//System.out.println("set1:::" + count);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public void setStateCheck(List checkUnavailableCarList) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String car_id;
		
		try {
			conn = getConnection();
			String sql = "update car_list set state='2' where car_id=?";
			for(int i = 0; i < checkUnavailableCarList.size(); i++){
				car_id=(String)checkUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				int count = pstmt.executeUpdate();
				System.out.println("set2:::" + count);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	
	public List getAvailableCarList(int agency_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int car_no;
		List<CarListJoinCarDataBean> availableCarList = new ArrayList<CarListJoinCarDataBean>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"select distinct c from "
				+ "(select car_id,car_list.car_no c,agency_no,carnumber,color,options,state,"
				+ "car_name,car_type,car_brand,price from car_list, car where car_list.car_no=car.car_no) "
				+ "where state='0' and agency_no=?");
			pstmt.setInt(1, agency_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				car_no = rs.getInt(1);
				pstmt = conn.prepareStatement(
					"select car_id,car_no,agency_no,carnumber,color,options,car_name,car_type,car_brand,price "
					+ "from (select car_id ,car_list.car_no,agency_no,carnumber,color,options,state,car_name,car_type,car_brand,price "
					+ "from car_list, car where car_list.car_no=car.car_no) where car_no=? and state='0' and agency_no=?");
				pstmt.setInt(1, car_no);
				pstmt.setInt(2, agency_no);
				rs2 = pstmt.executeQuery();
				if(rs2.next()){
					CarListJoinCarDataBean list = new CarListJoinCarDataBean();
					list.setCar_id(rs2.getString("car_id"));
					list.setCar_no(rs2.getInt("car_no"));
					list.setAgency_no(rs2.getInt("agency_no"));
					list.setCarNumber(rs2.getString("carnumber"));
					list.setColor(rs2.getString("color"));
					list.setOptions(rs2.getString("options"));
					list.setCar_name(rs2.getString("car_name"));
					list.setCar_type(rs2.getString("car_type"));
					list.setCar_brand(rs2.getString("car_brand"));
					list.setPrice(rs2.getInt("price"));
					
					availableCarList.add(list);
					
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
		return availableCarList;
	}*/
	
	public List getAvailableCarList(int agency_no, List bookUnavailableCarList, List checkUnavailableCarList) throws Exception{
		
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int car_no;
		String car_id;
		List<CarListJoinCarDataBean> availableCarList = new ArrayList<CarListJoinCarDataBean>();
		
		try {
			
			// car_list에 state를 0으로 초기화
			conn = getConnection();
			stmt = conn.createStatement();
			int count = stmt.executeUpdate("update car_list set state='0'");
			//System.out.println("set0:::" + count);
		
			//이미 예약된 차들의 state를 1로 변경한다.
			String sql = "update car_list set state='1' where car_id=?";
			for(int i = 0; i < bookUnavailableCarList.size(); i++){
				car_id=(String)bookUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				pstmt.executeUpdate();
			}
				//System.out.println("set1:::" + count);	
		
	
			//수리중인 차들의 state를 2로 변경한다.
			sql = "update car_list set state='2' where car_id=?";
			for(int i = 0; i < checkUnavailableCarList.size(); i++){
				car_id=(String)checkUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				pstmt.executeUpdate();
//				System.out.println("set2:::" + count);	
			}
			
			//car_list에서 예약가능한 차 목록을 꺼낸다.
			pstmt = conn.prepareStatement(
				"select distinct c from "
				+ "(select car_id,car_list.car_no c,agency_no,carnumber,color,options,state,"
				+ "car_name,car_type,car_brand,price from car_list, car where car_list.car_no=car.car_no) "
				+ "where state='0' and agency_no=?");
			pstmt.setInt(1, agency_no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				car_no = rs.getInt(1);
				pstmt = conn.prepareStatement(
					"select car_id,car_no,agency_no,carnumber,color,options,car_name,car_type,car_brand,price "
					+ "from (select car_id ,car_list.car_no,agency_no,carnumber,color,options,state,car_name,car_type,car_brand,price "
					+ "from car_list, car where car_list.car_no=car.car_no) where car_no=? and state='0' and agency_no=?");
				pstmt.setInt(1, car_no);
				pstmt.setInt(2, agency_no);
				rs2 = pstmt.executeQuery();
				if(rs2.next()){
					CarListJoinCarDataBean list = new CarListJoinCarDataBean();
					list.setCar_id(rs2.getString("car_id"));
					list.setCar_no(rs2.getInt("car_no"));
					list.setAgency_no(rs2.getInt("agency_no"));
					list.setCarNumber(rs2.getString("carnumber"));
					list.setColor(rs2.getString("color"));
					list.setOptions(rs2.getString("options"));
					list.setCar_name(rs2.getString("car_name"));
					list.setCar_type(rs2.getString("car_type"));
					list.setCar_brand(rs2.getString("car_brand"));
					list.setPrice(rs2.getInt("price"));
					
					availableCarList.add(list);
					
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.rollback(conn); //리스트는 뽑은 후 원상태로 돌려줌
			JdbcUtil.close(rs);
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return availableCarList;
	}
	
public List getAvailableCarList(String car_type, int agency_no, List bookUnavailableCarList, List checkUnavailableCarList) throws Exception{
		
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int car_no;
		String car_id;
		List<CarListJoinCarDataBean> availableCarList = new ArrayList<CarListJoinCarDataBean>();
		
		try {
			
			// car_list에 state를 0으로 초기화
			conn = getConnection();
			stmt = conn.createStatement();
			int count = stmt.executeUpdate("update car_list set state='0'");
			//System.out.println("set0:::" + count);
		
			//이미 예약된 차들의 state를 1로 변경한다.
			String sql = "update car_list set state='1' where car_id=?";
			for(int i = 0; i < bookUnavailableCarList.size(); i++){
				car_id=(String)bookUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				pstmt.executeUpdate();
			}
				//System.out.println("set1:::" + count);	
		
	
			//수리중인 차들의 state를 2로 변경한다.
			sql = "update car_list set state='2' where car_id=?";
			for(int i = 0; i < checkUnavailableCarList.size(); i++){
				car_id=(String)checkUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				pstmt.executeUpdate();
//				System.out.println("set2:::" + count);	
			}
			
			//car_list에서 예약가능한 차 목록을 꺼낸다.
			pstmt = conn.prepareStatement(
				"select distinct c from "
				+ "(select car_id,car_list.car_no c,agency_no,carnumber,color,options,state,"
				+ "car_name,car_type,car_brand,price from car_list, car where car_list.car_no=car.car_no) "
				+ "where state='0' and agency_no=? and car_type=?");
			pstmt.setInt(1, agency_no);
			pstmt.setString(2, car_type);
			rs = pstmt.executeQuery();
			while(rs.next()){
				car_no = rs.getInt(1);
				pstmt = conn.prepareStatement(
					"select car_id,car_no,agency_no,carnumber,color,options,car_name,car_type,car_brand,price "
					+ "from (select car_id ,car_list.car_no,agency_no,carnumber,color,options,state,car_name,car_type,car_brand,price "
					+ "from car_list, car where car_list.car_no=car.car_no) where car_no=? and state='0' and agency_no=? and car_type=?");
				pstmt.setInt(1, car_no);
				pstmt.setInt(2, agency_no);
				pstmt.setString(3, car_type);
				rs2 = pstmt.executeQuery();
				if(rs2.next()){
					CarListJoinCarDataBean list = new CarListJoinCarDataBean();
					list.setCar_id(rs2.getString("car_id"));
					list.setCar_no(rs2.getInt("car_no"));
					list.setAgency_no(rs2.getInt("agency_no"));
					list.setCarNumber(rs2.getString("carnumber"));
					list.setColor(rs2.getString("color"));
					list.setOptions(rs2.getString("options"));
					list.setCar_name(rs2.getString("car_name"));
					list.setCar_type(rs2.getString("car_type"));
					list.setCar_brand(rs2.getString("car_brand"));
					list.setPrice(rs2.getInt("price"));
					
					availableCarList.add(list);
					
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.rollback(conn); //리스트는 뽑은 후 원상태로 돌려줌
			JdbcUtil.close(rs);
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return availableCarList;
	}
	
	public CarListJoinCarDataBean getCarData(String car_id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CarListJoinCarDataBean car = new CarListJoinCarDataBean();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from car_list, car where car_id=? and car_list.car_no=car.car_no");
			pstmt.setString(1, car_id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				car.setCar_id(rs.getString("car_id"));
				car.setCar_no(rs.getInt("car_no"));
				car.setAgency_no(rs.getInt("agency_no"));
				car.setCarNumber(rs.getString("carnumber"));
				car.setColor(rs.getString("color"));
				car.setOptions(rs.getString("options"));
				car.setCar_name(rs.getString("car_name"));
				car.setCar_type(rs.getString("car_type"));
				car.setCar_brand(rs.getString("car_brand"));
				car.setPrice(rs.getInt("price"));
			}
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return car;
	}
}
