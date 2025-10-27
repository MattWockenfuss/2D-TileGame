package org.toast.blocks.wood.logs;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class PineLogBlock extends Block{

	public PineLogBlock(int id) {
		super(Assets.logs[1], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
