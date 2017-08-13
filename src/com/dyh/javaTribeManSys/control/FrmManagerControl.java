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
 * 管理员界面控制器类，即控制层
 * 
 * 控制层作用是：   
 * 1、获得视图层的数据   
 * 2、调用业务层的处理方法
 * 3、通知视图层显示处理后的数据
 * 
 * MVC设计模式:
 * V: view 视图 所有的ui页面 ：功能：显示数据 获取数据
 * C: control: 控制层  功能：获取视图层的数据,并且调用业务逻辑类
 * M: Model: 负责数据录入 和业务逻辑的实现
 * 
 */
 
public class FrmManagerControl implements ActionListener,ItemListener{

	//登陆界面（视图层）
	FrmLogin frmLogin ;

	//管理者主界面（视图层）
	public static FrmManager frmManager ;  	
	//普通成员主界面
	private FrmStudent frmStudent ;
	
	//创建业务逻辑类对象（业务层之一）
	ManagerImpl managerImpl = new ManagerImpl();

	//当前登录的id
	public static String onlineId;
	//记录当前登陆用户头像的文件路径
	public static String onlinePATH ;

	/**
	 * 无参数的构造方法
	 */
	public FrmManagerControl(){
		
	}
		
	public FrmManagerControl(FrmLogin frmLogin) {
		this.frmLogin = frmLogin ;
	}
	
	/**
	 * 重载ActionListener中方法
	 */
	public void actionPerformed(ActionEvent e) {		
		
		String request = e.getActionCommand() ;
		
		if(request.equals("登陆")){
			doLogin();					
		}	
		/**
		 * frmManager主界面中   菜单项   与   管理面板中的
		 * "信息查询"、"信息添加"、"信息删除"、"信息修改"、"我的信息"和"设置"
		 * 6个按钮的处理操作
		 */
		else if(request.equals("注销(L)")){		
			doLogout();		
		}
		else if(request.equals("退出(X)")){
			System.exit(0);
		}
		else if(request.equals("关于")){
			JOptionPane.showMessageDialog(frmManager,"爪哇部落信息管理系统");
		}
		else if(request.equals("信息查询")||request.equals("查找(F)")){									
			frmManager.showPanFind();
		}
		else if(request.equals("信息添加")||request.equals("添加(A)")){           			
			frmManager.showPanAdd();
		}
		else if(request.equals("信息删除")||request.equals("删除(D)")){
			frmManager.showPanDelete();
		}
        else if(request.equals("信息修改")||request.equals("修改(U)")){            					
			frmManager.showPanUpdate();
		}
        else if(request.equals("我的信息")){
			frmManager.showPanMyInfo();
			frmManager.panMyInfo.initMyInformation(onlineId);
		}
        else if(request.equals("设置")){
        	frmManager.showPanInstall();
        	frmManager.panInstall.clearPassword();
        }
		
        /**
         * panFind“信息查询”面板中“查找”按钮的处理操作
         */
        else if(request.equals("查找")){      	
        	doFindUser();    	
        }	
		
        /**
         * panAdd“信息添加”面板中“确定添加”、“添加下一个”、“添加头像”3个按钮的处理操作
         */
        else if(request.equals("确定添加")){   	
        	doAddUser();
        }	
        else if(request.equals("添加下一个")){
        	frmManager.panAdd.addAgainResponse();
        }
        else if(request.equals("添加头像")){       	
        	String path = managerImpl.getHeadImagePath();      	
        	frmManager.panAdd.addHeadImageResponse(path);    	
        }
				
        /**
         * panUpdate“信息修改”面板中“确认修改”按钮的处理操作
         */
        else if(request.equals("确认修改")){        	
        	//获得要修改的id
    		String id = frmManager.panUpdate.getParameter("id"); 		  		
    		boolean isUserExist = managerImpl.clickBtnUpdate(id);        	
    		frmManager.panUpdate.BtnUpdateResponse(isUserExist,id, frmManager, frmManager.panMyInfo);
        }	
		
        /**
         * panDelete“信息删除”面板中 “确认删除”按钮的处理操作
         */    
        else if(request.equals("确认删除")){     	
        	doDeleteUser();
        }
		
		/**
	     * panMyInfo“我的信息”面板中“修改”、“确认”、“更改头像”3个按钮的处理操作
	     */
        else if(e.getSource() == frmManager.panMyInfo.getBtnUpdate()){       	
        	frmManager.panMyInfo.response();
        }
        else if(e.getSource() == frmManager.panMyInfo.getBtnOK()){
        	doUpdateUser();
        }
        else if(request.equals("更换头像")){
        	String path = managerImpl.getHeadImagePath();       			       			    			
        	frmManager.panMyInfo.ChangeHeadImageResponse(path);      	
        }
	
        /**
         * panInstall设置面板中“重置”“确认”“更改管理者权限”和“查找管理员”4个按钮的处理操作
         */	
        else if(request.equals("重置")){
        	frmManager.panInstall.clearPassword();
        }	
        else if(e.getSource() == frmManager.panInstall.getBtnOk()){    		
        	doUpdatePassword();
        }	
        else if(request.equals("修改管理员权限")){  	
        	String id = JOptionPane.showInputDialog(frmManager,"请输入要修改的管理员学号");	
        	managerImpl.updateManager(id);      	
        }
        else if(request.equals("查询所有管理员")){
        	managerImpl.findAllManager();
        }
			   
		/**
		 * 弹出式菜单的菜单项处理：修改、删除、详细信息
		 */ 
        else if(request.equals("修改")||request.equals("详细信息")){
        	String id = managerImpl.getSelectedUserId();
            frmManager.showPanMyInfo();
            frmManager.panMyInfo.initMyInformation(id);
        }
        else if(request.equals("删除")){
        	Object[][] datas = managerImpl.deleteUsers();
        	frmManager.panFind.createNewTable(datas);
        }
				
	}
	
	/**
	 * 修改密码
	 */
	private void doUpdatePassword() {
		String oldPwd = frmManager.panInstall.getOldPwd();
 		String newPwd = frmManager.panInstall.getNewPwd();
 		String reNewPwd = frmManager.panInstall.getReNewPwd();
 		   
 		//检查密码是否合法
 		if(oldPwd.equals("")){
			JOptionPane.showMessageDialog(frmManager, "原密码不能为空","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}		
		if(oldPwd.length()<9||oldPwd.length()>15){
			JOptionPane.showMessageDialog(frmManager, "原密码长度范围在9~15之间","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		String dbPassword = managerImpl.findUserCorrectly(onlineId).get(0).getPassword().trim();
		if(!oldPwd.equals(dbPassword)){
			JOptionPane.showMessageDialog(frmManager, "原密码错误","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(newPwd.equals("")){
			JOptionPane.showMessageDialog(frmManager, "新密码不能为空","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(newPwd.length()<9||newPwd.length()>15){
			JOptionPane.showMessageDialog(frmManager, "新密码长度范围在9~15之间","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
			
		if(!reNewPwd.equals(newPwd)){
			JOptionPane.showMessageDialog(frmManager, "确认密码和密码必须相同","提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
 		
 		
    	boolean isUpdatePwordSuccessful = managerImpl.updatePassword(oldPwd, newPwd, reNewPwd);
    	
    	frmManager.panInstall.updatePasswordResponse(isUpdatePwordSuccessful);
		
	}

	/**
	 * 重载ItemListener中方法
	 * 信息查询中模糊查询的三个下拉列表框的事件处理
	 */
	public void itemStateChanged(ItemEvent e) {				
		
		//获得页面数据
		String grade = frmManager.panFind.getGrade();
		String department = frmManager.panFind.getDepartment();
		String sex = frmManager.panFind.getSex();
			
		ManagerImpl managerImpl = new ManagerImpl();

		List<User> users = managerImpl.findUserdoByAddition(grade, department, sex);
		Object[][] datas = managerImpl.getTableDatas(users);
				
		frmManager.panFind.findResponse(datas);				
	}
	

	/**
	 * 登陆
	 */
	private void doLogin() {
		// （1）e.getActionCommand().equals("登陆")--->获得登陆请求
		// （2）得到图形界面（显示层）的数据，包括学号、密码和管理员权限
		String id = frmLogin.getParameter("id");
		String password = frmLogin.getParameter("password");
		boolean isChooseManager = frmLogin.isManager();

		// 检查id是否合法
		if (id.equals("")) {
			JOptionPane.showMessageDialog(frmLogin, "学号不能为空", "提示",
					JOptionPane.WARNING_MESSAGE);
			return;// 下面的代码不再运行
		}
		if (!verifyId(id)) {
			JOptionPane.showMessageDialog(frmLogin, "学号请输入9位数字", "提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 检查密码是否合法
		if (password.equals("")) {
			JOptionPane.showMessageDialog(frmLogin, "密码不能为空", "提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (password.length() < 9 || password.length() > 15) {
			JOptionPane.showMessageDialog(frmLogin, "密码长度范围在9~15之间", "提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		// （3）调用业务处理类（业务层）的登陆方法
		boolean isLoginSuccessful;
		// 管理员登陆
		if (isChooseManager) {
			isLoginSuccessful = managerImpl.managerLogin(id, password);
			// （4）图形界面（显示层） 根据 业务处理方法返回的结果 做出处理操作
			if (isLoginSuccessful) {
				if (frmManager == null) {
					frmManager = new FrmManager();
					onlineId = id;
				}
				frmManager.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(frmLogin, "请输入正确的登录号与密码 !");
			}

		}
		// 普通成员登陆
		else {
			isLoginSuccessful = managerImpl.userLogin(id, password);
			if (isLoginSuccessful) {
				if (frmStudent == null) {
					frmStudent = new FrmStudent();
					onlineId = id;
				}
				frmStudent.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(frmLogin, "请输入正确的登录号与密码 !");
			}
		}

		// （4）图形界面（显示层）login界面的反应操作
		frmLogin.loginResponse(isLoginSuccessful);

	}

	/**
	 * 注销用户
	 */
	private void doLogout() {
		if(frmLogin == null){
			frmLogin = new FrmLogin();
		}
		frmLogin.setVisible(true);	
		frmManager.dispose();
	}
	
	/**
	 * 查找用户
	 */
	private void doFindUser() {
		//（1）e.getSource() == panFind.getBtnFind()--->查找请求
    	//（2）获得视图层中的查找条件
		String findCondition = frmManager.panFind.getFindCondition() ; 
		String value = frmManager.panFind.getId_OR_Name();      			
		//（3）调用业务层的处理方法：模糊查找
		List<User> users = managerImpl.findUserProbably(findCondition, value);			
		Object[][] datas = managerImpl.getTableDatas(users);	
		//（4）视图层根据处理结果做出反应处理
		frmManager.panFind.findResponse(datas);
		
	}
	
	/**
	 * 修改用户信息
	 */
	private void doUpdateUser() {
		User user = new User();   		
    	//获得用户界面的信息   
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
	 * 添加用户
	 */
	private void doAddUser() {
		// （1）e.getSource()==panAdd.getBtnOK()--->获得请求：确定添加
		// （2）得到视图层的数据并封装到实体类User中
		String id = frmManager.panAdd.getParameter("id");
		// 检查id是否合法
		if (id.equals("")) {
			JOptionPane.showMessageDialog(frmLogin, "学号不能为空", "提示",
					JOptionPane.WARNING_MESSAGE);
			return;// 下面的代码不再运行
		}
		if (!verifyId(id)) {
			JOptionPane.showMessageDialog(frmLogin, "学号请输入9位数字", "提示",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		// 检查用户是否已注册
		if (!managerImpl.findUserCorrectly(id).isEmpty()) {
			JOptionPane.showMessageDialog(null, "用户已注册，请更改学号");
			return;
		}
		// 检查name是否合法
		String name = frmManager.panAdd.getParameter("name");
		if (name.equals("")) {
			JOptionPane.showMessageDialog(frmManager, "姓名不能为空");
			return;
		}
		if (name.length() > 10) {
			JOptionPane.showMessageDialog(frmManager, "姓名不能超过10个字符");
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
		// 是否赋予管理员权限
		int i = JOptionPane.showConfirmDialog(null, "是否将该用户设为管理员？", "管理员设置",
				JOptionPane.YES_NO_OPTION);
		if (i == JOptionPane.YES_OPTION)
			user.setIsManager("是");
		else
			user.setIsManager("否");

		// （3）调用业务层的处理方法，并返回处理结果
		boolean isAddSuccessful = managerImpl.addUser(user);
		// （4）通知是视图层，视图层根据处理结果做出反应
		frmManager.panAdd.addResponse(isAddSuccessful);

	}
	
	/**
	 * 删除用户
	 */
	private void doDeleteUser() {
		String id = frmManager.panDelete.getParameter("id");             	
    	boolean isDeleteSuccessful = managerImpl.deleteUser(id);        	
    	frmManager.panDelete.deleteResponse(isDeleteSuccessful);	
	}

	/**
	 * 正则表达式： 检查匹配学号是否为数字
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
