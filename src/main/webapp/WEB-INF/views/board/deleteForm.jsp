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
	<c:if test="${check == 0}">
	<form action="/board/updatePro" method="post">
		<input type="hidden" name="num" value="${num}"/>
		<input type="hidden" name="check" value="0"/>
		<h2>글 수정 비밀번호 확인</h2>
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="확인"/></td>
			</tr>
		</table>
	</form>
	</c:if>
	<c:if test="${check == 1}">
	<form action="/board/deletePro" method="post">
		<input type="hidden" name="num" value="${num}"/>
		<input type="hidden" name="check" value="1"/>
		<h2>글 삭제 비밀번호 확인</h2>
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="확인"/></td>
			</tr>
		</table>
	</form>
	</c:if>
</body>
</html>