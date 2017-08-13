package com.dyh.javaTribeManSys.dao;

import java.sql.ResultSet;
import java.util.List;

import com.dyh.javaTribeManSys.pojo.User;

/**
 * 接口，定义了关于数据库操作的抽象方法
 * @author ding
 *
 */
public interface IManagerDao {
	
	//查找
	public ResultSet selectLikeId(String id);
	public ResultSet selectLikeName(String name);
	
	public ResultSet selectById(String id);
	public ResultSet selectByIsManager(String isManager);
	public ResultSet selectByGrade_Department_Sex(String grade,String department,String sex);
	//插入
	public boolean insertUser(User user);
	//删除
	public boolean deleteUser(String id);
	//修改
	public boolean updateUser(User user) ;
	
	//修改管理员权限
	public boolean updateIsManager(String id,String isManager);
	//修改密码
	public boolean updatePassword(String id,String password);
	
	//检查id与密码是否正确
	public boolean checkUser(String id,String password);
	//检查是否为管理员
	public boolean isManager(String id);
   
	//得到用户
	public User getUser(ResultSet rs) ;
	public List<User> getAllUsers(ResultSet rs) ;
	
	//关闭数据库资源 
	public void closeAll();

}
