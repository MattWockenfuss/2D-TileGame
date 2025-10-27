package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class PineApple extends Item{
	
	protected boolean canBePlaced = true;

	public PineApple(int id,int maxStackSize) {
		super(Assets.pineapple, id,maxStackSize);
		displayName = "Pineapple";
	}
	
	
	
	
}
