package org.toast.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {
	
	private BufferedImage[] images;
	private ClickListener clicker;
	

	public UIImageButton(float x, float y, int width, int height, BufferedImage[] images,ClickListener clicker) {
		super(x, y, width, height);
		this.setImages(images);
		this.clicker = clicker;
	}


	public void tick() {

		
	}


	public void render(Graphics g) {
		if(hovering)
			g.drawImage(images[0], (int)x, (int)y,width,height, null);
		else
			g.drawImage(images[1], (int)x, (int)y,width,height, null);
		
	}


	public void onClick() {
		clicker.onClick();
		//hovering = false;
	}

	
	
	////////////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////////////////////////
	
	public BufferedImage[] getImages() {
		return images;
	}
	public void setImages(BufferedImage[] images) {
		this.images = images;
	}
	
	
	
	
	
	
	
}
