package board_proj.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardReplyService;

public class BoardReplyFormAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset = UTF-8");
		
		ActionForward forward = new ActionForward();

		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");

		BoardReplyService service = new BoardReplyService();
		BoardDto article = service.getArticle(board_num);
		
		/* debugging용 sysout */
//		System.out.println("board_num >> " + board_num);
//		System.out.println("page >> " + page);
//		System.out.println("article >> " + article);
		
		request.setAttribute("article", article);
		request.setAttribute("page", page);
		forward.setPath("/board/qna_board_reply.jsp");
		return forward;
	}

}
