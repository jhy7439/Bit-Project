import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ToeicTable extends JPanel implements ActionListener {
	
	JTable table;//변수선언
	DefaultTableModel model;
	JButton jb;
	//제목 : 1차원 배열
	String[] title = {"토익과정","수강가능시간","설명","가격"};
	
	Object[][]data= {		};
	JScrollPane sp;
	
	public ToeicTable() {
		model = new DefaultTableModel(data, title);
		jb= new JButton("수강신청");
		table = new JTable(model);
		sp= new JScrollPane(table);
		
		
		add(sp);
		add(jb);
		selectAllRecord();
		
		
		setSize(500,300);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	
		
	//수강신청 버튼 이벤트 처리
	jb.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			UserToeicTable us = new UserToeicTable();
			//dispose();
		} 
		
	});
	}
		
	
	//전체 레코드 선택
		public void selectAllRecord() {
			model.setNumRows(0);//model의 행의 수를 0행으로 만들면 원래 데이터가 없어진다.
			ToiecDAO dao = new ToiecDAO();
			List<ToiecVO> list=dao.selectAllRecord();
			for(int i = 0; i<list.size(); i++) {
				ToiecVO vo = list.get(i);
				//1차원 배열로 전환
				Object[]record = {vo.getToeicName(), vo.getToeicTime(),vo.getToeicEx(),vo.getToeicPrice()};
				model.addRow(record);
			}
		}
	public static void main(String[] args) {
		new ToeicTable();

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}


	

}
