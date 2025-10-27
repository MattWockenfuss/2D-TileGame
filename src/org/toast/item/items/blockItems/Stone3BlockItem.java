package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Stone3BlockItem extends BlockItem{

	public Stone3BlockItem(int id,int maxStackSize) {
		super(Assets.stone[2], id,maxStackSize,Block.stoneBlock3);
		displayName = "Stone (3/5)";
	}
	
	
	
	
}
