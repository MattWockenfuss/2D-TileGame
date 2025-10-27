package org.toast.blocks.animated;

import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Animation;
import org.toast.item.Item;

public class DiamondOreBlock extends AnimatedBlock{


	public DiamondOreBlock(int id, Animation animation) {
		super(animation.getCurrentFrame(), id, animation); 
	}
	
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		return new ItemStack(Item.Diamond,1);
	}
	
	
	
}
