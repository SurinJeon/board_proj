package board_proj.dao;

import static org.junit.Assert.fail;

import java.sql.Connection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import board_proj.dao.impl.BoardDaoImpl;
import board_proj.dto.BoardDto;
import board_proj.util.JdbcUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BoardDaoTest {
	private static Connection con = JdbcUtil.getConnection();
	private static BoardDaoImpl dao = BoardDaoImpl.getInstance();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		dao.setCon(con);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
	}

	@Test
	public void test02SelectListCount() {
		System.out.println("testSelectListCount()");
		int res = dao.selectListCount();
		Assert.assertNotEquals(-1, res); // -1만아니면 게시글 있긴 있다는 말
		System.out.println("List Count >> " + res);
	}

	@Test
	public void test03SelectArticleList() {
		System.out.println("testSelectArticleList()");
		int page = 1;
		int limit = 10;
		List<BoardDto> list = dao.selectArticleList(page, limit);
		Assert.assertNotNull(list);
		
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test05SelectArticle() {
		System.out.println("test5SelectArticle()");
		
		int board_num = 21;
		
		BoardDto searchArticle = dao.selectArticle(board_num);
		Assert.assertNotNull(searchArticle);
		
		System.out.println(searchArticle);
		
	}

	@Test
	public void test04InsertArticle() {
		System.out.println("testInsertArticle()");
		BoardDto newBoard = new BoardDto(
				"짱수린", 
				"1111", 
				"마칠 시간", 
				"5시", 
				"test.txt"
				);
		
		int res = dao.insertArticle(newBoard);
		Assert.assertEquals(1, res);
		System.out.println("res >> " + res);
		
	}

	@Test
	public void testInsertReplyArticle() {
		fail("Not yet implemented");
	}

	@Test
	public void test08UpdateArticle() {
		System.out.println("testUpdateArticle()");
		int board_num = 20;
		BoardDto article = dao.selectArticle(board_num);
		
		int res = dao.updateArticle(article);
		
		Assert.assertEquals(1, res);
		System.out.println("res >> " + res);
	}

	@Test
	public void test09DeleteArticle() {
		System.out.println("testDeleteArticle()");
		int board_num = dao.nextBoardNum() - 1; // 최근에 등록한 것의 번호가 나옴
		int res = dao.deleteArticle(board_num);
		Assert.assertEquals(1, res);
		System.out.println("res >> " + res);
	}

	@Test
	public void test06UpdateReadCount() {
		System.out.println("testUpdateReadCount()");
		int board_num = 21;
		int res = dao.updateReadCount(board_num);
		Assert.assertEquals(1, res);
		System.out.println(dao.selectArticle(board_num));
		
	}

	@Test
	public void test07IsArticleBoardWriter() {
		System.out.println("testIsArticleBoardWriter()");
		
		int board_num = 19;
		String pass = "dd";
		
		boolean res = dao.isArticleBoardWriter(board_num, pass);
		Assert.assertEquals(true, res); // true일때 작성자가 맞음을 확인할 수 있음
		System.out.println("res >> " + res);
		
	}
	
	@Test
	public void test01NextBoardNum() {
		System.out.println("testNextBoardNum()");
		int res = dao.nextBoardNum();
		
		Assert.assertNotEquals(0, res); // 0만 아니면 됨
		System.out.println("next Number >> " + res);
	}

}
