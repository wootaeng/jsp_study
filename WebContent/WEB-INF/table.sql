
-- 사용자(회원) 정보를 저장할 테이블
create table users(
	id varchar2(100) primary key,
	pwd varchar2(100) not null,
	email varchar2(100),
	profile varchar2(100), --프로필 이미지 경로를 저장할 칼럼
	regdate date -- 가입일
);

-- 게시글을 저장할 테이블
create table board_cafe(
	num number primary key, --글 번호
	writer varcjar2(100) not null, --작성자(로그인된 아이디)
	title varchar2(100) not null, --제목
	content clob, -- 글 내용
	viewCount number, --조회수
	regdate date -- 글 작성일
);

--게시글의 번호를 얻어낼 시퀀스
create sequence board_cafe_seq;

--업로드된 정보를 저장할 테이블
create table board_file(
	num number primary key
	writer varchar2(100) not null,
	title varchar2(100) not null,
	orgFileName varchar2(100) not null, --원본 파일명
	saveFileName varchar2(100) not null, --서버에 실제로 저장된 파일명
	fileSize number not null, --파일크기
	regdate date
);


create sequence board_file_seq;



create table board_gallery(
	num number primary key,
	writer varchar2(100),
	caption varchar2(100),
	iamgePath varchar2(100),
	regdate date
);


create sequence board_gallery_seq;





