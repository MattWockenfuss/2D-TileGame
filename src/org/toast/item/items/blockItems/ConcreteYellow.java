package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcreteYellow extends BlockItem{

	public ConcreteYellow(int id,int maxStackSize) {
		super(Assets.concrete[2], id,maxStackSize,Block.yellowConcrete);
		displayName = "Yellow Concrete";
	}
	
	
	
	
}
