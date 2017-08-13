package com.dyh.javaTribeManSys.dao;

import java.sql.Connection;

/**
 * 接口，数据库连接的抽象方法
 * @author ding
 *
 */
public interface IDatabaseConnection {
	
	//连接数据库
	public Connection getConnection();

}
