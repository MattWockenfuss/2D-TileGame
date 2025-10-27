package org.toast.blocks.animated;

import org.toast.gfx.Animation;

public class WaterBlock extends AnimatedBlock{
	
	public WaterBlock(int id, Animation animation) {
		super(animation.getCurrentFrame(), id, animation);
	}
	public boolean isSwimable() {
		return true;
	}
	
	

}
