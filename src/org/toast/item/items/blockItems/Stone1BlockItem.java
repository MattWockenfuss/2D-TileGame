package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Stone1BlockItem extends BlockItem{

	public Stone1BlockItem(int id,int maxStackSize) {
		super(Assets.stone[0], id,maxStackSize,Block.stoneBlock1);
		displayName = "Stone (1/5)";
	}
	
	
	
	
}
