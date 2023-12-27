<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>restaurant</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
	
		<script language="JavaScript">           
			function deleteSave(){	
				if(document.delForm.passwd.value==''){	
					alert("비밀번호를 입력하십시요.");
					document.delForm.passwd.focus();
					return false;
				}	
			}          
		</script>
	</head>
	<body>
		<form method="post" name = "deleForm" action = "deletePro" onsubmit="return deleteSave()">
			<table>
				<tr>
					<td>비밀 번호를 입력해 주세요</td>
				</tr>
				<tr>
					<input type="password" name="passwd">
					<input type="hidden" name="num" value="${num}">
				</tr>
				<tr>
					<td><input class="btn btn-danger" type="submit" value="글삭제" >
					<input class="btn btn-secondary" type="button" value="글목록" OnClick="window.location='/imgboard/list'"></td>
				</tr>
			</table>
	</body>
</html>