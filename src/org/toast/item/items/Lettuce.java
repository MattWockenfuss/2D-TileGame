package org.toast.item.items;

import org.toast.gfx.Assets;
import org.toast.item.Item;

public class Lettuce extends Item{
	
	protected boolean canBePlaced = true;

	public Lettuce(int id,int maxStackSize) {
		super(Assets.lettuce, id,maxStackSize);
		displayName = "Lettuce";
	}
	
	
	
	
}
