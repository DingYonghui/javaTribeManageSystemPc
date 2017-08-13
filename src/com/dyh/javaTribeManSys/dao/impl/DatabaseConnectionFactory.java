package com.dyh.javaTribeManSys.dao.impl;

import com.dyh.javaTribeManSys.dao.IDatabaseConnection;

/**
 * Dao�����࣬����  ����  ������  ��ͬ���ݿ��Connection����
 * @author ding
 *
 */
public class DatabaseConnectionFactory {
		
	private static DatabaseConnectionFactory dbConnectionFactory;
	
	/**
	 * ��̬����DaoFactory�����
	 * ��֤ÿ��Ӧ��ֻ��һ�������������
	 */
	static{
		dbConnectionFactory = new DatabaseConnectionFactory();
	}
	
	/**
	 * ���췽��
	 */
	private DatabaseConnectionFactory(){
		
	}
	
	/**
	 * ���ع�������
	 * @return
	 */
	public static DatabaseConnectionFactory newInstance(){
		return dbConnectionFactory;
	}
	
	/**
	 * ����type��ֵ���ض�Ӧ�Ľӿڶ���
	 * ����ͬ���͵����ݿ�Connection
	 * @param type
	 * @return
	 */
	public IDatabaseConnection createDao(String type){
		
		if(type.equalsIgnoreCase(DBConstants.DBTYPE_SQLSERVER2005))
			return new SQLServer2005ConnectionImpl();//����������SQLServer 2005��Connection
		else if(type.equalsIgnoreCase(DBConstants.DBTYPE_MYSQL))
			return new MySQLConnectionImpl();//����������MySQL���ݿ��Connection
		else if(type.equalsIgnoreCase(DBConstants.DBTYPE_ORACLE))
			return new OracleConnectionImpl();//����������Oracle���ݿ��Connection
		else
		    return null;
	}
	
	
}
