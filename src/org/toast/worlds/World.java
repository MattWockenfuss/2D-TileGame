package org.toast.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.entities.Entity;
import org.toast.entities.EntityManager;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.entities.itemEntities.ItemEntity;
import org.toast.entities.mobs.Player;
import org.toast.entities.statics.CraftingStation;
import org.toast.entities.statics.Crate;
import org.toast.entities.statics.Furnace;
import org.toast.gfx.Assets;
import org.toast.item.Item;
import org.toast.utils.Utils;

public class World {
	
	private Handler handler;
	private String path;
	
	
	private String name;
	private int seed;
	private int time = 0;
	public static final int TIME_IN_A_DAY = 36000;
	private int spawnX,spawnY;
	private String dateCreated,dateLastPlayed;
	
	public ArrayList<Chunk> chunks;
	private EntityManager entityManager;

	
	private int renderDistance = 4;
	private int generateDistance = 5;
	
	
	
	
	
	
	public World(Handler handler,String path) {
		this.handler = handler;
		this.path = path; 
	}
	
	
	

	
	
	
	public void tick() {
		//time+=1;
		if(time >= TIME_IN_A_DAY)
			time = 0;
		//only tick in a 5x5
		for(Chunk c : chunks) {
			
			int px = (int) handler.getGameState().player.getX();
			int py = (int) handler.getGameState().player.getY();
			
			int ChunkX = (px / Chunk.PIXEL_CHUNK_WIDTH); 
			int ChunkY = (py / Chunk.PIXEL_CHUNK_HEIGHT); 
			
			
			int startX = ChunkX - renderDistance; 
			int endX = ChunkX + renderDistance;
			int startY = ChunkY - renderDistance;
			int endY = ChunkY + renderDistance;
			
			if(c.getX() > startX && c.getX() < endX) {
				if(c.getY() > startY && c.getY() < endY) {
					c.tick();
				}
			}
			
		}
		
		generateChunk();
		
		
		
		entityManager.tick();
		Block.waterBlock.tick();
	}
	public void render(Graphics g) {
		
		renderBackground(g); 
		
		//only render a 5x5 around the player
		
		for(Chunk c : chunks) {
			
			int px = (int) handler.getGameState().player.getX();
			int py = (int) handler.getGameState().player.getY();
			
			int ChunkX = (px / Chunk.PIXEL_CHUNK_WIDTH); 
			int ChunkY = (py / Chunk.PIXEL_CHUNK_HEIGHT); 
			
			int startX = ChunkX - renderDistance; 
			int endX = ChunkX + renderDistance;
			int startY = ChunkY - renderDistance;
			int endY = ChunkY + renderDistance;
			
			if(c.getX() > startX && c.getX() < endX) {
				if(c.getY() > startY && c.getY() < endY) {
					c.render(g);
				}
			}
			
		}
		
		entityManager.render(g);
		

		
		
	}
	public void renderBackground(Graphics g) {
		int transTime = 1000;
		int biomeID = 0;
		for(Entity e : entityManager.getEntities()) {
			if(e instanceof Player) {
				if(getChunk(e.getX(), e.getY()) != null) {
					biomeID = getChunk(e.getX(), e.getY()).getBiomeID();
				}	
					if(biomeID == 300) {//underground
						g.setColor(Color.BLACK);
					}else {				//draw the time
						
						//time 9000 is noon
						//time 27000 is midnight
						
						
						
						if(time > 0 && time < transTime) {
							g.setColor(Utils.InterpolateColors(time, TIME_IN_A_DAY, Assets.black, Assets.biome_sky)); 
						}else if(time > (18000 - transTime) && time < (18000 + transTime)) {
							//then its turning night
							g.setColor(Utils.InterpolateColors(time, TIME_IN_A_DAY / 2, Assets.biome_sky, Assets.black)); 
						}else if(time > transTime && time < (18000 - transTime)) {
							//then its day time	
							g.setColor(Assets.biome_sky); 
						}else if(time > (18000 + transTime) && time < (36000 - transTime)) {
							//then its night time
							g.setColor(Assets.black); 
						}
						
						
						
					}
					g.fillRect(0, 0, handler.getWidth(), handler.getHeight()); 
				

				
				return;
			}
			
			
			
		}
	}
		


	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

	
	
	public void loadWorld(String path) {
		chunks = new ArrayList<Chunk>();
		entityManager = new EntityManager(handler);
		

		Crate c = new Crate(handler,400,-1200);
		CraftingStation cs = new CraftingStation(handler,400,-1300);
		Furnace cf = new Furnace(handler,400,-1400);
		TEMP$(c); 
		
		
		entityManager.addEntity(c);
		entityManager.addEntity(cs);
		entityManager.addEntity(cf);
		handler.setWorld(this);
		
		//because its single player
		handler.getGameState().setPlayer(new Player(handler, spawnX, spawnY, 100));
		
		
		
		
		
	}//END OF LOADING WORLD
	public void menuScreenLoad() {
		//read the properties file set the variables that will be shown on menu screen
		
		//spawnpoint and seed, dates
		
		//System.out.println(path); 
		String[] pathPart = path.split("/");
		name = pathPart[2];
		
		
		String propertiesFile = Utils.loadFileAsString(path + "/properties.txt");
	
		
		String[] lines = propertiesFile.split("\\r?\\n");;
		
		for(String line : lines) {
			String[] tokens = line.split(":");
			
			if(tokens[0].trim().equalsIgnoreCase("spawnpoint")) {
				String[] parts = tokens[1].substring(1, tokens[1].length()-1).split(",");
				spawnX = Utils.parseInt(parts[0]);
				spawnY = Utils.parseInt(parts[1]);
			}else if(tokens[0].trim().equalsIgnoreCase("seed")) {
				seed = Utils.parseInt(tokens[1]);
			}else if(tokens[0].trim().equalsIgnoreCase("dateCreated")) {
				dateCreated = tokens[1];
			}else if(tokens[0].trim().equalsIgnoreCase("dateLastPlayed")) {
				dateLastPlayed = tokens[1];
			}else if(tokens[0].trim().equalsIgnoreCase("time")) {
				time = Utils.parseInt(tokens[1]);  
			}
			
			
			
			
			
			
		}
		
		
//		System.out.println("Loaded " + name);
//		System.out.println("\t" + spawnX + ", " + spawnY); 
//		System.out.println("\t" + seed); 
//		System.out.println("\t" + dateCreated); 
//		System.out.println("\t" + dateLastPlayed); 
		
		
		
		
		
	}
	
	
	
	
	
	//Chunk Methods
	public void generateChunk() {
		
		int px = (int) handler.getGameState().player.getX();
		int py = (int) handler.getGameState().player.getY();
		
		int ChunkX = (px / Chunk.PIXEL_CHUNK_WIDTH); 
		int ChunkY = (py / Chunk.PIXEL_CHUNK_HEIGHT); 
		
		for(int y = ChunkY - generateDistance;y <= ChunkY + generateDistance;y++) {
			for(int x = ChunkX - generateDistance;x <= ChunkX + generateDistance;x++) {
				
				
				if(chunks.isEmpty()) {
					chunks.add(new Chunk(handler,x,y));
				}else {//Check to see if the chunk already exists first
					boolean newChunkFlag = true;
					for(Chunk c : chunks) {
						if(c.getX() == x && c.getY() == y) {
							//System.out.println("Chunk (" + x + " , " + y + ") Already Exists!");
							newChunkFlag = false;
							
						}
					}
					if(newChunkFlag) {
						Chunk newChunk = new Chunk(handler,x,y);
						chunks.add(newChunk);
					}
						
					
				}
			}
		}
	}
	public Block getBlock(float x, float y) {// x and y are coords
		
		Chunk c = getChunk(x, y);
		Point rel = getRelPos(x, y);
		if(c != null)
			return c.getBlocks()[rel.x][rel.y];
		return Block.airBlock;
		
		
	}
	public void setBlock(Block b, float x, float y) {
		Chunk c = getChunk(x, y);
		Point rel = getRelPos(x, y);
		c.getBlocks()[rel.x][rel.y] = b;
	}
	public void breakBlock(float x, float y) {
		Chunk c = getChunk(x, y);
		Point rel = getRelPos(x, y);
		
		Block toBeBroken = c.getBlocks()[rel.x][rel.y];
		
		if(toBeBroken.getItemDropped() != null) {
			handler.getWorld().entityManager.addEntity(new ItemEntity(handler, x, y, toBeBroken.getItemDropped().getItem().getID(), toBeBroken.getItemDropped().getCount())); 
		}
		
		c.getBlocks()[rel.x][rel.y] = Block.airBlock;
	}
	public Point getAbsPos(float x,float y) {// in coords by pixel
		//so to input, take screen coords and add offset
		
		float newX = ((x) / Block.BLOCKWIDTH);
		float newY = ((y) / Block.BLOCKHEIGHT);
		
		if(newX < 0)
			newX -= 1;
		if(newY < 0)
			newY -= 1;
		
		
		return new Point((int)(newX),(int)(newY)); 
	}
	public Point getRelPos(float x,float y) {// in coords by pixel
		Point abs = getAbsPos(x, y);
		Point rel = new Point();
		
		if(abs.x > 0) {
			rel.x = abs.x % Chunk.CHUNK_WIDTH;
		}else {
			rel.x = abs.x % 10;
			if(rel.x != 0)
				rel.x += 10;
		}
		
		if(abs.y > 0) {
			rel.y = abs.y % Chunk.CHUNK_HEIGHT;
		}else {
			rel.y = abs.y % 10;
			if(rel.y != 0)
				rel.y += 10;
		}
		

		
		
		
		
		
		return rel;
	}
	public Chunk getChunk(float x,float y) {//in coords by pixel
		
		
		
		float cx = x / Chunk.PIXEL_CHUNK_WIDTH;
		float cy = y / Chunk.PIXEL_CHUNK_HEIGHT;
		
		if(cx < 0)
			cx-=1;
		if(cy < 0)
			cy-=1;
		
		for(Chunk c : chunks) {
			if((int)cx == c.getX() && (int)cy == c.getY()) {
				return c;
			}
		}
		
		
		return null;
	}
	public Chunk getChunk(int x,int y) {//in coords by Chunk used for Chunk Generation
		for(Chunk c : chunks) {
			if((int)x == c.getX() && (int)y == c.getY()) {
				return c;
			}
		}
		
		
		return null;
	}

	

	
	public void TEMP$(Crate c) {
		c.getContents()[0][0].setCurrentItemStack(new ItemStack(Item.RedConcrete,64));
		c.getContents()[1][0].setCurrentItemStack(new ItemStack(Item.OrangeConcrete,64)); 
		c.getContents()[2][0].setCurrentItemStack(new ItemStack(Item.YellowConcrete,64)); 
		c.getContents()[3][0].setCurrentItemStack(new ItemStack(Item.GreenConcrete,64)); 
		c.getContents()[4][0].setCurrentItemStack(new ItemStack(Item.BlueConcrete,64)); 
		c.getContents()[5][0].setCurrentItemStack(new ItemStack(Item.AquaConcrete,64)); 
		c.getContents()[6][0].setCurrentItemStack(new ItemStack(Item.MagentaConcrete,64)); 
		c.getContents()[7][0].setCurrentItemStack(new ItemStack(Item.PurpleConcrete,64)); 
		c.getContents()[8][0].setCurrentItemStack(new ItemStack(Item.PinkConcrete,64)); 
		
		c.getContents()[0][1].setCurrentItemStack(new ItemStack(Item.RedWool,64));
		c.getContents()[1][1].setCurrentItemStack(new ItemStack(Item.OrangeWool,64)); 
		c.getContents()[2][1].setCurrentItemStack(new ItemStack(Item.YellowWool,64)); 
		c.getContents()[3][1].setCurrentItemStack(new ItemStack(Item.GreenWool,64)); 
		c.getContents()[4][1].setCurrentItemStack(new ItemStack(Item.BlueWool,64)); 
		c.getContents()[5][1].setCurrentItemStack(new ItemStack(Item.AquaWool,64)); 
		c.getContents()[6][1].setCurrentItemStack(new ItemStack(Item.MagentaWool,64)); 
		c.getContents()[7][1].setCurrentItemStack(new ItemStack(Item.PurpleWool,64)); 
		c.getContents()[8][1].setCurrentItemStack(new ItemStack(Item.PinkWool,64)); 
		
		c.getContents()[0][2].setCurrentItemStack(new ItemStack(Item.fir_log,64));
		c.getContents()[1][2].setCurrentItemStack(new ItemStack(Item.pine_log,64)); 
		c.getContents()[2][2].setCurrentItemStack(new ItemStack(Item.savanna_log,64)); 
		c.getContents()[3][2].setCurrentItemStack(new ItemStack(Item.jungle_log,64)); 
		
		c.getContents()[5][2].setCurrentItemStack(new ItemStack(Item.fir_planks,64)); 
		c.getContents()[6][2].setCurrentItemStack(new ItemStack(Item.pine_planks,64)); 
		c.getContents()[7][2].setCurrentItemStack(new ItemStack(Item.savanna_planks,64)); 
		c.getContents()[8][2].setCurrentItemStack(new ItemStack(Item.jungle_planks,64)); 
		
		c.getContents()[0][3].setCurrentItemStack(new ItemStack(Item.stone1,64));
		c.getContents()[1][3].setCurrentItemStack(new ItemStack(Item.stone2,64)); 
		c.getContents()[2][3].setCurrentItemStack(new ItemStack(Item.stone3,64)); 
		c.getContents()[3][3].setCurrentItemStack(new ItemStack(Item.stone4,64)); 
		c.getContents()[4][3].setCurrentItemStack(new ItemStack(Item.stone5,64)); 
		
		c.getContents()[6][3].setCurrentItemStack(new ItemStack(Item.snow_block,64)); 
		c.getContents()[7][3].setCurrentItemStack(new ItemStack(Item.ice_block,64)); 
		
		c.getContents()[0][4].setCurrentItemStack(new ItemStack(Item.sand_block,64)); 
		c.getContents()[1][4].setCurrentItemStack(new ItemStack(Item.sandstone_block,64));
		
		c.getContents()[3][4].setCurrentItemStack(new ItemStack(Item.grass_block1,64));
		c.getContents()[4][4].setCurrentItemStack(new ItemStack(Item.grass_block2,64));
		c.getContents()[5][4].setCurrentItemStack(new ItemStack(Item.grass_block3,64));
		c.getContents()[6][4].setCurrentItemStack(new ItemStack(Item.grass_block4,64));
		
		c.getContents()[7][4].setCurrentItemStack(new ItemStack(Item.WhiteConcrete,64));
		c.getContents()[8][4].setCurrentItemStack(new ItemStack(Item.WhiteWool,64));
		
		c.getContents()[0][5].setCurrentItemStack(new ItemStack(Item.maple_log,64));
		c.getContents()[1][5].setCurrentItemStack(new ItemStack(Item.maple_planks,64));
		c.getContents()[2][5].setCurrentItemStack(new ItemStack(Item.Stone_Pickaxe,1));
		c.getContents()[3][5].setCurrentItemStack(new ItemStack(Item.paladin_axe,1));
	}
	
	
	
	
	
	
	//////////////////////////////////////////GETTERS AND SETTERS.///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Preloaded Stuff
	public String getName() {
		return name;
	}
	public int getSpawnX() {
		return spawnX;
	}
	public int getSpawnY() {
		return spawnY;
	}
	public int getSeed() {
		return seed;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getDateCreated() {
		return dateCreated;
	}
	public String getDateLastPlayed() {
		return dateLastPlayed;
	}
	public String getPath() {
		return path;
	}
	
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	

	
	
	
	
	
}
