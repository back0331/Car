package logon;

import java.sql.*;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

import logon.UserListDataBean;

public class UserListDBBean {
   
	private static UserListDBBean instance = new UserListDBBean();
   
	
    public static UserListDBBean getInstance() {
        return instance;
    }
   
    private UserListDBBean() {}
   
/*	private Connection getConnection() throws Exception{
		String jdbcDriver="jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}    */
    private Connection getConnection() throws Exception {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","shin","tls1980");
    	
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
            "select password from user_car where id = ?");
            pstmt.setString(1, id);
            rs= pstmt.executeQuery();

            if(rs.next()){
            	dbpasswd= rs.getString("password");
            	if(dbpasswd.equals(password))
            		           		
            		x= 1; 
            	else
            		x= 0;
            }else
            	x= -1;
          System.out.println("dbpasswd:"+dbpasswd);
          System.out.println("password:"+password);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        System.out.println("x::"+x);
        return x; 
    }   

	public int confirmId(String id) throws Exception {
    	Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs= null;
        int x=-1;
       
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select id from user_car where id = ?");
            pstmt.setString(1, id);
           
           
            rs= pstmt.executeQuery();
         //   System.out.println(id);
           
            if(rs.next())
            	x= 1; 
            else
            	x= -1;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return x;
    }
    
    
    
    
    public void insertMember(UserListDataBean member) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
       
        try {
            conn = getConnection();
 	  
            pstmt = conn.prepareStatement(
            "insert into user_car values (?,?,?,?,?,?,?)");
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getEmail());
            pstmt.setString(6, member.getZipcode());
            pstmt.setString(7, member.getAddress());
  
            pstmt.executeUpdate();
            	          
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    
    }
    
    public int searchPw(String id,String email) throws Exception{
    	Connection conn=null;
    	PreparedStatement pstmt= null;
    	ResultSet rs=null;
    	int x=-1;
    	try{
    		conn=getConnection();
    		pstmt=conn.prepareStatement("select * from user_car where id=? and email=?");
    		pstmt.setString(1, id);
    		pstmt.setString(2, email);
    		rs= pstmt.executeQuery();
    		
    		System.out.println("id:::"+id);
    		   if(rs.next())
               	x= 1; 
               else
               	x= -1;
           } catch(Exception ex) {
               ex.printStackTrace();
           } finally {
           	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
               if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
               if (conn != null) try { conn.close(); } catch(SQLException ex) {}
           }
           return x;
    }
    public int updatePw(String pw,String id) throws Exception{
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	int x=0;
    	try{
    		conn=getConnection();
    		pstmt=conn.prepareStatement("update user_car set password=? where id=?");
    		pstmt.setString(1, pw);
    		pstmt.setString(2, id);
    		x=pstmt.executeUpdate();
    	} catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	return x;
    	
    }
}