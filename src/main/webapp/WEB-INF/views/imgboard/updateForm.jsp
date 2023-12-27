<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>restaurant</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
		<script src="https://cdn.ckeditor.com/ckeditor5/12.0.0/classic/ckeditor.js"></script>
	</head>
	<body>
		<form method="post" enctype="multipart/form-data" name="updateForm" action="/imgboard/updatePro">
			<table>
				<tr>
					<input type = "hidden" name="num" value="${dto.num}">
					<td>제목</td>
					<td><input type = "text" name = "subject" value = "${dto.subject}"></td>
				</tr>
				<tr>
					<td>글 내용</td>
					<td><textarea id="content" name = "content" rows="15" cols="50">${dto.content}</textarea></td>
				</tr>
				<tr>
						<td align="center">
					        <input class="btn btn-success" type="submit" value="글수정">  
					        <input class="btn btn-dark" type="reset" value="다시작성">
					    <input type="button" class="btn btn-secondary" value="목록보기" onclick="window.location='/imgboard/list'">
					</td>
				</tr>
			</table>
		</form>
		<script src="${pageContext.request.contextPath}/resources/js/ckeditor.js"></script>
	</body>
</html>