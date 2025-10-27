package org.toast.entities.itemEntities;

import java.awt.Graphics;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.entities.Entity;
import org.toast.item.Item;

public class ItemEntity extends Entity{
	
	public static int ItemWidth = 32;
	public static int ItemHeight = 32;
	
	private int itemID;
	private int itemAmount;				

	public ItemEntity(Handler handler, float x, float y,int itemID,int itemAmount) {
		super(handler, x, y, ItemWidth, ItemHeight);
		this.itemID = itemID;
		this.itemAmount = itemAmount;
		this.bounds.width = 0;
		this.bounds.height = 0;
		//System.out.println("ID: " + itemID + " , itemAmount: " + itemAmount);
 		if(itemAmount > Item.items[itemID].getMaxStackSize())
			itemAmount = Item.items[itemID].getMaxStackSize();
	}

	public void tick() {
		Item.items[itemID].tick();
		//if the item is in a wall, proceed to air block
		Block currentBlock = handler.getWorld().getBlock(x, y);
		if(currentBlock.isSolid()) {
			y--;
		}
	}

	public void render(Graphics g) {
		Item.items[itemID].render(g, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()));
	}
	
	
	
	
	
	////////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////
	
	public int getItemID() {
		return itemID;
	}
	public int getItemStackCount() {
		return itemAmount;
	}
	
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public void setItemStackCount(int itemAmount) {
		this.itemAmount = itemAmount;
	}
	
	
	
	
	
	
	

}
