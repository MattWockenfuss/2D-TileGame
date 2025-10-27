package org.toast.ui;

import java.awt.Color;
import java.awt.Graphics;

import org.toast.Handler;
import org.toast.gfx.Assets;
import org.toast.states.GameStateManager;
import org.toast.worlds.World;

public class UIWorldButton extends UIImageButton{
	
	private World w;
	
	public UIWorldButton(Handler handler,float x, float y, int width, int height,String name,World w) {
		super(x, y, width, height, null, new ClickListener() {

			@Override
			public void onClick() {
				w.loadWorld("res/Worlds/" + name); 
				handler.getGameState().setCurrentWorld(w);  
				GameStateManager.setState(handler.getGameState()); 
			}
			
			
			
		});
		
		w.menuScreenLoad();
		this.w = w;
		
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.setFont(Assets.menu); 
		g.drawString(w.getName(), (int)(x + 10), (int)(y + 20));
		g.drawString("Seed: " + w.getSeed(), (int)(x + 15), (int)(y + 50));
		g.drawString("SpawnPoint: (" + w.getSpawnX() + ", " + w.getSpawnY() + ")" , (int)(x + 15), (int)(y + 80));
		
		
		g.fillRect((int)x, (int)(y + height - 1), width, 2); 
		
		
		
		
		
		
	}
	
	

}
