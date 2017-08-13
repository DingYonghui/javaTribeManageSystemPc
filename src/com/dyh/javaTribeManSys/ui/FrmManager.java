package com.dyh.javaTribeManSys.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.dyh.javaTribeManSys.control.FrmManagerControl;

import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * 管理员界面
 * 视图层 ，作用是：显示数据
 * @author ding
 *
 */
public class FrmManager extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2536887944285004863L;
	
	private JPanel contentPane;
	
	private JPanel panBackground ;
	
	private JPanel panManage ;	
	private JPanel panShow ;
	private CardLayout cl ;
			
	public PanFind panFind;
	public PanAdd panAdd ;
	public PanDelete panDelete ;
	public PanUpdate panUpdate  ;
	public PanMyInformation panMyInfo ;
	public PanInstall panInstall ;
	
	private String imagePath = "./res/background/Main.jpg" ;
	
	/**
	 * Create the frame.
	 */
	public FrmManager() {
		
		//监听者类	
		FrmManagerControl managerControl = FrmLogin.frmManagerControl;
		
		panFind = new PanFind(managerControl);
		panFind.setOpaque(false);
		panAdd = new PanAdd(managerControl);
		panAdd.setOpaque(false);
		panUpdate  = new PanUpdate(managerControl);
		panUpdate.setOpaque(false);
		panDelete = new PanDelete(managerControl);
		panDelete.setOpaque(false);
		panMyInfo = new PanMyInformation(managerControl);
		panMyInfo.setOpaque(false);
		panInstall = new PanInstall(managerControl);
		panInstall.setOpaque(false);
				
		panShow = new JPanel();
		panShow.setBounds(182, 21, 652, 498);
		panShow.setOpaque(false);
		//卡片式布局管理器
	    cl = new CardLayout();
	    //设置卡片式布局管理器
		panShow.setLayout(cl);
		
		panShow.add("panFind", panFind);
		panShow.add("panAdd", panAdd);
		panShow.add("panUpdate", panUpdate);
		panShow.add("panDelete", panDelete);
		panShow.add("panMyInfo", panMyInfo);
		panShow.add("panInstall", panInstall);
			
		setResizable(false);
		setTitle("\u722A\u54C7\u90E8\u843D\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 850, 579);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setOpaque(false);
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("\u6587\u4EF6(F)");
		menuBar.add(mnFile);
		
		//注销
		JMenuItem mntmLogout = new JMenuItem("\u6CE8\u9500(L)");
		mntmLogout.setMnemonic('L');
		mntmLogout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		mntmLogout.addActionListener(managerControl);
		mnFile.add(mntmLogout);
		
		//退出
		JMenuItem mntmExit = new JMenuItem("\u9000\u51FA(X)");
		mntmExit.setMnemonic('X');	
		mntmExit.addActionListener(managerControl);
		mnFile.add(mntmExit);
		
		JMenu mnOperate = new JMenu("\u64CD\u4F5C(O)");
		mnOperate.setMnemonic('O');
		menuBar.add(mnOperate);
		
		JMenuItem mntmFind = new JMenuItem("\u67E5\u627E(F)");
		mntmFind.addActionListener(managerControl);
		mntmFind.setMnemonic('F');
		mntmFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_MASK));
		mnOperate.add(mntmFind);
		
		JMenuItem mntmAdd = new JMenuItem("\u6DFB\u52A0(A)");
		mntmAdd.addActionListener(managerControl);
		mntmAdd.setMnemonic('A');
		mntmAdd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		mnOperate.add(mntmAdd);
		
		JMenuItem mntmUpdate = new JMenuItem("\u4FEE\u6539(U)");
		mntmUpdate.addActionListener(managerControl);
		mntmUpdate.setMnemonic('U');
		mntmUpdate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_MASK));
		mnOperate.add(mntmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("\u5220\u9664(D)");
		mntmDelete.addActionListener(managerControl);
		mntmDelete.setMnemonic('D');
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_MASK));
		mnOperate.add(mntmDelete);
		
		JMenu mnHelp = new JMenu("\u5E2E\u52A9(H)");
		mnHelp.setMnemonic('H');
		menuBar.add(mnHelp);
		
		//关于
		JMenuItem mntmAbout = new JMenuItem("\u5173\u4E8E");
		mntmAbout.addActionListener(managerControl);
		mnHelp.add(mntmAbout);
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
	    panBackground = new Background(850, 579,imagePath);
	    panBackground.setOpaque(false);   
	    
	    
	    contentPane.add(panShow);
		
	    panManage = new JPanel();
		panManage.setOpaque(false);
		panManage.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7BA1\u7406", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panManage.setBounds(10, 10, 162, 406);
		contentPane.add(panManage);
		panManage.setLayout(null);
		
		JButton btnMyInformation = new JButton("\u6211\u7684\u4FE1\u606F");
		btnMyInformation.setOpaque(false);
		btnMyInformation.setBounds(10, 218, 141, 37);
		btnMyInformation.addActionListener(managerControl);
		
		JButton btnFindInformation = new JButton("\u4FE1\u606F\u67E5\u8BE2");
		btnFindInformation.setOpaque(false);
		btnFindInformation.setBounds(10, 30, 141, 37);
		btnFindInformation.setContentAreaFilled(false);
		btnFindInformation.addActionListener(managerControl);
		panManage.add(btnFindInformation);
		
		JButton btnAddInformation = new JButton("\u4FE1\u606F\u6DFB\u52A0");
		btnAddInformation.setOpaque(false);
		btnAddInformation.setBounds(10, 77, 141, 37);
		btnAddInformation.setContentAreaFilled(false);
		btnAddInformation.addActionListener(managerControl);
		panManage.add(btnAddInformation);
		
		JButton btnUpdeteInformation = new JButton("\u4FE1\u606F\u4FEE\u6539");
		btnUpdeteInformation.setOpaque(false);
		btnUpdeteInformation.setBounds(10, 124, 141, 37);
		btnUpdeteInformation.setContentAreaFilled(false);
		btnUpdeteInformation.addActionListener(managerControl);
		panManage.add(btnUpdeteInformation);
		
		JButton btnDelete = new JButton("\u4FE1\u606F\u5220\u9664");
		btnDelete.setOpaque(false);
		btnDelete.setBounds(10, 171, 141, 37);
		btnDelete.setContentAreaFilled(false);
		btnDelete.addActionListener(managerControl);
		panManage.add(btnDelete);
		btnMyInformation.setContentAreaFilled(false);
		panManage.add(btnMyInformation);
		
		JButton btnInstall = new JButton("\u8BBE\u7F6E");
		btnInstall.setOpaque(false);
		btnInstall.setBounds(10, 265, 141, 37);
		btnInstall.setContentAreaFilled(false);
		btnInstall.addActionListener(managerControl);
		panManage.add(btnInstall);
		
		//关键：背景要最后才添加
		contentPane.add(panBackground,-1);
	}
			
	/**
	 * 显示“信息查询”面板
	 */
	public void showPanFind() {		
		cl.show(panShow, "panFind");
		contentPane.updateUI();		
	}
	
	/**
	 * 显示“信息添加”面板
	 */
	public void showPanAdd() {	
		cl.show(panShow, "panAdd");
		contentPane.updateUI();	
	}
	
	/**
	 * 显示“信息修改”面板
	 */
	public void showPanUpdate() {
		cl.show(panShow, "panUpdate");
		contentPane.updateUI();	
	}
	
	/**
	 * 显示“信息删除”面板
	 */
	public void showPanDelete() {
		cl.show(panShow, "panDelete");
		contentPane.updateUI();	
	}
	
	/**
	 * 显示“我的信息”面板
	 */
	public void showPanMyInfo() {	
		cl.show(panShow, "panMyInfo");
		contentPane.updateUI();	
	}
	
	/**
	 * 显示“设置”面板
	 */
	public void showPanInstall() {
		cl.show(panShow, "panInstall");
		contentPane.updateUI();		
	}


//	public static void main(String[] args){
//		FrmManager f = new FrmManager();
//		f.setVisible(true);
//	}
	
}
