package com.dyh.javaTribeManSys.dao;

import java.sql.ResultSet;
import java.util.List;

import com.dyh.javaTribeManSys.pojo.User;

/**
 * �ӿڣ������˹������ݿ�����ĳ��󷽷�
 * @author ding
 *
 */
public interface IManagerDao {
	
	//����
	public ResultSet selectLikeId(String id);
	public ResultSet selectLikeName(String name);
	
	public ResultSet selectById(String id);
	public ResultSet selectByIsManager(String isManager);
	public ResultSet selectByGrade_Department_Sex(String grade,String department,String sex);
	//����
	public boolean insertUser(User user);
	//ɾ��
	public boolean deleteUser(String id);
	//�޸�
	public boolean updateUser(User user) ;
	
	//�޸Ĺ���ԱȨ��
	public boolean updateIsManager(String id,String isManager);
	//�޸�����
	public boolean updatePassword(String id,String password);
	
	//���id�������Ƿ���ȷ
	public boolean checkUser(String id,String password);
	//����Ƿ�Ϊ����Ա
	public boolean isManager(String id);
   
	//�õ��û�
	public User getUser(ResultSet rs) ;
	public List<User> getAllUsers(ResultSet rs) ;
	
	//�ر����ݿ���Դ 
	public void closeAll();

}
