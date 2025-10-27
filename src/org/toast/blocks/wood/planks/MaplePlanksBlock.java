package org.toast.blocks.wood.planks;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class MaplePlanksBlock extends Block{

	public MaplePlanksBlock(int id) {
		super(Assets.planks[2], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
