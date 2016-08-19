package bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


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
            "insert into user_list(id,password,name,phone,address,zipcode,email,reg_date,birth,point,grade) values (?,?,?,?,?,?,?,sysdate,TO_DATE(?,'YYYYMMDD'),0,1)");
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress());
            pstmt.setString(6, member.getZipcode());
            pstmt.setString(7, member.getEmail());
            pstmt.setString(8, member.getBirth());
  
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
    public List getUserList() throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		UserListDataBean uldb = new UserListDataBean();
		List userList = new ArrayList();
		
		try{
			conn = getConnection();
			sql = "select * from user_list";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				do{
					uldb.setId(rs.getString("id"));
					uldb.setPassword(rs.getString("password"));
					uldb.setName(rs.getString("name"));
					uldb.setPhone(rs.getString("phone"));
					uldb.setAddress(rs.getString("address"));
					uldb.setZipcode(rs.getString("zipcode"));
					uldb.setEmail(rs.getString("email"));
					uldb.setReg_date(rs.getString("reg_date"));
					uldb.setBirth(rs.getString("birth"));
					uldb.setPoint(rs.getInt("point"));
					uldb.setGrade(rs.getInt("grade"));
					userList.add(uldb);
				}while(rs.next());
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return userList;
	}
    public int getUserCount() throws Exception{
    	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		int count = 0;
		
		try{
			conn = getConnection();
			sql = "select count(*) from user_list";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
		return count;
		
    }
	public int kickUser(String id) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String kickingId = id;
		String sql = "";
		int check = 0;
		
		try{
			conn = getConnection();
			sql = "delete from user_list where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kickingId);
			check = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		return check;
	}
}