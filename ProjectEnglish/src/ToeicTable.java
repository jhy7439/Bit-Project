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
	
	JTable table;//��������
	DefaultTableModel model;
	JButton jb;
	//���� : 1���� �迭
	String[] title = {"���Ͱ���","�������ɽð�","����","����"};
	
	Object[][]data= {		};
	JScrollPane sp;
	
	public ToeicTable() {
		model = new DefaultTableModel(data, title);
		jb= new JButton("������û");
		table = new JTable(model);
		sp= new JScrollPane(table);
		
		
		add(sp);
		add(jb);
		selectAllRecord();
		
		
		setSize(500,300);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	
		
	//������û ��ư �̺�Ʈ ó��
	jb.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			UserToeicTable us = new UserToeicTable();
			//dispose();
		} 
		
	});
	}
		
	
	//��ü ���ڵ� ����
		public void selectAllRecord() {
			model.setNumRows(0);//model�� ���� ���� 0������ ����� ���� �����Ͱ� ��������.
			ToiecDAO dao = new ToiecDAO();
			List<ToiecVO> list=dao.selectAllRecord();
			for(int i = 0; i<list.size(); i++) {
				ToiecVO vo = list.get(i);
				//1���� �迭�� ��ȯ
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
