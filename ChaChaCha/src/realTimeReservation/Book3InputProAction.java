package realTimeReservation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookDBBean;
import bean.BookDataBean;
import action.CommandAction;

public class Book3InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		
		String id = (String)request.getSession().getAttribute("userId");
		//String id = "test";
		String car_id = request.getParameter("car_id");
		String rent_date = request.getParameter("rent_date");
		String return_date = request.getParameter("return_date");
		int total_price = Integer.parseInt(request.getParameter("total_price"));
		String carOwner = request.getParameter("carOwner");
		
		BookDataBean book = new BookDataBean();
		book.setId(id);
		book.setCar_id(car_id);
		book.setCarOwner(carOwner);
		book.setRent_date(rent_date);
		book.setReturn_date(return_date);
		book.setTotal_price(total_price);
		book.setState("0");
		
		BookDBBean bookDb = BookDBBean.getInstance();
		int result = bookDb.insertBook(book);
		 
		request.setAttribute("result", result);
		
		return "book3InputPro.jsp";
	}

}
