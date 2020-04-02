package client;

import java.net.Socket;

import javax.swing.JOptionPane;

import server.ServerHandler;

 
public class ClientHandler {
	private static final ClientHandler instance = new ClientHandler();
	private ServerMsgListener sml;
	private JoinDialog joinDialog;
	private MainFrame frame;
	private LoginPanel loginPanel;
	private WaitRoom waitRoom;
	private Member member;
	private ChatRoom chatRoom;
	private InviteDialog inviteDialog;
	private PwdDialog pwdDialog;

	public static void main(String[] args) {
		
		ClientHandler.getInstance().startClient();
	}

	public ClientHandler() {
		frame = new MainFrame();
		frame.setHandler(this);
	}

	public static ClientHandler getInstance() {
		return instance;
	}

	public void startClient() {
		openLogin();
	}

	private void openLogin() {
		Socket socket = null;
		try {
			socket = new Socket(Message.SERVER_URL, Message.CHAT_PORT);
			sml = new ServerMsgListener(this, socket);
			sml.start();
			loginPanel = new LoginPanel();
			frame.setPanel(loginPanel);
			frame.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "서버에 연결할 수 없습니다");
		}
	}

	public void openJoin() {
		joinDialog = new JoinDialog(frame, "회원가입", true, Message.MODE_JOIN);
		joinDialog.setVisible(true);
	}

	public void requestJoin(String msg) {
		sml.sendMsg(Message.LOGIN_REQUEST_JOIN + msg);
	}

	public MainFrame getFrame() {
		return frame;
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public void requestCheckId(String id) {
		System.out.println(this.hashCode());
		sml.sendMsg(Message.LOGIN_REQUEST_CHECKID + id);
	}

	public void requestCheckNick(String nick, int checkMode) {
		switch (checkMode) {
		case Message.LOGIN_CHECK:
			sml.sendMsg(Message.LOGIN_REQUEST_CHECKNICK + nick);
			break;
		case Message.MOD_CHECK:
			sml.sendMsg(Message.MOD_REQUEST_CHECKNICK + nick);
			break;
		}
	}

	public JoinDialog getJoinDialog() {
		return joinDialog;
	}

	public void requestLogin(String msg) {
		sml.sendMsg(Message.LOGIN_REQUEST + msg);
	}

	public void openWaitRoom() {
		waitRoom = new WaitRoom();
		frame.setPanel(waitRoom);
		frame.setMenuItemEnabled(true);
		if (member != null) {
			waitRoom.setChatBorder("채팅(" + member.getNick() + ")");
		}
		waitRoom.focusText();
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public WaitRoom getWaitRoom() {
		return waitRoom;
	}

	public void logout() {
		sml.sendMsg(Message.LOGOUT);
		loginPanel.setPwdField("");
		frame.setPanel(loginPanel);
		frame.setMenuItemEnabled(false);
	}

	public void sendWaitChat(String txt) {
		sml.sendMsg(Message.WAIT_CHAT_MSG + txt);
	}

	public Member getMember() {
		return member;
	}

	public void makeRoom(String roomName) {
		sml.sendMsg(Message.ROOM_REQUEST_MAKE + roomName);
	}

	public void openChatRoom(String msg) {
		chatRoom = new ChatRoom();
		frame.setPanel(chatRoom);
		chatRoom.focusText();
	}

	public void exitChatRoom() {
		openWaitRoom();
		sml.sendMsg(Message.ROOM_USER_OUT);
	}

	public void enterRoom(String roomName) {
		openChatRoom(roomName);
		sml.sendMsg(Message.ROOM_USER_IN + roomName);
	}

	public ChatRoom getChatRoom() {
		return chatRoom;
	}

	public void sendRoomChat(String text) {
		sml.sendMsg(Message.ROOM_CHAT_MSG + text);
	}

	public void invite(String nick) {
		sml.sendMsg(Message.ROOM_INVITE_USER + nick);
	}

	public void inviteDeny(String msg) {
		sml.sendMsg(Message.ROOM_INVITE_DENY + msg);
	}

	public void openInvite() {
		inviteDialog = new InviteDialog(frame, "초대", true);
		sml.sendMsg(Message.ROOM_REQUEST_WAITUSER);
		inviteDialog.setVisible(true);
	}

	public InviteDialog getInviteDialog() {
		return inviteDialog;
	}

	public void exitWaitRoom() {
		sml.sendMsg(Message.WAIT_USER_OUT);
		frame.dispose();
		System.exit(0);
	}

	public void sendfile(String nick) {
		sml.sendMsg(Message.GET_ADDR + nick);
	}

	public void sendWhisper(String nick, String msg) {
		sml.sendMsg(Message.ROOM_CHAT_WHISPER + nick + "," + msg);
	}

	public void kickOff(String nick) {
		sml.sendMsg(Message.ROOM_KICKOFF + nick);
	}

	public void requestModify(String msg) {
		sml.sendMsg(Message.MOD_UPDATE_USERINFO + msg);
	}

	public void openModify() {
		joinDialog = new JoinDialog(frame, "정보 수정", true, Message.MODE_MODIFY);
		joinDialog.setVisible(true);
	}

	public void openPwdCheck() {
		pwdDialog = new PwdDialog(frame);
		pwdDialog.setVisible(true);
	}

}
