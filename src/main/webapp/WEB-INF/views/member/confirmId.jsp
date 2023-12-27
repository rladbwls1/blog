<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>ID 중복확인</title>

<body>

<c:if test="${check ==1}">
<table>
  <tr> 
    <td height="39" >${id}이미 사용중인 아이디입니다.</td>
  </tr>
</table>
<form name="checkForm" method="post" action="/m/confirmId.me">
<table>
  <tr>
    <td> 
       다른 아이디를 선택하세요.<p>
       <input type="text" size="10" maxlength="12" name="id"> 
       <input type="submit" value="ID중복확인">
    </td>
  </tr>
</table>
</form>
</c:if>
<c:if test="${check == 0}">
<table>
  <tr> 
    <td> 
      <p>입력하신 ${id} 는 사용하실 수 있는 ID입니다. </p>
      <input type="button" value="닫기" onclick="setid('${id}')">
    </td>
  </tr>
</table>
</c:if>

</body>
</html>

<script language="javascript">
  function setid(id)
    {		
    	opener.document.userinput.id.value=id;
		self.close();
	}
</script>







