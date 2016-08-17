package bean;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class AgencyDBBean {
	
	private static AgencyDBBean instance = new AgencyDBBean();
	public static AgencyDBBean getInstance(){
		return instance;
	}
	private AgencyDBBean(){}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	
	
	//DB 에서 가져온 번호 이름 주소 등...Agency Table의 값들을 Agency 객체에 집어넣는다. 
	public AgencyDataBean makeAgency(ResultSet rs) throws Exception{
		AgencyDataBean agency = new AgencyDataBean();
		agency.setAgency_no(rs.getInt("agency_no"));
		agency.setAgency_name(rs.getString("agency_name"));
		agency.setAgency_address(rs.getString("agency_address"));
		agency.setZipcode(rs.getString("zipcode"));
		agency.setMgr(rs.getString("mgr"));
		agency.setTel(rs.getString("tel"));
		agency.setCount(rs.getInt("count"));
		agency.setLoc(rs.getString("loc"));
		
		return agency;
	}
	
	
	
	
	
	
	
	//지점이 정보를 다 가져온다. 
	public List<AgencyDataBean> getAgencyList() throws Exception{
		List<AgencyDataBean> agencyList = new ArrayList<AgencyDataBean>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from agency");
			while(rs.next()){
				AgencyDataBean agency = makeAgency(rs);
				agencyList.add(agency);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);
		}
		
		return agencyList;
	}
	
	
	
	
	//지점의 번호를 치면, 지점의 이름이 나오는거지 ----> 지점1' 이렇게.
	public String getAgencyName(int agency_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String agency_name="";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select agency_name from agency where agency_no=?");
															
			pstmt.setInt(1, agency_no);
			rs = pstmt.executeQuery();
			if(rs.next())
				agency_name = rs.getString(1);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return agency_name;
	}
}
