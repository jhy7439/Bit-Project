import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ToiecDAO extends DBConn {

	public ToiecDAO() {
		
	}
	public static ToiecDAO getInstance() {
		return new ToiecDAO();
	}
	//등록
	public int listInsert(ToiecVO vo) {
		int cnt = 0;
		try {
			getConn();
				String sql = "insert into listTable(ToeicName, ToeicTime, ToeicEx, ToeicPrice) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt = setString(1,vo.getToeicName());
				pstmt = setString(2,vo.getToeicTime());
				pstmt = setString(3, vo.getToeicEx());
				pstmt = setString(4, vo.getToeicPrice());
				
				cnt = pstmt.executeUpdate();
				
		}catch(Exception e) {e.printStackTrace();}
			
		finally {getClose();}
		
		return cnt;
	}
	////////////////////////////////////////////////////////////////////////////////
	//등록
		public int memberInsert(ToiecVO vo) {
			int cnt = 0;
			try {
				getConn();
					String sql = "insert into Toeic(ToeicName, ToeicTime, ToeicEx, ToeicPrice) values(?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt = setString(1,vo.getToeicName());
					pstmt = setString(2,vo.getToeicTime());
					pstmt = setString(3, vo.getToeicEx());
					pstmt = setString(4, vo.getToeicPrice());
					
					cnt = pstmt.executeUpdate();
					
			}catch(Exception e) {e.printStackTrace();}
				
			finally {getClose();}
			
			return cnt;
		}
	/////////////////////////////////////////////////////////////////////////////////
	private PreparedStatement setString(int i, String ToeicName) {
		// TODO Auto-generated method stub
		return null;
	}
	private PreparedStatement setInt(int i, int no) {
		// TODO Auto-generated method stub
		return null;
	}
	//수정
	public int updateRecord(ToiecVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update Toeic set username=?, tel=?, email=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt. setString(1, vo.getToeicName());
			pstmt. setString(2, vo.getToeicTime());
			pstmt. setString(3, vo.getToeicEx());
			pstmt. setString(4,vo.getToeicPrice());
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("레코드 수정 실패...");
		}finally {
			getClose();
		}
		return cnt;
	}
	
	//삭제
	public int deleteRecord(int no) {
		int cnt =0;
		try {
			getConn();
			String sql = "delete from Toeic where no =?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			getClose();
		}
		return cnt;
	}
	//선택
	public List<ToiecVO> selectAllRecord() {
		List<ToiecVO> list = new ArrayList<ToiecVO>();
		try {
		//DB연결
		getConn();
		
		String sql = "select ToeicName,ToeicTime, ToeicEx, ToeicPrice from Toeic";
		pstmt = conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		while(rs.next()) {
			ToiecVO vo = new ToiecVO();
			vo.setToeicName(rs.getString(1));
			vo.setToeicTime(rs.getString(2));
			vo.setToeicEx(rs.getString(3));
			vo.setToeicPrice(rs.getString(4));
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