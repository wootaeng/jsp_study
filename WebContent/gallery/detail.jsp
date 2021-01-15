<%@page import="test.gallery.dao.GalleryDao"%>
<%@page import="test.gallery.dto.GalleryDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//자세히 보여줄 겔러리 item 번호를 읽어온다.
	int num=Integer.parseInt(request.getParameter("num"));
	//번호를 이용해서 겔러리 item 정보를 얻어온다.
	GalleryDto dto=GalleryDao.getInstance().getData(num);
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/datail.jsp</title>
<jsp:include page="../include/resource.jsp"></jsp:include>
</head>
<body>
<jsp:include page="../include/navbar.jsp">
	<jsp:param value="gallery" name="thispage"/>
</jsp:include>
<div class="container">
	<nav>
		<ul class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/">Home</a>
			</li>
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/gallery/list.jsp">사진 목록</a>
			</li>
			<li class="breadcrumb-item acrtive">상세보기</li>
		</ul>
	</nav>
	<div class="rounded mx-auto d-block">
		<img class="img-fluid" src="${pageContext.request.contextPath}<%=dto.getImagePath() %>" />
	    	<ul class="list-group list-group-flush">
			    <li class="list-group-item"><%=dto.getCaption() %></li>
			    <li class="list-group-item">by <strong><%=dto.getWriter() %></strong></li>
			    <li class="list-group-item"><small>등록일 <%=dto.getRegdate() %></small></li>
		  	</ul>
	</div>
   	<div class="card-body">
	    <a href="list.jsp" class="card-link">목록으로 가기</a>
	    <%if(dto.getPrevNum() != 0){ %>
			<a href="detail.jsp?num=<%=dto.getPrevNum()%>">이전</a>
		<%} %>
		<%if(dto.getNextNum() != 0){ %>
			<a href="detail.jsp?num=<%=dto.getNextNum()%>">다음</a>
		<%} %>
  	</div>
	  
</div>
</body>
</html>