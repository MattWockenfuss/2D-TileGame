package org.toast.blocks.wood.planks;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class SavannaPlanksBlock extends Block{

	public SavannaPlanksBlock(int id) {
		super(Assets.planks[3], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
