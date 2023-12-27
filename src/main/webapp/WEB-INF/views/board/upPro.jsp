<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:if test="${result == 1}">
	  <meta http-equiv="Refresh" content="0;url=/board/list" >
</c:if>
<c:if test="${result != 1}">
       <script language="JavaScript">           
         alert("비밀번호가 맞지 않습니다");
         history.go(-1);
      </script>
</c:if>