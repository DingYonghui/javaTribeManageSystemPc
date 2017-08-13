package com.dyh.javaTribeManSys.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * ���������
 */	
class Background extends JPanel{
	   		
	/**
	 * 
	 */
	private static final long serialVersionUID = 2321812395082817239L;
	private int width = 0 ;
	private int height = 0 ; 
	private String filePath ; 
	
	/**
	 * ���췽������int�����ñ���ͼƬ��С��·��
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public Background(int width,int height,String filePath){
		this.width = width ;
		this.height = height ; 
		this.filePath = filePath ;
		setSize(width,height);
		setVisible(true);
	}
	
	/**
	 * ���췽������double�����ñ���ͼƬ��С��·��
	 * @param width
	 * @param height
	 * @param imgPath
	 */
	public Background(double width,double height,String filePath){
		this.width = (int)width ;
		this.height = (int)height ; 
		this.filePath = filePath ;
		setSize((int)width,(int)height);
		setVisible(true);
	}
	
	//�ػ����ķ���
	public void paint(Graphics g){
		
		super.paint(g);
				
		File file = new File(filePath);	
		
		//����ļ��Ƿ����
		if(!file.exists()){
			
			return;
			
		}else{
			
			try {
				//ͼƬ���뻺����
				BufferedImage im = ImageIO.read(file);
				g.drawImage(im,0,0, width, height, this);
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
		}
			
		
		
	}
	
	
}
