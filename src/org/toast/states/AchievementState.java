package org.toast.states;

import java.awt.Color;
import java.awt.Graphics;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.gfx.Assets;
import org.toast.ui.ClickListener;
import org.toast.ui.UIImageButton;
import org.toast.ui.UIManager;



public class AchievementState extends State{

	private UIManager AchievementsUIManager;
	
	private int buttonWidth = handler.getWidth() / 4;
	private int buttonHeight = handler.getHeight() / 17;
	
	public AchievementState(Handler handler) {
		super(handler);
		
		AchievementsUIManager = new UIManager(handler);
		
		AchievementsUIManager.addObject(new UIImageButton((handler.getWidth() / 2 - buttonWidth / 2),(handler.getHeight() - 90),buttonWidth,buttonHeight,Assets.buttons,new ClickListener() {
			
			public void onClick() {
				if(GameStateManager.getLastState() != null)
				GameStateManager.setState(GameStateManager.getLastState()); 
			}
			
		}));
		
		
		
	}

	@Override
	public void tick() {
		if(handler.getMouseManager().getUIManager() != AchievementsUIManager)
			handler.getMouseManager().setUIManager(AchievementsUIManager);
		
		AchievementsUIManager.tick();
		
		if(handler.getKeyManager().escape) {
			GameStateManager.setState(GameStateManager.getLastState()); 
		}
	}

	@Override
	public void render(Graphics g) {
		
		for(int y = 0;y < (handler.getHeight() / Block.BLOCKHEIGHT) + 1;y++) {
			for(int x = 0;x < (handler.getWidth() / 2 / Block.BLOCKWIDTH);x++) {
				g.drawImage(Assets.avacado, (handler.getWidth() / 2) + (x * Block.BLOCKWIDTH), y * Block.BLOCKHEIGHT, null);
			}
		}
 
		
		
		AchievementsUIManager.render(g);

		
		
		g.setColor(Color.black);
		g.drawString("ha", 100, 100);
		g.setFont(Assets.achievementLOL); 
		g.drawString("You can Breathe", 100, 400);
		
		
		g.setFont(Assets.menu);
		g.setColor(Color.white); 
		g.drawString("Back", (handler.getWidth() / 2 - 30), handler.getHeight() - 57);
		
		
		
		
		
		
		
		
	}
	
	
	
	

}
