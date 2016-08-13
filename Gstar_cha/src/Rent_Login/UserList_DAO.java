package Rent_Login;

import java.sql.*;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

public class UserList_DAO {//DB�� ���õ� ���� �ϴ� Ŭ����: DBBean, DAO
   
	private static UserList_DAO instance = new UserList_DAO();
   
	
    public static UserList_DAO getInstance() {
        return instance;
    }
   
    private UserList_DAO() {}
   
    private Connection getConnection() throws Exception {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","broad","broad");
    	
    }
    



    
    
public int userCheck(String id, String password) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;    
        ResultSet rs= null;
        String dbpasswd="";
        int x=-1;
        
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select password from test05 where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

            if(rs.next()){
            	dbpasswd= rs.getString("password");
            	if(dbpasswd.equals(password))
            		
            		
            		x= 1; //���� ����
            	else
            		x= 0; //��й�ȣ Ʋ��
            }else
            	x= -1;//�ش� ���̵� ����
            System.out.println(dbpasswd);
            System.out.println(password);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x; 
    }


    

    
    
    private void close(Connection con, PreparedStatement ps, ResultSet rs) {
		// TODO Auto-generated method stub
		
	}

	public int confirmId(String id) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;//����� ��
       
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select id from test05 where id = ?");
            pstmt.setString(1, id);
           
           
            rs= pstmt.executeQuery();
            System.out.println(id);
           
            if(rs.next())
            	x= 1; //�ش� ���̵� ����
            else
            	x= -1;//�ش� ���̵� ����
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
    
    
    
    
    public String insertMember(UserListDataBean member) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
       
        try {
            conn = getConnection();
 	  
            pstmt = conn.prepareStatement(
            "insert into test05 values (?,?,?,?,?,?,?)");
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getPassword());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getPhone());
            pstmt.setString(6, member.getZipcode());
            pstmt.setString(7, member.getAddress());
  
            int check = pstmt.executeUpdate();
            	if(check >= 1 ){
            		System.out.println("����");
            		return "/UserForm/success.jsp";
            	}
            
            
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return "/UserForm/fail.jsp";
    }
}