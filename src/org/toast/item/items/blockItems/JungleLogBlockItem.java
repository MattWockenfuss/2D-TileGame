package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class JungleLogBlockItem extends BlockItem{

	public JungleLogBlockItem(int id,int maxStackSize) {
		super(Assets.logs[4], id,maxStackSize,Block.jungleLog);
		displayName = "Log";
	}
	
	
	
	
}
