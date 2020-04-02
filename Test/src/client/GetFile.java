package client;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;


public class GetFile extends JDialog implements ActionListener {
	private JProgressBar pBar;
	private JButton okBtn;
	private JButton ccBtn;
	private String addr;
	private Socket socket;
	private String fileName;
	private long fileLength;

	public GetFile(JFrame frame, String addr) {
		super(frame, "파일받기", false);
		this.addr = addr;
		init();
		start();
		setSize(300, 130);
		setLocation(frame.getLocation().x + 10, frame.getLocation().y + 10);
	}

	private void init() {

		getContentPane().setLayout(new BorderLayout());
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		pBar = new JProgressBar();
		pBar.setBounds(12, 10, 260, 30);
		contentPanel.add(pBar);
		pBar.setStringPainted(true);
		pBar.setString("대기중... 0%");

		okBtn = new JButton("받기");
		okBtn.setBounds(51, 50, 80, 30);
		contentPanel.add(okBtn);

		ccBtn = new JButton("취소");
		ccBtn.setBounds(143, 50, 80, 30);
		contentPanel.add(ccBtn);

		okBtn.addActionListener(this);
		ccBtn.addActionListener(this);
	}

	private void start() {
		BufferedReader in = null;
		try {
			socket = new Socket(addr, Message.FILE_PORT);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String msg = in.readLine();
			String[] tmp = msg.split(",");
			fileName = tmp[0];
			fileLength = Long.parseLong(tmp[1]);
			if (fileName.length() > 20)
				pBar.setString(fileName.substring(0, 20) + "...");
			else
				pBar.setString(fileName);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		PrintWriter out = null;
		if (src == okBtn) {
			try {
				out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				FileDialog dlg = new FileDialog(this, "파일 저장", FileDialog.SAVE);
				dlg.setDirectory("C:");
				dlg.setFile(fileName);
				dlg.setVisible(true);
				final File file = new File(dlg.getDirectory() + dlg.getFile());
				new Thread(new Runnable() {
					@Override
					public void run() {
						getFile(file);
					}
				}).start();
				out.println("Accept");
				out.flush();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		} else if (src == ccBtn) {
			try {
				out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
				out.println("Cancel");
				out.flush();
				dispose();
			} catch (IOException e1) {
				e1.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			dispose();
		}
	}

	private void getFile(File file) {
		FileOutputStream out = null;
		InputStream in = null;
		int b = 0;
		int cnt = 0;
		int receive = 0;
		try {
			out = new FileOutputStream(file);
			in = socket.getInputStream();
			while (true) {
				b = in.read();
				if (b == -1)
					break;
				out.write((char) b);
				out.flush();
				cnt++;
				receive = (int) (cnt / fileLength);
				if (receive > pBar.getValue()) {
					pBar.setValue(receive);
					pBar.setString("수신중\t" + receive + "%");
				}
			}
			pBar.setValue(100);
			pBar.setString("수신 완료");
			JOptionPane.showMessageDialog(this, "파일을 다 받았습니다");
			dispose();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
