package com.dyh.javaTribeManSys.impl;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.dyh.javaTribeManSys.control.FrmManagerControl;
import com.dyh.javaTribeManSys.dao.impl.ManagerDaoImpl;
import com.dyh.javaTribeManSys.pojo.User;
import com.dyh.javaTribeManSys.ui.PanAdd;
import com.dyh.javaTribeManSys.ui.PanFind;

/**
 * �����ߵ�ʵ���ࣨҵ�����ࣩ
 * ��ҵ��㣬�����ǣ���������
 * @author ding
 *
 */
public class ManagerImpl {
	

	//���ݿ�ʵ����
	ManagerDaoImpl dao = new ManagerDaoImpl();
	
	//��ȡͼƬ�ļ���·��
	private String path = null ;
			
	/**
	 * ģ������
	 * @return 
	 */
	public List<User> findUserProbably(String findCondition,String findValue) {		
					
		List<User> users = null ;	
		
		//��������Ϊid
		if(findCondition.equals("id")){		
			//��ò�ѯ���
			ResultSet rs =dao.selectLikeId(findValue);
			//��������
			users = dao.getAllUsers(rs);
			//�ر����ݿ�
			dao.closeAll();
		}
		//��������Ϊname
		else{
			ResultSet rs = dao.selectLikeName(findValue);
			users = dao.getAllUsers(rs);
		    dao.closeAll();
		}		
		
		return users;
	    		
	}
	
	/**
	 * ����ѯ�õ���User�����ٴη�װ�ɴ�������Object[][]����
	 * @param users
	 * @return
	 */
	public Object[][] getTableDatas(List<User> users){
		
		if(users == null ){
			return null ;
		}
		
		Object[][] datas = null;
		String[] titles = {"ѧ��","����","�Ա�","�꼶","QQ","�绰"};

		//������users�ĸ���
		int rowCount = users.size();
		datas = new Object[rowCount][titles.length];	
		//�û��б�
		User[] user = new User[rowCount] ;
		
	    for(int i = 0; i<rowCount; i++){
	    	
	    	user[i] = users.get(i);
	    	
	    	String id = user[i].getId();
	    	String name = user[i].getName();
	    	String sex = user[i].getSex();
	    	String grade = user[i].getGrade();
	    	String qq = user[i].getQQ();
	    	String phone = user[i].getPhone();
	    	
	    	datas[i][0] = id;
			datas[i][1] = name;
			datas[i][2] = sex;
			datas[i][3] = grade;
			datas[i][4] = qq;
			datas[i][5] = phone;
		    	
	    }
		return datas;
	}
	
	/**
	 * ��ȷ����
	 * @return 
	 */
	public List<User> findUserCorrectly(String id) {		
					
		ManagerDaoImpl dao = new ManagerDaoImpl() ;	
		List<User> users = null;	
		
		//��ò�ѯ���
		ResultSet rs = dao.selectById(id);
		System.err.println("������dao.selectById(findValue)");
		//��������
		users = dao.getAllUsers(rs);
		//�ر����ݿ�
		dao.closeAll();
					
		return users;
	    		
	}
	
	/**
	 * �������ң��꼶��ϵ���Ա�
	 * @return 
	 */
    public List<User> findUserdoByAddition(String grade,String department,String sex) {
      
		ManagerDaoImpl dao = new ManagerDaoImpl() ;
	
		List<User> users ;
			
		ResultSet rs = dao.selectByGrade_Department_Sex(grade, department, sex);
		//��������
		users = dao.getAllUsers(rs);
		//�ر����ݿ�
		dao.closeAll();
					
		return users;			
	}	
		
	/**
	 * ���
	 * 
	 * @return
	 */
	public boolean addUser(User user) {

		ManagerDaoImpl dao = new ManagerDaoImpl();
		// ������Ϣ�����ݿ�
		boolean isAddSuccessful = dao.insertUser(user);
		// �ر�������Դ
		dao.closeAll();
		if (isAddSuccessful)
			// ����ͷ����Ŀ�ļ���
			savePicture(user.getHeadImage());

		return isAddSuccessful;

	}	
            
	/**
	 * ���ͷ��ť�������ͷ��ť����
	 * 1���õ�ͷ���·��
	 */
    public String getHeadImagePath() {    	
    	path = getFilePath(); 	
    	return path;	
	}	
    
    /**
     * ������޸İ�ť
     * 1�����Ҫ�޸ĵ�id�Ƿ�Ϊ��
     * 2�����Ҫ�޸ĵ�id�Ƿ����
     * @return 
     * @return 
     * @return 
     */
	public boolean clickBtnUpdate(String id){		
		
		//����û����Ƿ�Ϊ��
     	if( id == null || id.equals("")){
 			JOptionPane.showMessageDialog(null, "ѧ�Ų���Ϊ�գ�����");
 			return false;
 		}  	
 	
     	//����û��Ƿ����
     	if( findUserCorrectly(id).isEmpty() ){
     		JOptionPane.showMessageDialog(null, "�Ҳ������û�");
			return false;
     	}else{
     		return true;
     	}  						
	}	
       
    /**
     * ȷ��ɾ��
     * 1�����Ҫ�޸ĵ�id�Ƿ�Ϊ��
     * 2�����Ҫ�޸ĵ�id�Ƿ����
     * 3��չʾҪɾ���û�����Ϣ
     * 4�����ȷ������ִ��ɾ��
     */
	public boolean deleteUser(String id) {
		
		//����û����Ƿ�Ϊ��
     	if( id == null || id.equals("")){
 			JOptionPane.showMessageDialog(null , "ѧ�Ų���Ϊ�գ�����");
 			return false ;
 		}
     	   	
     	//����û��Ƿ����
     	if(findUserCorrectly(id).isEmpty()){
     		JOptionPane.showMessageDialog(null, "�Ҳ������û�");
			return false;
     	}
						
		int i = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "ȷ��ɾ��", JOptionPane.YES_NO_OPTION);
		//ȷ��ɾ��
		if(i==JOptionPane.YES_OPTION){
			ManagerDaoImpl dao = new ManagerDaoImpl();
			boolean isDeleteSuccessful = dao.deleteUser(id);
			dao.closeAll();
			return isDeleteSuccessful ;
		}else{
			return false ;
		}
			
	}
    	
	
	///**
	// * ����޸İ�ť
	 //*/
    //public void clickBtnUpdate(){	
    	
	//}
	
	/**
	 * �޸��û���Ϣ
	 * @param name
	 * @param sex
	 * @param id
	 * @param grade
	 * @param department
	 * @param qq
	 * @param phone
	 * @param address
	 * @param birthday
	 * @param sign
	 * @param headImage
	 * @return
	 */
	public boolean updateUser(User user){
				
		ManagerDaoImpl dao = new ManagerDaoImpl();
		boolean isUpdateSuccessful = dao.updateUser(user);	
		dao.closeAll();
		
		if(isUpdateSuccessful){
			//����ͷ��"./res/headImage/"�ļ�����
    		savePicture(user.getHeadImage());
    		return true;
		}	
		else			
		    return false;	
		
	}
	
     
 	/**
 	 * �޸�����
 	 * @return 
 	 */
 	public boolean updatePassword(String oldPwd,String newPwd ,String reNewPwd) {
 			
 		//���ڼ�¼���ݿ����û�������
 		String pwd = null ;
 		
 		ManagerDaoImpl dao = new ManagerDaoImpl();
 		
 		ResultSet rs = dao.selectById(FrmManagerControl.onlineId);
 		//��������
 		User user = dao.getUser(rs);
 		//�ر����ݿ�
 		dao.closeAll();
 		
 		//ȡ���û�������
 		pwd = user.getPassword().trim();
 			
 		if(!oldPwd.equals(pwd)){
 			JOptionPane.showMessageDialog(null, "ԭ�������");
 			return false; 
 		}
 		if(oldPwd.equals(newPwd)){
 			JOptionPane.showMessageDialog(null, "ԭ������������ظ�");
 			return false;
 		}
 		if(!newPwd.equals(reNewPwd)){
 			JOptionPane.showMessageDialog(null, "ȷ�������������벻һ��");
 			return false;
 		}else{
 			dao.updatePassword(FrmManagerControl.onlineId,newPwd);
 			return true ; 
 		}
 		
 	}
 	
 	/**
 	 * �޸Ĺ���ԱȨ��
 	 */
 	public void updateManager(String id){
 		 		
 		if(id == null || id.equals(""))
 			return ;
 		
 		ManagerDaoImpl dao = new ManagerDaoImpl();
 		try {
 			
 			//����û��Ƿ����
 			if(!dao.selectById(id).next()){
 				JOptionPane.showMessageDialog(null, "�Ҳ������û���");
 				return ;
 			}
 			
 			//�Ƿ���Ϊ����Ա
 			int i = JOptionPane.showConfirmDialog(null,  "ѡ�� �� �������Ϊ����Ա��ѡ�� �� �����Ƴ�����ԱȨ��", "����ԱȨ��ȷ��", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);					
 		    //ѡ��1����Ϊ����Ա
 			if(i==JOptionPane.YES_OPTION){
 					
 				boolean yn = dao.updateIsManager(id, "��");
 				dao.closeAll();
 				if(yn){
 				    JOptionPane.showMessageDialog(null, "����ԱȨ���޸ĳɹ������û�����Ϊ����Ա");
 				}else{
 				    JOptionPane.showMessageDialog(null, "����ԱȨ���޸�ʧ��");
 				}
 			//ѡ��2���Ƴ�����ԱȨ��	
 			}else if(i==JOptionPane.NO_OPTION){
 				
 				boolean yn = dao.updateIsManager(id, "��");
 				dao.closeAll();
 				if(yn){
 				    JOptionPane.showMessageDialog(null, "����ԱȨ���޸ĳɹ������û����Ƴ��˹���ԱȨ��");
 				}else{
 				    JOptionPane.showMessageDialog(null, "����ԱȨ���޸�ʧ��");
 				}
 			//ѡ��3��ȡ��	
 			}else{
 				return ;
 			}	
 			
 		} catch (SQLException e1) {
 			e1.printStackTrace();
 		}
 		
 	}
 	
 	/**
 	 * �������й���Ա
 	 * ����ʾ�������Ի���
 	 */
 	public void findAllManager() {
 		
 		JDialog dialog = new JDialog();
 		dialog.setLayout(new BorderLayout());
 		dialog.setBounds(420, 250, 600, 400);
         
 		dialog.setModal(true);
 		JPanel contentPanel = new JPanel();
 		contentPanel.setLayout(new BorderLayout());
 		
 		dialog.add(contentPanel, BorderLayout.CENTER);								
 		
 		ManagerDaoImpl dao = new ManagerDaoImpl() ;				
 		List<User> users ;									
 		//��ò�ѯ���
 		ResultSet rs = dao.selectByIsManager("��");
 		//��������
 		users = dao.getAllUsers(rs);
 		//�ر����ݿ�
 		dao.closeAll();				
 	    
 		//�����½����
 		Object[][] datas = null;
 		String[] titles = {"ѧ��","����","�Ա�","�꼶","QQ","�绰"};	
 		datas = getTableDatas(users);						
 			    		    						   					
 		//�½����					
 		JTable tableManagers = new JTable();
 		tableManagers.setModel(new DefaultTableModel(datas,titles));
 		tableManagers.setOpaque(false);
 		
 		
 		JScrollPane jspan = new JScrollPane(tableManagers);
 		jspan.setOpaque(false);			
 		jspan.getViewport().setOpaque(false);
 		jspan.setBorder(new TitledBorder(null, "���ҵ������й���ԱΪ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
 		contentPanel.add(jspan, BorderLayout.CENTER);
 			
 		dialog.setVisible(true);
 		
 	}
 	              
    /**
     * �õ���Ƭ��·��
     * @return
     */
    private String getFilePath() {
		
		// ����һ������ָ��������ļ��Ի��򴰿ڣ����ڼ����ļ���
		FileDialog filedialog = new FileDialog(FrmManagerControl.frmManager,"��",FileDialog.LOAD);
		filedialog.setVisible(true);
		//��ȡ�ļ��Ի������û�ѡ�е��ļ����ڵ�·��
		String filePath = filedialog.getDirectory();
		//��ȡ�Ի������û�ѡ�е��ļ���
		String fileName = filedialog.getFile();
		//System.out.println("��ѡ����л�õ�pathΪ"+filePath+fileName);
		
		if(filePath!=null && fileName!=null ){
			return filePath+fileName;
		}else{
			return null ; 
		}
		
		
		
		
	}
	
	/**
	 * ����ͷ��"./res/headImage/"�ļ�����		
	 * @param headImage
	 */
	public void savePicture(String headImage){
				
		Image image ;	
		
		System.out.println("��Ҫ�����ͼƬ·��Ϊ"+path);
		//���ж�·���Ƿ�Ϊ��
		if( path == null && !new File("./res/headImage/"+PanAdd.txtId.getText().trim()+".jpg").exists()){
			try {
				//����ͼƬ
				image = ImageIO.read(new File("./res/headImage/noHeadImage.jpg"));
				//�������ͼƬд�뵽��Ŀ��"./res/headImage/"�ļ�����
				ImageIO.write((RenderedImage)image, "jpg", new File("./res/headImage/"+headImage));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "ͼƬ�������");
				e.printStackTrace();
			}
		}
		
		else if( path != null ){
			try {
				//����ͼƬ
				image = ImageIO.read(new File(path));
				//�������ͼƬд�뵽��Ŀ��"./res/headImage/"�ļ�����
				ImageIO.write((RenderedImage)image, "jpg", new File("./res/headImage/"+headImage));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "ͼƬ�������");
				e.printStackTrace();
			}
		}
		
	}

	/**
	 *  �ڱ����ִ��ɾ��������
	 *  1����ñ���б�ѡ�е���
	 *  2��ɾ��
	 *  3����������
	 * @return datas
	 */
	public Object[][] deleteUsers() {
		//�õ���ѡ�е���
    	int[] row = PanFind.tableFindResult.getSelectedRows() ;
    	if(row.length==0){
    		JOptionPane.showMessageDialog(null, "������ѡ��һ���û�");
        	return null;
    	}
        //���汻ѡ�е���
    	String[] temp = new String[row.length] ;
        for(int i = 0;i<row.length;i++){
        	temp[i] = (String) PanFind.tableFindResult.getValueAt(row[i], 0);
        
        }
    	int a = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if(a!=JOptionPane.YES_OPTION)
    		return null ;
    	
        //����ɾ���ɹ��ĸ���
        int deleteCount = 0 ;
        //���ɾ��
		for(int i = 0;i<temp.length;i++){	
            ManagerDaoImpl dao =  new ManagerDaoImpl();
            if( dao.deleteUser(temp[i]))
        	   deleteCount++ ;         			
		}
		if(deleteCount == temp.length ){
			JOptionPane.showMessageDialog(null, "ɾ���ɹ�", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
			List<User> users = findUserProbably("id", "");
			Object[][] datas = getTableDatas(users);
			return datas ;
		}else{
			JOptionPane.showMessageDialog(null, "ɾ��ʧ��", "��ʾ", JOptionPane.WARNING_MESSAGE);
		}
		return null;
		
	}

	/**
	 * �õ�����б�ѡ�е��û�id
	 * @return
	 */
	public String getSelectedUserId() {
		
		int[] row = PanFind.tableFindResult.getSelectedRows() ;
        if(row.length != 1){
        	JOptionPane.showMessageDialog(null, "��ѡ��һ���û�");
        	return null;
        }
    	
        return (String) PanFind.tableFindResult.getValueAt(row[0], 0);

	}

	/**
	 * ����Ա��½
	 * @param id
	 * @param password
	 * @return 
	 */
	public boolean managerLogin(String id, String password) {
		
	
		//��ѯ���ݿ⣬�ж��Ƿ�Ϊ����Ա		
		boolean isManager = dao.isManager(id);	
		
		if(isManager){								
			
			//�Ƿ��½�ɹ���������ȷ
			boolean isLoginSuccessful = dao.checkUser(id,password);					
			if(isLoginSuccessful){										
				//��¼��½�ĵ�ǰ�û�id
				FrmManagerControl.onlineId = id ;							
				return true;					
			}else{
				return false;
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "���ǹ���Ա�����ṩ����ԱȨ��");
			return false;
		}					
		
	}

	/**
	 * ��ͨ��Ա��½
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean userLogin(String id, String password) {

		// ���������ѧ���Ƿ�Ϊ��
		if (id == null || id.equals("")) {
			JOptionPane.showMessageDialog(null, "������ѧ��", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		// �Ƿ��½�ɹ�
		boolean isLoginSucessful = dao.checkUser(id, password);
		if (isLoginSucessful) {
			// ��¼��½�ĵ�ǰ�û�id
			FrmManagerControl.onlineId = id;
			return true;
		} else {
			return false;
		}

	}

}
