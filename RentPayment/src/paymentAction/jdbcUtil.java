package paymentAction;
import java.sql.*;
public class jdbcUtil {
	public static void close(ResultSet rs){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException sqle){}
		}
	}
	
	public static void close(Statement stmt){
		if(stmt!=null){
			try{
				stmt.close();
			}catch(SQLException sqle){}
		}
	}

	public static void close(Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException sqle){}
		}
	}

	public static void rollback(Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException sqle){}
		}
	}
}
//connectionǮ���� ���� connection�� ���� ������ �ϴ� �޼ҵ���� ��Ƴ������̴�.