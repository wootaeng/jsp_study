package test.gallery.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import test.file.dto.FileDto;
import test.gallery.dto.GalleryDto;
import test.util.DbcpBean;

public class GalleryDao {
	private static GalleryDao dao;
	private GalleryDao() {}
	public static GalleryDao getInstance() {
		if(dao==null) {
			dao=new GalleryDao();
		}
		return dao;
	}
	
	//이미지 한개의 정보 리턴하는 메소드
	   public GalleryDto getData(int num) {
	      GalleryDto dto=null;
	      Connection conn = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         conn = new DbcpBean().getConn();
	         //select문 작성
	         String sql = "SELECT num,writer,caption,imagePath,regdate"
	               + " FROM board_gallery"
	               + " WHERE num=?";
	         pstmt = conn.prepareStatement(sql);
	         // ? 에 바인딩 할게 있으면 여기서 바인딩한다.
	         pstmt.setInt(1, num);
	         //select 문 수행하고 ResultSet 받아오기
	         rs = pstmt.executeQuery();
	         //while문 혹은 if문에서  ResultSet으로 부터 data 추출
	         if(rs.next()) {
	            dto=new GalleryDto();
	            dto.setNum(num);
	            dto.setWriter(rs.getString("writer"));
	            dto.setCaption(rs.getString("caption"));
	            dto.setImagePath(rs.getString("imagePath"));
	            dto.setRegdate(rs.getString("regdate"));
	         }
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            if (rs != null)
	               rs.close();
	            if (pstmt != null)
	               pstmt.close();
	            if (conn != null)
	               conn.close();
	         } catch (Exception e) {
	            e.printStackTrace();
	         }
	      }
	      return dto;
	   }
	
	
	
	//제목 파일명 검색인 경우
	public int getCountCI(GalleryDto dto) {
		//글의 갯수를 담을 지역변수 
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//select 문 작성
			String sql = "SELECT NVL(MAX(ROWNUM), 0) AS num "
					+ " FROM board_gallery"
					+ " WHERE caption LIKE '%'||?||'%'"
					+ " OR imagePath LIKE '%'||?||'%'";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getCaption());
			pstmt.setString(2, dto.getImagePath());
			//select 문 수행하고 ResultSet 받아오기
			rs = pstmt.executeQuery();
			//while문 혹은 if문에서 ResultSet 으로 부터 data 추출
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return count;
	}
	public int getCountC(GalleryDto dto) {
		//글의 갯수를 담을 지역변수 
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//select 문 작성
			String sql = "SELECT NVL(MAX(ROWNUM), 0) AS num "
					+ " FROM board_gallery"
					+ " WHERE caption LIKE '%'||?||'%'";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getCaption());
			//select 문 수행하고 ResultSet 받아오기
			rs = pstmt.executeQuery();
			//while문 혹은 if문에서 ResultSet 으로 부터 data 추출
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return count;
	}
	public int getCountW(GalleryDto dto) {
		//글의 갯수를 담을 지역변수 
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//select 문 작성
			String sql = "SELECT NVL(MAX(ROWNUM), 0) AS num "
					+ " FROM board_gallery"
					+ " WHERE writer LIKE '%'||?||'%'";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getWriter());
			//select 문 수행하고 ResultSet 받아오기
			rs = pstmt.executeQuery();
			//while문 혹은 if문에서 ResultSet 으로 부터 data 추출
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return count;
	}
	
	
	
	
	
	
	
	//전체 row의 갯수를 리턴하는 메소드
	public int getCount() {
		//글의 갯수를 담을 지역변수
		int count=0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT NVL(MAX(ROWNUM), 0) AS num"
					+ " FROM board_gallery";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 여기서 바인딩한다.

			//select 문 수행하고 ResultSet 받아오기
			rs = pstmt.executeQuery();
			//while문 혹은 if문에서 ResultSet으로 부터 data 추출
			if (rs.next()) {
				count=rs.getInt("num");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return count;
	}
	
	
	
	
	//업로드된 사진 하나의 정보를 저장하는 메소드
	public boolean insert(GalleryDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 insert, update, delete 문 구성
			String sql = "INSERT INTO board_gallery"
					+ " (num,writer,caption,imagePath,regdate)"
					+ " VALUES(board_gallery_seq.NEXTVAL,?,?,?,SYSDATE)";		
			
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getCaption());
			pstmt.setString(3, dto.getImagePath());
			
			
			//sql 문 실행하고 변화된 row 객수 리턴 받기
			flag = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if (pstmt != null)pstmt.close();
				if (conn != null)conn.close();
			} catch (Exception e) {
			}
		}
		if(flag>0) {
			return true;
		}else {
			return false;
		}
	}
	
	//제목 파일명 검색인 경우에 파일 목록 리턴
		public List<GalleryDto> getListCI(GalleryDto dto){
			List<GalleryDto> list=new ArrayList<GalleryDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = new DbcpBean().getConn();
				String sql = "SELECT *" + 
						"		FROM" + 
						"		    (SELECT result1.*, ROWNUM AS rnum" + 
						"		    FROM" + 
						"		        (SELECT num,writer,caption,imagePath,regdate" + 
						"		        FROM board_galley"+ 
						" 				where caption Like '%'||?||'%'" +
						"				or imagePath Like '%'||?||'%'"+					
						"		        ORDER BY num DESC) result1)" + 
						"		WHERE rnum BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				//? 에 바인딩 할게 있으면 여기서 바인딩한다.
				pstmt.setString(1, dto.getCaption());
				pstmt.setString(2, dto.getImagePath());
				pstmt.setInt(3, dto.getStartRowNum());
				pstmt.setInt(4, dto.getEndRowNum());
				//select 문 수행하고 ResultSet 받아오기
				rs = pstmt.executeQuery();
				//while문 혹은 if문에서 ResultSet으로 부터 data 추출
				while (rs.next()) {
					GalleryDto dto2=new GalleryDto();
					dto2.setNum(rs.getInt("num"));
					dto2.setWriter(rs.getString("writer"));
					dto2.setCaption(rs.getString("caption"));
					dto2.setImagePath(rs.getString("imagePath"));
					dto2.setRegdate(rs.getString("regdate"));
					list.add(dto2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
				}
			}
			return list;
		}
		//제목 검색인 경우에 파일 목록 리턴
		public List<GalleryDto> getListC(GalleryDto dto){
			List<GalleryDto> list=new ArrayList<GalleryDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = new DbcpBean().getConn();
				String sql = "SELECT *" + 
						"		FROM" + 
						"		    (SELECT result1.*, ROWNUM AS rnum" + 
						"		    FROM" + 
						"		        (SELECT num,writer,caption,imagePath,regdate" + 
						"		        FROM board_gallery"+ 
						" 				where cpation Like '%'||?||'%'" +
						"		        ORDER BY num DESC) result1)" + 
						"		WHERE rnum BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				//? 에 바인딩 할게 있으면 여기서 바인딩한다.
				pstmt.setString(1, dto.getCaption());
				pstmt.setInt(2, dto.getStartRowNum());
				pstmt.setInt(3, dto.getEndRowNum());
				//select 문 수행하고 ResultSet 받아오기
				rs = pstmt.executeQuery();
				//while문 혹은 if문에서 ResultSet으로 부터 data 추출
				while (rs.next()) {
					GalleryDto dto2=new GalleryDto();
					dto2.setNum(rs.getInt("num"));
					dto2.setWriter(rs.getString("writer"));
					dto2.setCaption(rs.getString("caption"));
					dto2.setImagePath(rs.getString("imgaPath"));
					dto2.setRegdate(rs.getString("regdate"));
					list.add(dto2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
				}
			}
			return list;
		}
		//작성자 검색인 경우에 파일 목록 리턴
		public List<GalleryDto> getListW(GalleryDto dto){
			List<GalleryDto> list=new ArrayList<GalleryDto>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = new DbcpBean().getConn();
				String sql = "SELECT *" + 
						"		FROM" + 
						"		    (SELECT result1.*, ROWNUM AS rnum" + 
						"		    FROM" + 
						"		        (SELECT num,writer,caption,imagePath,regdate" + 
						"		        FROM board_gallery"+ 
						" 				where writer Like '%'||?||'%'" +
						"		        ORDER BY num DESC) result1)" + 
						"		WHERE rnum BETWEEN ? AND ?";
				pstmt = conn.prepareStatement(sql);
				//? 에 바인딩 할게 있으면 여기서 바인딩한다.
				pstmt.setString(1, dto.getWriter());
				pstmt.setInt(2, dto.getStartRowNum());
				pstmt.setInt(3, dto.getEndRowNum());
				//select 문 수행하고 ResultSet 받아오기
				rs = pstmt.executeQuery();
				//while문 혹은 if문에서 ResultSet으로 부터 data 추출
				while (rs.next()) {
					GalleryDto dto2=new GalleryDto();
					dto2.setNum(rs.getInt("num"));
					dto2.setWriter(rs.getString("writer"));
					dto2.setCaption(rs.getString("caption"));
					dto2.setImagePath(rs.getString("imagePath"));
					dto2.setRegdate(rs.getString("regdate"));
					list.add(dto2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (pstmt != null)
						pstmt.close();
					if (conn != null)
						conn.close();
				} catch (Exception e) {
				}
			}
			return list;
		}
	
	
	
	//업로드된 사진의 정보를 리턴하는 메소드
	public List<GalleryDto> getList(GalleryDto dto){
		List<GalleryDto> list=new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = "SELECT *" + 
					"		FROM" + 
					"		    (SELECT result1.*, ROWNUM AS rnum" + 
					"		    FROM" + 
					"		        (SELECT num,writer,caption,imagePath,regdate" + 
					"		        FROM board_gallery" + 
					"		        ORDER BY num DESC) result1)" + 
					"		WHERE rnum BETWEEN ? AND ?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setInt(1, dto.getStartRowNum());
			pstmt.setInt(2, dto.getEndRowNum());
			//select 문 수행하고 ResultSet 받아오기
			rs = pstmt.executeQuery();
			//while문 혹은 if문에서 ResultSet으로 부터 data 추출
			while (rs.next()) {
				GalleryDto dto2=new GalleryDto();
				dto2.setNum(rs.getInt("num"));
				dto2.setWriter(rs.getString("writer"));
				dto2.setCaption(rs.getString("caption"));
				dto2.setImagePath(rs.getString("imagepath"));
				dto2.setRegdate(rs.getString("regdate"));
				list.add(dto2); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		return list;
	}
}
