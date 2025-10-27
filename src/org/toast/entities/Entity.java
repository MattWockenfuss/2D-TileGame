package org.toast.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.entities.mobs.Player;

public abstract class Entity {
	
	protected Handler handler;
	protected float x, y;
	protected int width,height;
	protected Rectangle bounds;
	protected boolean active = true;
	protected boolean rendered = false;
	protected boolean dropItemsOnDeath = true;
	protected boolean hasGravity = true;
	protected boolean isSolid = true;
	
	//physics (for Gravity)
	
	protected float speed;
	public static final float DEFAULT_SPEED = 3.0F;
	protected float xMove,yMove;
	
	
	protected float velocity;
	protected final static float ACCELERATION = 0.15f;
	protected final static float TERMINAL_VELOCITY = 30;
	
	public Entity(Handler handler,float x,float y,int width,int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(0,0,width,height);
		
		//it would be cool if later, depending on the size of the object, acceleration is larger
		//acceleration = .25f;
		
		
		
		
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	
	
	
	
	
	
	
	
	
	
	
	public void move() {
		if(hasGravity)
			applyGravity();
		
		

		if(!checkEntityCollision(0f,yMove))
			moveY();
		if(!checkEntityCollision(xMove,0f))
			moveX();
		
		
		
		
	}
	public void moveX() {
		if(xMove > 0) {//Moving Right
			int dx = (int) (x + xMove + bounds.x + bounds.width);// the place we want to move to
			if(!collisionWithTile(dx,(int)(y + bounds.y)) && !collisionWithTile(dx,(int)(y + bounds.y + bounds.height))) {
				x+=xMove;
			}else {
				x = handler.getWorld().getAbsPos(dx, y).x * Block.BLOCKWIDTH - (bounds.width + bounds.x + 1);
			}
		}else if(xMove < 0) {//Moving Left
			int dx = (int) (x + xMove + bounds.x); 
			if(!collisionWithTile(dx,(int)(y + bounds.y)) && !collisionWithTile(dx,(int)(y + bounds.y + bounds.height))) {
				x+=xMove;
			}else {
				x = (handler.getWorld().getAbsPos(dx, y).x * Block.BLOCKWIDTH) + Block.BLOCKWIDTH - (bounds.x - 1);
			}
		}
		
		//old collision
		/*
		 * if(xMove > 0) {//Moving Right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;
			if(!collisionWithTile(tx,(int)(y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx,(int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x+=xMove;
			}else {
				x = tx * Tile.TILEWIDTH - bounds.width - bounds.x - 1; 
			}
		}else if(xMove < 0) {//Moving Left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH; 
			if(!collisionWithTile(tx,(int)(y + bounds.y) / Tile.TILEHEIGHT) && !collisionWithTile(tx,(int)(y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x+=xMove;
			}else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
		 */
		
		
		
	}
	public void moveY() { 
		
		if(yMove > 0) {//Down
			//COLLISION ABOVE CHUNK 0 on Y SEEMS FIXED NOW BUT IM NOT SURE WHAT I DID
			
			if(this instanceof ItemEntity) {
				int dy = (int)(y + yMove + bounds.y + 32);
				if(!collisionWithTile((int)(x + bounds.x + (bounds.width / 2)), dy)) {
					y += yMove;
				}else {
					y = (handler.getWorld().getAbsPos(x, dy).y * Block.BLOCKHEIGHT) - (32 + bounds.y + 1);
				}
			}else {////////////////////////////////ENTITIES FALLING
				int dy = (int)(y + yMove + bounds.y + bounds.height);
				if(!collisionWithTile((int)(x + bounds.x), dy) && !collisionWithTile((int)(x + bounds.x + bounds.width), dy)) {
					y += yMove;
					y = (int) y;
				}else {
					//The problem is when we set our new coords in a negative area
					//we cast to an int because i want to make sure the y value is .0
					y = (int)((handler.getWorld().getAbsPos(x, dy).y * Block.BLOCKHEIGHT) - (bounds.height + bounds.y + 1)); 
//					if(this instanceof Player) {
//						System.out.println("------------------------");
//						System.out.println("dy: " + dy);
//						System.out.println("yBlock: " + handler.getWorld().getAbsPos(x, dy).y);
//						System.out.println("yBlock * 48 - (bounds + 1) : " + (int)((handler.getWorld().getAbsPos(x, dy).y * Block.BLOCKHEIGHT) - (bounds.height + bounds.y + 1)));
//					}
				}
			}
			
		}else if(yMove < 0) {//Up
			int dy = (int)(y + yMove + bounds.y);
			if(!collisionWithTile((int)(x + bounds.x), dy) && !collisionWithTile((int)(x + bounds.x + bounds.width), dy)) {
				y += yMove;
			}else {
				y = (handler.getWorld().getAbsPos(x, dy).y * Block.BLOCKHEIGHT + Block.BLOCKHEIGHT) - (bounds.height);
			}
		}
		
	}
	public void applyGravity() {
		
		
		Block middle;
		
		if(this instanceof ItemEntity) {
			//only check middle for itemEntities
			if(y > 0) {												//bounds.width / 2
				middle = handler.getWorld().getBlock(x + bounds.x + 16, (y + bounds.y + 32 + 2));					//BELOW ENTITY
			}else {
				middle = handler.getWorld().getBlock(x + bounds.x + 16, (y + bounds.y + 32 + 2));					//BELOW ENTITY
			}
			
			if(!middle.isSolid()) {
				velocity += ACCELERATION;
				if(velocity > TERMINAL_VELOCITY)
					velocity = TERMINAL_VELOCITY;	
			}
			if(middle.isSolid() && velocity > 0) {//if we are going to hit the ground on either side and we are falling
				velocity = 0;
			}
			
			
			
			
		}else {
			Block leftSide;
			Block rightSide;
			if(y > 0) {
				leftSide = handler.getWorld().getBlock(x + bounds.x, (y + bounds.y + bounds.height + 2));					//BELOW ENTITY
				rightSide = handler.getWorld().getBlock(x + bounds.x + bounds.width, (y + bounds.y + bounds.height + 2));	//BELOW ENTITY
			}else {
				leftSide = handler.getWorld().getBlock(x + bounds.x, (y + bounds.y + bounds.height + 2));					//BELOW ENTITY
				rightSide = handler.getWorld().getBlock(x + bounds.x + bounds.width, (y + bounds.y + bounds.height + 2));	//BELOW ENTITY
			}
			

			
			
			if(!(leftSide.isSolid() && rightSide.isSolid())) {
				velocity += ACCELERATION;
				if(velocity > TERMINAL_VELOCITY)
					velocity = TERMINAL_VELOCITY;	
			}
			if((leftSide.isSolid() || rightSide.isSolid()) && velocity > 0) {//if we are going to hit the ground on either side and we are falling
				velocity = 0;
			}
		}
		
		

		

		
		

		
		//only do this if the current entity is solid
		if(checkEntityCollision(0f, velocity) && !(this instanceof ItemEntity))
			velocity = 0;
		
		
		yMove = velocity;
		
		
	}
	

	

	
	
	
	protected boolean collisionWithTile(int x,int y) {
		if(this instanceof Player) {
//			Point p = handler.getWorld().getAbsPos(x, y);
//			System.out.println("(" + p.x + "," + p.y + ") " + handler.getWorld().getBlock(x,y).getClass().getName());
		}
		return handler.getWorld().getBlock(x,y).isSolid();
	}
	public boolean checkEntityCollision(float xOffset,float yOffset) {
	
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.isSolid) {
				if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
					return true;
				}
			}		
		}
		return false;
	}
	public boolean checkEntityCollision(int x,int y,int width,int height) {
		//if the box supplied intercepts any entity, then return true
		Rectangle r = new Rectangle(x, y, width, height);
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.isSolid) {
				if(e.getCollisionBounds(0f, 0f).intersects(r))
					return true;
			}

		}
		return false;
	}
	public boolean checkPlayerCollision(int x,int y,int width,int height) {
		//if the box supplied intercepts any entity, then return true
		Rectangle r = new Rectangle(x, y, width, height);
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(this instanceof Player)
				if(e.getCollisionBounds(0f, 0f).intersects(r))
					return true;
		}
		return false;
	}
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void spawnChildren() {
		
	}
	
	
	
	
	//////////////////////////////////////////////GETTERS AND SETTERS///////////////////////////////////////////////////
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isHasGravity() {
		return hasGravity;
	}
	public void setHasGravity(boolean hasGravity) {
		this.hasGravity = hasGravity;
	}
	public boolean isSolid() {
		return isSolid;
	}
	public void setSolid(boolean isSolid) {
		this.isSolid = isSolid;
	}
	public boolean canDropItemsOnDeath() {
		return dropItemsOnDeath;
	}
	public void DropsItemsOnDeath(boolean dropItemsOnDeath) {
		this.dropItemsOnDeath = dropItemsOnDeath;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public float getxMove() {
		return xMove;
	}
	public void setxMove(float xMove) {
		this.xMove = xMove;
	}
	public float getyMove() {
		return yMove;
	}
	public void setyMove(float yMove) {
		this.yMove = yMove;
	}
	public float getVelocity() {
		return velocity;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	
	
	
	
}
