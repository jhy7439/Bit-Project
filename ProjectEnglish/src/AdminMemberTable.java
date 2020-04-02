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
			JLabel ToeicCode = new JLabel("번호");
			JLabel ToeicName = new JLabel("이름");
			JLabel ToeicTime = new JLabel("아이디");
			JLabel ToeicEx = new JLabel("비밀번호");
			JLabel ToeicAddress = new JLabel("주소");
			JLabel ToeicPrice = new JLabel("이메일");
			JLabel memTel = new JLabel("연락처");
			
		JPanel formCenter = new JPanel(new GridLayout(7,1));
			JTextField codeTf= new JTextField();
			JTextField nameTf= new JTextField();
			JTextField timeTf= new JTextField();
			JTextField exTf= new JTextField();
			JTextField addressTf= new JTextField();
			JTextField priceTf= new JTextField();
			JTextField telTf= new JTextField();
			
		JToolBar tb = new JToolBar();
			JButton addBtn = new JButton("등록");
			JButton editBtn = new JButton("수정");
			JButton delBtn = new JButton("삭제");
			
	
	JTable table;//변수선언
	DefaultTableModel model;
	//제목 : 1차원 배열
	String[] title = {"번호","이름","아이디","비밀번호", "주소","연락처","이메일"};
			
	Object[][]data= {		};
	
	JScrollPane sp;
	
	public AdminMemberTable() {
		//super("회원 정보목록");
		//툴바
		tb.add(addBtn); tb.add(editBtn);tb.add(delBtn);
		northForm.add(tb, "North");
		//폼 넣기
		northForm.add(formLbl, BorderLayout.WEST);
			formLbl.add(ToeicCode);
			formLbl.add(ToeicName);
			formLbl.add(ToeicTime);
			formLbl.add(ToeicEx);
			formLbl.add(ToeicAddress);
			formLbl.add(memTel);
			formLbl.add(ToeicPrice);
			
			
			
		//텍스트 필드 넣기
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
		
		selectAllRecord();//전체목록 바로보기
		
		setSize(500,300);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	table.addMouseListener(new MouseAdapter() {//마우스 이벤트 마우스로 클릭하여 정보를 볼수 있게 한다 
			

			public void mouseReleased(MouseEvent me) {//눌렀다 땔때
				//마우스 버튼 종료 알아내기
				
				int click = me.getButton();//int  1:왼쪽버튼,2:휠,3:오른쪽버튼
				if(click==1){
					//선택한 행의 index를 구한다 
					int rowIdx = table.getSelectedRow();//row
					
					codeTf.setText(String.valueOf(table.getValueAt(rowIdx, 0)));
					nameTf.setText(String.valueOf(table.getValueAt(rowIdx, 1)));
					timeTf.setText((String)table.getValueAt(rowIdx, 2));//시간
					exTf.setText((String)table.getValueAt(rowIdx, 3));//설명
					priceTf.setText((String)table.getValueAt(rowIdx,4));//가격
					telTf.setText((String)table.getValueAt(rowIdx,5));
					addressTf.setText((String)table.getValueAt(rowIdx,6));
				}
			}
		});	
		
		
	//버튼 이벤트 넣기
	addBtn.addActionListener(this);
	editBtn.addActionListener(this);
	delBtn.addActionListener(this);
	}
	//오버라이딩
	public void actionPerformed(ActionEvent e) {
		String eventBtn = e.getActionCommand();
		if(eventBtn.equals("등록") ) {
			forDataInsert();
		}else if(eventBtn.equals("수정")) {
			recordEdit();
		}else if (eventBtn.equals("삭제")) {
			recordDelete();
		}
	}
	
	//삭제
		public void recordDelete() {
			if(codeTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "이름이 있어야 삭제가 가능합니다");
			}else  {
				MemberDAO dao =  MemberDAO.getInstance();
				int result = dao.deleteRecord(Integer.parseInt(codeTf.getText()));
				if(result>0) {//삭제됨
					JOptionPane.showMessageDialog(this, codeTf.getText()+"이 삭제 완료되었습니다");
					selectAllRecord();
				}else {//삭제 실패함
					JOptionPane.showMessageDialog(this, "삭제 실패하였습니다");
					
				}
				
			}
		}
		
		//수정
		public void recordEdit() {
			if(nameTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "번호가 없으면 수정할 수 없습니다..");
			}else if(codeTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "이름이 없으면 수정할수 없습니다..");
			}else if(timeTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "아이디가 없으면 수정할 수 없습니다..");
			}else if (exTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "비밀번호가 없으면 수정할 수 없습니다..");
			}else if (priceTf.getText().equals("")){
				JOptionPane.showMessageDialog(this,  "이메일이 없으면 수정할 수 없습니다..");
			}else if(telTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this,  "연락처가 없으면 수정할 수 없습니다..");
			}else if(addressTf.getText().equals("")) {
				JOptionPane.showMessageDialog(this,  "이메일이 없으면 수정할 수 없습니다..");
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
				
				if(cnt <0) {//수정성공
					JOptionPane.showMessageDialog(this, vo.getName()+"수정이 되었습니다");
					selectAllRecord();
				}else {//수정실패
					selectAllRecord();
					JOptionPane.showMessageDialog(this, vo.getName()+" 수정실패 하였습니다 ");
					}
				
				}
		
		}
		//등록
		public void forDataInsert() {//등록 메세지창
			JOptionPane pane = new JOptionPane();
			//토익과정,시간,설명,가격
			if(codeTf.getText().equals("")) {//번호
				pane.showMessageDialog(this, "번호를 입력하세요.");
			}else if(nameTf.getText().equals("")) {//토익과정
				pane.showMessageDialog(this, "이름을 입력하세요.");
				
			}else if(timeTf.getText().equals("")) {//시간
				pane.showMessageDialog(this, "아이디를 입력하세요");
			}else if(exTf.getText().equals("")) {//설명
				pane.showMessageDialog(this, "비밀번호을 입력하세요");
			}else if(priceTf.getText().equals("")) {//가격
				pane.showMessageDialog(this, "이메일을 입력하세요");
			}else if(telTf.getText().equals("")) {
				pane.showMessageDialog(this, "연락처를 입력하세요");
			
			}else if(addressTf.getText().equals("")) {
				pane.showMessageDialog(this, "이메일을 입력하세요");
			
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
				//회원등록 유,무
				int cnt = dao.memberInsert(vo);
				if(cnt ==0) {//회원등록 추가 실패
					pane.showMessageDialog(this, "회원 등록이 실패하였습니다.");
				}else {
					pane.showMessageDialog(this, "회원 등록 되었습니다.");
				}
				selectAllRecord();
			}
			
		}
	//전체 레코드 선책	
	public void selectAllRecord() {
		model.setNumRows(0);
		MemberDAO dao = new MemberDAO();
		List<MemberVO> list = dao.selectAllRecord();
		for(int i = 0; i<list.size(); i++) {
			MemberVO vo = list.get(i);
			//1차원 배열로 전환
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
