package paymentAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import paymentDTO.PayListDBBean;

public class InsertImp_uidAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String imp_uid = "";
		int book_no = 0;
		int check = 0;
		imp_uid=request.getParameter("imp_uid");
		book_no=Integer.parseInt(request.getParameter("book_no"));
		PayListDBBean pldbb = PayListDBBean.getInstance();
		System.out.println("imp_uid:::"+imp_uid+", book_no:::"+book_no);
		try{
			System.out.println("imp_uid:::"+imp_uid);
			check = pldbb.insertImp_uid(imp_uid, book_no);
			if(check==0){
				return "/Payment/Fail.jsp";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "/Payment/InsertSuccess.jsp";
	}

}
