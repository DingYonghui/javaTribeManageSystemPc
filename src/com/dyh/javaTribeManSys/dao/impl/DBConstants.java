package com.dyh.javaTribeManSys.dao.impl;

import com.dyh.javaTribeManSys.config.Config;

/**
 * 数据库常量类
 * 
 * @author ding
 * 
 */

public class DBConstants {

	// 数据库类型
	public static final String DBTYPE_SQLSERVER2005 = "SQLServer2005";
	public static final String DBTYPE_MYSQL = "MySQL";
	public static final String DBTYPE_ORACLE = "Oracle";

	// MySQL的driver、url、user、password
	public static String MySQL_DRIVER = Config.getValue("MySQL_driver");// 数据库驱动
	public static String MySQL_URL = Config.getValue("MySQL_url");// 数据库协议
	public static String MySQL_USERNAME = Config.getValue("MySQL_user");// 用户名
	public static String MySQL_PASSWORD = Config.getValue("MySQL_password"); // 密码

	// SQLServer2005的driver、url、user、password
	public static String SQLServer2005_DRIVER = Config
			.getValue("SQLServer2005_driver");// 数据库驱动
	public static String SQLServer2005_URL = Config
			.getValue("SQLServer2005_url");// 数据库协议
	public static String SQLServer2005_USERNAME = Config
			.getValue("SQLServer2005_user");// 用户名
	public static String SQLServer2005_PASSWORD = Config
			.getValue("SQLServer2005_password"); // 密码

	// Oracle的driver、url、user、password
	public static String Oracle_DRIVER = Config.getValue("Oracle_driver");// 数据库驱动
	public static String Oracle_URL = Config.getValue("Oracle_url");// 数据库协议
	public static String Oracle_USERNAME = Config.getValue("Oracle_user");// 用户名
	public static String Oracle_PASSWORD = Config.getValue("Oracle_password"); // 密码

}

