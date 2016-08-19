package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewBoardDBBean {
	  private static ReviewBoardDBBean instance = new ReviewBoardDBBean();
	   
	    public static ReviewBoardDBBean getInstance() {
	        return instance;
	    }
	   
	    private ReviewBoardDBBean() {
	    }
	   
	    private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
			String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
			return DriverManager.getConnection(jdbcDriver);
		}
	    
	    public int insertArticle(ReviewBoardDataBean review){
	    	Connection conn=null;
	    	PreparedStatement pstmt = null;
			ResultSet rs = null;
			int r=0;
	       	String sql="";
	       	 
	       	try{
	       		conn=getConnection();
	       		sql="insert into review_board(article_no,book_no,article_subject,article_content,password,reg_date,id)"
	       				+"values(seg_review_board_article_no.NEXTVAL,?,?,?,?,?,?)";
	       		pstmt=conn.prepareStatement(sql);
	       		pstmt.setInt(1,review.getBook_no() );
	       		pstmt.setString(2, review.getArticle_subject());
	       		pstmt.setString(3, review.getArticle_content());
	       		pstmt.setString(4,review.getPassword());
	       		pstmt.setTimestamp(5, review.getReg_date());
	       		pstmt.setString(6, review.getId());
	             r= pstmt.executeUpdate();
	       	}catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	        	if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
	       	return r;
	    }
	    public int getArticleCount() throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;

	        int x=0;

	        try {
	            conn = getConnection();
	           
	            pstmt = conn.prepareStatement("select count(*) from review_board");//占쏙옙체 占쏙옙 占쏙옙占쏙옙
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	               x= rs.getInt(1);
	}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }return x;
	    }

	    //list.jsp ==> Paging!!! DB占싸븝옙占쏙옙 占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占� 占쌨는댐옙.
	    public List getArticles(int start, int end) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        List articleList=null;
	        try {
	            conn = getConnection();
	           
	            pstmt = conn.prepareStatement(
	            "select article_no,book_no,article_subject,article_content,id,password,reg_date,r  " +
	            "from (select article_no,book_no,article_subject,article_content,id,password,reg_date,rownum r"
	            + " from review_board order by reg_date desc) where r >= ? and r <= ? ");
	            pstmt.setInt(1, start);
	    		pstmt.setInt(2, end);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	                articleList = new ArrayList(end);
	                do{
	                  ReviewBoardDataBean review= new ReviewBoardDataBean();
	  				  review.setArticle_no(rs.getInt("article_no"));
	  				  review.setBook_no(rs.getInt("book_no"));
	  				  review.setArticle_subject(rs.getString("article_subject"));
	  				  review.setArticle_content(rs.getString("article_content"));
	  				  review.setPassword(rs.getString("password"));
	  				  review.setReg_date(rs.getTimestamp("reg_date"));
	  				  review.setId(rs.getString("id"));
	 
	                  articleList.add(review);
			}while(rs.next());
	    	}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
	return articleList;
	    }

	    //read.jsp : DB占싸븝옙占쏙옙 占쏙옙占쏙옙占쏙옙 占쏙옙占쏙옙占싶몌옙 占쏙옙占쏙옙占승댐옙.
	    public ReviewBoardDataBean getArticle(int article_no) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        ReviewBoardDataBean review=null;
	        try {
	            conn = getConnection();

	            pstmt = conn.prepareStatement("select * from review_board where article_no = ?");
	            pstmt.setInt(1, article_no);
	            rs = pstmt.executeQuery();
	           
	            if (rs.next()) {
	            	review = new ReviewBoardDataBean();
	            	review.setArticle_no(rs.getInt("article_no"));
	            	review.setId(rs.getString("id"));
	            	review.setBook_no(rs.getInt("book_no"));
	            	review.setArticle_subject(rs.getString("article_subject"));
	            	review.setArticle_content(rs.getString("article_content"));
	            	review.setPassword(rs.getString("password"));
	             	review.setReg_date(rs.getTimestamp("reg_date"));
	            		            	
					}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
	        System.out.println("review:::"+review);
			return review;
	    }
	    
	    public int getArticleCount(int n, String searchKeyword) throws Exception{
	    	
	    	Connection conn=null;
	    	PreparedStatement pstmt=null;
	    	ResultSet rs=null;
	    	
	    	String[] column_name = {"id","article_subject","article_content"};
			
			int x = 0;
			
			try
			{
				conn = getConnection();
				pstmt = conn.prepareStatement("select count (*) from review_board where "+column_name[n]+" like '%"+searchKeyword+"%'");
				
				rs = pstmt.executeQuery();
				
				if(rs.next())
					x = rs.getInt(1);
			}
	    	catch(Exception ex){
	    		ex.printStackTrace();
	    	}finally{
	    		if(rs!=null) try{rs.close();}catch(SQLException ex){}
	    		if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
				if(conn != null) try {conn.close();} catch(SQLException ex){}
			}
			return x;
		}
	    public List getArticles(int start, int end, int n, String searchKeyword) throws Exception{
	    	Connection conn=null;
	    	PreparedStatement pstmt=null;
	    	ResultSet rs=null;
	    	List articleList=null;
	    	
		String[] column_name = {"id","article_subject","article_content"};
			
			try
			{
				conn = getConnection();
				
				String sql = "select article_no,book_no,article_subject,id,password,reg_date,article_content,r "	
							+ "from (select article_no,book_no,article_subject,id,password,reg_date,article_content,rownum r "
							+"from review_board where "+column_name[n]+" like '%"+searchKeyword+"%') where r >= ? and r <= ?";
				
	    		pstmt=conn.prepareStatement(sql);
	    		pstmt.setInt(1,start);
	    		pstmt.setInt(2, end);
	    		
	    		rs=pstmt.executeQuery();
	    		
	    		if(rs.next())
	    		{
	    			articleList=new ArrayList(end);
	    			
	    			do{
	    					ReviewBoardDataBean review= new ReviewBoardDataBean();
	    					review.setArticle_no(rs.getInt("article_no"));
	    	            	review.setBook_no(rs.getInt("book_no"));
	    	            	review.setArticle_subject(rs.getString("article_subject"));
	    	            	review.setPassword(rs.getString("password"));
	    	            	review.setReg_date(rs.getTimestamp("reg_date"));
	    	            	review.setArticle_content(rs.getString("article_content"));
	    	            	review.setId(rs.getString("id"));
	    	            	
	    					articleList.add(review);
	    				}while(rs.next());
	    		}
	    			
	    		}
	    		catch(Exception ex)
	    		{
	    			ex.printStackTrace();
	    		}
	    		finally
	    		{
	    			if(rs != null) try {rs.close();} catch(SQLException ex){}
	    			if(pstmt != null) try {pstmt.close();} catch(SQLException ex){}
	    			if(conn != null) try {conn.close();} catch(SQLException ex){}
	    		}
	    	return articleList;
	    }
	    public ReviewBoardDataBean updateGetArticle(int article_no) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        ReviewBoardDataBean review=null;
	        try {
	            conn = getConnection();

	            pstmt = conn.prepareStatement(
	            "select * from review_board where article_no = ?");
	            pstmt.setInt(1, article_no);
	            rs = pstmt.executeQuery();

	            if (rs.next()) {
	            	review = new ReviewBoardDataBean();
	            	review.setArticle_no(rs.getInt("article_no"));
	            	review.setArticle_subject(rs.getString("article_subject"));
	            	review.setPassword(rs.getString("password"));
	            	review.setReg_date(rs.getTimestamp("reg_date"));
	            	review.setArticle_content(rs.getString("article_content"));
	            	
	}
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
		return review;
	    }
	    public int updateArticle(ReviewBoardDataBean review) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        int r=0;
	        try {
	            conn = getConnection();
	           
	    pstmt = conn.prepareStatement(
	            "update review_board set article_subject=?,article_content=? where article_no=?");
	        
	                pstmt.setString(1, review.getArticle_subject());
	                pstmt.setString(2, review.getArticle_content());
	                pstmt.setInt(3, review.getArticle_no());
	       
	                r =  pstmt.executeUpdate();
	                System.out.println(r);
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	        
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }	return r;
	    }
	    public int deleteArticle(int article_no) throws Exception {
	        Connection conn = null;
	        PreparedStatement pstmt = null;
	        int r=0;
	      
	        try {
	        	conn = getConnection();

	            pstmt = conn.prepareStatement("delete from review_board where article_no= ?" );
	                    pstmt.setInt(1, article_no);                                 
	                 r=   pstmt.executeUpdate();
	  
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	          
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }
	return r;
	    }
	    public ReviewBoardDataBean checkpwd(int article_no) throws Exception{
	  	  Connection conn = null;
	        PreparedStatement pstmt = null;
	        ResultSet rs=null;
	        ReviewBoardDataBean review=null;
	        try {
	            conn = getConnection();
	           
	    pstmt = conn.prepareStatement("select * from review_board where article_no=?");
	   
	                pstmt.setInt(1,article_no);
	       
	                rs =  pstmt.executeQuery();
	              if(rs.next()){
	            	  review=new ReviewBoardDataBean();
	            	  review.setArticle_no(rs.getInt("article_no"));
	            	  review.setArticle_subject(rs.getString("article_subject"));
	            	  review.setArticle_content(rs.getString("article_content"));
	            	  review.setPassword(rs.getString("password"));
	              }
	        } catch(Exception ex) {
	            ex.printStackTrace();
	        } finally {
	      	  if(rs != null) try {rs.close();} catch(SQLException ex){}
	            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	        }	
	        
	        return review;
	    }
	   
	    }

