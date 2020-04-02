
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class TopleDAO extends DBConn{

	public TopleDAO() {
		
	}

	public static TopleDAO getInstance() {
		return new TopleDAO();
	}
	//���
	public int memberInsert(TopleVO vo) {
		int cnt = 0;
		try {
			getConn();
				String sql ="insert into jusorok(TopleName, TopleTime , TopleEx, ToplePrice) values(?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt = setString(1,vo.getTopleName());
				pstmt = setString(2,vo.getTopleTime());
				pstmt = setString(3,vo.getTopleEx());
				pstmt = setString(4,vo.getToplePrice());
				
				cnt= pstmt.executeUpdate();
		}catch(Exception e) {e.printStackTrace();}
		
		finally {getClose();}
		return cnt;
	}
	private PreparedStatement setString(int i,String TopleName) {
		return null;
	}
	//����
	public int updateRecord(TopleVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update jusorok set TopleName=?, TopleTime=?, TopleEx=? ToplePrice=?";
			pstmt = conn.prepareStatement(sql);
			pstmt. setString(1, vo.getTopleName());
			pstmt. setString(2, vo.getTopleTime());
			pstmt. setString(3, vo.getTopleEx());
			pstmt. setString(4,vo.getToplePrice());
			
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
	public List<TopleVO> selectAllRecord(){
		List<TopleVO> list = new ArrayList<TopleVO>();
		try {
		//DB����
		getConn();
		
		String sql = "select TopleName,TopleTime,TopleEx,ToplePrice from Tople";
		pstmt = conn.prepareStatement(sql);
		rs= pstmt.executeQuery();
		while(rs.next()) {
			TopleVO vo = new TopleVO();
			vo.setTopleName(rs.getString(1));
			vo.setTopleTime(rs.getString(2));
			vo.setTopleEx(rs.getString(3));
			vo.setToplePrice(rs.getString(4));
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
	
	
	
	
	
	
	
	
	
	
