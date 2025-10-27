package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class FurnaceItem extends Item{
	
	protected boolean canBePlaced = true;

	public FurnaceItem(int id,int maxStackSize) {
		super(Assets.furnace[0], id,maxStackSize);
		displayName = "Furnace";
	}
	
	
}
