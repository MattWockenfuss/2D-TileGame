package org.toast.blocks.animated;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.toast.blocks.Block;
import org.toast.gfx.Animation;

public class AnimatedBlock extends Block{
	
	private Animation animation;
	
	
	public AnimatedBlock(BufferedImage texture, int id, Animation animation) {
		super(texture, id);
		this.animation = animation;
	}
	
	public void tick() {
		animation.tick();
	}
	public void render(Graphics g, int x,int y) {
		g.drawImage(animation.getCurrentFrame(), x, y, BLOCKWIDTH, BLOCKHEIGHT,null);
	}
	
	
	
	
	

}
