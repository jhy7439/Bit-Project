import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TakeList extends JFrame  {

	
	JTable table;//변수선언
	DefaultTableModel model;
	//제목 : 1차원 배열
	String[] title = {"번호","토익과정","수강가능시간","설명","가격"};
	
	Object[][]data= {		};
	JScrollPane sp;
	
	public TakeList() {
		JPanel jp = new JPanel();
			
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp	  = new JScrollPane(table);
		
		//selectAllRecord(); 전체수강 목록
		add(jp);
		add(sp);
		setSize(500,500);
		setVisible(true);
		setTitle("수강목록");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	//전체 레코드 선택
		public void selectAllRecord() {
			
			model.setNumRows(0);//model의 행의 수를 0행으로 만들면 원래 데이터가 없어진다.
			AdminToeicDAO dao  = new AdminToeicDAO();
			List<AdminVO> list=dao.selectAllRecord();
			for(int i = 0; i<list.size(); i++) {
				AdminVO vo = list.get(i);
				//1차원 배열로 전환
				Object[]record = {vo.getCode(),
								  vo.gettName(),
								  vo.gettTime(),
								  vo.gettEx(),
								  vo.gettPrice()};
			 model.addRow(record);
			
			}
		}
		
		
		
		
	public static void main(String[] args) {
		new TakeList();

	}

}
