package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class Stone4BlockItem extends BlockItem{

	public Stone4BlockItem(int id,int maxStackSize) {
		super(Assets.stone[3], id,maxStackSize,Block.stoneBlock4);
		displayName = "Stone (4/5)";
	}
	
	
	
	
}
