import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class ToeicListDAO extends DBConn {

	public ToeicListDAO() {
		
	}
	public static ToeicListDAO getInstance() {
		return new ToeicListDAO();
	}
	//���
	public int memberInsert(ToeicListVO vo) {
		int cnt = 0;
		try {
			getConn();
				String sql = "insert into ToeicList(listCode) values(?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,vo.getListCode());
				
				
				cnt = pstmt.executeUpdate();
				
		}catch(Exception e) {e.printStackTrace();}
			
		finally {getClose();}
		
		return cnt;
	}
	//����
	public int updateRecord(ToeicListVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update tTable set listCode=? where listCode=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt. setInt(1, vo.getListCode());
			
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("���ڵ� ���� ����...");
		}finally {
			getClose();
		}
		return cnt;
	}
	
	//����
	public int deleteRecord(int a) {
		int cnt =0;
		try {
			getConn();
			String sql = "delete from ToeicList where lstCode =?";
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
	//����
	public List<ToeicListVO> selectAllRecord() {
		List<ToeicListVO> list = new ArrayList<ToeicListVO>();
		try {
		//DB����
		getConn();
		System.out.println("getConn ����");
		String sql = "select listCode from ToeicList";
		pstmt = conn.prepareStatement(sql);
		System.out.println("sql");
		rs= pstmt.executeQuery();
		while(rs.next()) {
			ToeicListVO vo = new ToeicListVO();
			
			vo.setListCode(rs.getInt(1));
			
			list.add(vo);
		}
		
	}catch(Exception e) {
		e.printStackTrace();
		System.out.println("���ڵ弱�ÿ���..." + e.getMessage());
	}finally {
		getClose();
	}
		return list;
	}
	
	public void course() {
		try {
			getConn();
			String sql = "insert into ToeicList(listCode, member_code, code) values(?)";
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1,vo.getListCode());
			pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
			
		finally {getClose();}
	}
	
}
	