package org.toast.blocks;

import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Assets;
import org.toast.item.Item;

public class IceBlock extends Block{

	public IceBlock(int id) {
		super(Assets.ice, id);
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.ice_block,1);
	}
}
