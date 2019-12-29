<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<table width="300"  border="1">
	<form action="modify.do" method="post">
	
		<input type="hidden" name="bId" value="${content_view.bId}">
		<tr>
			<td>조회수</td>
			<td>${content_view.bHit}</td>
		</tr>
		<tr>
			<td>이름</td> 
			<td><input type="text" name="bName" value="${content_view.bName}"></td>
		<tr>
			<td>제목</td>
			<td><input type="text" name="bTitle" value="${content_view.bTitle}"></td>
		</tr>
		<tr>
			<td>내용</td> 
			<td><textarea name="bContent" rows="10" >${content_view.bContent}</textarea></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="수정하기"></td>
			<td>
				<a href="list.do">목록</a>
				<a href="delete.do?bId=${content_view.bId}">삭제하기</a>
				<a href="reply_view.do?bId=${content_view.bId}">답변달기</a>
			</td>		
		</tr>	
	</form>	
	</table>
</body>
</html>