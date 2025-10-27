package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcreteWhite extends BlockItem{

	public ConcreteWhite(int id,int maxStackSize) {
		super(Assets.concrete[9], id,maxStackSize,Block.whiteConcrete);
		displayName = "White Concrete";
	}
	
	
	
	
}
