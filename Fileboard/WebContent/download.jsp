<%@ page pageEncoding="utf-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@page import="pds.service.IncreaseDownloadCountService"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="pds.service.PdsItemNotFoundException"%>
<%@page import="pds.file.FileDownloadHelper"%>
<%@page import="pds.model.PdsItem"%>
<%@page import="pds.service.GetPdsItemService"%>
<%
	int id = Integer.parseInt(request.getParameter("id"));
	try {
		PdsItem item = GetPdsItemService.getInstance().getPdsItem(id);

		// 응답 헤더 다운로드로 설정
		response.reset();
		
		String fileName = new String(item.getFileName().getBytes("utf-8"), 
				"iso-8859-1");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", 
				"attachment; filename=\"" + fileName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setContentLength((int)item.getFileSize());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");

		FileDownloadHelper.copy(item.getRealPath(), 
				response.getOutputStream());
		
		response.getOutputStream().close();
		
		IncreaseDownloadCountService.getInstance().increaseCount(id);
	} catch (PdsItemNotFoundException ex) {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	}
%>