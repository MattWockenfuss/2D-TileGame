package org.toast.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.toast.Handler;
import org.toast.entities.Entity;
import org.toast.entities.statics.trees.MapleTree;
import org.toast.states.GameStateManager;
import org.toast.states.MultiPlayer.MultiPlayerGameState;
import org.toast.utils.Utils;
import org.toast.worlds.World;

public class GameClient extends Thread{
	
	//LoopStuff
	private boolean running = true;
	
	
	private String myAddress;
	private InetAddress serverAddress;
	private DatagramSocket socket;
	private Handler handler;
	private String username;
	private int port;
	
	
	//Loading World Stuff
	private World world;
	String name = "";
	int width = 0,height = 0;
	int[][] tiles = null;
	String worldBuilder = "";
	
	//LOADING ENTITIES STUFF
	ArrayList<Entity> entities;
	
	
	
	public GameClient(Handler handler,String serverAddress,String username) {
		this.handler = handler;
		this.username = username;
		try {
			this.socket = new DatagramSocket();
			this.myAddress = InetAddress.getLocalHost().getHostAddress();
			this.serverAddress = InetAddress.getByName(serverAddress);
			port = socket.getPort();
		} catch (UnknownHostException | SocketException e) {
			GameStateManager.setState(handler.getMenuState()); 
			JOptionPane.showMessageDialog(handler.getDisplay().getFrame(), "An Error Occurred whilst Connecting to the Server!","Connection Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		
		
	}
	
	
	public void run() {
		while(running) {
//			System.out.println(myAddress);  
			Packet p = recieveData();
			//System.out.println(p.getID() + p.getData()); 				//Prints out Packet
			
			handlePackets(p); 
			
			
			
			   
		}//Run with the game
	}
	
	
	public void handlePackets(Packet p) {
		if(p.getID() == Packet.CONNECT) {
			System.out.println(p.getID() + p.getData()); 
		}else if(p.getID() == Packet.DISCONNECT) {
			GameStateManager.setState(handler.getMenuState());
			if(p.getData().equalsIgnoreCase("You have been kicked!")) {
				JOptionPane.showMessageDialog(handler.getDisplay().getFrame(), p.getData(),"You have been Kicked!",JOptionPane.WARNING_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(handler.getDisplay().getFrame(), p.getData(),"You have Disconnected!",JOptionPane.ERROR_MESSAGE);
			}
			
			myStop();
		}else if(p.getID() == Packet.MESSAGE) {
			System.out.println(p.getID() + p.getData()); 
		}else if(p.getID() == Packet.WORLDDATA) {
//			System.out.println("---- RECIEVING WORLD DATA ----");
//			System.out.println(p.getData());
			

			
			
			if(p.getData().startsWith("World")) {	//Then its the first packet
				String[] tokens = p.getData().split(">");
				name = tokens[0];
				width = Integer.parseInt(tokens[1]);
				height = Integer.parseInt(tokens[2]);
				tiles = new int[width][height];
				
			}else if(p.getData().startsWith("EndOf")) {
				if(width == 0 || height == 0) {
					System.out.println("Problem Loading the World"); 
					System.exit(0); 
				}
				worldBuilder = worldBuilder.substring(0, worldBuilder.length() - 1); 
				String[] tokens = worldBuilder.split(":");
				
				
				int i = 0;
				for(int y = 0;y < height;y++) {
					for(int x = 0;x < width;x++) {
						tiles[x][y] = Integer.parseInt(tokens[i]);
						i++;
					}
				}
				
				//BUILD WORLD
				//world = new World(handler,name,width,height,tiles);
				((MultiPlayerGameState) handler.getMultiplayerGameState()).setWorld(world); 
				GameStateManager.setState(handler.getMultiplayerGameState());
				
			}else {
				worldBuilder += p.getData();
				
			}
			
		}else if(p.getID() == Packet.ENTITYDATA) {							//LOADENTITIES AT BEGINNING
			
			if(p.getData().equalsIgnoreCase("LoadingEntities")) {						//FIRST ENTITIES PACKET
				//System.out.println("WE ARE BUILDING THE ENTITIES");
				entities = new ArrayList<Entity>();
			}else if(p.getData().equalsIgnoreCase("EntitiesLoaded")) {				//LAST ENTITIES PACKET
				world.getEntityManager().getEntities().addAll(entities);
			}else {																	//EVERYTHING INBETWEEN
				String[] tokens = p.getData().split(":");
				if((Utils.parseInt(tokens[0]) == 1)) {		//THEN ITS TREE
					entities.add(new MapleTree(handler,Utils.parseInt(tokens[1]),Utils.parseInt(tokens[2])));
				}
			}
			
			
			
			
			
			
			
		}else if(p.getID() == Packet.INVALID) { 
			GameStateManager.setState(handler.getMenuState()); 
			JOptionPane.showMessageDialog(handler.getDisplay().getFrame(), p.getData(),"Connection Error",JOptionPane.ERROR_MESSAGE);
			myStop();
		}else {

		}
		
	}
	
	//SENDS DATA TO THE SERVER IT IS CURRENTLY CONNECTED TO
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, 1331);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	public Packet recieveData() {
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String message = new String(packet.getData()).trim();
		int id = Utils.parseInt(message.substring(0, 2));
		
		
		return new Packet(id,message.substring(2));
	}
	
	public synchronized void myStart() {
		if(running)
			return;
		running = true;
			
	}
	
	@SuppressWarnings("deprecation")
	public synchronized void myStop() {
		running = false;
		sendData(("01" + username + "::" + myAddress).getBytes());
		socket.close();
		this.stop();
	}
	
	
	
	
	
	
	
	
	
	//////////////////////////////GETTERS AND SETTERS////////////////////////////////////////////////
	public String getUserName() {
		return username;
	}
	public String getIP() {
		return myAddress;
	}
	public int getPort() {
		return port;
	}
	public DatagramSocket getSocket() {
		return socket;
	}
	public World getWorld() {
		return world;
	}

	
	
	
}
