package org.toast.blocks.stone;

import org.toast.blocks.Block;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Assets;
import org.toast.item.Item;

public class Stone3 extends Block{

	public Stone3(int id) {
		super(Assets.stone[2], id);
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.stone3,1);
	}
	
	

}
