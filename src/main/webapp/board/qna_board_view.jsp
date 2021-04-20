<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 만들기</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/board/css/article.css">
</head>
<body>
	<div id="container">
	<%-- ${page } ${article } <<디버깅용 --%>
		<section id="articleForm">
			<h2>글 내용 상세보기</h2>
			<section id="basicInfoArea">
				제목: ${article.board_subject }  <br>
				첨부파일: 
				<c:if test="${article.board_file ne null}">
					<a href="fileDown.do?downFile=${article.board_file }">${article.board_file }</a>
				</c:if>
			</section>
		
			<section id="articleContentArea">
				${article.board_content }
			</section>
		</section>
		
		<section id="commandList">
			<a href="boardReplyForm.do?board_num=${article.board_num }&page=${page}"><button>답변</button></a>
			<a href="boardModifyForm.do?board_num=${article.board_num }&page=${page}"><button>수정</button></a>
			<a href="boardDeleteForm.do?board_num=${article.board_num }&page=${page}"><button>삭제</button></a>
			<a href="boardList.do?board_num=${article.board_num }&page=${page}"><button>목록</button></a>
		</section>
	</div>
</body>
</html>





























