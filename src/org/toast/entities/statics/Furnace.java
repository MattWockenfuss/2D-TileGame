package org.toast.entities.statics;

import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.HUD.inventory.InventorySlot;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.gfx.Assets;
import org.toast.item.Item;

public class Furnace extends StaticEntity{
	
	private int state = 0;
	
	private InventorySlot ingredientSlot,fuelSlot,productSlot;
	private int energy = 0;
	public static int maxEnergy = 4800;
		
	public Furnace(Handler handler, float x, float y) {
		super(handler, x, y,Assets.furnace[0].getWidth(), Assets.furnace[0].getHeight());
		
		//We dont want it to have collision
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 32;
		bounds.height = 32;
		
		ingredientSlot = new InventorySlot();
		fuelSlot = new InventorySlot();
		productSlot = new InventorySlot();
		
	}


	public void tick() {
		
		if(fuelSlot.getCurrentItemStack() != null && fuelSlot.getCurrentItemStack().getItem() == Item.coal && energy <= (maxEnergy - 400)) {
			energy += 400;
			fuelSlot.getCurrentItemStack().setCount(fuelSlot.getCurrentItemStack().getCount() - 1);
			if(fuelSlot.getCurrentItemStack().getCount() == 0) {
				fuelSlot.setCurrentItemStack(null);
			}
		}
		
		if(fuelSlot.getCurrentItemStack() != null && fuelSlot.getCurrentItemStack().getItem() == Item.uranium_dust && energy <= (maxEnergy - 2400)) {
			energy += 2400;
			fuelSlot.getCurrentItemStack().setCount(fuelSlot.getCurrentItemStack().getCount() - 1);
			if(fuelSlot.getCurrentItemStack().getCount() == 0) {
				fuelSlot.setCurrentItemStack(null);
			}
		}
		
		
		
		if(energy > 0) {
			state = 1;
		}else {
			state = 0;
		}
		
		
		if(energy > 1200 && ingredientSlot.getCurrentItemStack() != null) {
			
			if(productSlot.getCurrentItemStack() == null) {
				//add Graphite Dust
				productSlot.setCurrentItemStack(new ItemStack(Item.graphite_dust,1));
				ingredientSlot.getCurrentItemStack().setCount(ingredientSlot.getCurrentItemStack().getCount() - 1);
				energy -= 1200;
			}else{
				if(productSlot.getCurrentItemStack().getItem() == Item.graphite_dust) {
					if(productSlot.getCurrentItemStack().getCount() < Item.graphite_dust.getMaxStackSize()) {
						//Then we can burn the thing
						ingredientSlot.getCurrentItemStack().setCount(ingredientSlot.getCurrentItemStack().getCount() - 1);
						productSlot.getCurrentItemStack().setCount(productSlot.getCurrentItemStack().getCount() + 1);
						energy -= 1200;
					}
				}
			}
			if(ingredientSlot.getCurrentItemStack().getCount() == 0) {
				ingredientSlot.setCurrentItemStack(null);
			}
				
		}
		
		
		
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.furnace[state], (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width,height,null);
	}
	
	
	public void spawnChildren() {
		
			int centerX = (int)(this.getX() + this.getBounds().x + (this.getBounds().width / 2));
			int centerY = (int)(this.getY() + this.getBounds().y + (this.getBounds().height / 2));
			handler.getWorld().getEntityManager().getEntities().add(
					new ItemEntity(handler,(float)centerX, (float)centerY, 58 ,1));
		

	}
	
	
	
	
	
	


	
	
	/////////////////////////GETTERS AND SETTERS////////////////////////////////
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}


	public InventorySlot getIngredientSlot() {
		return ingredientSlot;
	}
	public InventorySlot getFuelSlot() {
		return fuelSlot;
	}
	public InventorySlot getProductSlot() {
		return productSlot;
	}
	
	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	
	
	
	
	

}
