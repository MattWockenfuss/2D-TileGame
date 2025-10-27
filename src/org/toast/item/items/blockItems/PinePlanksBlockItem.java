package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class PinePlanksBlockItem extends BlockItem{

	public PinePlanksBlockItem(int id,int maxStackSize) {
		super(Assets.planks[1], id,maxStackSize,Block.pinePlanks);
		displayName = "Pine Planks";
	}
	
	
	
	
}
