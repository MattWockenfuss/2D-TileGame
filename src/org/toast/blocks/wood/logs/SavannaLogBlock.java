package org.toast.blocks.wood.logs;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class SavannaLogBlock extends Block{

	public SavannaLogBlock(int id) {
		super(Assets.logs[3], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
