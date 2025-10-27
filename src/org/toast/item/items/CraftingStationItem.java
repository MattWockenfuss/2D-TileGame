package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class CraftingStationItem extends Item{
	
	protected boolean canBePlaced = true;

	public CraftingStationItem(int id,int maxStackSize) {
		super(Assets.crafting_station, id,maxStackSize);
		displayName = "Crafting Table";
	}

	
	
}
