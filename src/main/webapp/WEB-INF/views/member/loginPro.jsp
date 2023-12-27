<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${check==1 }">
	<c:set var="memId" value="${dto.id }" scope="session"/>
	<c:set var="memName" value="${dto.name }" scope="session"/>
	<c:set var="status" value="${status }" scope="session"/>
	<c:redirect url="/m/main"/>
</c:if>
<c:if test="${check==0 }">
	<script> 
	  alert("아이디 / 비밀번호를 확인하세요.");
      history.go(-1);
	</script>
</c:if>