package com.dvoragames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.dvoragames.main.Game;

public class GunP extends Entity{
	
	public BufferedImage[] GUN_P;

	public GunP(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		GUN_P = new BufferedImage[2];
		
		for(int i = 0; i < 2;i++) {
			GUN_P[i] = Game.spritesheet.getSprite(32, 0 + (i*16), 16, 16);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(GUN_P[0], getX(), getY(), null);
	}
}
