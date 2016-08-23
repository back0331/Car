package bean;

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
	
	/*public void setState() throws Exception{
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
	}*/
	
	public List getAvailableUserCarList(int agency_no, String rent_date, String return_date, List bookUnavailableCarList) throws Exception{
		double discount = 0.1;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int car_no;
		String car_id;
		List<CarListJoinCarDataBean> availableUserCarList = new ArrayList<CarListJoinCarDataBean>();
		
		try {
			
			//user_car_list에서 state를 0으로 초기화
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("update user_car_list set state='0'");
			//System.out.println("set0:::" + count);
			
			//이미 예약된 차들의 state를 1로 변경
			String sql = "update user_car_list set state='1' where car_id=?";
			for(int i = 0; i < bookUnavailableCarList.size(); i++){
				car_id=(String)bookUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				pstmt.executeUpdate();
				//System.out.println("set1:::" + count);
			}
			
			//예약 가능한 차들의 목록을 가져옴
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
					int price = rs2.getInt("price");
					price = price - (int)(price * discount);
					list.setPrice(price);
					
					availableUserCarList.add(list);
					
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.rollback(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return availableUserCarList;
	}
	
	public List getAvailableUserCarList(String car_type, int agency_no, String rent_date, String return_date, List bookUnavailableCarList) throws Exception{
		double discount = 0.1;
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int car_no;
		String car_id;
		List<CarListJoinCarDataBean> availableUserCarList = new ArrayList<CarListJoinCarDataBean>();
		
		try {
			
			//user_car_list에서 state를 0으로 초기화
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("update user_car_list set state='0'");
			//System.out.println("set0:::" + count);
			
			//이미 예약된 차들의 state를 1로 변경
			String sql = "update user_car_list set state='1' where car_id=?";
			for(int i = 0; i < bookUnavailableCarList.size(); i++){
				car_id=(String)bookUnavailableCarList.get(i);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, car_id);
				pstmt.executeUpdate();
				//System.out.println("set1:::" + count);
			}
			
			//예약 가능한 차들의 목록을 가져옴
			pstmt = conn.prepareStatement(
				"select distinct no from"
				+ "(select u.car_id,u.id,u.mycar_name,u.agency_no,u.startdate,u.enddate,u.state,"
				+ "m.car_no no,m.carnumber,m.color,m.options,c.car_name,c.car_type,c.car_brand,c.price "
				+ "from user_car_list u, mycar_register m, car c "
				+ "where u.id=m.id and u.mycar_name=m.mycar_name and m.car_no=c.car_no) "
				+ "where state='0' and agency_no=? and startdate<=? and enddate>=? and car_type=?");
			pstmt.setInt(1, agency_no);
			pstmt.setString(2, rent_date);
			pstmt.setString(3, return_date);
			pstmt.setString(4, car_type);
			rs = pstmt.executeQuery();
			while(rs.next()){
				car_no = rs.getInt(1);
				pstmt = conn.prepareStatement(
					"select car_id,no,agency_no,carnumber,color,options,car_name,car_type,car_brand,price "
					+ "from(select u.car_id,u.id,u.mycar_name,u.agency_no,u.startdate,u.enddate,u.state,"
					+ "m.car_no no,m.carnumber,m.color,m.options,c.car_name,c.car_type,c.car_brand,c.price "
					+ "from user_car_list u, mycar_register m, car c "
					+ "where u.id=m.id and u.mycar_name=m.mycar_name and m.car_no=c.car_no) "
					+ "where no=? and state='0' and agency_no=? and startdate<=? and enddate>=? and car_type=?");
				pstmt.setInt(1, car_no);
				pstmt.setInt(2, agency_no);
				pstmt.setString(3, rent_date);
				pstmt.setString(4, return_date);
				pstmt.setString(5, car_type);
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
					int price = rs2.getInt("price");
					price = price - (int)(price * discount);
					list.setPrice(price);
					
					availableUserCarList.add(list);
					
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.rollback(conn);
			JdbcUtil.close(rs);
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return availableUserCarList;
	}
	
	public CarListJoinCarDataBean getCarData(String car_id) throws Exception{
		double discount = 0.1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CarListJoinCarDataBean car = new CarListJoinCarDataBean();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"select * from user_car_list u,mycar_register m,car c "
					+ "where u.car_id=? and u.id=m.id and u.mycar_name=m.mycar_name and "
					+ "m.car_no=c.car_no");
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
				int price = rs.getInt("price");
				price = price - (int)(price * discount);
				car.setPrice(price);
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
	
	public int insertUserCarList(UserCarListDataBean userCar) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("insert into user_car_list values(seq_car_list_car_id.nextval,?,?,?,TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),TO_DATE(?,'YYYY-MM-DD'),?,sysdate)");
			pstmt.setString(1, userCar.getId());
			pstmt.setString(2, userCar.getMyCarName());
			pstmt.setInt(3, userCar.getAgency_no());
			pstmt.setString(4, userCar.getStartDate());
			pstmt.setString(5, userCar.getEndDate());
			pstmt.setString(6, userCar.getCheckDate());
			pstmt.setString(7, "0");
			result = pstmt.executeUpdate();
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	//user_car_list에 이미 내 차가 등록되어있는지 확인
	public int checkCarList(String id, String mycar_name) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_car_list where id=? and mycar_name=?");
			pstmt.setString(1, id);
			pstmt.setString(2, mycar_name);
			rs = pstmt.executeQuery();
			if(rs.next())
				result = 1;
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
	
	public void updateExpiredUserCarList(String today) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_car_list where enddate<?");
			pstmt.setString(1, today);
			rs = pstmt.executeQuery();
			while(rs.next()){
				pstmt = conn.prepareStatement(
					"insert into expired_user_car_list(car_id,id,mycar_name,agency_no,startdate,enddate,checkdate,state,reg_date)"
					+ " values(?,?,?,?,?,?,?,?,?)");
				pstmt.setString(1, rs.getString("car_id"));
				pstmt.setString(2, rs.getString("id"));
				pstmt.setString(3, rs.getString("mycar_name"));
				pstmt.setInt(4, rs.getInt("agency_no"));
				pstmt.setTimestamp(5, rs.getTimestamp("startdate"));
				pstmt.setTimestamp(6, rs.getTimestamp("enddate"));
				pstmt.setTimestamp(7, rs.getTimestamp("checkdate"));
				pstmt.setString(8, rs.getString("state"));
				pstmt.setTimestamp(9, rs.getTimestamp("reg_date"));
				int result = pstmt.executeUpdate();
//				System.out.println("result:::"+result);
				if(result==1){
					pstmt2 = conn.prepareStatement("delete from user_car_list where car_id=?");
					pstmt2.setString(1, rs.getString("car_id"));
					int result2 = pstmt2.executeUpdate();
//					System.out.println("result2:::"+result2);
					if(result2==0)
						JdbcUtil.rollback(conn);
				}
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public List getExpiredUserCarList(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List expiredUserCarRegisterList = new ArrayList();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"select TO_CHAR(reg_date,'YYYY-MM-DD') reg_date, mycar_name,agency_name,"
				+ "TO_CHAR(startdate,'YYYY-MM-DD') startdate,TO_CHAR(enddate,'YYYY-MM-DD') enddate "
				+ "from expired_user_car_list u, agency a where id=? and a.agency_no=u.agency_no order by reg_date desc");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				UserCarListDataBean expiredUserCarList = new UserCarListDataBean();
				expiredUserCarList.setReg_date(rs.getString("reg_date"));
				expiredUserCarList.setMyCarName(rs.getString("mycar_name"));
				//state 프로퍼티에 지점명을 임시로 저장해둠
				expiredUserCarList.setState(rs.getString("agency_name"));
				expiredUserCarList.setStartDate(rs.getString("startdate"));
				expiredUserCarList.setEndDate(rs.getString("enddate"));
				
				expiredUserCarRegisterList.add(expiredUserCarList);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return expiredUserCarRegisterList;
	}
	
	//user_car_list에서 해당 id로 등록된 차 목록을 가져온다.
	public List getUserCarList(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List UserCarRegisterList = new ArrayList();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"select car_id,TO_CHAR(reg_date,'YYYY-MM-DD') reg_date, mycar_name,agency_name,"
				+ "TO_CHAR(startdate,'YYYY-MM-DD') startdate,TO_CHAR(enddate,'YYYY-MM-DD') enddate "
				+ "from user_car_list u, agency a where id=? and a.agency_no=u.agency_no order by reg_date desc");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				UserCarListDataBean userCarList = new UserCarListDataBean();
				userCarList.setCar_id(rs.getString("car_id"));
				userCarList.setReg_date(rs.getString("reg_date"));
				userCarList.setMyCarName(rs.getString("mycar_name"));
				//state 프로퍼티에 지점명을 임시로 저장해둠
				userCarList.setState(rs.getString("agency_name"));
				userCarList.setStartDate(rs.getString("startdate"));
				userCarList.setEndDate(rs.getString("enddate"));
				
				UserCarRegisterList.add(userCarList);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return UserCarRegisterList;
	}
	
	//해당 아이디로 user_car_list에 차량이 등록되어있는지 확인한다.
	// 0 차량없음 , 1 차량있음
	public int checkCarList(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select * from user_car_list where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				result = 1;
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return result;
	}
	
}
