import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class AdminToeicDAO extends DBConn {

	public AdminToeicDAO() {
		
	}
	public static AdminToeicDAO getInstance() {
		return new AdminToeicDAO();
	}
	//등록
	public int memberInsert(AdminVO vo) {
		int cnt = 0;
		try {
			getConn();
				String sql = "insert into tTable(code,tName, tTime, tEx, tPrice) values(?,?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,vo.getCode());
				pstmt.setString(2,vo.gettName());
				pstmt.setString(3,vo.gettTime());
				pstmt.setString(4,vo.gettEx());
				pstmt.setString(5,vo.gettPrice());
				
				cnt = pstmt.executeUpdate();
				
		}catch(Exception e) {e.printStackTrace();}
			
		finally {getClose();}
		
		return cnt;
	}
	//수정
	public int updateRecord(AdminVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update tTable set code=?, tName=? ,tTime=?, tEx=?, tPrice=? where code=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt. setInt(1, vo.getCode());
			pstmt. setString(2, vo.gettName());
			pstmt. setString(3, vo.gettTime());
			pstmt. setString(4, vo.gettEx());
			pstmt. setString(5,vo.gettPrice());
			pstmt. setInt(6, vo.getCode());
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("레코드 수정 실패...");
		}finally {
			getClose();
		}
		return cnt;
	}
	
	//삭제
	public int deleteRecord(int a) {
		int cnt =0;
		try {
			getConn();
			String sql = "delete from tTable where code =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, a);
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return cnt;
	}
	//선택
	public List<AdminVO> selectAllRecord() {
		List<AdminVO> list = new ArrayList<AdminVO>();
		try {
		//DB연결
		getConn();
		System.out.println("getConn 연결");
		String sql = "select code, tName, tTime, tEx, tPrice from tTable";
		pstmt = conn.prepareStatement(sql);
		System.out.println("sql");
		rs= pstmt.executeQuery();
		while(rs.next()) {
			AdminVO vo = new AdminVO();
			
			vo.setCode(rs.getInt(1));
			vo.settName(rs.getString(2));
			vo.settTime(rs.getString(3));
			vo.settEx(rs.getString(4));
			vo.settPrice(rs.getString(5));
			
			list.add(vo);
		}
		
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("레코드선택에러..." + e.getMessage());
	}finally {
		getClose();
	}
		return list;
	}
	
	
	
}
	

