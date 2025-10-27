package org.toast.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	public static final int width = 32;
	public static final int height = 32;
	
	
	
	
	//Blocks
	public static BufferedImage air,dirt,snow,ice,graphite_ore,sand,sandstone;
	public static BufferedImage[] grass,stone,water,planks,logs,diamond_ore,wool,concrete,destroying;
	
	//static Entities
	public static BufferedImage cactus,rock,crate;
	public static BufferedImage[] cornCrop,cottonCrop,lettuceCrop,pineappleCrop,furnace,trees;
	
	//Items	
	
	public static BufferedImage stick,bass,corn,avacado,backpack,glasses,tinted_glasses,seed,cotton,lettuce,pineapple,
								night_vision_helmet,trident,terra_blade,mana_sheath,paladin_axe,nano_chestplate,salmon_bucket,tropical_fish_bucket,
								cod_bucket,nano_leggings,coal,blueprint,wirecutters,skyhook,hammer,nano_boots,
								aluminum_dust,coke_dust,constantan_dust,copper_dust,electrum_dust,gold_dust,graphite_dust,
								iron_dust,lead_dust,nickel_dust,quartz_dust,silver_dust,steel_dust,uranium_dust,
								aluminum_bar,coke_bar,constantan_bar,copper_bar,electrum_bar,gold_bar,graphite_bar,
								iron_bar,lead_bar,nickel_bar,quartz_bar,silver_bar,steel_bar,uranium_bar,
								oshit,crafting_station,diamond,stone_pickaxe;

	//CHARACTER
	public static BufferedImage[] playerDown,playerLeft,playerUp,playerRight;					   //PLAYER SWIMMING EVENTUALLY MAKE ANIMATION
	public static BufferedImage playerSwimingDown,playerSwimingLeft,playerSwimingUp,playerSwimingRight;
		
		
	
	//UI
	public static BufferedImage background,title,minimap,castlebackground;
	public static BufferedImage[] buttons;
	public static BufferedImage inventory_gui,furnace_gui,crate_gui,crafting_station_gui,hotbar,selectedItem;




	
	public static Font menu,debug,updateLog,newMenu,achievementLOL;
	public static Color InventoryGray,tan,tinted_black;
	public static Color biome_sky,biome_cave,black;
	
	public static void init() {
		
		BlockInit();
		itemInit();
		entityInit();
		uiInit();
		

	}
	
	
	
	private static void BlockInit() {
		SpriteSheet tileSheet = new SpriteSheet(ImageLoader.loadImage("/textures/TileSheet.png"));
		
		air = tileSheet.crop(0, 0, width, height);
		dirt = tileSheet.crop(width * 0, height * 1, width, height);
		
		grass = new BufferedImage[4];
		grass[0] = tileSheet.crop(width * 1, height * 1, width, height);
		grass[1] = tileSheet.crop(width * 2, height * 1, width, height);
		grass[2] = tileSheet.crop(width * 3, height * 1, width, height);
		grass[3] = tileSheet.crop(width * 4, height * 1, width, height);
		
		
		
		water = new BufferedImage[3];
		water[0] = tileSheet.crop(width * 5, 0, width, height);
		water[1] = tileSheet.crop(width * 6, 0, width, height);
		water[2] = tileSheet.crop(width * 7, 0, width, height); 
		
		
		
		snow = tileSheet.crop(width * 6, height * 1, width, height);
		ice = tileSheet.crop(width * 7, height * 1, width, height);
		
		
		stone = new BufferedImage[5];
		stone[0] = tileSheet.crop(width * 0, height * 2, width, height);
		stone[1] = tileSheet.crop(width * 1, height * 2, width, height);
		stone[2] = tileSheet.crop(width * 2, height * 2, width, height);
		stone[3] = tileSheet.crop(width * 3, height * 2, width, height);
		stone[4] = tileSheet.crop(width * 4, height * 2, width, height);
		
		
		
		graphite_ore = tileSheet.crop(width * 5, height * 2, width, height);
		
		diamond_ore = new BufferedImage[5];
		diamond_ore[0] = tileSheet.crop(width * 6, height * 2, width, height);
		diamond_ore[1] = tileSheet.crop(width * 7, height * 2, width, height);
		diamond_ore[2] = tileSheet.crop(width * 8, height * 2, width, height);
		diamond_ore[3] = tileSheet.crop(width * 9, height * 2, width, height);
		diamond_ore[4] = tileSheet.crop(width * 10, height * 2, width, height);
		
		sand = tileSheet.crop(width * 0, height * 3, width, height);
		sandstone = tileSheet.crop(width * 1, height * 3, width, height);
		
		//logs
		logs = new BufferedImage[5];
		logs[0] = tileSheet.crop(width * 0, height * 5, width, height);
		logs[1] = tileSheet.crop(width * 1, height * 5, width, height);
		logs[2] = tileSheet.crop(width * 2, height * 5, width, height);
		logs[3] = tileSheet.crop(width * 3, height * 5, width, height);
		logs[4] = tileSheet.crop(width * 4, height * 5, width, height);
		//planks
		
		planks = new BufferedImage[5];
		planks[0] = tileSheet.crop(width * 0, height * 4, width, height);
		planks[1] = tileSheet.crop(width * 1, height * 4, width, height);
		planks[2] = tileSheet.crop(width * 2, height * 4, width, height);
		planks[3] = tileSheet.crop(width * 3, height * 4, width, height);
		planks[4] = tileSheet.crop(width * 4, height * 4, width, height);

		destroying = new BufferedImage[10];
		wool = new BufferedImage[10];
		concrete = new BufferedImage[10];
		
		for(int y = 0;y < 10;y++) {
			destroying[y] = tileSheet.crop(width * 13, height * y, width, height);
			wool[y] = tileSheet.crop(width * 14, height * y, width, height);
			concrete[y] = tileSheet.crop(width * 15, height * y, width, height);
		}
		
		
		
		
		
		
		
		
		
		
		
	}
	private static void itemInit() {
		SpriteSheet itemSheet = new SpriteSheet(ImageLoader.loadImage("/textures/itemSheet.png"));
		stick = itemSheet.crop(width * 0,height * 0, width, height);
		seed = itemSheet.crop(width * 1,height * 1, width, height);
		
		bass = itemSheet.crop(width * 7,height * 0, width, height);
		corn = itemSheet.crop(width * 8,height * 0, width, height);
		avacado = itemSheet.crop(width * 9,height * 0, width, height);
		
		cotton = itemSheet.crop(width * 7,height * 1, width, height);
		lettuce = itemSheet.crop(width * 8,height * 1, width, height);
		pineapple = itemSheet.crop(width * 9,height * 1, width, height);
		
		backpack = itemSheet.crop(width * 14,height * 0, width, height);
		glasses = itemSheet.crop(width * 15,height * 0, width, height);
		tinted_glasses = itemSheet.crop(width * 15,height * 1, width, height);
		
		trident = itemSheet.crop(width * 0,height * 3, width, height);
		terra_blade = itemSheet.crop(width * 1,height * 3, width, height);
		mana_sheath = itemSheet.crop(width * 2,height * 3, width, height);
		paladin_axe = itemSheet.crop(width * 3,height * 3, width, height);
		
		salmon_bucket = itemSheet.crop(width * 2,height * 4, width, height);
		tropical_fish_bucket = itemSheet.crop(width * 2,height * 4, width, height);
		cod_bucket = itemSheet.crop(width * 2,height * 4, width, height);
		
		coal = itemSheet.crop(width * 0,height * 5, width, height);
		blueprint = itemSheet.crop(width * 1,height * 5, width, height);
		wirecutters = itemSheet.crop(width * 3,height * 5, width, height);
		skyhook = itemSheet.crop(width * 4,height * 5, width, height);
		hammer = itemSheet.crop(width * 5,height * 5, width, height);
		
		//////DUST
		aluminum_dust = itemSheet.crop(width * 0,height * 6, width, height);
		coke_dust = itemSheet.crop(width * 1,height * 6, width, height);
		constantan_dust = itemSheet.crop(width * 2,height * 6, width, height);
		copper_dust = itemSheet.crop(width * 3,height * 6, width, height);
		electrum_dust = itemSheet.crop(width * 4,height * 6, width, height);
		gold_dust = itemSheet.crop(width * 5,height * 6, width, height);
		graphite_dust = itemSheet.crop(width * 6,height * 6, width, height);
		
		iron_dust = itemSheet.crop(width * 0,height * 7, width, height);
		lead_dust = itemSheet.crop(width * 1,height * 7, width, height);
		nickel_dust = itemSheet.crop(width * 2,height * 7, width, height);
		quartz_dust = itemSheet.crop(width * 3,height * 7, width, height);
		silver_dust = itemSheet.crop(width * 4,height * 7, width, height);
		steel_dust = itemSheet.crop(width * 5,height * 7, width, height);
		uranium_dust = itemSheet.crop(width * 6,height * 7, width, height);
		//////////BARS
		aluminum_bar = itemSheet.crop(width * 0,height * 8, width, height);
		coke_bar = itemSheet.crop(width * 1,height * 8, width, height);
		constantan_bar = itemSheet.crop(width * 2,height * 8, width, height);
		copper_bar = itemSheet.crop(width * 3,height * 8, width, height);
		electrum_bar = itemSheet.crop(width * 4,height * 8, width, height);
		gold_bar = itemSheet.crop(width * 5,height * 8, width, height);
		graphite_bar = itemSheet.crop(width * 6,height * 8, width, height);
		
		iron_bar = itemSheet.crop(width * 0,height * 9, width, height);
		lead_bar = itemSheet.crop(width * 1,height * 9, width, height);
		nickel_bar = itemSheet.crop(width * 2,height * 9, width, height);
		quartz_bar = itemSheet.crop(width * 3,height * 9, width, height);
		silver_bar = itemSheet.crop(width * 4,height * 9, width, height);
		steel_bar = itemSheet.crop(width * 5,height * 9, width, height);
		uranium_bar = itemSheet.crop(width * 6,height * 9, width, height);
		
		diamond = itemSheet.crop(width * 7,height * 6, width, height);
		
		night_vision_helmet = itemSheet.crop(width * 15,height * 2, width, height);
		nano_chestplate = itemSheet.crop(width * 15,height * 3, width, height);
		nano_leggings = itemSheet.crop(width * 15,height * 4, width, height);
		nano_boots = itemSheet.crop(width * 15,height * 5, width, height);
		
		stone_pickaxe = itemSheet.crop(width * 4,height * 3, width, height);

		
		
		
		
	}
	private static void entityInit() {
		SpriteSheet staticEntitySheet = new SpriteSheet(ImageLoader.loadImage("/textures/StaticSheet.png"));
		
		trees = new BufferedImage[5];
		trees[0] = staticEntitySheet.crop(79, 0, 63, 85);
		trees[1] = staticEntitySheet.crop(159, 0, 101, 94);
		trees[2] = staticEntitySheet.crop(0, 0, 63, 85);
		trees[3] = staticEntitySheet.crop(302, 2, 83, 82);
		trees[4] = staticEntitySheet.crop(412, 1, 90, 95);
		
		
		
		rock = staticEntitySheet.crop(width * 4, height * 0, width * 3, height * 3); 
		cactus = staticEntitySheet.crop(width * 10, height * 4, 53, 84);
		
		cornCrop = new BufferedImage[3];
		cottonCrop = new BufferedImage[3];
		lettuceCrop = new BufferedImage[3];
		pineappleCrop = new BufferedImage[3];
		
		cornCrop[0] = staticEntitySheet.crop(width * 1, height * 3, width, height);
		cornCrop[1] = staticEntitySheet.crop(width * 2, height * 3, width, height);
		cornCrop[2] = staticEntitySheet.crop(width * 3, height * 3, width, height);
		
		cottonCrop[0] = staticEntitySheet.crop(width * 1, height * 4, width, height);
		cottonCrop[1] = staticEntitySheet.crop(width * 2, height * 4, width, height);
		cottonCrop[2] = staticEntitySheet.crop(width * 3, height * 4, width, height);
		
		lettuceCrop[0] = staticEntitySheet.crop(width * 1, height * 5, width, height);
		lettuceCrop[1] = staticEntitySheet.crop(width * 2, height * 5, width, height);
		lettuceCrop[2] = staticEntitySheet.crop(width * 3, height * 5, width, height);
		
		pineappleCrop[0] = staticEntitySheet.crop(width * 1, height * 6, width, height);
		pineappleCrop[1] = staticEntitySheet.crop(width * 2, height * 6, width, height);
		pineappleCrop[2] = staticEntitySheet.crop(width * 3, height * 6, width, height);
		
		//Crafting Stuff
		crate = staticEntitySheet.crop(width * 0, height * 3, width, height);
		crafting_station = staticEntitySheet.crop(width * 0, height * 4, width, height);
		
		furnace = new BufferedImage[2];
		
		furnace[0] = staticEntitySheet.crop(width * 0, height * 5, width, height);
		furnace[1] = staticEntitySheet.crop(width * 0, height * 6, width, height);
		
		playerDown = new BufferedImage[4];
		playerUp = new BufferedImage[4];
		playerRight = new BufferedImage[3];
		playerLeft = new BufferedImage[3];
		
		playerDown[0] = staticEntitySheet.crop(width * 0, height * 14, width, height);
		playerDown[1] = staticEntitySheet.crop(width * 1, height * 14, width, height);
		playerDown[2] = staticEntitySheet.crop(width * 2, height * 14, width, height);
		playerDown[3] = staticEntitySheet.crop(width * 3, height * 14, width, height);
		
		playerUp[0] = staticEntitySheet.crop(width * 4, height * 14, width, height);
		playerUp[1] = staticEntitySheet.crop(width * 5, height * 14, width, height);
		playerUp[2] = staticEntitySheet.crop(width * 6, height * 14, width, height);
		playerUp[3] = staticEntitySheet.crop(width * 7, height * 14, width, height);
		
		playerRight[0] = staticEntitySheet.crop(width * 8, height * 14, width, height);
		playerRight[1] = staticEntitySheet.crop(width * 9, height * 14, width, height);
		playerRight[2] = staticEntitySheet.crop(width * 10, height * 14, width, height);
		
		playerLeft[0] = staticEntitySheet.crop(width * 11, height * 14, width, height);
		playerLeft[1] = staticEntitySheet.crop(width * 12, height * 14, width, height);
		playerLeft[2] = staticEntitySheet.crop(width * 13, height * 14, width, height);
		
		playerSwimingRight = staticEntitySheet.crop(width * 0, height * 15, width, height);
		playerSwimingLeft = staticEntitySheet.crop(width * 1, height * 15, width, height);
		playerSwimingUp = staticEntitySheet.crop(width * 2, height * 15, width, height);
		playerSwimingDown = staticEntitySheet.crop(width * 3, height * 15, width, height);
		
		
		
		
	}
	private static void uiInit() {
		background = ImageLoader.loadImage("/textures/UI/misc/background.png");
		title = ImageLoader.loadImage("/textures/UI/misc/title.png");
		castlebackground = ImageLoader.loadImage("/textures/UI/misc/castlebackground.png");
		
		SpriteSheet UISheet = new SpriteSheet(ImageLoader.loadImage("/textures/UI/misc/widgets.png"));
		
		buttons = new BufferedImage[2];
		buttons[0] = UISheet.crop(0, 172, 400, 40);
		buttons[1] = UISheet.crop(0, 132, 400, 40);
		
		
		//Containers
		inventory_gui = ImageLoader.loadImage("/textures/UI/Container/inventory.png");
		furnace_gui = ImageLoader.loadImage("/textures/UI/Container/furnacePanel.png");
		crate_gui = ImageLoader.loadImage("/textures/UI/Container/cratePanel.png");
		crafting_station_gui = ImageLoader.loadImage("/textures/UI/Container/craftingPanel.png");
		
		SpriteSheet hotbar_gui = new SpriteSheet(ImageLoader.loadImage("/textures/UI/Container/hotbar.png"));
		hotbar = hotbar_gui.crop(1, 1, 362, 42);
		selectedItem = hotbar_gui.crop(1, 45, 46, 46); 
		
		
		
		
		
		
		//FONTS
		menu = new Font("Times New Roman",Font.BOLD,24);
		debug = new Font("Verdana",Font.BOLD,16);
		updateLog = new Font("Verdana",Font.PLAIN,20);
		newMenu = new Font("Times New Roman",Font.BOLD,48);
		achievementLOL = new Font("Times New Roman",Font.PLAIN,96);
		
		//Color
		InventoryGray = new Color(220,220,220);
		tan = new Color(255,178,102);
		tinted_black = new Color(0,0,0,0.2f);
		
		black = new Color(0,0,0);
		biome_sky = new Color(173,216,255);
		biome_cave = new Color(48,46,46);
		
		
		Color[] light = new Color[100];
		for(int i = 0;i < light.length;i++) {
			light[i] = new Color(0,0,0,i/100f);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
}
