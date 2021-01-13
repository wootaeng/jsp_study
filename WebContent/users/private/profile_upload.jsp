<%@page import="test.users.dto.UsersDto"%>
<%@page import="test.users.dao.UsersDao"%>
<%@page import="java.io.File"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
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
	int sizeLimit=1024*1024*5; // 5 MByte
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
	//업로드된 이미지의 우너본 파일명
	String orgFileName=mr.getOriginalFileName("image");
	//upload 폴더에 실제 저장된 파일명
	String saveFileName=mr.getFilesystemName("image");
	
	//로그인 된 아이디
	String id=(String)session.getAttribute("id");
	//저장된 파일명을 이용해서 프로팊 이미지 경로를 구성한다
	String profile="/upload/"+saveFileName;
	//UsersDto 객체에 담아서
	UsersDto dto=new UsersDto();
	dto.setId(id);
	dto.setProfile(profile);
	//DB 에 반영하기
	UsersDao.getInstance().updateProfile(dto);
	//개인정보 수정 페이지로 리다일렉트
	String cPath=request.getContextPath();
	response.sendRedirect(cPath+"/users/private/updateform.jsp");
%>
