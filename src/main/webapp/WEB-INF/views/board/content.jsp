<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<!DOCTYPE html>
<html lang="ko"> 
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>place</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/resources/css/content.css">
        <script src="/resources/se2/js/HuskyEZCreator.js"></script>
		<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
		<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
		<script>
			function deleteItem(comm){
				location.href = "/board/commdel?comm="+comm+"&num="+${boardList.num};
			}
			
			function updateItem(comm){
				var bb = document.getElementById('bb');
				var dd = document.getElementById('dd');
				bb.style.display = "block";
				dd.style.display = "none";
			}
			
			function xx(){
				var bb = document.getElementById('bb');
				var dd = document.getElementById('dd');
				bb.style.display = "none";
				dd.style.display = "block";
			}
			
			function update(comm){
				location.href = "/board/commup?comm="+comm+"&num="+${boardList.num};
			}
		</script>
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
                        <h1 class="mt-4">장소 공유</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                No. ${boardList.num}
                            </div>
                            <div class="card-body">
                            <div id="table">
                                <table id="tb">
                                        <tr id="f_tr">
                                            <th>제목</th>
                                            <td>${boardList.title}</td>
                                        </tr>
                                        <tr> 
                                            <th>조회수</th>
                                            <td>${boardList.count}</td>
                                        </tr>
                                        <tr> 
                                            <th>작성자</th>
                                            <td>${boardList.writer}</td>
                                        </tr>
                                        <c:if test="${boardList.isfile > 0}">
	                                        <tr> 
	                                            <th>첨부파일</th>
	                                            <td>
	                                            <c:forEach var="img" items="${imgList}">
		                                            <img src="/resources/db_image/board/${img}" width="150"/><br/>
	                                            </c:forEach>
	                                        	</td>
	                                        </tr>
                                        </c:if>
                                        <tr>
                                            <th>내용</th>
                                            <td><div rows="10" cols="100" readonly="readonly">${boardList.content}</div></td>
                                        </tr>
                                        <tr>
                                            <th>시간</th>
                                            <td><fmt:formatDate value="${boardList.reg}" type="date" dateStyle="short"/></td>
                                        </tr>    
                                </table>
                                </div>
                                <c:if test="${sessionScope.memId == boardList.writer}">
                                <div id="btn1">
                                	<button type="button" onclick="location.href='/board/deleteForm?num=${boardList.num}&check=0'">글 수정</button>
                                	<button type="button" onclick="location.href='/board/deleteForm?num=${boardList.num}&check=1'">글 삭제</button>
                            	</div>
                            	</c:if>
                            </div>
                        </div>
                     <div>
                     	<p><img src="/resources/img/check.png" width="25"/>Commend ${count}</p>
                     	<div id="tb2">
                     	<input type="hidden" value="${boardList.num}" id="vv"/>
                     	<c:forEach var="comm" items="${comm}">
                     		<table id="tb3">
                     				<form action="/board/commup" method="post">
                     			<tr id="bb" style="display:none;">
                     				<input type="hidden" name="num" value="${boardList.num}">
                     				<input type="hidden" name="comm" value="${comm.num}">
                     				<td id="commid">${comm.id}</td>
                     				<td class="co"><textarea id="cc" name="content">${comm.content}</textarea></td>
                     				<td>
                     				<button class="re" type="submit">적용</button>
                     				<button class="xx" onclick="xx()" type="button">취소</button>
                     				</td>
                     			</tr>
                     				</form>
                     			<tr id="dd">
                     				<td id="commid">${comm.id}</td>
                     				<td class="con">${comm.content}</td>
                     				<td id="commreg"><fmt:formatDate value="${comm.reg}" type="both"/></td>
                     				<td id="lasttd">
									<c:if test="${comm.id == sessionScope.memId}">
                     					<button class="re" type="button" onclick="updateItem('${comm.num}')">수정</button>
                     					<button class="xx" onclick="deleteItem('${comm.num}')" type="button">삭제</button>
                     				</c:if>
                     				</td>
                     			</tr>
                     		</table>
                     	</c:forEach>
                     </div>
                     </div>       
                    <form id="comm" method="post">
                    	<input type="hidden" name="boardnum" value="${num}"/>
                    	<input type="hidden" name="id" value="${sessionScope.memId}" readonly="readonly"/>
                   		<textarea id="commend" rows="5" cols="10" name="content" style="resize:none;"></textarea>
						<input type="submit" id="sub" onclick="addComm('${num}')" value="댓글등록"/>                    
                    </form>
                    </div>
                    <div>
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
        <script>
        	function addComm(num){
        		var comm = document.getElementById('comm');
        		if(${sessionScope.memId==null}){
        			alert("로그인 하셔야 댓글 작성 가능합니다!");
        			comm.action = "/m/loginForm";
        			comm.submit();
        		}
        		else{
        			comm.action = "/board/comment";
        			comm.submit();
        		}
        	}
        </script>
        <script type="text/javascript" src = "/resources/js/notice-write2.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/datatables-simple-demo.js"></script>
    </body>
</html>