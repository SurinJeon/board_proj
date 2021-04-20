package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardDetailService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardDetailService() {
		dao.setCon(con);
	}

	public BoardDto getArticle(int board_num) {
		// 조회수 증가
		// board_num 해당하는 BoardDto return;
		dao.updateReadCount(board_num);
		return dao.selectArticle(board_num);
	}
	
}
