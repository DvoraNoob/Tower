package com.dvoragames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.dvoragames.main.Game;

public class Castle extends Entity{
	
	public static int lifeCastle = 1000;
		
	public static BufferedImage CASTLE = Game.spritesheet.getSprite(80, 80, 48, 48);

	public Castle(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void render(Graphics g) {
		g.drawImage(CASTLE, this.getX(), this.getY()-16, null);
	}

}
