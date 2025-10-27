package org.toast.states.SinglePlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.toast.Handler;
import org.toast.entities.HUD.inventory.Inventory;
import org.toast.entities.mobs.Player;
import org.toast.gfx.Assets;
import org.toast.states.GameStateManager;
import org.toast.states.State;
import org.toast.ui.ClickListener;
import org.toast.ui.UIImageButton;
import org.toast.ui.UIManager;
import org.toast.worlds.Chunk;
import org.toast.worlds.World;

public class SinglePlayerGameState extends State{
	
	public boolean debug = false;
	public static boolean escape = false;
	boolean toggle = false,toggle1 = false,toggle2 = false;
	
	private int buttonWidth = handler.getWidth() / 5;
	private int buttonHeight = handler.getHeight() / 18;
	
	
	private UIManager gameUIManager;
	private UIManager escapeUIManager;
	
	//Stuff the GameState needs
	private World currentWorld;
	public Player player;
	
	
	public SinglePlayerGameState(Handler handler) {
		super(handler);
		gameUIManager  = new UIManager(handler);
		escapeUIManager = new UIManager(handler);
		
		escapeUIManager.addObject(new UIImageButton(handler.getWidth() / 5, handler.getHeight() / 5, buttonWidth, buttonHeight, Assets.buttons, new ClickListener() {

			@Override
			public void onClick() {
				GameStateManager.setState(handler.getMenuState());
				escape = false;
			}
		}));
		escapeUIManager.addObject(new UIImageButton(handler.getWidth() / 5, handler.getHeight() / 5 + 100, buttonWidth, buttonHeight, Assets.buttons, new ClickListener() {

			@Override
			public void onClick() {
				GameStateManager.setState(handler.getSettingsState());
			}
		}));
		escapeUIManager.addObject(new UIImageButton(handler.getWidth() / 5 + (handler.getWidth() / 3), handler.getHeight() / 5, buttonWidth, buttonHeight, Assets.buttons, new ClickListener() {

			@Override
			public void onClick() {
				GameStateManager.setState(handler.getAchievementState()); 
			}
		}));


		
		
		
	}
	
	
	public void tick() {
		if(handler.getMouseManager().getUIManager() != gameUIManager)
			handler.getMouseManager().setUIManager(gameUIManager);
		if(currentWorld == null) {
			GameStateManager.setState(handler.getMenuState());
			System.out.println("There Was A Problem Loading the World!");
		}
		
			
		handler.getKeyManager().tick();
		
		
		if(handler.getKeyManager().l) {
			handler.getWorld().setTime(handler.getWorld().getTime() + 9); 
		}

		
		
		if(handler.getKeyManager().escape && !toggle2) {
			escape = !escape; 
			toggle2 = true;
		}else if(!handler.getKeyManager().escape) {
			toggle2 = false;
		}
		
		if(escape) {
			if(handler.getMouseManager().getUIManager() != escapeUIManager)
				handler.getMouseManager().setUIManager(escapeUIManager);
			 
			escapeUIManager.tick();
			
			
		}else {
			
			
			
			togglablesandControls();
			
		}
		
		
		
		
		
		
		
		
	}
	public void render(Graphics g) {
		currentWorld.render(g); 		//The Player is now rendered with the entities, and the world
		
		//we must render these here or they would get rendered before entities they come after player, so trees and cactus clip through
		player.getHUD().render(g); 
		if(player.InventoryOpen()) {
			player.getInventory().render(g);
		}
		
		
		if(debug) {
			renderDebugMenu(g);
		}
		if(escape) {
			renderPauseMenu(g); 
		}
		
	
	}
	
	
	
	
	
	public void renderDebugMenu(Graphics g) {
		g.setColor(Color.white);
		g.setFont(Assets.debug);
		
		int start = 70;
		int gap = 17;
		
		
		Point abs = getCurrentWorld().getAbsPos(handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset(), handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset());
		Chunk c = handler.getWorld().getChunk(handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset(),handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset());
		//Point rel = getCurrentWorld().getRelPos(handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset(), handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset());
		
		
		g.drawString("Spelunked! 0.7.3" , 50, start + (gap * 1));
		g.drawString("", 50, start + (gap * 2));
		g.drawString("FPS: " + handler.getGame().getFPS() + "        (" + (int)(player.getX()) + "," + (int)(player.getY()) + ")", 50, start + (gap * 3)); 
		g.drawString("E:(" + handler.getWorld().getEntityManager().getEntitiesRendered() + "/" + handler.getWorld().getEntityManager().getEntitiesUpdated() + "/" + handler.getWorld().getEntityManager().getEntities().size() + ")" , 50, start + (gap * 4)); 
		g.drawString("", 50, start + (gap * 5));
		g.drawString("abs (x,y): (" + abs.x + "," + abs.y + ")", 50, start + (gap * 6));
		g.drawString("Seed: " + handler.getWorld().getSeed(), 50, start + (gap * 7));
		g.drawString("Time: " + handler.getWorld().getTime(), 50, start + (gap * 8));
		g.drawString("Chunk: (" + c.getX() + "," + c.getY() + ")", 50, start + (gap * 9));
		g.drawString("Chunk Biome ID: " + c.getBiomeID(), 50, start + (gap * 10));
		g.drawString("", 50, start + (gap * 11));
		g.drawString("Player Velocity: " + player.getVelocity(), 50, start + (gap * 12));
		
		
	}
	public void renderPauseMenu(Graphics g) {
		g.setColor(Assets.tinted_black);
		g.fillRect(0, 0, handler.getWidth(), handler.getHeight());
		
		escapeUIManager.render(g);
		
		g.setColor(Color.WHITE);
		g.setFont(Assets.menu);
		
		//Menu Screen
		g.drawString("Menu Screen", handler.getWidth() / 4 + (buttonWidth / 2) - ("Menu Screen".length() * (Assets.menu.getSize() / 2)) - 20, handler.getHeight() / 5 + 35);
		//Settings Menu
		g.drawString("Settings", handler.getWidth() / 4 + (buttonWidth / 2) - ("Settings".length() * (Assets.menu.getSize() / 2)) - 30, handler.getHeight() / 5 + 135);
		//Achievements
		g.drawString("Achievements", handler.getWidth() / 4 + (buttonWidth / 2) - ("Achievements".length() * (Assets.menu.getSize() / 2)) - 10 + (handler.getWidth() / 3), handler.getHeight() / 5 + 35); 
	}
	
	
	boolean num1Toggle = false,num2Toggle = false,num3Toggle = false,
			num4Toggle = false,num5Toggle = false,num6Toggle = false,
			num7Toggle = false,num8Toggle = false,num9Toggle = false,
			num1ed = false,num2ed = false,num3ed = false,
			num4ed = false,num5ed = false,num6ed = false,
			num7ed = false,num8ed = false,num9ed = false;
	public void togglablesandControls() {
			
		

		
		currentWorld.tick();
		player.getHUD().tick();
		

		if(player.InventoryOpen()) {
			player.getInventory().tick();
			
			Inventory i = player.getInventory();
			int x = i.currentlyHoveredOnSlot.x;
			int y = i.currentlyHoveredOnSlot.y;
			if(num1ed) {
				i.swapInventorySlots(x, y, 0, 3); 
			}else if(handler.getKeyManager().num2 && num2ed) {
				i.swapInventorySlots(x, y, 1, 3); 
			}else if(handler.getKeyManager().num3 && num3ed) {
				i.swapInventorySlots(x, y, 2, 3); 
			}else if(handler.getKeyManager().num4 && num4ed) {
				i.swapInventorySlots(x, y, 3, 3); 
			}else if(handler.getKeyManager().num5 && num5ed) {
				i.swapInventorySlots(x, y, 4, 3); 
			}else if(handler.getKeyManager().num6 && num6ed) {
				i.swapInventorySlots(x, y, 5, 3); 
			}else if(handler.getKeyManager().num7 && num7ed) {
				i.swapInventorySlots(x, y, 6, 3); 
			}else if(handler.getKeyManager().num8 && num8ed) {
				i.swapInventorySlots(x, y, 7, 3); 
			}else if(handler.getKeyManager().num9 && num9ed) {
				i.swapInventorySlots(x, y, 8, 3); 
			}
			num1ed = false;
			num2ed = false;
			num3ed = false;
			num4ed = false;
			num5ed = false;
			num6ed = false;
			num7ed = false;
			num8ed = false;
			num9ed = false;
			ToggleNums();
			
			
			
		}else {
			if(handler.getKeyManager().num1) {
				player.getHUD().setSlotSelected(0);
			}else if(handler.getKeyManager().num2) {
				player.getHUD().setSlotSelected(1);
			}else if(handler.getKeyManager().num3) {
				player.getHUD().setSlotSelected(2);
			}else if(handler.getKeyManager().num4) {
				player.getHUD().setSlotSelected(3);
			}else if(handler.getKeyManager().num5) {
				player.getHUD().setSlotSelected(4);
			}else if(handler.getKeyManager().num6) {
				player.getHUD().setSlotSelected(5);
			}else if(handler.getKeyManager().num7) {
				player.getHUD().setSlotSelected(6);
			}else if(handler.getKeyManager().num8) {
				player.getHUD().setSlotSelected(7);
			}else if(handler.getKeyManager().num9) {
				player.getHUD().setSlotSelected(8);
			}
			
			num1Toggle = false;
			num2Toggle = false;
			num3Toggle = false;
			num4Toggle = false;
			num5Toggle = false;
			num6Toggle = false;
			num7Toggle = false;
			num8Toggle = false;
			num9Toggle = false;
			
			if(handler.getMouseManager().getMouseWheelScroll() != 0) {
				int newSlot = player.getHUD().getSlotSelected() - handler.getMouseManager().getMouseWheelScroll();
				
				if(newSlot < 0)
					newSlot = 8;
				if(newSlot > 8)
					newSlot = 0;
				
				player.getHUD().setSlotSelected(newSlot);
				handler.getMouseManager().resetMouseWheelScroll();
			}
			if(handler.getKeyManager().t) {
				player.setX(handler.getWorld().getSpawnX());
				player.setY(handler.getWorld().getSpawnY());
				player.setVelocity(0.0f);
			}
			
			
		}

		
		
		
		
		//////////////////////////////////////////////////////////////////
		if(handler.getKeyManager().F3 && !toggle) {
			debug = !debug;
			toggle = true;
		}else if(!handler.getKeyManager().F3) {
			toggle = false;
		}
		//////////////////////////////////////////////////////////////////
		if(handler.getKeyManager().F11 && !toggle1) {
			handler.getDisplay().setFullscreen(!handler.getDisplay().getFullscreen()); 
			toggle1 = true;
		}else if(!handler.getKeyManager().F11) {
			toggle1 = false;
		}
		/////////////////////////////////////////////////////////////////


		
		

		
		
		
	
	
	
	
		
		
		
			
	}
	private void ToggleNums() {
		if(handler.getKeyManager().num1 && !num1Toggle) {
			num1ed = !num1ed;
			num1Toggle = true;
		}else if(!handler.getKeyManager().num1) {
			num1Toggle = false;
		}
		///////////////////////////////////////////////////////
		if(handler.getKeyManager().num2 && !num2Toggle) {
			num2ed = !num2ed;
			num2Toggle = true;
		}else if(!handler.getKeyManager().num2) {
			num2Toggle = false;
		}
		///////////////////////////////////////////////////////
		if(handler.getKeyManager().num3 && !num3Toggle) {
			num3ed = !num3ed;
			num3Toggle = true;
		}else if(!handler.getKeyManager().num3) {
			num3Toggle = false;
		}
		///////////////////////////////////////////////////////
		if(handler.getKeyManager().num4 && !num4Toggle) {
			num4ed = !num4ed;
			num4Toggle = true;
		}else if(!handler.getKeyManager().num4) {
			num4Toggle = false;
		}
		///////////////////////////////////////////////////////
		if(handler.getKeyManager().num5 && !num5Toggle) {
			num5ed = !num5ed;
			num5Toggle = true;
		}else if(!handler.getKeyManager().num5) {
			num5Toggle = false;
		}
		///////////////////////////////////////////////////////
		if(handler.getKeyManager().num6 && !num6Toggle) {
			num6ed = !num6ed;
			num6Toggle = true;
		}else if(!handler.getKeyManager().num6) {
			num6Toggle = false;
		}
		///////////////////////////////////////////////////////
		if(handler.getKeyManager().num7 && !num7Toggle) {
			num7ed = !num7ed;
			num7Toggle = true;
		}else if(!handler.getKeyManager().num7) {
			num7Toggle = false;
		}
		///////////////////////////////////////////////////////
		if(handler.getKeyManager().num8 && !num8Toggle) {
			num8ed = !num8ed;
			num8Toggle = true;
		}else if(!handler.getKeyManager().num8) {
			num8Toggle = false;
		}
		///////////////////////////////////////////////////////
		if(handler.getKeyManager().num9 && !num9Toggle) {
			num9ed = !num9ed;
			num9Toggle = true;
		}else if(!handler.getKeyManager().num9) {
			num9Toggle = false;
		}
		///////////////////////////////////////////////////////
		
	}
	/////////////////////////////////////////////////////////////////////GETTERS AND SETTERS/////////////////////////////////////////////////////////////////////
	public void setCurrentWorld(World world) {
		currentWorld = world;
	}
	public World getCurrentWorld() {
		return currentWorld;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	
	

}
