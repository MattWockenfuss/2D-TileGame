package org.toast.entities.statics;

import java.awt.Graphics;

import org.toast.Handler;
import org.toast.entities.HUD.inventory.InventorySlot;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.gfx.Assets;
import org.toast.item.craftingrecipes.CraftingRecipe;
import org.toast.item.craftingrecipes.RecipeLoader;
import org.toast.utils.Utils;

public class CraftingStation extends StaticEntity{
	
	public static final int containerWidth = 3,containerHeight = 3;
	
	private InventorySlot[][] contents;
	private InventorySlot productSlot;
	
	private CraftingRecipe craftableRecipe;	
	
	
	public CraftingStation(Handler handler, float x, float y) {
		super(handler, x, y,Assets.crafting_station.getWidth(), Assets.crafting_station.getHeight());
		
		//We dont want it to have collision
		
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
		productSlot = new InventorySlot();
		
	}


	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.crafting_station, (int) (x - handler.getGameCamera().getxOffset()),(int) (y - handler.getGameCamera().getyOffset()),width,height,null);
	}
	
	
	
	
	public void spawnChildren() {
		
		int centerX = (int) this.getX();
		int centerY = (int)(this.getY() - this.getWidth() / 2); // So the items spawn "above" it so they dont leak into ground
		
		
		handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,(float)centerX, (float)centerY, 57 ,1));
		
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
	
	
	public void reloadRecipes() {
		
		ItemStack[][] craftingInventory = new ItemStack[containerWidth][containerHeight];
		
		for(int b = 0;b < containerHeight;b++) {
			for(int a = 0;a < containerWidth;a++) {
				craftingInventory[a][b] = contents[a][b].getCurrentItemStack();
			}
		}
		
		
		CraftingRecipe craftableRecipe = RecipeLoader.getCraftableRecipe(craftingInventory);
		this.craftableRecipe = craftableRecipe;
		if(this.craftableRecipe == null) {
			productSlot.setCurrentItemStack(null);
		}else {
			productSlot.setCurrentItemStack(craftableRecipe.getProduct());
		}
	}
	public void clickedProductSlotFlag(int crafts) {
		//we have made product
		//what ever is in the crafting table, we have made 1 recipe's worth of it
		
		if(craftableRecipe != null) {
			
			for(int b = 0;b < containerHeight;b++) {
				for(int a = 0;a < containerWidth;a++) {
					
					if(contents[a][b].getCurrentItemStack() != null && craftableRecipe.getIngredients()[a][b] != null) {
						contents[a][b].getCurrentItemStack().setCount(contents[a][b].getCurrentItemStack().getCount() - craftableRecipe.getIngredients()[a][b].getCount() * crafts);//crafts is an integer representing the number of times you can complete the recipe
						if(contents[a][b].getCurrentItemStack().getCount() <= 0)
							contents[a][b].setCurrentItemStack(null);
					}
					
					

				}
			}
			
			
			
			
			
		}
		
		
	}


	
	
	
	
	
	


	
	
	/////////////////////////GETTERS AND SETTERS////////////////////////////////
	public int getShiftMaxCraftAmount() {
		int amount = 64;

		if(craftableRecipe != null) {//set the last time we changed the data in the crafting table
			
			
			//if we can have 64 avocados in a slot, and we need 2 for this recipe, than we can craft 32 times
			if(productSlot.getCurrentItemStack() != null) {
				amount = productSlot.getCurrentItemStack().getItem().getMaxStackSize() / craftableRecipe.getProduct().getCount();		
			}
			
			
			
			
			for(int b = 0;b < containerHeight;b++) {
				for(int a = 0;a < containerWidth;a++) {
					
					//loop through all the slots, and find the max amount of times we can craft this recipe is
					if(craftableRecipe.getIngredients()[a][b] != null) {
						
						// if we have 5 corn in a slot, and need 1 in this slot to craft, then we can craft 5 times
						// if its the lower than the current lowest number of times we can craft, than reset it
						int current = (int)(contents[a][b].getCurrentItemStack().getCount() / craftableRecipe.getIngredients()[a][b].getCount());
						if(current < amount)
							amount = current;
					}
					
					
					
					
				}
			}
			
			
			
		}
		
		
		
		
		
		
		
		
		return amount;
	}
	public InventorySlot[][] getContents() {
		return contents;
	}
	public InventorySlot getProductSlot() {
		return productSlot;
	}
	public CraftingRecipe getCraftableRecipe() {
		return craftableRecipe;
	}
	
	
	
	
	
	
	

}
