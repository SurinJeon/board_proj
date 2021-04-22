package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardModifyService;

public class BoardModifyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		// http://localhost:8080/board_proj/boardModifyForm.do?board_num=20
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
//		System.out.println("board_num >> " + board_num); // debugging
		
		response.setContentType("text/html; charset = UTF-8");
		BoardModifyService service = new BoardModifyService();
		BoardDto article = service.getArticle(board_num);
		
//		System.out.println("article >> " + article); // debugging
		
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_modify.jsp");
		
		return forward;
	}

}
