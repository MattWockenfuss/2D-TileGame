package org.toast.states.MultiPlayer;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.gfx.Assets;
import org.toast.net.GameClient;
import org.toast.states.GameStateManager;
import org.toast.states.State;
import org.toast.ui.ClickListener;
import org.toast.ui.UIImageButton;
import org.toast.ui.UIManager;

public class MultiPlayerState extends State{
	
	private UIManager MultiplayerUIManager;
	private int buttonWidth,buttonHeight;
	
	private GameClient socketClient;
	//private GameServer socketServer;
	//USE A MULTIPLAYERGAMESTATE NOT SINGLEPLAYER GAMESTATE
	
	
	
	public MultiPlayerState(Handler handler) {
		super(handler);
		MultiplayerUIManager = new UIManager(handler);
		buttonWidth = handler.getWidth() / 4;
		buttonHeight = handler.getHeight() / 17;
		
		MultiplayerUIManager.addObject(new UIImageButton((handler.getWidth() / 2 - buttonWidth / 2),(handler.getHeight() - 90),buttonWidth,buttonHeight,Assets.buttons,new ClickListener() {
			
			public void onClick() {
				GameStateManager.setState(handler.getMenuState()); 
			}
			
		}));
			

		

		
		
		
		
		
		
		
		
		
		
		
		
	}


	public void tick() {
		if (handler.getMouseManager().getUIManager() != MultiplayerUIManager)
			handler.getMouseManager().setUIManager(MultiplayerUIManager);
		MultiplayerUIManager.tick();
		handler.getKeyManager().tick();
		
		if(handler.getKeyManager().enter && handler.getGame().getSeconds() > 1) {
			String message = "02" + JOptionPane.showInputDialog("Enter a message for the console!");
			if(message.equalsIgnoreCase("02exit")) {
				socketClient.sendData(("01" + socketClient.getUserName() + "::" + socketClient.getIP()).getBytes());
				System.exit(0); 
			}else {
				socketClient.sendData((message + "//" + socketClient.getUserName()).getBytes()); 
			}
			 handler.getGame().resetClock();
		}
		
		
		
	}
	
	
	@SuppressWarnings("deprecation")
	public void switchedToMultiplayerState() {
		if(socketClient != null) {
			socketClient.getSocket().close();
			socketClient.stop();
			socketClient = null;
		}
		socketClient = new GameClient(handler,JOptionPane.showInputDialog("Please enter the IP of the server!"),JOptionPane.showInputDialog("Please enter a Username!")); 
		//socketClient = new GameClient(handler,"localhost","Matt");
		socketClient.start();
		
		//System.out.println(("00" + socketClient.getUserName() + "::" + socketClient.getIP()).getBytes()); 
		
		socketClient.sendData(("00" + socketClient.getUserName() + "::" + socketClient.getIP()).getBytes());   
	}
	
	
	
	public void render(Graphics g) {
		
		for(int x = 0;x < (handler.getWidth() / Block.BLOCKWIDTH);x++) {
			for(int y = 0;y < (handler.getHeight() / Block.BLOCKHEIGHT + 1);y++) {
				g.drawImage(Assets.ice, x * Block.BLOCKWIDTH, y * Block.BLOCKHEIGHT + 1, Block.BLOCKWIDTH,Block.BLOCKHEIGHT,null);
			}
		}
		
		
		MultiplayerUIManager.render(g); 
		
		g.setColor(Color.white);
		g.setFont(Assets.menu);
		g.drawString("Back", (handler.getWidth() / 2 - 30), handler.getHeight() - 62); 
	}
	
	
	
	///////////////////////////////////////////////////GETTERS AND SETTERS/////////////////////////////////////////////////////////
	public GameClient getGameClient() {
		return socketClient;
	}

}
