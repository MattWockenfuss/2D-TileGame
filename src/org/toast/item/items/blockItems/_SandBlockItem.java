package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class _SandBlockItem extends BlockItem{

	public _SandBlockItem(int id,int maxStackSize) {
		super(Assets.sand, id,maxStackSize,Block.sandBlock);
		displayName = "Sand";
	}
	
	
	
	
}
