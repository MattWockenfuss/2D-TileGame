package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class SavannaLogBlockItem extends BlockItem{

	public SavannaLogBlockItem(int id,int maxStackSize) {
		super(Assets.logs[3], id,maxStackSize,Block.savannaLog);
		displayName = "Log";
	}
	
	
	
	
}
