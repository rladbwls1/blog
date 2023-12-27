
function writeSave(){	
	if(document.writeform.content.value==""){
	  alert("내용을 입력하십시요.");
	  document.writeform.content.focus();
	  return false;
	}
 } 
 
 function deletePop(url) {
    window.open(url, 'DeleteFormPopup', 'width=500, height=500, resizable=yes, scrollbars=yes');
    return false; 
}

