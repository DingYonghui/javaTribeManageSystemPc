package com.dyh.javaTribeManSys.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.dyh.javaTribeManSys.control.FrmManagerControl;

/**
 * ��Ϣ���������
 */
public class PanFind extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1509353927720475616L;
	
	private JTextField txtFindCondition;
	public static JTable tableFindResult;
	private JButton btnFind ;
	
	private JComboBox cbBoxFindCondition ;
	
	private JComboBox cbBoxGrade ;
	private JComboBox cbBoxDepartment ;
	private JComboBox cbBoxSex ;
	
	private JPopupMenu popupMenu ;
	
	private JScrollPane jspan ;
	/**
	 * Create the panel.
	 */
	public PanFind(FrmManagerControl control) {
		
		setBorder(new TitledBorder(null, "\u4FE1\u606F\u67E5\u8BE2", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setOpaque(false);
		setLayout(null);
		
		JPanel panAccurateFind = new JPanel();
		panAccurateFind.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u666E\u901A\u67E5\u627E", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panAccurateFind.setToolTipText("");
		panAccurateFind.setBounds(10, 31, 626, 63);
		panAccurateFind.setOpaque(false);
		add(panAccurateFind);
		panAccurateFind.setLayout(null);
		
		JLabel lblFindCondition = new JLabel("\u67E5\u627E\u6761\u4EF6\uFF1A");
		lblFindCondition.setBounds(10, 25, 75, 28);
		panAccurateFind.add(lblFindCondition);
		
		//��������
		cbBoxFindCondition = new JComboBox();
		cbBoxFindCondition.setModel(new DefaultComboBoxModel(
				new String[] {"\u5B66\u53F7", "\u59D3\u540D"})
		);	
		//cbBoxFindCondition.addActionListener(control);
		cbBoxFindCondition.setSelectedIndex(0);
		cbBoxFindCondition.setBounds(95, 25, 90, 28);
		panAccurateFind.add(cbBoxFindCondition);	
		cbBoxFindCondition.setBackground(new Color(85,255,205));  //���ô�����ı���ɫ��
		cbBoxFindCondition.setForeground(Color.BLACK);  //���ô������ǰ��ɫ��		   
		cbBoxFindCondition.setFont(new Font("����",Font.BOLD,12));  //��������
		
		txtFindCondition = new JTextField();
		txtFindCondition.setBounds(195, 26, 170, 28);
		panAccurateFind.add(txtFindCondition);
		txtFindCondition.setColumns(10);
		
		btnFind = new JButton("����");
		btnFind.setMnemonic('F');
		btnFind.setBounds(413, 25, 93, 28);
		btnFind.addActionListener(control);
		btnFind.setContentAreaFilled(false);
		panAccurateFind.add(btnFind);
		
		JPanel panIndistinctFind = new JPanel();
		panIndistinctFind.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u6A21\u7CCA\u67E5\u627E", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panIndistinctFind.setBounds(10, 104, 626, 63);
		panIndistinctFind.setOpaque(false);
		add(panIndistinctFind);
		panIndistinctFind.setLayout(null);
		
		
		
		cbBoxGrade = new JComboBox();
		cbBoxGrade.setModel(new DefaultComboBoxModel(new String[] {"\u5927\u4E00", "\u5927\u4E8C", "\u5927\u4E09", "\u5927\u56DB"}));
		cbBoxGrade.setBounds(64, 26, 58, 27);
		panIndistinctFind.add(cbBoxGrade);
		cbBoxGrade.addItemListener(control);
		cbBoxGrade.setBackground(new Color(85,255,205));  //���ô�����ı���ɫ��
		cbBoxGrade.setForeground(Color.BLACK);  //���ô������ǰ��ɫ��		   
		cbBoxGrade.setFont(new Font("����",Font.BOLD,12));  //��������
		
		JLabel lblGrade = new JLabel("\u5E74\u7EA7\uFF1A");
		lblGrade.setBounds(10, 26, 44, 27);
		panIndistinctFind.add(lblGrade);
		
		JLabel lblDepartment = new JLabel("\u7CFB\u522B\uFF1A");
		lblDepartment.setBounds(142, 26, 44, 27);
		panIndistinctFind.add(lblDepartment);
		
		cbBoxDepartment = new JComboBox();
		cbBoxDepartment.setModel(new DefaultComboBoxModel(new String[] {"\u4E92\u8054\u7F51\u91D1\u878D\u4E0E\u4FE1\u606F\u5DE5\u7A0B\u7CFB", "\u91D1\u878D\u7CFB", "\u4F1A\u8BA1\u7CFB", "\u6CD5\u5F8B\u7CFB", "\u5916\u8BED\u7CFB"}));
		cbBoxDepartment.setBounds(196, 26, 170, 27);
		panIndistinctFind.add(cbBoxDepartment);
		cbBoxDepartment.addItemListener(control);
		cbBoxDepartment.setBackground(new Color(85,255,205));  //���ô�����ı���ɫ��
		cbBoxDepartment.setForeground(Color.BLACK);  //���ô������ǰ��ɫ��		   
		cbBoxDepartment.setFont(new Font("����",Font.BOLD,12));  //��������
		
		JLabel lblSex = new JLabel("\u6027\u522B\uFF1A");
		lblSex.setBounds(392, 26, 44, 27);
		panIndistinctFind.add(lblSex);
		
		cbBoxSex = new JComboBox();
		cbBoxSex.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973"}));
		cbBoxSex.setBounds(446, 26, 58, 27);
		panIndistinctFind.add(cbBoxSex);
		cbBoxSex.addItemListener(control);
		cbBoxSex.setBackground(new Color(85,255,205));  //���ô�����ı���ɫ��
		cbBoxSex.setForeground(Color.BLACK);  //���ô������ǰ��ɫ��		   
		cbBoxSex.setFont(new Font("����",Font.BOLD,12));  //��������
		
		JPanel panFindResult = new JPanel();
		panFindResult.setOpaque(false);
		panFindResult.setBounds(10, 177, 626, 286);
		add(panFindResult);
		panFindResult.setLayout(new BorderLayout(0, 0));
		
		jspan = new JScrollPane();
		jspan.setOpaque(false);
		//�ؼ�
		jspan.getViewport().setOpaque(false);
		jspan.setBorder(new TitledBorder(null, "\u67E5\u8BE2\u7ED3\u679C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panFindResult.add(jspan, BorderLayout.CENTER);
		
		
		
			
		tableFindResult = new JTable();
		tableFindResult.setModel(new DefaultTableModel(
			new Object[][] {
					
			},
			new String[] {
					"ѧ��","����","�Ա�","�꼶","QQ","�绰"
			}
		));
		tableFindResult.setOpaque(false);
		jspan.setViewportView(tableFindResult);

		
		popupMenu = new JPopupMenu();
		addPopup(jspan, popupMenu);
			
		JMenuItem mmtmUpdate = new JMenuItem("\u4FEE\u6539");
		mmtmUpdate.addActionListener(control);
		popupMenu.add(mmtmUpdate);
		
		JMenuItem mntmDelete = new JMenuItem("\u5220\u9664");
		mntmDelete.addActionListener(control);
		popupMenu.add(mntmDelete);
		
		JMenuItem menuDetailedInformation = new JMenuItem("\u8BE6\u7EC6\u4FE1\u606F");
		menuDetailedInformation.addActionListener(control);
		//menuDetailedInformation.setEnabled(false);
		popupMenu.add(menuDetailedInformation);
	}
	
	/**
	 * ȡ�������б���б�ѡ���������id��������
	 * @return
	 */
	public String getFindCondition(){
		
		String str = cbBoxFindCondition.getSelectedItem().toString();
		
		if(str.equals("ѧ��"))
			return "id" ;
		else if(str.equals("����")) 
			return "name" ;
		else
			return null;
		
	}
	
	/**
	 * �õ��ı����е�id����ѧ�ţ��������֣�����������
	 * @return
	 */
	public String getId_OR_Name() {		
		return txtFindCondition.getText();
	}
	
	/**
	 * ��������б���б�ѡ����ַ����꼶��ϵ���Ա�
	 * @return  grade
	 */
	public String getGrade(){
		return cbBoxGrade.getSelectedItem().toString();		
	}
	
	public String getDepartment(){
		return cbBoxDepartment.getSelectedItem().toString();			
	}
	
	public String getSex(){		
		return cbBoxSex.getSelectedItem().toString();			
	}
	
	/**
	 * �ؽ����
	 * @param datas
	 */
	public void createNewTable(Object[][] datas){
		
		if(datas!=null){
			String[] titles = {"ѧ��","����","�Ա�","�꼶","QQ","�绰"};
			tableFindResult = new JTable(datas,titles);
			
			tableFindResult.setOpaque(false);
			tableFindResult.setDragEnabled(true) ;
			addPopup(tableFindResult, popupMenu);
			jspan.setViewportView(tableFindResult);
			
		}
		
	}
	
	

	/**
	 * ���Ҳ�����ķ�Ӧ
	 * @param datas
	 */
	public void findResponse(Object[][] datas) {
		
		if(datas == null){
			JOptionPane.showMessageDialog(this, "�Ҳ����û�", 
					"��ʾ", JOptionPane.INFORMATION_MESSAGE );
			return ;
		}
		
		createNewTable(datas);
		txtFindCondition.setText("");
		updateUI();
		
	}
		
	
	private void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
				
				System.out.println(" ���ذ�����ǰ���༭�ĵ�Ԫ���������"+tableFindResult.getEditingColumn());	
				System.out.println(" ����ѡ������"+tableFindResult.getSelectedRowCount() );
		         		          
			}
		});
	}
}
