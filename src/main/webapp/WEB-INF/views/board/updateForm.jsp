<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>place</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
<link href="/resources/css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
<script src="/resources/se2/js/HuskyEZCreator.js"></script>
<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script>
	function addFile(){
		document.getElementById("addfile").innerHTML +=
			'<br/><input type="file" name="upload"/>';
	}
	function DelFile(){
		document.getElementById("addfile");
	}
</script>
<script>
	function deleteItem(msg){
		document.getElementById("addfile").innerHTML += '<input type="hidden" name="ff" value="'+msg+'"/>';
		document.getElementById(msg).style.display = "none";
	}
</script>
<link rel="stylesheet" href="/resources/css/updateForm.css">
</head>
<body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="/m/main">Blog</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            <div class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
			</div>
			<c:if test="${sessionScope.memId!=null }">
				<h5 style="color: white; font-family: 'Pretendard-Regular'"> 
					<em>${sessionScope.memId}</em>님 환영합니다</h5>
			</c:if>
            
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
		                <c:if test="${sessionScope.memId==null }">
							<li><a href="/m/loginForm" class="dropdown-item">Login</a></li>
						</c:if>
                        <c:if test="${sessionScope.memId!=null }">
                        <li><a class="dropdown-item" href="/m/logout?id=${sessionScope.memId }">Logout</a></li>
                        </c:if>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">notice</div>
                            <a class="nav-link" href="/n/list">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                공지게시판
                            </a>
                            <div class="sb-sidenav-menu-heading">Guest</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Q & A
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="/qa/writeForm">문의하기</a>
                                    <a class="nav-link" href="/qa/list">문의목록</a>
                                </nav>
                            </div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                방명록
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                	<a class="nav-link" href="/guest/writeForm">작성하기</a>
                                    <a class="nav-link" href="/guest/list">방명록 보기</a>
                                </nav>
                            </div>
                            <div class="sb-sidenav-menu-heading">post</div>
                            <a class="nav-link" href="/board/list">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                장소
                            </a>
                            <a class="nav-link" href="/imgboard/list">
                                <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                                맛집
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Logged in as:</div>
                        final04
                    </div>
                </nav>
             </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">수정</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Update Write Form
                            </div>
                            <div class="card-body">
								<form id="form" action="/board/upPro" method="post" enctype="multipart/form-data">
								    <div id="table">
								    <input type="hidden" name="num" value="${num}"/>
								    <input type="hidden" name="isfile" value="${boardList.isfile}"/>
								    <table id="tb"> 
								        <tr id="f_tr">
								            <th>제목</th>
								            <td><input type="text" name="title" value="${boardList.title}"/></td>
								        </tr>
								        <tr> 
								            <th>작성자</th>
								            <td><input type="text" name="writer" value="${boardList.writer}" readonly="readonly"/></td>
								        </tr>
								        <tr> 
								            <th>첨부파일</th>
								            <td id="addfile">
							            	<div id="file_img">
								            <c:forEach var="img" items="${img}">
								            <div id="${img}" class="ee">
								            	<img id="x" src="/resources/img/x.png" onclick="deleteItem('${img}')" width="20"/>
								            	<img src="/resources/db_image/board/${img}" width="60" height="60"/>
								            </div>
								            </c:forEach>
							            	</div>
								            <input type="file" name="upload"/>
								            <button id="button" type="button" onclick="addFile()">파일추가</button></td>
								        </tr>
								        <tr>
								            <th>내용</th>
								            <td><textarea id="content" name="content"
								            			rows="10" cols="100" style="width:100%;">${boardList.content}</textarea></td>
								        </tr>
								        <tr>
								            <th>비밀번호</th>
								            <td><input type="password" name="pw" value="${boardList.pw}"/></td>
								        </tr>
								   	 </table>
									</div>
								   	 <button type="submit" onclick="submitForm()">글 수정</button>
								</form>
								</div>
								</div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2023</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <srcipt>
        </srcipt>
        <script type="text/javascript" src = "/resources/js/notice-write.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/datatables-simple-demo.js"></script>
    </body>
</html>