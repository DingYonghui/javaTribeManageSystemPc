package com.dyh.javaTribeManSys.ui;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.dyh.javaTribeManSys.control.FrmManagerControl;

/**
 * 登陆界面（视图层）
 * @author ding
 *
 */
public class FrmLogin extends JFrame{
	
	private static final long serialVersionUID = 1941419316845590812L;
	
	private JPanel contentPane;
	private JPanel panBackground ;
	
	private JTextField textId;
	private JPasswordField textPassword;
	
	private final ButtonGroup bgIdentity = new ButtonGroup();
	
	private String imagePath = "./res/background/Login.jpg" ;
	
	//监听者（控制器）
	public static FrmManagerControl frmManagerControl ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
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
	public FrmLogin() {
		        
		frmManagerControl = new FrmManagerControl(this);
		
	    setTitle("登陆");
	    setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 450, 300);
  		
		contentPane = new JPanel();	
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//背景面板
		panBackground = new Background(450, 300, imagePath);
		panBackground.setOpaque(false);
		
		
		JLabel lblId = new JLabel("\u767B\u5F55\u540D\uFF1A");	
		lblId.setBounds(79, 103, 54, 22);
		lblId.setOpaque(false);
		contentPane.add(lblId);
		
		textId = new JTextField();
		textId.setText("请输入学号");
		textId.setBounds(143, 104, 151, 21);
		contentPane.add(textId);
		textId.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent e) {
				textId.setText("");		
			}
		});
		textId.setColumns(10);
		
		JLabel lblIdTip = new JLabel("* 9\u4F4D\u6570\u5B57");
		lblIdTip.setBounds(294, 104, 151, 21);
		contentPane.add(lblIdTip);
		
		JLabel lblPassword = new JLabel("\u5BC6\u7801\uFF1A");
		lblPassword.setBounds(79, 134, 54, 22);
		lblPassword.setOpaque(false);
		contentPane.add(lblPassword);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(143, 135, 151, 21);
		contentPane.add(textPassword);
		
		JLabel lblPasswordTip = new JLabel("* 9-15\u4F4D\u6570\u5B57\u6216\u82F1\u6587");
		lblPasswordTip.setBounds(294, 135, 151, 21);
		contentPane.add(lblPasswordTip);
		
		JRadioButton rbtnStudent = new JRadioButton("\u5B66\u751F");
		bgIdentity.add(rbtnStudent);
		rbtnStudent.setBounds(152, 162, 75, 23);
		rbtnStudent.setContentAreaFilled(false);
		contentPane.add(rbtnStudent);
		
		JRadioButton rbtnManager = new JRadioButton("\u7BA1\u7406\u5458");
		rbtnManager.setSelected(true);
		bgIdentity.add(rbtnManager);
		rbtnManager.setBounds(229, 162, 75, 23);
		rbtnManager.setContentAreaFilled(false);
		contentPane.add(rbtnManager);
		
		JButton btnLogin = new JButton("\u767B\u9646");
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBounds(150, 191, 137, 23);
		btnLogin.addActionListener(frmManagerControl);
		contentPane.add(btnLogin);
		
		contentPane.add(panBackground,-1);
		
	}

	/**
	 * 是否选择以管理员身份登陆
	 * @return
	 */
	public boolean isManager(){
			
		Enumeration<AbstractButton> e = bgIdentity.getElements();
		JRadioButton rd;
		while(true){		
			rd = (JRadioButton)e.nextElement();	
		
			if( rd.isSelected() ){
				
				if(rd.getActionCommand().equals("管理员"))
					return true;
				else
					return false;
			
			}
		
		}
			
	}

	/**
	 * 获得id与password
	 * @param valueName
	 * @return
	 */
	public String getParameter(String valueName) {
		if(valueName.equalsIgnoreCase("id"))
			return textId.getText();
		else if(valueName.equalsIgnoreCase("password"))
			return String.valueOf(textPassword.getPassword());
		else	
		    return null;
	}

	/**
	 * 登陆成功或失败的反应
	 * @param isLoginSuccessful
	 */
	public void loginResponse(boolean isLoginSuccessful) {
		if(isLoginSuccessful){
			dispose();
		}	
	}
	
	
}
