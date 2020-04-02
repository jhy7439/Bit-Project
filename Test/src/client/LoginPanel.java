package client;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

 
public class LoginPanel extends JPanel implements ActionListener {
	private JTextField idField;
	private JPasswordField pwdField;
	private JButton btnLogin;
	private JButton btnClose;
	private ClientHandler ch;

	private void init() {
		setLayout(null);
		
		JLabel lblTt = new JLabel("NODAK");
		lblTt.setBounds(100, 94, 300, 100);
		lblTt.setFont(lblTt.getFont().deriveFont(78.0f));
		add(lblTt);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(162, 194, 80, 25);
		add(lblId);

		JLabel lblPwd = new JLabel("PASSWORD");
		lblPwd.setBounds(104, 229, 80, 25);
		add(lblPwd);

		idField = new JTextField();
		idField.setBounds(184, 194, 200, 30);
		add(idField);
		idField.setColumns(10);

		pwdField = new JPasswordField();
		pwdField.setBounds(184, 229, 200, 30);
		add(pwdField);
		pwdField.setColumns(10);

		btnLogin = new JButton("로그인");
		btnLogin.setBounds(184, 268, 95, 30);
		add(btnLogin);

		btnClose = new JButton("취소");
		btnClose.setBounds(289, 268, 95, 30);
		add(btnClose);

	}

	private void start() {
		pwdField.addActionListener(this);
		btnLogin.addActionListener(this);
		btnClose.addActionListener(this);
	}

	public LoginPanel() {
		init();
		start();
		ch = ClientHandler.getInstance();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if (src == pwdField || src == btnLogin) {
			if (idField.getText().length() == 0) {
				JOptionPane.showMessageDialog(ch.getFrame(), "아이디를 입력하세요");
			} else if (pwdField.getPassword().length == 0) {
				JOptionPane.showMessageDialog(ch.getFrame(), "암호를 입력하세요");
			} else {
				ch.requestLogin(idField.getText() + "," + String.valueOf(pwdField.getPassword()));
			}
		} else if (src == btnClose) {
			System.exit(0);
		}
	}

	public void setId(String id) {
		idField.setText(id);
	}

	public void focusIdField() {
		idField.requestFocus();
		idField.selectAll();
	}

	public void focusPwdField() {
		pwdField.requestFocus();
	}

	public void setPwdField(String string) {
		pwdField.setText(string);
	}
}
