package org.toast.entities.statics;

import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.gfx.Assets;

public class Lettuce extends StaticEntity{
	
	private int stage = 0;
	private int life = 0;
	
	public Lettuce(Handler handler, float x, float y) {
		super(handler, x, y,Assets.lettuceCrop[0].getWidth(), Assets.lettuceCrop[0].getHeight());
		
		//We dont want it to have collision
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 0;
		bounds.height = 0;
		
		
		
	}


	public void tick() {
		//Make the plants able to grow
		if(stage != 2) {
			life++;
			if(life > 600) {
				stage++;
				life = 0;
			}
		}

		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.lettuceCrop[stage], (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width,height,null);
	}
	
	
	public void spawnChildren() {
		
		if(stage == 2) {//Only if fully grown
			int centerX = (int)(this.getX() + this.getBounds().x + (this.getBounds().width / 2));
			int centerY = (int)(this.getY() + this.getBounds().y + (this.getBounds().height / 2));
			handler.getWorld().getEntityManager().getEntities().add(
					new ItemEntity(handler,(float)centerX, (float)centerY, 54 ,2));
		}

	}
	
	
	
	
	
	


	
	
	/////////////////////////GETTERS AND SETTERS////////////////////////////////
	public int getStage() {
		return stage;
	}
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	
	
	
	
	
	
	

}
