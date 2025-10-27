package org.toast.blocks;

import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Assets;
import org.toast.item.Item;

public class DirtBlock extends Block{

	public DirtBlock(int id) {
		super(Assets.dirt, id);
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.dirt_block,1);
	}

}
