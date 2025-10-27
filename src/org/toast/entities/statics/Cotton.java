package org.toast.entities.statics;

import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.gfx.Assets;

public class Cotton extends StaticEntity{
	
	private int stage = 0;
	private int life = 0;
	
	
	public Cotton(Handler handler, float x, float y) {
		super(handler, x, y,Assets.cottonCrop[0].getWidth(), Assets.cottonCrop[0].getHeight());
		
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
			if(life > 300) {
				stage++;
				life = 0;
			}
		}

		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.cottonCrop[stage], (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width,height,null);
	}
	
	
	public void spawnChildren() {
		
		if(stage == 2) {//Only if fully grown
			int centerX = (int)(this.getX() + this.getBounds().x + (this.getBounds().width / 2));
			int centerY = (int)(this.getY() + this.getBounds().y + (this.getBounds().height / 2));
			handler.getWorld().getEntityManager().getEntities().add(
					new ItemEntity(handler,(float)centerX, (float)centerY, 53 ,2));
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
