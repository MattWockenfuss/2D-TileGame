package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcreteGreen extends BlockItem{

	public ConcreteGreen(int id,int maxStackSize) {
		super(Assets.concrete[3], id,maxStackSize,Block.greenConcrete);
		displayName = "Green Concrete";
	}
	
	
	
	
}
