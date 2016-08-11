package mvc.controller2;

import java.util.Map;
import java.util.Properties;
import java.net.URL;
import java.util.HashMap;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSeparatorUI;

import actionCar.CommandAction;


public class ControllerUsingURI1 extends HttpServlet {

	private Map commandMap = new HashMap(); // ��ɾ�� ��ɾ� ó�� Ŭ������ ������ ����

	
	
	
	public void init(ServletConfig config) throws ServletException{		//�Ű����� config�� ��ó�� ����??
		
	/*	[ServletConfig, ServletContext] (Servlet ��ü�� ���� ����)
		- ServletConfig
		Servlet ��ü�� ���ϴµ� �ʿ��� ������ ������ �ִ� ��ü
		WebContainer�� �����Ͽ� Servlet ��ü init() ȣ�� �� ����
		Servlet ��ü�� �ϳ��� �����ȴ�.
		- ServletContext
		WebApplication�� ���ϴµ� �ʿ��� ������ ������ �ִ� ��ü
		WebApplication�� ó�� ����Ǵ� ������ WebContainer�� ����
		Servlet�� getServletContext()�� ���� ����
		WebApplication�� �ϳ��� �����ȴ�.
		[��ó] 6. ServletConfig, ServletContext (Servlet ��ü�� ���� ����)|�ۼ��� ����*/


		
		
		String props = config.getInitParameter("propertyConfig");	//property �³׵� �Ķ���ͷ� �����´�...?
																							//
		Properties pr=new Properties();			//name value �����س����ϱ�.
																//properties�� �Ӽ������� name value ������ �����ϴ� ������ Ŭ�����̴�. 
		
		
		FileInputStream f = null;
												//java������ �ƴϴϱ� Ŭ������ �ƴ϶� ~ properties�� ���Ͽ� �����Ѵ�.
		
		try{
			f = new FileInputStream(props);		
																	//�׳� Ŭ���� ���� ���������� �������� �ȵ�? v ���� ���� ����.
			
			pr.load(f);									//��Ʈ�� �����ؼ� ���Ϸε�...pr��.
		}catch(IOException e){
			throw new ServletException(e);
		}finally{
			if(f!=null) try{f.close();}catch(IOException ex){}
		}
		Iterator keyIter=pr.keySet().iterator();
			/*Iterator�� �ݺ��ڶ�� �ؼ��� �Ǵµ�, �޼��尡 3���̴�.
				hasNext(), next(), remove()*/
/*			���÷��� ���� �ִ� iterator()��
				List�������̽��� ������ collection���� get�޼ҵ�� ����� �����͸� �о�ü��յ�.
					������ List�������̽��� ������ �÷��ǵ��� ��������� �����Ǳ⶧��.
				������ SEt�������̽��� ������ �÷��ǵ��� get�޼ҵ尡 ����.?
					���� arraylist�÷����� ����ϴ� HashSet�÷������� ��ü�ؾ��� �ʿ伺�� ����� �����͸� �������� �ڵ���� ��� �����ؾ��Ѵ�.
				������ �ݺ��ڴ� ��� �÷����� �ݺ����̴� ������ �����ϴ�.
					���� �ݺ��ڸ� �̿��ؼ� ������ �س��Ҵٸ� �÷����� ��ü�� �� �����͸� �������� �ڵ�� �������� �ʾƵ� �ǹǷ� ���������ð� �ٿ��ش�. */
					
		while(keyIter.hasNext()){									/*Ű���� �����´�..?*/
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);			/*key���� ~~~.do�̰�, value���� property���̳�!! URI����.*/
			try{
				Class commandClass= Class.forName(className);		//�׳� ���ڴϱ�. class�� ������ְ�.
				Object commandInstance=commandClass.newInstance();	//�� Ŭ������ ��üȭ �����ְ�.
				commandMap.put(command, commandInstance);			//�ٽ� Map ��ü���ٰ� �ִ� ��. ---key���� value(��üȭ�� Ŭ�������.)
			}catch(ClassNotFoundException e){
				throw new ServletException(e);
			}catch(InstantiationException e){
				throw new ServletException(e);
			}catch(IllegalAccessException e){
				throw new ServletException(e);
			}
		}
	}//������� init �޼���
	
	
	
	
	
	
	
	
	
	
	
	
									//get���. post���. �����ʿ�
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		requestPro(request, response);
	}

	
	
	
	
	
	//request response �ٽ� ����  �ڡڡڡ�
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String view = null;
	CommandAction com = null;
		try{
			
					System.out.println(commandMap);
			
			
			String command = request.getRequestURI();	//��û���� URI�����ͼ� command �� �־���.
					System.out.println(command);				//			/Before/MVC/list.do
			
			if(command.indexOf(request.getContextPath())==0){					//�� URI�� contextpath �� �����ϴ� index�� ���ϴµ� 0�̸�
																											/*��, contextpath���� �����ϸ�.*/
																								//contextPath = /Before �̴�.!!!!
				
				command = command.substring(request.getContextPath().length());	 //contextPath ���� ���İ��� �߶�. command�� �ٽ�����.
					System.out.println(command);		//			/MVC/list.do
			}
			com = (CommandAction)commandMap.get(command);		//commandAction�� �������̽��ε�,
			System.out.println(com);			//			action.ListAction@641d6fb0
														//list.do�� ����� ���� action.ListAction�̹Ƿ�! 
				System.out.println(request);
				System.out.println(response+"������� �����.");
			view=com.requestPro(request,response);				//���̰�������?? �������̽��� �޼��� ����....v		��Ʈ�ѷ����� ���� �ҷ��� �����Ų������ ���Ϲ޾ƿ���
			System.out.println(response+"ȣ��?.");																//==> action.ListAction �ȿ��ִ� requestPro() �޼��� �����Ų��.
																					//�� ���ϰ��� �������µ� �� ���� /MVC/list.jsp   !!!!
					
					System.out.println(view);		//			/MVC/list.jsp
		}catch(Throwable e){
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);			//�信�� �Ѱ��ִ°�!!! request���� ���� �Ѱ��༭ �����ְ�!!!
		dispatcher.forward(request, response);

	}
	
	
}
