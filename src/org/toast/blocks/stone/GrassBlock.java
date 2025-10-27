package org.toast.blocks.stone;

import org.toast.blocks.Block;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Assets;
import org.toast.item.Item;

public class GrassBlock extends Block{

	public GrassBlock(int id) {
		super(Assets.grass[0],id);
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.grass_block1,1);
	}
	
}
