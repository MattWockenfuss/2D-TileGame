package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Grass3BlockItem extends BlockItem{

	public Grass3BlockItem(int id,int maxStackSize) {
		super(Assets.grass[2], id,maxStackSize,Block.savannaGrassBlock);
		displayName = "Grass";
	}
	
	
	
	
}
