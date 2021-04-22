package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.service.BoardDeleteService;

public class BoardDeleteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// boardDeletePro.do?board_num = 22
		// hiddenpage = 1
		// BOARD_PASS = 1111
		response.setContentType("text/html; charset = UTF-8");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		String page = request.getParameter("page");
		String pass = request.getParameter("BOARD_PASS");
		BoardDeleteService service = new BoardDeleteService();
		ActionForward forward = null;

		boolean isArticleWriter = service.isArticleWriter(board_num, pass); // 작성자 일치여부 확인

		if (!isArticleWriter) { // 작성자가 아니므로 삭제 실패임
			sendMessage(response, "삭제할 권한이 없습니다.");
			return forward;
		}

		boolean isDeleteSuccess = service.removeArticle(board_num);

		if (!isDeleteSuccess) {
			sendMessage(response, "삭제실패");
			return forward;
		}

		// 책이랑 코드 다름 (중간중간 return해서 다 통과해오면 밑의 문장 수행하게끔)
		forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("boardList.do?page=" + page);
		return forward;

	}

	private void sendMessage(HttpServletResponse response, String msg) {
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
