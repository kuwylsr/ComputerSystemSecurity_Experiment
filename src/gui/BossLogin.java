package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import system.Bank;
import system.Manager;
import system.Worker;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

public class BossLogin extends JFrame {

	private JPanel contentPane;
	private JTextField textField_name;
	private JPasswordField passwordField;

	public boolean bossLogin = false;
	/**
	 * Create the frame.
	 */
	public BossLogin(Bank bank,Manager m,Logger log1,JTextArea textArea_manager,JTextArea textArea_worker) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("姓名：");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(57, 100, 72, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("密码：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(57, 149, 72, 18);
		contentPane.add(lblNewLabel_2);
		
		textField_name = new JTextField();
		textField_name.setBounds(129, 99, 180, 24);
		contentPane.add(textField_name);
		textField_name.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(129, 148, 180, 24);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("行长登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String Name = textField_name.getText();
				String Passwd = new String(passwordField.getPassword());
				if(bank.BossLogin(Name, Passwd)!=null) {
					bossLogin = true;
					LoginWarning dialog = new LoginWarning("登录成功！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}else {
					bossLogin = false;
					LoginWarning dialog = new LoginWarning("密码错误，登陆失败！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}
				if(bossLogin) {
					log1.info("银行开门了！");
					Set<Worker> SetWorker = bank.SetWorker;
					textArea_manager.setText("行长："+bank.manager.getName()+"\n"+" 已上岗！");
					Iterator<Worker> it = SetWorker.iterator();
					Worker w = it.next();
					textArea_worker.setText("职工："+w.getName()+"\n"+"编号："+w.getid()+"\n"+"正在为您服务！");
				}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 25));
		btnNewButton.setBounds(129, 28, 180, 47);
		contentPane.add(btnNewButton);
	}
}
