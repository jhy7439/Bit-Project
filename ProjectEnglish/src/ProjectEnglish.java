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
	public ProjectEnglish() {//생성자 메소드 
		super("영어학원 회원관리 프로그램");
		
		
		JPanel jp = new JPanel();
			jp.setLayout(null);
		
		Label mainChang = new Label("비트 영어학원");
		add(mainChang);
		Font ft = new Font("궁서체", Font.BOLD + Font.ITALIC,30);
		mainChang.setFont(ft);
		
		Label idLb = new Label("아이디:");
		add(idLb);
		Label pwLb = new Label("비밀번호:");
		add(pwLb);
		TextField idTd = new TextField();
		add(idTd);
		TextField pwTd = new TextField();
		add(pwTd);
		pwTd.setEchoChar('*');//암호화 표시
		JButton loginJb = new JButton("로그인"); 
		add(loginJb);
		JButton memJb = new JButton("회원가입");
		add(memJb);
		
		mainChang.setBounds(150,20,200,70);//메인 제목
		idLb.setBounds(40,100,40,40);//아이디
		pwLb.setBounds(40,130,60,40);//비밀번호
		idTd.setBounds(150, 100, 200, 30);//아이디 텍스트
		pwTd.setBounds(150, 130, 200, 30);//비번 텍스트
		loginJb.setBounds(380,100,80,30);//로그인 버튼
		memJb.setBounds(380,130,90,30);//회원가입 버튼
		
		add(jp);
		//setBounds(가로위치, 세로위치, 가로길이, 세로길이);
		
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		
		
		//회원가입
		memJb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입창으로 이동
				JoinMember jm = new JoinMember();
				
			} 
		});
		loginJb.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent ee) {//로그인 할떄//메소드
				Object obj = ee.getSource();//버튼을 누르면 이벤트 실행시켜줌
				if(obj == loginJb) {
					String inputId = idTd.getText();
					String inputPw = pwTd.getText();
					
					MemberDAO dao = new MemberDAO();
					
					List<MemberVO> list = dao.selectID_PW();
					
					for(int i = 0; i < list.size(); i++) {
						MemberVO vo = list.get(i);	
						
						if( inputId.equals(vo.getId()) && inputPw.equals(vo.getPw())) {//회원 로그인
							LoginOk lo = new LoginOk();
							dispose();
							break;
						}else if(manId.equals(inputId) && manPwd.equals(inputPw)){//마스터 아이디		
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
		 if(result==1) {//로그인 성공
			 LoginOk lo = new LoginOk();
		 }else  {
			 JOptionPane.showMessageDialog(this, "아이디 혹은 비밀번호가 잘못 입력되었습니다. 다시한번 시도해주세요....");
		 }
	}
	
	
	public void actionPerformed(ActionEvent e) {	
	}

	

	
	public static void main(String[] args) {
		new ProjectEnglish();
	}
}
