import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class AdminMain extends JFrame implements ActionListener{

	 JTabbedPane tb= new JTabbedPane();
	 AdminToeicTable at = new AdminToeicTable();
	 AdminMemberTable mt = new AdminMemberTable();
	 
	 	JPanel p1 = new JPanel(); //JPanel ����
	    
	 	
	   
	public AdminMain() {
		super("������ �޴�");
		 tb.add("ȸ������ ���", mt); //JTabbedPane�� ���߰�
	     tb.add("���ͼ�������", at);
		
	        add(tb);
		
		
	        setSize(630, 550);
	        setLocationRelativeTo(null);
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        //p1.setLayout(new GridLayout(1,3));
		
		
	}
	
	
	
	
	public static void main(String[] args) {
		AdminMain ad = new AdminMain();
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
