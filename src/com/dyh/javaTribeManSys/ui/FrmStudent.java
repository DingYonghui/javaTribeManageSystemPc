package com.dyh.javaTribeManSys.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

/**
 * 一般成员界面
 * @author ding
 *
 */
public class FrmStudent extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9010706036880007380L;
	
	private JTabbedPane contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmMember frame = new FrmMember();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrmStudent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 150, 650, 500);
		
		String[] tabNames = {"个人信息","爪哇部落信息","信息咨询","设置"};
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("\u6587\u4EF6(F)");
		mnFile.setMnemonic('F');
		menuBar.add(mnFile);
		
		JMenuItem mntmLogout = new JMenuItem("\u6CE8\u9500(L)");
		mntmLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntmLogout.setMnemonic('L');
		mntmLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				FrmLogin login = new FrmLogin();
				login.setVisible(true);
			}
		});
		mnFile.add(mntmLogout);
		
		JMenuItem mntmExit = new JMenuItem("\u9000\u51FA(X)");
		mntmExit.setMnemonic('X');
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnAbout = new JMenu("\u5173\u4E8E(A)");
		mnAbout.setMnemonic('A');
		menuBar.add(mnAbout);
		
		JMenuItem mntmHelp = new JMenuItem("\u5E2E\u52A9(H)");
		mntmHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "爪哇部落信息管理系统");
			}
		});
		mntmHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_MASK));
		mnAbout.add(mntmHelp);
			
		contentPane = new JTabbedPane();
		contentPane.addTab(tabNames[0], new JLabel());
		contentPane.addTab(tabNames[1], new JLabel());
		contentPane.addTab(tabNames[2], new JLabel());
		contentPane.addTab(tabNames[3], new JLabel());
		setContentPane(contentPane);
	}

}
