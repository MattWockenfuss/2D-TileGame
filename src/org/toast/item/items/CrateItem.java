package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class CrateItem extends Item{
	
	protected boolean canBePlaced = true;

	public CrateItem(int id,int maxStackSize) {
		super(Assets.crate, id,maxStackSize);
		displayName = "Crate";
	}
	
	
	
}
