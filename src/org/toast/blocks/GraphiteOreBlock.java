package org.toast.blocks;

import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Assets;
import org.toast.item.Item;
import org.toast.utils.Utils;

public class GraphiteOreBlock extends Block{

	public GraphiteOreBlock(int id) {
		super(Assets.graphite_ore,id);
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.graphite_dust,Utils.randomNumber(2, 4));
	}
	
	
	
}
