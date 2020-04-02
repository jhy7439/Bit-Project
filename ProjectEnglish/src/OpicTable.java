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
	
	JTable table;//��������
	DefaultTableModel model;
	JButton jb;//������û ��ư����
	//���� : 1���� �迭
	String[] title = {"���Ȱ���","�������ɽð�","����","����"};
	
	Object[][]data= {		};
	JScrollPane sp;
	
	public OpicTable() {
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp= new JScrollPane(table);
		jb = new JButton("������û");
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
				TakeList tl = new TakeList();
				//dispose();
			} 
			
		});
		}	
		//��ü ���ڵ� ����
		public void selectAllRecord() {
			model.setNumRows(0);//model�� ���� ���� 0������ ����� ���� �����Ͱ� ��������.
			OpicDAO dao = new OpicDAO();
			List<OpicVO> list=dao.selectAllRecord();
			for(int i = 0; i<list.size(); i++) {
				OpicVO vo = list.get(i);
				//1���� �迭�� ��ȯ
				Object[]record = {vo.getOpicName(), vo.getOpicTime(),vo.getOpicEx(),vo.getOpicPrice()};
				model.addRow(record);
			}
		}
	public static void main(String[] args) {
		new OpicTable();

	}

}
