package com.dyh.javaTribeManSys.dao.impl;

import com.dyh.javaTribeManSys.dao.IDatabaseConnection;

/**
 * Dao工厂类，用于  创建  并返回  不同数据库的Connection对象
 * @author ding
 *
 */
public class DatabaseConnectionFactory {
		
	private static DatabaseConnectionFactory dbConnectionFactory;
	
	/**
	 * 静态生成DaoFactory类对象，
	 * 保证每个应用只有一个工厂对象存在
	 */
	static{
		dbConnectionFactory = new DatabaseConnectionFactory();
	}
	
	/**
	 * 构造方法
	 */
	private DatabaseConnectionFactory(){
		
	}
	
	/**
	 * 返回工厂对象
	 * @return
	 */
	public static DatabaseConnectionFactory newInstance(){
		return dbConnectionFactory;
	}
	
	/**
	 * 根据type的值返回对应的接口对象，
	 * 即不同类型的数据库Connection
	 * @param type
	 * @return
	 */
	public IDatabaseConnection createDao(String type){
		
		if(type.equalsIgnoreCase(DBConstants.DBTYPE_SQLSERVER2005))
			return new SQLServer2005ConnectionImpl();//创建并返回SQLServer 2005的Connection
		else if(type.equalsIgnoreCase(DBConstants.DBTYPE_MYSQL))
			return new MySQLConnectionImpl();//创建并返回MySQL数据库的Connection
		else if(type.equalsIgnoreCase(DBConstants.DBTYPE_ORACLE))
			return new OracleConnectionImpl();//创建并返回Oracle数据库的Connection
		else
		    return null;
	}
	
	
}
