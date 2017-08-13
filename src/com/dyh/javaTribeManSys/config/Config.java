package com.dyh.javaTribeManSys.config;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 读取配置文件的类
 * @author ding
 *
 */
public class Config {
	private static Properties p = null;
	static {
		try{
			p = new Properties();
			//加载配置文件
			p.load(new FileInputStream("config/db.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//获取键对应的值
	public static String getValue(String key){
		return p.get(key).toString();
	}

}
