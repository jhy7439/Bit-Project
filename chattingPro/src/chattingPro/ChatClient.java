package chattingPro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient extends JFrame implements ActionListener, Runnable{
	//frame - center
	JPanel mainPanel = new JPanel(new BorderLayout());
		JPanel leftPane = new JPanel(new BorderLayout());//connect,message,send
			JPanel connectPane = new JPanel(new BorderLayout());
				JTextField connTf = new JTextField("192.168.0.15");
				JButton connBtn = new JButton("Connect");
			JTextArea ta = new JTextArea();
				JScrollPane sp = new JScrollPane(ta);
			JPanel sendPane = new JPanel(new BorderLayout());
				JTextField sendTf = new JTextField();
				JButton sendBtn = new JButton("보내기");
		JPanel rightPane = new JPanel(new BorderLayout());//접속자 정보
			JLabel connectListLbl = new JLabel("접속자 리스트");
			DefaultListModel<String> model = new DefaultListModel<String>();
			JList<String> connectList = new JList<String>(model);
			JScrollPane sp2 = new JScrollPane(connectList);
			JLabel connetCount = new JLabel("현재원: 0명");
	/////////////////////////////
	Socket s;
	PrintWriter pw;
	BufferedReader br;
	
	public ChatClient() {
		super("채팅");
		
		mainPanel.add(leftPane, "Center");
			leftPane.add(connectPane,"North");
				connectPane.add(connTf, "Center");
				connectPane.add(connBtn, "East");
			leftPane.add(sp, "Center");
			leftPane.add(sendPane, "South");
				sendPane.add(sendTf, "Center");
				sendPane.add(sendBtn, "East");
		mainPanel.add(rightPane, "East");
			rightPane.add(connectListLbl,BorderLayout.NORTH);
			rightPane.add(sp2, BorderLayout.CENTER);
			rightPane.add(connetCount,BorderLayout.SOUTH);
			model.addElement(" ");
		add(mainPanel);
		setSize(500,300);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		connTf.addActionListener(this);
		connBtn.addActionListener(this);
		sendTf.addActionListener(this);
		sendBtn.addActionListener(this);
	}
	//오버라이딩
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == connTf || obj == connBtn) {
			startConnection();
		}else if(obj == sendTf || obj == sendBtn) {
			startSend();
		}
	}
	//서버에 접속
	public void startConnection() {
		try {
			InetAddress ia = InetAddress.getByName(connTf.getText());
			s = new Socket(ia, 23000);
			
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			
			connTf.setEditable(false);
			connBtn.setEnabled(false);
			
			Thread t = new Thread(this);
			t.start();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	//서버로 메세지 보내기
	public void startSend() {
		String msg = sendTf.getText();
		if(msg !=null && !msg.equals("") ) {
			pw.println(msg);
			pw.flush();
		}
		sendTf.setText("");
	}
	//서버에 보낸 데이터 읽기 위한 InputStream은 Thread로 구현한다.
	public void run() {
		while(true) {
			try {
			String inData = br.readLine();
			if(inData !=null) {
				if(inData.substring(0,7).equals("//Guest")) {//접속자 알림
					ta.append(inData.substring(7) + "\n");
					
				}else if(inData.substring(0,7).equals("//Count")) {
					String connCount = inData.substring(7);
					connetCount.setText("현재원: " + connCount + "명");
				}else if(inData.substring(0,7).equals("//cList") ) {//접속자 목록 입력된 경우
					setUsername(inData.substring(7));
				}else if(inData.substring(0,7).equals("//msg//")) {//		//msg//hi
					ta.append(inData.substring(8) + "\n");
					//sendTf.setText("");
					ta.setCaretPosition(ta.getText().length());//ta
				}
				
			}
			
			}catch(Exception e) {
				
			}	
		}
	}
	public void setUsername(String user) {
		//connectList.removeAll();//목록 지우기
		model.removeAllElements();
		
		StringTokenizer st = new StringTokenizer(user, "@");
		while(st.hasMoreElements()) {
			String username =  st.nextToken();
			model.addElement(username);
		}
	}
	public static void main(String[] args) {
		new ChatClient();

	}

}
