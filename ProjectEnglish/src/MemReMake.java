import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class MemReMake extends JFrame{//����Ŭ����
	public MemReMake(){
	JPanel p = new JPanel();
    p.setLayout(null);
    Label l1= new Label("�̸�");
    Label l2 = new Label("���̵�");
    Label l3= new Label("��й�ȣ");
    Label l4 = new Label("�ּ�");
    Label l5 = new Label("�̸���");
    add(l1);
    add(l2);
    add(l3);
    add(l4);
    add(l5);
    TextField t1 = new TextField();
    TextField t2 = new TextField();
    TextField t3 = new TextField();
    TextField t4 = new TextField();
    TextField t5 = new TextField();
    t3.setEchoChar('*');
    add(t1);
    add(t2);
    add(t3);
    add(t4);
    add(t5);
    t1.setEditable(true);
    t2.setEditable(true);
    t3.setEditable(true);
    t4.setEditable(true);
    t5.setEditable(true);
    JButton j1 = new JButton("����");
    JButton j2 = new JButton("���");
    add(j1);
    add(j2);
    l1.setBounds(40, 10, 40, 40);
    l2.setBounds(40, 50, 40, 40);
    l3.setBounds(40,90,60,40);
    l4.setBounds(40, 130, 40, 40);
    l5.setBounds(40, 170, 60, 40);
    t1.setBounds(120, 10, 200, 30);
    t2.setBounds(120, 50, 200, 30);
    t3.setBounds(120, 90, 200, 30);
    t4.setBounds(120, 130, 280, 30);
    t5.setBounds(120, 180, 280, 30);
    j1.setBounds(125, 280, 80, 30);
    j2.setBounds(240, 280, 80, 30);
	add(p);
	setSize(500,400);
	setTitle("����");
	setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	setVisible(true);
	try { //������ ȸ������ �ҷ�����
		   String s; 
		   String[] array; 
		   BufferedReader br = new BufferedReader(new FileReader("ȸ�����.txt")); 
		   while ((s = br.readLine()) != null) { 
		      array = s.split("/"); 
		     t1.setText(array[0]);
		     t2.setText(array[1]);
		     t3.setText(array[2]);
		     t4.setText("");
		     t5.setText("");
		   } 
		   br.close(); 
		   } catch (IOException e2) { 
		   e2.printStackTrace(); 
		}
	j1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ee) {//�����Ѱ��� �ٽ� ����
			// TODO Auto-generated method stub
			try{
				BufferedWriter bo = new BufferedWriter(new FileWriter("ȸ�����.txt"));
				bo.write(t1.getText()+"/");
				bo.write(t2.getText()+"/");
				bo.write(t3.getText()+"/");
				bo.write(t4.getText()+"/");
				bo.write(t5.getText()+"\r\n");
			
				bo.close();
				dispose();
				 JOptionPane.showMessageDialog(null, "�����Ͽ����ϴ�."); 
	        } catch (Exception ex) { 
	        JOptionPane.showMessageDialog(null, "���忡 �����Ͽ����ϴ�."); 
	     } 
			}
	});
	j2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			setVisible(false);
		}
	});
		}
	}