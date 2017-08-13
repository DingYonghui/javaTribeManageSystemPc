package com.dyh.javaTribeManSys.main;

import java.awt.EventQueue;

import com.dyh.javaTribeManSys.ui.FrmLogin;

/**
 * 程序的入口
 * @author ding
 *
 */

public class javaTribeManageSystem {
	
	public static FrmLogin frmLogin ;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmLogin = new FrmLogin();
					frmLogin.setVisible(true);				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
