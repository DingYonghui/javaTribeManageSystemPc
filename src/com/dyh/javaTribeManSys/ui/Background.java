package com.dyh.javaTribeManSys.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 背景面板类
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
	 * 构造方法，以int型设置背景图片大小和路径
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
	 * 构造方法，以double型设置背景图片大小和路径
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
	
	//重绘面板的方法
	public void paint(Graphics g){
		
		super.paint(g);
				
		File file = new File(filePath);	
		
		//检查文件是否存在
		if(!file.exists()){
			
			return;
			
		}else{
			
			try {
				//图片输入缓冲区
				BufferedImage im = ImageIO.read(file);
				g.drawImage(im,0,0, width, height, this);
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
		}
			
		
		
	}
	
	
}
