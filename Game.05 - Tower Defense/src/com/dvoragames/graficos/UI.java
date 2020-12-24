package com.dvoragames.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.dvoragames.entities.CannonGun;
import com.dvoragames.entities.Castle;
import com.dvoragames.entities.FireGun;
import com.dvoragames.entities.Gun;
import com.dvoragames.entities.IceGun;
import com.dvoragames.entities.StormGun;
import com.dvoragames.main.Game;

public class UI {

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g.setColor(Color.RED);
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, 20);
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, ((Castle.lifeCastle/4)*3)*1 , 20);
		
		g.setColor(Color.white);
		g.setFont(new Font("ARIAL", Font.BOLD, 20));
		g.drawString("$: "+Game.money, 10, Game.HEIGHT*Game.SCALE-20);
		
		for(int i = 0; i < 5; i++) {
			g.setColor(Color.DARK_GRAY);
			g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*70/2))+(i*70), Game.HEIGHT*Game.SCALE-60, 60, 60);
			g.setColor(Color.WHITE);
			g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*68/2))+(i*70), Game.HEIGHT*Game.SCALE-55, 50, 50);
			
			if(Game.gun) {
				g.setColor(Color.ORANGE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*70/2))+(0*70), Game.HEIGHT*Game.SCALE-60, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*68/2))+(0*70), Game.HEIGHT*Game.SCALE-55, 50, 50);
			}else if(Game.ice) {
				g.setColor(Color.ORANGE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*70/2))+(1*70), Game.HEIGHT*Game.SCALE-60, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*68/2))+(1*70), Game.HEIGHT*Game.SCALE-55, 50, 50);
			}else if(Game.fire) {
				g.setColor(Color.ORANGE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*70/2))+(2*70), Game.HEIGHT*Game.SCALE-60, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*68/2))+(2*70), Game.HEIGHT*Game.SCALE-55, 50, 50);
			}else if(Game.storm) {
				g.setColor(Color.ORANGE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*70/2))+(3*70), Game.HEIGHT*Game.SCALE-60, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*68/2))+(3*70), Game.HEIGHT*Game.SCALE-55, 50, 50);
			}else if(Game.cannon) {
				g.setColor(Color.ORANGE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*70/2))+(4*70), Game.HEIGHT*Game.SCALE-60, 60, 60);
				g.setColor(Color.WHITE);
				g.fillRect(((Game.WIDTH*Game.SCALE/2)-(5*68/2))+(4*70), Game.HEIGHT*Game.SCALE-55, 50, 50);
			}
			
			g.drawImage(Gun.GUN,((Game.WIDTH*Game.SCALE/2)-(5*65/2)), Game.HEIGHT*Game.SCALE-42,36,36,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString("10", ((Game.WIDTH*Game.SCALE/2)-(5*68/2)), Game.HEIGHT*Game.SCALE-42);
			
			g.drawImage(IceGun.ICE,((Game.WIDTH*Game.SCALE/2)-(5*37/2)), Game.HEIGHT*Game.SCALE-41,36,36,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString("100", ((Game.WIDTH*Game.SCALE/2)-(5*40/2)), Game.HEIGHT*Game.SCALE-42);
			
			g.drawImage(FireGun.FIRE,((Game.WIDTH*Game.SCALE/2)-(5*9/2)), Game.HEIGHT*Game.SCALE-41,36,36,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString("200", ((Game.WIDTH*Game.SCALE/2)-(5*12/2)), Game.HEIGHT*Game.SCALE-42);
			
			g.drawImage(StormGun.STORM,((Game.WIDTH*Game.SCALE/2)-(5*-19/2)), Game.HEIGHT*Game.SCALE-41,36,36,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString("500", ((Game.WIDTH*Game.SCALE/2)-(5*-16/2)), Game.HEIGHT*Game.SCALE-42);
			
			g.drawImage(CannonGun.CANNON,((Game.WIDTH*Game.SCALE/2)-(5*-47/2)), Game.HEIGHT*Game.SCALE-41,36,36,null);
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 15));
			g.drawString("1000", ((Game.WIDTH*Game.SCALE/2)-(5*-44/2)), Game.HEIGHT*Game.SCALE-42);
		}
	}
	
}
