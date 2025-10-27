package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class FirLogBlockItem extends BlockItem{

	public FirLogBlockItem(int id,int maxStackSize) {
		super(Assets.logs[0], id,maxStackSize,Block.firLog);
		displayName = "Log";
	}
	
	
	
	
}
