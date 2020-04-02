package client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

 
public class MainFrame extends JFrame implements ActionListener {
	private JMenuItem joinItem;
	private JMenuItem modItem;
	private JMenuItem logoutItem;
	private JMenuItem infoItem;
	private ClientHandler ch;

	public MainFrame() {
		JMenuBar menubar = new JMenuBar();
		JMenu menu1 = new JMenu("Menu");
		JMenu menu2 = new JMenu("Help");
		menubar.add(menu1);
		menubar.add(menu2);
		joinItem = new JMenuItem("가입");
		modItem = new JMenuItem("수정");
		logoutItem = new JMenuItem("로그아웃");
		infoItem = new JMenuItem("정보");
		menu1.add(joinItem);
		menu1.add(modItem);
		menu1.add(new JSeparator());
		menu1.add(logoutItem);
		menu2.add(infoItem);
		modItem.setEnabled(false);
		logoutItem.setEnabled(false);

		setJMenuBar(menubar);

		
		setResizable(false);

		joinItem.addActionListener(this);
		modItem.addActionListener(this);
		logoutItem.addActionListener(this);
		infoItem.addActionListener(this);
	}

	public void setHandler(ClientHandler ch) {
		this.ch = ch;
	}

	public void setPanel(JPanel panel) {
		
		if (panel instanceof LoginPanel) {
			setSize(500, 500);
			setTitle("NODAK");
			setResizable(false);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension size = getSize();
			setLocation(screenSize.width / 2 - size.width / 2, screenSize.height / 2 - size.height);
		} else if (panel instanceof WaitRoom) {
			setSize(500, 500);
		} else if (panel instanceof ChatRoom) {
			setSize(500, 500);
		}
		setContentPane(panel);
		revalidate();
		setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == joinItem) {
			ch.openJoin();
		} else if (src == infoItem) {
			JOptionPane.showMessageDialog(this, "관리자 : 한경수\n연락처 : hanks0716@naver.com");
		} else if (src == logoutItem) {
			ch.logout();
		} else if (src == modItem) {
			ch.openPwdCheck();
		}
	}

	public void setMenuItemEnabled(boolean flag) {
		joinItem.setEnabled(!flag);
		modItem.setEnabled(flag);
		logoutItem.setEnabled(flag);
	}
}
