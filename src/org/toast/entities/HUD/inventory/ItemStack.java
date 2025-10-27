package org.toast.entities.HUD.inventory;

import org.toast.item.Item;

public class ItemStack {
	
	private Item item;
	private int count;
	
	public ItemStack(Item i,int count) {
		this.item = i;
		if(count > i.getMaxStackSize()) {
			count = i.getMaxStackSize();
		}
		this.count = count;

			
	}
	
	public ItemStack(Item i) {		//if no count specfied, than the default count is 1
		this.item = i;
		this.count = 1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////////////////GETTERS AND SETTERS/////////////////////////////////////////////////////
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
	
}
