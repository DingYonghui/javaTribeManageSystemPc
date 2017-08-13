package com.dyh.javaTribeManSys.config;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * ��ȡ�����ļ�����
 * @author ding
 *
 */
public class Config {
	private static Properties p = null;
	static {
		try{
			p = new Properties();
			//���������ļ�
			p.load(new FileInputStream("config/db.properties"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//��ȡ����Ӧ��ֵ
	public static String getValue(String key){
		return p.get(key).toString();
	}

}
