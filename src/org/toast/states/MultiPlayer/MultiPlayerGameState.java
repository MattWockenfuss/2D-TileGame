package org.toast.states.MultiPlayer;

import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.mobs.Player;
import org.toast.states.State;
import org.toast.worlds.World;

public class MultiPlayerGameState extends State{
	
	private World world;
	private Player player;
	
	
	
	
	

	public MultiPlayerGameState(Handler handler) {
		super(handler);
		//Dont initialize any of the multiplayer game state variables here, they are set as they are loaded in, hence the setworld method
		//Non player entities are already taken care of in the world class
		
		
		
		
		
	}

	public void tick() {
		if(world != null) {
			world.tick();
		}
		if(player != null) {
			player.tick();
		}
		
	}

	public void render(Graphics g) {
		if(world != null) {
			world.render(g); 
		}
		if(player != null) {
			player.render(g); 
		}
	}
	
	
	////////////////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setWorld(World w) {
		this.world = w;
	}
	public void setNewPlayer(float x,float y,int health) {
		player = new Player(handler,x,y,health);
	}
	
	
	
	
	

}
