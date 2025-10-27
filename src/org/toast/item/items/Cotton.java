package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class Cotton extends Item{
	
	protected boolean canBePlaced = true;

	public Cotton(int id,int maxStackSize) {
		super(Assets.cotton, id,maxStackSize);
		displayName = "Cotton is King";
	}
	
	
	
	
}
