<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/board/css/list.css">
</head>
<body>
	<div id="container">
		<section id="list">
			<h2>게시판 목록</h2>
			<p>
				<a href="boardWriteForm.do"><button>게시판 글쓰기</button></a>
			</p>
			<table class="table">
				<thead>
					<th id="num">번호</th>
					<th id="subject">제목</th>
					<th id="name">작성자</th>
					<th id="date">날짜</th>
					<th id="readcount">조회수</th>
				</thead>
				<tbody>
					<c:forEach var="board" items="${articleList }">
						<tr>
							<td>${board.board_num }</td>
							<td id="subject">
								<c:if test="${board.board_re_lev ne 0 }">
									<c:forEach var="i" begin="1" end="${board.board_re_lev }" step="2">
										&nbsp;&nbsp;
									</c:forEach>
									└
								</c:if>
								<a href="boardDetail.do?board_num=${board.board_num}&page=${pageInfo.page}">
									${board.board_subject }
								</a>
							</td>
							<td>${board.board_name }</td>
							<td>${board.board_date }</td>
							<td>${board.board_readcount }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<%-- ${pageInfo} --%>
			<!-- 디버깅용 -->
		</section>

		<section id="pageList">
			<c:if test="${pageInfo.page <= 1 }">
				[이전]&nbsp;
			</c:if>
			<c:if test="${pageInfo.page > 1 }">
				<a href="boardList.do?page=${pageInfo.page - 1}">[이전]</a>&nbsp;
			</c:if>
			<c:forEach var="a" begin="1" end="${pageInfo.endPage }">
				<c:if test="${a == pageInfo.page}">
					<span>[${a}]</span>&nbsp;
				</c:if>
				<c:if test="${a ne pageInfo.page}">
					<a href="boardList.do?page=${a}">[${a}]</a>&nbsp;
				</c:if>
			</c:forEach>
			
			<c:if test="${pageInfo.page >= pageInfo.maxPage}">
				[다음]&nbsp;
			</c:if>
			<c:if test="${pageInfo.page < pageInfo.maxPage}">
				<a href="boardList.do?page=${pageInfo.page + 1}">[다음]</a>&nbsp;
			</c:if>
		</section>
	</div>
</body>
</html>