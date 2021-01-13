<%@page import="test.gallery.dao.GalleryDao"%>
<%@page import="java.util.List"%>
<%@page import="test.gallery.dto.GalleryDto"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//한 페이지에 몇개씩 표시할 것인지
	final int PAGE_ROW_COUNT=8;
	//하단 페이지를 몇개씩 표시할 것인지
	final int PAGE_DISPLAY_COUNT=5;
	
	//보여줄 페이지의 번호를 일단 1이라고 초기값 지정
	int pageNum=1;
	//페이지 번호가 파라미터로 전달되는지 읽어와 본다.
	String strPageNum=request.getParameter("pageNum");
	//만일 페이지 번호가 파라미터로 넘어 온다면
	if(strPageNum != null){
		//숫자로 바꿔서 보여줄 페이지 번호로 지정한다.
		pageNum=Integer.parseInt(strPageNum);
	}
	//보여줄 페이지의 시작 ROWNUM
	int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
	//보여줄 페이지의 끝 ROWNUM
	int endRowNum=pageNum*PAGE_ROW_COUNT;
	
	String keyword=request.getParameter("keyword");
	String condition=request.getParameter("condition");
	//만일 키워드가 넘어오지 않는다면
	if(keyword==null){
		//키워드와 검색조건에 빈 문자열을 넣어준다.
		//클라이언트 웹 브라우저에 출력할때 "null" 을 출력되지 않게 하기위해서
		keyword="";
		condition="";
	}
	//특수 기호를 인코딩한 키워드를 미리 준비한다. 빈문자열이면 이런식keyword=&encodedK
	String encodedK=URLEncoder.encode(keyword);
	
	
	//startRowNum 과 endRowNum  을 CafeDto 객체에 담고
	GalleryDto dto=new GalleryDto();
	dto.setStartRowNum(startRowNum);
	dto.setEndRowNum(endRowNum);
	
	//ArrayList 객체의 참조값을 담을 지역변수를 미리 만든다.
	List<GalleryDto> list=null;
	//전체 row의 갯수를 담을 지역변수를 미리 만든다
	int totalRow=0;
	//만일 검색 키워드가 넘어온다면
	if(!keyword.equals("")){
		//검색 조건이 무엇이냐에 따라 분기 하기
		if(condition.equals("caption_imagePath")){//제목 + 파일명 검색인 경우
			//검색 키워드를 FileDto 에 담아서 전달한다.
			dto.setCaption(keyword);
			dto.setImagePath(keyword);
			//제목+파일명 검색일때 호출하는 메소드를 이용해서 목록 얻어오기 
			list=GalleryDao.getInstance().getListCI(dto);
			//제목+파일명 검색일때 호출하는 메소드를 이용해서 row  의 갯수 얻어오기
			totalRow=GalleryDao.getInstance().getCountCI(dto);
		}else if(condition.equals("caption")){ //제목 검색인 경우
			dto.setCaption(keyword);
			list=GalleryDao.getInstance().getListC(dto);
			totalRow=GalleryDao.getInstance().getCountC(dto);
		}else if(condition.equals("writer")){ //작성자 검색인 경우
			dto.setWriter(keyword);
			list=GalleryDao.getInstance().getListW(dto);
			totalRow=GalleryDao.getInstance().getCountW(dto);
		} // 다른 검색 조건을 추가 하고 싶다면 아래에 else if() 를 계속 추가 하면 된다.
	}else{//검색 키워드가 넘어오지 않는다면
		//키워드가 없을때 호출하는 메소드를 이용해서 파일 목록을 얻어온다. 
		list=GalleryDao.getInstance().getList(dto);
		//키워드가 없을때 호출하는 메소드를 이용해서 전제 row 의 갯수를 얻어온다.
		totalRow=GalleryDao.getInstance().getCount();
	}
	
	//CafeDao 객체를 이용해서 글 목록을 얻어온다.
	//List<GalleryDto> list=GalleryDao.getInstance().getList(); //검색기능 전 리스트
	
	//하단 시작 페이지 번호 
	int startPageNum = 1 + ((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
	//하단 끝 페이지 번호
	int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
	
	//전체 row 의 갯수
	//int totalRow=GalleryDao.getInstance().getCount();
	//전체 페이지의 갯수 구하기
	int totalPageCount=(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
	//끝 페이지 번호가 이미 전체 페이지 갯수보다 크게 계산되었다면 잘못된 값이다.
	if(endPageNum > totalPageCount){
		endPageNum=totalPageCount; //보정해 준다. 
	}
	//List<FileDto> list=FileDao.getInstance().getList();
	//로그인된 아이디가 있는지 읽어와 본다(로그인을 하지 않았으면 null 이다)
	String id=(String)session.getAttribute("id");
%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/list.jsp</title>
<jsp:include page="../include/resource.jsp"></jsp:include>
<!-- 
	jquery 플러그인 imgLiquid.js 로딩하기
	- 반드시 jquery.js 가 먼저 로딩이 되어 있어야지만 동작한다.
	- 사용법은 이미지의 부모 div 크기를 결정하고 이미지를 선택해서  .imgLiquid() 동작을 하면된다.
 -->
<script src="${pageContext.request.contextPath }/js/imgLiquid.js"></script>
<style>
	
	/* card 이미지 부모요소의 높이 지정 */
	.img-wrapper{
		height: 250px;
		/* transform 을 적용할때 0.3s 동안 순차적으로 적용하기
		적용 시간을 정하는 것.  */
		transition: transform 0.3s ease-out
	}
	/* .img-wrapper 에 마우스가 hover 되었을때 적용할 css*/
	.img-wrapper:hover{
		/* 원본 크기의 1.1배로 확대 시키기 */
		transform: scale(1.1);
	}
	.card .card-text{
		/* 한줄만 text 가 나오고  한줄 넘는 길이에 대해서는 ... 처리 하는 css */
		display:block;
		white-space : nowrap;
		text-overflow: ellipsis;
		overflow: hidden;
	}
</style>
</head>
<body>
<jsp:include page="../include/navbar.jsp">
	<jsp:param value="gallery" name="thisPage"/>
</jsp:include>
<div class="container">
	<nav>
		<ul class="breadcrumb">
			<li class="breadcrumb-item">
				<a href="${pageContext.request.contextPath }/">Home</a>
			</li>
			<li class="breadcrumb-item active">사진 목록</li>
		</ul>
	</nav>
	<a class="btn btn-outline-secondary" href="private/upload_form.jsp" role="button">사진 업로드 하러 가기</a></br>
	<a class="btn btn-outline-secondary" href="private/ajax_form.jsp" role="button">사진 업로드 하러 가기2</a>
	<h1>갤러리 목록 입니다.</h1>
	<form action="list.jsp" method="get">
		<label for="condition">검색조건</label>
		<select name="condition" id="condition">
			<option value="caption_imagePath"<%=condition.equals("cpation_imagePath") ? "selected" : "" %>>제목+파일명</option>
			<option value="caption"<%=condition.equals("caption") ? "selected" : "" %>>제목</option>
			<option value="writer"<%=condition.equals("writer") ? "selected" : "" %>>작성자</option>
		</select>
		<input type="text" name="keyword" placeholder="검색어..." value="<%=keyword%>"/>
		<button type="submit">검색</button>
	</form>
	<%-- 만일 검색 키워드가 존재한다면 몇개의 글이 검색 되었는지 알려준다. --%>
	<%if(!keyword.equals("")){ %>
		<div class="alert alert-success">
			<strong><%=totalRow %></strong> 개의 자료가 검색되었습니다.
		</div>
	<%} %>
	<div class="row">
		<%for(GalleryDto tmp:list){ %>
		<!-- 
			[ 칼럼의 폭을 반응형으로 ]
			device 폭 768px 미만에서  칼럼의 폭 => 6/12 (50%)
			device 폭 768px ~ 992px 에서  칼럼의 폭 => 4/12 (33.333%)
			device 폭 992  이상에서  칼럼의 폭 => 3/12 (25%)
		 -->
		<div class="col-6 col-md-4 col-lg-3" >
			
				<div class="card mb-3" >
					<a href="detail.jsp?num=<%=tmp.getNum() %>">
						<div class="img-wrapper">
							<img class="card-img-top" src="${pageContext.request.contextPath }<%=tmp.getImagePath() %>"  />
						</div>	
					</a>
					<div class="card-body">
						<p class="card-text"><%=tmp.getCaption() %></p>
						<p class="card-text"> by <strong><%=tmp.getWriter() %></strong></p>
						<p><small>등록일 <%=tmp.getRegdate() %></small></p>
					</div>
				</div>
				
		</div>
		
		<%} %>
	</div>
	
	
	<nav>
		<ul class="pagination justify-content-center">
			<%if(startPageNum != 1){ %>
				<li class="page-item">
					<a class="page-link" href="list.jsp?pageNum=<%=startPageNum-1 %>&condition=<%=condition%>&keyword=<%=encodedK%>">Prev</a>
				</li>
			<%}else{ %>
				<li class="page-item disabled">
					<a class="page-link" href="javascript:">Prev</a>
				</li>
			<%} %>
			<%for(int i=startPageNum; i<=endPageNum; i++) {%>
				<%if(i==pageNum){ %>
					<li class="page-item active">
						<a class="page-link" href="list.jsp?pageNum=<%=i %>&condition=<%=condition%>&keyword=<%=encodedK%>"><%=i %></a>
					</li>
				<%}else{ %>
					<li class="page-item">
						<a class="page-link" href="list.jsp?pageNum=<%=i %>&condition=<%=condition%>&keyword=<%=encodedK%>"><%=i %></a>
					</li>
				<%} %>
			<%} %>
			<%if(endPageNum < totalPageCount){ %>
				<li class="page-item">
					<a class="page-link" href="list.jsp?pageNum=<%=endPageNum+1 %>&condition=<%=condition%>&keyword=<%=encodedK%>">Next</a>
				</li>
			<%}else{ %>
				<li class="page-item disabled">
					<a class="page-link" href="javascript:">Next</a>
				</li>
			<%} %>
		</ul>
	</nav>
	
</div>
<script>
	// card 이미지의 부모 요소를 선택해서 imgLiquid  동작(jquery plugin 동작) 하기 
	$(".img-wrapper").imgLiquid();
</script>
</body>
</html>