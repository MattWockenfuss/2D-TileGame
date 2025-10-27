package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Stone5BlockItem extends BlockItem{

	public Stone5BlockItem(int id,int maxStackSize) {
		super(Assets.stone[4], id,maxStackSize,Block.stoneBlock5);
		displayName = "Stone (5/5)";
	}
	
	
	
	
}
