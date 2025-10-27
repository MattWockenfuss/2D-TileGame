package org.toast.item.items.blockItems;

import java.awt.image.BufferedImage;

import org.toast.blocks.Block;
import org.toast.item.Item;

public class BlockItem extends Item{
	
	Block b;

	public BlockItem(BufferedImage texture, int id, int maxStackSize, Block b) {
		super(texture, id, maxStackSize);
		this.b = b;
	}
	
	public Block getBlock() {
		return b;
	}
	
	
	
	
	

}
