package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class WoolYellow extends BlockItem{

	public WoolYellow(int id,int maxStackSize) {
		super(Assets.wool[2], id,maxStackSize,Block.yellowWool);
		displayName = "Yellow Wool";
	}
	
	
	
	
}
