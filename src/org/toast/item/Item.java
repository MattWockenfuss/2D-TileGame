package org.toast.item;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.toast.item.items.Avacado;
import org.toast.item.items.BackPack;
import org.toast.item.items.Bass;
import org.toast.item.items.BluePrint;
import org.toast.item.items.Coal;
import org.toast.item.items.CodBucket;
import org.toast.item.items.Corn;
import org.toast.item.items.Cotton;
import org.toast.item.items.CraftingStationItem;
import org.toast.item.items.CrateItem;
import org.toast.item.items.Diamond;
import org.toast.item.items.FurnaceItem;
import org.toast.item.items.Glasses;
import org.toast.item.items.Hammer;
import org.toast.item.items.Lettuce;
import org.toast.item.items.ManaSheath;
import org.toast.item.items.NanoBoots;
import org.toast.item.items.NanoLeggings;
import org.toast.item.items.NightVisionHelmet;
import org.toast.item.items.PaladinAxe;
import org.toast.item.items.PineApple;
import org.toast.item.items.SalmonBucket;
import org.toast.item.items.Seed;
import org.toast.item.items.SkyHook;
import org.toast.item.items.Stick;
import org.toast.item.items.StonePickaxe;
import org.toast.item.items.TerraBlade;
import org.toast.item.items.TintedGlasses;
import org.toast.item.items.Trident;
import org.toast.item.items.TropicalFishBucket;
import org.toast.item.items.WireCutters;
import org.toast.item.items.blockItems.ConcreteAqua;
import org.toast.item.items.blockItems.ConcreteBlue;
import org.toast.item.items.blockItems.ConcreteGreen;
import org.toast.item.items.blockItems.ConcreteMagenta;
import org.toast.item.items.blockItems.ConcreteOrange;
import org.toast.item.items.blockItems.ConcretePink;
import org.toast.item.items.blockItems.ConcretePurple;
import org.toast.item.items.blockItems.ConcreteRed;
import org.toast.item.items.blockItems.ConcreteWhite;
import org.toast.item.items.blockItems.ConcreteYellow;
import org.toast.item.items.blockItems.FirLogBlockItem;
import org.toast.item.items.blockItems.FirPlanksBlockItem;
import org.toast.item.items.blockItems.Grass1BlockItem;
import org.toast.item.items.blockItems.Grass2BlockItem;
import org.toast.item.items.blockItems.Grass3BlockItem;
import org.toast.item.items.blockItems.Grass4BlockItem;
import org.toast.item.items.blockItems.JungleLogBlockItem;
import org.toast.item.items.blockItems.JunglePlanksBlockItem;
import org.toast.item.items.blockItems.MapleLogBlockItem;
import org.toast.item.items.blockItems.MaplePlanksBlockItem;
import org.toast.item.items.blockItems.PineLogBlockItem;
import org.toast.item.items.blockItems.PinePlanksBlockItem;
import org.toast.item.items.blockItems.SavannaLogBlockItem;
import org.toast.item.items.blockItems.SavannaPlanksBlockItem;
import org.toast.item.items.blockItems.Stone1BlockItem;
import org.toast.item.items.blockItems.Stone2BlockItem;
import org.toast.item.items.blockItems.Stone3BlockItem;
import org.toast.item.items.blockItems.Stone4BlockItem;
import org.toast.item.items.blockItems.Stone5BlockItem;
import org.toast.item.items.blockItems.WoolAqua;
import org.toast.item.items.blockItems.WoolBlue;
import org.toast.item.items.blockItems.WoolGreen;
import org.toast.item.items.blockItems.WoolMagenta;
import org.toast.item.items.blockItems.WoolOrange;
import org.toast.item.items.blockItems.WoolPink;
import org.toast.item.items.blockItems.WoolPurple;
import org.toast.item.items.blockItems.WoolRed;
import org.toast.item.items.blockItems.WoolWhite;
import org.toast.item.items.blockItems.WoolYellow;
import org.toast.item.items.blockItems._DirtBlockItem;
import org.toast.item.items.blockItems._IceBlockItem;
import org.toast.item.items.blockItems._SandBlockItem;
import org.toast.item.items.blockItems._SandStoneBlockItem;
import org.toast.item.items.blockItems._SnowBlockItem;
import org.toast.item.items.metals.AluminumBar;
import org.toast.item.items.metals.AluminumDust;
import org.toast.item.items.metals.CokeBar;
import org.toast.item.items.metals.CokeDust;
import org.toast.item.items.metals.ConstantanBar;
import org.toast.item.items.metals.ConstantanDust;
import org.toast.item.items.metals.CopperBar;
import org.toast.item.items.metals.CopperDust;
import org.toast.item.items.metals.ElectrumBar;
import org.toast.item.items.metals.ElectrumDust;
import org.toast.item.items.metals.GoldBar;
import org.toast.item.items.metals.GoldDust;
import org.toast.item.items.metals.GraphiteBar;
import org.toast.item.items.metals.GraphiteDust;
import org.toast.item.items.metals.IronBar;
import org.toast.item.items.metals.IronDust;
import org.toast.item.items.metals.LeadBar;
import org.toast.item.items.metals.LeadDust;
import org.toast.item.items.metals.NickelBar;
import org.toast.item.items.metals.NickelDust;
import org.toast.item.items.metals.QuartzBar;
import org.toast.item.items.metals.QuartzDust;
import org.toast.item.items.metals.SilverBar;
import org.toast.item.items.metals.SilverDust;
import org.toast.item.items.metals.SteelBar;
import org.toast.item.items.metals.SteelDust;
import org.toast.item.items.metals.UraniumBar;
import org.toast.item.items.metals.UraniumDust;

public class Item {
	
	public static Item[] items = new Item[256];
	
	public static Item stick = new Stick(1,64);
	
	public static Item bass = new Bass(2,64);
	public static Item corn = new Corn(3,64);
	public static Item avacado = new Avacado(4,64);
	public static Item cotton = new Cotton(53,64);
	public static Item lettuce = new Lettuce(54,64);
	public static Item pineapple = new PineApple(55,64);
	
	
	public static Item backpack = new BackPack(5,1);
	public static Item glasses = new Glasses(6,1);
	public static Item tinted_glasses = new TintedGlasses(7,1);
	public static Item seed = new Seed(8,64);
	
	public static Item trident = new Trident(9,1);
	public static Item terra_blade = new TerraBlade(10,1);
	public static Item mana_sheath = new ManaSheath(11,1);
	public static Item paladin_axe = new PaladinAxe(12,1);
	
	public static Item salmon_bucket = new SalmonBucket(13,16);
	public static Item tropical_fish_bucket = new TropicalFishBucket(14,16);
	public static Item cod_bucket = new CodBucket(15,16);
	
	public static Item coal = new Coal(16,64);
	public static Item blueprint = new BluePrint(19,64);
	
	public static Item wirecutter = new WireCutters(17,1);
	public static Item skyhook = new SkyHook(18,1);
	public static Item hammer = new Hammer(19,1);
	
	
	///////////////DUSTS
	public static Item aluminum_dust = new AluminumDust(20,64);
	public static Item coke_dust = new CokeDust(21,64);
	public static Item constantan_dust = new ConstantanDust(22,64);
	public static Item copper_dust = new CopperDust(23,64);
	public static Item electrum_dust = new ElectrumDust(24,64);
	public static Item gold_dust = new GoldDust(25,64);
	public static Item graphite_dust = new GraphiteDust(26,64);
	
	public static Item iron_dust = new IronDust(27,64);
	public static Item lead_dust = new LeadDust(28,64);
	public static Item quartz_dust = new QuartzDust(29,64);
	public static Item nickel_dust = new NickelDust(30,64);
	public static Item silver_dust = new SilverDust(31,64);
	public static Item steel_dust = new SteelDust(32,64);
	public static Item uranium_dust = new UraniumDust(33,64);
	
	///////////////////Bars
	
	public static Item aluminum_bar = new AluminumBar(34,64);
	public static Item coke_bar = new CokeBar(35,64);
	public static Item constantan_bar = new ConstantanBar(36,64);
	public static Item copper_bar = new CopperBar(37,64);
	public static Item electrum_bar = new ElectrumBar(38,64);
	public static Item gold_bar = new GoldBar(39,64);
	public static Item graphite_bar = new GraphiteBar(40,64);
	
	public static Item iron_bar = new IronBar(41,64);
	public static Item lead_bar = new LeadBar(42,64);
	public static Item quartz_bar = new QuartzBar(43,64);
	public static Item nickel_bar = new NickelBar(44,64);
	public static Item silver_bar = new SilverBar(45,64);
	public static Item steel_bar = new SteelBar(46,64);
	public static Item uranium_bar = new UraniumBar(47,64);
	
	public static Item Diamond = new Diamond(48,64);
	
	public static Item night_vision_helmet = new NightVisionHelmet(49,1);
	public static Item nano_chestplate = new NanoBoots(50,1);
	public static Item nano_leggings = new NanoLeggings(51,1);
	public static Item nano_boots = new NanoBoots(52,1);
	
	//Crafting
	public static Item crate = new CrateItem(56,64);
	public static Item crafting_station = new CraftingStationItem(57,64);
	public static Item furnace = new FurnaceItem(58,64);
	
	//Block Items
	public static Item dirt_block = new _DirtBlockItem(59,64);
	public static Item grass_block1 = new Grass1BlockItem(60,64);
	public static Item grass_block2 = new Grass2BlockItem(61,64);
	public static Item grass_block3 = new Grass3BlockItem(62,64);
	public static Item grass_block4 = new Grass4BlockItem(63,64);
	
	public static Item snow_block = new _SnowBlockItem(64,64);
	public static Item ice_block = new _IceBlockItem(65,64);
	
	public static Item stone1 = new Stone1BlockItem(66,64);
	public static Item stone2 = new Stone2BlockItem(67,64);
	public static Item stone3 = new Stone3BlockItem(68,64);
	public static Item stone4 = new Stone4BlockItem(69,64);
	public static Item stone5 = new Stone5BlockItem(70,64);
	public static Item sand_block = new _SandBlockItem(71,64);
	public static Item sandstone_block = new _SandStoneBlockItem(72,64);
	
	public static Item fir_log = new FirLogBlockItem(73,64);
	public static Item pine_log = new PineLogBlockItem(74,64);
	public static Item maple_log = new MapleLogBlockItem(75,64);
	public static Item savanna_log = new SavannaLogBlockItem(76,64);
	public static Item jungle_log = new JungleLogBlockItem(77,64);
	
	public static Item fir_planks = new FirPlanksBlockItem(78,64);
	public static Item pine_planks = new PinePlanksBlockItem(79,64);
	public static Item maple_planks = new MaplePlanksBlockItem(80,64);
	public static Item savanna_planks = new SavannaPlanksBlockItem(81,64);
	public static Item jungle_planks = new JunglePlanksBlockItem(82,64);
	
	public static Item RedWool = new WoolRed(83,64);
	public static Item OrangeWool = new WoolOrange(84,64);
	public static Item YellowWool = new WoolYellow(85,64);
	public static Item GreenWool = new WoolGreen(86,64);
	public static Item BlueWool = new WoolBlue(87,64);
	public static Item AquaWool = new WoolAqua(88,64);
	public static Item MagentaWool = new WoolMagenta(89,64);
	public static Item PurpleWool = new WoolPurple(90,64);
	public static Item PinkWool = new WoolPink(91,64);
	public static Item WhiteWool = new WoolWhite(92,64);
	
	public static Item RedConcrete = new ConcreteRed(93,64);
	public static Item OrangeConcrete = new ConcreteOrange(94,64);
	public static Item YellowConcrete = new ConcreteYellow(95,64);
	public static Item GreenConcrete = new ConcreteGreen(96,64);
	public static Item BlueConcrete = new ConcreteBlue(97,64);
	public static Item AquaConcrete = new ConcreteAqua(98,64);
	public static Item MagentaConcrete = new ConcreteMagenta(99,64);
	public static Item PurpleConcrete = new ConcretePurple(100,64);
	public static Item PinkConcrete = new ConcretePink(101,64);
	public static Item WhiteConcrete = new ConcreteWhite(102,64);
	
	public static Item Stone_Pickaxe = new StonePickaxe(103,1);
	
	
	
	//////////////////////////////////////////////////////START OF ITEM CLASS///////////////////////////////////////////////////////////////////
	protected BufferedImage texture;
	protected final int id;
	protected final int maxStackSize;
	protected String displayName;
	protected boolean canBePlaced = false;
	
	//Items look a little small so i added a scale
	protected static final float SCALE = 1.0f;
	public static final int DEFAULT_STRENGH = 1;
	
	public Item(BufferedImage texture,int id,int maxStackSize) {
		this.texture = texture;
		this.id = id;
		this.maxStackSize = maxStackSize;
		items[id] = this;
	}
	
	
	public void tick() {
		
	}
	public void render(Graphics g,int x,int y) {
		g.drawImage(texture, x, y,(int)(32 * SCALE),(int)(32 * SCALE), null);
	}
	public void renderInventory(Graphics g,int x,int y,int width,int height) {
		g.drawImage(texture, x, y,width,height, null);
	}
	
	
	
	public boolean canBePlaced() {
		return canBePlaced;
	}
	public int getStrength() {
		return DEFAULT_STRENGH;
	}
	
	///////////////////////////////////////////////////////////GETTERS AND SETTERS/////////////////////////////////////////////////////
	public int getID() {
		return id;
	}
	public int getMaxStackSize() {
		return maxStackSize;
	}
	public String getDisplayName() {
		if(this.displayName != null) {
			return this.displayName;
		}else {
			return this.getClass().getSimpleName();
		}
		
	}
	
	
	
}
