package com.dvoragames.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.dvoragames.main.Game;

public class FireGun extends Entity{
	
	public int xTarget,yTarget;
	public boolean isAtack;
	public int time = 0,time2 = 0;
	
	public static int priceF = 200;
	
	public static BufferedImage FIRE = Game.spritesheet.getSprite(32, 80, 16, 16);

	public FireGun(double x, double y, int width, int height, double speed, BufferedImage sprite) {
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
				if(Entity.calculateDistance(this.getX(), this.getY(), xEnemy, yEnemy) < 50) {
					enemy = (Enemy)e;
				}
			}else if(e instanceof Boss) {
				int xBoss = e.getX();
				int yBoss = e.getY();
				if(Entity.calculateDistance(this.getX(), this.getY(), xBoss, yBoss) < 50) {
					boss = (Boss)e;
				}
			}
		}
		
		if(enemy != null) {
			isAtack = true;
			if(isAtack) {
				time++;
				if(time < 40) {
					xTarget = enemy.getX();
					yTarget = enemy.getY();
					enemy.vida-=2;
				}else {
					isAtack = false;
					time2++;
					if(time2 == 60) {
						time = 0;
						time2 = 0;
					}
				}
			}
		}else if(boss != null) {
			isAtack = true;
			if(isAtack) {
				time++;
				if(time < 40) {
					xTarget = boss.getX();
					yTarget = boss.getY();
					boss.vida-=2;
				}else {
					isAtack = false;
					time2++;
					if(time2 == 60) {
						time = 0;
						time2 = 0;
					}
				}
			}
		}else {
			isAtack = false;
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		double degrees = Math.atan2(xTarget-getX(), yTarget-getY());
		
		if(isAtack) {
			g.setColor(Color.RED);
			g.drawLine((int)x+8, (int)y+8, xTarget+8, yTarget+8);
		}
		
		g2.rotate(-degrees+Math.toRadians(180), getX()+8, getY()+8);
		g2.drawImage(FIRE, getX(), getY(), null);	
		g2.rotate(degrees+Math.toRadians(180), getX()+8, getY()+8);
	}
}
