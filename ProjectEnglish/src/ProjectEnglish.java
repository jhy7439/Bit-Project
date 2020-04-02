import java.awt.Font;
import java.awt.Label;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public  class ProjectEnglish extends JFrame implements ActionListener{
	
	private String manId = "sss";
	private String manPwd = "sss";
	
	
	TextField idTd = new TextField();
	TextField pwTd = new TextField();
	public ProjectEnglish() {//������ �޼ҵ� 
		super("�����п� ȸ������ ���α׷�");
		
		
		JPanel jp = new JPanel();
			jp.setLayout(null);
		
		Label mainChang = new Label("��Ʈ �����п�");
		add(mainChang);
		Font ft = new Font("�ü�ü", Font.BOLD + Font.ITALIC,30);
		mainChang.setFont(ft);
		
		Label idLb = new Label("���̵�:");
		add(idLb);
		Label pwLb = new Label("��й�ȣ:");
		add(pwLb);
		TextField idTd = new TextField();
		add(idTd);
		TextField pwTd = new TextField();
		add(pwTd);
		pwTd.setEchoChar('*');//��ȣȭ ǥ��
		JButton loginJb = new JButton("�α���"); 
		add(loginJb);
		JButton memJb = new JButton("ȸ������");
		add(memJb);
		
		mainChang.setBounds(150,20,200,70);//���� ����
		idLb.setBounds(40,100,40,40);//���̵�
		pwLb.setBounds(40,130,60,40);//��й�ȣ
		idTd.setBounds(150, 100, 200, 30);//���̵� �ؽ�Ʈ
		pwTd.setBounds(150, 130, 200, 30);//��� �ؽ�Ʈ
		loginJb.setBounds(380,100,80,30);//�α��� ��ư
		memJb.setBounds(380,130,90,30);//ȸ������ ��ư
		
		add(jp);
		//setBounds(������ġ, ������ġ, ���α���, ���α���);
		
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		
		//ȸ������
		memJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//ȸ������â���� �̵�
				JoinMember jm = new JoinMember();
				
			} 
		});
		loginJb.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent ee) {//�α��� �ҋ�//�޼ҵ�
				Object obj = ee.getSource();//��ư�� ������ �̺�Ʈ ���������
				if(obj == loginJb) {
					String inputId = idTd.getText();
					String inputPw = pwTd.getText();
					
					MemberDAO dao = new MemberDAO();
					
					List<MemberVO> list = dao.selectID_PW();
					
					for(int i = 0; i < list.size(); i++) {
						MemberVO vo = list.get(i);	
						
						if( inputId.equals(vo.getId()) && inputPw.equals(vo.getPw())) {//ȸ�� �α���
							LoginOk lo = new LoginOk();
							dispose();
							break;
						}else if(manId.equals(inputId) && manPwd.equals(inputPw)){//������ ���̵�		
							AdminMain ad = new AdminMain();	
							break;
						}
					}				
				}	
			}
		});
	}
	
	public void login() {
		 MemberDAO dao = MemberDAO.getInstance();
		 int result =dao.loginChk(idTd.getText(),pwTd.getText());
		 if(result==1) {//�α��� ����
			 LoginOk lo = new LoginOk();
		 }else  {
			 JOptionPane.showMessageDialog(this, "���̵� Ȥ�� ��й�ȣ�� �߸� �ԷµǾ����ϴ�. �ٽ��ѹ� �õ����ּ���....");
		 }
	}
	
	
	public void actionPerformed(ActionEvent e) {	
	}

	

	
	public static void main(String[] args) {
		new ProjectEnglish();
	}
}
