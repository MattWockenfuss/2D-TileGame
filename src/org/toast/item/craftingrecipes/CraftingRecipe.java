package org.toast.item.craftingrecipes;

import org.toast.entities.HUD.inventory.ItemStack;

public class CraftingRecipe {
	
	private final ItemStack[][] ingredients;
	private final ItemStack product;
	
	
	public CraftingRecipe(ItemStack[][] ingredients,ItemStack product) {
		this.ingredients = ingredients;
		this.product = product;

		
//		System.out.println("NEW CraftingRecipe,");
//		for(int b = 0;b < ingredients.length;b++) {
//			for(int a = 0;a < ingredients[0].length;a++) {//Only works because its a 3x3
//				if(ingredients[a][b] != null)
//					System.out.println("[" + a + "][" + b + "]" + "Ingredient " + ingredients[a][b].getItem().getID() + " , " + ingredients[a][b].getCount());
//				
//				
//			}
//		}
		
		
	}



	
	
	
	
	////////////////////GETTERS AND SETTERS/////////////////////////
	public ItemStack[][] getIngredients() {
		return ingredients;
	}
	public ItemStack getProduct() {
		return product;
	}
	
	
	
}
