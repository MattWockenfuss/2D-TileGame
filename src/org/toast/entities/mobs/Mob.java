package org.toast.entities.mobs;

import org.toast.Handler;
import org.toast.entities.Entity;

public abstract class Mob extends Entity{
	
	public static final int DEFAULT_HEALTH = 10;
	
	public static final int DEFAULT_CREATURE_WIDTH = 32;
	public static final int DEFAULT_CREATE_HEIGHT = 17;
	protected boolean isSwimming = false;

	protected int health;

	public Mob(Handler handler,float x, float y,int width,int height) {
		super(handler,x, y,width,height);
		this.health = DEFAULT_HEALTH;
	}


	
	
	
	
	
	
	
	
	
	/////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////
	

	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean getSwimming() {
		return isSwimming;
	}
	
	

}
