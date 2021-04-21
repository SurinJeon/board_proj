package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;

public class BoardWriteFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset = UTF-8");
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_write.jsp"); // forwarding
		return forward;
	}

}
