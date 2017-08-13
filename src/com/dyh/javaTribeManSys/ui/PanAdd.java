package com.dyh.javaTribeManSys.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.dyh.javaTribeManSys.control.FrmManagerControl;
/**
 * ��Ϣ��������
 */
public class PanAdd extends JPanel {

	
	private static final long serialVersionUID = 4365509612638202715L;
	
	private JTextField txtName;
//	private JComboBox cbSex;
	
	private JRadioButton rdbtnMale ;
	private JRadioButton rdbtnFemale ;
	private ButtonGroup bgSex = new ButtonGroup();
	
	public static JTextField txtId;
	private JComboBox cbGrade;
	private JComboBox cbDepartment;
	private JTextField txtQQ;
	private JTextField txtPhone;
	private JTextField txtAddress;
	private JTextField txtBirthday;
	private JTextArea txtSign ;
	
	private JButton btnSureAdd ;
	private JButton btnAddAgain ;
	private JButton btnAddHeadImage ;
 
	//ͷ�����
    private JPanel panHeadImage ;
  			
	
	/**
	 * Create the panel.
	 */
	public PanAdd( final FrmManagerControl control ) {
	
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "��Ϣ���", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));	
		setOpaque(false);
		setLayout(null);
		
		JLabel lblName = new JLabel("\u59D3\u540D\uFF1A");
		lblName.setBounds(111, 45, 44, 21);
		add(lblName);
		
		txtName = new JTextField();
		txtName.setEditable(true);
		txtName.setBounds(193, 45, 247, 21);
		add(txtName);
		txtName.setColumns(10);
		
		JLabel lblSex = new JLabel("\u6027\u522B\uFF1A");
		lblSex.setBounds(111, 76, 44, 21);
		add(lblSex);
		
		
		rdbtnMale = new JRadioButton("��");
		rdbtnMale.setSelected(true);
		rdbtnMale.setBounds(193, 76, 40, 21);
		rdbtnMale.setContentAreaFilled(false);
		add(rdbtnMale);
		
		rdbtnFemale = new JRadioButton("Ů");
		rdbtnFemale.setBounds(233, 76, 40, 21);
		rdbtnFemale.setContentAreaFilled(false);
		add(rdbtnFemale);
						
		bgSex = new ButtonGroup();
		bgSex.add(rdbtnFemale);  
		bgSex.add(rdbtnMale);
		
		
//		cbSex = new JComboBox();
//		cbSex.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973"}));
//		cbSex.setEditable(true);
//		cbSex.setBounds(193, 76, 64, 21);
//		add(cbSex);
	
		
		JLabel lblId = new JLabel("\u5B66\u53F7\uFF1A");
		lblId.setBounds(111, 14, 44, 21);
		add(lblId);
		
		txtId = new JTextField();
		txtId.setEditable(true);
		txtId.setBounds(193, 14, 247, 21);
		add(txtId);
		txtId.setColumns(10);
		
		JLabel lblGrade = new JLabel("\u5E74\u7EA7\uFF1A");
		lblGrade.setBounds(111, 107, 44, 21);
		add(lblGrade);
		
		cbGrade = new JComboBox();
		cbGrade.setModel(new DefaultComboBoxModel(new String[] {"\u5927\u4E00", "\u5927\u4E8C", "\u5927\u4E09", "\u5927\u56DB"}));
		cbGrade.setEditable(true);
		cbGrade.setBounds(193, 107, 64, 21);
		add(cbGrade);

		
		JLabel lblDepartment = new JLabel("\u7CFB\u522B\uFF1A");
		lblDepartment.setBounds(111, 138, 44, 21);
		add(lblDepartment);
		
		cbDepartment = new JComboBox();
		cbDepartment.setModel(new DefaultComboBoxModel(new String[] {"\u4E92\u8054\u7F51\u91D1\u878D\u4E0E\u4FE1\u606F\u5DE5\u7A0B\u7CFB", "\u91D1\u878D\u7CFB", "\u4F1A\u8BA1\u7CFB", "\u5916\u8BED\u7CFB", "\u6CD5\u5F8B\u7CFB", "\u7ECF\u8D38\u7CFB", "\u5176\u4ED6"}));
		cbDepartment.setEditable(true);
		cbDepartment.setBounds(193, 138, 179, 21);
		add(cbDepartment);

		
		JLabel lblQQ = new JLabel("QQ:");
		lblQQ.setBounds(111, 169, 44, 21);
		add(lblQQ);
		
		txtQQ = new JTextField();
		txtQQ.setEditable(true);
		txtQQ.setBounds(193, 169, 247, 21);
		add(txtQQ);
		txtQQ.setColumns(10);
		
		JLabel lblPhone = new JLabel("\u624B\u673A\uFF1A");
		lblPhone.setBounds(111, 194, 44, 21);
		add(lblPhone);
		
		txtPhone = new JTextField();
		txtPhone.setEditable(true);
		txtPhone.setBounds(193, 200, 247, 21);
		add(txtPhone);
		txtPhone.setColumns(10);
		
		panHeadImage = new JPanel();
		panHeadImage.setOpaque(false);
		panHeadImage.setBorder(new TitledBorder(null, "\u5934\u50CF", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panHeadImage.setBounds(468, 29, 179, 205);
		panHeadImage.setLayout(new BorderLayout());
		add(panHeadImage);
				
		JLabel lblBirthday = new JLabel("\u751F\u65E5\uFF1A");
		lblBirthday.setBounds(111, 225, 44, 21);
		add(lblBirthday);
		
		txtBirthday = new JTextField();
		txtBirthday.setEditable(true);
		txtBirthday.setBounds(193, 231, 247, 21);
		add(txtBirthday);
		txtBirthday.setColumns(10);
		
		JLabel lblAddress = new JLabel("\u4F4F\u5740\uFF1A");
		lblAddress.setBounds(111, 262, 44, 21);
		add(lblAddress);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(true);
		txtAddress.setBounds(193, 262, 247, 21);
		add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblSign = new JLabel("\u4E2A\u6027\u7B7E\u540D\uFF1A");
		lblSign.setBounds(111, 294, 70, 21);
		add(lblSign);
		
		txtSign = new JTextArea();
		txtSign.setEditable(true);
		txtSign.setBounds(193, 293, 247, 110);
		txtSign.setLineWrap(true);
		add(txtSign);
				
		btnSureAdd = new JButton("ȷ�����");
		btnSureAdd.setBounds(280, 413, 100, 23);
		btnSureAdd.setEnabled(true);
		btnSureAdd.addActionListener(control);
		btnSureAdd.setContentAreaFilled(false);
		add(btnSureAdd);
		
		btnAddAgain = new JButton("�����һ��");
		btnAddAgain.setBounds(130, 413, 100, 23);
		btnAddAgain.setEnabled(true);
		btnAddAgain.addActionListener(control);
		btnAddAgain.setContentAreaFilled(false);
		add(btnAddAgain);
		
		btnAddHeadImage = new JButton("���ͷ��");
		btnAddHeadImage.setBounds(519, 241, 93, 23);
		btnAddHeadImage.addActionListener(control);
		btnAddHeadImage.setContentAreaFilled(false);
		add(btnAddHeadImage);

	}
		
	/**
     * �õ������ı��������
     * @param valueName
     * @return
     */
    public String getParameter(String valueName){
    	if(valueName.equalsIgnoreCase("name"))
    		return txtName.getText();
    	else if(valueName.equalsIgnoreCase("sex")){
    		
    		Enumeration<AbstractButton> e = bgSex.getElements();
    		JRadioButton rd;
    		String sex ;
    		//��ȡ�Ա�
   		    e = bgSex.getElements();
   		    while(true){
   		    	rd = (JRadioButton)e.nextElement();	
   	   			if(rd.isSelected()==true){
   	   				sex = rd.getText();
   	   				break;
   	   			}
   		    }		    
   			return sex;

    	}
    	else if(valueName.equalsIgnoreCase("id"))
		    return txtId.getText().trim();
    	else if(valueName.equalsIgnoreCase("grade"))
		    return cbGrade.getSelectedItem().toString();
    	else if(valueName.equalsIgnoreCase("department"))		
		    return cbDepartment.getSelectedItem().toString();
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
	/**
	 * ��������ı�
	 */
	public void clearAllTextField(){		
		txtName.setText("");
		rdbtnMale.setSelected(true);
		txtId.setText("");
		cbGrade.setSelectedIndex(0);
		cbDepartment.setSelectedIndex(0);
		txtQQ.setText("");
		txtPhone.setText("");
		txtAddress.setText("");
		txtBirthday.setText("");
		txtSign.setText("");		
	}
	/**
	 * �����ı����Ƿ���Ա༭
	 * @param yn
	 */
    public void setAllTextField_Editable(boolean yn){	
		txtName.setEditable(yn);
		txtId.setEditable(yn);
		cbGrade.setEnabled(yn);
		cbDepartment.setEnabled(yn);
		txtQQ.setEditable(yn);
		txtPhone.setEditable(yn);
		txtAddress.setEditable(yn);
		txtBirthday.setEditable(yn);
		txtSign.setEditable(yn) ;	
	}
    
    
    /**
     * ���ȷ����ť�Ķ���
     * @return
     */
	public JButton getBtnOK() {
		return btnSureAdd;
	}
	
	/**
	 * ����ͷ�����
	 * @param path
	 */
	public void createPanHeadImage(String path) {
		panHeadImage.removeAll();
		//ͷ�����
		JPanel head = new HeadImage(path);
		head.setOpaque(false);
		
		panHeadImage.add(head);		
	}
	
	/**
	 * ���ͷ�����
	 */
	public void clearImage() {
		panHeadImage.removeAll();
	}
	
	/**
	 * ��ӳɹ�ʱ�Ļ�Ӧ����
	 * 1���ɹ�ʱ�������Ի����֪��ӳɹ��������ı��򲻿ɱ༭��������Ӧ��ť�ɱ༭
	 * 2��ʧ��ʱ�������Ի����֪���ʧ�ܡ���������
	 * @param isAddSuccessful
	 */
	public void addResponse(boolean isAddSuccessful) {	
		if(isAddSuccessful){
			JOptionPane.showMessageDialog(this, "��Ϣ��ӳɹ�");
			
			setAllTextField_Editable(false);
		    btnAddHeadImage.setEnabled(false);
		    btnSureAdd.setEnabled(false);	    
		    btnAddAgain.setEnabled(true);
		    
		    updateUI();	  
		}else{		
			JOptionPane.showMessageDialog(this, "��Ϣ���ʧ��");			
		}		
	}

	/**
	 * �����һ����ť�Ļ�Ӧ����
	 * 1����������ı���ͼƬ����
	 * 2��ʹ�ı���ȷ����ť�����ͼƬ�İ�ť�ɱ༭
	 * 3���������һ����ť��Ϊ���ɱ༭
	 */
	public void addAgainResponse() {
		//��������ı���ͼƬ��
		clearAllTextField();
		clearImage();
		//ʹ�ı���ȷ����ť�����ͼƬ�İ�ť�ɱ༭
		setAllTextField_Editable(true);	   		
		btnSureAdd.setEnabled(true);	
		btnAddHeadImage.setEnabled(true);
		//�������һ����ť��Ϊ���ɱ༭
		btnAddAgain.setEnabled(false);
		//����UI
		updateUI();
		
	}

	/**
	 * ���ͷ��ť�Ļ�Ӧ����
	 * 1������ͷ��·������ͷ�����
	 * @param path
	 */
	public void addHeadImageResponse(String path) {
		if(path==null||path.equals(""))
    		return ;
    	//����ͷ����ӵ������
    	createPanHeadImage(path);
    	updateUI();	
	}
	
}



