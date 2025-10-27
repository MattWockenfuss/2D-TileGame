package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class ConcreteAqua extends BlockItem{

	public ConcreteAqua(int id,int maxStackSize) {
		super(Assets.concrete[5], id,maxStackSize,Block.aquaConcrete);
		displayName = "Aqua Concrete";
	}
	
	
	
	
}
