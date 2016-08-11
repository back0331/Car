package Rent_Login;

import java.sql.*;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.tomcat.jdbc.pool.DataSource;

public class UserList_DAO {//DB와 관련된 일을 하는 클래스: DBBean, DAO
   
	private static UserList_DAO instance = new UserList_DAO();
   
	
    public static UserList_DAO getInstance() {
        return instance;
    }
   
    private UserList_DAO() {}
   
    private Connection getConnection() throws Exception {
    	Class.forName("oracle.jdbc.driver.OracleDriver");
    	return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","broad","broad");
    	
    }
    
    
    public boolean idCheck(String id) throws Exception{
    	boolean result = true;
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs  = null;
    	try {
    		conn = getConnection();
    		pstmt= conn.prepareStatement("Select * from test05 where id = ?");
    		pstmt.setString(1,  id);
    		rs = pstmt.executeQuery();
    		if(!rs.next())
    			result = false;
    	}catch (SQLException e){
    		e.printStackTrace();
    	}finally{
    		if (rs !=null) try {rs.close(); } catch (SQLException sqle1){}
    		if (pstmt !=null) try {pstmt.close();} catch (SQLException sqle2){}
    		if (conn !=null) try { conn.close(); } catch (SQLException sqle3){}
    	}
    	return result;
    	    	
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
            		System.out.println("성공");
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