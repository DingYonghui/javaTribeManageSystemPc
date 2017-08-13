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
 * 管理者的实现类（业务处理类）
 * 即业务层，作用是：处理数据
 * @author ding
 *
 */
public class ManagerImpl {
	

	//数据库实现类
	ManagerDaoImpl dao = new ManagerDaoImpl();
	
	//读取图片文件的路径
	private String path = null ;
			
	/**
	 * 模糊查找
	 * @return 
	 */
	public List<User> findUserProbably(String findCondition,String findValue) {		
					
		List<User> users = null ;	
		
		//查找条件为id
		if(findCondition.equals("id")){		
			//获得查询结果
			ResultSet rs =dao.selectLikeId(findValue);
			//处理结果集
			users = dao.getAllUsers(rs);
			//关闭数据库
			dao.closeAll();
		}
		//查找条件为name
		else{
			ResultSet rs = dao.selectLikeName(findValue);
			users = dao.getAllUsers(rs);
		    dao.closeAll();
		}		
		
		return users;
	    		
	}
	
	/**
	 * 将查询得到的User数据再次封装成创建表格的Object[][]数据
	 * @param users
	 * @return
	 */
	public Object[][] getTableDatas(List<User> users){
		
		if(users == null ){
			return null ;
		}
		
		Object[][] datas = null;
		String[] titles = {"学号","姓名","性别","年级","QQ","电话"};

		//行数即users的个数
		int rowCount = users.size();
		datas = new Object[rowCount][titles.length];	
		//用户列表
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
	 * 精确查找
	 * @return 
	 */
	public List<User> findUserCorrectly(String id) {		
					
		ManagerDaoImpl dao = new ManagerDaoImpl() ;	
		List<User> users = null;	
		
		//获得查询结果
		ResultSet rs = dao.selectById(id);
		System.err.println("调用了dao.selectById(findValue)");
		//处理结果集
		users = dao.getAllUsers(rs);
		//关闭数据库
		dao.closeAll();
					
		return users;
	    		
	}
	
	/**
	 * 条件查找：年级、系别、性别
	 * @return 
	 */
    public List<User> findUserdoByAddition(String grade,String department,String sex) {
      
		ManagerDaoImpl dao = new ManagerDaoImpl() ;
	
		List<User> users ;
			
		ResultSet rs = dao.selectByGrade_Department_Sex(grade, department, sex);
		//处理结果集
		users = dao.getAllUsers(rs);
		//关闭数据库
		dao.closeAll();
					
		return users;			
	}	
		
	/**
	 * 添加
	 * 
	 * @return
	 */
	public boolean addUser(User user) {

		ManagerDaoImpl dao = new ManagerDaoImpl();
		// 保存信息到数据库
		boolean isAddSuccessful = dao.insertUser(user);
		// 关闭数据资源
		dao.closeAll();
		if (isAddSuccessful)
			// 保存头像到项目文件夹
			savePicture(user.getHeadImage());

		return isAddSuccessful;

	}	
            
	/**
	 * 添加头像按钮（或更换头像按钮）：
	 * 1、得到头像的路径
	 */
    public String getHeadImagePath() {    	
    	path = getFilePath(); 	
    	return path;	
	}	
    
    /**
     * 点击了修改按钮
     * 1、检查要修改的id是否为空
     * 2、检查要修改的id是否存在
     * @return 
     * @return 
     * @return 
     */
	public boolean clickBtnUpdate(String id){		
		
		//检查用户名是否为空
     	if( id == null || id.equals("")){
 			JOptionPane.showMessageDialog(null, "学号不能为空！！！");
 			return false;
 		}  	
 	
     	//检查用户是否存在
     	if( findUserCorrectly(id).isEmpty() ){
     		JOptionPane.showMessageDialog(null, "找不到该用户");
			return false;
     	}else{
     		return true;
     	}  						
	}	
       
    /**
     * 确认删除
     * 1、检查要修改的id是否为空
     * 2、检查要修改的id是否存在
     * 3、展示要删除用户的信息
     * 4、如果确定，则执行删除
     */
	public boolean deleteUser(String id) {
		
		//检查用户名是否为空
     	if( id == null || id.equals("")){
 			JOptionPane.showMessageDialog(null , "学号不能为空！！！");
 			return false ;
 		}
     	   	
     	//检查用户是否存在
     	if(findUserCorrectly(id).isEmpty()){
     		JOptionPane.showMessageDialog(null, "找不到该用户");
			return false;
     	}
						
		int i = JOptionPane.showConfirmDialog(null, "确认删除？", "确认删除", JOptionPane.YES_NO_OPTION);
		//确认删除
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
	// * 点击修改按钮
	 //*/
    //public void clickBtnUpdate(){	
    	
	//}
	
	/**
	 * 修改用户信息
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
			//保存头像到"./res/headImage/"文件夹中
    		savePicture(user.getHeadImage());
    		return true;
		}	
		else			
		    return false;	
		
	}
	
     
 	/**
 	 * 修改密码
 	 * @return 
 	 */
 	public boolean updatePassword(String oldPwd,String newPwd ,String reNewPwd) {
 			
 		//用于记录数据库中用户的密码
 		String pwd = null ;
 		
 		ManagerDaoImpl dao = new ManagerDaoImpl();
 		
 		ResultSet rs = dao.selectById(FrmManagerControl.onlineId);
 		//处理结果集
 		User user = dao.getUser(rs);
 		//关闭数据库
 		dao.closeAll();
 		
 		//取得用户的密码
 		pwd = user.getPassword().trim();
 			
 		if(!oldPwd.equals(pwd)){
 			JOptionPane.showMessageDialog(null, "原密码错误");
 			return false; 
 		}
 		if(oldPwd.equals(newPwd)){
 			JOptionPane.showMessageDialog(null, "原密码和新密码重复");
 			return false;
 		}
 		if(!newPwd.equals(reNewPwd)){
 			JOptionPane.showMessageDialog(null, "确认密码与新密码不一致");
 			return false;
 		}else{
 			dao.updatePassword(FrmManagerControl.onlineId,newPwd);
 			return true ; 
 		}
 		
 	}
 	
 	/**
 	 * 修改管理员权限
 	 */
 	public void updateManager(String id){
 		 		
 		if(id == null || id.equals(""))
 			return ;
 		
 		ManagerDaoImpl dao = new ManagerDaoImpl();
 		try {
 			
 			//检查用户是否存在
 			if(!dao.selectById(id).next()){
 				JOptionPane.showMessageDialog(null, "找不到该用户！");
 				return ;
 			}
 			
 			//是否设为管理员
 			int i = JOptionPane.showConfirmDialog(null,  "选择“ 是 ”则添加为管理员，选择“ 否 ”则移除管理员权限", "管理员权限确认", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);					
 		    //选择1、设为管理员
 			if(i==JOptionPane.YES_OPTION){
 					
 				boolean yn = dao.updateIsManager(id, "是");
 				dao.closeAll();
 				if(yn){
 				    JOptionPane.showMessageDialog(null, "管理员权限修改成功，该用户被设为管理员");
 				}else{
 				    JOptionPane.showMessageDialog(null, "管理员权限修改失败");
 				}
 			//选择2、移除管理员权限	
 			}else if(i==JOptionPane.NO_OPTION){
 				
 				boolean yn = dao.updateIsManager(id, "否");
 				dao.closeAll();
 				if(yn){
 				    JOptionPane.showMessageDialog(null, "管理员权限修改成功，该用户被移除了管理员权限");
 				}else{
 				    JOptionPane.showMessageDialog(null, "管理员权限修改失败");
 				}
 			//选择3、取消	
 			}else{
 				return ;
 			}	
 			
 		} catch (SQLException e1) {
 			e1.printStackTrace();
 		}
 		
 	}
 	
 	/**
 	 * 查找所有管理员
 	 * 并显示到弹出对话框
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
 		//获得查询结果
 		ResultSet rs = dao.selectByIsManager("是");
 		//处理结果集
 		users = dao.getAllUsers(rs);
 		//关闭数据库
 		dao.closeAll();				
 	    
 		//用于新建表格
 		Object[][] datas = null;
 		String[] titles = {"学号","姓名","性别","年级","QQ","电话"};	
 		datas = getTableDatas(users);						
 			    		    						   					
 		//新建表格					
 		JTable tableManagers = new JTable();
 		tableManagers.setModel(new DefaultTableModel(datas,titles));
 		tableManagers.setOpaque(false);
 		
 		
 		JScrollPane jspan = new JScrollPane(tableManagers);
 		jspan.setOpaque(false);			
 		jspan.getViewport().setOpaque(false);
 		jspan.setBorder(new TitledBorder(null, "查找到的所有管理员为", TitledBorder.LEADING, TitledBorder.TOP, null, null));
 		contentPanel.add(jspan, BorderLayout.CENTER);
 			
 		dialog.setVisible(true);
 		
 	}
 	              
    /**
     * 得到照片的路径
     * @return
     */
    private String getFilePath() {
		
		// 创建一个具有指定标题的文件对话框窗口，用于加载文件。
		FileDialog filedialog = new FileDialog(FrmManagerControl.frmManager,"打开",FileDialog.LOAD);
		filedialog.setVisible(true);
		//获取文件对话框中用户选中的文件所在的路径
		String filePath = filedialog.getDirectory();
		//获取对话框中用户选中的文件名
		String fileName = filedialog.getFile();
		//System.out.println("在选择框中获得的path为"+filePath+fileName);
		
		if(filePath!=null && fileName!=null ){
			return filePath+fileName;
		}else{
			return null ; 
		}
		
		
		
		
	}
	
	/**
	 * 保存头像到"./res/headImage/"文件夹中		
	 * @param headImage
	 */
	public void savePicture(String headImage){
				
		Image image ;	
		
		System.out.println("需要保存的图片路劲为"+path);
		//先判断路径是否为空
		if( path == null && !new File("./res/headImage/"+PanAdd.txtId.getText().trim()+".jpg").exists()){
			try {
				//读入图片
				image = ImageIO.read(new File("./res/headImage/noHeadImage.jpg"));
				//将读入的图片写入到项目的"./res/headImage/"文件夹中
				ImageIO.write((RenderedImage)image, "jpg", new File("./res/headImage/"+headImage));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "图片读入错误");
				e.printStackTrace();
			}
		}
		
		else if( path != null ){
			try {
				//读入图片
				image = ImageIO.read(new File(path));
				//将读入的图片写入到项目的"./res/headImage/"文件夹中
				ImageIO.write((RenderedImage)image, "jpg", new File("./res/headImage/"+headImage));
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "图片读入错误");
				e.printStackTrace();
			}
		}
		
	}

	/**
	 *  在表格中执行删除操作：
	 *  1、获得表格中被选中的行
	 *  2、删除
	 *  3、返回数据
	 * @return datas
	 */
	public Object[][] deleteUsers() {
		//得到被选中的行
    	int[] row = PanFind.tableFindResult.getSelectedRows() ;
    	if(row.length==0){
    		JOptionPane.showMessageDialog(null, "请至少选中一个用户");
        	return null;
    	}
        //保存被选中的行
    	String[] temp = new String[row.length] ;
        for(int i = 0;i<row.length;i++){
        	temp[i] = (String) PanFind.tableFindResult.getValueAt(row[i], 0);
        
        }
    	int a = JOptionPane.showConfirmDialog(null, "确认删除？", "确认", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if(a!=JOptionPane.YES_OPTION)
    		return null ;
    	
        //计算删除成功的个数
        int deleteCount = 0 ;
        //逐个删除
		for(int i = 0;i<temp.length;i++){	
            ManagerDaoImpl dao =  new ManagerDaoImpl();
            if( dao.deleteUser(temp[i]))
        	   deleteCount++ ;         			
		}
		if(deleteCount == temp.length ){
			JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
			List<User> users = findUserProbably("id", "");
			Object[][] datas = getTableDatas(users);
			return datas ;
		}else{
			JOptionPane.showMessageDialog(null, "删除失败", "提示", JOptionPane.WARNING_MESSAGE);
		}
		return null;
		
	}

	/**
	 * 得到表格中被选中的用户id
	 * @return
	 */
	public String getSelectedUserId() {
		
		int[] row = PanFind.tableFindResult.getSelectedRows() ;
        if(row.length != 1){
        	JOptionPane.showMessageDialog(null, "请选中一个用户");
        	return null;
        }
    	
        return (String) PanFind.tableFindResult.getValueAt(row[0], 0);

	}

	/**
	 * 管理员登陆
	 * @param id
	 * @param password
	 * @return 
	 */
	public boolean managerLogin(String id, String password) {
		
	
		//查询数据库，判断是否为管理员		
		boolean isManager = dao.isManager(id);	
		
		if(isManager){								
			
			//是否登陆成功，密码正确
			boolean isLoginSuccessful = dao.checkUser(id,password);					
			if(isLoginSuccessful){										
				//记录登陆的当前用户id
				FrmManagerControl.onlineId = id ;							
				return true;					
			}else{
				return false;
			}
			
		}else{
			JOptionPane.showMessageDialog(null, "不是管理员，请提供管理员权限");
			return false;
		}					
		
	}

	/**
	 * 普通成员登陆
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public boolean userLogin(String id, String password) {

		// 检验输入的学号是否为空
		if (id == null || id.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入学号", "提示",
					JOptionPane.INFORMATION_MESSAGE);
			return false;
		}

		// 是否登陆成功
		boolean isLoginSucessful = dao.checkUser(id, password);
		if (isLoginSucessful) {
			// 记录登陆的当前用户id
			FrmManagerControl.onlineId = id;
			return true;
		} else {
			return false;
		}

	}

}
