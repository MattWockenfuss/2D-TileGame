package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Grass4BlockItem extends BlockItem{

	public Grass4BlockItem(int id,int maxStackSize) {
		super(Assets.grass[3], id,maxStackSize,Block.jungleGrassBlock);
		displayName = "Grass";
	}
	
	
	
	
}
