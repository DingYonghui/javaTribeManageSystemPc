package com.dyh.javaTribeManSys.ui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.dyh.javaTribeManSys.control.FrmManagerControl;

import javax.swing.border.TitledBorder;

/**
 * 信息删除面板
 * @author ding
 *
 */
public class PanDelete extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4028701431814553949L;
	private JTextField txtDelete;
	private JButton btnSureDelete ;
	/**
	 * Create the panel.
	 */
	public PanDelete(FrmManagerControl control ) {
		
		setOpaque(false);
		setBorder(new TitledBorder(null, "\u4FE1\u606F\u5220\u9664", TitledBorder.LEADING, TitledBorder.TOP, null, null));	
		setLayout(null);
		
		JLabel lblDelete = new JLabel("\u8BF7\u8F93\u5165\u8981\u5220\u9664\u7684\u5B66\u53F7\uFF1A");
		lblDelete.setBounds(53, 87, 149, 30);
		add(lblDelete);
		
		txtDelete = new JTextField();
		txtDelete.setBounds(212, 87, 188, 30);
		add(txtDelete);
		txtDelete.setColumns(10);
		
	    btnSureDelete = new JButton("确认删除");
		btnSureDelete.setContentAreaFilled(false);
		btnSureDelete.setOpaque(false);
		btnSureDelete.setBounds(437, 87, 93, 30);
		btnSureDelete.addActionListener(control);
		add(btnSureDelete);

	}
	
	
	public String getParameter(String valueName) {
		
		if(valueName.equalsIgnoreCase("id")){
			return txtDelete.getText();
		}else{
			return null;
		}
			
		    
		
	}
	public void clearId() {
		txtDelete.setText("");
	}
	public JButton getBtnSureDelete() {
		return btnSureDelete;
	}


	public void deleteResponse(boolean isDeleteSuccessful) {
		if(isDeleteSuccessful){
			JOptionPane.showMessageDialog(this, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(this, "删除失败", "提示", JOptionPane.WARNING_MESSAGE);
		}
		
	}
}
