import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class UserToeicTable extends JPanel implements ActionListener {
	//JPanel northForm = new JPanel(new BorderLayout());
		
		
	JButton jb;
	JTable table;//��������
	DefaultTableModel model;
	//���� : 1���� �迭
	String[] title = {"��ȣ","���Ͱ���","�������ɽð�","����","����"};
			
	Object[][]data= {		};
	
	JScrollPane sp;
	
	public UserToeicTable() {
		
		//add(northForm,"North");
			
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp= new JScrollPane(table);
		jb= new JButton("������û");
		
		add(sp, "Center");
		add(jb);
		selectAllRecord();//��ü��� �ٷκ���
		
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
		
	table.addMouseListener(new MouseAdapter() {//���콺 �̺�Ʈ ���콺�� Ŭ���Ͽ� ������ ���� �ְ� �Ѵ� 
			

			public void mouseReleased(MouseEvent me) {//������ ����
				
				
				int click = me.getButton();//int  1:���ʹ�ư,2:��,3:�����ʹ�ư
				if(click==1){
					
					//������ ���� index�� ���Ѵ� 
					int rowIdx = table.getSelectedRow();//row
					ToeicListTable tl = new ToeicListTable();
				}
			}
			
		});	
	}
	//��ü ���ڵ� ����	
	public void selectAllRecord() {
		model.setNumRows(0);
		AdminToeicDAO dao = new AdminToeicDAO();
		List<AdminVO> list = dao.selectAllRecord();
		for(int i = 0; i<list.size(); i++) {
			AdminVO vo = list.get(i);
			//1���� �迭�� ��ȯ
			Object[] record = {vo.getCode(),vo.gettName(),vo.gettTime(),vo.gettEx(),vo.gettPrice()};
			model.addRow(record);
		}
	}
	
	public static void main(String[] args) {
		new UserToeicTable();

	}





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
