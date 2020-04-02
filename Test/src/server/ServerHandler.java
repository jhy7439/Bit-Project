package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JOptionPane;

import server.database.DBManager;
import server.database.OracleManager;

import client.*;


public class ServerHandler extends Thread {
	List<Guest> guests;//대기실의 유저들을 저장
	private HashMap<String, String> idMap;//로그인한 모든 유저들의 id 와 ip 주소를 저장(ip주소는 파일전송때 사용됨)
	private HashMap<String, ArrayList<Guest>> roomMap;//채팅방의 유저들을 저장
	private MemberDAO dao;//DAO변수

	public static void main(String[] args) {
		try {
			new ServerHandler(new OracleManager()).start(); //디비연결
			ClientHandler.getInstance().startClient();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public ServerHandler(DBManager dbmgr) {//인터페이스 사용
		guests = new ArrayList<Guest>();
		idMap = new HashMap<String, String>();
		roomMap = new HashMap<String, ArrayList<Guest>>();
		try {
			dao = MemberDAO.getInstance(dbmgr);//getInstance: 동일한 객체를 매번 생성하지 않고 jvm에 static으로 생성
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		ServerSocket ss = null;//서버소켓선언
		Socket socket = null;//통신소켓선언
		try {
			ss = new ServerSocket(Message.CHAT_PORT);
			System.out.println("서버 시작됨 ...");
			while (true) {
				socket = ss.accept();
				Guest guest = new Guest(this, socket);
				guest.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				ss.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean checkId(String id) throws SQLException {
		return dao.checkId(id);
	}

	public boolean checkNick(String nick) throws SQLException {
		return dao.checkNick(nick);
	}

	public boolean requestJoin(String msg) throws SQLException {
		return dao.requestJoin(msg.split(","));
	}

	public Member getMember(String id) throws SQLException {
		Member member = dao.getMember(id);
		return member;
	}

	public void addUserToWait(Guest guest) {
		guests.add(guest);
	}

	public void userLogin(Guest guest) {
		guests.add(guest);
		idMap.put(guest.getMember().getId(), guest.getSocket().getInetAddress().getHostAddress());
	}

	public void broadcastWaitRoomUpdate() {
		broadcastWait_userUpdate();
		broadcastWait_roomUpdate();
	}

	public void broadcastWait_userUpdate() {
		String msg = "";
		Iterator<Guest> it = guests.iterator();
		while (it.hasNext()) {
			Guest g = it.next();
			msg += g.getMember().getNick() + ",";
		}
		broadcast_waitRoom(Message.WAIT_USER_UPDATE + msg);
	}

	public void broadcastWait_roomUpdate() {
		String msg2 = "";
		Set<String> set = roomMap.keySet();
		Iterator<String> it2 = set.iterator();
		while (it2.hasNext()) {
			String roomName = it2.next();
			ArrayList<Guest> list = roomMap.get(roomName);
			msg2 += roomName + " : " + list.size() + "명,";
		}
		broadcast_waitRoom(Message.WAIT_ROOM_UPDATE + msg2);
	}

	public void broadcast_waitRoom(String msg) {
		Iterator<Guest> it = guests.iterator();
		while (it.hasNext()) {
			Guest guest = it.next();
			guest.sendMsg(msg);
		}
	}

	public void broadcast_chatRoom(String msg, String roomName) {
		ArrayList<Guest> list = roomMap.get(roomName);
		if (list == null)
			return;
		Iterator<Guest> it = list.iterator();
		while (it.hasNext()) {
			Guest g = it.next();
			g.sendMsg(msg);
		}
	}

	public void removeUserFromWait(Guest guest) {
		guests.remove(guest);
	}

	public void removeUserFromIdMap(String id) {
		idMap.remove(id);
	}

	public void removeUserFromRoom(Guest guest, String roomName) {
		ArrayList<Guest> list = roomMap.get(roomName);
		if (list == null)
			return;
		list.remove(guest);
		if (list.size() == 0)
			roomMap.remove(roomName);
	}

	public void broadcastChatRoomUpdate(String roomName) {
		String msg = "";
		ArrayList<Guest> list = roomMap.get(roomName);
		if (list == null)
			return;
		Iterator<Guest> it = list.iterator();
		while (it.hasNext()) {
			Guest g = it.next();
			msg += g.getMember().getNick() + ",";
		}
		broadcast_chatRoom(Message.CHAT_USER_UPDATE + msg, roomName);
	}

	public boolean checkRoomName(String roomName) {
		return !roomMap.containsKey(roomName);
	}

	public boolean addRoom(String roomName) {
		if (roomMap.containsKey(roomName)) {
			return false;
		} else {
			roomMap.put(roomName, new ArrayList<Guest>());
			return true;
		}
	}

	public void addUserToRoom(Guest g, String roomName) {
		ArrayList<Guest> list = roomMap.get(roomName);
		list.add(g);
	}

	public boolean checkIdMap(String id) {
		return idMap.containsKey(id);
	}

	public void inviteUser(String inviterId, String receiverNick, String roomName) {
		for (Guest g : guests) {
			if (g.getMember().getNick().equals(receiverNick)) {
				g.sendMsg(Message.ROOM_INVITE_REQUEST + inviterId + "," + roomName);
				break;
			}
		}
	}

	public void inviteDeny(String inviterId, String roomName, String denierNick) {
		ArrayList<Guest> list = roomMap.get(roomName);
		for (Guest g : list) {
			if (g.getMember().getId().equals(inviterId)) {
				g.sendMsg(Message.ROOM_INVITE_DENIED + denierNick);
				break;
			}
		}
	}

	public void returnWaitUsers(Guest guest) {
		String msg = "";
		for (Guest g : guests) {
			msg += g.getMember().getNick() + ",";
		}
		guest.sendMsg(Message.ROOM_RETURN_WAITUSER + msg);
	}

	public void getAddr(Guest guest, String nick, String roomName) {
		ArrayList<Guest> list = roomMap.get(roomName);
		for (Guest g : list) {
			if (g.getMember().getNick().equals(nick)) {
				g.sendMsg(Message.GET_ADDR + idMap.get(guest.getMember().getId()));
				break;
			}
		}
	}

	public void sendWhisper(Guest sender, String receiverNick, String msg, String roomName) {
		String senderNick = sender.getMember().getNick();
		ArrayList<Guest> users = roomMap.get(roomName);
		Guest receiver = null;

		Iterator<Guest> it = users.iterator();
		while (it.hasNext()) {
			Guest g = it.next();
			if (g.getMember().getNick().equals(receiverNick)) {
				receiver = g;
				break;
			}
		}
		if (receiver == null) {
			sender.sendMsg(Message.ROOM_WHISPER_FAIL + receiverNick);
		} else {
			msg = "[" + senderNick + "]\t▶ " + msg;
			sender.sendMsg(Message.ROOM_CHAT_MSG + msg);
			receiver.sendMsg(Message.ROOM_CHAT_MSG + msg);
		}
	}

	public void kickOff(String nick, String roomName) {
		ArrayList<Guest> list = roomMap.get(roomName);
		for (Guest g : list) {
			if (g.getMember().getNick().equals(nick)) {
				g.sendMsg(Message.ROOM_KICKOFF);
				break;
			}
		}
	}

	public boolean modUserInfo(String[] tmp) throws SQLException {
		return dao.updateMember(tmp);
	}

}
