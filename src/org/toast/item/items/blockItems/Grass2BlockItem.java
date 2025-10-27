package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Grass2BlockItem extends BlockItem{

	public Grass2BlockItem(int id,int maxStackSize) {
		super(Assets.grass[1], id,maxStackSize,Block.tundraGrassBlock);
		displayName = "Grass";
	}
	
	
	
	
}
