<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
	<body>
		<form action="reply.do" method="post">
		<table width="300" border="1">
			<input type="hidden" name="bId" value="${reply_view.bId}">
			<input type="hidden" name="bGroup" value="${reply_view.bGroup}">
			<input type="hidden" name="bStep" value="${reply_view.bStep}">
			<input type="hidden" name="bIndent" value="${reply_view.bIndent}">	
			<tr>
				<td>�ۼ���</td>
				<td><input type="text" name="bName" size="10"></td>
			</tr>
			<tr>
				<td>����</td>
				<td><input type="text" name="bTitle" size="10"></td>
			</tr>
			<tr>
				<td>����</td>
				<td><textarea rows="10" name="bContent"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="�ۼ��Ϸ�"></td>
				<td><a href="list.do">���</a>
			</tr>
		</table>
		</form>
	</body>
</html>