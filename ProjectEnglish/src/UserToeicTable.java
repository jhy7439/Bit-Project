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

public class UserToeicTable extends JPanel implements ActionListener {
	//JPanel northForm = new JPanel(new BorderLayout());
		
		
	JButton jb;
	JTable table;//변수선언
	DefaultTableModel model;
	//제목 : 1차원 배열
	String[] title = {"번호","토익과정","수강가능시간","설명","가격"};
			
	Object[][]data= {		};
	
	JScrollPane sp;
	
	public UserToeicTable() {
		
		//add(northForm,"North");
			
		model = new DefaultTableModel(data, title);
		table = new JTable(model);
		sp= new JScrollPane(table);
		jb= new JButton("수강신청");
		
		add(sp, "Center");
		add(jb);
		selectAllRecord();//전체목록 바로보기
		
		setSize(500,300);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		//수강신청 버튼 이벤트 처리
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TakeList tl = new TakeList();
				
				//dispose();
			} 
			
		});
		
	table.addMouseListener(new MouseAdapter() {//마우스 이벤트 마우스로 클릭하여 정보를 볼수 있게 한다 
			

			public void mouseReleased(MouseEvent me) {//눌렀다 땔때
				
				
				int click = me.getButton();//int  1:왼쪽버튼,2:휠,3:오른쪽버튼
				if(click==1){
					
					//선택한 행의 index를 구한다 
					int rowIdx = table.getSelectedRow();//row
					ToeicListTable tl = new ToeicListTable();
				}
			}
			
		});	
	}
	//전체 레코드 선택	
	public void selectAllRecord() {
		model.setNumRows(0);
		AdminToeicDAO dao = new AdminToeicDAO();
		List<AdminVO> list = dao.selectAllRecord();
		for(int i = 0; i<list.size(); i++) {
			AdminVO vo = list.get(i);
			//1차원 배열로 전환
			Object[] record = {vo.getCode(),vo.gettName(),vo.gettTime(),vo.gettEx(),vo.gettPrice()};
			model.addRow(record);
		}
	}
	
	public static void main(String[] args) {
		new UserToeicTable();

	}





	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
