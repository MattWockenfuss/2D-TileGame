package org.toast.entities.statics;

import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.gfx.Assets;
import org.toast.utils.Utils;

public class Cactus extends StaticEntity{
	
	////For shits and giggles i want to see how often i pen this class
	// 9-19-2020 9:40 AM
	//1 Times
	
	
	
	public Cactus(Handler handler, float x, float y) {
		super(handler, x, y, Assets.cactus.getWidth(), Assets.cactus.getHeight());
		
		bounds.x = 22;
		bounds.y = 36;
		bounds.width = 11;
		bounds.height = 47;
		
		isSolid = false;
		
		
	}


	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.cactus, (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width,height,null);
//		if(handler.getKeyManager().F3) {
//			g.setColor(Color.red); 
//			g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
//		}

	}
	
	
	public void spawnChildren() {
		int spread = 10;
		int centerX = (int)(this.getX() + this.getBounds().x + (this.getBounds().width / 2));
		int centerY = (int)(this.getY() + this.getBounds().y + (this.getBounds().height / 2));
		handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,Utils.randomNumber((int)(centerX - spread), (int)(centerX + spread)),
				Utils.randomNumber((int)(centerY - spread), (int)(centerY + spread)), 4 ,Utils.randomNumber(1, 3)));
	}
	

}
