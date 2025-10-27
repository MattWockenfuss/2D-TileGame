package org.toast.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed,index;
	private long lastTime;
	private int timer;
	
	
	private BufferedImage[] frames;
	
	public Animation(int speed,BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		index = 0;
		timer = 0;
		lastTime = System.currentTimeMillis();
	}
	
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed) {
			index++;
			timer = 0; 
			if(index >= frames.length)
				index = 0;
		}
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	
	public BufferedImage getCurrentFrame() {
		return frames[index];
	}
	
	
	
	
}
