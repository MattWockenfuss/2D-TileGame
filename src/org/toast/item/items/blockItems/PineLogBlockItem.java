package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class PineLogBlockItem extends BlockItem{

	public PineLogBlockItem(int id,int maxStackSize) {
		super(Assets.logs[1], id,maxStackSize,Block.pineLog);
		displayName = "Log";
	}
	
	
	
	
}
