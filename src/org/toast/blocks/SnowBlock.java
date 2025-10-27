package org.toast.blocks;

import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Assets;
import org.toast.item.Item;

public class SnowBlock extends Block{

	public SnowBlock(int id) {
		super(Assets.snow, id);
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.snow_block,1);
	}
}
