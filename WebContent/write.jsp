<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="write.do" method="post">
	<table border="1">
		<tr>
			<td>�̸�</td> <td><input type="text" name="bName" size="50"></td>
		<tr>
			<td>����</td> <td><input type="text" name="bTitle" size="50"></td>
		</tr>
		<tr>
			<td>����</td> <td><textarea rows="10" ></textarea></td>
		</tr>
		<tr>
			<td><input type="submit" value="�Է¿Ϸ�"><td>
		</tr>	
	</table>
	</form>
</body>
</html>