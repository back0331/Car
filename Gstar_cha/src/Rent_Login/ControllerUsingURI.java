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
	//��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����
	//��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties������ �о Map��ü�� commandMap�� ����
	//��ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties������ Command.properties����
	
	public void init(ServletConfig config) throws ServletException{
		String props = config.getInitParameter("propertyConfig");
		//web.xml���� propertyConfig�� �޾ƿ�
		Properties pr = new Properties();
		//��ɾ�� ó��Ŭ������ ���������� ������ properties��ü ����
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(props);
			//Command.properties������ ������ �о��
			pr.load(fis);
			//Command.properties������ ������ Properties��ü�� ����
		}catch(IOException ioe){
			throw new ServletException(ioe);
		}finally{
			if(fis!=null)try{fis.close();}catch(IOException ioe){}
		}
		Iterator keyIter = pr.keySet().iterator();
		//Iterator��ü�� Enumeration��ü�� Ȯ���Ų ����. keySet�޼ҵ尡 key���� ���� �� �ֵ��� �Ѵ�.
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
