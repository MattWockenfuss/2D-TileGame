package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class StonePickaxe extends Item{

	public StonePickaxe(int id,int maxStackSize) {
		super(Assets.stone_pickaxe, id,maxStackSize);
		displayName = "Stone Pickaxe";
	}
	
	public int getStrength() {
		return 5;
	}
	
	
	
	
}
