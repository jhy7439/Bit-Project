import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class JoinMember extends JFrame {//ȸ������

	public JoinMember() {
		super("ȸ������");
		JPanel jp = new JPanel();
			Label lName = new Label("�̸�");
			Label lId = new Label("���̵�");
			Label lPw = new Label("��й�ȣ");
			Label lAd = new Label("�ּ�");
			Label tel = new Label("����ó");
			Label lEm = new Label("�̸���");
			//�гο� �ֱ�
			add(lName);
			add(lId);
			add(lPw);
			add(lAd);
			add(tel);
			add(lEm);
			
			
			//�гο� �ؽ�Ʈ ����
			TextField tName = new TextField();
			TextField tId = new TextField();
			TextField tPw = new TextField();
			TextField tAd = new TextField();
			TextField telt = new TextField();
			TextField tEm = new TextField();
			//�ؽ�Ʈ�� �ֱ�
			add(tName);
			add(tId);
			add(tPw);
			add(tAd);
			add(telt);
			add(tEm);
			
			tPw.setEchoChar('*');//��й�ȣ ��ȣȭ
			
			//��ư�߰�
			JButton jbRe = new JButton("Ȯ��");
			JButton jbDe = new JButton("���");
			//��ư�� �ֱ�
			add(jbRe);
			add(jbDe);
			
		//��ġ ����	
			lName.setBounds(40,10,40,40);
		    lId.setBounds(40, 50, 40, 40);
	        lPw.setBounds(40,90,60,40);
	        lAd.setBounds(40, 130, 40, 40);
	        tel.setBounds(40,170,60,40);
	        lEm.setBounds(40, 210, 60, 40);
	        tName.setBounds(120, 10, 200, 30);
	        tId.setBounds(120, 50, 200, 30);
	        tPw.setBounds(120, 90, 200, 30);
	        tAd.setBounds(120, 130, 280, 30);
	        telt.setBounds(120,180,280,30);
	        tEm.setBounds(120, 220, 280, 30);
	        jbRe.setBounds(125, 250, 80, 30);
	        jbDe.setBounds(240, 250, 80, 30); 
		
		
		add(jp);//�г� ����
		setSize(450,370);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//�����ư �׼� �־��ֱ�
		jbRe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//ȸ������ ������ ����
				try {
					MemberVO vo = new MemberVO();
					vo.setName(tName.getText());
					vo.setId(tId.getText());
					vo.setPw(tPw.getText());
					vo.setAddress(tAd.getText());
					vo.setTel(telt.getText());
					vo.setEmail(tEm.getText());
					
					MemberDAO dao = new MemberDAO();
					int cnt = dao.memberInsert(vo);
					/////////////////////////////////////////////
					if(cnt==0) {//ȸ����� �߰�����
						System.out.println("����");
					}else {//ȸ����� �߰�����
						System.out.println("����");
					}
					
					dispose();//����,��� ��ưŬ���ϸ� �ݱ�
				}catch(Exception ee) {
					JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����Ͽ����ϴ�.");
				}
				
			}
		});
	}
		
		
	public static void main(String[] args) {
		new JoinMember();
	}

}
