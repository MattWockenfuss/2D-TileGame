package org.toast.entities.mobs;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.entities.Entity;
import org.toast.entities.HUD.HUD;
import org.toast.entities.HUD.inventory.Inventory;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.entities.statics.CraftingStation;
import org.toast.entities.statics.Crate;
import org.toast.entities.statics.Furnace;
import org.toast.entities.statics.Rock;
import org.toast.entities.statics.StaticEntity;
import org.toast.gfx.Animation;
import org.toast.gfx.Assets;
import org.toast.item.Item;
import org.toast.item.items.blockItems.BlockItem;
import org.toast.utils.Utils;

public class Player extends Mob{
	
	public static int maxHealth = 100;
	
	boolean toggle = false,InventoryOpen = false;
	private HUD hud;
	private Inventory inventory;
	
	//Animation
	private Animation left,right,down,up;
	private int animationSpeed = 300;
	public static BufferedImage currentAnimationState; //used so we can draw the player in the inventory
	
	
	//mining and placing
	private boolean canPlace = false;
	int timer = 0;
	long lastTime = System.currentTimeMillis();
	private Point lastPoint = new Point(-1000,-1000);
	
	
	public Player(Handler handler,float x, float y, int health) {
		super(handler,x, y, Assets.width * 2, Assets.height * 2);
		
		bounds.x = 10 * 2;
		bounds.y = 14 * 2;
		bounds.width = 12 * 2;
		bounds.height = 18 * 2;
		
		handler.getWorld().getEntityManager().addEntity(this);
		hud = new HUD(handler);
		inventory = hud.getInventory();
		this.setHealth(maxHealth);//We want the player to start at Max Health
		
		
		left = new Animation(animationSpeed, Assets.playerLeft);
		up = new Animation(animationSpeed, Assets.playerUp);
		right = new Animation(animationSpeed, Assets.playerRight);
		down = new Animation(animationSpeed, Assets.playerDown);
		
		
		
		
		
		
	}
	

	public void tick() {
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);

		
		controls();
		mineEntities();
		collectItems();
		
		hud.tick();
		
		


		



		
		
		
		

		
		
		if(getHealth() <= 0) {//Meaning that we died
			onDeath();
		}
		
	}
	public void render(Graphics g) {
		
		// draw the player
		g.drawImage(currentAnimationState, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), (int)(width),(int) (height), null);
		
		//render where we are going to place a block
		float mouseX = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();
		float mouseY = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();
		
		if(canPlace) {
			Point mouseAbsCoords = handler.getWorld().getAbsPos(mouseX, mouseY);
			g.setColor(Color.yellow);
			if(handler.getWorld().getBlock(mouseX, mouseY) != Block.airBlock)
				g.fillRect((int)(mouseAbsCoords.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset())
						,(int)(mouseAbsCoords.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH, Block.BLOCKHEIGHT);
		}

		
		
		//Draw the mining animation while mining
		//first check if its an air block
		
		Item i = null;
		if(inventory.getInventorySlot(hud.getSlotSelected(),3).getCurrentItemStack() != null) {
			i = inventory.getInventorySlot(hud.getSlotSelected(),3).getCurrentItemStack().getItem();
		}
		int strength = 1;
		if(i == null)
			strength = 1;
		else
			strength = i.getStrength();
		if(handler.getWorld().getBlock(mouseX, mouseY) != Block.airBlock && handler.getMouseManager().isLeftPressed()) {
			Point p = handler.getWorld().getAbsPos(mouseX,mouseY);
			
			
			
			if(timer >= (int) (handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / 11 * 10))
				g.drawImage(Assets.destroying[9],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 9))
				g.drawImage(Assets.destroying[8],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 8))
				g.drawImage(Assets.destroying[7],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 7))
				g.drawImage(Assets.destroying[6],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 6))
				g.drawImage(Assets.destroying[5],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 5))
				g.drawImage(Assets.destroying[4],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 4))
				g.drawImage(Assets.destroying[3],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 3))
				g.drawImage(Assets.destroying[2],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 2))
				g.drawImage(Assets.destroying[1],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			else if(timer >= (int) ((handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) / 11 * 1))
				g.drawImage(Assets.destroying[0],(int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
		}
		
		
		//Render HitBox
		if(handler.getGameState().debug) {
			//g.setColor(Color.red);
			//g.fillRect((int) (x - handler.getGameCamera().getxOffset()) + bounds.x,(int) (y - handler.getGameCamera().getyOffset()) + bounds.y, bounds.width, bounds.height);
			
			//draw the hoverblock on cursor
			//handler.getWorld().getBlock(mouseX, mouseY).render(g, handler.getMouseManager().getMouseX(), handler.getMouseManager().getMouseY());
			
			
			Point p = handler.getWorld().getAbsPos(x + bounds.x, (y + bounds.y + bounds.height + 2));
			Point p2 = handler.getWorld().getAbsPos(x + bounds.x + bounds.width, (y + bounds.y + bounds.height + 2));
			//System.out.println((int)(y + bounds.y + bounds.height + 1)); 
			
			g.setColor(Color.yellow);
			g.drawRect((int)(p.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH, Block.BLOCKHEIGHT);  
			g.drawRect((int)(p2.x * Block.BLOCKWIDTH - handler.getGameCamera().getxOffset()), (int)(p2.y * Block.BLOCKHEIGHT - handler.getGameCamera().getyOffset()), Block.BLOCKWIDTH, Block.BLOCKHEIGHT); 
			
			
		}
		
		

		

		
		
	}

	
	
	
	////////////////Garbage Methods
	
	public void mineEntities() {
		if(handler.getKeyManager().f) {
			for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
				if(e instanceof StaticEntity) { 
					
					int centerX = (int)(e.getX() + e.getBounds().x + (e.getBounds().width / 2));
					int centerY = (int)(e.getY() + e.getBounds().y + (e.getBounds().height / 2));
					
					int playerCenterX = (int)(x + bounds.x + (bounds.width / 2));
					int playerCenterY = (int)(y + bounds.y + (bounds.height / 2));
					
					if(e instanceof Rock){						//Rocks can't be mined from the side, so give more tolerance for x if Rock
						if(Math.abs(playerCenterY - centerY) < 50) {
							if(Math.abs(playerCenterX - centerX) < 60) {
								//Then mine the object
								e.setActive(false); 
							}
						}
						
					}else {
						if(Math.abs(playerCenterY - centerY) < 50) {
							if(Math.abs(playerCenterX - centerX) < 40) {
								//Then mine the object
								e.setActive(false); 
							}
						}
					}
					

					
					
					
				}
			}
		}
		

		
		
		Item i = null;
		if(inventory.getInventorySlot(hud.getSlotSelected(),3).getCurrentItemStack() != null) {
			i = inventory.getInventorySlot(hud.getSlotSelected(),3).getCurrentItemStack().getItem();
		}
		int strength = 1;
		if(i == null)
			strength = 1;
		else
			strength = i.getStrength();
		canPlace = false;
		
		float mouseX = handler.getMouseManager().getMouseX() + handler.getGameCamera().getxOffset();
		float mouseY = handler.getMouseManager().getMouseY() + handler.getGameCamera().getyOffset();
		Point p = handler.getWorld().getAbsPos(mouseX, mouseY);
		


		
		

		if(!checkPlayerCollision((int)(p.x * Block.BLOCKWIDTH), (int)(p.y * Block.BLOCKHEIGHT), Block.BLOCKWIDTH, Block.BLOCKHEIGHT) && !InventoryOpen) { 
			canPlace = true;
			if(handler.getMouseManager().isLeftPressed() && handler.getWorld().getBlock(mouseX, mouseY) != Block.airBlock) {
				timer += System.currentTimeMillis() - lastTime;
				lastTime = System.currentTimeMillis();
				if(timer >= handler.getWorld().getBlock(mouseX, mouseY).getBreakTime() / strength) {
					handler.getWorld().breakBlock(mouseX, mouseY);
					timer = 0;
				}
				
				
			}else if(i != null && handler.getMouseManager().isRightPressed() && handler.getWorld().getBlock(mouseX, mouseY) == Block.airBlock && i instanceof BlockItem){
				Block b = ((BlockItem) i).getBlock();
				handler.getWorld().setBlock(b, mouseX, mouseY);
				int Count = inventory.getInventorySlot(hud.getSlotSelected(),3).getCurrentItemStack().getCount() - 1;
				if(Count <= 0)
					inventory.getInventorySlot(hud.getSlotSelected(),3).setCurrentItemStack(null);
				else
					inventory.getInventorySlot(hud.getSlotSelected(),3).setCurrentItemStack(new ItemStack(i,Count));
			}
			
		}
		
		if(!handler.getMouseManager().isLeftPressed())
			timer = 0;

		//if we change blocks, then stop
		if(handler.getWorld().getAbsPos(mouseX, mouseY).getX() != lastPoint.getX() || handler.getWorld().getAbsPos(mouseX, mouseY).getY() != lastPoint.getY()) {
			timer = 0;
			lastPoint = handler.getWorld().getAbsPos(mouseX, mouseY);
		}
		
		
		
		//System.out.println("Timer: " + timer + " , LastTimer: " + lastTime); 
		lastTime = System.currentTimeMillis();

	}
	public void collectItems() {	
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof ItemEntity) { 
				
					if(Math.abs(y + bounds.y - e.getY()) < 45) {
						if(Math.abs(x + bounds.x - e.getX()) < 45) {
						
						
						 //Then pick up the Item
						ItemStack is = new ItemStack(Item.items[((ItemEntity) e).getItemID()],((ItemEntity) e).getItemStackCount());
						if(inventory.canAddItemStack(is) >= is.getCount()) {
							e.setActive(false);
							inventory.addItemStack(is);
						}
						

						

					}
				}
					
					
			}
		}
	}
	public void controls() {
		
		if(handler.getKeyManager().shift) {//Sprint
			speed = 4.5f;
			animationSpeed = 150; 
			
		}else if(handler.getKeyManager().left_control){//The Super Sprint button
			speed = 20.0f;
			animationSpeed = 50; 
			
		}else {
			speed = Entity.DEFAULT_SPEED;
			animationSpeed = 300; 
		}
		
		
		left.setSpeed(animationSpeed);
		right.setSpeed(animationSpeed);
		up.setSpeed(animationSpeed);
		down.setSpeed(animationSpeed);
		
		
		
		
		
		
		if(handler.getKeyManager().e && !toggle) {
			InventoryOpen = !InventoryOpen;
			toggle = true;
		}else if(!handler.getKeyManager().e) {
			toggle = false;
		}
		
		inventory.setCrate(null);
		inventory.setFurnace(null);
		inventory.setCraftingTable(null); 
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			int centerX = (int)(e.getX() + e.getBounds().x + (e.getBounds().width / 2));
			int centerY = (int)(e.getY() + e.getBounds().y + (e.getBounds().height / 2));
			
			int playerCenterX = (int)(x + bounds.x + (bounds.width / 2));
			int playerCenterY = (int)(y + bounds.y + (bounds.height / 2));
			
			if(Math.abs(playerCenterY - centerY) < Block.BLOCKHEIGHT * 2) {
				if(Math.abs(playerCenterX - centerX) < Block.BLOCKWIDTH * 2) {
					
					if(e instanceof Crate) { 
						inventory.setCrate((Crate) e);
					}else if(e instanceof CraftingStation) {
						inventory.setCraftingTable((CraftingStation) e);
					}else if(e instanceof Furnace) {
						inventory.setFurnace((Furnace) e);
					}
					
					
					
				}
			}
			
		}
		

		
		
		if(handler.getKeyManager().k) {
			health -= 1;
		}
		
		if(velocity == 0) {//if we are standing on a solid block
			if(handler.getKeyManager().space) {
				velocity = -4;
			}
			if(handler.getKeyManager().v) {
				velocity = -20;
			}
		}
		

		
		
		
		

	}
	private void getInput() {
		xMove = 0;
		yMove = 0;
		
//		if(handler.getKeyManager().w)
//			yMove = -speed;
//		if(handler.getKeyManager().s)
//			yMove = speed;
		if(handler.getKeyManager().a)
			xMove = -speed;
		if(handler.getKeyManager().d)
			xMove = speed;
		
		
		
		
		
		
		
		left.tick();
		up.tick();
		right.tick();
		down.tick();
		
		if(xMove < 0) {
			currentAnimationState = left.getCurrentFrame();
		}else if(xMove > 0) {
			currentAnimationState = right.getCurrentFrame();
		}else if(yMove < 0) {
			currentAnimationState = up.getCurrentFrame();
		}else if(yMove > 0){
			currentAnimationState = down.getCurrentFrame();
		}else {
			currentAnimationState = Assets.playerDown[1];
		}
		
	}
	private void onDeath() {
		int centerX = (int)(this.getX() + this.getBounds().x + (this.getBounds().width / 2));
		int centerY = (int)(this.getY() + this.getBounds().y + (this.getBounds().height / 2));
		InventoryOpen = false;
		
		if(!isSwimming) {			///if Im Swimming, than all my items would sink to the bottom of the ocean
			//Drop all the items in inventory
			for(int b = 0;b < Inventory.InventoryHeight;b++) {
				for(int a = 0;a < Inventory.InventoryWidth;a++) {
					int spread = Utils.randomNumber(-10, 10);
					if(inventory.getInventorySlot(a, b).getCurrentItemStack() != null) {
						handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,(float)centerX + spread, (float)centerY + spread, inventory.getInventorySlot(a, b).getCurrentItemStack().getItem().getID() ,inventory.getInventorySlot(a, b).getCurrentItemStack().getCount()));
						System.out.println("added Item to World!"); 
					}
					
					  
				}
			}
			if(inventory.getCurrentItemStackInHand() != null) {
				handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,(float)centerX, (float)centerY, inventory.getCurrentItemStackInHand().getItem().getID() ,inventory.getCurrentItemStackInHand().getCount()));
			}
			
			//Add the ones for the armor later here.....
			
		}

		inventory.clearInventory();
		
		System.out.println("Player Died!");
		
		
		this.x = handler.getWorld().getSpawnX();
		this.y = handler.getWorld().getSpawnY();
		
		this.health = maxHealth;
		
	}
	
//////////////////////////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////////
	public boolean InventoryOpen() {
		return InventoryOpen;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public HUD getHUD() {
		return hud;
	}


	
}
