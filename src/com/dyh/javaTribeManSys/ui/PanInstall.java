package com.dyh.javaTribeManSys.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import com.dyh.javaTribeManSys.control.FrmManagerControl;

/**
 * 设置面板类
 * @author ding
 *
 */
public class PanInstall extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8782906570135069902L;
	private JPasswordField txtOldPwd;
	private JPasswordField txtNewPwd;
	private JPasswordField txtReNewPwd;
	
	private JButton btnOk ;
	private JButton btnReset ;
	
	private JButton btnFindManager ;
	private JButton btnUpdateManager ;
	
	/**
	 * Create the panel.
	 */
	public PanInstall(FrmManagerControl control) {
		
		setOpaque(false);
		setBorder(new TitledBorder(null, "\u8BBE\u7F6E", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		
		JPanel panUpdatePassword = new JPanel();
		panUpdatePassword.setOpaque(false);
		panUpdatePassword.setBorder(new TitledBorder(null, "\u5BC6\u7801\u4FEE\u6539", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panUpdatePassword.setBounds(37, 29, 531, 161);
		add(panUpdatePassword);
		panUpdatePassword.setLayout(null);
		
		JLabel lblOldPassword = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		lblOldPassword.setBounds(66, 27, 75, 22);
		panUpdatePassword.add(lblOldPassword);
		
		JLabel lblNewPassword = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		lblNewPassword.setBounds(66, 59, 75, 22);
		panUpdatePassword.add(lblNewPassword);
		
		JLabel lblReNewPassword = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblReNewPassword.setBounds(66, 91, 75, 22);
		panUpdatePassword.add(lblReNewPassword);
		
		txtOldPwd = new JPasswordField();
		txtOldPwd.setBounds(151, 28, 211, 21);
		panUpdatePassword.add(txtOldPwd);
		
		txtNewPwd = new JPasswordField();
		txtNewPwd.setBounds(151, 60, 211, 21);
		panUpdatePassword.add(txtNewPwd);
		
		txtReNewPwd = new JPasswordField();
		txtReNewPwd.setBounds(151, 92, 211, 21);
		panUpdatePassword.add(txtReNewPwd);
		
		btnOk = new JButton("\u786E\u5B9A");
		btnOk.setBounds(161, 123, 93, 23);
		btnOk.setContentAreaFilled(false);
		btnOk.addActionListener(control);
		panUpdatePassword.add(btnOk);
		
		btnReset = new JButton("\u91CD\u7F6E");
		btnReset.setBounds(264, 123, 93, 23);
		btnReset.setContentAreaFilled(false);
		btnReset.addActionListener(control);
		panUpdatePassword.add(btnReset);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBorder(new TitledBorder(null, "\u7BA1\u7406\u5458", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(37, 200, 531, 91);
		add(panel);
		panel.setLayout(null);
		
		btnFindManager = new JButton("\u67E5\u8BE2\u6240\u6709\u7BA1\u7406\u5458");
		btnFindManager.addActionListener(control);
		btnFindManager.setContentAreaFilled(false);
		btnFindManager.setOpaque(false);
		btnFindManager.setBounds(157, 33, 130, 33);
		panel.add(btnFindManager);
		
		btnUpdateManager = new JButton("\u4FEE\u6539\u7BA1\u7406\u5458\u6743\u9650");
		btnUpdateManager.setOpaque(false);
		btnUpdateManager.setContentAreaFilled(false);
		btnUpdateManager.addActionListener(control);
		btnUpdateManager.setBounds(326, 33, 130, 33);
		panel.add(btnUpdateManager);

	}

	//取得旧密码
	public String getOldPwd() {
		return String.valueOf(txtOldPwd.getPassword());
	}

	//取得新密码
	public String getNewPwd() {
		return String.valueOf(txtNewPwd.getPassword());
	}

	//取得确认密码
	public String getReNewPwd() {
		return String.valueOf(txtReNewPwd.getPassword());
	}

	/**
	 * 重置密码框
	 */
	public void clearPassword() {
		txtOldPwd.setText("");
		txtNewPwd.setText("");
		txtReNewPwd.setText("");
	}
	
	//取得确认按钮对象
	public JButton getBtnOk() {
		return btnOk;
	}
	//取得重置按钮对象
	public JButton getBtnReset() {
		return btnReset;
	}

	public JButton getBtnFindManager() {
		return btnFindManager;
	}

	public JButton getBtnUpdateManager() {
		return btnUpdateManager;
	}
	
	/**
	 * 对修改密码是否成功   的   反应操作
	 * @param isUpdatePasswordSuccessful
	 */
	public void updatePasswordResponse(boolean isUpdatePasswordSuccessful){
		
		if(isUpdatePasswordSuccessful){
			JOptionPane.showMessageDialog(this, "密码修改成功");
		}else{
			JOptionPane.showMessageDialog(this, "密码修改失败");
		}
		txtOldPwd.setText("");
		txtNewPwd.setText("");
		txtReNewPwd.setText("");
	}
	
}
