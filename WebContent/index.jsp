<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
	//session scope 에 "id" 라는 키값으로 저장된 문자열이 있는지 읽어와 본다.
	String id=(String)session.getAttribute("id");
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 World</title>
<jsp:include page="include/resource.jsp"></jsp:include>
</head>
<body>
<jsp:include page="include/navbar.jsp"></jsp:include>
<div class="container">
	
	<h1>어서와 404는 첨이니?</h1>
	<ul>
		<li><a href="users/signup_form.jsp">회원가입</a></li>
		<li><a href="users/loginform.jsp">로그인</a></li>
		<li><a href="cafe/list.jsp">카페 글 목록 보기</a></li>
		<li><a href="file/list.jsp">자료실 목록 보기</a></li>
		<li><a href="gallery/list.jsp">갤러리 목록 보기</a></li>
	</ul>
</div>
</body>
</html>