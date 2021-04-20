package board_proj.service;

import java.sql.Connection;
import java.util.ArrayList;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.ds.JndiDS;
import board_proj.dto.BoardDto;

public class BoardListService {
	private BoardDaoImpl dao = BoardDaoImpl.getInstance();
	private Connection con = JndiDS.getConnection();
	
	public BoardListService() {
		dao.setCon(con);
	}

	public int getListCount() {
		return dao.selectListCount();
	}
	
	public ArrayList<BoardDto> getArticleList(int page, int limit){
		return dao.selectArticleList(page, limit);
	}
	
}
