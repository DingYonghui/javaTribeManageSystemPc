package com.dyh.javaTribeManSys.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dyh.javaTribeManSys.dao.IDatabaseConnection;

/**
 * Oracle�����ࣺ
 * 1����������
 * 2���������ݿ�
 * 3������Oracle��Connection����
 * @author ding
 *
 */
public class OracleConnectionImpl implements IDatabaseConnection{


	public Connection getConnection() {
		
		String driver = DBConstants.Oracle_DRIVER;
		String url = DBConstants.Oracle_URL;
		String username = DBConstants.Oracle_USERNAME;
		String password = DBConstants.Oracle_PASSWORD; 
		
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
