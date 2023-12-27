function openConfirmid(userinput) {
    // 아이디를 입력했는지 검사
    if (userinput.id.value == "") {
        alert("아이디를 입력하세요");
        return;
    }
    // url과 사용자 입력 id를 조합합니다.
    url = "/m/confirmId?id="+userinput.id.value ;
    
    // 새로운 윈도우를 엽니다.
    open(url, "confirm",  "toolbar=no, location=no,status=no, menubar=no, scrollbars=no, resizable=no, width=300, height=200");
}


function checkIt() {
        var userinput = eval("document.userinput");
        if(!userinput.name.value) {
            alert("사용자 이름을 입력하세요");
            return false;
        }
        if(!userinput.id.value) {
            alert("ID를 입력하세요");
            return false;
        }
        
        if(!userinput.passwd.value ) {
            alert("비밀번호를 입력하세요");
            return false;
        }
        if(userinput.passwd.value != userinput.passwdCon.value)
        {
            alert("비밀번호를 동일하게 입력하세요");
            return false;
        }
       
    }