package board_proj.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;
import board_proj.dto.BoardDto;
import board_proj.dto.PageInfo;
import board_proj.service.BoardListService;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int page = 1;
		int limit = 10;
		
		response.setContentType("text/html; charset = UTF-8");
		if(request.getParameter("page") != null) { // page 누른 수가 존재한다면,
			page = Integer.parseInt(request.getParameter("page"));
		}

		BoardListService service = new BoardListService();

		ArrayList<BoardDto> list = service.getArticleList(page, limit);

		// 총 list 개수
		int listCount = service.getListCount();
		
		int maxPage = (int)Math.ceil((double)listCount/limit);
		// 올림으로 처리해야함(나머지 페이지가 남아도 그게 또다른 한 페이지에 담겨야하기 때문)
		
		// 1page 1~5 / 2page 6~10 ...
		// 11page 51~56
		// [이전] [1][2][3][4][5][6][7][8][9][10] [다음]
		// [이전] [11][12][13][14][15][16][17][18][19][20] [다음]
		// startPage는 앞의 1과 11을 찾는 것
		int startPage =(int)Math.floor(page/10) * 10 + 1; // 여기는 limit과 관계 X
		int endPage = startPage + 9;
		
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		PageInfo pageInfo = new PageInfo(page, maxPage, startPage, endPage, listCount);
		
		request.setAttribute("articleList", list);
		request.setAttribute("pageInfo", pageInfo);
		
		ActionForward forward = new ActionForward();
		forward.setPath("/board/qna_board_list.jsp");
		return forward;
	}

}
