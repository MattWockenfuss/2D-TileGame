package org.toast.entities.statics;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.entities.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}
	
	public boolean isOnWater() {
		if(handler.getWorld().getBlock((int) (x + bounds.x  + (bounds.width / 2)) / Block.BLOCKWIDTH,(int) ((y + bounds.y + (bounds.height / 2)) / Block.BLOCKHEIGHT)).isSwimable()) {
			return true;
		}else {
			return false;
		}
	}
	
	

	
	
	
}
