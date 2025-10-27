package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class MaplePlanksBlockItem extends BlockItem{

	public MaplePlanksBlockItem(int id,int maxStackSize) {
		super(Assets.planks[2], id,maxStackSize,Block.maplePlanks);
		displayName = "Maple Planks";
	}
	
	
	
	
}
