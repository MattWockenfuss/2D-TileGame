package org.toast.entities.statics;

import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.HUD.inventory.InventorySlot;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.gfx.Assets;
import org.toast.utils.Utils;

public class Crate extends StaticEntity{
	
	public static final int containerWidth = 9,containerHeight = 6;
	private InventorySlot[][] contents;
	
		
	public Crate(Handler handler, float x, float y) {
		super(handler, x, y,Assets.crate.getWidth(), Assets.crate.getHeight());
		
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 32;
		bounds.height = 32;
		
		contents = new InventorySlot[containerWidth][containerHeight];
		for(int b = 0;b < containerHeight;b++) {
			for(int a = 0;a < containerWidth;a++) {
				contents[a][b] = new InventorySlot();
				  
			}
		}

		//populates the crate with random garbage
//		for(int b = 0;b < 3;b++) {
//			for(int a = 0;a < containerWidth;a++) {
//				Item i = Item.items[Utils.randomNumber(1, 50)];
//				contents[a][b].setCurrentItemStack(new ItemStack(i,Utils.randomNumber(1, i.getMaxStackSize())));
//			}
//		}
		
		
		
	}


	public void tick() {

	}
	public void render(Graphics g) {
		g.drawImage(Assets.crate, (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width,height,null);
	}
	
	
	public void spawnChildren() {
		
		int centerX = (int) this.getX();
		int centerY = (int)(this.getY() - this.getWidth() / 2); // So the items spawn "above" it so they dont leak into ground
		
		
		handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,(float)centerX, (float)centerY, 56 ,1));
		
		//Drop all the items inside
		for(int b = 0;b < containerHeight;b++) {
			for(int a = 0;a < containerWidth;a++) {
				int spread = Utils.randomNumber(-30, 30);
				if(contents[a][b].getCurrentItemStack() != null) {
					handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,(float)centerX + spread, (float)centerY,
							contents[a][b].getCurrentItemStack().getItem().getID() ,contents[a][b].getCurrentItemStack().getCount()));
				}
				
				  
			}
		}
			
	}
	
	
	
	
	
	



	
	
	
	
	
	
	public void addItemStack(ItemStack is) {
		
		//First thing we do is loop through our inventory, and see if any itemstacks already exist, and have space
		
		for(int b = 0;b < containerHeight;b++) {
			for(int a = 0;a < containerWidth;a++) {
					
				if(contents[a][b].getCurrentItemStack() != null) {
					
					if(contents[a][b].getCurrentItemStack().getItem().getID() == is.getItem().getID()) {//then they are the same item
						
						if(contents[a][b].getCurrentItemStack().getCount() + is.getCount() <= is.getItem().getMaxStackSize()) {//the itemstack will fit in this stack
							//then add them
							contents[a][b].getCurrentItemStack().setCount(contents[a][b].getCurrentItemStack().getCount() + is.getCount());
							return;
						}else {																										//the item stack wont fit in this stack
							
							if(contents[a][b].getCurrentItemStack().getCount() == is.getItem().getMaxStackSize()) {
								//the items have the same id,but this stack is full
							}else {
								//this stack isnt full, it just doesnt have enough space for everything
								int leftover = contents[a][b].getCurrentItemStack().getCount() + is.getCount() - is.getItem().getMaxStackSize();
								contents[a][b].getCurrentItemStack().setCount(is.getItem().getMaxStackSize());
								addItemStack(new ItemStack(is.getItem(),leftover));
								return;
								
							}
							
							
						}
						
						
						
					}
					
					
					
					
				}

				
				
				
			}
		}
		
		
		//We have already checked if we can add it to an item, we cant, so add it to the next available slot
		for(int b = 0;b < containerHeight;b++) {
			for(int a = 0;a < containerWidth;a++) {
				if(contents[a][b].getCurrentItemStack() == null) {	//Then the slot is open
					
					contents[a][b].setCurrentItemStack(new ItemStack(is.getItem(),is.getCount()));
					return;
					
					
				}
			}
		}

		
		
		
		
		
		
		
		
	}
	public boolean canAddItemStack(ItemStack is) {
		boolean canFit = false;
		
		for(int b = 0;b < containerHeight;b++) {
			for(int a = 0;a < containerWidth;a++) {
				if(contents[a][b].getCurrentItemStack() != null) {	//If Its Not, then we have space, if it is
					
					if(contents[a][b].getCurrentItemStack().getItem().getID() == is.getItem().getID()) {
						if(contents[a][b].getCurrentItemStack().getCount() + is.getCount() < is.getItem().getMaxStackSize()) {
							canFit = true;
						}
						if(contents[a][b].getCurrentItemStack().getCount() < is.getItem().getMaxStackSize()) {
							canFit = true;
							if(contents[a][b].getCurrentItemStack().getCount() + is.getCount() > is.getItem().getMaxStackSize()) {
								canFit = false;
							}
						}
					}
					
					
					
					
				}else {
					return true;
				}
			}
		}
		
		
		
		
		return canFit;
		
	}
	
	
	/////////////////////////GETTERS AND SETTERS////////////////////////////////
	public InventorySlot[][] getContents(){
		return contents;
	}
	
	
	
	
	
	

}
