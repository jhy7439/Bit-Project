import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.SelectableChannel;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TopleTable extends JPanel{
	JTable table;
	DefaultTableModel model;
	JButton jb;
	//제묵: 1차원 배열
	String[] title = {"토플과정", "수강가능시간","설명","가격"};
	
	Object[][]data= {     };
	JScrollPane sp;
	
	JPanel formCenter = new JPanel(new GridLayout(5,1));
	JTextField noTf= new JTextField();
	JTextField nameTf= new JTextField();
	JTextField telTf= new JTextField();
	JTextField emailTf= new JTextField();
	JTextField regTf= new JTextField();
	
	public TopleTable() {
		model = new DefaultTableModel(data,title);
		table = new JTable(model);
		sp = new JScrollPane(table);
		jb = new JButton("수강신청");
		add(sp);
		add(jb);
		selectAllRecord(); 
		
		setSize(500,300);
		setVisible(true);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	
		formCenter.add(noTf);formCenter.add(nameTf);formCenter.add(telTf);formCenter.add(emailTf);formCenter.add(regTf);
		
		
		
		
		//수강신청 버튼 이벤트 처리
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TakeList tl = new TakeList();
				//dispose();
			} 
			
		});
		
		//마우스 이벤트
				table.addMouseListener(new MouseAdapter() {//마우스 이벤트 마우스로 클릭하여 정보를 볼수 있게 한다 
					public void mouseReleased(MouseEvent me) {//눌렀다 땔때
						//마우스 버튼 종료 알아내기
						
						int click = me.getButton();//int  1:왼쪽버튼,2:휠,3:오른쪽버튼
						if(click==1){
							//선택한 행의 index를 구한다 
							int rowIdx = table.getSelectedRow();//row
							 
							noTf.setText(String.valueOf(table.getValueAt(rowIdx, 0)));//번호
							nameTf.setText((String)table.getValueAt(rowIdx, 1));//이름
							telTf.setText((String)table.getValueAt(rowIdx, 2));//연락처
							emailTf.setText((String)table.getValueAt(rowIdx,3));//이메일
							
							
					
						}
					}
				});
				
		
		}
		
	
	//전체 레코드 선택
	 public void selectAllRecord() {
		model.setNumRows(0);//행의 수를 0행으로 만들면 원래 데이터가 사라진다.
		TopleDAO dao = new TopleDAO();
		List<TopleVO> list = dao.selectAllRecord();
		for(int i=0; i<list.size(); i++) {
			TopleVO vo = list.get(i);
			//1차원 배열로 전환
			Object[] record = {vo.getTopleName(), vo.getTopleTime(), vo.getTopleEx(),
							   vo.getToplePrice()};
			model.addRow(record);
			}
		}
	

	public static void main(String[] args) {
		new TopleTable();

	}

}
