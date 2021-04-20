<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script type="text/javascript">
		function modifyboard(){
			modifyForm.submit();
		}
	</script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/board/css/modify.css">
</head>
<body>
	<div id="container">
			<h2>게시판 글 수정</h2>
		<section id="writeForm">
			<form action="boardModifyPro.do" method="post" name="modifyForm">
				<input type="hidden" name="board_num" value=${article.board_num }>
				<input type="hidden" name="page" value=${page }>
				<table>
					<tr>
						<td class="td_left"><label for="board_name">글쓴이</label></td>
						<td class="td_right"><input name="board_name" type="text" id="board_name" value="${article.board_name}">
							
						</td>
					</tr>
					<tr>
						<td class="td_left"><label for="board_pass">비밀번호</label></td>
						<td class="td_right"><input name="board_pass" type="password" id="board_pass"></td>
					</tr>
					
					<tr>
						<td class="td_left"><label for="board_subject">제목</label></td>
						<td class="td_right"><input name="board_subject" type="text" id="board_subject" value="${article.board_subject }"></td>
					</tr>
					<tr>
						<td class="td_left"><label for="board_content">내용</label></td>
						<td><textarea rows="15" cols="40" id="board_content" name="board_content">
							${article.board_content }
						</textarea></td>
					</tr>
				</table>

				<section id="commandCell">
					<a href="javascript:modifyboard()">[수정]</a>&nbsp;&nbsp; <a href="javascript:history.go(-1)">[뒤로]</a>
				</section>
			</form>
		</section>
	</div>
</body>
</html>