package bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import jdbc.JdbcUtil;

public class QnACommentDBBean {
		
	private static QnACommentDBBean instance=new QnACommentDBBean();
	
	public static QnACommentDBBean getInstance(){
		return instance;
	}
	
	private QnACommentDBBean(){}
	
	private Connection getConnection() throws Exception{	// 해당클래스 안에서만 사용하기 위해 private설정.
		String jdbcDriver = "jdbc:apache:commons:dbcp:/pool";
		return DriverManager.getConnection(jdbcDriver);
	}
	
	public void insertComment(QnACommentDataBean cdb) throws Exception{
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		try{
			conn=getConnection();
			String sql="insert into qna_comment values(?,?,?,?)";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cdb.getComment_no());
			pstmt.setInt(2, cdb.getArticle_no());
			pstmt.setString(3, cdb.getComment_content());
			pstmt.setTimestamp(4, cdb.getReg_date());
			pstmt.executeUpdate();
			pstmt=conn.prepareStatement("update qna_board set com_check='y' where article_no=?");
			pstmt.setInt(1, cdb.getArticle_no());
			pstmt.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	
	public ArrayList getComments(int article_no)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList cm=null;
		try{
			conn=getConnection();
			String sql="select * from qna_comment where article_no="+article_no+" order by reg_date desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cm=new ArrayList();
				do{
					QnACommentDataBean cdb=new QnACommentDataBean();
					cdb.setComment_no(rs.getInt("comment_no"));
					cdb.setArticle_no(rs.getInt("article_no"));
					cdb.setComment_content(rs.getString("comment_content"));
					cdb.setReg_date(rs.getTimestamp("reg_date"));
					cm.add(cdb);
				}while(rs.next());
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return cm;
	}
	
	public int getCommentCount(int article_no)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList cm=null;
		int count=0;
		
		try{
			conn=getConnection();
			String sql="select * from qna_comment where article_no="+article_no+" order by reg_date desc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				cm=new ArrayList();
				do{
					QnACommentDataBean cdb=new QnACommentDataBean();
					cdb.setComment_content(rs.getString("comment_content"));
					cdb.setReg_date(rs.getTimestamp("reg_date"));
					cm.add(cdb);
				}while(rs.next());
				count=cm.size();
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
		return count;
	}
	
	public int deleteComment(int comment_no,int article_no)throws Exception{
		Connection conn=null;
		PreparedStatement pstmt=null;
		int r=0;
		
		try{
			conn=getConnection();
			pstmt=conn.prepareStatement("delete from qna_comment where comment_no=? and article_no=?");
			pstmt.setInt(1, comment_no);
			pstmt.setInt(2, article_no);
			r=pstmt.executeUpdate();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	return r;
	}
}