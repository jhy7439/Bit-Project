﻿package client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

 
public class PwdDialog extends JDialog implements ActionListener {

	private JPasswordField textField;
	private JButton okBtn;
	private JButton ccBtn;
	private ClientHandler ch;

	public PwdDialog(JFrame frame) {
		super(frame, "암호 입력", true);
		ch = ClientHandler.getInstance();
		init();
		setSize(275, 159);
		setLocation(frame.getLocation().x + 50, frame.getLocation().y + 50);
	}

	private void init() {
		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("암호를 입력하세요");
		lblNewLabel.setBounds(77, 10, 108, 15);
		contentPanel.add(lblNewLabel);

		textField = new JPasswordField();
		textField.setBounds(42, 35, 178, 31);
		contentPanel.add(textField);
		textField.setColumns(10);

		okBtn = new JButton("확인");
		okBtn.setBounds(42, 76, 83, 31);
		contentPanel.add(okBtn);

		ccBtn = new JButton("취소");
		ccBtn.setBounds(136, 76, 83, 31);
		contentPanel.add(ccBtn);

		okBtn.addActionListener(this);
		ccBtn.addActionListener(this);
		textField.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okBtn || e.getSource() == textField) {
			String pwd = String.valueOf(textField.getPassword());
			if (pwd.isEmpty())
				return;
			if (pwd.equals(ch.getMember().getPwd())) {
				dispose();
				ch.openModify();
			} else {
				JOptionPane.showMessageDialog(this, "암호가 틀립니다");
			}
		} else if (e.getSource() == ccBtn) {
			dispose();
		}
	}

}
