package board_proj.dao;

import java.util.ArrayList;

import board_proj.dto.BoardDto;

public interface BoardDao {
	int selectListCount();
	ArrayList<BoardDto> selectArticleList(int page, int limit);
	BoardDto selectArticle(int board_num);
	int insertArticle(BoardDto article);
	int insertReplyArticle(BoardDto article);
	int updateArticle(BoardDto article);
	int deleteArticle(int board_num);
	int updateReadCount(int board_num);
	boolean isArticleBoardWriter(int board_num, String pass);
	int nextBoardNum(); // 글 번호 알게하는 method
}
