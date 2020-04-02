import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ToeicListTable extends JPanel implements ActionListener {
	
	JTable table;//변수선언
	DefaultTableModel model;
	
	//제목 : 1차원 배열
	String[] title = {"번호","토익과정","수강가능시간","설명","가격"};
	
	Object[][]data= {		};
	JScrollPane sp;
	
	public ToeicListTable() {
		model = new DefaultTableModel(data, title);
		
		table = new JTable(model);
		sp= new JScrollPane(table);
		
		
		add(sp);
				selectAllRecord();
		
		
		setSize(500,300);
		setVisible(true);
	}
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	
		

		
	
	//전체 레코드 선택
		public void selectAllRecord() {
			model.setNumRows(0);//model의 행의 수를 0행으로 만들면 원래 데이터가 없어진다.
			ToeicListDAO dao = new ToeicListDAO();
			List<ToeicListVO> list=dao.selectAllRecord();
			for(int i = 0; i<list.size(); i++) {
				ToeicListVO vo = list.get(i);
				//1차원 배열로 전환
				Object[]record = {vo.getListCode()};
				model.addRow(record);
			}
		}
	public static void main(String[] args) {
		new ToeicListTable();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	

}
