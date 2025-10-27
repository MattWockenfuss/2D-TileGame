package org.toast.utils;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Utils {
	
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine()) != null)
				builder.append(line + "\n");
			
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return builder.toString();
		
		
	}
	
	
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
			
			
		}catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	//random number gen
	public static int randomNumber(int min,int max) {		//THIS METHOD IS INCLUSIVE (AKA INCLUDING MIN AND MAX)											//https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
		Random r = new Random();
		return r.nextInt((max-min) + 1) + min;
	}
	public static int randomNumber(int seed, int min,int max) {//random number gen with seed
		Random r = new Random(seed); 
		return r.nextInt((max-min) + 1) + min;
	}
	
	
	
	public static Color InterpolateColors(int t, int maxT,Color startColor,Color endColor) {
		
		int r1 = startColor.getRed();
		int g1 = startColor.getGreen();
		int b1 = startColor.getBlue();
		
		int r2 = endColor.getRed();
		int g2 = endColor.getBlue();
		int b2 = endColor.getGreen();
		
		float tRatio = (float)t/maxT;
		//System.out.println(); 
		
		int rx = (int)((r2-r1) * tRatio);
		int gx = (int)((g2-g1) * tRatio);
		int bx = (int)((b2-b1) * tRatio);
		
		
		rx += r1;
		gx += g1;
		bx += b1;
		
		System.out.println(rx + "," + gx + "," + bx); 
		
		return new Color(rx,gx,bx); 
	}
	
	
	
}
