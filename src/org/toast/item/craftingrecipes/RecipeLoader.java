package org.toast.item.craftingrecipes;

import java.util.ArrayList;

import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.entities.statics.CraftingStation;
import org.toast.item.Item;
import org.toast.utils.Utils;

public class RecipeLoader {	
	
	public static ArrayList<CraftingRecipe> craftingRecipes = new ArrayList<CraftingRecipe>();
	
	private static ItemStack[] itemVars;
	
	private static CraftingRecipe recipe; 
	private static ItemStack[][] Ingredients;
	private static ItemStack product;
	
	
	
	public static void loadRecipes() {
		 String recipesFile = Utils.loadFileAsString("res/GameFiles/recipes.txt");
		 String[] lines = recipesFile.split("\n");
		 
		 for(String line : lines) {
			 line = line.trim();
			 if(line.equalsIgnoreCase("") || line.startsWith("//")) {
				 continue;
			 }
			 
			 
			 
			 if(line.startsWith("CraftingRecipe")) {
				 itemVars = new ItemStack[9]; //The Maximum number of ItemStacks it can have
				 Ingredients = new ItemStack[3][3];
				 
				 
				 
			 }else if(line.substring(0,1).startsWith("1") || line.substring(0,1).startsWith("2") || line.substring(0,1).startsWith("3")) {
				 //Then its a line with quotes
				 int num = Utils.parseInt(line.charAt(0) + "");
				 line = line.substring(3, line.length() - 1); //Get rid of the quotes and numbers
				 //System.out.println(line);
				 if(num == 1) {	 
					 //Get the 3 characters in the String(Including Spaces)
					 //System.out.println(line.charAt(0) + "");
					 Ingredients[0][0] = getItemVarsAt(line.charAt(0) + "");
					 Ingredients[1][0] = getItemVarsAt(line.charAt(1) + "");
					 Ingredients[2][0] = getItemVarsAt(line.charAt(2) + "");
					 
					 
				 }else if(num == 2) {
					 Ingredients[0][1] = getItemVarsAt(line.charAt(0) + "");
					 Ingredients[1][1] = getItemVarsAt(line.charAt(1) + "");
					 Ingredients[2][1] = getItemVarsAt(line.charAt(2) + "");
				 }else if(num == 3) {
					 Ingredients[0][2] = getItemVarsAt(line.charAt(0) + "");
					 Ingredients[1][2] = getItemVarsAt(line.charAt(1) + "");
					 Ingredients[2][2] = getItemVarsAt(line.charAt(2) + "");
				 }
				 
				 
			 }else if(line.equalsIgnoreCase("}")) {
				 
				 recipe = new CraftingRecipe(Ingredients,product);
				 craftingRecipes.add(recipe);
				 
				 
				 
				 
				 //Create the new object,add it to the craftingrecipe
				 
			 }else if(line.startsWith("product")){
				 
				 
				 String[] tokens = line.substring(6).split(","); 
				 tokens[0] = tokens[0].substring(2);
				 
				 int id = Utils.parseInt(tokens[0]);
				 int stackAmount = Utils.parseInt(tokens[1]);
				 
				 if(Item.items[id] == null) {		//If the item doesnt exists
					 continue;
				 }
				 if(stackAmount > Item.items[id].getMaxStackSize())
					 stackAmount = Item.items[id].getMaxStackSize();
				 
				 product = new ItemStack(Item.items[id],stackAmount);
				 
				 
			 }else {
				 String letter = line.substring(0, 1);
				 
				 //sperate the String with the name of item and stack Amount
				 String[] tokens = line.split(",");
				 tokens[0] = tokens[0].substring(2);
				 
				 //For A:3,64
				 // letter = "A", tokens[0] = "3", tokens[1] = "1"
				 
				 int id = Utils.parseInt(tokens[0]);
				 int stackAmount = Utils.parseInt(tokens[1]);
				 ItemStack is = new ItemStack(Item.items[id],stackAmount);
				 setItemVars(letter,is);
			 }
			 
			 
			 
			 
		 }
		 
		 
		 
	}
	
	
		public static void setItemVars(String letter,ItemStack is) {
			 if(letter.equalsIgnoreCase("a")) {
				 itemVars[0] = is;
			 }else if(letter.equalsIgnoreCase("b")) {
				 itemVars[1] = is;
			 }else if(letter.equalsIgnoreCase("c")) {
				 itemVars[2] = is;
			 }else if(letter.equalsIgnoreCase("d")) {
				 itemVars[3] = is;
			 }else if(letter.equalsIgnoreCase("e")) {
				 itemVars[4] = is;
			 }else if(letter.equalsIgnoreCase("f")) {
				 itemVars[5] = is;
			 }else if(letter.equalsIgnoreCase("g")) {
				 itemVars[6] = is;
			 }else if(letter.equalsIgnoreCase("h")) {
				 itemVars[7] = is;
			 }else if(letter.equalsIgnoreCase("i")) {
				 itemVars[8] = is;
			 }
		}
		public static ItemStack getItemVarsAt(String letter) {
			 if(letter.equalsIgnoreCase("a")) {
				 return itemVars[0];
			 }else if(letter.equalsIgnoreCase("b")) {
				 return itemVars[1];
			 }else if(letter.equalsIgnoreCase("c")) {
				 return itemVars[2];
			 }else if(letter.equalsIgnoreCase("d")) {
				 return itemVars[3];
			 }else if(letter.equalsIgnoreCase("e")) {
				 return itemVars[4];
			 }else if(letter.equalsIgnoreCase("f")) {
				 return itemVars[5];
			 }else if(letter.equalsIgnoreCase("g")) {
				 return itemVars[6];
			 }else if(letter.equalsIgnoreCase("h")) {
				 return itemVars[7];
			 }else if(letter.equalsIgnoreCase("i")) {
				 return itemVars[8];
			 }else {
				 return null;//That means its a space, no ItemStack exists
			 }
		}
	
	
		
		
		
		
	public static CraftingRecipe getCraftableRecipe(ItemStack[][] itemstacks) {
		
		//If there is a recipe that can be crafted, it returns that recipe, otherwise it returns null
			CraftingRecipe craftRecipe = null;
		
		
		for(CraftingRecipe recipe : craftingRecipes) {
			boolean canCraft = true;
			//loop through all the recipes and check if they can be crafted
			for(int b = 0;b < CraftingStation.containerHeight;b++) {
				for(int a = 0;a < CraftingStation.containerWidth;a++) {
					
					
					
					//check to see if the slots are identical
					//first check to see if the slot should be empty
					if(recipe.getIngredients()[a][b] != null) {//The recipe has an item in this slot
						
						if(itemstacks[a][b] != null) {		// we both have an item, check if we have the same item, and enough
							
							if(recipe.getIngredients()[a][b].getItem().getID() == itemstacks[a][b].getItem().getID()) {
								
								if(itemstacks[a][b].getCount() >= recipe.getIngredients()[a][b].getCount()) {
									//We have the same item, and more or the same amount as the recipe in the slot
								}else {				//We dont have enough of the item
									canCraft = false;
								}
								
								
								
							}else {							// we dont have the same item
								canCraft = false;
							}
							
							
							
						}else {								//The recipe has an item, but we dont, so you cant craft
							canCraft = false;
						}
						
						
					}else {										//the recipe doesnt have an item in this slot
						if(itemstacks[a][b] != null) {			//if we have an item and the recipe doesn't, we can't craft this item
							canCraft = false;
						}
					}
					
			
				}
			}
			
			if(canCraft) {
				craftRecipe = recipe;
			}
			
			
			
		}
		return craftRecipe;
		
	}
	
}
