
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DBConn {

	public MemberDAO() {
		
	}
	public static MemberDAO getInstance() {
		return new MemberDAO();
	}
	//등록
	public int memberInsert(MemberVO vo) {
		int cnt = 0;
		try {
			getConn();
				String sql = "insert into member(member_sq, name, id, pw, address, tel, email) values(member_sq.NEXTVAL,?,?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,vo.getMember_sq());
				pstmt.setString(1,vo.getName());
				pstmt.setString(2, vo.getId());
				pstmt.setString(3, vo.getPw());
				pstmt.setString(4, vo.getAddress());
				pstmt.setString(5, vo.getTel());
				pstmt.setString(6, vo.getEmail());
				
				cnt = pstmt.executeUpdate();
				
		}catch(Exception e) {e.printStackTrace();}
			
		finally {getClose();}
		
		return cnt;
	}
	//수정
	public int updateRecord(MemberVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update member set member_sq=? ,name=?, id=?, pw=? address=?, tel=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt. setInt(1, vo.getMember_sq());
			pstmt. setString(2, vo.getName());
			pstmt. setString(3, vo.getId());
			pstmt. setString(4,vo.getPw());
			pstmt. setString(5,vo.getAddress());
			pstmt. setString(6,vo.getTel());
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("레코드 수정 실패...");
		}finally {
			getClose();
		}
		return cnt;
	}
	
	//삭제
	public int deleteRecord(int member_sq) {
		int cnt =0;
		try {
			getConn();
			String sql = "delete from member where member_sq =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member_sq);
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return cnt;
	}
	//선택
	public List<MemberVO> selectAllRecord() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
		//DB연결
		getConn();
		
		String sql = "select member_sq, name, id, pw, address,tel, email from member order by member_sq";
		pstmt = conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		while(rs.next()) {
			MemberVO vo = new MemberVO();
			vo.setMember_sq(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setId(rs.getString(3));
			vo.setPw(rs.getString(4));
			vo.setAddress(rs.getString(5));
			vo.setTel(rs.getString(6));
			list.add(vo);
		}
		
	}catch(Exception e) {
		System.out.println("레코드선택에러..." + e.getMessage());
	}finally {
		getClose();
	}
		return list;
	}
	//로그인
	public int loginChk(String id, String pw) {
		
		
		
		String SQL = "select pw from member where id = ?";
		try {
			getConn();
			pstmt = conn.prepareStatement(SQL);
			ResultSet rs = null;
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			//중복아이디가 있으면 1로 변환
			if (rs.next()) {
				if (rs.getString(1).equals(pw)) {
					return 1; //인증성공
				} else {
					return 0; //해당 아이디 없음
			}
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}

		return -2; // 데이터베이스 오류를 의미
		
	}
	
	//선택
	public List<MemberVO> selectID_PW() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
		//DB연결
		getConn();
		
		String sql = "select iD, PW from member";
		pstmt = conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		
		while(rs.next()) {
			MemberVO vo = new MemberVO();
			
			vo.setId(rs.getString(1));
			vo.setPw(rs.getString(2));
	
			list.add(vo);
		}
		
	}catch(Exception e) {
		System.out.println("레코드선택에러..." + e.getMessage());
	}finally {
		getClose();
	}
		return list;
	}
}










	

