package org.toast.entities.HUD;

import java.awt.Color;
import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.Entity;
import org.toast.entities.HUD.inventory.Inventory;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.entities.mobs.Player;
import org.toast.gfx.Assets;

public class HUD {
	
	private Handler handler;
	
	private Inventory inventory;
	
	private ItemStack currentlySelectedItem;
	private int slotSelected = 0;
	
	
	//Rendering Stuff
	private int startX,startY,gapX;
	
	
	public HUD(Handler handler) {
		this.handler = handler;
		inventory = new Inventory(handler);
	}
	
	
	public void tick() {
		startX = (int) (5 * handler.getGuiScale());
		startY = (int) (5 * handler.getGuiScale());
		
		gapX = (int) (8 * handler.getGuiScale());
	}
	public void render(Graphics g) {
		int hotbarX = (int) ((handler.getWidth() / 2) - (Assets.hotbar.getWidth() * handler.getGuiScale() / 2));
		int hotbarY = (int) (handler.getHeight() - (Assets.hotbar.getHeight() * handler.getGuiScale()));
		int itemWidth = (int) (32 * handler.getGuiScale());
		//draw the hotbar
		g.drawImage(Assets.hotbar,hotbarX,hotbarY,(int)(Assets.hotbar.getWidth() * handler.getGuiScale()),(int)(Assets.hotbar.getHeight() * handler.getGuiScale()), null);
		
		//draw the items in it
		for(int a = 0;a < Inventory.InventoryWidth;a++) {
			if(inventory.getInventorySlot(a, 3).getCurrentItemStack() != null) {
				//Render the item
				inventory.getInventorySlot(a, 3).getCurrentItemStack().getItem().renderInventory(g, hotbarX + startX + a * (gapX + itemWidth), hotbarY + startY, itemWidth,itemWidth);
			
				
			//DrawString for Count
			if(inventory.getInventorySlot(a, 3).getCurrentItemStack().getCount() != 1) {
				g.setColor(Color.black);
				g.setFont(Assets.debug);
				g.drawString(inventory.getInventorySlot(a, 3).getCurrentItemStack().getCount() + "", 1 + (hotbarX + startX) + (itemWidth + gapX) * a, hotbarY + startY  + (int)(8 * handler.getGuiScale())); 
			}
					
				
				
			}
			
		}
		
		
		
		//Draw the current selected thing
		g.drawImage(Assets.selectedItem, hotbarX + (slotSelected * (gapX + itemWidth)), hotbarY,(int)((Assets.selectedItem.getWidth() * handler.getGuiScale() - 2) ),(int)((Assets.selectedItem.getHeight() * handler.getGuiScale() - 2) ),null);
		
		
		
		//HEALTH BAR AND SWIMING BAR
		int health = Player.maxHealth;
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof Player) {
				health = ((Player) e).getHealth();
			}
				
		}
		
		
		int healthWidth = (int) (45);
		int healthHeight = (int) (45);
		
		
		
		//We want to render the health bar cross thing
		g.setColor(Color.DARK_GRAY); 
		g.fillRect(hotbarX, hotbarY - healthHeight, healthWidth, healthHeight);
		

		

		
		
		
		double percentHealth = (double)(health) / Player.maxHealth;
		//System.out.println("HP: " + health + " , " + percentHealth);
		g.setColor(Color.RED); 
		g.fillRect(hotbarX + 2, hotbarY - 2, healthWidth - 4, (int)(-(healthHeight) * percentHealth) + 4);
		
		g.setColor(Color.white);
		g.setFont(Assets.debug);
		g.drawString("HP: " + health, hotbarX + 5, hotbarY - healthHeight - 2);
		
		
		//draw the cross looking thing
		g.setColor(Color.black);
		g.fillRect(hotbarX + 4, hotbarY - healthHeight + (healthHeight / 2) - 4, healthWidth - 8, 8);
		g.fillRect(hotbarX + healthWidth - (healthWidth / 2) - 5, hotbarY - healthHeight + 4, 8, healthHeight - 8);
		
		
	
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	///////////////////////////////////////GETTERS AND SETTERS//////////////////////////////////////////////////////////////////
	public ItemStack getCurrentlySelectedItem() {
		return currentlySelectedItem;
	}
	public Inventory getInventory() {
		return inventory;
	}
	public void setSlotSelected(int slot) {
		this.slotSelected = slot;
	}
	public int getSlotSelected() {
		return slotSelected;
	}

}
