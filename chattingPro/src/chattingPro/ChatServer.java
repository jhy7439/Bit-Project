package chattingPro;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer extends Thread{
	//��ü ������ ��ü�� ������ �÷���
	List<ChatService> connectAll = new ArrayList<ChatService>();
	ServerSocket ss;
	public ChatServer() {
		this.start();//������ ����
	}
	public void run() {//���� ��ٸ���
		try {
			ss = new ServerSocket(23000);
			
			while(true) {
				System.out.println("���� �����");
				Socket s = ss.accept();//(accept)Ŭ���̾�Ʈ�� �����°��� ����ϴ� ����
				
				//�����ڸ� ChatService��ü�� �����Ͽ� �÷��ǿ� �߰�
				ChatService cs = new ChatService(s);
				
				//�ߺ������� ����
				cs.setDoubleUserCheck(cs.username);
				connectAll.add(cs);//�÷��� �߰�
				
				//���������� ��� �����ڿ� ����
				cs.setAllMessage("//Guest" + cs.username + "���� �����Ͽ����ϴ�....");
				//�����ڼ��� ��� �����ڿ� ����
				cs.setAllMessage("//Count" + connectAll.size());
				//���� ������ ����� ��� �����ڿ��� ������
				cs.setAlluser();
				//�����ڰ� ������ ���ں����� ���� InputStream �����带 �����Ѵ�.
				cs.start();
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//�� �����ڿ� ���� ������ ������ Ŭ������ �־�� �Ѵ�.
	class ChatService extends Thread{
		Socket socket;
		BufferedReader br; //input
		PrintWriter pw; //write
		String username; //�����ڸ�
		String clientMsg;
		
		ChatService(){}
		ChatService(Socket socket){
			this.socket = socket;
			try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			}catch(Exception e) {
				System.out.println("������ ��ü ��������... " + e.getMessage());
			}
			username = socket.getInetAddress().getHostAddress();//������ ������ �̸����� ���
		}
		public void run() {
			while(true) {
				try  {
				String client = br.readLine();
				if(client != null) {
					setAllMessage("//msg//"+username+ "��:"+ client);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			}	
		}
		//������ ���� ������
		public void setAllMessage(String msg) {
			for(int i=0; i<connectAll.size(); i++) {// i=0,1,2,3,4
				ChatService cs = connectAll.get(i);
				try {
				cs.pw.println(msg);
				cs.pw.flush();
				}catch(Exception e) {
					connectAll.remove(i);
					i--;
				}
			}
		}
		//��� �����ڸ��� ������
		public void setAlluser() {
			// //cList@ù���� ������(192.168.0.15)@�ι��� ������@
			String connList = "//cList";
			for(int i=0; i<connectAll.size(); i++) {
				ChatService cs = connectAll.get(i);
				connList += "@" + cs.username;
			}
			setAllMessage(connList);
		}
		//�ߺ������� �����ϱ�
		public void setDoubleUserCheck(String username) {
			for(int i=0; i<connectAll.size(); i++) {
				ChatService cs = connectAll.get(i);
				if(cs.username.equals(username)) {
					connectAll.remove(i);
					break;
				}
			}
		}
	}//class
	
	
	public static void main(String[] args) {
		new ChatServer();
		

	}

}
