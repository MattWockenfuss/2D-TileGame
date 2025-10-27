package org.toast.blocks;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.toast.blocks.animated.DiamondOreBlock;
import org.toast.blocks.animated.WaterBlock;
import org.toast.blocks.color.Concrete;
import org.toast.blocks.color.Wool;
import org.toast.blocks.stone.GrassBlock;
import org.toast.blocks.stone.GrassBlock2;
import org.toast.blocks.stone.GrassBlock3;
import org.toast.blocks.stone.GrassBlock4;
import org.toast.blocks.stone.Stone1;
import org.toast.blocks.stone.Stone2;
import org.toast.blocks.stone.Stone3;
import org.toast.blocks.stone.Stone4;
import org.toast.blocks.stone.Stone5;
import org.toast.blocks.wood.logs.FirLogBlock;
import org.toast.blocks.wood.logs.JungleLogBlock;
import org.toast.blocks.wood.logs.MapleLogBlock;
import org.toast.blocks.wood.logs.PineLogBlock;
import org.toast.blocks.wood.logs.SavannaLogBlock;
import org.toast.blocks.wood.planks.FirPlanksBlock;
import org.toast.blocks.wood.planks.JunglePlanksBlock;
import org.toast.blocks.wood.planks.MaplePlanksBlock;
import org.toast.blocks.wood.planks.PinePlanksBlock;
import org.toast.blocks.wood.planks.SavannaPlanksBlock;
import org.toast.entities.HUD.inventory.ItemStack;
import org.toast.gfx.Animation;
import org.toast.gfx.Assets;

public class Block {
	
	public static Block[] blocks = new Block[256];
	
	public static Block airBlock = new AirBlock(100);
	public static Block dirtBlock = new DirtBlock(102);
	public static Block waterBlock = new WaterBlock(103,new Animation(10000,Assets.water));
	
	public static Block grassBlock = new GrassBlock(104);
	public static Block tundraGrassBlock = new GrassBlock2(105);
	public static Block savannaGrassBlock = new GrassBlock3(106);
	public static Block jungleGrassBlock = new GrassBlock4(107);
	
	public static Block snowBlock = new SnowBlock(108);
	public static Block iceBlock = new IceBlock(109);
	
	public static Block stoneBlock1 = new Stone1(110);
	public static Block stoneBlock2 = new Stone2(111);
	public static Block stoneBlock3 = new Stone3(112);
	public static Block stoneBlock4 = new Stone4(113);
	public static Block stoneBlock5 = new Stone5(114);
	
	public static Block graphiteOreBlock = new GraphiteOreBlock(115);
	public static Block diamondOreBlock = new DiamondOreBlock(116,new Animation(300,Assets.diamond_ore));
	
	public static Block sandBlock = new SandBlock(117);
	public static Block sandStoneBlock = new SandStoneBlock(118);
	
	public static Block firLog = new FirLogBlock(119);
	public static Block pineLog = new PineLogBlock(120);
	public static Block mapleLog = new MapleLogBlock(121);
	public static Block savannaLog = new SavannaLogBlock(122);
	public static Block jungleLog = new JungleLogBlock(123);
	
	public static Block firPlanks = new FirPlanksBlock(124);
	public static Block pinePlanks = new PinePlanksBlock(125);
	public static Block maplePlanks = new MaplePlanksBlock(126);
	public static Block savannaPlanks = new SavannaPlanksBlock(127);
	public static Block junglePlanks = new JunglePlanksBlock(128);
	
	public static Block redWool = new Wool(Assets.wool[0],130);
	public static Block orangeWool = new Wool(Assets.wool[1],131);
	public static Block yellowWool = new Wool(Assets.wool[2],132);
	public static Block greenWool = new Wool(Assets.wool[3],133);
	public static Block blueWool = new Wool(Assets.wool[4],134);
	public static Block aquaWool = new Wool(Assets.wool[5],135);
	public static Block magentaWool = new Wool(Assets.wool[6],136);
	public static Block purpleWool = new Wool(Assets.wool[7],137);
	public static Block pinkWool = new Wool(Assets.wool[8],138);
	public static Block whiteWool = new Wool(Assets.wool[9],139);
	
	public static Block redConcrete = new Concrete(Assets.concrete[0],140);
	public static Block orangeConcrete = new Concrete(Assets.concrete[1],141);
	public static Block yellowConcrete = new Concrete(Assets.concrete[2],142);
	public static Block greenConcrete = new Concrete(Assets.concrete[3],143);
	public static Block blueConcrete = new Concrete(Assets.concrete[4],144);
	public static Block aquaConcrete = new Concrete(Assets.concrete[5],145);
	public static Block magentaConcrete = new Concrete(Assets.concrete[6],146);
	public static Block purpleConcrete = new Concrete(Assets.concrete[7],147);
	public static Block pinkConcrete = new Concrete(Assets.concrete[8],148);
	public static Block whiteConcrete = new Concrete(Assets.concrete[9],149);
	
	
		
	
	
	//CLASS
	
	public static final int BLOCKWIDTH = 48;
	public static final int BLOCKHEIGHT = 48;
	public static final int DEFAULT_BREAKTIME = 2000;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Block(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		blocks[id] = this;
		
	}
	
	public void tick() {
		
	}
	public void render(Graphics g, int x,int y) {
		g.drawImage(texture, x, y, BLOCKWIDTH, BLOCKHEIGHT,null);
	}
	public boolean isSwimable() {
		return false;
	}
	public boolean isSolid() {
		return false;
	}
	public ItemStack getItemDropped() {
		return null;
	}
	
	
	public int getID() {
		return id;
	}

	public int getBreakTime() {
		return DEFAULT_BREAKTIME;
	}
	
	
	
}
