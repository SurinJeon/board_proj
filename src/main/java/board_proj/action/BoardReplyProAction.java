package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardReplyProService;

public class BoardReplyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset = UTF-8");
		int page = Integer.parseInt(request.getParameter("page"));
		BoardDto replyArticle = getArticle(request);
		System.out.println("replyArticle >> " + replyArticle);
		
		BoardReplyProService service = new BoardReplyProService();
		boolean res = service.replyArticle(replyArticle);
		System.out.println("res >> " + res);
		
		ActionForward forward = null;
		
		if(res) { // 성공했다면 리스트로 가게끔
			forward = new ActionForward("boardList.do?page=" + page, true);
		} else { // 실패했다면 알림창
			sendMessage(response, "답글실패");
		}
		
		return forward;
	}

	private BoardDto getArticle(HttpServletRequest request) {
		int page = Integer.parseInt(request.getParameter("page"));
		int BOARD_NUM = Integer.parseInt(request.getParameter("board_num"));
		int BOARD_RE_REF = Integer.parseInt(request.getParameter("board_re_ref"));
		int BOARD_RE_LEV = Integer.parseInt(request.getParameter("board_re_lev"));
		int BOARD_RE_SEQ = Integer.parseInt(request.getParameter("board_re_seq"));

		String BOARD_NAME = request.getParameter("BOARD_NAME");
		String BOARD_PASS = request.getParameter("BOARD_PASS");
		String BOARD_SUBJECT = request.getParameter("BOARD_SUBJECT");
		String BOARD_CONTENT = request.getParameter("BOARD_CONTENT");

		return new BoardDto(BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, "", BOARD_RE_REF,
				BOARD_RE_LEV, BOARD_RE_SEQ, 0, null);
	}

	private void sendMessage(HttpServletResponse response, String msg){
		try {
			response.setContentType("text/html; charset = UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('" + msg + "');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
