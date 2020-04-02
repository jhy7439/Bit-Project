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

public class AdminMemberTable extends JPanel implements ActionListener {
	JPanel northForm = new JPanel(new BorderLayout());
		JPanel formLbl = new JPanel(new GridLayout(7,1));
			JLabel ToeicCode = new JLabel("��ȣ");
			JLabel ToeicName = new JLabel("�̸�");
			JLabel ToeicTime = new JLabel("���̵�");
			JLabel ToeicEx = new JLabel("��й�ȣ");
			JLabel ToeicAddress = new JLabel("�ּ�");
			JLabel ToeicPrice = new JLabel("�̸���");
			JLabel memTel = new JLabel("����ó");
			
		JPanel formCenter = new JPanel(new GridLayout(7,1));
			JTextField codeTf= new JTextField();
			JTextField nameTf= new JTextField();
			JTextField timeTf= new JTextField();
			JTextField exTf= new JTextField();
			JTextField addressTf= new JTextField();
			JTextField priceTf= new JTextField();
			JTextField telTf= new JTextField();
			
		JToolBar tb = new JToolBar();
			JButton addBtn = new JButton("���");
			JButton editBtn = new JButton("����");
			JButton delBtn = new JButton("����");
			
	
	JTable table;//��������
	DefaultTableModel model;
	//���� : 1���� �迭
	String[] title = {"��ȣ","�̸�","���̵�","��й�ȣ", "�ּ�","����ó","�̸���"};
			
	Object[][]data= {		};
	
	JScrollPane sp;
	
	public AdminMemberTable() {
		//super("ȸ�� �������");
		//����
		tb.add(addBtn); tb.add(editBtn);tb.add(delBtn);
		northForm.add(tb, "North");
		//�� �ֱ�
		northForm.add(formLbl, BorderLayout.WEST);
			formLbl.add(ToeicCode);
			formLbl.add(ToeicName);
			formLbl.add(ToeicTime);
			formLbl.add(ToeicEx);
			formLbl.add(ToeicAddress);
			formLbl.add(memTel);
			formLbl.add(ToeicPrice);
			
			
			
		//�ؽ�Ʈ �ʵ� �ֱ�
			northForm.add(formCenter);
			formCenter.add(codeTf);
			formCenter.add(nameTf);
			formCenter.add(timeTf);
			formCenter.add(exTf);
			formCenter.add(priceTf);
			formCenter.add(telTf);
			formCenter.add(addressTf);
		add(northForm,"North");
			
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp= new JScrollPane(table);
		
		add(sp, "Center");
		
		selectAllRecord();//��ü��� �ٷκ���
		
		setSize(500,300);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	table.addMouseListener(new MouseAdapter() {//���콺 �̺�Ʈ ���콺�� Ŭ���Ͽ� ������ ���� �ְ� �Ѵ� 
			

			public void mouseReleased(MouseEvent me) {//������ ����
				//���콺 ��ư ���� �˾Ƴ���
				
				int click = me.getButton();//int  1:���ʹ�ư,2:��,3:�����ʹ�ư
				if(click==1){
					//������ ���� index�� ���Ѵ� 
					int rowIdx = table.getSelectedRow();//row
					
					codeTf.setText(String.valueOf(table.getValueAt(rowIdx, 0)));
					nameTf.setText(String.valueOf(table.getValueAt(rowIdx, 1)));
					timeTf.setText((String)table.getValueAt(rowIdx, 2));//�ð�
					exTf.setText((String)table.getValueAt(rowIdx, 3));//����
					priceTf.setText((String)table.getValueAt(rowIdx,4));//����
					telTf.setText((String)table.getValueAt(rowIdx,5));
					addressTf.setText((String)table.getValueAt(rowIdx,6));
				}
			}
		});	
		
		
	//��ư �̺�Ʈ �ֱ�
	addBtn.addActionListener(this);
	editBtn.addActionListener(this);
	delBtn.addActionListener(this);
	}
	//�������̵�
	public void actionPerformed(ActionEvent e) {
		String eventBtn = e.getActionCommand();
		if(eventBtn.equals("���") ) {
			forDataInsert();
		}else if(eventBtn.equals("����")) {
			recordEdit();
		}else if (eventBtn.equals("����")) {
			recordDelete();
		}
	}
	
	//����
		public void recordDelete() {
			if(codeTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "�̸��� �־�� ������ �����մϴ�");
			}else  {
				MemberDAO dao =  MemberDAO.getInstance();
				int result = dao.deleteRecord(Integer.parseInt(codeTf.getText()));
				if(result>0) {//������
					JOptionPane.showMessageDialog(this, codeTf.getText()+"�� ���� �Ϸ�Ǿ����ϴ�");
					selectAllRecord();
				}else {//���� ������
					JOptionPane.showMessageDialog(this, "���� �����Ͽ����ϴ�");
					
				}
				
			}
		}
		
		//����
		public void recordEdit() {
			if(nameTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "��ȣ�� ������ ������ �� �����ϴ�..");
			}else if(codeTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "�̸��� ������ �����Ҽ� �����ϴ�..");
			}else if(timeTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "���̵� ������ ������ �� �����ϴ�..");
			}else if (exTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "��й�ȣ�� ������ ������ �� �����ϴ�..");
			}else if (priceTf.getText().equals("")){
				JOptionPane.showMessageDialog(this,  "�̸����� ������ ������ �� �����ϴ�..");
			}else if(telTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this,  "����ó�� ������ ������ �� �����ϴ�..");
			}else if(addressTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this,  "�̸����� ������ ������ �� �����ϴ�..");
			}else{
				MemberVO vo = new MemberVO();
				;
				vo.setMember_sq(Integer.parseInt(codeTf.getText()));
				vo.setName(nameTf.getText());
				vo.setId(timeTf.getText());
				vo.setPw(exTf.getText());
				vo.setAddress(priceTf.getText());
				vo.setTel(telTf.getText());
				vo.setTel(addressTf.getText());
				MemberDAO dao = MemberDAO.getInstance();
				int cnt = dao.updateRecord(vo);
				
				if(cnt <0) {//��������
					JOptionPane.showMessageDialog(this, vo.getName()+"������ �Ǿ����ϴ�");
					selectAllRecord();
				}else {//��������
					selectAllRecord();
					JOptionPane.showMessageDialog(this, vo.getName()+" �������� �Ͽ����ϴ� ");
					}
				
				}
		
		}
		//���
		public void forDataInsert() {//��� �޼���â
			JOptionPane pane = new JOptionPane();
			//���Ͱ���,�ð�,����,����
			if(codeTf.getText().equals("")) {//��ȣ
				pane.showMessageDialog(this, "��ȣ�� �Է��ϼ���.");
			}else if(nameTf.getText().equals("")) {//���Ͱ���
				pane.showMessageDialog(this, "�̸��� �Է��ϼ���.");
				
			}else if(timeTf.getText().equals("")) {//�ð�
				pane.showMessageDialog(this, "���̵� �Է��ϼ���");
			}else if(exTf.getText().equals("")) {//����
				pane.showMessageDialog(this, "��й�ȣ�� �Է��ϼ���");
			}else if(priceTf.getText().equals("")) {//����
				pane.showMessageDialog(this, "�̸����� �Է��ϼ���");
			}else if(telTf.getText().equals("")) {
				pane.showMessageDialog(this, "����ó�� �Է��ϼ���");
			
			}else if(addressTf.getText().equals("")) {
				pane.showMessageDialog(this, "�̸����� �Է��ϼ���");
			
			}else {
				MemberVO vo = new MemberVO();
				vo.setMember_sq(Integer.parseInt(codeTf.getText()));
				vo.setName(nameTf.getText());
				vo.setId(timeTf.getText());
				vo.setPw(exTf.getText());
				vo.setAddress(priceTf.getText());
				vo.setTel(telTf.getText());
				vo.setTel(addressTf.getText());
				MemberDAO dao = new MemberDAO();
				//ȸ����� ��,��
				int cnt = dao.memberInsert(vo);
				if(cnt ==0) {//ȸ����� �߰� ����
					pane.showMessageDialog(this, "ȸ�� ����� �����Ͽ����ϴ�.");
				}else {
					pane.showMessageDialog(this, "ȸ�� ��� �Ǿ����ϴ�.");
				}
				selectAllRecord();
			}
			
		}
	//��ü ���ڵ� ��å	
	public void selectAllRecord() {
		model.setNumRows(0);
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.selectAllRecord();
		for(int i = 0; i<list.size(); i++) {
			MemberVO vo = list.get(i);
			//1���� �迭�� ��ȯ
			Object[] record = {vo.getMember_sq(),
							   vo.getName(),
							   vo.getId(),
							   vo.getPw(),
							   vo.getAddress(),
							   vo.getTel(),
							   vo.getEmail()};
			model.addRow(record);
		}
	}
	public static void main(String[] args) {
		new AdminMemberTable();

	}

	

}
