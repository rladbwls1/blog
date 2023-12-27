<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
	<script language="JavaScript" src="/resources/js/script.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>

<body>
<!-- 댓글 다는 폼 -->
<form name="replyform" action="replyPro" method="post" onSubmit="return replyCheck('${sessionScope.memId}')" >
	<input type="hidden" name="noticenum" value="${notice.num }"/>
	<input type="hidden" name="id" value="${sessionScope.memId }"/>
	<div class="input-group mb-3">
	  <input type="text" class="form-control" name="content" placeholder="댓글을 입력하세요">
	  <button class="btn btn-outline-secondary" type="submit">등록</button>
	</div>
</form>
<!-- 작성된 댓글 출력 -->

<table style="width:100%;">
	<c:if test="${replys !=null}">
		<c:forEach var="reply" items="${replys }">
			<tr >
				<td width="100%"><b>${reply.id }</b></td>
				<td width="100">
				<c:if test="${sessionScope.status==10 || reply.id.equals(sessionScope.memId) }">
					<input type="button" class="btn btn-outline-danger" value="X" onclick="deleteCheck('${reply.num }','${reply.noticenum}')"/>
				</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="2">${reply.content }</td>
			</tr >
			<tr> 
				<td colspan="2"><small><fmt:formatDate value="${reply.reg }" pattern="yyyy/MM/dd HH:mm"/></small></td>
			</tr>
		</c:forEach>
	</c:if>
</table>

<!-- 답글 가능하게 할 예정 -->
<!-- 대신 답글의 단계는 1단계만 -->

<%-- --%>
</body>
</html>
