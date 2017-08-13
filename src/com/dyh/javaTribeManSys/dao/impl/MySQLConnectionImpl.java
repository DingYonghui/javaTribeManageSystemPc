package com.dyh.javaTribeManSys.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dyh.javaTribeManSys.dao.IDatabaseConnection;

/**
 * MySQL连接类：
 * 1、加载驱动
 * 2、连接数据库
 * 3、创建MySQL的Connection对象
 * @author ding
 *
 */
public class MySQLConnectionImpl implements IDatabaseConnection{

	
	public Connection getConnection() {
		String driver = DBConstants.MySQL_DRIVER;
		String url = DBConstants.MySQL_URL;
		String username = DBConstants.MySQL_USERNAME;
		String password = DBConstants.MySQL_PASSWORD; 
		
		/**
		 *  加载驱动程序，连接数据库 
		 */
		try { 			
			Class.forName(driver); 
			System.out.println("加载驱动成功");
			Connection conn = DriverManager.getConnection( url,username, password ); 		
			System.out.println("连接数据库成功");
			return conn;
		}
		//捕获加载驱动程序异常
		 catch ( ClassNotFoundException cnfex ) {
			 System.err.println("装载 JDBC/ODBC 驱动程序失败。" );
			 cnfex.printStackTrace(); 
		 } 
		 //捕获连接数据库异常
		 catch ( SQLException sqlex ) {
			 System.err.println( "无法连接数据库" );
			 sqlex.printStackTrace(); 
		 }
		return null;
		
	}


}
