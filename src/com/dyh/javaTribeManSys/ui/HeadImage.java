package com.dyh.javaTribeManSys.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * 头像照片面板类
 */	
class HeadImage extends JPanel{
	   	
	private static final long serialVersionUID = 1055217783738198136L;

	String path ; 
	
	HeadImage(String path){
		this.path = path ; 
	}
	
	//重绘面板的方法
	public void paint(Graphics g){
			
		super.paint(g);
		System.out.println("图像路径为："+path);		
		File file = new File(path);
		
		
		//检查文件是否存在
		if(!file.exists() && !new File("./res/headImage/"+PanAdd.txtId.getText().trim()+".jpg").exists()){
			System.out.println("----->>"+"./res/headImage/"+PanAdd.txtId.getText().trim()+".jpg");
			try {
				//图片输入缓冲区
				BufferedImage im = ImageIO.read(new File("./res/headImage/noHeadImage.jpg"));
				g.drawImage(im,4,0, 160, 180, null);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}else if( file.exists() ){
			try {
				//图片输入缓冲区
				BufferedImage im = ImageIO.read(file);
				g.drawImage(im,4,0, 160, 180, null);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
			
		
		
	}
	
	
}
