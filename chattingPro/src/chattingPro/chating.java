package chattingPro;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class chating extends JFrame {
	
		JTextField jtx = new JTextField(20); 
		JButton jb = new JButton("����");
		JLabel jl = new JLabel("������ ����Ʈ",JLabel.RIGHT); //�����ʿ� ��ġ
	JPanel jp = new JPanel();
	
		
	JPanel jp1 = new JPanel();
		JTextArea jta = new JTextArea();
		JScrollPane jsp =new JScrollPane(jta);
		JTextArea jta1 = new JTextArea();
		JScrollPane jsp1 =new JScrollPane(jta1);
	
	
	public chating() {
		setLayout(new BorderLayout());//���� ���̾ƿ� ��,��,��,��,�߾�
		setVisible(true);
		setSize(400,300);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		add(jp,"North");
			//jp.setLayout();
			jp.add(jtx);
			//��ư
			jp.add(jb);
			//��
			jp.add(jl);
		/////////////////////////
		add(jp1, "Center");
			jp1.setLayout(new GridLayout());
			jp1.add(jsp);
			jsp.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
			jp1.add(jsp1);
			jsp1.setVerticalScrollBarPolicy(jsp.VERTICAL_SCROLLBAR_ALWAYS);
			
	}
	public static void main(String[] args) {
		new chating();

	}

}
