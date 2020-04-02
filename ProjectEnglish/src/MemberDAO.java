
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
	//���
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
	//����
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
			System.out.println("���ڵ� ���� ����...");
		}finally {
			getClose();
		}
		return cnt;
	}
	
	//����
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
	//����
	public List<MemberVO> selectAllRecord() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
		//DB����
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
		System.out.println("���ڵ弱�ÿ���..." + e.getMessage());
	}finally {
		getClose();
	}
		return list;
	}
	//�α���
	public int loginChk(String id, String pw) {
		
		
		
		String SQL = "select pw from member where id = ?";
		try {
			getConn();
			pstmt = conn.prepareStatement(SQL);
			ResultSet rs = null;
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			//�ߺ����̵� ������ 1�� ��ȯ
			if (rs.next()) {
				if (rs.getString(1).equals(pw)) {
					return 1; //��������
				} else {
					return 0; //�ش� ���̵� ����
			}
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}

		return -2; // �����ͺ��̽� ������ �ǹ�
		
	}
	
	//����
	public List<MemberVO> selectID_PW() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		try {
		//DB����
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
		System.out.println("���ڵ弱�ÿ���..." + e.getMessage());
	}finally {
		getClose();
	}
		return list;
	}
}










	

