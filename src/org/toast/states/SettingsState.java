package org.toast.states;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.gfx.Assets;
import org.toast.ui.ClickListener;
import org.toast.ui.UIImageButton;
import org.toast.ui.UIManager;

public class SettingsState extends State{
	
	private UIManager SettingsUIManager;
	private int buttonWidth,buttonHeight;
	
	private String[] controls;
	

	public SettingsState(Handler handler) {
		super(handler);
		SettingsUIManager = new UIManager(handler);
		buttonWidth = handler.getWidth() / 4;
		buttonHeight = handler.getHeight() / 17;
		
		SettingsUIManager.addObject(new UIImageButton(100F,100F,buttonWidth,buttonHeight,Assets.buttons,new ClickListener() {
			
			public void onClick() {
				
				handler.getDisplay().setFullscreen(!handler.getDisplay().getFullscreen()); 
				
				
			}
			
		}));

		SettingsUIManager.addObject(new UIImageButton(100F,400F,buttonWidth,buttonHeight,Assets.buttons,new ClickListener() {
			
			public void onClick() {
				
				handler.setInventoryHover(!handler.isInventoryHover());
				
				
			}
			
		}));
		SettingsUIManager.addObject(new UIImageButton((handler.getWidth() / 2 - buttonWidth / 2),(handler.getHeight() - 90),buttonWidth,buttonHeight,Assets.buttons,new ClickListener() {
		
			public void onClick() {
				if(GameStateManager.getLastState() != null)
				GameStateManager.setState(GameStateManager.getLastState());
				
			}
			
			
			
		}));
		
		
		controls = new String[] {
				"                              Controls",
				"Escape - Opens Menu",
				"",
				"WASD - To move around",
				"Left Click - Mine Block",
				"Right CLick - Place Block",
				"E - Inventory",
				"F - Mines Static Entities(Trees, Crates)",
				"K - Lowers health",
				"Shift - to Sprint, CTLR - Super Sprint"
				
		};
		
		
	}


	public void tick() {
		if(handler.getMouseManager().getUIManager() != SettingsUIManager)
			handler.getMouseManager().setUIManager(SettingsUIManager);
		handler.getKeyManager().tick();
		SettingsUIManager.tick();
		if(handler.getKeyManager().escape) {
			GameStateManager.setState(GameStateManager.getLastState()); 
		}
		
	}
	public void render(Graphics g) {  
	
		
	//g.drawImage(Assets.castlebackground, 0, 0, null);
	
		
	for(int y = 0;y < (handler.getHeight() / Block.BLOCKHEIGHT) + 1;y++) {
		for(int x = 0;x < (handler.getWidth() / Block.BLOCKWIDTH) + 1;x++) {
			g.drawImage(Assets.stone[3], x * Block.BLOCKWIDTH, y * Block.BLOCKHEIGHT,Block.BLOCKWIDTH,Block.BLOCKHEIGHT, null);
		}
	}
	
		
	SettingsUIManager.render(g);
	g.setFont(Assets.menu);
	
	g.setColor(Color.WHITE);
	g.drawString("FullScreen",100 + "FullScreen".length() * (Assets.menu.getSize() / 2) + 50,100 + 35);
	g.drawString("Back", (handler.getWidth() / 2 - 30), handler.getHeight() - 57);
	g.drawString("HoverInventory: " + handler.isInventoryHover(), 100 + (buttonWidth / 2) - 75, 435);
	
	
	g.setColor(Color.BLACK);
	g.fillRect(handler.getWidth() - 622, 128,540,370 + (20 * controls.length) + 24);
	g.setColor(Color.white);
	g.fillRect(handler.getWidth() - 620, 130,536,370 + (20 * controls.length) + 20);
	
	g.setColor(Color.black); 
	g.setFont(Assets.updateLog);
	for(int i = 0;i < controls.length;i++) {
		g.drawString(controls[i], handler.getWidth() - 600, 150 + (24 * i)); 
	}
	
	
	
	}

	

}
