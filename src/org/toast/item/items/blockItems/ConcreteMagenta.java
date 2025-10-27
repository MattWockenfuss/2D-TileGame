package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcreteMagenta extends BlockItem{

	public ConcreteMagenta(int id,int maxStackSize) {
		super(Assets.concrete[6], id,maxStackSize,Block.magentaConcrete);
		displayName = "Magenta Concrete";
	}
	
	
	
	
}
