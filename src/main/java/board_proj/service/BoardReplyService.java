package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardReplyService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardReplyService() {
		dao.setCon(con);
	}

	public BoardDto getArticle(int board_num) {
		return dao.selectArticle(board_num);
	} // 조회수 증가 안 되는 method
	
}
