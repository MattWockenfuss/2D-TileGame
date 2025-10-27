package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class Corn extends Item{
	
	protected boolean canBePlaced = true;

	public Corn(int id,int maxStackSize) {
		super(Assets.corn, id,maxStackSize);
		displayName = "Corn";
	}
	
	
	
	
}
