package org.toast.blocks.wood.planks;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class JunglePlanksBlock extends Block{

	public JunglePlanksBlock(int id) {
		super(Assets.planks[4], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
