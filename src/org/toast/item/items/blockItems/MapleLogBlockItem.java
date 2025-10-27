package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class MapleLogBlockItem extends BlockItem{

	public MapleLogBlockItem(int id,int maxStackSize) {
		super(Assets.logs[2], id,maxStackSize,Block.mapleLog);
		displayName = "Log";
	}
	
	
	
	
}
