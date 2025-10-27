package org.toast.net;

public class Packet {
	
	public static final int INVALID = 99;
	public static final int CONNECT = 00;
	public static final int DISCONNECT = 01;
	public static final int MESSAGE = 02;
	public static final int WORLDDATA = 03;
	public static final int ENTITYDATA = 04;
	public static final int PLAYERDATA = 05;
	
	private int ID;
	private String data;
	
	public Packet(int ID,String data) {
		this.ID = ID;
		this.data = data;
	}

	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getID() {
		return ID;
	}
	
	
	
	
	
	
	
	
	
	
}
