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

	private Map commandMap = new HashMap(); // 명령어와 명령어 처리 클래스를 쌍으로 저장

	
	
	
	public void init(ServletConfig config) throws ServletException{		//매개변수 config는 출처가 어디야??
		
	/*	[ServletConfig, ServletContext] (Servlet 객체에 대한 설정)
		- ServletConfig
		Servlet 객체가 일하는데 필요한 정보를 가지고 있는 객체
		WebContainer가 생성하여 Servlet 객체 init() 호출 시 주입
		Servlet 객체당 하나씩 생성된다.
		- ServletContext
		WebApplication이 일하는데 필요한 정보를 가지고 있는 객체
		WebApplication이 처음 실행되는 시점에 WebContainer가 생성
		Servlet의 getServletContext()를 통해 참조
		WebApplication당 하나씩 생성된다.
		[출처] 6. ServletConfig, ServletContext (Servlet 객체에 대한 설정)|작성자 나라*/


		
		
		String props = config.getInitParameter("propertyConfig");	//property 걔네들 파라미터로 가져온다...?
																							//
		Properties pr=new Properties();			//name value 저장해놨으니깐.
																//properties는 속성값들을 name value 식으로 저장하는 형태의 클래스이다. 
		
		
		FileInputStream f = null;
												//java파일이 아니니깐 클래스가 아니라서 ~ properties의 파일에 연결한다.
		
		try{
			f = new FileInputStream(props);		
																	//그냥 클래스 변수 가져오듯이 가져오면 안됨? v 위에 답이 있음.
			
			pr.load(f);									//스트림 연결해서 파일로딩...pr로.
		}catch(IOException e){
			throw new ServletException(e);
		}finally{
			if(f!=null) try{f.close();}catch(IOException ex){}
		}
		Iterator keyIter=pr.keySet().iterator();
			/*Iterator는 반복자라고 해석이 되는데, 메서드가 3개이다.
				hasNext(), next(), remove()*/
/*			★컬렉션 마다 있는 iterator()★
				List인터페이스를 구현한 collection들은 get메소드로 저장된 데이터를 읽어올수잇따.
					이유는 List인터페이스를 구현한 컬렉션들은 저장순서가 유지되기때문.
				하지만 SEt인터페이스를 구현한 컬렉션들은 get메소드가 없다.?
					만약 arraylist컬렉션을 사용하닥 HashSet컬렉션으로 교체해야할 필요성이 생기면 데이터를 가져오는 코드들을 모두 수정해야한다.
				하지만 반복자는 어느 컬렉션의 반복자이던 사용법이 동일하다.
					따라서 반복자를 이용해서 구현을 해놓았다면 컬렉션을 교체할 때 데이터를 가져오는 코드는 수정하지 않아도 되므로 유지보수시간 줄여준다. */
					
		while(keyIter.hasNext()){									/*키값들 가져온다..?*/
			String command = (String)keyIter.next();
			String className = pr.getProperty(command);			/*key값이 ~~~.do이고, value값이 property값이네!! URI값들.*/
			try{
				Class commandClass= Class.forName(className);		//그냥 글자니깐. class로 취급해주고.
				Object commandInstance=commandClass.newInstance();	//그 클래스들 객체화 시켜주고.
				commandMap.put(command, commandInstance);			//다시 Map 객체에다가 넣는 것. ---key값과 value(객체화된 클래스들로.)
			}catch(ClassNotFoundException e){
				throw new ServletException(e);
			}catch(InstantiationException e){
				throw new ServletException(e);
			}catch(IllegalAccessException e){
				throw new ServletException(e);
			}
		}
	}//여기까지 init 메서드
	
	
	
	
	
	
	
	
	
	
	
	
									//get방식. post방식. 정리필요
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		requestPro(request, response);
	}

	
	
	
	
	
	//request response 다시 정리  ★★★★
	
	private void requestPro(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException{
		String view = null;
	CommandAction com = null;
		try{
			
					System.out.println(commandMap);
			
			
			String command = request.getRequestURI();	//요청받은 URI가져와서 command 에 넣었음.
					System.out.println(command);				//			/Before/MVC/list.do
			
			if(command.indexOf(request.getContextPath())==0){					//그 URI의 contextpath 로 시작하는 index를 구하는데 0이면
																											/*즉, contextpath부터 시작하면.*/
																								//contextPath = /Before 이다.!!!!
				
				command = command.substring(request.getContextPath().length());	 //contextPath 길이 이후것은 잘라서. command에 다시저장.
					System.out.println(command);		//			/MVC/list.do
			}
			com = (CommandAction)commandMap.get(command);		//commandAction이 인터페이스인데,
			System.out.println(com);			//			action.ListAction@641d6fb0
														//list.do의 연결된 값은 action.ListAction이므로! 
				System.out.println(request);
				System.out.println(response+"여기까진 실행됨.");
			view=com.requestPro(request,response);				//★이과정뭐지?? 인터페이스의 메서드 실행....v		컨트롤러에서 모델을 불러서 실행시킨다음에 리턴받아오면
			System.out.println(response+"호잇?.");																//==> action.ListAction 안에있는 requestPro() 메서드 실행시킨다.
																					//그 리턴값을 가져오는데 그 값이 /MVC/list.jsp   !!!!
					
					System.out.println(view);		//			/MVC/list.jsp
		}catch(Throwable e){
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);			//뷰에다 넘겨주는것!!! request값도 같이 넘겨줘서 쓸수있게!!!
		dispatcher.forward(request, response);

	}
	
	
}
