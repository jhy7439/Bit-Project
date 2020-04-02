import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OpicTable extends JPanel {
	
	JTable table;//변수선언
	DefaultTableModel model;
	JButton jb;//수강신청 버튼생성
	//제목 : 1차원 배열
	String[] title = {"오픽과정","수강가능시간","설명","가격"};
	
	Object[][]data= {		};
	JScrollPane sp;
	
	public OpicTable() {
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp= new JScrollPane(table);
		jb = new JButton("수강신청");
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
				TakeList tl = new TakeList();
				//dispose();
			} 
			
		});
		}	
		//전체 레코드 선택
		public void selectAllRecord() {
			model.setNumRows(0);//model의 행의 수를 0행으로 만들면 원래 데이터가 없어진다.
			OpicDAO dao = new OpicDAO();
			List<OpicVO> list=dao.selectAllRecord();
			for(int i = 0; i<list.size(); i++) {
				OpicVO vo = list.get(i);
				//1차원 배열로 전환
				Object[]record = {vo.getOpicName(), vo.getOpicTime(),vo.getOpicEx(),vo.getOpicPrice()};
				model.addRow(record);
			}
		}
	public static void main(String[] args) {
		new OpicTable();

	}

}
