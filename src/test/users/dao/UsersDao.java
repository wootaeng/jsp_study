package test.users.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import test.users.dto.UsersDto;
import test.util.DbcpBean;
public class UsersDao {
	//static 필드
	private static UsersDao dao;
	//생성자
	private UsersDao() {}
	//Dao 의 참조값을 리턴해주는 static 메소드
	public static UsersDao getInstance() {
		if(dao==null) {
			dao=new UsersDao();
		}
		return dao;
	}
	//프로필 이미지 경로를 수정하는 메소드
	public boolean updateProfile(UsersDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 insert, update, delete 문 구성
			String sql = "update users set profile=?"
					+ " where id=?";		
			
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getProfile());
			pstmt.setString(2, dto.getId());
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
	
	
	//인자로 전달된 아이디가 DB에 존재하는지 여부를 리턴하는 메소드
	public boolean isExist(String id) {
		//아이디가 이미 존재하는지 여부를 담을 지연변수 선언하고 초기값 지정
		boolean isExist=false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			String sql = "select * from users"
					+ " where id=?";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1, id);
			//select 문 수행하고 ResultSet 받아오기
			rs = pstmt.executeQuery();
			//while문 혹은 if문에서 ResultSet으로 부터 data 추출
			if (rs.next()) {
				isExist=true;
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
		//아이디가 이미 존재하는지 여부를 리턴해준다.
		return isExist;
	}
	
	
	//회원의 비밀번호를 수정하는 메소드
	public boolean updatePwd(UsersDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 insert, update, delete 문 구성
			String sql = "update users"
					+ " set pwd=?"
					+ " where id=? and pwd=?";		
			
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1,dto.getNewPwd());
			pstmt.setString(2,dto.getId());
			pstmt.setString(3,dto.getPwd());
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
	
	// 회원 가입 정보를 수정 반영하는 메소드
	public boolean update(UsersDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 insert, update, delete 문 구성
			String sql = "update users"
					+ " set email=?"
					+ " where id=?";		
			
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1,dto.getEmail());
			pstmt.setString(2,dto.getId());
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
	
	
	//인자로 전달된 아이디에 해당하는 가입정보를 삭제하는 메소드
	public boolean delete(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag=0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 insert, update, delete 문 구성
			String sql = "delete from users"
					+ " where id=?";		
			
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1,id );
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
		
	//인자로 전달된 아이디에 해당하는 가입정보를 리턴해주는 메소드
	public UsersDto getData(String id) {
		//회원 정보를 담을 UsersDto 
		UsersDto dto=null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//select 문 작성
			String sql = "SELECT pwd,email,profile,regdate"
					+ " FROM users"
					+ " WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1, id);
			//select 문 수행하고 ResultSet 받아오기
			rs = pstmt.executeQuery();
			//while문 혹은 if문에서 ResultSet 으로 부터 data 추출
			if (rs.next()) {
				dto=new UsersDto();
				dto.setId(id);
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setProfile(rs.getString("profile"));
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
			}
		}
		return dto;
	}


	//인자로 전달된 정보가 유효한 정보인지 여부를 리턴하는 메소드
	public boolean isValid(UsersDto dto) {
		//아이디 비밀번호가 유효한 정보인지 여부를 담을 지역변수 만들고 초기값 부여
		boolean isValid=false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = new DbcpBean().getConn();
			//select 문 작성
			String sql = "SELECT id"
					+ " FROM users"
					+ " WHERE id=? AND pwd=?";
			pstmt = conn.prepareStatement(sql);
			// ? 에 바인딩 할게 있으면 여기서 바인딩한다.
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			//select 문 수행하고 ResultSet 받아오기
			rs = pstmt.executeQuery();
			//while문 혹은 if문에서 ResultSet 으로 부터 data 추출
			if(rs.next()) {//만일 select 된 row 가 있다면
				//유효한 정보임으로 isValid 에 true 를 대입한다. 
				isValid=true;
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
		//아이디가 유효한지 여부를 리턴해준다. 
		return isValid;
	}//isValid()
	
	//회원 정보를 저장하는 메소드
	public boolean insert(UsersDto dto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		try {
			conn = new DbcpBean().getConn();
			//실행할 insert, update, delete 문 구성
			String sql = "INSERT INTO users"
					+ " (id, pwd, email, regdate)"
					+ " VALUES(?, ?, ?, SYSDATE)";
			pstmt = conn.prepareStatement(sql);
			//? 에 바인딩할 내용이 있으면 바인딩한다.
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getEmail());
			flag = pstmt.executeUpdate(); //sql 문 실행하고 변화된 row 갯수 리턴 받기
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
			}
		}
		if (flag > 0) {
			return true;
		} else {
			return false;
		}
	}
}
