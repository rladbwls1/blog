<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <c:if test="${status == 10 || name == dto.writer}">
		<form method="post" name="delete" action="/guest/deletePro">
			<input type="hidden" name="num" value="${num}">
			<input type="hidden" name="ref" value="${ref}">
			<div>
			<p>삭제하시겠습니까?</p>
			<button type="submit">삭제</button>
			</div>
		</form>
	</c:if>
</body>
</html>