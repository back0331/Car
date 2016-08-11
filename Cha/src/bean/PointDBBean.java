package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;

public class PointDBBean {
	private static PointDBBean instance = new PointDBBean();
	public static PointDBBean getInstance(){
		return instance;
	}
	private PointDBBean(){}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	public int getMyPoint(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int point = 0;
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select point from user_list where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				point = rs.getInt(1);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return point;
	}
	
	//포인트 사용or적립
	public void insertPointList(PointDataBean point) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			//먼저 타입에 따라 해당고객의 포인트값을 변경
			String type = point.getType();
			if(type.equals("적립")){
				pstmt = conn.prepareStatement("update user_list set point=point+? where id=?");
				pstmt.setInt(1, Integer.parseInt((point.getAmount())));
				pstmt.setString(2, point.getId());
				pstmt.executeUpdate();
			} else if(type.equals("사용")){
				pstmt = conn.prepareStatement("update user_list set point-? where id=?");
				pstmt.setInt(1, Integer.parseInt((point.getAmount())));
				pstmt.setString(2, point.getId());
				pstmt.executeUpdate();
			}
			
			//point테이블에 포인트 내역 삽입
			pstmt = conn.prepareStatement("insert into point values(seq_point_point_no.nextval,?,?,?,sysdate)");
			pstmt.setString(1, point.getId());
			pstmt.setString(2, point.getType());
			pstmt.setString(3, point.getAmount());
			pstmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public List<PointDataBean> getMyPointList(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PointDataBean> pointList = new ArrayList<PointDataBean>();
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement("select point_no,id,type,amount,to_char(reg_date,'YYYY-MM-DD') reg_date from point where id=? order by reg_date desc");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				PointDataBean point = new PointDataBean();
				point.setPoint_no(rs.getInt("point_no"));
				point.setId(rs.getString("id"));
				point.setType(rs.getString("type"));
				point.setAmount(rs.getString("amount"));
				point.setReg_date(rs.getString("reg_date"));
				
				pointList.add(point);
			}
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return pointList;
	}
	
	
}
