package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcretePurple extends BlockItem{

	public ConcretePurple(int id,int maxStackSize) {
		super(Assets.concrete[7], id,maxStackSize,Block.purpleConcrete);
		displayName = "Purple Concrete";
	}
	
	
	
	
}
