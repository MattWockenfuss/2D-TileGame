package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcretePink extends BlockItem{

	public ConcretePink(int id,int maxStackSize) {
		super(Assets.concrete[8], id,maxStackSize,Block.pinkConcrete);
		displayName = "Pink Concrete";
	}
	
	
	
	
}
