package com.dyh.javaTribeManSys.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import com.dyh.javaTribeManSys.control.FrmManagerControl;
import javax.swing.border.TitledBorder;

/**
 * ��Ϣ�޸������
 * @author ding
 *
 */
public class PanUpdate extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 403227216366505736L;
	private JTextField textId;
	private JButton btnSureUpdate ;
	/**
	 * Create the panel.
	 */
	public PanUpdate(FrmManagerControl control ) {
		
		setOpaque(false);
		setBorder(new TitledBorder(null, "\u4FE1\u606F\u4FEE\u6539", TitledBorder.LEADING, TitledBorder.TOP, null, null));		
		setLayout(null);
		
		JLabel lblUpdate = new JLabel("\u8BF7\u8F93\u5165\u8981\u4FEE\u6539\u7684\u5B66\u53F7\uFF1A");
		lblUpdate.setBounds(53, 87, 149, 30);
		add(lblUpdate);
		
		textId = new JTextField();
		textId.setBounds(212, 87, 188, 30);
		add(textId);
		textId.setColumns(10);
		
		btnSureUpdate = new JButton("ȷ���޸�");
		btnSureUpdate.addActionListener(control);
		btnSureUpdate.setOpaque(false);
		btnSureUpdate.setContentAreaFilled(false);
		btnSureUpdate.setBounds(437, 87, 93, 30);
		add(btnSureUpdate);

	}
	
	//�õ��ı����е�id
	public String getParameter(String valueName){
		
		if(valueName.equals("id"))
			return textId.getText();	
		else
			return null;
		
	}

	public JButton getBtnSureUpdate() {
		return btnSureUpdate;
	}
	
	public void BtnUpdateResponse(boolean isUserExist,String id,
			FrmManager frmManager,PanMyInformation panMyInfo){
		
		if(isUserExist){
			//��ʾ�ҵ���Ϣ��塢���ݻ�õ�id���������Ϣ���������߽�ı���
			frmManager.showPanMyInfo();
			panMyInfo.initMyInformation(id);
			panMyInfo.setBorder(new TitledBorder(null, "��Ϣ�޸�", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			//ʹ�޸İ�ť���ɱ༭��ȫ���ı���ɱ༭��ȷ����ť�ɱ༭������ͼ��ť���ɱ༭
			panMyInfo.setBtnUpdate_Enabled(false);
			panMyInfo.setAllTextField_Editable(true);
			panMyInfo.setBtnOK_Enabled(true);
			panMyInfo.setBtnChangeHeadImage_Enabled(true);
			//����UI
			panMyInfo.updateUI();
		}
			    
	}
}
