package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import system.Bank;
import system.Manager;
import system.User;
import system.Worker;

import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;

public class BankGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField_userName;
	private JTextField textField_workerName;
	private JTextField textField_inMoney;
	private JTextField textField_outMoney;
	
	private boolean userlogin = false;
	private boolean workerlogin = false;
	
	private User u = null;
	private Worker w = null;
	private Manager m = null;
	
	private boolean WorkerAction = false;
	private boolean ManagerAction = false;
	
	String name = Bank.class.getName();
	public Logger log1 = LogFactory.getLogger(name);// 记录异常/错误的日志
	private JPasswordField passwordField_user;
	private JPasswordField passwordField_worker;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				Bank bank = new Bank();
				try {
					bank = bank.initBank(bank);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					BankGUI frame = new BankGUI(bank);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BankGUI(Bank bank) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 758);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(677, 250, 226, 49);
		contentPane.add(scrollPane);
		
		JTextArea textArea_worker = new JTextArea();
		scrollPane.setViewportView(textArea_worker);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(790, 21, 164, 38);
		contentPane.add(scrollPane_1);
		
		JTextArea textArea_manager = new JTextArea();
		scrollPane_1.setViewportView(textArea_manager);
		
		JButton btnNewButton = new JButton("银行营业！");
		btnNewButton.setBounds(113, 13, 511, 60);
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 28));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BossLogin dialog = new BossLogin(bank,m,log1,textArea_manager,textArea_worker);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocation(500, 500);
				dialog.setVisible(true);
				m = bank.manager;
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		textField_userName = new JTextField();
		textField_userName.setBounds(130, 258, 138, 24);
		contentPane.add(textField_userName);
		textField_userName.setColumns(10);
		
		passwordField_user = new JPasswordField();
		passwordField_user.setBounds(130, 305, 138, 24);
		contentPane.add(passwordField_user);
		
		
		JButton button = new JButton("客户登录");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserName = textField_userName.getText();
				String UserPasswd = new String(passwordField_user.getPassword());
				u = bank.UserLogin(UserName, UserPasswd);
				if(!workerlogin) {
					log1.info("前台工作人员上班迟到！");
					LoginWarning dialog = new LoginWarning("前台人员没有准备就绪！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}else if(u!=null) {
					log1.info("客户"+u.getName()+"来办业务！");
					userlogin = true;
					LoginWarning dialog1 = new LoginWarning("哈工大银行为您服务！");
					dialog1.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog1.setLocation(500, 500);
					dialog1.setVisible(true);
					LoginWarning dialog = new LoginWarning("登录成功！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
					u.map.put(u, w);
					w.map.put(w, u);
				}else {
					log1.info("用户输入密码错误！");
					userlogin = false;
					LoginWarning dialog = new LoginWarning("密码错误，登陆失败！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}
				
			}
		});
		button.setBounds(113, 201, 132, 36);
		button.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(button);
		
		JLabel lblNewLabel = new JLabel("账户姓名：");
		lblNewLabel.setBounds(41, 250, 90, 36);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("账户密码：");
		label.setBounds(41, 297, 90, 36);
		label.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(label);
		
		
		
		JLabel label_1 = new JLabel("行长信息：");
		label_1.setBounds(677, 13, 103, 36);
		label_1.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("行长监督：");
		label_2.setBounds(677, 77, 103, 36);
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label_2);
		
		JButton button_1 = new JButton("同意");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log1.info("行长同意了客户请求！");
				m.accept();
				ManagerAction = true;
			}
		});
		button_1.setBounds(801, 81, 113, 27);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("拒绝");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log1.info("行长拒绝了客户请求！");
				m.refuse();
				ManagerAction = true;
			}
		});
		button_2.setBounds(802, 122, 113, 27);
		contentPane.add(button_2);
		
		
		textField_workerName = new JTextField();
		textField_workerName.setBounds(489, 263, 138, 24);
		textField_workerName.setColumns(10);
		contentPane.add(textField_workerName);
		
		passwordField_worker = new JPasswordField();
		passwordField_worker.setBounds(489, 309, 138, 24);
		contentPane.add(passwordField_worker);
		
		JButton button_3 = new JButton("前台登录");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String WorkerName = textField_workerName.getText();
				String WorkerPasswd = new String(passwordField_worker.getPassword());
				w = bank.WorkLogin(WorkerName, WorkerPasswd);
				if(w!=null) {
					log1.info("前台"+w.getName()+"来上岗了！");
					workerlogin = true;
					LoginWarning dialog = new LoginWarning("登录成功！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}else {
					log1.info("前台工作人员输入密码错误！");
					workerlogin = false;
					LoginWarning dialog = new LoginWarning("密码错误，登陆失败！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}
			}
		});
		button_3.setBounds(472, 206, 132, 36);
		button_3.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(button_3);
		
		JLabel label_3 = new JLabel("职工姓名：");
		label_3.setBounds(400, 255, 90, 36);
		label_3.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("职工密码：");
		label_4.setBounds(400, 302, 90, 36);
		label_4.setFont(new Font("宋体", Font.PLAIN, 18));
		contentPane.add(label_4);
		
		
		
		JLabel label_5 = new JLabel("前台职工信息：");
		label_5.setBounds(682, 206, 158, 36);
		label_5.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("职工操作：");
		label_6.setBounds(678, 307, 103, 36);
		label_6.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label_6);
		
		JButton button_4 = new JButton("同意");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log1.info("前台同意了客户请求！");
				w.accept();
				WorkerAction = true;
			}
		});
		button_4.setBounds(790, 312, 113, 27);
		contentPane.add(button_4);
		
		JButton button_5 = new JButton("拒绝");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log1.info("前台拒绝了客户请求！");
				w.refuse();
				WorkerAction = true;
			}
		});
		button_5.setBounds(791, 353, 113, 27);
		contentPane.add(button_5);
		
		JLabel label_7 = new JLabel("客户请求：");
		label_7.setBounds(27, 410, 103, 36);
		label_7.setFont(new Font("宋体", Font.PLAIN, 20));
		contentPane.add(label_7);
		
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(472, 474, 445, 179);
		contentPane.add(scrollPane_2);
		
		JTextArea textArea_result = new JTextArea();
		scrollPane_2.setViewportView(textArea_result);
		
		
		JButton btnNewButton_1 = new JButton("查询卡内资金");
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!userlogin) {
					log1.info("当前用户还未登录！");
					LoginWarning dialog = new LoginWarning("当前用户还未登录！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}else {
					u.viewMoney();
					if(WorkerAction&&ManagerAction) {
						w.handleRequest(m, bank);
						textArea_result.setText(u.getResult());
					}else {
						textArea_result.setText("前台工作人员和经理正在审核，请稍等......");
						log1.info("前台工作人员:"+w.getName()+"和经理:"+m.getName()+"正在审核查询请求！");
					}
					WorkerAction = false;
					ManagerAction = false;
					log1.info("客户"+u.getName()+"查询卡内资金！");
				}
			}
		});
		btnNewButton_1.setBounds(131, 417, 148, 48);
		contentPane.add(btnNewButton_1);
		
		textField_inMoney = new JTextField();
		textField_inMoney.setBounds(314, 532, 97, 24);
		contentPane.add(textField_inMoney);
		textField_inMoney.setColumns(10);
		
		JButton button_6 = new JButton("存钱");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!userlogin) {
					log1.info("当前用户还未登录！");
					LoginWarning dialog = new LoginWarning("当前用户还未登录！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}else {
					String money = textField_inMoney.getText();
					u.putMoney(money);;
					if(WorkerAction&&ManagerAction) {
						w.handleRequest(m, bank);
						textArea_result.setText(u.getResult());
					}else {
						textArea_result.setText("前台工作人员和经理正在审核，请稍等......");
						log1.info("前台工作人员:"+w.getName()+"和经理:"+m.getName()+"正在审核存钱请求！");
					}
					WorkerAction = false;
					ManagerAction = false;
					log1.info("客户"+u.getName()+"存入了"+textField_inMoney.getText()+"钱！");
				}
			}
		});
		button_6.setFont(new Font("宋体", Font.PLAIN, 18));
		button_6.setBounds(131, 508, 148, 48);
		contentPane.add(button_6);
		
		textField_outMoney = new JTextField();
		textField_outMoney.setColumns(10);
		textField_outMoney.setBounds(314, 629, 97, 24);
		contentPane.add(textField_outMoney);
		
		JButton button_7 = new JButton("取钱");
		button_7.setFont(new Font("宋体", Font.PLAIN, 18));
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!userlogin) {
					log1.info("当前用户还未登录！");
					LoginWarning dialog = new LoginWarning("当前用户还未登录！");
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocation(500, 500);
					dialog.setVisible(true);
				}else {
					String money = textField_outMoney.getText();
					u.getMoney(money);;
					if(WorkerAction&&ManagerAction) {
						w.handleRequest(m, bank);
						textArea_result.setText(u.getResult());
					}else {
						textArea_result.setText("前台工作人员和经理正在审核，请稍等......");
						log1.info("前台工作人员:"+w.getName()+"和经理:"+m.getName()+"正在审核取钱请求！");
					}
					WorkerAction = false;
					ManagerAction = false;
					log1.info("客户"+u.getName()+"取走了"+textField_outMoney.getText()+"钱！");
				}
			}
		});
		button_7.setBounds(129, 605, 150, 48);
		contentPane.add(button_7);
		
		JLabel lblNewLabel_1 = new JLabel("   金额");
		lblNewLabel_1.setBounds(325, 508, 86, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_8 = new JLabel("   金额");
		label_8.setBounds(325, 605, 86, 18);
		contentPane.add(label_8);

		JLabel lblNewLabel_2 = new JLabel("请求结果：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(472, 421, 142, 36);
		contentPane.add(lblNewLabel_2);
		
		JButton button_8 = new JButton("银行关门！");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log1.info("银行关门了！");
				File file = new File("src/system/bank.txt");
				if(u == null||w == null) {
					System.exit(0);
				}
				try {
					FileWriter out = new FileWriter(file);
					BufferedWriter writer = new BufferedWriter(out);
					String content = "";
					content = content + "用户" + "\r\n";
					Iterator<User> it1 = bank.getUsers().iterator();
					while(it1.hasNext()) {
						User u = it1.next();
						content = content + "姓名:" +u.getName()+"\t"+ "密码:" +u.getPassword()+"\t"+ "存款:"+bank.getMoneySystem().get(u)+"\r\n";
					}
					content = content + "\r\n";
					content = content + "前台" + "\r\n";
					Iterator<Worker> it2 = bank.SetWorker.iterator();
					while(it2.hasNext()) {
						Worker w = it2.next();
						content = content + "姓名:" +w.getName()+"\t"+ "密码:" +w.getPassword()+"\t"+ "id:"+w.getid()+"\r\n";
					}
					content = content + "\r\n";
					content = content + "经理" + "\r\n";
					content = content + "姓名:" +m.getName()+"\t"+ "密码:" +m.getPasswd();
					writer.write(content);
					writer.flush();
					writer.close();
					System.exit(0);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_8.setFont(new Font("宋体", Font.PLAIN, 28));
		button_8.setBounds(113, 86, 511, 60);
		contentPane.add(button_8);
		
		JButton button_9 = new JButton("审计");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				log1.info("行长"+m.getName()+"查看了审计日志！");
				Log dialog = null;
				try {
					dialog = new Log();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setLocation(500, 500);
				dialog.setVisible(true);
			}
		});
		button_9.setBounds(801, 162, 113, 27);
		contentPane.add(button_9);
		
		
		
		
		
		
	}
}
