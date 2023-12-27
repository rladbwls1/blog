  

function fileAdd(){
	document.getElementById("files").innerHTML+='<br/> <input class="form-control" type="file" size="40" maxlength="30" name="files" >';
}
function replyCheck(id){
	if(id==null||id==""){
		alert("로그인 후 이용해주세요.");
		return false;
	}
	if(document.replyform.content.value==""){
		alert("내용을 입력해주세요.");
		return false;
	}
	
}
function updatenoticeFile(filename){
	document.getElementById("files").innerHTML+='<input type="hidden" name="deletefiles" value="'+filename+'"/>';
	document.getElementById(filename).style.display='none';
}


function deleteCheck(num,noticenum){
	if(confirm('삭제하겠습니까?')){
		location.href="/n/deleteReply?num="+num+"&noticenum="+noticenum;
	}
}

