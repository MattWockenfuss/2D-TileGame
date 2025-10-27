package org.toast.states;

import java.awt.Color;
import java.awt.Graphics;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.gfx.Assets;

public class LoadingState extends State{
	
	private int progress = 0;//Int from 0 to 100
	private String text = "";//Text to display
	
	
	public LoadingState(Handler handler) {
		super(handler);
	}

	int timer = 0;
	long lastTime = System.currentTimeMillis();
	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		if(timer > 10 && progress < 400) {
			timer = 0;
			progress++;
			
			if(progress == 400) {
				progress = 0;
				GameStateManager.setState(handler.getMenuState()); 
			}
			
			
		}

		
	}
	public void render(Graphics g) {
		
		for(int y = 0;y < handler.getHeight() / Block.BLOCKHEIGHT + 1;y++) {
			for(int x = 0;x < handler.getWidth() / Block.BLOCKWIDTH;x++) {
				
				if((x > 3 && x < 6) && y == 11) {
					g.drawImage(Assets.graphite_ore, x * Block.BLOCKWIDTH, y * Block.BLOCKHEIGHT, Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
				}else {
					g.drawImage(Assets.stone[2], x * Block.BLOCKWIDTH, y * Block.BLOCKHEIGHT, Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
				}
				
			}
		}
		
		
		g.drawImage(Assets.playerDown[0], handler.getWidth() / 2 - (64), 200,128,128,null);
		g.setFont(Assets.menu); 
		g.drawString(text, handler.getWidth() / 2 - (text.length() * Assets.menu.getSize() / 4), handler.getHeight() / 2 - 100);
		
		
		int rectWidth = 400;
		int rectHeight = 40;
		int margin = 4;
		int startX = (handler.getWidth() / 2) - ((rectWidth + margin) / 2);
		int startY = (handler.getHeight() / 2) - ((rectHeight + margin) / 2);
		g.setColor(Color.black);
		g.fillRect(startX, startY, rectWidth + margin, rectHeight + margin); 
		g.setColor(Color.blue);
		g.fillRect(startX + margin / 2, startY + margin / 2, progress, rectHeight); 
		
	}
	
	
	

}
