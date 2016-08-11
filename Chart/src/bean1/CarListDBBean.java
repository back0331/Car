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
	
	private Connection getConnection() throws Exception{	// �ش�Ŭ���� �ȿ����� ����ϱ� ���� private����.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	
	
	
	
	
	
	//1. �ϴ� ��� �������� �⺻ state�� (0����)����� �޼���.
	public void setState() throws Exception{
		Connection conn = null;											
		Statement stmt = null;
		try {
			conn = getConnection();										//1. Ŀ�ؼ��� ������ ��������.
			stmt = conn.createStatement();							//2. �� Ŀ�ؼǿ��� statement �غ��ؼ�.(�������� ��� ��)
			int count = stmt.executeUpdate("update car_list set state='0'");	//3. �ű⿡ ������ �����ؼ� �ִ´�. 
											//������ '������'�̵� '������,������'�̵� repair�� book ���̺� �ű⿡ �ִ� ���� �����ؼ� �ϴ°Ŵϱ�
											//���� �� ���̺��� state�� ���� ������ �ʿ�� ����. 
			System.out.println("set0:::" + count);
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			JdbcUtil.close(stmt);
			JdbcUtil.close(conn);							//car_list ���̺� �غ�Ǿ�� �Ѵ�. 
		}
	}
	
	
	
	
	
	
	
	
	//2. ���߿� ���� 
		//�Ű����� �ȿ� �ִ� �޼��带 ���� ������Ѽ� ���������ؼ� �����ϰ�.		//BookDBBean��  getUnavailableCarList �޼���!
		//�״����� �� �޼��� �����Ű��(�ҷ�����)�ȴ�. 
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
	
	
	
	
	
	
	
	//�ϴ� �ǳʶٰ�.
	public void setStateCheck(List checkUnavailableCarList) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String car_id;
		
		try {
			conn = getConnection();
			String sql = "update car_list set state='2' where car_id=?";
			for(int i = 0; i < checkUnavailableCarList.size(); i++){
				car_id=(String)checkUnavailableCarList.get(i);		//get(i) �ε���
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
	
	//���� ������ �޼���� ���ϰ� �ʿ����. 
	//�ٷ� DB�� �ִ� ���� �ǵ�� ������ ~ ���� ���� method �� ���� ��ų �ʿ� ����. 
	//���ǿ� ���� ���ʱ�ȭ �����ִ� �޼����� ���� �ȴ�.
	
	
	
	
	
	
	
	
	
	public List getAvailableCarList(int agency_no) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		int car_no;
		List<CarListJoinCarDataBean> availableCarList = new ArrayList<CarListJoinCarDataBean>();
		//������� ����ǰ�..
		
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(
				"select distinct c from "
				+ "(select car_id,car_list.car_no c,agency_no,carnumber,color,options,state,"
				+ "car_name,car_type,car_brand,price from car_list, car where car_list.car_no=car.car_no) "
				+ "where state='0' and agency_no=?");
			pstmt.setInt(1, agency_no);
			rs = pstmt.executeQuery();
			//��������� ��������.
			
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
			
			JdbcUtil.close(rs);			//���Ⱑ ������!!!!!
			System.out.println("ȣ��?");
			JdbcUtil.close(rs2);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		

		return availableCarList;
	}
	

}
