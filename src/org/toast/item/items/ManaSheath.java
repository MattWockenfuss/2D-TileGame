package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class ManaSheath extends Item{
	
	

	public ManaSheath(int id,int maxStackSize) {
		super(Assets.mana_sheath, id,maxStackSize);
		displayName = "Mana Sheath";
	}
	
	
}
