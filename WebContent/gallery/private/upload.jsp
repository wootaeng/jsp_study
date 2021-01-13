<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@page import="test.gallery.dao.GalleryDao"%>
<%@page import="test.gallery.dto.GalleryDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//Tomcat 서버를 실행했을때 WebContent/upload 폴더의 실제 경로 얻어오기
	String realPath=application.getRealPath("/upload");
	System.out.println("realPath:"+realPath);
	
	File f=new File(realPath);
	if(!f.exists()){
		f.mkdir();
	}
	
	
	
	//최대 업로드 사이즈 설정
	int sizeLimit=1024*1024*50; // 50 MByte
	/*
		WEB-INF/lib/cos.jar 라이브러리가 있으면 아래의 객체를 생성할수 있다.
		
		new MultipartRequest(HttpServletRequest 객체,
				업로드된 파일을 저장할 절대경로,
				최대 업로드 사이즈 제한,
				인코딩설정,
				DefaultFilerenamePolicy 객체)
		
		MultipartRequest 객체가 성공적으로 생성이 된다면 업로드된 파일에 대한 정보도
		추출할수 있다. 
	*/
	// <form enctype="multipart/form-data"> 로 전송된 값은 아래의 객체를 이용해서 추출한다.
	MultipartRequest mr=new MultipartRequest(request,
			realPath,
			sizeLimit,
			"utf-8",
			new DefaultFileRenamePolicy());
	//이미지 설명
	String caption=mr.getParameter("caption");
	//원본 파일명
	String orgFileName=mr.getOriginalFileName("image");
	//upload 폴더에 저장된 파일
	String saveFileName=mr.getFilesystemName("image");
	//업로드한 클라이언트의 아이디
	String writer=(String)session.getAttribute("id");
	
	//업로드된 파일의 정보를 FileDto 에 담고
	GalleryDto dto=new GalleryDto();
	dto.setWriter(writer);
	dto.setCaption(caption);
	dto.setImagePath("/upload/"+saveFileName);
	//DB 에 저장하고
	GalleryDao.getInstance().insert(dto);
	//목록 보기로 리다일렉트 이동 응답
	String cPath=request.getContextPath();
	response.sendRedirect(cPath+"/gallery/list.jsp");
	
	// /gallery/list.jsp 페이지로 리다일렉트 이동해서 업로드된 이미지 목록을 
	// 보여주는 프로그래밍을 해보세요.

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/gallery/private/upload.jsp</title>
</head>
<body>
	
</body>
</html>