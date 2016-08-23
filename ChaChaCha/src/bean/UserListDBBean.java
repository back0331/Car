package bean;

import java.sql.*;

import jdbc.JdbcUtil;


public class UserListDBBean {
   
	private static UserListDBBean instance = new UserListDBBean();
   
	
    public static UserListDBBean getInstance() {
        return instance;
    }
   
    private UserListDBBean() {}
   
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
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
	            "select password from user_list where id = ?");
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
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
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
            "select id from user_list where id = ?");
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
    
    
    
    
    public int insertMember(UserListDataBean member) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int result=0;
       
        try {
            conn = getConnection();
 	  
            pstmt = conn.prepareStatement(
            "insert into user_list(id,password,name,phone,address1,address2,zipcode,email,reg_date,birth,point,grade) values (?,?,?,?,?,?,?,?,sysdate,TO_DATE(?,'YYYYMMDD'),0,1)");
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress1());
            pstmt.setString(6, member.getAddress2());
            pstmt.setString(7, member.getZipcode());
            pstmt.setString(8, member.getEmail());
            pstmt.setString(9, member.getBirth());
  
            result = pstmt.executeUpdate();
            	          
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        
        return result;
    
    }
    
    public int searchPw(String id,String email) throws Exception{
    	Connection conn=null;
    	PreparedStatement pstmt= null;
    	ResultSet rs=null;
    	int x=-1;
    	try{
    		conn=getConnection();
    		pstmt=conn.prepareStatement("select * from user_list where id=? and email=?");
    		pstmt.setString(1, id);
    		pstmt.setString(2, email);
    		rs= pstmt.executeQuery();
    		
//    		System.out.println("id:::"+id);
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
    		pstmt=conn.prepareStatement("update user_list set password=? where id=?");
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
    
    public UserListDataBean getMember(String id) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	ResultSet rs = null;
    	UserListDataBean member = new UserListDataBean();
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    			"select id,name,password,phone,address1,address2,zipcode,email,TO_CHAR(reg_date,'YYYYMMDD') reg_date,"
    			+ "TO_CHAR(birth,'YYYYMMDD') birth,point,grade from user_list where id=?");
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		if(rs.next()){
	    		member.setId(rs.getString("id"));
	    		member.setName(rs.getString("name"));
	    		member.setPassword(rs.getString("password"));
	    		member.setPhone(rs.getString("phone"));
	    		member.setAddress1(rs.getString("address1"));
	    		member.setAddress2(rs.getString("address2"));
	    		member.setZipcode(rs.getString("zipcode"));
	    		member.setEmail(rs.getString("email"));
	    		member.setReg_date(rs.getString("reg_date"));
	    		member.setBirth(rs.getString("birth"));
	    		member.setPoint(rs.getInt("point"));
	    		member.setGrade(rs.getInt("grade"));
    		}
    	} catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        } 
    	return member;
    }
    
    public int updateMember(UserListDataBean member) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	int result = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement("update user_list set password=?,phone=?,zipcode=?,address1=?,address2=? where id=?");
    		pstmt.setString(1, member.getPassword());
    		pstmt.setString(2, member.getPhone());
    		pstmt.setString(3, member.getZipcode());
    		pstmt.setString(4, member.getAddress1());
    		pstmt.setString(5, member.getAddress2());
    		pstmt.setString(6, member.getId());
    		result = pstmt.executeUpdate();
    	} catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	return result;
    }
    
    public int deleteMember(UserListDataBean member, String reason) throws Exception{
    	Connection conn = null;
    	PreparedStatement pstmt = null;
    	int result = 0;
    	int result2 = 0;
    	
    	try {
    		conn = getConnection();
    		pstmt = conn.prepareStatement(
    			"insert into out_user(no,id,name,phone,address1,address2,zipcode,email,birth,reg_date,drop_date,reason) "
    			+ "values(seq_out_user_no.nextval,?,?,?,?,?,?,?,TO_DATE(?,'YYYYMMDD'),TO_DATE(?,'YYYYMMDD'),sysdate,?)");
    		pstmt.setString(1, member.getId());
    		pstmt.setString(2, member.getName());
    		pstmt.setString(3, member.getPhone());
    		pstmt.setString(4, member.getAddress1());
    		pstmt.setString(5, member.getAddress2());
    		pstmt.setString(6, member.getZipcode());
    		pstmt.setString(7, member.getEmail());
    		pstmt.setString(8, member.getBirth());
    		pstmt.setString(9, member.getReg_date());
    		pstmt.setString(10, reason);
    		
    		result = pstmt.executeUpdate();
    		if(result==1){
    			pstmt = conn.prepareStatement("delete from user_list where id=?");
    			pstmt.setString(1, member.getId());
    			result2 = pstmt.executeUpdate();
    			if(result2==0){
    				JdbcUtil.rollback(conn);
    			}
    		}
    	} catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        } 
    	return result2;
    }
    
    public int searchId1(String name,String email) throws Exception{
    	Connection conn=null;
    	PreparedStatement pstmt= null;
    	ResultSet rs=null;
    	int x=-1;
    	try{
    		conn=getConnection();
    		pstmt=conn.prepareStatement("select * from user_list where name=? and email=?");
    		pstmt.setString(1, name);
    		pstmt.setString(2, email);
    		rs= pstmt.executeQuery();
    		
//    		System.out.println("name:::"+name);
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

	public String getUserId(String name, String email) throws Exception {
		Connection conn=null;
    	PreparedStatement pstmt= null;
    	ResultSet rs=null;
    	String x= "";
    	try{
    		conn=getConnection();
    		pstmt=conn.prepareStatement("select id from user_list where name=? and email=?");
    		pstmt.setString(1, name);
    		pstmt.setString(2, email);
    		rs= pstmt.executeQuery();
    		
//    		System.out.println("name:::"+name);
    		   if(rs.next())
               	x=rs.getString("id");
               
           } catch(Exception ex) {
               ex.printStackTrace();
           } finally {
           	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
               if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
               if (conn != null) try { conn.close(); } catch(SQLException ex) {}
           }
           return x;
	}
}