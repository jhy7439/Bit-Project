import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class LoginOk extends JFrame implements ActionListener{
	
	
	OpicTable ob = new OpicTable();
	TopleTable tt = new TopleTable();
	//ToeicTable to = new ToeicTable();
	UserToeicTable us = new UserToeicTable();//�״�� �޴°� ���̺�
	
    /*�ʿ��� ������Ʈ �غ�*/
   
    JTabbedPane tb= new JTabbedPane();  //JTabbedPane����
    
    
    JPanel p1 = new JPanel(); //JPanel ����
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
   
    JCheckBox cb1 = new JCheckBox("ȸ����������", false);
    JCheckBox cb2 = new JCheckBox("�������� ���", false);
    JCheckBox cb3 = new JCheckBox("ȸ��Ż��", false);
    
   
    public LoginOk() {  //������
    	
    	
        super("�޴���"); //���� ����
       
       
      
       
        
        tb.add("����������", p2); //JTabbedPane�� ���߰�
        tb.add("����", us);
        tb.add("����", tt);
        tb.add("����", ob);
        
        
        
        //����������
        p2.add(cb1);//ȸ����������
        p2.add(cb2);//�������
        p2.add(cb3);//ȸ��Ż��
        add(tb);
       
        
        p2.setLayout(new GridLayout(1,3));
        
        setSize(480, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
 
        //ȸ����������
        cb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemReMake mr = new MemReMake();
				//dispose();
			} 
		});
        //�������
        cb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TakeList tl = new TakeList();
				
				//dispose();
			} 
		});
        //ȸ��Ż��
        cb3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MembershipOut mo = new MembershipOut();
			dispose();
		} 
	});
        
        
    }//������ ��
    
 
          
          
          
    
    
    
    public static void main(String[] args) {
        new LoginOk();
        
       
    }//���θ޼ҵ� ��


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    //ȸ������������ �׼� �־��ֱ�
   
 
}//Ŭ���� ��