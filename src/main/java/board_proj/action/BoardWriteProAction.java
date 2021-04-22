package board_proj.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.service.BoardWriteService;

public class BoardWriteProAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		response.setContentType("text/html; charset = UTF-8");
		String realFolder = "";
		String saveFolder = "/boardUpload";
		int fileSize = 5 * 1024 * 1024; // 5M
		
		ServletContext context = request.getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		
		
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(
					request, 
					realFolder, 
					fileSize, 
					"utf-8", 
					new DefaultFileRenamePolicy());
		} catch (IOException e) {
			e.printStackTrace();
		}
		BoardDto boardDto = new BoardDto();
		boardDto.setBoard_name(multi.getParameter("BOARD_NAME"));
		boardDto.setBoard_pass(multi.getParameter("BOARD_PASS"));
		boardDto.setBoard_subject(multi.getParameter("BOARD_SUBJECT"));
		boardDto.setBoard_content(multi.getParameter("BOARD_CONTENT"));
		boardDto.setBoard_file(multi.getOriginalFileName((String)multi.getFileNames().nextElement()));
		
		System.out.println("realFolder >> " + realFolder);
		System.out.println("boardDto >> " + boardDto);
		
		// service필요
		BoardWriteService service = new BoardWriteService();
		boolean result = service.registerArticle(boardDto);
		
		ActionForward forward = null;
		if(result) { // 성공했을 경우
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath("boardList.do");
		} else {
			sendMessage(response);
		}

		return forward;
	}

	private void sendMessage(HttpServletResponse response) {
		try {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('등록실패')");
			out.println("history.back()");
			out.println("</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
