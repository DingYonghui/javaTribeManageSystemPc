package com.dyh.javaTribeManSys.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.dyh.javaTribeManSys.control.FrmManagerControl;
import com.dyh.javaTribeManSys.dao.impl.ManagerDaoImpl;
import com.dyh.javaTribeManSys.pojo.User;

/**
 * �ҵ���Ϣ�����
 * @author ding
 *
 */
public class PanMyInformation extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4365509612638202715L;
	private JTextField txtName;
	private JTextField txtSex;
	private JTextField txtId;
	private JTextField txtGrade;
	private JTextField txtDepartment;
	private JTextField txtQQ;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtBirthday;
	private JTextArea txtSign ;
	
	private JButton btnUpdate ;
	private JButton btnOK ;
	private JButton btnChangeHeadImage ;
	private JPanel panHeadImage ;

	/**
	 * Create the panel.
	 */
	public PanMyInformation(FrmManagerControl control) {

		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"\u6211\u7684\u4FE1\u606F", TitledBorder.LEFT,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		setOpaque(false);
		setLayout(null);

		JLabel lblName = new JLabel("\u59D3\u540D\uFF1A");
		lblName.setBounds(10, 48, 44, 15);
		add(lblName);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setBounds(88, 45, 181, 21);
		add(txtName);
		txtName.setHorizontalAlignment(JTextField.LEFT);

		JLabel lblSex = new JLabel("\u6027\u522B\uFF1A");
		lblSex.setBounds(279, 48, 44, 15);
		add(lblSex);

		txtSex = new JTextField();
		txtSex.setEditable(false);
		txtSex.setBounds(333, 45, 131, 21);
		add(txtSex);
		txtSex.setHorizontalAlignment(JTextField.LEFT);

		JLabel lblId = new JLabel("\u5B66\u53F7\uFF1A");
		lblId.setBounds(10, 85, 44, 15);
		add(lblId);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(88, 82, 181, 21);
		add(txtId);
		txtId.setHorizontalAlignment(JTextField.LEFT);

		JLabel lblGrade = new JLabel("\u5E74\u7EA7\uFF1A");
		lblGrade.setBounds(279, 85, 44, 15);
		add(lblGrade);

		txtGrade = new JTextField();
		txtGrade.setEditable(false);
		txtGrade.setBounds(333, 82, 131, 21);
		add(txtGrade);
		txtGrade.setHorizontalAlignment(JTextField.LEFT);

		JLabel lblDepartment = new JLabel("\u7CFB\u522B\uFF1A");
		lblDepartment.setBounds(10, 122, 44, 15);
		add(lblDepartment);

		txtDepartment = new JTextField();
		txtDepartment.setEditable(false);
		txtDepartment.setBounds(88, 119, 181, 21);
		add(txtDepartment);
		txtDepartment.setHorizontalAlignment(JTextField.LEADING);

		JLabel lblQQ = new JLabel("QQ:");
		lblQQ.setBounds(279, 122, 44, 15);
		add(lblQQ);

		txtQQ = new JTextField();
		txtQQ.setEditable(false);
		txtQQ.setBounds(333, 119, 131, 21);
		add(txtQQ);
		txtQQ.setHorizontalAlignment(JTextField.LEFT);

		JLabel lblPhone = new JLabel("\u624B\u673A\uFF1A");
		lblPhone.setBounds(10, 162, 44, 15);
		add(lblPhone);

		txtPhone = new JTextField();
		txtPhone.setEditable(false);
		txtPhone.setBounds(88, 159, 181, 21);
		add(txtPhone);
		txtPhone.setHorizontalAlignment(JTextField.LEFT);

		panHeadImage = new JPanel();
		panHeadImage.setOpaque(false);
		panHeadImage.setBorder(new TitledBorder(null, "\u5934\u50CF",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panHeadImage.setBounds(468, 29, 179, 205);
		panHeadImage.setLayout(new BorderLayout());
		add(panHeadImage);

		JLabel lblBirthday = new JLabel("\u751F\u65E5\uFF1A");
		lblBirthday.setBounds(279, 162, 44, 15);
		add(lblBirthday);

		txtBirthday = new JTextField();
		txtBirthday.setEditable(false);
		txtBirthday.setBounds(333, 159, 131, 21);
		add(txtBirthday);
		txtBirthday.setHorizontalAlignment(JTextField.LEFT);

		JLabel lblAddress = new JLabel("\u4F4F\u5740\uFF1A");
		lblAddress.setBounds(10, 203, 44, 15);
		add(lblAddress);

		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setBounds(85, 200, 379, 21);
		add(txtAddress);
		txtAddress.setHorizontalAlignment(JTextField.LEFT);

		JLabel lblSign = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
		lblSign.setBounds(10, 245, 70, 15);
		add(lblSign);

		txtSign = new JTextArea();
		txtSign.setEditable(false);
		txtSign.setBounds(85, 241, 379, 140);
		txtSign.setLineWrap(true);
		add(txtSign);

		btnUpdate = new JButton("\u4FEE\u6539");
		btnUpdate.addActionListener(control);
		btnUpdate.setBounds(148, 413, 100, 23);
		btnUpdate.setContentAreaFilled(false);
		add(btnUpdate);

		btnOK = new JButton("\u786E\u5B9A");
		btnOK.setBounds(296, 413, 100, 23);
		btnOK.setEnabled(false);
		btnOK.addActionListener(control);
		btnOK.setContentAreaFilled(false);
		add(btnOK);

		btnChangeHeadImage = new JButton("����ͷ��");
		btnChangeHeadImage.setBounds(519, 241, 93, 23);
		btnChangeHeadImage.setEnabled(false);
		btnChangeHeadImage.addActionListener(control);
		btnChangeHeadImage.setContentAreaFilled(false);
		add(btnChangeHeadImage);

		// ��ʼ���ҵ���Ϣ���
		initMyInformation(FrmManagerControl.onlineId);

	}
	
	//��ʼ���ҵ���Ϣ���
	public void initMyInformation(String id){
		
		//����UserDao����
		ManagerDaoImpl dao = new ManagerDaoImpl();
		//��ѯ����ý����
		ResultSet rs = dao.selectById(id);
		//��������
		User  user  =  dao.getUser(rs);
		//�ر����ݿ⣬�ͷ���Դ
		dao.closeAll();
		if(user!=null){
			txtId.setText(user.getId().trim());
			txtName.setText(user.getName().trim());
			txtAddress.setText(user.getAdress().trim());
			txtBirthday.setText(user.getBirthday().trim());
			txtDepartment.setText(user.getDepartment().trim());
			txtGrade.setText(user.getGrade().trim());
			txtPhone.setText(user.getPhone().trim());
			txtQQ.setText(user.getQQ().trim());
			txtSex.setText(user.getSex().trim());
			txtSign.setText(user.getSign().trim());
			
			String head = user.getHeadImage();
			String path = "./res/headImage/"+head;
			createPanHeadImage(path);
			
			updateUI();
		}
		
	}
	
	/**
     * �õ������ı��������
     * @param valueName
     * @return
     */
    public String getParameter(String valueName){
    	if(valueName.equalsIgnoreCase("name"))
    		return txtName.getText();
    	else if(valueName.equalsIgnoreCase("sex"))
		    return txtSex.getText();
    	else if(valueName.equalsIgnoreCase("id"))
		    return txtId.getText();
    	else if(valueName.equalsIgnoreCase("grade"))
		    return txtGrade.getText();
    	else if(valueName.equalsIgnoreCase("department"))		
		    return txtDepartment.getText().trim();
		else if(valueName.equalsIgnoreCase("QQ"))
		    return txtQQ.getText();
		else if(valueName.equalsIgnoreCase("phone"))
		    return txtPhone.getText();
		else if(valueName.equalsIgnoreCase("address"))
		    return txtAddress.getText();
		else if(valueName.equalsIgnoreCase("birthday"))
		    return txtBirthday.getText();
		else if(valueName.equalsIgnoreCase("sign"))
		    return txtSign.getText();
		else
			return null ;
    }


	public JButton getBtnUpdate() {
		return btnUpdate;
	}

	public JButton getBtnOK() {
		return btnOK;
	}
	
	/**
	 * �����ı����Ƿ���Ա༭
	 * @param yn
	 */
	public void setAllTextField_Editable(boolean yn){
		
		txtName.setEditable(yn);
		txtSex.setEditable(yn);
		txtId.setEditable(yn);
		txtGrade.setEditable(yn);
		txtDepartment.setEditable(yn);
		txtQQ.setEditable(yn);
		txtPhone.setEditable(yn);
		txtAddress.setEditable(yn);
		txtBirthday.setEditable(yn);
		txtSign.setEditable(yn) ;
		
	}
	
    //���ð�ť�Ƿ���Ա༭
	public void setBtnUpdate_Enabled(boolean yn) {	
		btnUpdate.setEnabled(yn);
	}

	public void setBtnOK_Enabled(boolean yn) {
		btnOK.setEnabled(yn);
	}

	public void setBtnChangeHeadImage_Enabled(boolean yn) {
		btnChangeHeadImage.setEnabled(yn);;
	}
	
	//����ͷ��
	public void createPanHeadImage(String path) {
		//�Ƴ�ԭ����ͷ��	
		panHeadImage.removeAll();
		//�����µ�ͷ��
		JPanel head = new HeadImage(path);
		head.setOpaque(false);
		panHeadImage.add(head);	
			
	}

	/**
	 * �޸����ݳɹ��ķ�Ӧ
	 * @param isUpdateSuccessful
	 */
	public void UpdateResponse(boolean isUpdateSuccessful) {	
		if(isUpdateSuccessful){		
			JOptionPane.showMessageDialog(this, "��Ϣ�޸ĳɹ�");
			setAllTextField_Editable(false);
		    btnOK.setEnabled(false);
            btnChangeHeadImage.setEnabled(false);
		    btnUpdate.setEnabled(true);
            updateUI();	
		}else
			JOptionPane.showMessageDialog(this, "��Ϣ�޸�ʧ��");
		
	}
	
	/**
	 * �޸�ͷ��
	 * @param filePath
	 */
	public void ChangeHeadImageResponse(String filePath){
		
		//����ͷ����ӵ������
    	createPanHeadImage(filePath);
    	updateUI();
	}

	/**
	 * ������޸İ�ť�ķ�Ӧ
	 */
	public void response() {
		//���ı�����Ϊ�ɱ༭
    	setAllTextField_Editable(true);
    	//�����°�ť��Ϊ���ɱ༭
    	btnUpdate.setEnabled(false);
    	//������ͷ��ť��ȷ����ť��Ϊ��Ϊ���ɱ༭
    	btnChangeHeadImage.setEnabled(true);	
    	btnOK.setEnabled(true);
    	//����UI
    	updateUI();
		
	}
}
