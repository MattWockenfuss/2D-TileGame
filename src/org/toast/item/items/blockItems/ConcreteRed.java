package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcreteRed extends BlockItem{

	public ConcreteRed(int id,int maxStackSize) {
		super(Assets.concrete[0], id,maxStackSize,Block.redConcrete);
		displayName = "Red Concrete";
	}
	
	
	
	
}
