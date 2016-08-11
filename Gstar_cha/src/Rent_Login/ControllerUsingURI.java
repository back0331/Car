package Rent_Login;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ControllerUsingURI extends HttpServlet {

	private Map commandMap = new HashMap();
	//명령어와 명령어 처리 클래스를 쌍으로 저장
	//명령어와 처리클래스가 매핑되어 있는 properties파일을 읽어서 Map객체인 commandMap에 저장
	//명령어와 처리클래스가 매핑되어 있는 properties파일은 Command.properties파일
	
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("propertyConfig");
		//web.xml에서 propertyConfig값 받아옴
		Properties pr = new Properties();
		//명령어와 처리클래스의 매핑정보를 저장할 properties객체 생성
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(props);
			//Command.properties파일의 내용을 읽어옴
			pr.load(fis);
			//Command.properties파일의 정보를 Properties객체에 저장
		}catch(IOException ioe){
			throw new ServletException(ioe);
		}finally{
			if(fis!=null)try{fis.close();}catch(IOException ioe){}
		}
		Iterator keyIter = pr.keySet().iterator();
		//Iterator객체는 Enumeration객체를 확장시킨 개념. keySet메소드가 key값만 꺼낼 수 있도록 한다.
		while(keyIter.hasNext()){
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);
			try{
				Class commandClass = Class.forName(className);
				Object commandInstance = commandClass.newInstance();
				commandMap.put(command, commandInstance);
			}catch(ClassNotFoundException cnfe){
				throw new ServletException(cnfe);
			}catch(InstantiationException ie){
				throw new ServletException(ie);
			}catch(IllegalAccessException iae){
				throw new ServletException(iae);
			}
		}
	}  
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		requestPro(request, response);
	}
	
	public void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view = null;  
		CommandAction com = null;
		try{
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0){
				command = command.substring(request.getContextPath().length());
			}
			com = (CommandAction)commandMap.get(command);
			
			System.out.println(command);
			
			view = com.execute(request, response);
		}catch(Throwable e){
			throw new ServletException(e);
		}
		System.out.println(view);
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}
