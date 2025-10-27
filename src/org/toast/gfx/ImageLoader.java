package org.toast.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource("" + path));
			//Make SURE BEFORE YOU EXPORT to change Empty "" to "/res" before export to be able to read file, 
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1); 
		}
		return null;
	}
	
	
	
}
