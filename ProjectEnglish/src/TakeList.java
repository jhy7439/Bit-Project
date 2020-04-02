import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TakeList extends JFrame  {

	
	JTable table;//��������
	DefaultTableModel model;
	//���� : 1���� �迭
	String[] title = {"��ȣ","���Ͱ���","�������ɽð�","����","����"};
	
	Object[][]data= {		};
	JScrollPane sp;
	
	public TakeList() {
		JPanel jp = new JPanel();
			
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp	  = new JScrollPane(table);
		
		//selectAllRecord(); ��ü���� ���
		add(jp);
		add(sp);
		setSize(500,500);
		setVisible(true);
		setTitle("�������");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	//��ü ���ڵ� ����
		public void selectAllRecord() {
			
			model.setNumRows(0);//model�� ���� ���� 0������ ����� ���� �����Ͱ� ��������.
			AdminToeicDAO dao  = new AdminToeicDAO();
			List<AdminVO> list=dao.selectAllRecord();
			for(int i = 0; i<list.size(); i++) {
				AdminVO vo = list.get(i);
				//1���� �迭�� ��ȯ
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
