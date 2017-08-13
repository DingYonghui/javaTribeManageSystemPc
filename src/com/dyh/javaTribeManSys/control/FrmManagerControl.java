package com.dyh.javaTribeManSys.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import com.dyh.javaTribeManSys.impl.ManagerImpl;
import com.dyh.javaTribeManSys.pojo.User;
import com.dyh.javaTribeManSys.ui.FrmLogin;
import com.dyh.javaTribeManSys.ui.FrmManager;
import com.dyh.javaTribeManSys.ui.FrmStudent;

/**
 * ����Ա����������࣬�����Ʋ�
 * 
 * ���Ʋ������ǣ�   
 * 1�������ͼ�������   
 * 2������ҵ���Ĵ�����
 * 3��֪ͨ��ͼ����ʾ����������
 * 
 * MVC���ģʽ:
 * V: view ��ͼ ���е�uiҳ�� �����ܣ���ʾ���� ��ȡ����
 * C: control: ���Ʋ�  ���ܣ���ȡ��ͼ�������,���ҵ���ҵ���߼���
 * M: Model: ��������¼�� ��ҵ���߼���ʵ��
 * 
 */
 
public class FrmManagerControl implements ActionListener,ItemListener{

	//��½���棨��ͼ�㣩
	FrmLogin frmLogin ;

	//�����������棨��ͼ�㣩
	public static FrmManager frmManager ;  	
	//��ͨ��Ա������
	private FrmStudent frmStudent ;
	
	//����ҵ���߼������ҵ���֮һ��
	ManagerImpl managerImpl = new ManagerImpl();

	//��ǰ��¼��id
	public static String onlineId;
	//��¼��ǰ��½�û�ͷ����ļ�·��
	public static String onlinePATH ;

	/**
	 * �޲����Ĺ��췽��
	 */
	public FrmManagerControl(){
		
	}
		
	public FrmManagerControl(FrmLogin frmLogin) {
		this.frmLogin = frmLogin ;
	}
	
	/**
	 * ����ActionListener�з���
	 */
	public void actionPerformed(ActionEvent e) {		
		
		String request = e.getActionCommand() ;
		
		if(request.equals("��½")){
			doLogin();					
		}	
		/**
		 * frmManager��������   �˵���   ��   ��������е�
		 * "��Ϣ��ѯ"��"��Ϣ���"��"��Ϣɾ��"��"��Ϣ�޸�"��"�ҵ���Ϣ"��"����"
		 * 6����ť�Ĵ������
		 */
		else if(request.equals("ע��(L)")){		
			doLogout();		
		}
		else if(request.equals("�˳�(X)")){
			System.exit(0);
		}
		else if(request.equals("����")){
			JOptionPane.showMessageDialog(frmManager,"צ�۲�����Ϣ����ϵͳ");
		}
		else if(request.equals("��Ϣ��ѯ")||request.equals("����(F)")){									
			frmManager.showPanFind();
		}
		else if(request.equals("��Ϣ���")||request.equals("���(A)")){           			
			frmManager.showPanAdd();
		}
		else if(request.equals("��Ϣɾ��")||request.equals("ɾ��(D)")){
			frmManager.showPanDelete();
		}
        else if(request.equals("��Ϣ�޸�")||request.equals("�޸�(U)")){            					
			frmManager.showPanUpdate();
		}
        else if(request.equals("�ҵ���Ϣ")){
			frmManager.showPanMyInfo();
			frmManager.panMyInfo.initMyInformation(onlineId);
		}
        else if(request.equals("����")){
        	frmManager.showPanInstall();
        	frmManager.panInstall.clearPassword();
        }
		
        /**
         * panFind����Ϣ��ѯ������С����ҡ���ť�Ĵ������
         */
        else if(request.equals("����")){      	
        	doFindUser();    	
        }	
		
        /**
         * panAdd����Ϣ��ӡ�����С�ȷ����ӡ����������һ�����������ͷ��3����ť�Ĵ������
         */
        else if(request.equals("ȷ�����")){   	
        	doAddUser();
        }	
        else if(request.equals("�����һ��")){
        	frmManager.panAdd.addAgainResponse();
        }
        else if(request.equals("���ͷ��")){       	
        	String path = managerImpl.getHeadImagePath();      	
        	frmManager.panAdd.addHeadImageResponse(path);    	
        }
				
        /**
         * panUpdate����Ϣ�޸ġ�����С�ȷ���޸ġ���ť�Ĵ������
         */
        else if(request.equals("ȷ���޸�")){        	
        	//���Ҫ�޸ĵ�id
    		String id = frmManager.panUpdate.getParameter("id"); 		  		
    		boolean isUserExist = managerImpl.clickBtnUpdate(id);        	
    		frmManager.panUpdate.BtnUpdateResponse(isUserExist,id, frmManager, frmManager.panMyInfo);
        }	
		
        /**
         * panDelete����Ϣɾ��������� ��ȷ��ɾ������ť�Ĵ������
         */    
        else if(request.equals("ȷ��ɾ��")){     	
        	doDeleteUser();
        }
		
		/**
	     * panMyInfo���ҵ���Ϣ������С��޸ġ�����ȷ�ϡ���������ͷ��3����ť�Ĵ������
	     */
        else if(e.getSource() == frmManager.panMyInfo.getBtnUpdate()){       	
        	frmManager.panMyInfo.response();
        }
        else if(e.getSource() == frmManager.panMyInfo.getBtnOK()){
        	doUpdateUser();
        }
        else if(request.equals("����ͷ��")){
        	String path = managerImpl.getHeadImagePath();       			       			    			
        	frmManager.panMyInfo.ChangeHeadImageResponse(path);      	
        }
	
        /**
         * panInstall��������С����á���ȷ�ϡ������Ĺ�����Ȩ�ޡ��͡����ҹ���Ա��4����ť�Ĵ������
         */	
        else if(request.equals("����")){
        	frmManager.panInstall.clearPassword();
        }	
        else if(e.getSource() == frmManager.panInstall.getBtnOk()){    		
        	doUpdatePassword();
        }	
        else if(request.equals("�޸Ĺ���ԱȨ��")){  	
        	String id = JOptionPane.showInputDialog(frmManager,"������Ҫ�޸ĵĹ���Աѧ��");	
        	managerImpl.updateManager(id);      	
        }
        else if(request.equals("��ѯ���й���Ա")){
        	managerImpl.findAllManager();
        }
			   
		/**
		 * ����ʽ�˵��Ĳ˵�����޸ġ�ɾ������ϸ��Ϣ
		 */ 
        else if(request.equals("�޸�")||request.equals("��ϸ��Ϣ")){
        	String id = managerImpl.getSelectedUserId();
            frmManager.showPanMyInfo();
            frmManager.panMyInfo.initMyInformation(id);
        }
        else if(request.equals("ɾ��")){
        	Object[][] datas = managerImpl.deleteUsers();
        	frmManager.panFind.createNewTable(datas);
        }
				
	}
	
	/**
	 * �޸�����
	 */
	private void doUpdatePassword() {
		String oldPwd = frmManager.panInstall.getOldPwd();
 		String newPwd = frmManager.panInstall.getNewPwd();
 		String reNewPwd = frmManager.panInstall.getReNewPwd();
 		   
 		//��������Ƿ�Ϸ�
 		if(oldPwd.equals("")){
			JOptionPane.showMessageDialog(frmManager, "ԭ���벻��Ϊ��","��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}		
		if(oldPwd.length()<9||oldPwd.length()>15){
			JOptionPane.showMessageDialog(frmManager, "ԭ���볤�ȷ�Χ��9~15֮��","��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		String dbPassword = managerImpl.findUserCorrectly(onlineId).get(0).getPassword().trim();
		if(!oldPwd.equals(dbPassword)){
			JOptionPane.showMessageDialog(frmManager, "ԭ�������","��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(newPwd.equals("")){
			JOptionPane.showMessageDialog(frmManager, "�����벻��Ϊ��","��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(newPwd.length()<9||newPwd.length()>15){
			JOptionPane.showMessageDialog(frmManager, "�����볤�ȷ�Χ��9~15֮��","��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		if(!reNewPwd.equals(newPwd)){
			JOptionPane.showMessageDialog(frmManager, "ȷ����������������ͬ","��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
 		
 		
    	boolean isUpdatePwordSuccessful = managerImpl.updatePassword(oldPwd, newPwd, reNewPwd);
    	
    	frmManager.panInstall.updatePasswordResponse(isUpdatePwordSuccessful);
		
	}

	/**
	 * ����ItemListener�з���
	 * ��Ϣ��ѯ��ģ����ѯ�����������б����¼�����
	 */
	public void itemStateChanged(ItemEvent e) {				
		
		//���ҳ������
		String grade = frmManager.panFind.getGrade();
		String department = frmManager.panFind.getDepartment();
		String sex = frmManager.panFind.getSex();
			
		ManagerImpl managerImpl = new ManagerImpl();

		List<User> users = managerImpl.findUserdoByAddition(grade, department, sex);
		Object[][] datas = managerImpl.getTableDatas(users);
				
		frmManager.panFind.findResponse(datas);				
	}
	

	/**
	 * ��½
	 */
	private void doLogin() {
		// ��1��e.getActionCommand().equals("��½")--->��õ�½����
		// ��2���õ�ͼ�ν��棨��ʾ�㣩�����ݣ�����ѧ�š�����͹���ԱȨ��
		String id = frmLogin.getParameter("id");
		String password = frmLogin.getParameter("password");
		boolean isChooseManager = frmLogin.isManager();

		// ���id�Ƿ�Ϸ�
		if (id.equals("")) {
			JOptionPane.showMessageDialog(frmLogin, "ѧ�Ų���Ϊ��", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;// ����Ĵ��벻������
		}
		if (!verifyId(id)) {
			JOptionPane.showMessageDialog(frmLogin, "ѧ��������9λ����", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// ��������Ƿ�Ϸ�
		if (password.equals("")) {
			JOptionPane.showMessageDialog(frmLogin, "���벻��Ϊ��", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (password.length() < 9 || password.length() > 15) {
			JOptionPane.showMessageDialog(frmLogin, "���볤�ȷ�Χ��9~15֮��", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// ��3������ҵ�����ࣨҵ��㣩�ĵ�½����
		boolean isLoginSuccessful;
		// ����Ա��½
		if (isChooseManager) {
			isLoginSuccessful = managerImpl.managerLogin(id, password);
			// ��4��ͼ�ν��棨��ʾ�㣩 ���� ҵ���������صĽ�� �����������
			if (isLoginSuccessful) {
				if (frmManager == null) {
					frmManager = new FrmManager();
					onlineId = id;
				}
				frmManager.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(frmLogin, "��������ȷ�ĵ�¼�������� !");
			}

		}
		// ��ͨ��Ա��½
		else {
			isLoginSuccessful = managerImpl.userLogin(id, password);
			if (isLoginSuccessful) {
				if (frmStudent == null) {
					frmStudent = new FrmStudent();
					onlineId = id;
				}
				frmStudent.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(frmLogin, "��������ȷ�ĵ�¼�������� !");
			}
		}

		// ��4��ͼ�ν��棨��ʾ�㣩login����ķ�Ӧ����
		frmLogin.loginResponse(isLoginSuccessful);

	}

	/**
	 * ע���û�
	 */
	private void doLogout() {
		if(frmLogin == null){
			frmLogin = new FrmLogin();
		}
		frmLogin.setVisible(true);	
		frmManager.dispose();
	}
	
	/**
	 * �����û�
	 */
	private void doFindUser() {
		//��1��e.getSource() == panFind.getBtnFind()--->��������
    	//��2�������ͼ���еĲ�������
		String findCondition = frmManager.panFind.getFindCondition() ; 
		String value = frmManager.panFind.getId_OR_Name();      			
		//��3������ҵ���Ĵ�������ģ������
		List<User> users = managerImpl.findUserProbably(findCondition, value);			
		Object[][] datas = managerImpl.getTableDatas(users);	
		//��4����ͼ����ݴ�����������Ӧ����
		frmManager.panFind.findResponse(datas);
		
	}
	
	/**
	 * �޸��û���Ϣ
	 */
	private void doUpdateUser() {
		User user = new User();   		
    	//����û��������Ϣ   
    	user.setName(frmManager.panMyInfo.getParameter("name")); 
		user.setSex(frmManager.panMyInfo.getParameter("sex"));  
		user.setId(frmManager.panMyInfo.getParameter("id"));  
		user.setGrade(frmManager.panMyInfo.getParameter("grade")); 
		user.setDepartment(frmManager.panMyInfo.getParameter("department"));  
		user.setQQ(frmManager.panMyInfo.getParameter("qq")); 
		user.setPhone(frmManager.panMyInfo.getParameter("phone"));  
		user.setAdress(frmManager.panMyInfo.getParameter("address"));  
		user.setBirthday(frmManager.panMyInfo.getParameter("birthday"));  
		user.setSign(frmManager.panMyInfo.getParameter("sign")); 
		user.setHeadImage(frmManager.panMyInfo.getParameter("id")+".jpg"); 
		
		boolean isUpdateSuccessful = managerImpl.updateUser(user);
		
		frmManager.panMyInfo.UpdateResponse(isUpdateSuccessful);
		
	}

	/**
	 * ����û�
	 */
	private void doAddUser() {
		// ��1��e.getSource()==panAdd.getBtnOK()--->�������ȷ�����
		// ��2���õ���ͼ������ݲ���װ��ʵ����User��
		String id = frmManager.panAdd.getParameter("id");
		// ���id�Ƿ�Ϸ�
		if (id.equals("")) {
			JOptionPane.showMessageDialog(frmLogin, "ѧ�Ų���Ϊ��", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;// ����Ĵ��벻������
		}
		if (!verifyId(id)) {
			JOptionPane.showMessageDialog(frmLogin, "ѧ��������9λ����", "��ʾ",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// ����û��Ƿ���ע��
		if (!managerImpl.findUserCorrectly(id).isEmpty()) {
			JOptionPane.showMessageDialog(null, "�û���ע�ᣬ�����ѧ��");
			return;
		}
		// ���name�Ƿ�Ϸ�
		String name = frmManager.panAdd.getParameter("name");
		if (name.equals("")) {
			JOptionPane.showMessageDialog(frmManager, "��������Ϊ��");
			return;
		}
		if (name.length() > 10) {
			JOptionPane.showMessageDialog(frmManager, "�������ܳ���10���ַ�");
			return;
		}
		User user = new User();
		user.setId(id);
		user.setName(name);
		user.setSex(frmManager.panAdd.getParameter("sex"));
		user.setGrade(frmManager.panAdd.getParameter("grade"));
		user.setDepartment(frmManager.panAdd.getParameter("department"));
		user.setQQ(frmManager.panAdd.getParameter("qq"));
		user.setPhone(frmManager.panAdd.getParameter("phone"));
		user.setAdress(frmManager.panAdd.getParameter("address"));
		user.setBirthday(frmManager.panAdd.getParameter("birthday"));
		user.setSign(frmManager.panAdd.getParameter("sign"));
		user.setHeadImage(frmManager.panAdd.getParameter("id") + ".jpg");
		// �Ƿ������ԱȨ��
		int i = JOptionPane.showConfirmDialog(null, "�Ƿ񽫸��û���Ϊ����Ա��", "����Ա����",
				JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION)
			user.setIsManager("��");
		else
			user.setIsManager("��");

		// ��3������ҵ���Ĵ������������ش�����
		boolean isAddSuccessful = managerImpl.addUser(user);
		// ��4��֪ͨ����ͼ�㣬��ͼ����ݴ�����������Ӧ
		frmManager.panAdd.addResponse(isAddSuccessful);

	}
	
	/**
	 * ɾ���û�
	 */
	private void doDeleteUser() {
		String id = frmManager.panDelete.getParameter("id");             	
    	boolean isDeleteSuccessful = managerImpl.deleteUser(id);        	
    	frmManager.panDelete.deleteResponse(isDeleteSuccessful);	
	}

	/**
	 * ������ʽ�� ���ƥ��ѧ���Ƿ�Ϊ����
	 * 
	 * @param input
	 * @return
	 */
    public boolean verifyId(String input){
    	boolean ck = false ;
		String regex = "\\b\\d{9}\\b";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if(matcher.matches()){
			ck = true ; 
		}
		return ck ;
    }
   
}
