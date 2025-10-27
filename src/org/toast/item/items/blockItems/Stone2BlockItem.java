package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Stone2BlockItem extends BlockItem{

	public Stone2BlockItem(int id,int maxStackSize) {
		super(Assets.stone[1], id,maxStackSize,Block.stoneBlock2);
		displayName = "Stone (2/5)";
	}
	
	
	
	
}
