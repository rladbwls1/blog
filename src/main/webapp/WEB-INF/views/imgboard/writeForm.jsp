<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8">
    <title>글쓰기</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
	<script src="https://cdn.ckeditor.com/ckeditor5/12.0.0/classic/ckeditor.js"></script>
    <script>
	    function fileAdd(){
			document.getElementById("files").innerHTML += `<br><input type="file" size="40" maxlength="30" name="files" >`;
		}
	    function fileRemove() {
	        var filesElement = document.getElementById("files");
	        var fileInputs = filesElement.querySelectorAll('input[type="file"]');
	        if (fileInputs.length > 1) {
	            var lastFileInput = fileInputs[fileInputs.length - 1];
	            filesElement.removeChild(lastFileInput.previousElementSibling);
	            filesElement.removeChild(lastFileInput);
	        }
	    }
	</script>
</head>
<body>

	<div class="d-grid gap-2 col-6 mx-auto">
	    <form method="post" enctype="multipart/form-data" name="writeform" action="/imgboard/writePro">
	        <table>
	            <tr>
	                <td>제목</td>
	                <td><input type="text" name="subject"></td>
	            </tr>
	            <tr>
	                <td>작성자</td>
	                <td><input type="text" name="writer" value=${id} readonly></td>
	            </tr>
	            <tr>
	                <td>글 내용</td>
	                <td>
				        <textarea name="content" id="content" rows="10" cols="100"></textarea>
	                </td>
	            </tr>
	            <tr>
	                <td>파일 첨부</td>
	                <td id="files">
	                	<input type="file" size="40" maxlength="30" name="files" >
	                    <input type="button" value="+" onclick="fileAdd()">
	                    <input type="button" value="-" onclick="fileRemove()">
	                </td>
	            </tr>
	            <tr>
	                <td>비밀번호</td>
	                <td><input type="password" name="passwd"></td>
	            </tr>
	            <tr>
	                <td>
	                    <input class="btn btn-success" type="submit" value="글쓰기">
	                    <input class="btn btn-warning" type="reset" value="다시작성">
	                    <input class="btn btn-secondary" type="button" value="목록보기" onclick="window.location='/imgboard/list'">
	                </td>
	            </tr>
	        </table>
	    </form>
    </div>
    <script src="${pageContext.request.contextPath}/resources/js/ckeditor.js"></script>
</body>
</html>
