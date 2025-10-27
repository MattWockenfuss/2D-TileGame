package org.toast;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import org.toast.display.Display;
import org.toast.gfx.Assets;
import org.toast.gfx.GameCamera;
import org.toast.input.KeyManager;
import org.toast.input.MouseManager;
import org.toast.item.craftingrecipes.RecipeLoader;
import org.toast.states.AchievementState;
import org.toast.states.GameStateManager;
import org.toast.states.LoadingState;
import org.toast.states.MenuState;
import org.toast.states.SettingsState;
import org.toast.states.State;
import org.toast.states.MultiPlayer.MultiPlayerGameState;
import org.toast.states.MultiPlayer.MultiPlayerState;
import org.toast.states.SinglePlayer.SinglePlayerGameState;
import org.toast.states.SinglePlayer.SinglePlayerState;
public class Game implements Runnable{
	

	private Display display;
	//private int width,height;
	private boolean fullscreen;
	public String title;
	
	private boolean running = false;
	private Thread thread;
	private int seconds = 0;
	private int minutes = 0;
	private int hours = 0;
	private int FPS = 0;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private State gameState;
	private State menuState;
	private State settingsState;
	private State singlePlayerState;
	private State multiplayerState;
	private State multiplayerGameState;
	private State achievementState;
	@SuppressWarnings("unused")
	private State loadingState;
	
	//Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	//Camera
	private GameCamera gameCamera;
	private Handler handler;
	
	public Game(String title,boolean fullscreen) {
		this.title = title;
		this.fullscreen = fullscreen;
		
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
	}
	
	
	
	
	private void init() {
		handler = new Handler(this);
		Assets.init();
		RecipeLoader.loadRecipes();
		gameCamera = new GameCamera(handler,0,0);
		
		display = new Display(handler,title,fullscreen);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getFrame().addMouseWheelListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseWheelListener(mouseManager);
		

		refreshStates();
		GameStateManager.setState(menuState);
		

	}
	
	private void tick() {
		if(seconds >= 60) {
			seconds = 0;
			minutes++;
		}
		if(minutes >= 60) {
			minutes = 0;
			hours++;
		}
		if(GameStateManager.getState() != null) {
			GameStateManager.getState().tick();
		}
		
		
		//System.out.println("SCREEN: " + handler.getWidth() + ", " + handler.getHeight());
		
		
	}
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null || bs.getDrawGraphics() == null) { 
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		//Clear SCREEN
		g.clearRect(0, 0, handler.getWidth(), handler.getHeight()); 
		//DRAWING
		
		if(GameStateManager.getState() != null) {
			GameStateManager.getState().render(g);
		}
		
		//END DRAWING
		bs.show();
		g.dispose();
	}
	
	
	//////////////////////////////////////THREAD METHODS/////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	public void run() {
		init();
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0; //every second clock
		long timer2 = 0; //5th second clock
		int ticks = 0;
		int frames = 0;
		boolean shouldRender = false;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			timer2 += now - lastTime;
			lastTime = now;
			if(delta >= 1) {
				ticks++;
				tick();
				shouldRender = true;
				delta--;
			}

			
//			try {
//				Thread.sleep(2);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} 
			
			if(shouldRender) {
				render();
				frames++;
			}
			
			if(timer >= 1000000000) {
				handler.getDisplay().getFrame().setTitle("FPS: " + frames); 
				FPS = frames;
				ticks = 0;
				frames = 0;
				timer = 0;
				seconds++;
				 
			}
			

		}
		stop();
		
		
	}
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this); 
		thread.start();
		
	}
	public synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	//////////////////////////////////////GETTERS AND SETTERS/////////////////////////////////////////////////
	//GAME OBJECTS
	public KeyManager getKeyManager() {
		return keyManager;
	}
	public MouseManager getMouseManager() {
		return mouseManager;
	}
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	public Display getDisplay() {
		return display;
	}
	
	
	//STATES
	public void refreshStates() {
		 
		gameState = new SinglePlayerGameState(handler);
		menuState = new MenuState(handler);
		settingsState = new SettingsState(handler);
		singlePlayerState = new SinglePlayerState(handler);
		multiplayerState = new MultiPlayerState(handler);
		multiplayerGameState = new MultiPlayerGameState(handler);
		achievementState = new AchievementState(handler);
		loadingState = new LoadingState(handler);
		GameStateManager.setState(menuState);
	}
	public SinglePlayerGameState getGameState() {
		return (SinglePlayerGameState) gameState; 
	}
	public State getMenuState() {
		return menuState;
	}
	public State getSettingsState() {
		return settingsState;
	}
	public State getSinglePlayerState() {
		return singlePlayerState;
	}
	public State getMultiplayerState() {
		return multiplayerState;
	}
	public State getMultiplayerGameState() {
		return multiplayerGameState;
	}
	public State getAchievementState() {
		return achievementState;
	}
	
	//TIME
	public int getSeconds() {
		return seconds;
	}
	public int getMinutes() {
		return minutes;
	}
	public int getHours() {
		return hours;
	}
	public void resetClock() {
		seconds = 0;
		minutes = 0;
		hours = 0;
	}
	public int getFPS() {
		return FPS;
	}





	
	
	
	
}
