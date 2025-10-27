package org.toast;

import org.toast.display.Display;
import org.toast.gfx.GameCamera;
import org.toast.input.KeyManager;
import org.toast.input.MouseManager;
import org.toast.states.State;
import org.toast.states.SinglePlayer.SinglePlayerGameState;
import org.toast.worlds.World;

public class Handler {

	private Game game;
	private World world;
	private boolean inventoryHover = true;

	public Handler(Game game) {
		this.game = game;
	}

	public int getWidth() {
		return getDisplay().getFrame().getWidth();
	}
	public int getHeight() {
		return getDisplay().getFrame().getHeight();
	}
	
	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}
	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}
	public GameCamera getGameCamera() {
		return game.getGameCamera();
	}
	public Display getDisplay() {
		return game.getDisplay();
	}

	
	
	

	// STATES
	public SinglePlayerGameState getGameState() {
		return game.getGameState();
	}
	public State getMenuState() {
		return game.getMenuState();
	}
	public State getSettingsState() {
		return game.getSettingsState();
	}
	public State getSinglePlayerState() {
		return game.getSinglePlayerState();
	}
	public State getMultiplayerState() {
		return game.getMultiplayerState();
	}
	public State getMultiplayerGameState() {
		return game.getMultiplayerGameState();
	}
	public State getAchievementState() {
		return game.getAchievementState();
	}
	
	public Game getGame() {
		return game;
	}
	public World getWorld() {
		return world;
	}
	public void setWorld(World world) {
		this.world = world;
	}
	
	public float getGuiScale() {
		return 1.5f;
	}
	public boolean isInventoryHover() {
		return inventoryHover;
	}
	public void setInventoryHover(boolean inventoryHover) {
		this.inventoryHover = inventoryHover;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
