package org.toast.entities.HUD.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.entities.mobs.Player;
import org.toast.entities.statics.CraftingStation;
import org.toast.entities.statics.Crate;
import org.toast.entities.statics.Furnace;
import org.toast.gfx.Assets;
import org.toast.item.Item;
import org.toast.item.items.Corn;
import org.toast.item.items.Cotton;
import org.toast.item.items.CraftingStationItem;
import org.toast.item.items.CrateItem;
import org.toast.item.items.FurnaceItem;
import org.toast.item.items.Lettuce;
import org.toast.item.items.PineApple;
import org.toast.states.SinglePlayer.SinglePlayerGameState;

public class Inventory {
	
	private Handler handler;
	private InventorySlot[][] InventorySlots;
	
	ItemStack currentSelectedItemInInventory;
	ItemStack currentlyHoveredOn = null;
	public Point currentlyHoveredOnSlot;
	
	//Variables
	private int InventoryX = 100,InventoryY = 100,InventoryGUIWidth,InventoryGUIHeight;
	
	private int itemWidth,itemHeight,ItemStartX,ItemStartY,gapX,gapY;
	private int crateWidth,crateHeight,craftWidth,craftHeight,furnaceWidth,furnaceHeight;
	
	private boolean leftClick = false,rightClick = false,toggle = false,toggle1 = false;
	private boolean rightDragClick = false,leftDragClick = false;
	//rightclickstuff
	private int lastA = -1,lastB = 1;
	
	private Crate crate;
	private CraftingStation craftingTable;
	private Furnace furnace;
	
	
	
	//STUFF ABOUT THE INVENTORY
	public final static int InventoryWidth = 9;
	public final static int InventoryHeight = 4;
	
	public Inventory(Handler handler) {
		this.handler = handler;
		InventorySlots = new InventorySlot[InventoryWidth][InventoryHeight];
		for(int b = 0;b < InventoryHeight;b++) {
			for(int a = 0;a < InventoryWidth;a++) {
				InventorySlots[a][b] = new InventorySlot();
			}
		}
		currentlyHoveredOnSlot = new Point(0,0);
		
		//Add a random item ID with a stack size between 1 and that items max stack size in a random slot in our Inventory
		
//		for(int e = 0;e < 9;e++) {
//			Item i = Item.items[Utils.randomNumber(1, 52)];
//			InventorySlots[e][0].setCurrentItemStack(new ItemStack(i,Utils.randomNumber(1, i.getMaxStackSize())));	
//		}
//		for(int e = 0;e < 9;e++) {
//			Item i = Item.items[Utils.randomNumber(1, 52)];
//			InventorySlots[e][1].setCurrentItemStack(new ItemStack(i,Utils.randomNumber(1, i.getMaxStackSize())));	
//		}
//		for(int e = 0;e < 9;e++) {
//			Item i = Item.items[Utils.randomNumber(1, 52)];
//			InventorySlots[e][2].setCurrentItemStack(new ItemStack(i,Utils.randomNumber(1, i.getMaxStackSize())));	
//		}
		 	InventorySlots[0][3].setCurrentItemStack(new ItemStack(Item.paladin_axe,1)); 

	}
	
	
	
	
	
	public void tick() {
		

		
		InventoryGUIWidth = (int)(Assets.inventory_gui.getWidth() * handler.getGuiScale());
		InventoryGUIHeight = (int)(Assets.inventory_gui.getHeight() * handler.getGuiScale());
		InventoryX = (handler.getWidth() / 2) - (InventoryGUIWidth / 2);
		InventoryY = 128;
		itemWidth = (int)(32 * handler.getGuiScale());
		itemHeight = (int)(32 * handler.getGuiScale());
		ItemStartX = InventoryX + (int) (16 * handler.getGuiScale());
		ItemStartY = InventoryY + (int) (168 * handler.getGuiScale());
		gapX = (int)(4 * handler.getGuiScale());
		gapY = (int)(4 * handler.getGuiScale());
		
		crateWidth = (int)(Assets.crate_gui.getWidth() * handler.getGuiScale());
		crateHeight = (int)(Assets.crate_gui.getHeight() * handler.getGuiScale());
		furnaceWidth = (int)(Assets.furnace_gui.getWidth() * handler.getGuiScale());
		furnaceHeight = (int)(Assets.furnace_gui.getHeight() * handler.getGuiScale());
		craftWidth = (int)(Assets.crafting_station_gui.getWidth() * handler.getGuiScale());
		craftHeight = (int)(Assets.crafting_station_gui.getHeight() * handler.getGuiScale());
		
		
		//Process the clicks
		
		
		leftClick = false;
		rightClick = false;
		
		if(handler.getMouseManager().isLeftPressed() && !toggle) {
			leftClick = !leftClick;
			toggle = true;
		}else if(!handler.getMouseManager().isLeftPressed()) {
			toggle = false;
		}
		
		if(handler.getMouseManager().isRightPressed() && !toggle1) {
			rightClick = !rightClick;
			toggle1 = true;
		}else if(!handler.getMouseManager().isRightPressed()) {
			toggle1 = false;
		}
		
		leftDragClick = handler.getMouseManager().isLeftPressed();
		rightDragClick = handler.getMouseManager().isRightPressed();
		
		clickInventory();
		tickInventory();
		

		
		
			
	}///////////////////End of the Tick
	public void render(Graphics g) {

		g.drawImage(Assets.inventory_gui, InventoryX, InventoryY,InventoryGUIWidth,InventoryGUIHeight,null);
		
		//Draws the player inside
		int playerWidth = (int) (Assets.width * handler.getGuiScale() * 3.5);
		int playerHeight = (int)(Assets.height * handler.getGuiScale() * 3.5);
		
		int playerIndentWidth = (int) (InventoryX + 138 * handler.getGuiScale());
		int playerIndentHeight = (int)(InventoryY + 36 * handler.getGuiScale());
		
		
		g.drawImage(Player.currentAnimationState, playerIndentWidth, playerIndentHeight,playerWidth,playerHeight, null);
		
		
		//Draw the items
		for(int b = 0;b < InventoryHeight;b++) {
			for(int a = 0;a < InventoryWidth;a++) {
				
				if(InventorySlots[a][b].getCurrentItemStack() != null) {
					
					//If its the last row, than bmp it down cuz its the hotbar
					if(b == 3) {
						InventorySlots[a][b].getCurrentItemStack().getItem().renderInventory(g, ItemStartX + (itemWidth + gapX) * a, ItemStartY + ((itemHeight + gapY) * b) + (gapY * 2), itemWidth, itemHeight);
					}else {
						InventorySlots[a][b].getCurrentItemStack().getItem().renderInventory(g, ItemStartX + (itemWidth + gapX) * a, ItemStartY + (itemHeight + gapY) * b, itemWidth, itemHeight);
					}
					
					
					if(InventorySlots[a][b].getCurrentItemStack().getCount() != 1) {
						g.setColor(Color.black);
						g.setFont(Assets.debug);
						if(b == 3) {
							g.drawString(InventorySlots[a][b].getCurrentItemStack().getCount() + "", 1 + ItemStartX + (itemWidth + gapX) * a, (int)(gapY * 2.0) + ItemStartY + ((itemHeight + gapY) * b) + (gapY * 2)); 
						}else {
							g.drawString(InventorySlots[a][b].getCurrentItemStack().getCount() + "", 1 + ItemStartX + (itemWidth + gapX) * a, (int)(gapY * 2.0) + ItemStartY + (itemHeight + gapY) * b); 
						}
						
					}
					
					
				}
				
				
			}
		}
		
		
		
		renderPanels(g);
		if(handler.isInventoryHover() && !(SinglePlayerGameState.escape)) {
			renderSelected(g);
		}
		
		
		if(currentSelectedItemInInventory != null) {
			currentSelectedItemInInventory.getItem().renderInventory(g, handler.getMouseManager().getMouseX() - itemWidth / 2, handler.getMouseManager().getMouseY() - itemHeight / 2,itemWidth,itemHeight);
			if(currentSelectedItemInInventory.getCount() != 1) {
				g.setColor(Color.black);
				g.setFont(Assets.debug);
				g.drawString(currentSelectedItemInInventory.getCount() + "", handler.getMouseManager().getMouseX() - itemWidth / 2, handler.getMouseManager().getMouseY() - itemHeight / 2 + (gapY * 2));
			}
			
		}else {
			
		}
		
		
		
			
		
		
		
	}
	public void renderPanels(Graphics g) {
		int crateWidth = (int)(Assets.crate_gui.getWidth() * handler.getGuiScale());
		int crateHeight = (int)(Assets.crate_gui.getHeight() * handler.getGuiScale());
		int furnaceWidth = (int)(Assets.furnace_gui.getWidth() * handler.getGuiScale());
		int furnaceHeight = (int)(Assets.furnace_gui.getHeight() * handler.getGuiScale());
		int craftWidth = (int)(Assets.crafting_station_gui.getWidth() * handler.getGuiScale());
		int craftHeight = (int)(Assets.crafting_station_gui.getHeight() * handler.getGuiScale());
		
		
		//Render the Tooltip
		if(currentlyHoveredOn != null) {
			
			String name = currentlyHoveredOn.getItem().getDisplayName();
			int length = name.length();
			
			int borderWidth = 3;
			
			int toolTipWidth = (int) (length * 6 + (48 * handler.getGuiScale()) + 135);
			int toolTipHeight = (int) ((48 * handler.getGuiScale()));
			
			int toolTipStartX = (int) (handler.getWidth() / 2 - (toolTipWidth / 2.2));
			int toolTipStartY = 0;
			
			g.setColor(Color.black);
			g.fillRect(toolTipStartX - borderWidth, toolTipStartY, toolTipWidth + (borderWidth * 2), toolTipHeight + borderWidth);
			
			g.setColor(Color.GRAY);
			g.fillRect(toolTipStartX, toolTipStartY, toolTipWidth, toolTipHeight);
			currentlyHoveredOn.getItem().renderInventory(g, handler.getWidth() / 2 - ((int)(70 * handler.getGuiScale())), 0, (int)(48 * handler.getGuiScale()),(int)(48 * handler.getGuiScale()));
			
			
			g.setColor(Color.white);
			g.setFont(Assets.menu);
			
			g.drawString(name, handler.getWidth() / 2 - (currentlyHoveredOn.getItem().getDisplayName().length() * 3 / 2), (int)((toolTipHeight / 2 - 10) * handler.getGuiScale()));
			
			//set the colors back to normal
			g.setColor(Color.black);
			g.setFont(Assets.debug);
			
			
			
		}
		
		
		
		if(crate != null) {
			g.drawImage(Assets.crate_gui, InventoryX - crateWidth, InventoryY,crateWidth,crateHeight,null);
			
			int ItemStartX = (int) (InventoryX - crateWidth + (24 * handler.getGuiScale()));
			int ItemStartY = (int) (InventoryY + (16 * handler.getGuiScale()));
			
			//draw its contents
			for(int b = 0;b < Crate.containerHeight;b++) {
				for(int a = 0;a < Crate.containerWidth;a++) {
					
					if(crate.getContents()[a][b].getCurrentItemStack() != null) {
						crate.getContents()[a][b].getCurrentItemStack().getItem().renderInventory(g, ItemStartX + (a * (itemWidth + gapX)), ItemStartY + (itemHeight + gapY) * b, itemWidth, itemHeight);
						
						if(crate.getContents()[a][b].getCurrentItemStack().getCount() != 1) {
							g.drawString(crate.getContents()[a][b].getCurrentItemStack().getCount() + "", 1 + ItemStartX + (itemWidth + gapX) * a, (int)(gapY * 2.0) + ItemStartY + (itemHeight + gapY) * b);
						}
						
					}	
						
						
					
				}
			}
			
		}
		if(craftingTable != null) {
			g.drawImage(Assets.crafting_station_gui, InventoryX + InventoryGUIWidth, InventoryY,craftWidth,craftHeight,null);
			
			int ItemStartX = (int) (InventoryX + InventoryGUIWidth + (60 * handler.getGuiScale()));
			int ItemStartY = (int) (InventoryY + (34 * handler.getGuiScale()));
			
			//draw its contents
			for(int b = 0;b < CraftingStation.containerHeight;b++) {
				for(int a = 0;a < CraftingStation.containerWidth;a++) {
					
					if(craftingTable.getContents()[a][b].getCurrentItemStack() != null) {
						craftingTable.getContents()[a][b].getCurrentItemStack().getItem().renderInventory(g, ItemStartX + (a * (itemWidth + gapX)), ItemStartY + (itemHeight + gapY) * b, itemWidth, itemHeight);
						
						if(craftingTable.getContents()[a][b].getCurrentItemStack().getCount() != 1) {
							g.drawString(craftingTable.getContents()[a][b].getCurrentItemStack().getCount() + "", 1 + ItemStartX + (itemWidth + gapX) * a, (int)(gapY * 2.0) + ItemStartY + (itemHeight + gapY) * b);
						}
						
					}	
						
						
					
				}
			}
			
			int ProductItemStartX = (int) (InventoryX + InventoryGUIWidth + (247 * handler.getGuiScale()));
			int ProductItemStartY = (int) (InventoryY + (69 * handler.getGuiScale()));
			
			if(craftingTable.getProductSlot().getCurrentItemStack() != null) {
				craftingTable.getProductSlot().getCurrentItemStack().getItem().renderInventory(g, ProductItemStartX, ProductItemStartY, itemWidth, itemHeight);
				if(craftingTable.getProductSlot().getCurrentItemStack().getItem().getMaxStackSize() != 1)
				g.drawString(craftingTable.getProductSlot().getCurrentItemStack().getCount() + "", ProductItemStartX, ProductItemStartY + gapY);
			}
			
			
		}
		if(furnace != null) {
			g.drawImage(Assets.furnace_gui, InventoryX + InventoryGUIWidth, InventoryY + craftHeight,furnaceWidth,furnaceHeight,null);
			
			int furnaceIngredientX = (int) (InventoryX + InventoryGUIWidth + (106 * handler.getGuiScale()));
			int furnaceIngredientY = (int) (InventoryY + craftHeight +  (52 * handler.getGuiScale()));
			
			int furnaceFuelX = (int) (InventoryX + InventoryGUIWidth + (16 * handler.getGuiScale()));
			int furnaceFuelY = (int) (InventoryY + craftHeight +  (106 * handler.getGuiScale()));
			
			int furnaceProductX = (int) (InventoryX + InventoryGUIWidth + (232 * handler.getGuiScale()));
			int furanceProductY = (int) (InventoryY + craftHeight +  (70 * handler.getGuiScale()));
			
			if(furnace.getIngredientSlot().getCurrentItemStack() != null) {
				furnace.getIngredientSlot().getCurrentItemStack().getItem().renderInventory(g, furnaceIngredientX, furnaceIngredientY, itemWidth, itemHeight);
				
				if(furnace.getIngredientSlot().getCurrentItemStack().getCount() != 1)
					g.drawString(furnace.getIngredientSlot().getCurrentItemStack().getCount() + "", furnaceIngredientX, furnaceIngredientY + gapY * 2);
			}
			
			if(furnace.getFuelSlot().getCurrentItemStack() != null) {
				furnace.getFuelSlot().getCurrentItemStack().getItem().renderInventory(g, furnaceFuelX, furnaceFuelY, itemWidth, itemHeight);
				
				if(furnace.getFuelSlot().getCurrentItemStack().getCount() != 1)
					g.drawString(furnace.getFuelSlot().getCurrentItemStack().getCount() + "", furnaceFuelX, furnaceFuelY + gapY * 2);
			}
			
			if(furnace.getProductSlot().getCurrentItemStack() != null) {
				furnace.getProductSlot().getCurrentItemStack().getItem().renderInventory(g, furnaceProductX, furanceProductY, itemWidth, itemHeight);
				
				if(furnace.getProductSlot().getCurrentItemStack().getCount() != 1)
					g.drawString(furnace.getProductSlot().getCurrentItemStack().getCount() + "", furnaceProductX, furanceProductY + gapY * 2);
			}
			
			int furnaceEnergyX = (int) (InventoryX + InventoryGUIWidth + (354 * handler.getGuiScale()));
			int furnaceEnergyY = (int) (InventoryY + craftHeight +  (142 * handler.getGuiScale()));
			int furnaceEnergyWidth = (int)(31 * handler.getGuiScale());
			double percentFull = (double)(furnace.getEnergy()) / Furnace.maxEnergy;
			//System.out.println(furnace.getEnergy() + ", " + Furnace.maxEnergy + "      :      " + percentFull);
			int furnaceEnergyHeight = -(int)(percentFull * (handler.getGuiScale() * 119));
			
			g.setColor(Color.red);
			g.fillRect(furnaceEnergyX,furnaceEnergyY,furnaceEnergyWidth,furnaceEnergyHeight);
			g.setColor(Color.black);
			
			

			
			
			
		}
	
	
	
	}
	public void renderSelected(Graphics g) {
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		
		if(mouseX > InventoryX && mouseX < InventoryX + InventoryGUIWidth) {
			if(mouseY > InventoryY && mouseY < InventoryY + InventoryGUIHeight) {
				
				for(int b = 0;b < InventoryHeight - 1;b++) {			//Renders all but the last row,
					for(int a = 0;a < InventoryWidth;a++) {
						
						
						if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
							if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
								g.setColor(Assets.tinted_black);
								g.fillRect(ItemStartX + (a * (itemWidth + gapX)), ItemStartY + (b * (itemHeight + gapY)), itemWidth, itemHeight);
								currentlyHoveredOn = InventorySlots[a][b].getCurrentItemStack();
								currentlyHoveredOnSlot.x = a;
								currentlyHoveredOnSlot.y = b;
							}
						}

						
						
						
						
						
					}
				}
			
			//Now lets do the last row
				
				int lastRowStartX = (int) (InventoryX + 16 * handler.getGuiScale());
				int lastRowEndX = (int) (InventoryX + 336  * handler.getGuiScale());
				
				int lastRowStartY = (int) (InventoryY + 284 * handler.getGuiScale());
				int lastRowEndY = (int) (InventoryY + 316 * handler.getGuiScale());
				
				if(mouseX > lastRowStartX && mouseX < lastRowEndX) {
					if(mouseY > lastRowStartY && mouseY < lastRowEndY) {
						//then the mouse is in the last row of the inventory
						
						for(int i = 0;i < InventoryWidth;i++) {
							if(mouseX > ItemStartX + (i * (itemWidth + gapX)) && mouseX < ItemStartX + (i * (itemWidth + gapX)) + itemWidth) {
								g.setColor(Assets.tinted_black);
								g.fillRect(ItemStartX + (i * (itemWidth + gapX)), lastRowStartY, itemWidth, itemHeight);
								currentlyHoveredOn = InventorySlots[i][3].getCurrentItemStack();
							}
						}
						
						
					}
				}
				
				
				
				//Lets check the furnace energy
				
				
				
				
				
				
				
			}
		}else if(mouseX > (InventoryX - crateWidth) && mouseX < InventoryX) {
			if(mouseY > InventoryY && mouseY < (InventoryY + crateHeight)) {
				if(crate != null) {
					
					int ItemStartX = (int) (InventoryX - crateWidth + (24 * handler.getGuiScale()));
					int ItemStartY = (int) (InventoryY + (16 * handler.getGuiScale()));
					
						for(int b = 0;b < Crate.containerHeight;b++) {
							for(int a = 0;a < Crate.containerWidth;a++) {
								
								
								if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
									if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
										
										g.setColor(Assets.tinted_black);
										g.fillRect(ItemStartX + (a * (itemWidth + gapX)), ItemStartY + (b * (itemHeight + gapY)), itemWidth, itemHeight);
										currentlyHoveredOn = crate.getContents()[a][b].getCurrentItemStack();
										
										
									}
								}
								
								
								
								
							}
						}

								


					
					
					
					
					
					
				}
				
			}
		}else if(mouseX > InventoryX + InventoryGUIWidth && mouseX < (InventoryX + InventoryGUIWidth + craftWidth)) {
			if(mouseY > InventoryY && mouseY < InventoryY + craftHeight) {
				if(craftingTable != null) {
					
					
					int ItemStartX = (int) (InventoryX + InventoryGUIWidth + (60 * handler.getGuiScale()));
					int ItemStartY = (int) (InventoryY + (34 * handler.getGuiScale()));
					
					
						for(int b = 0;b < CraftingStation.containerHeight;b++) {
							for(int a = 0;a < CraftingStation.containerWidth;a++) {
								
								if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
									if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
										g.setColor(Assets.tinted_black);
										g.fillRect(ItemStartX + (a * (itemWidth + gapX)), ItemStartY + (b * (itemHeight + gapY)), itemWidth, itemHeight);
										currentlyHoveredOn = craftingTable.getContents()[a][b].getCurrentItemStack();
										
									}
								}

								
								
								
								
								
								
							}
						}

								

						int ProductItemStartX = (int) (InventoryX + InventoryGUIWidth + (247 * handler.getGuiScale()));
						int ProductItemStartY = (int) (InventoryY + (69 * handler.getGuiScale()));
						if(mouseX > ProductItemStartX && mouseX < ProductItemStartX + itemWidth) {
							if(mouseY > ProductItemStartY && mouseY < ProductItemStartY + itemWidth) {
								g.setColor(Assets.tinted_black);
								g.fillRect(ProductItemStartX,ProductItemStartY, itemWidth, itemHeight);
								if(craftingTable.getProductSlot().getCurrentItemStack() != null) {
									currentlyHoveredOn = craftingTable.getProductSlot().getCurrentItemStack();
								}
								
							}
						}
					
					
					
					
					
					
					
				}//render the product slot
				


				
				
				
				
				
				
				
			}
		}
		if((mouseX > (InventoryX + InventoryGUIWidth)) && (mouseX < (InventoryX + InventoryGUIWidth + furnaceWidth))) {
			if(mouseY > (InventoryY + craftHeight) && mouseY < (InventoryY + craftHeight + furnaceHeight)) {
				if(furnace != null) {
					
					
					int furnaceIngredientX = (int) (InventoryX + InventoryGUIWidth + (106 * handler.getGuiScale()));
					int furnaceIngredientY = (int) (InventoryY + craftHeight +  (52 * handler.getGuiScale()));
					
					int furnaceFuelX = (int) (InventoryX + InventoryGUIWidth + (16 * handler.getGuiScale()));
					int furnaceFuelY = (int) (InventoryY + craftHeight +  (106 * handler.getGuiScale()));
					
					int furnaceProductX = (int) (InventoryX + InventoryGUIWidth + (232 * handler.getGuiScale()));
					int furanceProductY = (int) (InventoryY + craftHeight +  (70 * handler.getGuiScale()));
					
					
					///////////////////////////////these 3 slots/////////////////////////////////
					
					
					if(mouseX > furnaceIngredientX && mouseX < furnaceIngredientX + itemWidth) {
						if(mouseY > furnaceIngredientY && mouseY < furnaceIngredientY + itemWidth) {
							g.setColor(Assets.tinted_black);
							g.fillRect(furnaceIngredientX,furnaceIngredientY, itemWidth, itemHeight);
							currentlyHoveredOn = furnace.getIngredientSlot().getCurrentItemStack();
						}
					}
					
					
					if(mouseX > furnaceFuelX && mouseX < furnaceFuelX + itemWidth) {
						if(mouseY > furnaceFuelY && mouseY < furnaceFuelY + itemWidth) {
							g.setColor(Assets.tinted_black);
							g.fillRect(furnaceFuelX,furnaceFuelY, itemWidth, itemHeight);
							currentlyHoveredOn = furnace.getFuelSlot().getCurrentItemStack();
						}
					}
					

					
					if(mouseX > furnaceProductX && mouseX < furnaceProductX + itemWidth) {
						if(mouseY > furanceProductY && mouseY < furanceProductY + itemWidth) {
							g.setColor(Assets.tinted_black);
							g.fillRect(furnaceProductX,furanceProductY, itemWidth, itemHeight);
							currentlyHoveredOn = furnace.getProductSlot().getCurrentItemStack();
						}
					}

					
					
					
					
					
					
					
					
				}
			}
		}
	}
	
	
	



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//The Function Depths of Hell
	
	
	
	
	

	public void tickInventory() {
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		
		boolean insideInventory = false;
		boolean insideCrate = false;
		boolean insideCraft = false;
		boolean insideFurnace = false;
		
		
		if(mouseX > InventoryX && mouseX < InventoryX + InventoryGUIWidth) {
			if(mouseY > InventoryY && mouseY < InventoryY + InventoryGUIHeight) {
				insideInventory = true;
			}
		}else if(mouseX > (InventoryX - crateWidth) && mouseX < InventoryX) {
			if(mouseY > InventoryY && mouseY < (InventoryY + crateHeight)) {
				if(crate != null) {
					insideCrate = true;
				}
				
			}
		}else if(mouseX > InventoryX + InventoryGUIWidth && mouseX < (InventoryX + InventoryGUIWidth + craftWidth)) {
			if(mouseY > InventoryY && mouseY < InventoryY + craftHeight) {
				if(craftingTable != null) {
					insideCraft = true;
				}
				
			}
		}
		if((mouseX > (InventoryX + InventoryGUIWidth)) && (mouseX < (InventoryX + InventoryGUIWidth + furnaceWidth))) {
			if(mouseY > (InventoryY + craftHeight) && mouseY < (InventoryY + craftHeight + furnaceHeight)) {
				if(furnace != null) {
					insideFurnace = true;
				}
			}
		}
		
		
		
		if(insideInventory) {
			
		}else if(insideCrate) {
			clickCrate();
		}else if(insideCraft) {
			clickCrafting();
		}else if(insideFurnace) {
			clickFurnace();
		}else {
			//put item on the ground or plant the crop
			if(leftClick)
				clickedOutSide();
		}
		
		
		
		
		
		
		

		
		
		
	}

	public void clickedOutSide() {

		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		if(currentSelectedItemInInventory != null) {
			//First see if its a dirt Tile
			boolean dirt = false;
			if((handler.getWorld().getBlock((int)((mouseX + handler.getGameCamera().getxOffset()) / Block.BLOCKWIDTH),(int)((mouseY + handler.getGameCamera().getyOffset()) / Block.BLOCKHEIGHT)) == Block.dirtBlock)) {
				dirt = true;
			}

				
			if(currentSelectedItemInInventory.getItem() instanceof Corn && dirt) {
				handler.getWorld().getEntityManager().getEntities().add(new org.toast.entities.statics.Corn(handler, 
						(mouseX + handler.getGameCamera().getxOffset() - 16), 
						(mouseY + handler.getGameCamera().getyOffset() - 16)));
				//Put the item in the world
				currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
				if(currentSelectedItemInInventory.getCount() == 0) {
					currentSelectedItemInInventory = null;
					return;
				}

			}else if(currentSelectedItemInInventory.getItem() instanceof Cotton && dirt) {
				handler.getWorld().getEntityManager().getEntities().add(new org.toast.entities.statics.Cotton(handler, 
						(mouseX + handler.getGameCamera().getxOffset() - 16), 
						(mouseY + handler.getGameCamera().getyOffset() - 16)));
				//Put the item in the world
				currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
				if(currentSelectedItemInInventory.getCount() == 0) {
					currentSelectedItemInInventory = null;
					return;
				}
			}else if(currentSelectedItemInInventory.getItem() instanceof Lettuce && dirt) {
				handler.getWorld().getEntityManager().getEntities().add(new org.toast.entities.statics.Lettuce(handler, 
						(mouseX + handler.getGameCamera().getxOffset() - 16), 
						(mouseY + handler.getGameCamera().getyOffset() - 16)));
				//Put the item in the world
				currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
				if(currentSelectedItemInInventory.getCount() == 0) {
					currentSelectedItemInInventory = null;
					return;
				}
			}else if(currentSelectedItemInInventory.getItem() instanceof PineApple && dirt) {
				handler.getWorld().getEntityManager().getEntities().add(new org.toast.entities.statics.PineApple(handler, 
						(mouseX + handler.getGameCamera().getxOffset() - 16), 
						(mouseY + handler.getGameCamera().getyOffset() - 16)));
				//Put the item in the world
				currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
				if(currentSelectedItemInInventory.getCount() == 0) {
					currentSelectedItemInInventory = null;
					return;
				}
			}else if(currentSelectedItemInInventory.getItem() instanceof CrateItem) {
				handler.getWorld().getEntityManager().getEntities().add(new org.toast.entities.statics.Crate(handler, 
						(mouseX + handler.getGameCamera().getxOffset() - 16), 
						(mouseY + handler.getGameCamera().getyOffset() - 16)));
				//Put the item in the world
				currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
				if(currentSelectedItemInInventory.getCount() == 0) {
					currentSelectedItemInInventory = null;
					return;
				}
			}else if(currentSelectedItemInInventory.getItem() instanceof CraftingStationItem) {
				handler.getWorld().getEntityManager().getEntities().add(new org.toast.entities.statics.CraftingStation(handler, 
						(mouseX + handler.getGameCamera().getxOffset() - 16), 
						(mouseY + handler.getGameCamera().getyOffset() - 16)));
				//Put the item in the world
				currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
				if(currentSelectedItemInInventory.getCount() == 0) {
					currentSelectedItemInInventory = null;
					return;
				}
			}else if(currentSelectedItemInInventory.getItem() instanceof FurnaceItem) {
				handler.getWorld().getEntityManager().getEntities().add(new org.toast.entities.statics.Furnace(handler, 
						(mouseX + handler.getGameCamera().getxOffset() - 16), 
						(mouseY + handler.getGameCamera().getyOffset() - 16)));
				//Put the item in the world
				currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
				if(currentSelectedItemInInventory.getCount() == 0) {
					currentSelectedItemInInventory = null;
					return;
				}
			}else {
				//spit out the item
				
				//render the item in the middle of the mouse cursor
				
				handler.getWorld().getEntityManager().getEntities().add(new ItemEntity(handler,(int)(mouseX + handler.getGameCamera().getxOffset() - 16),(int)(mouseY + handler.getGameCamera().getyOffset() - 16),currentSelectedItemInInventory.getItem().getID(),currentSelectedItemInInventory.getCount()));
				currentSelectedItemInInventory = null;
			}
			
			
			
			}/////////////////////END OF PLACING ITEMS OUTSIDE IN
	
	}

	
	public void clickInventory() {
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();

		
		if(leftClick) {
			
			
			for(int b = 0;b < InventoryHeight;b++) {
				for(int a = 0;a < InventoryWidth;a++) {
					
					if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
						if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
							
							
							//IF WE SHIFT LEFT CLICK AND HAVE A CRATE, THEN SEND ITEMS TO CRATE
							if(crate != null && handler.getKeyManager().shift) {
								if(InventorySlots[a][b].getCurrentItemStack() != null)
									if(crate.canAddItemStack(InventorySlots[a][b].getCurrentItemStack())) {
										crate.addItemStack(InventorySlots[a][b].getCurrentItemStack());
										InventorySlots[a][b].setCurrentItemStack(null);
									
								}
							}else {
								
								
								
								
								if(currentSelectedItemInInventory != null) {
									//Then try and add it to stacks
									if(InventorySlots[a][b].getCurrentItemStack() != null) {
										
										if(InventorySlots[a][b].getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
											int invCount = InventorySlots[a][b].getCurrentItemStack().getCount();
											int handCount = currentSelectedItemInInventory.getCount();
											int maxStackSize = currentSelectedItemInInventory.getItem().getMaxStackSize();
											
											if(invCount < maxStackSize) {//Then the stack in inventory has room
												
												if(invCount + handCount > maxStackSize) {
													int leftover = (invCount + handCount) - maxStackSize;
													InventorySlots[a][b].getCurrentItemStack().setCount(maxStackSize);
													currentSelectedItemInInventory.setCount(leftover);
												}else {
													InventorySlots[a][b].getCurrentItemStack().setCount(invCount + handCount);
													currentSelectedItemInInventory = null;
												}
												
												
											}else {
												ItemStack tempStack = currentSelectedItemInInventory;
												currentSelectedItemInInventory = InventorySlots[a][b].getCurrentItemStack();
												InventorySlots[a][b].setCurrentItemStack(tempStack);
												tempStack = null;
											}
											
											
											
										}else {
											
											
											ItemStack tempStack = currentSelectedItemInInventory;
											currentSelectedItemInInventory = InventorySlots[a][b].getCurrentItemStack();
											InventorySlots[a][b].setCurrentItemStack(tempStack);
											tempStack = null;
											
											
										}

										
										
										
										
										
									}else {//If the slot is empty, then add it
										InventorySlots[a][b].setCurrentItemStack(currentSelectedItemInInventory);
										currentSelectedItemInInventory = null;
									}
								}else {///IF WE DONT HAVE ANYTHING IN OUR HAND YET, AND WE LEFT CLICK
									currentSelectedItemInInventory = InventorySlots[a][b].getCurrentItemStack();
									InventorySlots[a][b].setCurrentItemStack(null); 
								}
								
								
								
								
								
								
							}
							
							


							
							
						}
					}
				

					
					
					
					
					
					
					
					
					
					
					
					
					
				}	
			}
		}else if(rightClick) {
			for(int b = 0;b < InventoryHeight;b++) {
				for(int a = 0;a < InventoryWidth;a++) {

					
					if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
						if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
							
							//we Right clicked and we are in InventorySlots[a][b]
							//System.out.println(mouseX + " , " + mouseY);
							//System.out.println("[" + a + "][" + b + "]");
							
							if(currentSelectedItemInInventory != null) {
//								//if we have something, and the slot is empty, put down 1 item
//								if(InventorySlots[a][b].getCurrentItemStack() == null) {
//									//Then place the new Stack
//									InventorySlots[a][b].setCurrentItemStack(new ItemStack(currentSelectedItemInInventory.getItem(),1));
//									currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
//									if(currentSelectedItemInInventory.getCount() == 0) {
//										currentSelectedItemInInventory = null;
//									}
//								}else {
//									//We have something in our hand, and same thing in slot
//									if(InventorySlots[a][b].getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
//										//then there the same item
//										//first check if the max stack size is one, then do nothing
//										if(currentSelectedItemInInventory.getItem().getMaxStackSize() != 1) {
//											if(InventorySlots[a][b].getCurrentItemStack().getCount() + 1 <= currentSelectedItemInInventory.getItem().getMaxStackSize()) {
//												//then we want to add 1 more to the stack
//												InventorySlots[a][b].getCurrentItemStack().setCount(InventorySlots[a][b].getCurrentItemStack().getCount() + 1);
//												currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
//												if(currentSelectedItemInInventory.getCount() == 0) {
//													currentSelectedItemInInventory = null;
//												}
//											}
//										}
//										
//			
//									}
//								}
							}else {
								//if we dont already have something, then take half of it
								if(InventorySlots[a][b].getCurrentItemStack() != null) {//If we have nothing in our hand, but there is something in the slot, and we right clicked
									if(InventorySlots[a][b].getCurrentItemStack().getCount() == 1) {
										//if the count is 1, just take it
										currentSelectedItemInInventory = InventorySlots[a][b].getCurrentItemStack();
										InventorySlots[a][b].setCurrentItemStack(null);
										return;
									}
									
									int count = InventorySlots[a][b].getCurrentItemStack().getCount();
									
									if(count % 2 == 0) {
										//Then its an even number
										int newcount = count / 2;
										InventorySlots[a][b].getCurrentItemStack().setCount(newcount);
										currentSelectedItemInInventory = new ItemStack(Item.items[InventorySlots[a][b].getCurrentItemStack().getItem().getID()],newcount);
									}else {
										//then its an odd number, not 1
										int firstCount = count / 2;		//Gets truncated, so 5 would return 2.5 -> 2
										int secondCount = count / 2 + 1;//Gets truncated, so 5 would return 2.5 -> 2 + 1 = 3
										InventorySlots[a][b].getCurrentItemStack().setCount(firstCount);
										currentSelectedItemInInventory = new ItemStack(Item.items[InventorySlots[a][b].getCurrentItemStack().getItem().getID()],secondCount);
									}
									
									
									
									
									
									
								}
							}
							
							
						}/////////////////////////END OF FOR LOOP
					}
					
	
					
				}
			}
		}
		
		
		
		//right drag click start
		if(rightDragClick && !rightClick) {
			
			for(int b = 0;b < InventoryHeight;b++) {
				for(int a = 0;a < InventoryWidth;a++) {
					
					if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
						if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
							//the current hovered slot
							
							
							if(InventorySlots[a][b].getCurrentItemStack() != null && currentSelectedItemInInventory != null) {
								if(currentSelectedItemInInventory.getItem().getID() == InventorySlots[a][b].getCurrentItemStack().getItem().getID() && (lastA != a || lastB != b)) {
									//they are the same item, add
									InventorySlots[a][b].getCurrentItemStack().setCount(InventorySlots[a][b].getCurrentItemStack().getCount() + 1);
									currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
									if(currentSelectedItemInInventory.getCount() == 0) {
										currentSelectedItemInInventory = null;
									}
									System.out.println(a + ", " + b); 
									lastA = a;
									lastB = b;
									
								}
							}else if(currentSelectedItemInInventory != null && InventorySlots[a][b].getCurrentItemStack() == null && (lastA != a || lastB != b)) {
								InventorySlots[a][b].setCurrentItemStack(new ItemStack(currentSelectedItemInInventory.getItem(),1));
								currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
								if(currentSelectedItemInInventory.getCount() == 0) {
									currentSelectedItemInInventory = null;
								}	
								System.out.println(a + ", " + b);
								
								lastA = a;
								lastB = b;
							}
							
							
							
						}/////////////////////////////////////////Mouse Y
					}/////////////////////////////////////////Mouse X

					
					

					
					
					
				}
			}

			
			
			
		}
		
		
		
		
		
		
		
	}
	public void clickCrafting() {
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		
		int ItemStartX = (int) (InventoryX + InventoryGUIWidth + (60 * handler.getGuiScale()));
		int ItemStartY = (int) (InventoryY + (34 * handler.getGuiScale()));
		
		if(leftClick) {
			for(int b = 0;b < CraftingStation.containerHeight;b++) {
				for(int a = 0;a < CraftingStation.containerWidth;a++) {
					
					if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
						if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
							
							
							if(currentSelectedItemInInventory != null) {		//We have an item in our "hand" and left click
								if(craftingTable.getContents()[a][b].getCurrentItemStack() != null) {
									

									
									if(craftingTable.getContents()[a][b].getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
										int invCount = craftingTable.getContents()[a][b].getCurrentItemStack().getCount();
										int handCount = currentSelectedItemInInventory.getCount();
										int maxStackSize = currentSelectedItemInInventory.getItem().getMaxStackSize();
										
										if(invCount < maxStackSize) {//Then the stack in inventory has room
											
											if(invCount + handCount > maxStackSize) {
												int leftover = (invCount + handCount) - maxStackSize;
												craftingTable.getContents()[a][b].getCurrentItemStack().setCount(maxStackSize);
												currentSelectedItemInInventory.setCount(leftover);
											}else {
												craftingTable.getContents()[a][b].getCurrentItemStack().setCount(invCount + handCount);
												currentSelectedItemInInventory = null;
											}
											
											
										}else {
											ItemStack tempStack = currentSelectedItemInInventory;
											currentSelectedItemInInventory = craftingTable.getContents()[a][b].getCurrentItemStack();
											craftingTable.getContents()[a][b].setCurrentItemStack(tempStack);
											tempStack = null;
										}
										
										
										
									}else {
										
										
										ItemStack tempStack = currentSelectedItemInInventory;
										currentSelectedItemInInventory = craftingTable.getContents()[a][b].getCurrentItemStack();
										craftingTable.getContents()[a][b].setCurrentItemStack(tempStack);
										tempStack = null;
										
										
									}
									
									
									
									
									
									
								}else {//If the slot is empty, then add it
									craftingTable.getContents()[a][b].setCurrentItemStack(currentSelectedItemInInventory);
									currentSelectedItemInInventory = null;
								}
							}else {///IF WE DONT HAVE ANYTHING IN OUR HAND YET, AND WE LEFT CLICK
								
								if(handler.getKeyManager().shift) {
									if(craftingTable.getContents()[a][b].getCurrentItemStack() != null && (canAddItemStack(craftingTable.getContents()[a][b].getCurrentItemStack()) >= craftingTable.getContents()[a][b].getCurrentItemStack().getCount())) {
										addItemStack(craftingTable.getContents()[a][b].getCurrentItemStack());
										craftingTable.getContents()[a][b].setCurrentItemStack(null);
									}

								}else {
									currentSelectedItemInInventory = craftingTable.getContents()[a][b].getCurrentItemStack();
									craftingTable.getContents()[a][b].setCurrentItemStack(null);
								}
							}
							
							
							//We just changed the data in the crafting table, reload the crafting recipes so we cant craft stuff after we took ingreidents out
							craftingTable.reloadRecipes();
							
							 
					}
					
					
					
					}
					
				
					
					
					
					
				}
			}
			/////////////////////////////////////////////////////////////////////////////////LEFTCLICK IN PRODUCT SLOT//////////////////////////////////////////////////////////////
			//Check the product slot as well
			int ProductItemStartX = (int) (InventoryX + InventoryGUIWidth + (247 * handler.getGuiScale()));
			int ProductItemStartY = (int) (InventoryY + (69 * handler.getGuiScale()));
			if(mouseX > ProductItemStartX && mouseX < ProductItemStartX + itemWidth) {
				if(mouseY > ProductItemStartY && mouseY < ProductItemStartY + itemWidth) {
					
					
					//ADD SHIFT LEFT CLICK FOR PRODUCT SLOT
					if(craftingTable.getProductSlot().getCurrentItemStack() != null && handler.getKeyManager().shift) {
						//if we shift left click on an itemstack in product slot
						
						//then craft all we can fit in the inventory, or how much we have
						int max = craftingTable.getShiftMaxCraftAmount();
						int howmuchwecanfit = canAddItemStack(new ItemStack(craftingTable.getProductSlot().getCurrentItemStack().getItem(),craftingTable.getCraftableRecipe().getProduct().getCount() * max));
						if(howmuchwecanfit >= craftingTable.getCraftableRecipe().getProduct().getCount() * max) {//Then we send it to the inventory
							addItemStack(new ItemStack(craftingTable.getProductSlot().getCurrentItemStack().getItem(),craftingTable.getCraftableRecipe().getProduct().getCount() * max));
							
							craftingTable.clickedProductSlotFlag(max);			//THEN CRAFT
						}else {
							//Then dont craft
						}
						
						
						
						
						
					}////////////////////////////END OF SHIFT METHOD
					
					
					
					
					
				
					//wE HAVE LEFT CLICKED IN THE PRODUCT SLOT
					if(craftingTable.getProductSlot().getCurrentItemStack() != null && !handler.getKeyManager().shift) {
						//there is stuff if the Product slot
						
						if(currentSelectedItemInInventory != null) {//If we have Stuff and we left Click
							
							//Then we are either holding the same item and have space to grab it or we are holding shift and we can send it to the inventory
							if(currentSelectedItemInInventory.getItem().getID() == craftingTable.getProductSlot().getCurrentItemStack().getItem().getID()) {
								
								if(currentSelectedItemInInventory.getCount() + craftingTable.getProductSlot().getCurrentItemStack().getCount() <= currentSelectedItemInInventory.getItem().getMaxStackSize()) {
									currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() + craftingTable.getProductSlot().getCurrentItemStack().getCount());
									craftingTable.getProductSlot().setCurrentItemStack(null);
								}
								
							}else {
									
							}
							
							
						}else {
								currentSelectedItemInInventory = new ItemStack(craftingTable.getProductSlot().getCurrentItemStack().getItem(),craftingTable.getProductSlot().getCurrentItemStack().getCount());
								craftingTable.getProductSlot().setCurrentItemStack(null);
						}
						
						
						
					}
					
					craftingTable.clickedProductSlotFlag(1);
					
				}
			}
			
			
			
			
			//Because we just changed the data of the Crafting table by moving itemstacks around taking some etc, etc, then update the recipes
			craftingTable.reloadRecipes();
			
			
		}else if(rightClick) {																	///////////////////CRAFTING RIGHT CLICK
			
			for(int b = 0;b < CraftingStation.containerHeight;b++) {
				for(int a = 0;a < CraftingStation.containerWidth;a++) {
					
				if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
					if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
						
						
						if(currentSelectedItemInInventory != null) {
//							//if we have something, and the slot is empty, put down 1 item
//							if(craftingTable.getContents()[a][b].getCurrentItemStack() == null) {
//								//Then place the new Stack
//								craftingTable.getContents()[a][b].setCurrentItemStack(new ItemStack(currentSelectedItemInInventory.getItem(),1));
//								currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
//								if(currentSelectedItemInInventory.getCount() == 0) {
//									currentSelectedItemInInventory = null;
//								}
//							}else {
//								//We have something in our hand, and there is something in the slot
//								if(currentSelectedItemInInventory.getItem().getMaxStackSize() != 1) {
//									if(craftingTable.getContents()[a][b].getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
//										//then there the same item
//										if(craftingTable.getContents()[a][b].getCurrentItemStack().getCount() + 1 <= currentSelectedItemInInventory.getItem().getMaxStackSize()) {
//											//then we want to add 1 more to the stack
//											craftingTable.getContents()[a][b].getCurrentItemStack().setCount(craftingTable.getContents()[a][b].getCurrentItemStack().getCount() + 1);
//											currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
//											if(currentSelectedItemInInventory.getCount() == 0) {
//												currentSelectedItemInInventory = null;
//											}
//										}
//									}
//								}
//
//							}
						}else {
							//if we dont already have something, then take half of it
							if(craftingTable.getContents()[a][b].getCurrentItemStack() != null) {//If we have nothing in our hand, but there is something in the slot, and we right clicked
								if(craftingTable.getContents()[a][b].getCurrentItemStack().getCount() == 1) {
									//if the count is 1, just take it
									currentSelectedItemInInventory = craftingTable.getContents()[a][b].getCurrentItemStack();
									craftingTable.getContents()[a][b].setCurrentItemStack(null);
									return;
								}
								
								int count = craftingTable.getContents()[a][b].getCurrentItemStack().getCount();
								
								if(count % 2 == 0) {
									//Then its an even number
									int newcount = count / 2;
									craftingTable.getContents()[a][b].getCurrentItemStack().setCount(newcount);
									currentSelectedItemInInventory = new ItemStack(Item.items[craftingTable.getContents()[a][b].getCurrentItemStack().getItem().getID()],newcount);
								}else {
									//then its an odd number, not 1
									int firstCount = count / 2;		//Gets truncated, so 5 would return 2.5 -> 2
									int secondCount = count / 2 + 1;//Gets truncated, so 5 would return 2.5 -> 2 + 1 = 3
									craftingTable.getContents()[a][b].getCurrentItemStack().setCount(firstCount);
									currentSelectedItemInInventory = new ItemStack(Item.items[craftingTable.getContents()[a][b].getCurrentItemStack().getItem().getID()],secondCount);
								}
								
								
								
								
								
								
							}
						}
						
						
					}
				}

					
					

					
					
				}
			}
			
			//Check the product slot as well
			int ProductItemStartX = (int) (InventoryX + InventoryGUIWidth + (247 * handler.getGuiScale()));
			int ProductItemStartY = (int) (InventoryY + (69 * handler.getGuiScale()));
			if(mouseX > ProductItemStartX && mouseX < ProductItemStartX + itemWidth) {
				if(mouseY > ProductItemStartY && mouseY < ProductItemStartY + itemWidth) {
					
					//we have right clicked on the product slot,
					if(craftingTable.getProductSlot().getCurrentItemStack() != null) {
						//there is stuff if the Product slot
						
						if(currentSelectedItemInInventory != null) {//If we have Stuff and we left Click
							
							//Then we are either holding the same item and have space to grab it or we are holding shift and we can send it to the inventory
							if(currentSelectedItemInInventory.getItem().getID() == craftingTable.getProductSlot().getCurrentItemStack().getItem().getID()) {
								
								if(currentSelectedItemInInventory.getCount() + craftingTable.getProductSlot().getCurrentItemStack().getCount() < currentSelectedItemInInventory.getItem().getMaxStackSize()) {
									currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() + craftingTable.getProductSlot().getCurrentItemStack().getCount());
									craftingTable.getProductSlot().setCurrentItemStack(null);
								}
								
							}
							
						////////////////////////////END OF WE HAVE STUFF IN OUR HAND
						}else {
							currentSelectedItemInInventory = new ItemStack(craftingTable.getProductSlot().getCurrentItemStack().getItem(),craftingTable.getProductSlot().getCurrentItemStack().getCount());
							craftingTable.getProductSlot().setCurrentItemStack(null);
						}
						
						
						
					}
					
					
					craftingTable.clickedProductSlotFlag(1);
					
				}
			}

			
			
			
			
			//Because we just changed the data of the Crafting table by moving itemstacks around taking some etc, etc, then update the recipes
			craftingTable.reloadRecipes();
			
		}/////////////////////////END OF RIGHT CLICK
		
		
		//right drag click start
		if(rightDragClick && !rightClick) {
			
			for(int b = 0;b < CraftingStation.containerHeight;b++) {
				for(int a = 0;a < CraftingStation.containerWidth;a++) {
					
					if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
						if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
							//the current hovered slot
							
							
							if(craftingTable.getContents()[a][b].getCurrentItemStack() != null && currentSelectedItemInInventory != null) {
								if(currentSelectedItemInInventory.getItem().getID() == craftingTable.getContents()[a][b].getCurrentItemStack().getItem().getID() && (lastA != a || lastB != b)) {
									//they are the same item, add
									craftingTable.getContents()[a][b].getCurrentItemStack().setCount(craftingTable.getContents()[a][b].getCurrentItemStack().getCount() + 1);
									currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
									if(currentSelectedItemInInventory.getCount() == 0) {
										currentSelectedItemInInventory = null;
									}
									lastA = a;
									lastB = b;
									
								}
							}else if(currentSelectedItemInInventory != null && craftingTable.getContents()[a][b].getCurrentItemStack() == null && (lastA != a || lastB != b)) {
								craftingTable.getContents()[a][b].setCurrentItemStack(new ItemStack(currentSelectedItemInInventory.getItem(),1));
								currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
								if(currentSelectedItemInInventory.getCount() == 0) {
									currentSelectedItemInInventory = null;
								}	
								
								lastA = a;
								lastB = b;
							}
							
							
							
						}/////////////////////////////////////////Mouse Y
					}/////////////////////////////////////////Mouse X

					
					

					
					
					
				}
			}

			
			
			
		}
		
		
		
		
	
	}
	public void clickCrate() {
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		
		int ItemStartX = (int) (InventoryX - crateWidth + (24 * handler.getGuiScale()));
		int ItemStartY = (int) (InventoryY + (16 * handler.getGuiScale()));
		
		if(leftClick) {
			for(int b = 0;b < Crate.containerHeight;b++) {
				for(int a = 0;a < Crate.containerWidth;a++) {
					
					if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
						if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
							
							
							if(currentSelectedItemInInventory != null) {		//We have an item in our "hand" and left click
								if(crate.getContents()[a][b].getCurrentItemStack() != null) {
									


									
									if(crate.getContents()[a][b].getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
										int invCount = crate.getContents()[a][b].getCurrentItemStack().getCount();
										int handCount = currentSelectedItemInInventory.getCount();
										int maxStackSize = currentSelectedItemInInventory.getItem().getMaxStackSize();
										
										if(invCount < maxStackSize) {//Then the stack in inventory has room
											
											if(invCount + handCount > maxStackSize) {
												int leftover = (invCount + handCount) - maxStackSize;
												crate.getContents()[a][b].getCurrentItemStack().setCount(maxStackSize);
												currentSelectedItemInInventory.setCount(leftover);
											}else {
												crate.getContents()[a][b].getCurrentItemStack().setCount(invCount + handCount);
												currentSelectedItemInInventory = null;
											}
											
											
										}else {
											ItemStack tempStack = currentSelectedItemInInventory;
											currentSelectedItemInInventory = crate.getContents()[a][b].getCurrentItemStack();
											crate.getContents()[a][b].setCurrentItemStack(tempStack);
											tempStack = null;
										}
										
										
										
									}else {
										
										
										ItemStack tempStack = currentSelectedItemInInventory;
										currentSelectedItemInInventory = crate.getContents()[a][b].getCurrentItemStack();
										crate.getContents()[a][b].setCurrentItemStack(tempStack);
										tempStack = null;
										
										
									}
									
									
									
									
									
									
									
									
								}else {//If the slot is empty, then add it
									crate.getContents()[a][b].setCurrentItemStack(currentSelectedItemInInventory);
									currentSelectedItemInInventory = null;
								}
							}else {///IF WE DONT HAVE ANYTHING IN OUR HAND YET, AND WE LEFT CLICK
								
								if(handler.getKeyManager().shift) {
									if(crate.getContents()[a][b].getCurrentItemStack() != null && (canAddItemStack(crate.getContents()[a][b].getCurrentItemStack()) >= crate.getContents()[a][b].getCurrentItemStack().getCount())) {
										addItemStack(crate.getContents()[a][b].getCurrentItemStack());
										crate.getContents()[a][b].setCurrentItemStack(null);
									}

								}else {
									currentSelectedItemInInventory = crate.getContents()[a][b].getCurrentItemStack();
									crate.getContents()[a][b].setCurrentItemStack(null);
								}
								
							
							
							
						}
							
							 
					}
					
					
					
					}
					
				
					
					
					
					
				}
			}
			
		}else if(rightClick) {
			
			for(int b = 0;b < Crate.containerHeight;b++) {
				for(int a = 0;a < Crate.containerWidth;a++) {
					
				if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
					if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
						
						
						if(currentSelectedItemInInventory != null) {
//							//if we have something, and the slot is empty, put down 1 item
//							if(crate.getContents()[a][b].getCurrentItemStack() == null) {
//								//Then place the new Stack
//								crate.getContents()[a][b].setCurrentItemStack(new ItemStack(currentSelectedItemInInventory.getItem(),1));
//								currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
//								if(currentSelectedItemInInventory.getCount() == 0) {
//									currentSelectedItemInInventory = null;
//								}
//							}else {
//								//We have something in our hand, and there is something in the slot
//								if(currentSelectedItemInInventory.getItem().getMaxStackSize() != 1) {
//									if(crate.getContents()[a][b].getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
//										//then there the same item
//										if(crate.getContents()[a][b].getCurrentItemStack().getCount() + 1 <= currentSelectedItemInInventory.getItem().getMaxStackSize()) {
//											//then we want to add 1 more to the stack
//											crate.getContents()[a][b].getCurrentItemStack().setCount(crate.getContents()[a][b].getCurrentItemStack().getCount() + 1);
//											currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
//											if(currentSelectedItemInInventory.getCount() == 0) {
//												currentSelectedItemInInventory = null;
//											}
//										}
//									}
//								}
//
//							}
						}else {
							//if we dont already have something, then take half of it
							if(crate.getContents()[a][b].getCurrentItemStack() != null) {//If we have nothing in our hand, but there is something in the slot, and we right clicked
								if(crate.getContents()[a][b].getCurrentItemStack().getCount() == 1) {
									//if the count is 1, just take it
									currentSelectedItemInInventory = crate.getContents()[a][b].getCurrentItemStack();
									crate.getContents()[a][b].setCurrentItemStack(null);
									return;
								}
								
								int count = crate.getContents()[a][b].getCurrentItemStack().getCount();
								
								if(count % 2 == 0) {
									//Then its an even number
									int newcount = count / 2;
									crate.getContents()[a][b].getCurrentItemStack().setCount(newcount);
									currentSelectedItemInInventory = new ItemStack(Item.items[crate.getContents()[a][b].getCurrentItemStack().getItem().getID()],newcount);
								}else {
									//then its an odd number, not 1
									int firstCount = count / 2;		//Gets truncated, so 5 would return 2.5 -> 2
									int secondCount = count / 2 + 1;//Gets truncated, so 5 would return 2.5 -> 2 + 1 = 3
									crate.getContents()[a][b].getCurrentItemStack().setCount(firstCount);
									currentSelectedItemInInventory = new ItemStack(Item.items[crate.getContents()[a][b].getCurrentItemStack().getItem().getID()],secondCount);
								}
								
								
								
								
								
								
							}
						}
						
						
					}
				}

					
					

					
					
				}
			}
			
		}/////////////////////////END OF RIGHT CLICK
	
		//right drag click start
		if(rightDragClick && !rightClick) {
			
			for(int b = 0;b < Crate.containerHeight;b++) {
				for(int a = 0;a < Crate.containerWidth;a++) {
					
					if(mouseX > ItemStartX + (a * (itemWidth + gapX)) && mouseX < ItemStartX + (a * (itemWidth + gapX)) + itemWidth) {
						if(mouseY > ItemStartY + (b * (itemHeight + gapY)) && mouseY < ItemStartY + (b * (itemHeight + gapY)) + itemHeight) {
							//the current hovered slot
							
							
							if(crate.getContents()[a][b].getCurrentItemStack() != null && currentSelectedItemInInventory != null) {
								if(currentSelectedItemInInventory.getItem().getID() == crate.getContents()[a][b].getCurrentItemStack().getItem().getID() && (lastA != a || lastB != b)) {
									//they are the same item, add
									crate.getContents()[a][b].getCurrentItemStack().setCount(crate.getContents()[a][b].getCurrentItemStack().getCount() + 1);
									currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
									if(currentSelectedItemInInventory.getCount() == 0) {
										currentSelectedItemInInventory = null;
									}
									System.out.println(a + ", " + b); 
									lastA = a;
									lastB = b;
									
								}
							}else if(currentSelectedItemInInventory != null && crate.getContents()[a][b].getCurrentItemStack() == null && (lastA != a || lastB != b)) {
								crate.getContents()[a][b].setCurrentItemStack(new ItemStack(currentSelectedItemInInventory.getItem(),1));
								currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
								if(currentSelectedItemInInventory.getCount() == 0) {
									currentSelectedItemInInventory = null;
								}	
								System.out.println(a + ", " + b);
								
								lastA = a;
								lastB = b;
							}
							
							
							
						}/////////////////////////////////////////Mouse Y
					}/////////////////////////////////////////Mouse X

					
					

					
					
					
				}
			}

			
			
			
		}
		
	
	}
	public void clickFurnace() {
		int mouseX = handler.getMouseManager().getMouseX();
		int mouseY = handler.getMouseManager().getMouseY();
		
		int furnaceIngredientX = (int) (InventoryX + InventoryGUIWidth + (106 * handler.getGuiScale()));
		int furnaceIngredientY = (int) (InventoryY + craftHeight +  (52 * handler.getGuiScale()));
		
		int furnaceFuelX = (int) (InventoryX + InventoryGUIWidth + (16 * handler.getGuiScale()));
		int furnaceFuelY = (int) (InventoryY + craftHeight +  (106 * handler.getGuiScale()));
		
		int furnaceProductX = (int) (InventoryX + InventoryGUIWidth + (232 * handler.getGuiScale()));
		int furanceProductY = (int) (InventoryY + craftHeight +  (70 * handler.getGuiScale()));
		
		
		///////////////////////////////these 3 slots/////////////////////////////////
		
		if(mouseX > furnaceIngredientX && mouseX < furnaceIngredientX + itemWidth) {
			if(mouseY > furnaceIngredientY && mouseY < furnaceIngredientY + itemWidth) {/////////////////////////////////////////////////////INGREDIENT/////////////////////////////////////////
				
				if(leftClick) {
					if(currentSelectedItemInInventory != null) {		//We have an item in our "hand" and left click
						if(furnace.getIngredientSlot().getCurrentItemStack() != null) {
							

							
							
							
							if(furnace.getIngredientSlot().getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
								int invCount = furnace.getIngredientSlot().getCurrentItemStack().getCount();
								int handCount = currentSelectedItemInInventory.getCount();
								int maxStackSize = currentSelectedItemInInventory.getItem().getMaxStackSize();
								
								if(invCount < maxStackSize) {//Then the stack in inventory has room
									
									if(invCount + handCount > maxStackSize) {
										int leftover = (invCount + handCount) - maxStackSize;
										furnace.getIngredientSlot().getCurrentItemStack().setCount(maxStackSize);
										currentSelectedItemInInventory.setCount(leftover);
									}else {
										furnace.getIngredientSlot().getCurrentItemStack().setCount(invCount + handCount);
										currentSelectedItemInInventory = null;
									}
									
									
								}else {
									ItemStack tempStack = currentSelectedItemInInventory;
									currentSelectedItemInInventory = furnace.getIngredientSlot().getCurrentItemStack();
									furnace.getIngredientSlot().setCurrentItemStack(tempStack);
									tempStack = null;
								}
								
								
								
							}else {
								
								
								ItemStack tempStack = currentSelectedItemInInventory;
								currentSelectedItemInInventory = furnace.getIngredientSlot().getCurrentItemStack();
								furnace.getIngredientSlot().setCurrentItemStack(tempStack);
								tempStack = null;
								
								
							}
							
							
							
							
							
						}else {//If the slot is empty, then add it
							furnace.getIngredientSlot().setCurrentItemStack(currentSelectedItemInInventory);
							currentSelectedItemInInventory = null;
						}
					}else {///IF WE DONT HAVE ANYTHING IN OUR HAND YET, AND WE LEFT CLICK
						
						if(handler.getKeyManager().shift) {
							if(furnace.getIngredientSlot().getCurrentItemStack() != null) {
								addItemStack(furnace.getIngredientSlot().getCurrentItemStack());
								furnace.getIngredientSlot().setCurrentItemStack(null);
							}

						}else {
							currentSelectedItemInInventory = furnace.getIngredientSlot().getCurrentItemStack();
							furnace.getIngredientSlot().setCurrentItemStack(null);
						}
					}
					
					
					
				}/////////////////////END OF LEFT CLICK
				
				if(rightClick) {
					if(currentSelectedItemInInventory != null) {
						//if we have something, and the slot is empty, put down 1 item
						if(furnace.getIngredientSlot().getCurrentItemStack() == null) {
							//Then place the new Stack
							furnace.getIngredientSlot().setCurrentItemStack(new ItemStack(currentSelectedItemInInventory.getItem(),1));
							currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
							if(currentSelectedItemInInventory.getCount() == 0) {
								currentSelectedItemInInventory = null;
							}
						}else {
							//We have something in our hand, and there is something in the slot
							if(furnace.getIngredientSlot().getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
								//then there the same item
								if(furnace.getIngredientSlot().getCurrentItemStack().getCount() + 1 <= currentSelectedItemInInventory.getItem().getMaxStackSize()) {
									//then we want to add 1 more to the stack
									furnace.getIngredientSlot().getCurrentItemStack().setCount(furnace.getIngredientSlot().getCurrentItemStack().getCount() + 1);
									currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
									if(currentSelectedItemInInventory.getCount() == 0) {
										currentSelectedItemInInventory = null;
									}
								}
							}
						}
					}else {
						//if we dont already have something, then take half of it
						if(furnace.getIngredientSlot().getCurrentItemStack() != null) {//If we have nothing in our hand, but there is something in the slot, and we right clicked
							if(furnace.getIngredientSlot().getCurrentItemStack().getCount() == 1) {
								//if the count is 1, just take it
								currentSelectedItemInInventory = furnace.getIngredientSlot().getCurrentItemStack();
								furnace.getIngredientSlot().setCurrentItemStack(null);
								return;
							}
							
							int count = furnace.getIngredientSlot().getCurrentItemStack().getCount();
							
							if(count % 2 == 0) {
								//Then its an even number
								int newcount = count / 2;
								furnace.getIngredientSlot().getCurrentItemStack().setCount(newcount);
								currentSelectedItemInInventory = new ItemStack(Item.items[furnace.getIngredientSlot().getCurrentItemStack().getItem().getID()],newcount);
							}else {
								//then its an odd number, not 1
								int firstCount = count / 2;		//Gets truncated, so 5 would return 2.5 -> 2
								int secondCount = count / 2 + 1;//Gets truncated, so 5 would return 2.5 -> 2 + 1 = 3
								furnace.getIngredientSlot().getCurrentItemStack().setCount(firstCount);
								currentSelectedItemInInventory = new ItemStack(Item.items[furnace.getIngredientSlot().getCurrentItemStack().getItem().getID()],secondCount);
							}
							
							
							
							
							
							
						}
					}
				}
				
				
				
				
				
			}
		}
		
		if(mouseX > furnaceFuelX && mouseX < furnaceFuelX + itemWidth) {
			if(mouseY > furnaceFuelY && mouseY < furnaceFuelY + itemWidth) {		/////////////////////////////////////////////////////FUEL/////////////////////////////////////////
				
				if(leftClick) {
					if(currentSelectedItemInInventory != null) {		//We have an item in our "hand" and left click
						if(furnace.getFuelSlot().getCurrentItemStack() != null) {
							


							
							
							if(furnace.getFuelSlot().getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
								int invCount = furnace.getFuelSlot().getCurrentItemStack().getCount();
								int handCount = currentSelectedItemInInventory.getCount();
								int maxStackSize = currentSelectedItemInInventory.getItem().getMaxStackSize();
								
								if(invCount < maxStackSize) {//Then the stack in inventory has room
									
									if(invCount + handCount > maxStackSize) {
										int leftover = (invCount + handCount) - maxStackSize;
										furnace.getFuelSlot().getCurrentItemStack().setCount(maxStackSize);
										currentSelectedItemInInventory.setCount(leftover);
									}else {
										furnace.getFuelSlot().getCurrentItemStack().setCount(invCount + handCount);
										currentSelectedItemInInventory = null;
									}
									
									
								}else {
									ItemStack tempStack = currentSelectedItemInInventory;
									currentSelectedItemInInventory = furnace.getFuelSlot().getCurrentItemStack();
									furnace.getFuelSlot().setCurrentItemStack(tempStack);
									tempStack = null;
								}
								
								
								
							}else {
								
								
								ItemStack tempStack = currentSelectedItemInInventory;
								currentSelectedItemInInventory = furnace.getFuelSlot().getCurrentItemStack();
								furnace.getFuelSlot().setCurrentItemStack(tempStack);
								tempStack = null;
								
								
							}
							
							
							
							
							
							
							
						}else {//If the slot is empty, then add it
							furnace.getFuelSlot().setCurrentItemStack(currentSelectedItemInInventory);
							currentSelectedItemInInventory = null;
						}
					}else {///IF WE DONT HAVE ANYTHING IN OUR HAND YET, AND WE LEFT CLICK
						
						if(handler.getKeyManager().shift) {
							if(furnace.getFuelSlot().getCurrentItemStack() != null) {
								addItemStack(furnace.getFuelSlot().getCurrentItemStack());
								furnace.getFuelSlot().setCurrentItemStack(null);
							}

						}else {
							currentSelectedItemInInventory = furnace.getFuelSlot().getCurrentItemStack();
							furnace.getFuelSlot().setCurrentItemStack(null);
						}
					}
					
					
					
				}/////////////////////END OF LEFT CLICK
				
				if(rightClick) {
					if(currentSelectedItemInInventory != null) {
						//if we have something, and the slot is empty, put down 1 item
						if(furnace.getFuelSlot().getCurrentItemStack() == null) {
							//Then place the new Stack
							furnace.getFuelSlot().setCurrentItemStack(new ItemStack(currentSelectedItemInInventory.getItem(),1));
							currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
							if(currentSelectedItemInInventory.getCount() == 0) {
								currentSelectedItemInInventory = null;
							}
						}else {
							//We have something in our hand, and there is something in the slot
							if(furnace.getFuelSlot().getCurrentItemStack().getItem().getID() == currentSelectedItemInInventory.getItem().getID()) {
								//then there the same item
								if(furnace.getFuelSlot().getCurrentItemStack().getCount() + 1 <= currentSelectedItemInInventory.getItem().getMaxStackSize()) {
									//then we want to add 1 more to the stack
									furnace.getFuelSlot().getCurrentItemStack().setCount(furnace.getFuelSlot().getCurrentItemStack().getCount() + 1);
									currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() - 1);
									if(currentSelectedItemInInventory.getCount() == 0) {
										currentSelectedItemInInventory = null;
									}
								}
							}
						}
					}else {
						//if we dont already have something, then take half of it
						if(furnace.getFuelSlot().getCurrentItemStack() != null) {//If we have nothing in our hand, but there is something in the slot, and we right clicked
							if(furnace.getFuelSlot().getCurrentItemStack().getCount() == 1) {
								//if the count is 1, just take it
								currentSelectedItemInInventory = furnace.getFuelSlot().getCurrentItemStack();
								furnace.getFuelSlot().setCurrentItemStack(null);
								return;
							}
							
							int count = furnace.getFuelSlot().getCurrentItemStack().getCount();
							
							if(count % 2 == 0) {
								//Then its an even number
								int newcount = count / 2;
								furnace.getFuelSlot().getCurrentItemStack().setCount(newcount);
								currentSelectedItemInInventory = new ItemStack(Item.items[furnace.getFuelSlot().getCurrentItemStack().getItem().getID()],newcount);
							}else {
								//then its an odd number, not 1
								int firstCount = count / 2;		//Gets truncated, so 5 would return 2.5 -> 2
								int secondCount = count / 2 + 1;//Gets truncated, so 5 would return 2.5 -> 2 + 1 = 3
								furnace.getFuelSlot().getCurrentItemStack().setCount(firstCount);
								currentSelectedItemInInventory = new ItemStack(Item.items[furnace.getFuelSlot().getCurrentItemStack().getItem().getID()],secondCount);
							}
							
							
							
							
							
							
						}
					}
				}
				
			}
		}
		
		if(mouseX > furnaceProductX && mouseX < furnaceProductX + itemWidth) {
			if(mouseY > furanceProductY && mouseY < furanceProductY + itemWidth) {				/////////////////////////////////////////////////////PRODUCT/////////////////////////////////////////
				
					if(leftClick) {
						
					if(furnace.getProductSlot().getCurrentItemStack() != null) {
							//there is stuff if the Product slot
							
							if(currentSelectedItemInInventory != null) {//If we have Stuff and we left Click
								
								//Then we are either holding the same item and have space to grab it or we are holding shift and we can send it to the inventory
								if(currentSelectedItemInInventory.getItem().getID() == furnace.getProductSlot().getCurrentItemStack().getItem().getID()) {
									
									if(currentSelectedItemInInventory.getCount() + furnace.getProductSlot().getCurrentItemStack().getCount() <= currentSelectedItemInInventory.getItem().getMaxStackSize()) {
										currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() + furnace.getProductSlot().getCurrentItemStack().getCount());
										furnace.getProductSlot().setCurrentItemStack(null);
									}
									
								}else {
										
								}
								
								
							}else {
									currentSelectedItemInInventory = new ItemStack(furnace.getProductSlot().getCurrentItemStack().getItem(),furnace.getProductSlot().getCurrentItemStack().getCount());
									furnace.getProductSlot().setCurrentItemStack(null);
							}
							
							
							
						}
						
						
					}else if(rightClick) {
						if(furnace.getProductSlot().getCurrentItemStack() != null) {
							//there is stuff if the Product slot
							
							if(currentSelectedItemInInventory != null) {//If we have Stuff and we left Click
								
								//Then we are either holding the same item and have space to grab it or we are holding shift and we can send it to the inventory
								if(currentSelectedItemInInventory.getItem().getID() == furnace.getProductSlot().getCurrentItemStack().getItem().getID()) {
									
									if(currentSelectedItemInInventory.getCount() + furnace.getProductSlot().getCurrentItemStack().getCount() < currentSelectedItemInInventory.getItem().getMaxStackSize()) {
										currentSelectedItemInInventory.setCount(currentSelectedItemInInventory.getCount() + furnace.getProductSlot().getCurrentItemStack().getCount());
										furnace.getProductSlot().setCurrentItemStack(null);
									}
									
								}
								
							////////////////////////////END OF WE HAVE STUFF IN OUR HAND
							}else {
								currentSelectedItemInInventory = new ItemStack(furnace.getProductSlot().getCurrentItemStack().getItem(),furnace.getProductSlot().getCurrentItemStack().getCount());
								furnace.getProductSlot().setCurrentItemStack(null);
							}
							
							
							
						}
					}
				
			}
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	public void clearInventory() {											//Clear whole inventory
		for(int b = 0;b < InventoryHeight;b++) {
			for(int a = 0;a < InventoryWidth;a++) {
				InventorySlots[a][b].setCurrentItemStack(null); 
			}
		}
	}
	public void swapInventorySlots(int a,int b,int a2,int b2) {
		
		ItemStack t = InventorySlots[a][b].getCurrentItemStack();
		ItemStack r = InventorySlots[a2][b2].getCurrentItemStack();
		
		InventorySlots[a][b].setCurrentItemStack(r);
		InventorySlots[a2][b2].setCurrentItemStack(t);
		
		
	}
	
	public void addItemStack(ItemStack is) {
		
		//First thing we do is loop through our inventory, and see if any itemstacks already exist, and have space
		
		for(int b = 0;b < InventoryHeight;b++) {
			for(int a = 0;a < InventoryWidth;a++) {
					
				if(InventorySlots[a][b].getCurrentItemStack() != null) {
					
					if(InventorySlots[a][b].getCurrentItemStack().getItem().getID() == is.getItem().getID()) {//then they are the same item
						
						if(InventorySlots[a][b].getCurrentItemStack().getCount() + is.getCount() <= is.getItem().getMaxStackSize()) {//the itemstack will fit in this stack
							//then add them
							InventorySlots[a][b].getCurrentItemStack().setCount(InventorySlots[a][b].getCurrentItemStack().getCount() + is.getCount());
							return;
						}else {																										//the item stack wont fit in this stack
							
							if(InventorySlots[a][b].getCurrentItemStack().getCount() == is.getItem().getMaxStackSize()) {
								//the items have the same id,but this stack is full
							}else {
								//this stack isnt full, it just doesnt have enough space for everything
								int leftover = InventorySlots[a][b].getCurrentItemStack().getCount() + is.getCount() - is.getItem().getMaxStackSize();
								InventorySlots[a][b].getCurrentItemStack().setCount(is.getItem().getMaxStackSize());
								addItemStack(new ItemStack(is.getItem(),leftover));
								return;
								
							}
							
							
						}
						
						
						
					}
					
					
					
					
				}

				
				
				
			}
		}
		
		
		//We have already checked if we can add it to an item, we cant, so add it to the next available slot
		
		//EDIT: Try and add the item to the hotbar first
		for(int a = 0;a < InventoryWidth;a++) {
			if(InventorySlots[a][3].getCurrentItemStack() == null) {
				InventorySlots[a][3].setCurrentItemStack(new ItemStack(is.getItem(),is.getCount()));
				return;
			}
		}
		
		
		
		for(int b = 0;b < InventoryHeight;b++) {
			for(int a = 0;a < InventoryWidth;a++) {
				if(InventorySlots[a][b].getCurrentItemStack() == null) {	//Then the slot is open
					
					InventorySlots[a][b].setCurrentItemStack(new ItemStack(is.getItem(),is.getCount()));
					return;
					
					
				}
			}
		}

		
		
		
		
		
		
		
		
	}
	public int canAddItemStack(ItemStack is) {								//Returns total possible number ItemStacks could fit
		int amount = 0;
		@SuppressWarnings("unused")
		int amountLeftFromOriginalStack = is.getCount();
		
		//we want to see how much we can fit
		
		
		for(int b = 0;b < InventoryHeight;b++) {
			for(int a = 0;a < InventoryWidth;a++) {
				
				InventorySlot slot = InventorySlots[a][b];
				
				//For each slot, check to see if its empty, has an item, and the same item
				if(slot.getCurrentItemStack() != null) {
					//check to see if they are the same item
					if(slot.getCurrentItemStack().getItem().getID() == is.getItem().getID()) {
						//they are the same
						if(slot.getCurrentItemStack().getCount() < is.getItem().getMaxStackSize()) {
							//then there is room left
							//find out how much
							int leftoverroom = is.getItem().getMaxStackSize() - slot.getCurrentItemStack().getCount();
							amountLeftFromOriginalStack -= leftoverroom;
							amount += leftoverroom;
							
						}
						
					}else {
						//they are not the same, do nothing
					}
					
					
				}else {
					//then we can fit it all
					//we want to stop, no need to check further slots
					amount += is.getItem().getMaxStackSize();
				}
				
				
				
				
			}
		}

		
		
		return amount;
	}
	///////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////
	public InventorySlot getInventorySlot(int a, int b) {
		return InventorySlots[a][b];
	}
	
	public Crate getCrate() {
		return crate;
	}
	public void setCrate(Crate crate) {
		this.crate = crate;
	}
	public CraftingStation getCraftingTable() {
		return craftingTable;
	}
	public void setCraftingTable(CraftingStation craftingTable) {
		this.craftingTable = craftingTable;
	}
	public Furnace getFurnace() {
		return furnace;
	}
	public void setFurnace(Furnace furnace) {
		this.furnace = furnace;
	}
	public ItemStack getCurrentItemStackInHand() {
		return currentSelectedItemInInventory;
	}
	
	
	
	
	
	
	
	
	
	
	
}
