package bean;

import java.sql.*;

import java.util.*;

public class NoticeBoardDBBean {   
    private static NoticeBoardDBBean instance = new NoticeBoardDBBean();
   
    public static NoticeBoardDBBean getInstance() {
        return instance;
    }
   
    private NoticeBoardDBBean() {
    }
   
    private Connection getConnection() throws Exception {
     String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";         
        return DriverManager.getConnection(jdbcDriver);
    }
    
    //writePro.jsp
    public void insertArticle(NoticeBoardDataBean article) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
		
        //답변글인지 일반글인지를 구분해서 입력시켜주는 로직!!!
		
    	try{   	
    		conn=getConnection();
    	    //System.out.println(article.getArticle_content());

            pstmt = conn.prepareStatement(
            		"insert into notice_board(article_no,article_subject,reg_date,article_content) values(board_num.NEXTVAL,?,?,?)");
            pstmt.setString(1, article.getArticle_subject());
            pstmt.setTimestamp(2, article.getReg_date());
            pstmt.setString(3, article.getArticle_content());
           
            pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
   
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
    	
    }
    
    //list.jsp : 페이징을 위해서 전체 DB에 입력된 행의수가 필요하다...!!!
    public int getArticleCount() throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int x=0;

        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement("select count (*) from notice_board");//전체 글 갯수
            rs = pstmt.executeQuery();
 
            if (rs.next()) 
               x= rs.getInt(1);
                                
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
              
        }
        return x;
   
    }

    //list.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
  
   
    public NoticeBoardDataBean getArticle(int article_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NoticeBoardDataBean article=null;
        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(
            "select * from notice_board where article_no = ?");
            pstmt.setInt(1, article_no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	article = new NoticeBoardDataBean();
            	article.setArticle_no(rs.getInt("article_no"));
            	
            	article.setArticle_subject(rs.getString("article_subject"));
            	
            	article.setReg_date(rs.getTimestamp("reg_date"));
            	article.setArticle_content(rs.getString("article_content"));
            
				}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        return article;
    }

    //updateForm.jsp : 수정폼에 한줄의 데이터를 가져올때.
    public NoticeBoardDataBean updateGetArticle(int article_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        NoticeBoardDataBean article=null;
        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(
            "select * from notice_board where article_no = ?");
            pstmt.setInt(1, article_no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	article = new NoticeBoardDataBean();
            	article.setArticle_no(rs.getInt("article_no"));
            	
            	article.setArticle_subject(rs.getString("article_subject"));
            	
            	article.setReg_date(rs.getTimestamp("reg_date"));
               	article.setArticle_content(rs.getString("article_content"));
            	
            	
            }} catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
	return article;
    }

    //updatePro.jsp : 실제 데이터를 수정하는 메소드.
    public int updateArticle(NoticeBoardDataBean article) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int r=0;
        try {
            conn = getConnection();
           
    pstmt = conn.prepareStatement(
            "update notice_board set article_subject=?,article_content=? where article_no=?");
        
                pstmt.setString(1, article.getArticle_subject());
                pstmt.setString(2, article.getArticle_content());
                pstmt.setInt(3, article.getArticle_no());
               //System.out.println(article.getArticle_no());
                r =  pstmt.executeUpdate();
              
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
        
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }	return r;
    }
   
    //deletePro.jsp : 실제 데이터를 삭제하는 메소드...
    public int deleteArticle(int article_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int r=0;
      
        try {
        	conn = getConnection();

            pstmt = conn.prepareStatement("delete from notice_board where article_no= ?" );
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
    public int getArticleCount(int n, String searchKeyword) throws Exception{
    	
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	
    	String[] column_name = {"article_subject","article_content"};
		
		int x = 0;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count (*) from notice_board where "+column_name[n]+" like '%"+searchKeyword+"%'");
			
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
    public List getArticles(int start, int end) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List articleList=null;
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            		   "select article_no,article_subject,reg_date,article_content,r "
            		   + "from (select article_no,article_subject,reg_date,article_content,rownum r "
            		   + "from notice_board order by article_no desc) where r >= ? and r<=?");
            pstmt.setInt(1, start);
    		pstmt.setInt(2, end);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                articleList = new ArrayList(end);
               do{
                  NoticeBoardDataBean article= new NoticeBoardDataBean();
                  article.setArticle_no(rs.getInt("article_no"));
                  article.setArticle_subject(rs.getString("article_subject"));
                  article.setReg_date(rs.getTimestamp("reg_date"));
 				  article.setArticle_content(rs.getString("article_content"));
  				  

                  articleList.add(article);
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
    public List getArticles(int start, int end, int n, String searchKeyword) throws Exception{
    	Connection conn=null;
    	PreparedStatement pstmt=null;
    	ResultSet rs=null;
    	List articleList=null;
    	
	String[] column_name = {"article_subject","article_content"};
		
		try
		{
			conn = getConnection();
			
			String sql = "select article_no,article_subject,reg_date,article_content,r "
					+"from (select article_no,article_subject,reg_date,article_content, rownum r "
					+"from notice_board where "+column_name[n]+" like '%"+searchKeyword+"%') where r >= ? and r <= ?";
			
    		pstmt=conn.prepareStatement(sql);
    		pstmt.setInt(1,start);
    		pstmt.setInt(2, end);   		
    		rs=pstmt.executeQuery();
    		
    		if(rs.next())
    		{
    			articleList=new ArrayList(end);
    			
    			do{
    					NoticeBoardDataBean article= new NoticeBoardDataBean();
    					article.setArticle_no(rs.getInt("article_no"));
    					
    					article.setArticle_subject(rs.getString("article_subject"));
    					
    					article.setReg_date(rs.getTimestamp("reg_date"));
    	              	article.setArticle_content(rs.getString("article_content"));
    	            	
    					articleList.add(article);
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
  public NoticeBoardDataBean checkpwd(int article_no) throws Exception{
	  Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs=null;
      NoticeBoardDataBean article=null;
      try {
          conn = getConnection();   
         
  pstmt = conn.prepareStatement("select password from qna_board where article_no=?");
 
              pstmt.setInt(1,article_no);
     
              rs =  pstmt.executeQuery();
            if(rs.next()){
            	article=new NoticeBoardDataBean();
            
            }
      } catch(Exception ex) {
          ex.printStackTrace();
      } finally {
    	  if(rs != null) try {rs.close();} catch(SQLException ex){}
          if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
          if (conn != null) try { conn.close(); } catch(SQLException ex) {}
      }	
      return article;
  }
}

    		
    	
   
