package paymentListAction;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.*;
public class DBCPInit extends HttpServlet {
	public void init(ServletConfig config) throws ServletException{
		try{
			String drivers=config.getInitParameter("jdbcdriver");
			StringTokenizer st=new StringTokenizer(drivers, ",");
			while(st.hasMoreTokens()){
				String jdbcDriver = st.nextToken();
				Class.forName(jdbcDriver);
			}
			Class.forName("org.apache.commons.dbcp.PoolingDriver");
		}catch(Exception e){
			throw new ServletException(e);
		}
	}
}