package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Grass1BlockItem extends BlockItem{

	public Grass1BlockItem(int id,int maxStackSize) {
		super(Assets.grass[0], id,maxStackSize,Block.grassBlock);
		displayName = "Grass";
	}
	
	
	
	
}
