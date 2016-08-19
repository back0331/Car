package bean;

import java.sql.*;
import java.util.*;

public class QnABoardDBBean {   
    private static QnABoardDBBean instance = new QnABoardDBBean();
   
    public static QnABoardDBBean getInstance() {
        return instance;
    }
   
    private QnABoardDBBean() {
    }
   
    private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
    
    //writePro.jsp
    public void insertArticle(QnABoardDataBean qna) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rs = null;

       	String sql="";

        try {
            conn = getConnection();
          
            // 쿼리를 작성
            sql = "insert into qna_board(article_no,id,article_subject,password,reg_date,";
            sql+="article_content,ip,article_type,com_check) values(seq_qna_board_article_no.NEXTVAL,?,?,?,?,?,?,?,'n')";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, qna.getId());
            pstmt.setString(2, qna.getArticle_subject());
            pstmt.setString(3, qna.getPassword());
            pstmt.setTimestamp(4, qna.getReg_date());
             pstmt.setString(5, qna.getArticle_content());
            pstmt.setString(6, qna.getIp());
            pstmt.setString(7, qna.getArticle_type());
            pstmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
    if (rs != null) try { rs.close(); } catch(SQLException ex) {}
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
           
            pstmt = conn.prepareStatement("select count(*) from qna_board");//전체 글 갯수
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
        }
        System.out.println("x::::"+x);
        return x;
    }

    //list.jsp ==> Paging!!! DB로부터 여러행을 결과로 받는다.
    public List getArticles(int start, int end) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List articleList=null;
        try {
            conn = getConnection();
           
            pstmt = conn.prepareStatement(
            "select article_no,id,article_subject,password,reg_date,article_content,ip,article_type,com_check, r  " +
            "from (select article_no,id,article_subject,password,reg_date,article_content,ip,article_type,com_check, rownum r"
            + " from qna_board order by article_no desc) where r >= ? and r <= ? ");
            pstmt.setInt(1, start);
    		pstmt.setInt(2, end);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                articleList = new ArrayList(end);
                do{
                  QnABoardDataBean qna= new QnABoardDataBean();
  				  qna.setArticle_no(rs.getInt("article_no"));
  				  qna.setId(rs.getString("id"));
                  qna.setArticle_subject(rs.getString("article_subject"));
                  qna.setPassword(rs.getString("password"));
                  qna.setReg_date(rs.getTimestamp("reg_date"));
 				  qna.setArticle_type(rs.getString("article_type"));
                  qna.setArticle_content(rs.getString("article_content"));
  				  qna.setIp(rs.getString("ip"));
  				  qna.setCom_check(rs.getString("com_check"));
 
                  articleList.add(qna);
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

    //read.jsp : DB로부터 한줄의 데이터를 가져온다.
    public QnABoardDataBean getArticle(int article_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        QnABoardDataBean qna=null;
        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(
            "select * from qna_board where article_no = ?");
            pstmt.setInt(1, article_no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	qna = new QnABoardDataBean();
            	qna.setArticle_no(rs.getInt("article_no"));
            	qna.setId(rs.getString("id"));
            	qna.setArticle_subject(rs.getString("article_subject"));
            	qna.setPassword(rs.getString("password"));
            	qna.setReg_date(rs.getTimestamp("reg_date"));
               	qna.setArticle_content(rs.getString("article_content"));
            	qna.setIp(rs.getString("ip"));
            	qna.setArticle_type(rs.getString("article_type"));
            	qna.setCom_check(rs.getString("com_check"));
            	
            	
				}
            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
        System.out.println(qna);
		return qna;
    }

    //updateForm.jsp : 수정폼에 한줄의 데이터를 가져올때.
    public QnABoardDataBean updateGetArticle(int article_no) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        QnABoardDataBean qna=null;
        try {
            conn = getConnection();

            pstmt = conn.prepareStatement(
            "select * from qna_board where article_no = ?");
            pstmt.setInt(1, article_no);
            rs = pstmt.executeQuery();

            if (rs.next()) {
            	qna = new QnABoardDataBean();
            	qna.setArticle_no(rs.getInt("article_no"));
            	qna.setId(rs.getString("id"));
            	qna.setArticle_subject(rs.getString("article_subject"));
            	qna.setPassword(rs.getString("password"));
            	qna.setReg_date(rs.getTimestamp("reg_date"));
            	qna.setArticle_content(rs.getString("article_content"));
            	qna.setIp(rs.getString("ip"));
            	qna.setArticle_type(rs.getString("article_type"));
            	qna.setCom_check(rs.getString("com_check"));
}
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            if (rs != null) try { rs.close(); } catch(SQLException ex) {}
            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
        }
	return qna;
    }

    //updatePro.jsp : 실제 데이터를 수정하는 메소드.
    public int updateArticle(QnABoardDataBean qna) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        int r=0;
        try {
            conn = getConnection();
           
    pstmt = conn.prepareStatement(
            "update qna_board set article_subject=?,article_content=? where article_no=?");
        
                pstmt.setString(1, qna.getArticle_subject());
                pstmt.setString(2, qna.getArticle_content());
                pstmt.setInt(3, qna.getArticle_no());
       
                r =  pstmt.executeUpdate();
                System.out.println(r);
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
        ResultSet rs=null;
        int r=0;
      
        try {
        	conn = getConnection();
        	pstmt=conn.prepareStatement("select com_check from qna_board where article_no=?");
        	pstmt.setInt(1, article_no);
        	rs=pstmt.executeQuery();
        	if(rs.next()){
        		pstmt=conn.prepareStatement("delete from qna_comment where article_no=?");
        		pstmt.setInt(1, article_no);
        		pstmt.executeUpdate();
        	}
            pstmt = conn.prepareStatement("delete from qna_board where article_no= ?" );
                    pstmt.setInt(1, article_no);                                 
            r=   pstmt.executeUpdate();
            System.out.println("r:::"+r);
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
    	
    	String[] column_name = {"id","article_subject","article_content"};
		
		int x = 0;
		
		try
		{
			conn = getConnection();
			pstmt = conn.prepareStatement("select count (*) from qna_board where "+column_name[n]+" like '%"+searchKeyword+"%'");
			
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
			
			String sql = "select article_no,id,article_subject,password,reg_date,article_content,ip,article_type,com_check, r "	
						+ "from (select article_no,id,article_subject,password,reg_date,article_content,ip,article_type,com_check, rownum r "
						+"from qna_board where "+column_name[n]+" like '%"+searchKeyword+"%') where r >= ? and r <= ?";
			
    		pstmt=conn.prepareStatement(sql);
    		pstmt.setInt(1,start);
    		pstmt.setInt(2, end);
    		
    		rs=pstmt.executeQuery();
    		
    		if(rs.next())
    		{
    			articleList=new ArrayList(end);
    			
    			do{
    					QnABoardDataBean qna= new QnABoardDataBean();
    				  	qna.setArticle_no(rs.getInt("article_no"));
    	            	qna.setId(rs.getString("id"));
    	            	qna.setArticle_subject(rs.getString("article_subject"));
    	            	qna.setPassword(rs.getString("password"));
    	            	qna.setReg_date(rs.getTimestamp("reg_date"));
    	                qna.setArticle_content(rs.getString("article_content"));
    	            	qna.setIp(rs.getString("ip"));
    	            	qna.setArticle_type(rs.getString("article_type"));    
    					qna.setCom_check(rs.getString("com_check"));
    					
    					articleList.add(qna);
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
  public QnABoardDataBean checkpwd(int article_no) throws Exception{
	  Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs=null;
      QnABoardDataBean qna=null;
      try {
          conn = getConnection();
         
  pstmt = conn.prepareStatement("select password from qna_board where article_no=?");
 
              pstmt.setInt(1,article_no);
     
              rs =  pstmt.executeQuery();
            if(rs.next()){
            	qna=new QnABoardDataBean();
            	qna.setPassword(rs.getString("password"));
            }
      } catch(Exception ex) {
          ex.printStackTrace();
      } finally {
    	  if(rs != null) try {rs.close();} catch(SQLException ex){}
          if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
          if (conn != null) try { conn.close(); } catch(SQLException ex) {}
      }	
      return qna;
  }
}

    		
    	
   
