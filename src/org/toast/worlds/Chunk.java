package org.toast.worlds;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import org.toast.Handler;
import org.toast.blocks.Block;
import org.toast.entities.Entity;
import org.toast.entities.statics.Cactus;
import org.toast.entities.statics.Crate;
import org.toast.entities.statics.trees.FirTree;
import org.toast.entities.statics.trees.JungleTree;
import org.toast.entities.statics.trees.MapleTree;
import org.toast.entities.statics.trees.PineTree;
import org.toast.entities.statics.trees.SavannaTree;
import org.toast.utils.Utils;

public class Chunk {
	
	private Handler handler;
	
	public static final int CHUNK_WIDTH = 10, CHUNK_HEIGHT = 10;
	public static final int PIXEL_CHUNK_WIDTH = CHUNK_WIDTH * Block.BLOCKWIDTH, PIXEL_CHUNK_HEIGHT = CHUNK_HEIGHT * Block.BLOCKHEIGHT;
	
	private int x,y;
	private int biomeID;
	
	
	private Block[][] blocks;
	
	public Chunk(Handler handler, int x,int y) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		blocks = new Block[CHUNK_WIDTH][CHUNK_HEIGHT];
		
		generateChunk();
		
	}
	public Chunk(Handler handler, int x,int y,int biomeID, Block[][] blocks) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.biomeID = biomeID;
		this.blocks = blocks;
	}
	
	public void tick() {
		
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				if(blocks[x][y] != null)
					blocks[x][y].tick();
			}
		}
		
	}
	public void render(Graphics g) {
		
		//realworld values of chunk numbers
		int startX = (int) ((x * PIXEL_CHUNK_WIDTH) - handler.getGameCamera().getxOffset());
		int startY = (int) ((y * PIXEL_CHUNK_HEIGHT) - handler.getGameCamera().getyOffset());
		
		for(int y = 0;y < CHUNK_HEIGHT;y++) {
			for(int x = 0;x < CHUNK_WIDTH;x++) {
				
				if(blocks[x][y] != null) {
					blocks[x][y].render(g, startX + (x * Block.BLOCKWIDTH), startY + (y * Block.BLOCKHEIGHT));
					
				}
					
					
				
				
			}
		}
		
		
		if(handler.getGameState().debug) {
			
//			g.setColor(Color.PINK);
//			for(int y = 0;y < CHUNK_HEIGHT;y++) {
//				for(int x = 0;x < CHUNK_WIDTH;x++) {
//					g.drawRect((int) ((this.x * PIXEL_CHUNK_WIDTH - handler.getGameCamera().getxOffset()) + x * Block.BLOCKWIDTH),  (int)((this.y * PIXEL_CHUNK_HEIGHT - handler.getGameCamera().getyOffset()) + y * Block.BLOCKHEIGHT), Block.BLOCKWIDTH, Block.BLOCKHEIGHT); 
//				}
//			}

			
			g.setColor(Color.blue);
			g.drawRect((int) (x * PIXEL_CHUNK_WIDTH - handler.getGameCamera().getxOffset()), (int)(y * PIXEL_CHUNK_HEIGHT - handler.getGameCamera().getyOffset()), PIXEL_CHUNK_WIDTH, PIXEL_CHUNK_HEIGHT);
			//double thick
			//g.drawRect((int) (x * PIXEL_CHUNK_WIDTH - handler.getGameCamera().getxOffset() + 1), (int)(y * PIXEL_CHUNK_HEIGHT - handler.getGameCamera().getyOffset() + 1), PIXEL_CHUNK_WIDTH - 2, PIXEL_CHUNK_HEIGHT - 2);
			
			
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public void generateChunk() {//oh boy
		//System.out.println("--------(" + x + "," + y + ")--------");

		determineBiomeID();
		
		switch(biomeID) {
		case 100:
			TundraBiome();
			break;
		case 101:
			AppalacianBiome();
			break;
		case 102:
			ForestBiome();
			break;
		case 103:
			PlainsBiome();
			break;
		case 104:
			SavannaBiome();
			break;
		case 105:
			JungleBiome();
			break;
		case 106:
			DesertBiome();
			break;
		case 107:
			OceanBiome();
			break;
		case 999:
			SkyBiome();
			break;
		case 300:
			UndergroundBiome();
			break;
		default:
			SkyBiome();
			break;
		}
		
		
		
		
		/*
		 * RANK FROM HOTTEST TO COLDEST
		 * 
		 * Tundra			0
		 * Dark Forest		1
		 * Forest			2
		 * Plains			3
		 * Savanna			4
		 * Jungle			5
		 * Desert			6
		 * 
		 * 
		 * 
		 * Beach next to Oceans
		 * Oceans			7
		 * 
		 * Sky				0
		 * 
		 */
		
		
		
	}
	
	
	
	public void determineBiomeID() {
	
						
		
		//Then we can generate Surface Biomes

		int AdjacentBiomeChance = 9;
		Random r;
		if(x > 0)
			r = new Random(handler.getWorld().getSeed() + x^2);
		else
			r = new Random(handler.getWorld().getSeed() + x^3);
				
		
		biomeID = 999;
		
		if(y == 0) {
				//the surface biome method
				Chunk leftChunk = handler.getWorld().getChunk(x-1, y);
				Chunk rightChunk = handler.getWorld().getChunk(x+1, y);
				
				
				
				
				
				if(leftChunk != null && rightChunk != null) {
					int left = leftChunk.getBiomeID();
					int right = rightChunk.getBiomeID();
					biomeID = (int)(left + right) / 2;
					
				}else if(leftChunk != null) { 
					 if(r.nextInt((10-1) + 1) + 1 <= AdjacentBiomeChance) {//60% chance
						 biomeID = leftChunk.getBiomeID();
					 }else {
						 if(r.nextInt((2-1) + 1) + 1 == 1) {//50% chance
							 biomeID = leftChunk.getBiomeID() - 1;
						 }else {
							 biomeID = leftChunk.getBiomeID() + 1;
						 }
					 }
				}else if(rightChunk != null) {
					 if(r.nextInt((10-1) + 1) + 1 <= AdjacentBiomeChance) {//60% chance
						 biomeID = rightChunk.getBiomeID();
					 }else {
						 if(r.nextInt((2-1) + 1) + 1 == 1) {//50% chance
							 biomeID = rightChunk.getBiomeID() - 1;
						 }else {
							 biomeID = rightChunk.getBiomeID() + 1;
						 }
					 }
				}else {
					int min = 100;
					int max = 106;
					biomeID = r.nextInt((max-min) + 1) + min;
				}
				
				
				if(biomeID <= 100)
					biomeID = 100;
				if(biomeID > 106)
					biomeID = 106;
				
				
				

				
				
				
			
		
					
		}else if(y < 0) {
			//then its a sky biome
			biomeID = 999;
		}else if(y > 0) {
			//then its a underground biome, a plain stone biome, or a dungeon biome
			biomeID = 300;
		}
	}
	
	
	
	
	
	public void SkyBiome() {
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
			}
		}
	}
	public void OceanBiome() {
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
				if(y == 9)
					blocks[x][y] = Block.sandBlock;
			}
		}
	}
	
	public void TundraBiome() {
		//System.out.println(handler.getWorld().getSeed() + x * SEED_MULTIPLIER); 
		Random r = new Random(handler.getWorld().getSeed() + x^2);
		int min = 1;
		int max = 8;
		int switchPoint = r.nextInt((max-min) + 1) + min;
		min = 0;
		max = 1;
		int direction = r.nextInt((max-min) + 1) + min;
		if(direction == 0) {
			//reroll once
			min = 0;
			max = 1;
			direction = r.nextInt((max-min) + 1) + min;
		}
			
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
				if(y == 3) {
					blocks[x][y] = Block.dirtBlock;
					if(x < switchPoint) {
						blocks[x][y] = Block.snowBlock;
					}else {
						blocks[x][y - direction] = Block.snowBlock;
					}
				}else if(y == 9) {
					min = 1;
					max = 10;
					if(r.nextInt((max-min) + 1) + min <= 5) {//40% chance of a stone
						blocks[x][y] = Block.stoneBlock3;
					}else {
						blocks[x][y] = Block.dirtBlock;
					}
				}else if(y > 3) {
					blocks[x][y] = Block.dirtBlock;
				}
				

					
			}
		}
		
		
		//Same as a plains biome but with trees
		
		int minTrees = 4;
		int maxTrees = 8;
		for(int i = 0;i < r.nextInt((maxTrees-minTrees) + 1) + minTrees;i++) {												//spawns a tree between 0 and 440 relative in chunk
			handler.getWorld().getEntityManager().getEntities().add(new PineTree(handler,x * PIXEL_CHUNK_WIDTH + (r.nextInt((440-0) + 1) + 0),-100));
		}
		
		//we generated all the trees, check to see if any collide
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof PineTree && e.checkEntityCollision(0f, 0f)) {
				e.DropsItemsOnDeath(false); 
				e.setActive(false); 
			}
		}
		
		
	}
	public void AppalacianBiome() {
		//System.out.println(handler.getWorld().getSeed() + x * SEED_MULTIPLIER); 
		Random r = new Random(handler.getWorld().getSeed() + x^2);
		int min = 1;
		int max = 8;
		int switchPoint = r.nextInt((max-min) + 1) + min;
		min = 0;
		max = 1;
		int direction = r.nextInt((max-min) + 1) + min;
		if(direction == 0) {
			//reroll once
			min = 0;
			max = 1;
			direction = r.nextInt((max-min) + 1) + min;
		}
			
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
				if(y == 3) {
					blocks[x][y] = Block.dirtBlock;
					if(x < switchPoint) {
						blocks[x][y] = Block.tundraGrassBlock;
					}else {
						blocks[x][y - direction] = Block.tundraGrassBlock;
					}
				}else if(y == 9) {
					min = 1;
					max = 10;
					if(r.nextInt((max-min) + 1) + min <= 5) {//40% chance of a stone
						blocks[x][y] = Block.stoneBlock3;
					}else {
						blocks[x][y] = Block.dirtBlock;
					}
				}else if(y > 3) {
					blocks[x][y] = Block.dirtBlock;
				}
				

					
			}
		}
		
		
		//Same as a plains biome but with trees
		
		int minTrees = 4;
		int maxTrees = 8;
		for(int i = 0;i < r.nextInt((maxTrees-minTrees) + 1) + minTrees;i++) {												//spawns a tree between 0 and 440 relative in chunk
			handler.getWorld().getEntityManager().getEntities().add(new FirTree(handler,x * PIXEL_CHUNK_WIDTH + (r.nextInt((440-0) + 1) + 0),-100));
		}
		
		//we generated all the trees, check to see if any collide
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof FirTree && e.checkEntityCollision(0f, 0f)) {
				e.DropsItemsOnDeath(false); 
				e.setActive(false); 
			}
		}
		
		
	}
	public void ForestBiome() {
		//System.out.println(handler.getWorld().getSeed() + x * SEED_MULTIPLIER); 
		Random r = new Random(handler.getWorld().getSeed() + x^2);
		int min = 1;
		int max = 8;
		int switchPoint = r.nextInt((max-min) + 1) + min;
		min = 0;
		max = 1;
		int direction = r.nextInt((max-min) + 1) + min;
		if(direction == 0) {
			//reroll once
			min = 0;
			max = 1;
			direction = r.nextInt((max-min) + 1) + min;
		}
			
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
				if(y == 3) {
					blocks[x][y] = Block.dirtBlock;
					if(x < switchPoint) {
						blocks[x][y] = Block.grassBlock;
					}else {
						blocks[x][y - direction] = Block.grassBlock;
					}
				}else if(y == 9) {
					min = 1;
					max = 10;
					if(r.nextInt((max-min) + 1) + min <= 5) {//40% chance of a stone
						blocks[x][y] = Block.stoneBlock3;
					}else {
						blocks[x][y] = Block.dirtBlock;
					}
				}else if(y > 3) {
					blocks[x][y] = Block.dirtBlock;
				}
				

					
			}
		}
		
		
		//Same as a plains biome but with trees
		
		int minTrees = 4;
		int maxTrees = 8;
		for(int i = 0;i < r.nextInt((maxTrees-minTrees) + 1) + minTrees;i++) {												//spawns a tree between 0 and 440 relative in chunk
			handler.getWorld().getEntityManager().getEntities().add(new MapleTree(handler,x * PIXEL_CHUNK_WIDTH + (r.nextInt((440-0) + 1) + 0),-100));
		}
		
		//we generated all the trees, check to see if any collide
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof MapleTree && e.checkEntityCollision(0f, 0f)) {
				e.DropsItemsOnDeath(false); 
				e.setActive(false); 
			}
		}
		
		
	}
	public void PlainsBiome() {
		//System.out.println(handler.getWorld().getSeed() + x * SEED_MULTIPLIER); 
		Random r = new Random(handler.getWorld().getSeed() * x^2);
		int min = 1;
		int max = 8;
		int switchPoint = r.nextInt((max-min) + 1) + min;
		min = 0;
		max = 1;
		int direction = r.nextInt((max-min) + 1) + min;
		if(direction == 0) {
			//reroll once
			min = 0;
			max = 1;
			direction = r.nextInt((max-min) + 1) + min;
		}
			
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
				if(y == 3) {
					blocks[x][y] = Block.dirtBlock;
					if(x < switchPoint) {
						blocks[x][y] = Block.savannaGrassBlock;
					}else {
						blocks[x][y - direction] = Block.savannaGrassBlock;
					}
				}else if(y == 9) {
					min = 1;
					max = 10;
					if(r.nextInt((max-min) + 1) + min <= 5) {//40% chance of a stone
						blocks[x][y] = Block.stoneBlock3;
					}else {
						blocks[x][y] = Block.dirtBlock;
					}
				}else if(y > 3) {
					blocks[x][y] = Block.dirtBlock;
				}
				

					
			}
		}
		
		
		
		
	}
	public void SavannaBiome() {
		//System.out.println(handler.getWorld().getSeed() + x * SEED_MULTIPLIER); 
		Random r = new Random(handler.getWorld().getSeed() + x^2);
		int min = 1;
		int max = 8;
		int switchPoint = r.nextInt((max-min) + 1) + min;
		min = 0;
		max = 1;
		int direction = r.nextInt((max-min) + 1) + min;
		if(direction == 0) {
			//reroll once
			min = 0;
			max = 1;
			direction = r.nextInt((max-min) + 1) + min;
		}
			
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
				if(y == 3) {
					blocks[x][y] = Block.dirtBlock;
					if(x < switchPoint) {
						blocks[x][y] = Block.savannaGrassBlock;
					}else {
						blocks[x][y - direction] = Block.savannaGrassBlock;
					}
				}else if(y == 9) {
					min = 1;
					max = 10;
					if(r.nextInt((max-min) + 1) + min <= 5) {//40% chance of a stone
						blocks[x][y] = Block.stoneBlock3;
					}else {
						blocks[x][y] = Block.dirtBlock;
					}
				}else if(y > 3) {
					blocks[x][y] = Block.dirtBlock;
				}
				

					
			}
		}
		
		
		//Same as a plains biome but with trees
		
		int minTrees = 4;
		int maxTrees = 8;
		for(int i = 0;i < r.nextInt((maxTrees-minTrees) + 1) + minTrees;i++) {												//spawns a tree between 0 and 440 relative in chunk
			handler.getWorld().getEntityManager().getEntities().add(new SavannaTree(handler,x * PIXEL_CHUNK_WIDTH + (r.nextInt((440-0) + 1) + 0),-100));
		}
		
		//we generated all the trees, check to see if any collide
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof SavannaTree && e.checkEntityCollision(0f, 0f)) {
				e.DropsItemsOnDeath(false); 
				e.setActive(false); 
			}
		}
		
		
	}
	public void JungleBiome() {
		//System.out.println(handler.getWorld().getSeed() + x * SEED_MULTIPLIER); 
		Random r = new Random(handler.getWorld().getSeed() + x^2);
		int min = 1;
		int max = 8;
		int switchPoint = r.nextInt((max-min) + 1) + min;
		min = 0;
		max = 1;
		int direction = r.nextInt((max-min) + 1) + min;
		if(direction == 0) {
			//reroll once
			min = 0;
			max = 1;
			direction = r.nextInt((max-min) + 1) + min;
		}
			
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
				if(y == 3) {
					blocks[x][y] = Block.dirtBlock;
					if(x < switchPoint) {
						blocks[x][y] = Block.jungleGrassBlock;
					}else {
						blocks[x][y - direction] = Block.jungleGrassBlock;
					}
				}else if(y == 9) {
					min = 1;
					max = 10;
					if(r.nextInt((max-min) + 1) + min <= 5) {//40% chance of a stone
						blocks[x][y] = Block.stoneBlock3;
					}else {
						blocks[x][y] = Block.dirtBlock;
					}
				}else if(y > 3) {
					blocks[x][y] = Block.dirtBlock;
				}
				

					
			}
		}
		
		
		//Same as a plains biome but with trees
		
		int minTrees = 4;
		int maxTrees = 8;
		for(int i = 0;i < r.nextInt((maxTrees-minTrees) + 1) + minTrees;i++) {												//spawns a tree between 0 and 440 relative in chunk
			handler.getWorld().getEntityManager().getEntities().add(new JungleTree(handler,x * PIXEL_CHUNK_WIDTH + (r.nextInt((440-0) + 1) + 0),-100));
		}
		
		//we generated all the trees, check to see if any collide
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof JungleTree && e.checkEntityCollision(0f, 0f)) {
				e.DropsItemsOnDeath(false); 
				e.setActive(false); 
			}
		}
		
		
	}
	public void DesertBiome() {
		Random r = new Random(handler.getWorld().getSeed() + x^2);
		
		int min = 1;
		int max = 8;
		int switchPoint = r.nextInt((max-min) + 1) + min;
		min = 0;
		max = 1;
		int direction = r.nextInt((max-min) + 1) + min;
		if(direction == 0) {
			//reroll once
			min = 0;
			max = 1;
			direction = r.nextInt((max-min) + 1) + min;
		}
			
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.airBlock;
				if(y == 3) {
					blocks[x][y] = Block.sandBlock;
					if(x < switchPoint) {
						blocks[x][y] = Block.sandBlock;
					}else {
						blocks[x][y - direction] = Block.sandBlock;
					}
				}else if(y == 9) {
					min = 1;
					max = 10;
					if(r.nextInt((max-min) + 1) + min <= 5) {//40% chance of a stone
						blocks[x][y] = Block.stoneBlock3;
						blocks[x][y - 1] = Block.sandStoneBlock;
					}else {
						blocks[x][y] = Block.sandStoneBlock;
					}
				}else if(y > 3) {
					blocks[x][y] = Block.sandBlock;
				}
				

					
			}
		}
		
		
		//Same as a plains biome but with trees
		
		int minCactus = 3;
		int maxCactus = 5;
		for(int i = 0;i < r.nextInt((maxCactus-minCactus) + 1) + minCactus;i++) {												//spawns a tree between 0 and 440 relative in chunk
			handler.getWorld().getEntityManager().getEntities().add(new Cactus(handler,x * PIXEL_CHUNK_WIDTH + (r.nextInt((440-0) + 1) + 0),0));
		}
		
		//we generated all the cactuses, check to see if any collide
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e instanceof Cactus && e.checkEntityCollision(0f, 0f)) {
				e.DropsItemsOnDeath(false); 
				e.setActive(false); 
			}
		}
		
		
		
		
		}
	

	
	
	public void UndergroundBiome() {
		for(int y = 0; y < CHUNK_HEIGHT;y++) {
			for(int x = 0; x < CHUNK_WIDTH;x++) {
				blocks[x][y] = Block.stoneBlock3;
			}
		}
		
		generateOre(Block.dirtBlock, 9, 6, 16);
		generateOre(Block.stoneBlock5, 9, 12, 17);
		generateOre(Block.graphiteOreBlock, 20, 3, 8); 
		generateDungeon(150); 
		
		

		
		
		
		
	}
	
	//seperate Chunk generation method
	public void generateDungeon(int chance) {
		Random r = new Random(handler.getWorld().getSeed() + x^2 * y^2);
		
		if((r.nextInt((chance-1) + 1) + 1) == chance) {
			for(int y = 0; y < CHUNK_HEIGHT;y++) {
				for(int x = 0; x < CHUNK_WIDTH;x++) {
					blocks[x][y] = Block.airBlock;
					if(x == 0 || y == 0 || x == 9 || y == 9) {
						blocks[x][y] = Block.firPlanks;
					}
				}
			}
			//Eventually add a new Constructor for chest that takes in a random number as well as Chest type (Dungeon, Dungeon1, Rare Dungeon) and generates loot according	
			//So the chest doesnt spawn in the walls
			//chest spawns in first half, from x * pixelChunkWidth + (rand from 32 to 240 - 32)
			//chest spawns in first half, from x * pixelChunkWidth + (rand from 240 to 399) gravity doesnt apply if its in the 400 pixel

			// the 32 is the crate size, prevent crates in walls and each other
			handler.getWorld().getEntityManager().addEntity(new Crate(handler,x * PIXEL_CHUNK_WIDTH + (r.nextInt((PIXEL_CHUNK_WIDTH / 2 - 32 - Block.BLOCKWIDTH) + 1) + Block.BLOCKWIDTH), (y+1) * PIXEL_CHUNK_HEIGHT - (Block.BLOCKHEIGHT * 2) + 16));
			handler.getWorld().getEntityManager().addEntity(new Crate(handler,x * PIXEL_CHUNK_WIDTH + (r.nextInt(((PIXEL_CHUNK_WIDTH - Block.BLOCKWIDTH - 32) - PIXEL_CHUNK_WIDTH / 2) + 1) + (PIXEL_CHUNK_WIDTH / 2)),(y+1) * PIXEL_CHUNK_HEIGHT - (Block.BLOCKHEIGHT * 2) + 16));
			
			
		}
		
		

	}
	public void generateOre(Block b,int chance,int veinMin,int veinMax) {//chance a graphite vein between 2 and 4 ore blocks generate in that chunk
		Random r = new Random(handler.getWorld().getSeed()^3 + x * y);
		if((r.nextInt((chance-1) + 1) + 1) == chance) {
			
			int veinSize = Utils.randomNumber(veinMin, veinMax);
			//pick random block in chunk
			int min1 = 0;
			int max1 = Chunk.CHUNK_WIDTH - 1;
			int x = r.nextInt((max1-min1) + 1) + min1;
			int y = r.nextInt((max1-min1) + 1) + min1;
			blocks[x][y] = b;
			veinSize--;
			while(veinSize > 0) {
				
				
				
				//add a driftcap that increases when an ore has successfully been placed
				int driftxStart = -1;
				int driftxEnd = 1;
				int driftyStart = -1;
				int driftyEnd = 1;
				
				
				int directionX = r.nextInt((driftxEnd-driftxStart) + 1) + driftxStart;
				int directionY = r.nextInt((driftyEnd-driftyStart) + 1) + driftyStart;
				
				int newX,newY;
				
				//now we have a -1 or 1 in both x and y;    probably a much easier method
				if(x + directionX >= 0 && x + directionX <= 9) {
					newX = x + directionX;
					if(directionX < 0) {
						driftxStart--;
					}else {
						driftxEnd++;
					}
				}else {
					newX = x;
				}
				if(y + directionY >= 0 && y + directionY <= 9) {
					newY = y + directionY;
					if(directionY < 0) {
						driftyStart--;
					}else {
						driftyEnd++;
					}
				}else {
					newY = y;
				}
				
				blocks[newX][newY] = b;
				veinSize--;
				
			}
			//end of while loop
			
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////////////GETTERS AND SETTERS///////////////////////////////////
	
	public Block[][] getBlocks(){
		return blocks;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getBiomeID() {
		return biomeID;
	}
	
	
	
}
