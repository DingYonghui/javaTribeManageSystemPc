package com.dyh.javaTribeManSys.dao;

import java.sql.Connection;

/**
 * �ӿڣ����ݿ����ӵĳ��󷽷�
 * @author ding
 *
 */
public interface IDatabaseConnection {
	
	//�������ݿ�
	public Connection getConnection();

}
