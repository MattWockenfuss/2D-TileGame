package org.toast.blocks.wood.logs;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class FirLogBlock extends Block{

	public FirLogBlock(int id) {
		super(Assets.logs[0], id); 
	}
	
	public boolean isSolid() {
		return true;
	}

}
