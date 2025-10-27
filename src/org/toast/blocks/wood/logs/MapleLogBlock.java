package org.toast.blocks.wood.logs;

import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class MapleLogBlock extends Block{

	public MapleLogBlock(int id) {
		super(Assets.logs[2], id);
	}
	
	public boolean isSolid() {
		return true;
	}

}
