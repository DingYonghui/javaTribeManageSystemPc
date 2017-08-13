package com.dyh.javaTribeManSys.dao.impl;

import com.dyh.javaTribeManSys.config.Config;

/**
 * ���ݿⳣ����
 * 
 * @author ding
 * 
 */

public class DBConstants {

	// ���ݿ�����
	public static final String DBTYPE_SQLSERVER2005 = "SQLServer2005";
	public static final String DBTYPE_MYSQL = "MySQL";
	public static final String DBTYPE_ORACLE = "Oracle";

	// MySQL��driver��url��user��password
	public static String MySQL_DRIVER = Config.getValue("MySQL_driver");// ���ݿ�����
	public static String MySQL_URL = Config.getValue("MySQL_url");// ���ݿ�Э��
	public static String MySQL_USERNAME = Config.getValue("MySQL_user");// �û���
	public static String MySQL_PASSWORD = Config.getValue("MySQL_password"); // ����

	// SQLServer2005��driver��url��user��password
	public static String SQLServer2005_DRIVER = Config
			.getValue("SQLServer2005_driver");// ���ݿ�����
	public static String SQLServer2005_URL = Config
			.getValue("SQLServer2005_url");// ���ݿ�Э��
	public static String SQLServer2005_USERNAME = Config
			.getValue("SQLServer2005_user");// �û���
	public static String SQLServer2005_PASSWORD = Config
			.getValue("SQLServer2005_password"); // ����

	// Oracle��driver��url��user��password
	public static String Oracle_DRIVER = Config.getValue("Oracle_driver");// ���ݿ�����
	public static String Oracle_URL = Config.getValue("Oracle_url");// ���ݿ�Э��
	public static String Oracle_USERNAME = Config.getValue("Oracle_user");// �û���
	public static String Oracle_PASSWORD = Config.getValue("Oracle_password"); // ����

}

