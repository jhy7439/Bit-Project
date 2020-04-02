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
	
	JTable table;//��������
	DefaultTableModel model;
	
	//���� : 1���� �迭
	String[] title = {"��ȣ","���Ͱ���","�������ɽð�","����","����"};
	
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
	
	
		

		
	
	//��ü ���ڵ� ����
		public void selectAllRecord() {
			model.setNumRows(0);//model�� ���� ���� 0������ ����� ���� �����Ͱ� ��������.
			ToeicListDAO dao = new ToeicListDAO();
			List<ToeicListVO> list=dao.selectAllRecord();
			for(int i = 0; i<list.size(); i++) {
				ToeicListVO vo = list.get(i);
				//1���� �迭�� ��ȯ
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
