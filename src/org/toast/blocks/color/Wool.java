package org.toast.blocks.color;

import java.awt.image.BufferedImage;

import org.toast.blocks.Block;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.item.Item;

public class Wool extends Block{

	public Wool(BufferedImage texture, int id) {
		super(texture, id);
	}
	public boolean isSolid() {
		return true;
	}
	public ItemStack getItemDropped() {
		switch(id) {
		case 30:
			return new ItemStack(Item.RedWool,1);
		case 31:
			return new ItemStack(Item.OrangeWool,1);
		case 32:
			return new ItemStack(Item.YellowWool,1);
		case 33:
			return new ItemStack(Item.GreenWool,1);
		case 34:
			return new ItemStack(Item.BlueWool,1);
		case 35:
			return new ItemStack(Item.AquaWool,1);
		case 36:
			return new ItemStack(Item.MagentaWool,1);
		case 37:
			return new ItemStack(Item.PurpleWool,1);
		case 38:
			return new ItemStack(Item.PinkWool,1);
		case 39:
			return new ItemStack(Item.WhiteWool,1);
		}
		return null; 
		
	}
}
