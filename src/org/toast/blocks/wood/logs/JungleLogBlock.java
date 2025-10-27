package org.toast.blocks.wood.logs;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class JungleLogBlock extends Block{

	public JungleLogBlock(int id) {
		super(Assets.logs[4], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
