package controller;
  
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties; 

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.CommandAction;
     
public class ControllerUsingURI extends HttpServlet {
	private Map commandMap=new HashMap();
	
	public void init(ServletConfig config) throws ServletException{
		String props=config.getInitParameter("propertyConfig");
		
		Properties pr= new Properties();
		FileInputStream f= null;
		try{
			f= new FileInputStream(props);
			pr.load(f);//8개의 properties 저장
			
		}catch(IOException e){
			throw new ServletException(e);
		}finally{
			if(f!=null) try{ f.close();}catch(IOException ex){}
			
		}
		Iterator keyIter=pr.keySet().iterator();
		while(keyIter.hasNext()){
			String command = (String)keyIter.next();
			String className=pr.getProperty(command);
			try{
				Class commandClass = Class.forName(className);//클래스로만들고
			    Object commandInstance= commandClass.newInstance();//객체로 만들고
			    commandMap.put(command, commandInstance);//해당 키와 값을 map객체에 저장
			}catch(ClassNotFoundException e){
				throw new ServletException(e);
			}catch(InstantiationException e){
				throw new ServletException(e);
			}catch(IllegalAccessException e){
				throw new ServletException(e);
			}
		}
	}
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException{
		requestPro(request,response);
	}
	//폼태그에 post로 지정한 경우만 post방식, 그외는 get방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException,IOException{
		requestPro(request,response);
	}
	private void requestPro(HttpServletRequest request,HttpServletResponse response)
	throws ServletException,IOException{
		String view=null;
		CommandAction com=null;
		try{   
			String command=request.getRequestURI();
			if(command.indexOf(request.getContextPath())==0){
				command=command.substring(request.getContextPath().length());
			}
			com=(CommandAction) commandMap.get(command);
			view=com.requestPro(request,response);
		}catch(Throwable e){
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher(view);
		//경로를 저장하고있는 RequestDispatcher객체 생성
		dispatcher.forward(request,response);
	}

} 
