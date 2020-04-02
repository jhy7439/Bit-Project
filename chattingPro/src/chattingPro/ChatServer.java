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
	//전체 접속자 객체를 보관할 컬렉션
	List<ChatService> connectAll = new ArrayList<ChatService>();
	ServerSocket ss;
	public ChatServer() {
		this.start();//스레드 시작
	}
	public void run() {//접속 기다리기
		try {
			ss = new ServerSocket(23000);
			
			while(true) {
				System.out.println("접속 대기중");
				Socket s = ss.accept();//(accept)클라이언트가 들어오는것을 대기하는 역할
				
				//접속자를 ChatService객체를 생성하여 컬렉션에 추가
				ChatService cs = new ChatService(s);
				
				//중복접속자 제거
				cs.setDoubleUserCheck(cs.username);
				connectAll.add(cs);//컬렉션 추가
				
				//접속자정보 모든 접속자에 전송
				cs.setAllMessage("//Guest" + cs.username + "님이 접속하였습니다....");
				//접속자수를 모든 접속자에 전송
				cs.setAllMessage("//Count" + connectAll.size());
				//현재 접속자 목록을 모든 접속자에게 보내기
				cs.setAlluser();
				//접속자가 서버로 문자보내면 받을 InputStream 스레드를 시작한다.
				cs.start();
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//한 접속자에 대한 정보를 가지는 클래스가 있어야 한다.
	class ChatService extends Thread{
		Socket socket;
		BufferedReader br; //input
		PrintWriter pw; //write
		String username; //접속자명
		String clientMsg;
		
		ChatService(){}
		ChatService(Socket socket){
			this.socket = socket;
			try {
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			}catch(Exception e) {
				System.out.println("접속자 객체 생성에러... " + e.getMessage());
			}
			username = socket.getInetAddress().getHostAddress();//소켓의 아이피 이름으로 사용
		}
		public void run() {
			while(true) {
				try  {
				String client = br.readLine();
				if(client != null) {
					setAllMessage("//msg//"+username+ "님:"+ client);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			}	
		}
		//접속자 정보 보내기
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
		//모든 접속자명을 보내기
		public void setAlluser() {
			// //cList@첫번쨰 접속자(192.168.0.15)@두번쨰 접속자@
			String connList = "//cList";
			for(int i=0; i<connectAll.size(); i++) {
				ChatService cs = connectAll.get(i);
				connList += "@" + cs.username;
			}
			setAllMessage(connList);
		}
		//중복접속자 제거하기
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
