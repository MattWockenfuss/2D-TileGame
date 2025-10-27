package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcreteOrange extends BlockItem{

	public ConcreteOrange(int id,int maxStackSize) {
		super(Assets.concrete[1], id,maxStackSize,Block.orangeConcrete);
		displayName = "Orange Concrete";
	}
	
	
	
	
}
