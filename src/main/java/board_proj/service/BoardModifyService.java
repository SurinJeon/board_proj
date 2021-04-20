package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardModifyService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardModifyService() {
		dao.setCon(con);
	}

	// detail거 그대로 쓰려다가, 조회수 증가 부분 있어서 그거 빼고 그냥 파일 복붙함 
	public BoardDto getArticle(int board_num) {
		return dao.selectArticle(board_num);
	}
	
}
