package org.toast.blocks.wood.planks;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class PinePlanksBlock extends Block{

	public PinePlanksBlock(int id) {
		super(Assets.planks[1], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
