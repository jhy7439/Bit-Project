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

public class JoinMember extends JFrame {//회원가입

	public JoinMember() {
		super("회원가입");
		JPanel jp = new JPanel();
			Label lName = new Label("이름");
			Label lId = new Label("아이디");
			Label lPw = new Label("비밀번호");
			Label lAd = new Label("주소");
			Label tel = new Label("연락처");
			Label lEm = new Label("이메일");
			//패널에 넣기
			add(lName);
			add(lId);
			add(lPw);
			add(lAd);
			add(tel);
			add(lEm);
			
			
			//패널에 텍스트 적용
			TextField tName = new TextField();
			TextField tId = new TextField();
			TextField tPw = new TextField();
			TextField tAd = new TextField();
			TextField telt = new TextField();
			TextField tEm = new TextField();
			//텍스트에 넣기
			add(tName);
			add(tId);
			add(tPw);
			add(tAd);
			add(telt);
			add(tEm);
			
			tPw.setEchoChar('*');//비밀번호 암호화
			
			//버튼추가
			JButton jbRe = new JButton("확인");
			JButton jbDe = new JButton("취소");
			//버튼에 넣기
			add(jbRe);
			add(jbDe);
			
		//위치 지정	
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
		
		
		add(jp);//패널 생성
		setSize(450,370);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//저장버튼 액션 넣어주기
		jbRe.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//회원가입 데이터 저장
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
					if(cnt==0) {//회원등록 추가실패
						System.out.println("실패");
					}else {//회원등록 추가성공
						System.out.println("성공");
					}
					
					dispose();//저장,취소 버튼클릭하면 닫기
				}catch(Exception ee) {
					JOptionPane.showMessageDialog(null, "회원가입에 실패하였습니다.");
				}
				
			}
		});
	}
		
		
	public static void main(String[] args) {
		new JoinMember();
	}

}
