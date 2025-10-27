package org.toast.states.SinglePlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;

import org.toast.Handler;
import org.toast.gfx.Assets;
import org.toast.states.GameStateManager;
import org.toast.states.State;
import org.toast.ui.ClickListener;
import org.toast.ui.UIWorldButton;
import org.toast.worlds.World;
import org.toast.ui.UIImageButton;
import org.toast.ui.UIManager;

public class SinglePlayerState extends State{
	
	private UIManager SinglePlayerUIManager;
	
	//UI Elements
	private int xMargin = handler.getWidth() / 6 * 2;
	private int yMargin = 0;
	private int WorldButtonWidth = handler.getWidth() / 3 - 10;
	private int WorldButtonHeight = handler.getHeight() / 8;
	
	private int buttonWidth = handler.getWidth() / 4;
	private int buttonHeight = handler.getHeight() / 17;
	
	
	//World Search
	private File folder;
	private File[] worldfolders;

	

	public SinglePlayerState(Handler handler) {
		super(handler);
		SinglePlayerUIManager = new UIManager(handler);

		
		folder = new File("res/Worlds");
		worldfolders = folder.listFiles(File::isDirectory);
		if(worldfolders != null && worldfolders.length > 0) {
			
			for(int i = 0;i < worldfolders.length;i++) {	//LOop through all world folders
				yMargin = 61 + (WorldButtonHeight) * i; 
				SinglePlayerUIManager.addObject(new UIWorldButton(handler, xMargin, yMargin, WorldButtonWidth, WorldButtonHeight, worldfolders[i].getName(),new World(handler,"res/Worlds/" + worldfolders[i].getName()))); 
			}
			
		}

		
		SinglePlayerUIManager.addObject(new UIImageButton((handler.getWidth() / 2 - buttonWidth / 2),(handler.getHeight() - 90),buttonWidth,buttonHeight,Assets.buttons,new ClickListener() {
			
			public void onClick() {
				GameStateManager.setState(handler.getMenuState()); 
			}
			
		}));
		SinglePlayerUIManager.addObject(new UIImageButton((handler.getWidth() - (90 + buttonWidth)),(handler.getHeight() - 90),buttonWidth / 6 * 5,buttonHeight,Assets.buttons,new ClickListener() {
			
			public void onClick() {
				createNewWorld(); 
			}
			
		}));

		
	}

	public void tick() {
		if (handler.getMouseManager().getUIManager() != SinglePlayerUIManager)
			handler.getMouseManager().setUIManager(SinglePlayerUIManager);
		SinglePlayerUIManager.tick();
		
	}
	public void render(Graphics g) {
		for(int y = 0;y < (handler.getHeight() / Assets.height) + 1;y++) {
			for(int x = 0;x < (handler.getWidth() / Assets.width);x++) {
				g.drawImage(Assets.sandstone, x * Assets.width, y * Assets.height, null);
			}
		}
		
		g.setColor(Color.black);															//Render the tan box with edges
		g.fillRect(handler.getWidth() / 6 * 2 - 1, 0, handler.getWidth() / 3 - 8, handler.getHeight());
		g.setColor(Assets.tan);
		g.fillRect(handler.getWidth() / 6 * 2, 0, handler.getWidth() / 3 - 10, handler.getHeight());
		
		
		
		
		SinglePlayerUIManager.render(g);	
		
		
		
		
		g.setColor(Color.white);
		g.setFont(Assets.menu);
		g.drawString("Back", (handler.getWidth() / 2 - 30), handler.getHeight() - 52);
		g.drawString("Create New World", handler.getWidth() - (buttonWidth - 14), handler.getHeight() - 100 + buttonHeight - 13); 
		
		g.setColor(Color.black);															//Render the title
		g.setFont(Assets.newMenu);
		g.drawString("Worlds", handler.getWidth() / 2 - ("Worlds".length() * 30 / 2), 50);
		
		g.fillRect(handler.getWidth() / 6 * 2, 60, handler.getWidth() / 3 - 10, 2); 
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void createNewWorld() {
		System.out.println("Creating New World!"); 
	}
	
	
	
	
	
	
	
	
	
	
	

}
