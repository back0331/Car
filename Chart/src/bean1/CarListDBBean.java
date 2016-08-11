package bean1;

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
	
	
	
	
	
	
	
	//1. 일단 모든 차량들을 기본 state로 (0으로)만드는 메서드.
	public void setState() throws Exception{
		Connection conn = null;											
		Statement stmt = null;
		try {
			conn = getConnection();										//1. 커넥션은 무조건 가져오네.
			stmt = conn.createStatement();							//2. 그 커넥션에서 statement 준비해서.(쿼리문이 담길 곳)
			int count = stmt.executeUpdate("update car_list set state='0'");	//3. 거기에 쿼리문 실행해서 넣는다. 
											//어차피 '정비중'이든 '예약중,운행중'이든 repair과 book 테이블 거기에 있는 정보 참조해서 하는거니깐
											//따로 이 테이블의 state에 값을 저장할 필요는 없다. 
			System.out.println("set0:::" + count);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);							//car_list 테이블 준비되어야 한다. 
		}
	}
	
	
	
	
	
	
	
	
	//2. 나중에 쓸때 
		//매개변수 안에 있는 메서드를 먼저 실행시켜서 변수지정해서 저장하고.		//BookDBBean의  getUnavailableCarList 메서드!
		//그다음에 이 메서드 실행시키면(불러오면)된다. 
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
				System.out.println("set1:::" + count);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	
	
	
	
	
	
	//일단 건너뛰고.
	public void setStateCheck(List checkUnavailableCarList) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String car_id;
		
		try {
			conn = getConnection();
			String sql = "update car_list set state='2' where car_id=?";
			for(int i = 0; i < checkUnavailableCarList.size(); i++){
				car_id=(String)checkUnavailableCarList.get(i);		//get(i) 인덱스
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
	
	//위의 세개의 메서드는 리턴값 필요없이. 
	//바로 DB에 있는 값을 건들기 때문에 ~ 굳이 밑의 method 랑 연결 시킬 필요 없다. 
	//조건에 따라 ★초기화 시켜주는 메서드라고 보면 된다.
	
	
	
	
	
	
	
	
	
	public List getAvailableCarList(int agency_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int car_no;
		List<CarListJoinCarDataBean> availableCarList = new ArrayList<CarListJoinCarDataBean>();
		//여기까진 실행되고..
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"select distinct c from "
				+ "(select car_id,car_list.car_no c,agency_no,carnumber,color,options,state,"
				+ "car_name,car_type,car_brand,price from car_list, car where car_list.car_no=car.car_no) "
				+ "where state='0' and agency_no=?");
			pstmt.setInt(1, agency_no);
			rs = pstmt.executeQuery();
			//여기까지도 문제없다.
			
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
			
			JdbcUtil.close(rs);			//여기가 문제다!!!!!
			System.out.println("호잇?");
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		

		return availableCarList;
	}
	

}
