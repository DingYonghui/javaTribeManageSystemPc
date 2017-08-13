package com.dyh.javaTribeManSys.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dyh.javaTribeManSys.dao.IDatabaseConnection;

/**
 * SQLServer2005�����ࣺ
 * 1����������
 * 2���������ݿ�
 * 3������SQLServer2005��Connection����
 * @author ding
 *
 */

public class SQLServer2005ConnectionImpl implements IDatabaseConnection{


	public Connection getConnection() {
		
		String driver = DBConstants.SQLServer2005_DRIVER;
		String url = DBConstants.SQLServer2005_URL;
		String username = DBConstants.SQLServer2005_USERNAME;
		String password = DBConstants.SQLServer2005_PASSWORD; 
		
		/**
		 *  �������������������ݿ� 
		 */
		try { 			
			Class.forName(driver); 
			System.out.println("���������ɹ�");
			Connection conn = DriverManager.getConnection( url,username, password ); 		
			System.out.println("�������ݿ�ɹ�");
			return conn;
		}
		//����������������쳣
		 catch ( ClassNotFoundException cnfex ) {
			 System.err.println("װ�� JDBC/ODBC ��������ʧ�ܡ�" );
			 cnfex.printStackTrace(); 
		 } 
		 //�����������ݿ��쳣
		 catch ( SQLException sqlex ) {
			 System.err.println( "�޷��������ݿ�" );
			 sqlex.printStackTrace(); 
		 }
		return null;
		
	}


}