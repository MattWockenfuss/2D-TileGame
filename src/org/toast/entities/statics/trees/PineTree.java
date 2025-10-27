package org.toast.entities.statics.trees;

import java.awt.Color;
import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.entities.statics.StaticEntity;
import org.toast.gfx.Assets;
import org.toast.item.Item;
import org.toast.utils.Utils;

public class PineTree extends StaticEntity{
	
	private static int scale = 2; 
	
	public PineTree(Handler handler, float x, float y) {
		super(handler, x, y, Assets.trees[0].getWidth() * scale, Assets.trees[0].getHeight() * scale);
		//lets scale the tree
		bounds.x = 27 * scale;
		bounds.y = 57 * scale;
		bounds.width = 10 * scale;
		bounds.height = 30 * scale;
		isSolid = false;
		//hasGravity = false;
		
		
	}


	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.trees[0], (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width,height,null);
		if(handler.getGameState().debug) {
			g.setColor(Color.red); 
			g.fillRect((int)(x + bounds.x - handler.getGameCamera().getxOffset()),(int)(y + bounds.y - handler.getGameCamera().getyOffset()), bounds.width, bounds.height);
		}

	}
	
	
	public void spawnChildren() {
		int spread = 10;
		int centerX = (int)(this.getX() + this.getBounds().x + (this.getBounds().width / 2));
		int centerY = (int)(this.getY() + this.getBounds().y + (this.getBounds().height / 2));
		handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,Utils.randomNumber((int)(centerX - spread), (int)(centerX + spread)),
				Utils.randomNumber((int)(centerY - spread), (int)(centerY + spread)), Item.fir_log.getID() ,Utils.randomNumber(2, 4)));
		handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,Utils.randomNumber((int)(centerX - spread), (int)(centerX + spread)),
				Utils.randomNumber((int)(centerY - spread), (int)(centerY + spread)), Item.stick.getID() ,Utils.randomNumber(3, 6)));
	}
	
	

}
