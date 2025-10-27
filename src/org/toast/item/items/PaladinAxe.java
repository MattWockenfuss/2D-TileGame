package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class PaladinAxe extends Item{

	public PaladinAxe(int id,int maxStackSize) {
		super(Assets.paladin_axe, id,maxStackSize);
		displayName = "Paladin Axe";
	}
	public int getStrength() {
		return 1000;
	}
	
	
	
}
