package com.dvoragames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.dvoragames.main.Game;
import com.dvoragames.world.AStar;
import com.dvoragames.world.Vector2i;

public class Enemy extends Entity{
	
	public boolean right = true,left = false;
	
	public int vida = 100;

	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
				
		path =  AStar.findPath(Game.world, 
				new Vector2i((int)x/16, (int)y/16), 
				new Vector2i((int)Game.castle.x/16, (int)Game.castle.y/16));
	}
	
	public void tick() {
		depth = 2;
		if(isCollidingCastle() == false) {
			followPath(path);
		}else {
			if(StormGun.flash) {
				Castle.lifeCastle-=2;
			}else {
				Castle.lifeCastle--;
			}
		}
		
		if(vida <= 0) {
			Game.entities.remove(this);
			Game.money+=10;
		}
	}
	
	public boolean isCollidingCastle() {
		Rectangle enemyCurrent = new Rectangle(this.getX(),this.getY(),16,16);
		Rectangle castle = new Rectangle(Game.castle.getX(), Game.castle.getY(), 16,16);
		
		return enemyCurrent.intersects(castle);
	}
	
	public void render(Graphics g) {
		super.render(g);
		
		g.setColor(Color.red);
		g.fillRect(getX()+4, getY()+6, 8, 2);
		g.setColor(Color.BLUE);
		g.fillRect(getX()+4, getY()+6, (vida/12)*1, 2);
	}

}
