package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;

public class BoardDeleteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset = UTF-8");
		
		String nowPage = request.getParameter("page");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		
//		System.out.println(nowPage + ", " + board_num);
		request.setAttribute("page", nowPage);
		request.setAttribute("board_num", board_num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_delete.jsp");
		
		return forward;
	}

}
