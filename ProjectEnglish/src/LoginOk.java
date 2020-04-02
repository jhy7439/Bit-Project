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
	UserToeicTable us = new UserToeicTable();//그대로 받는곳 테이블
	
    /*필요한 콤포넌트 준비*/
   
    JTabbedPane tb= new JTabbedPane();  //JTabbedPane생성
    
    
    JPanel p1 = new JPanel(); //JPanel 생성
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
   
    JCheckBox cb1 = new JCheckBox("회원정보수정", false);
    JCheckBox cb2 = new JCheckBox("수강가능 목록", false);
    JCheckBox cb3 = new JCheckBox("회원탈퇴", false);
    
   
    public LoginOk() {  //생성자
    	
    	
        super("메뉴얼"); //제목 지정
       
       
      
       
        
        tb.add("마이페이지", p2); //JTabbedPane에 탭추가
        tb.add("토익", us);
        tb.add("토플", tt);
        tb.add("오픽", ob);
        
        
        
        //마이페이지
        p2.add(cb1);//회원정보수정
        p2.add(cb2);//수강목록
        p2.add(cb3);//회원탈퇴
        add(tb);
       
        
        p2.setLayout(new GridLayout(1,3));
        
        setSize(480, 550);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        
 
        //회원정보수정
        cb1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemReMake mr = new MemReMake();
				//dispose();
			} 
		});
        //수강목록
        cb2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TakeList tl = new TakeList();
				
				//dispose();
			} 
		});
        //회원탈퇴
        cb3.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			MembershipOut mo = new MembershipOut();
			dispose();
		} 
	});
        
        
    }//생성자 끝
    
 
          
          
          
    
    
    
    public static void main(String[] args) {
        new LoginOk();
        
       
    }//메인메소드 끝


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
    
    //회원정보수정에 액션 넣어주기
   
 
}//클래스 끝