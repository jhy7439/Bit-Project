import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.SelectableChannel;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TopleTableList extends JPanel{
	JTable table;
	DefaultTableModel model;
	JButton jb;
	//����: 1���� �迭
	String[] title = {"���ð���", "�������ɽð�","����","����"};
	
	Object[][]data= {     };
	JScrollPane sp;
	
	public TopleTableList() {
		model = new DefaultTableModel(data,title);
		table = new JTable(model);
		sp = new JScrollPane(table);
		jb = new JButton("������û");
		add(sp);
		add(jb);
		selectAllRecord();
		
		setSize(500,300);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//������û ��ư �̺�Ʈ ó��
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TakeList tl = new TakeList();
				//dispose();
			} 
			
		});
		//���콺 �̺�Ʈ
		table.addMouseListener(new MouseAdapter() {//���콺 �̺�Ʈ ���콺�� Ŭ���Ͽ� ������ ���� �ְ� �Ѵ� 
			public void mouseReleased(MouseEvent me) {//������ ����
				//���콺 ��ư ���� �˾Ƴ���
				
				int click = me.getButton();//int  1:���ʹ�ư,2:��,3:�����ʹ�ư
				if(click==1){
					//������ ���� index�� ���Ѵ� 
					int rowIdx = table.getSelectedRow();//row
					 
					table.getValueAt(rowIdx, 0);//��ȣ
					table.getValueAt(rowIdx, 1);//�̸�
					table.getValueAt(rowIdx, 2);//����ó
					table.getValueAt(rowIdx,3);//�̸���
					
			
				}
			}
		});
		
		}
		
	
	//��ü ���ڵ� ����
	 public void selectAllRecord() {
		model.setNumRows(0);//���� ���� 0������ ����� ���� �����Ͱ� �������.
		TopleDAO dao = new TopleDAO();
		List<TopleVO> list = dao.selectAllRecord();
		for(int i=0; i<list.size(); i++) {
			TopleVO vo = list.get(i);
			//1���� �迭�� ��ȯ
			Object[] record = {vo.getTopleName(), vo.getTopleTime(), vo.getTopleEx(),
							   vo.getToplePrice()};
			model.addRow(record);
			}
		}
	

	public static void main(String[] args) {
		new TopleTableList();

	}

}
