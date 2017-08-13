package com.dyh.javaTribeManSys.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.dyh.javaTribeManSys.dao.IDatabaseConnection;

/**
 * SQLServer2005连接类：
 * 1、加载驱动
 * 2、连接数据库
 * 3、创建SQLServer2005的Connection对象
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
