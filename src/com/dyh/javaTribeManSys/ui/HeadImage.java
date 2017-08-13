package com.dyh.javaTribeManSys.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * ͷ����Ƭ�����
 */	
class HeadImage extends JPanel{
	   	
	private static final long serialVersionUID = 1055217783738198136L;

	String path ; 
	
	HeadImage(String path){
		this.path = path ; 
	}
	
	//�ػ����ķ���
	public void paint(Graphics g){
			
		super.paint(g);
		System.out.println("ͼ��·��Ϊ��"+path);		
		File file = new File(path);
		
		
		//����ļ��Ƿ����
		if(!file.exists() && !new File("./res/headImage/"+PanAdd.txtId.getText().trim()+".jpg").exists()){
			System.out.println("----->>"+"./res/headImage/"+PanAdd.txtId.getText().trim()+".jpg");
			try {
				//ͼƬ���뻺����
				BufferedImage im = ImageIO.read(new File("./res/headImage/noHeadImage.jpg"));
				g.drawImage(im,4,0, 160, 180, null);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
		}else if( file.exists() ){
			try {
				//ͼƬ���뻺����
				BufferedImage im = ImageIO.read(file);
				g.drawImage(im,4,0, 160, 180, null);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
			
		
		
	}
	
	
}
