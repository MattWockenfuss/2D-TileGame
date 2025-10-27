package org.toast.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import org.toast.ui.UIManager;

public class MouseManager implements MouseListener,MouseMotionListener,MouseWheelListener{
	
	private boolean leftPressed,rightPressed;
	private int mouseWheelScrolled = 0; //Negative of down, positive if up, 0 otherwise
	private int mouseX,mouseY;
	private UIManager uiManager;
	
	public MouseManager() {
		
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	public boolean isRightPressed() {
		return rightPressed;
	}
	public int getMouseX() {
		return mouseX;
	}
	public int getMouseY() {
		return mouseY;
	}
	
	
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = true;
		if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = true;
		
	}
	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == MouseEvent.BUTTON1)
			leftPressed = false;
		if(e.getButton() == MouseEvent.BUTTON3)
			rightPressed = false;
		if(uiManager != null) {
			uiManager.onMouseRelease(e);
		}
		
	}
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		if(uiManager != null) 
			uiManager.onMouseMove(e);
		
	}
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		int notches = e.getWheelRotation();
		
		mouseWheelScrolled = notches;
		//System.out.println(notches); 
	}
	
	
	
	
	
	

	
	
	
	
	//////////////////////////////////////GETTERS AND SETTERS////////////////////////////////////
	
	
	public void setUIManager(UIManager uiManager) {
		this.uiManager = uiManager;
	}
	public UIManager getUIManager() {
		return uiManager;
	}
	public void resetMouseWheelScroll() {
		mouseWheelScrolled = 0;
	}
	public int getMouseWheelScroll() {
		return mouseWheelScrolled;
	}


	
	
	
	
	
}
