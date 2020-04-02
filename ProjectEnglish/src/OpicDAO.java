
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class OpicDAO extends DBConn{
	
	public OpicDAO() {
		
	}

	public static OpicDAO getInstance() {
		return new OpicDAO();
	}
	//���
	public int memberInsert(OpicVO vo) {
		int cnt = 0;
		try {
			getConn();
				String sql ="insert into jusorok(OpicName, OpicTime , OpicEx, OpicPrice) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt = setString(1,vo.getOpicName());
				pstmt = setString(2,vo.getOpicTime());
				pstmt = setString(3,vo.getOpicEx());
				pstmt = setString(4,vo.getOpicPrice());
				
				cnt= pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		
		finally {getClose();}
		return cnt;
	}
	private PreparedStatement setString(int i,String OpicName) {
		return null;
	}
	//����
	public int updateRecord(OpicVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update jusorok set OpicName=?, OpicTime=?, OpicEx=? OpicPrice=?";
			pstmt = conn.prepareStatement(sql);
			pstmt. setString(1, vo.getOpicName());
			pstmt. setString(2, vo.getOpicTime());
			pstmt. setString(3, vo.getOpicEx());
			pstmt. setString(4,vo.getOpicPrice());
			
			cnt = pstmt.executeUpdate();
			
		}catch(Exception e) {
			System.out.println("���ڵ� ���� �����Ͽ����ϴ�.");
		}finally {
			getClass();
		}
		return cnt;
	}
	
	//����
	public int deleteRecord(int no) {
		int cnt = 0;
		try {
			getConn();
			String sql = "delete from jusorok where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			cnt = pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();
		
		}finally {
			getClose();
		}
		return cnt;
}
	//����
	public List<OpicVO> selectAllRecord(){
		List<OpicVO> list = new ArrayList<OpicVO>();
		try {
		//DB����
		getConn();
		
		String sql = "select OpicName,OpicTime,OpicEx,OpicPrice from Opic";
		pstmt = conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		while(rs.next()) {
			OpicVO vo = new OpicVO();
			vo.setOpicName(rs.getString(1));
			vo.setOpicTime(rs.getString(2));
			vo.setOpicEx(rs.getString(3));
			vo.setOpicPrice(rs.getString(4));
			list.add(vo);
		}
		}catch(Exception e) {
			System.out.println("���ڵ弱�ÿ���" + e.getMessage());
		}finally {
			getClose();
		}
			return list;
	}
}
	
