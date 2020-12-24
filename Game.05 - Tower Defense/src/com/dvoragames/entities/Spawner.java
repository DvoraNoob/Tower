package com.dvoragames.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.dvoragames.main.Game;

public class Spawner extends Entity{
	
	private int timer = 80, timerGen = 0;
	private int curTimer = 0;
	private int countEnemy = 0;
	private int maxEnemyMap = 10;
	private boolean boss = false;
	
	public int xTarget,yTarget;

	public Spawner(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		gen();
		timerGen++;
		if(Boss.bossDead || timerGen == 120) {
			Boss.bossDead = false;
			timer = 60;
			curTimer = 0;
			countEnemy = 0;
			gen();
		}
	}
	
	public void gen() {
		timerGen=0;
		if(!boss && countEnemy <= maxEnemyMap) {
			curTimer++;
			if(curTimer == timer) {
				curTimer = 0;
				timer = Entity.rand.nextInt((60-30)+30);
				Enemy enemy = new Enemy(x,y,5,7,1,Entity.ENEMY);
				Game.entities.add(enemy);
				countEnemy++;
				if(countEnemy == maxEnemyMap) {
					boss = true;
				}
			}
		}else if(boss && countEnemy == maxEnemyMap){
			boss = false;
			Boss boss = new Boss(x,y,16,16,1,Entity.BOSS);
			Game.entities.add(boss);
		}
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.red);
		//g.fillRect((int)x, (int)y, width, height);
	}


}
