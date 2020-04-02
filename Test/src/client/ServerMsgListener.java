﻿package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

 
public class ServerMsgListener extends Thread {
	private ClientHandler ch;
	private BufferedReader in;
	private PrintWriter out;
	private Socket socket;

	ServerMsgListener(ClientHandler ch, Socket socket) throws IOException {
		this.ch = ch;
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	@Override
	public void run() {
		String msg;
		try {
			while ((msg = in.readLine()) != null) {
				String prefix = msg.substring(0, 4);
				msg = msg.substring(4);
				if (prefix.equals(Message.LOGIN_CHECKID_SUCCESS)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "사용할 수 있습니다");
					ch.getJoinDialog().setIdChecked(true);
				} else if (prefix.equals(Message.LOGIN_CHECKID_FAIL)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "이미 존재하는 아이디입니다");
					ch.getJoinDialog().setIdChecked(false);
				} else if (prefix.equals(Message.LOGIN_CHECKNICK_SUCCESS)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "사용할 수 있습니다");
					ch.getJoinDialog().setNickChecked(true);
				} else if (prefix.equals(Message.LOGIN_CHECKNICK_FAIL)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "이미 존재하는 닉네임입니다");
					ch.getJoinDialog().setNickChecked(false);
				} else if (prefix.equals(Message.LOGIN_JOIN_SUCCESS)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "가입이 승인되었습니다");
					ch.getLoginPanel().setId(ch.getJoinDialog().getId());
					ch.getJoinDialog().dispose();
				} else if (prefix.equals(Message.LOGIN_JOIN_FAIL)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "가입 실패!!\n관리자에게 문의하세요", "에러", JOptionPane.ERROR_MESSAGE);
				} else if (prefix.equals(Message.LOGIN_FAIL_WRONG_ID)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "없는 아이디입니다");
					ch.getLoginPanel().focusIdField();
				} else if (prefix.equals(Message.LOGIN_FAIL_WRONG_PWD)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "암호가 틀렸습니다");
					ch.getLoginPanel().focusPwdField();
				} else if (prefix.equals(Message.LOGIN_FAIL_LOGINED_ID)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "이미 로그인되어 있습니다");
					ch.getLoginPanel().focusIdField();
				} else if (prefix.equals(Message.LOGIN_SUCCESS)) {
					ch.setMember(new Member(msg.split(",")));
					ch.openWaitRoom();
					ch.getWaitRoom().focusText();
					ch.getWaitRoom().setChatBorder("채팅(" + ch.getMember().getNick() + ")");
				} else if (prefix.equals(Message.WAIT_USER_UPDATE)) {
					ch.getWaitRoom().setUserList(msg);
				} else if (prefix.equals(Message.WAIT_ROOM_UPDATE)) {
					ch.getWaitRoom().setRoomList(msg);
				} else if (prefix.equals(Message.CHAT_USER_UPDATE)) {
					String tmp[] = msg.split(",");
					ch.getChatRoom().setUserList(tmp);
					if (tmp[0].equals(ch.getMember().getNick())) {
						ch.getChatRoom().setKing();
					}
				} else if (prefix.equals(Message.WAIT_CHAT_MSG)) {
					ch.getWaitRoom().appendMsg(msg);
				} else if (prefix.equals(Message.ROOM_MAKE_SUCCESS)) {
					ch.openChatRoom(msg);
				} else if (prefix.equals(Message.ROOM_MAKE_FAIL)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "같은 이름이 존재합니다");
				} else if (prefix.equals(Message.ROOM_CHAT_MSG)) {
					ch.getChatRoom().appendMsg(msg);
				} else if (prefix.equals(Message.ROOM_SET_NAME)) {
					ch.getChatRoom().setChatBorder("[" + msg + "]");
				} else if (prefix.equals(Message.ROOM_INVITE_REQUEST)) {
					String tmp[] = msg.split(",");
					String roomName = tmp[1];
					String inviteMsg = "[" + roomName + "] 방에서 초대하였습니다\n입장하시겠습니까?";
					if (JOptionPane.showConfirmDialog(ch.getFrame(), inviteMsg, "알림", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
						ch.enterRoom(roomName);
					} else {
						ch.inviteDeny(msg + "," + ch.getMember().getNick()); // msg 내용 : id,roomName
					}
				} else if (prefix.equals(Message.ROOM_INVITE_DENIED)) {
					JOptionPane.showMessageDialog(ch.getFrame(), msg + "님이 초대를 거절하였습니다");
				} else if (prefix.equals(Message.ROOM_RETURN_WAITUSER)) {
					ch.getInviteDialog().setListData(msg);
				} else if (prefix.equals(Message.GET_ADDR)) {
					GetFile gf = new GetFile(ch.getFrame(), msg);
					gf.setVisible(true);
				} else if (prefix.equals(Message.ROOM_WHISPER_FAIL)) {
					JOptionPane.showMessageDialog(ch.getFrame(), "[" + msg + "] : 존재하지 않는 사용자입니다");
				} else if (prefix.equals(Message.ROOM_KICKOFF)) {
					ch.exitChatRoom();
					JOptionPane.showMessageDialog(ch.getFrame(), "강퇴 당하셨습니다");
				} else if (prefix.equals(Message.MOD_UPDATE_SUCCESS)) {
					JOptionPane.showMessageDialog(ch.getJoinDialog(), "수정 완료");
					ch.getJoinDialog().dispose();
					String tmp[] = msg.split(",");
					ch.getMember().setAll(tmp);
					ch.getWaitRoom().setChatBorder("채팅(" + tmp[3] + ")");
				} else if (prefix.equals(Message.MOD_UPDATE_FAIL)) {
					JOptionPane.showMessageDialog(ch.getJoinDialog(), "수정 중 오류가 발생하였습니다");
				} else if (prefix.equals(Message.ERR_DATABASE)) {
					JOptionPane.showMessageDialog(ch.getFrame(), msg, "", JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception e) {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void sendMsg(String msg) {
		out.println(msg);
		out.flush();
	}

}
