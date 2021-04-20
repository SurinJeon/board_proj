<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/board/css/delete.css">
</head>
<body>
	<section id = "passForm">
		<form name ="deleteForm" action="boardDeletePro.do" method="get">
			<input type="hidden" name="board_num" value=${board_num }>
			<input type="hidden" name="page" value=${page }> <!-- url에는 보이지 않지만 전송이 된다 -->
			<table>
				<tr>
				 	<td><label>글 비밀번호: </label></td>
				 	<td><input name="BOARD_PASS" type="password"></td>
				</tr>
				<tr>
					<td colspan="2" id="button">
						<input type="submit" value="삭제">&nbsp;&nbsp;
						<input type="button" value="돌아가기" onclick="javascript:history.go(-1)"> <!-- 바로 이전 페이지로 돌아감 -->
					</td>
				</tr>
			</table>
		</form>
	</section>
</body>
</html>