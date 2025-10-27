package org.toast.display;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;

import javax.swing.JFrame;

import org.toast.Handler;

public class Display {
	private Handler handler;
	
	private JFrame frame;
	private Canvas canvas;
	
	
	private String title;
	
	private int FullWidth, FullHeight;//Fullscreen Width and Height
	private int WinWidth,WinHeight;//Window Width and Height
	
	private Dimension Fullscreen,Windowed;
	private boolean fullscreen;
	
	public Display(Handler handler,String title,boolean fullscreen) {		//A display is made of a frame and a canvas,
		this.handler = handler;
		this.title = title;
		this.fullscreen = fullscreen;
		
		FullWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
		FullHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
		
		WinWidth = FullWidth * 3 / 4;
		WinHeight = FullHeight * 3 / 4;
		
		
		Fullscreen = new Dimension(FullWidth,FullHeight);
		Windowed = new Dimension(WinWidth,WinHeight);
		
		createDisplay();
		
	}
	
	
	private void createDisplay() {
		
		frame = new JFrame(title);
		canvas = new Canvas();

		
		if(fullscreen) {
			frame.setUndecorated(true);
			frame.setSize(Fullscreen);
			frame.setMinimumSize(Fullscreen); 
			frame.setMaximumSize(Fullscreen);
			frame.setPreferredSize(Fullscreen);
			
			canvas.setPreferredSize(Fullscreen);
			canvas.setMaximumSize(Fullscreen);
			canvas.setMinimumSize(Fullscreen);
			
			
			
			
		}else {
			frame.setUndecorated(false);
			frame.setSize(Windowed);
			frame.setMinimumSize(Windowed); 
			frame.setMaximumSize(Windowed);
			frame.setPreferredSize(Windowed);
			
			frame.setLocationRelativeTo(null); 
			
			
			canvas.setPreferredSize(Windowed);
			canvas.setMaximumSize(Windowed);
			canvas.setMinimumSize(Windowed); 
			
			
		}
		
		
		canvas.setFocusable(false);
		frame.add(canvas);
		
		frame.setFocusable(true); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null); //Window appears in center of screen
		frame.setVisible(true);
		
		
		
		frame.pack();
		frame.validate();
		
		
		
	}
	
	
	
	public void fixFullscreen() {
		
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		
		
		if(fullscreen) {
			device.setFullScreenWindow(frame);
			
		}else {
			device.setFullScreenWindow(null);
		}
		
		handler.getGame().refreshStates();
		frame.setVisible(true); 
		
		
	}
	


	
	
	
	public Canvas getCanvas() {
		return canvas;
	}
	public JFrame getFrame() {
		return frame;
	}
	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
		fixFullscreen();
	}
	public boolean getFullscreen() {
		return fullscreen;
	}
	
	
	
	
	
}
