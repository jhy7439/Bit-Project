package client;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


//  채팅방 GUI 를 담당
 
public class ChatRoom extends JPanel implements ActionListener {
	private JList<String> userList;
	private JTextArea textArea;
	private JButton fileSendBtn;
	private JTextField textField;
	private JButton inviteBtn;
	private JButton exitBtn;
	private ClientHandler ch;
	private String userBorder = "참가자 : ";
	private JPopupMenu popup;
	private JMenuItem whisperMenu;
	private JMenuItem kickOffMenu;

	public ChatRoom() {
		init();
		start();
		ch = ClientHandler.getInstance();
	}

	private void init() {
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 138, 385);
		add(scrollPane);

		userList = new JList<String>();
		scrollPane.setViewportView(userList);
		userList.setBorder(new TitledBorder(userBorder));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(162, 10, 320, 385);
		add(scrollPane_1);

		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setEditable(false);

		fileSendBtn = new JButton("파일전송");
		fileSendBtn.setBounds(12, 405, 72, 31);
		fileSendBtn.setMargin(new Insets(0, 0, 0, 0));
		add(fileSendBtn);


		textField = new JTextField();
		textField.setBounds(162, 405, 236, 31);
		add(textField);
		textField.setColumns(10);

		inviteBtn = new JButton("초대");
		inviteBtn.setMargin(new Insets(0, 0, 0, 0));
		inviteBtn.setBounds(96, 405, 54, 31);
		add(inviteBtn);


		exitBtn = new JButton("나가기");
		exitBtn.setMargin(new Insets(0, 0, 0, 0));
		exitBtn.setBounds(409, 405, 72, 31);
		add(exitBtn);


		popup = new JPopupMenu();
		whisperMenu = new JMenuItem("귓속말");
		popup.add(whisperMenu);
		userList.add(popup);
		kickOffMenu = new JMenuItem("강퇴");
	}

	private void start() {
		fileSendBtn.addActionListener(this);
		textField.addActionListener(this);
		inviteBtn.addActionListener(this);
		exitBtn.addActionListener(this);
		whisperMenu.addActionListener(this);
		kickOffMenu.addActionListener(this);
		userList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getModifiers() == MouseEvent.BUTTON3_MASK && userList.getSelectedIndex() != -1) {
					popup.show(userList, e.getX(), e.getY());
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == exitBtn) {
			ch.exitChatRoom();
		} else if (src == textField) {
			String text = textField.getText();
			if (text.isEmpty())
				return;
			if (text.startsWith("/w ")) {
				text = text.substring(3);
				int index = text.indexOf(" ");
				if (index != -1) {
					String nick = text.substring(0, index);
					String msg = text.substring(index + 1);
					if (msg.isEmpty())
						return;
					textField.setText("/w " + nick + " ");
					textField.setCaretPosition(textField.getText().length());
					ch.sendWhisper(nick, msg);
				}
			} else {
				ch.sendRoomChat(ch.getMember().getNick() + "\t▷ " + text);
				textField.setText("");
			}
		} else if (src == inviteBtn) {
			ch.openInvite();
		} else if (src == fileSendBtn) {
			String nick = userList.getSelectedValue();
			if (nick == null) {
				JOptionPane.showMessageDialog(ch.getFrame(), "유저를 선택하세요");
			} else if (nick.equals(ch.getMember().getNick())) {
				JOptionPane.showMessageDialog(ch.getFrame(), "자신에게 보낼 수 없습니다");
			} else {
				FileDialog dlg = new FileDialog(ch.getFrame(), "파일 열기", FileDialog.LOAD);
				dlg.setDirectory("C:");
				dlg.setVisible(true);
				if (dlg.getDirectory() == null)
					return;
				ch.sendfile(nick);
				File file = new File(dlg.getDirectory() + dlg.getFile());
				SendFile sf = new SendFile(ch.getFrame(), file);
				sf.setVisible(true);
			}
		} else if (src == whisperMenu) {
			String nick = userList.getSelectedValue();
			if (nick.equals(ch.getMember().getNick())) {
				JOptionPane.showMessageDialog(ch.getFrame(), "자신에게 보낼 수 없습니다");
			} else {
				textField.setText("/w " + userList.getSelectedValue() + " ");
				textField.setCaretPosition(textField.getText().length());
				textField.requestFocus();
			}
		} else if (src == kickOffMenu) {
			String nick = userList.getSelectedValue();
			if (nick.equals(ch.getMember().getNick())) {
				JOptionPane.showMessageDialog(ch.getFrame(), "자신을 강퇴할 수 없습니다");
			} else {
				ch.kickOff(nick);
			}
		}
	}

	public void setUserList(String[] nicks) {
		userList.setListData(nicks);
		userList.setBorder(new TitledBorder(userBorder + nicks.length + "명"));
	}

	public void appendMsg(String msg) {
		textArea.append(msg + "\n");
		textArea.setCaretPosition(textArea.getText().length());
	}

	public void setChatBorder(String msg) {
		textArea.setBorder(new TitledBorder(msg));
	}

	public void setKing() {
		popup.add(kickOffMenu);
	}

	public void focusText() {
		textField.requestFocus();
	}
}
