package org.toast.blocks.color;

import java.awt.image.BufferedImage;

import org.toast.blocks.Block;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.item.Item;

public class Concrete extends Block{

	public Concrete(BufferedImage texture, int id) {
		super(texture, id);
	}
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		switch(id) {
		case 40:
			return new ItemStack(Item.RedConcrete,1);
		case 41:
			return new ItemStack(Item.OrangeConcrete,1);
		case 42:
			return new ItemStack(Item.YellowConcrete,1);
		case 43:
			return new ItemStack(Item.GreenConcrete,1);
		case 44:
			return new ItemStack(Item.BlueConcrete,1);
		case 45:
			return new ItemStack(Item.AquaConcrete,1);
		case 46:
			return new ItemStack(Item.MagentaConcrete,1);
		case 47:
			return new ItemStack(Item.PurpleConcrete,1);
		case 48:
			return new ItemStack(Item.PinkConcrete,1);
		case 49:
			return new ItemStack(Item.WhiteConcrete,1);
		}
		return null; 
		
	}
	
	

}
