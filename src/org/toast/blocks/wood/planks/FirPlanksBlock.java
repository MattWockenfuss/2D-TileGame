package org.toast.blocks.wood.planks;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class FirPlanksBlock extends Block{

	public FirPlanksBlock(int id) {
		super(Assets.planks[0], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
