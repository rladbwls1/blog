<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>공지사항</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="/resources/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/script.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/datatables-simple-demo.js"></script>
        <script src="https://cdn.ckeditor.com/ckeditor5/12.0.0/classic/ckeditor.js"></script>
    </head>
    <body class="sb-nav-fixed">
    
<!-- 관리자만 작성할 수 있다. -->
<c:if test="${sessionScope.status==10 }">
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
                        <h1 class="mt-4">공지사항</h1>
                        <div class="card mb-4">
                            <div class="card-body">
                            	공지목록 보기 &nbsp
                            	<a href="/n/list" class="btn btn-secondary">목록</a>
                            </div>
                        </div>
                        
                        <div class="d-grid gap-2 col-6 mx-auto">
							<form action="/n/updatePro" method="post" enctype="multipart/form-data">
								<input type="hidden" name="num" value="${notice.num }"/>
								<input type="hidden" name="isfile" value="${notice.isfile }"/>	
									<div>
										<h1>공지 수정하기</h1>
									</div>
									<table class="table table-bordered">
										<tr>
											<td>제목</td>
											<td><input type="text" name="subject" class="form-control" value="${notice.subject }"/></td>
										</tr>
										<tr>
											<td>내용</td>
											<td><textarea name="content" id="content" rows="13" cols="40" class="form-control">${notice.content}</textarea></td>
										</tr>
										<tr>
											<td  width="330" colspan="2" align="center">
												<input type="button" class="btn btn-secondary" value="파일 추가" onclick="fileAdd()">
											</td>
										</tr>
										<tr>
											<td  width="70"  bgcolor="${value_c}" align="center">파 일</td>
											<td  width="330" id="files">
												<input type="file"  class="form-control" size="40" maxlength="30" name="files" >
											</td>
										</tr>
										<c:if test="${fileList.size()>0 }">
											<tr>
												<td colspan="2" >첨부파일</td>
											</tr>
											<c:forEach var="filename" items="${fileList }">
												<tr id="${filename} ">
													<td colspan="2">${filename } <input type="button" class="btn btn-outline-danger" value="삭제"  onclick="updatenoticeFile('${filename } ')"/></td>
												</tr>
											</c:forEach>
										</c:if>
										<tr>      
											<td colspan=2 bgcolor="${value_c}" align="center"> 
												<input type="submit" class="btn btn-primary" value="글쓰기" >  
												<input type="reset" class="btn btn-secondary" value="다시작성">
												<input type="button" class="btn btn-secondary"  value="목록보기" OnClick="window.location='/n/list'">
											</td>
										</tr>
									</table>
								</form>
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
				</c:if>
				<c:if test="${sessionScope.status!=10 }">
					<script>
						alert("권한이 없습니다.");
						history.go(-1);
					</script>
				</c:if>
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="/resources/js/datatables-simple-demo.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/ckeditor.js"></script>
	</body>
</html>