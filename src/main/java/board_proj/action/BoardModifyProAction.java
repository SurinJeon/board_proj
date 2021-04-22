package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardModifyProService;

public class BoardModifyProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		
		ActionForward forward = null;
		response.setContentType("text/html; charset = UTF-8");
		boolean isModifySuccess = false;
		
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String pass = request.getParameter("board_pass");
		String page = request.getParameter("page");
		
		System.out.println("pass >> " + pass);
		System.out.println("page >> " + page);
		
		BoardDto article = new BoardDto();
		
		BoardModifyProService service = new BoardModifyProService();
		
		boolean isArticleWriter = service.isArticleWriter(board_num, pass);
		
		
		if(!isArticleWriter) {
			sendMessage(response, "수정할 권한이 없습니다.");
			return forward;
		}
		
		article.setBoard_num(board_num);
		article.setBoard_name(request.getParameter("board_name"));
		article.setBoard_pass(pass);
		article.setBoard_subject(request.getParameter("board_subject"));
		article.setBoard_content(request.getParameter("board_content").replaceAll("\r\n", "<br>"));
		article.setBoard_file(request.getParameter("board_file"));
		
		isModifySuccess = service.modifyArticle(article);
		System.out.println("content >> " + request.getParameter("board_content"));
		
		if(!isModifySuccess) {
			sendMessage(response, "수정실패");
			return forward;
		}
		
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("boardDetail.do?board_num=" + article.getBoard_num() + "&page=" + page);
		
		return forward;
	}

	private void sendMessage(HttpServletResponse response, String msg){
		try {
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
