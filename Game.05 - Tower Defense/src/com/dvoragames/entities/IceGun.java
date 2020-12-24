package com.dvoragames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.dvoragames.main.Game;

public class IceGun extends Entity{
	
	public int xTarget,yTarget,time;
	public boolean isAtack;
	
	public static int priceI = 100;
	
	public static BufferedImage ICE = Game.spritesheet.getSprite(16, 80, 16, 16);

	public IceGun(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		Enemy enemy = null;
		Boss boss = null;
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Enemy) {
				int xEnemy = e.getX();
				int yEnemy = e.getY();
				if(Entity.calculateDistance(this.getX(), this.getY(), xEnemy, yEnemy) < 70) {
					enemy = (Enemy)e;
				}
			}else if(e instanceof Boss) {
				int xBoss = e.getX();
				int yBoss = e.getY();
				if(Entity.calculateDistance(this.getX(), this.getY(), xBoss, yBoss) < 70) {
					boss = (Boss)e;
				}
			}
		}
		
		if(enemy != null) {
			isAtack = true;
			if(isAtack) {
				xTarget = enemy.getX();
				yTarget = enemy.getY();
				time++;
				if(time == 2) {
					enemy.vida-=0.1;
					time = 0;
				}
				enemy.speed = 0.5;
			}else {
				enemy.speed = 1;
			}
		}else if(boss != null) {
			isAtack = true;
			if(isAtack) {
				xTarget = boss.getX();
				yTarget = boss.getY();
				time++;
				if(time == 2) {
					boss.vida-=0.1;
					time = 0;
				}
				boss.speed = 0.5;
			}else {
				boss.speed = 1;
			}
		}else{
			isAtack = false;
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double degrees = Math.atan2(xTarget-getX(), yTarget-getY());
		
		if(isAtack) {
			g.setColor(Color.BLUE);
			g.drawLine((int)x+8, (int)y+8, xTarget+8, yTarget+8);
		}
		
		g2.rotate(-degrees+Math.toRadians(180), getX()+8, getY()+8);
		g2.drawImage(ICE, getX(), getY(), null);	
		g2.rotate(degrees+Math.toRadians(180), getX()+8, getY()+8);


	}
}
