package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcreteBlue extends BlockItem{

	public ConcreteBlue(int id,int maxStackSize) {
		super(Assets.concrete[4], id,maxStackSize,Block.blueConcrete);
		displayName = "Blue Concrete";
	}
	
	
	
	
}
