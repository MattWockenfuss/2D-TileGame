package org.toast.item.items.blockItems;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class FirPlanksBlockItem extends BlockItem{

	public FirPlanksBlockItem(int id,int maxStackSize) {
		super(Assets.planks[0], id,maxStackSize,Block.firPlanks);
		displayName = "Fir Planks";
	}
	
	
	
	
}
