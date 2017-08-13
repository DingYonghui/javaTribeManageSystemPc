package com.dyh.javaTribeManSys.pojo;

import java.io.Serializable;

/**
 * 封装成员信息的类，实体类
 */
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4555481390652800296L;
	
	private String id ;
	private String password ;
	private String name ;
	private String headImage ;
	private String sex ;
	private String grade ;
	private String department ;
	private String phone ;
	private String QQ ;
	private String sign ;
	private String birthday ;	
	private String adress ;
	private String isManager;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHeadImage() {
		return headImage;
	}
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String QQ) {
		this.QQ = QQ;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIsManager() {
		return isManager;
	}
	public void setIsManager(String isManager) {
		this.isManager = isManager;
	}
	
}
