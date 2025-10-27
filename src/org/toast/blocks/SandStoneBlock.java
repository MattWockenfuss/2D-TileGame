package org.toast.blocks;

import org.toast.blocks.Block;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Assets;
import org.toast.item.Item;

public class SandStoneBlock extends Block{

	public SandStoneBlock(int id) {
		super(Assets.sandstone, id);
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.sandstone_block,1);
	}
	
	

}
