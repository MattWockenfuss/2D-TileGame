package org.toast.blocks;

import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Assets;
import org.toast.item.Item;

public class SandBlock extends Block{

	public SandBlock(int id) {
		super(Assets.sand, id);
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.sand_block,1);
	}
}
