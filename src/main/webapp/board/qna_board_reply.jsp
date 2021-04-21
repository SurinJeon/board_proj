<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/board/css/style.css">
</head>
<body>
<%-- 	article >> ${article }
	page >> ${page } --%>
	board_re_ref >> ${article.board_re_ref } <br>
	board_re_lev >> ${article.board_re_lev } <br>
	board_re_seq >> ${article.board_re_seq }
	
	<section id="writeForm">
	<h2>게시판 글 등록</h2>
	<form action="boardReplyPro.do" method="post" name="boardform">
		<input type="hidden" name="page" value=${page }>
		<input type="hidden" name="board_num" value=${article.board_num }>
		<input type="hidden" name="board_re_ref" value=${article.board_re_ref }>
		<input type="hidden" name="board_re_lev" value=${article.board_re_lev }>
		<input type="hidden" name="board_re_seq" value=${article.board_re_seq }>

			<table>
				<tr>
					<td class="td_left"><label for="BOARD_NAME">글쓴이</label></td>
					<td class="td_right"><input name="BOARD_NAME" type="text" id="BOARD_NAME">
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_PASS">비밀번호</label></td>
					<td class="td_right"><input type="password" name="BOARD_PASS" id="BOARD_PASS" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_SUBJECT">제목</label></td>
					<td class="td_right"><input type="text" name="BOARD_SUBJECT" id="BOARD_SUBJECT" required="required"></td>
				</tr>
				<tr>
					<td class="td_left"><label for="BOARD_CONTENT">내용</label></td>
					<td class="td_right"><textarea name="BOARD_CONTENT" id="BOARD_CONTENT" cols="40" rows="15" required="required"></textarea></td>
				</tr>
			</table>
			
			<section id="commandCell">
				<input type="submit" value="답변글등록"> &nbsp;&nbsp;
				<input type="reset" value="다시 작성">
			</section>
		</form>
	</section>
</body>
</html>