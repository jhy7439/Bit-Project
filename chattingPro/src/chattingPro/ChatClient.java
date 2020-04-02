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
				JButton sendBtn = new JButton("������");
		JPanel rightPane = new JPanel(new BorderLayout());//������ ����
			JLabel connectListLbl = new JLabel("������ ����Ʈ");
			DefaultListModel<String> model = new DefaultListModel<String>();
			JList<String> connectList = new JList<String>(model);
			JScrollPane sp2 = new JScrollPane(connectList);
			JLabel connetCount = new JLabel("�����: 0��");
	/////////////////////////////
	Socket s;
	PrintWriter pw;
	BufferedReader br;
	
	public ChatClient() {
		super("ä��");
		
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
	//�������̵�
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj == connTf || obj == connBtn) {
			startConnection();
		}else if(obj == sendTf || obj == sendBtn) {
			startSend();
		}
	}
	//������ ����
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
	//������ �޼��� ������
	public void startSend() {
		String msg = sendTf.getText();
		if(msg !=null && !msg.equals("") ) {
			pw.println(msg);
			pw.flush();
		}
		sendTf.setText("");
	}
	//������ ���� ������ �б� ���� InputStream�� Thread�� �����Ѵ�.
	public void run() {
		while(true) {
			try {
			String inData = br.readLine();
			if(inData !=null) {
				if(inData.substring(0,7).equals("//Guest")) {//������ �˸�
					ta.append(inData.substring(7) + "\n");
					
				}else if(inData.substring(0,7).equals("//Count")) {
					String connCount = inData.substring(7);
					connetCount.setText("�����: " + connCount + "��");
				}else if(inData.substring(0,7).equals("//cList") ) {//������ ��� �Էµ� ���
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
		//connectList.removeAll();//��� �����
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
