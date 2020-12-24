package com.dvoragames.entities;

import java.awt.image.BufferedImage;

import com.dvoragames.main.Game;
import com.dvoragames.world.World;

public class GunController extends Entity{
		
	public boolean isPressed = false;
	public boolean create;
	
	public int countGun;
	
	public int xx,yy;
	public static int xTarget,yTarget;
	
	public Entity e;
	
	public Entity curGun;

	public GunController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}

	public void tick() {
		if(isPressed){
			isPressed = false;

			xx = (xTarget/16)*16;
			yy = (yTarget/16)*16;

			Gun gun = new Gun(xx,yy,16,16,0, null);
			IceGun ice = new IceGun(xx,yy,16,16,0,null);
			FireGun fire = new FireGun(xx,yy,16,16,0,null);
			StormGun storm = new StormGun(xx,yy,16,16,0,null);
			CannonGun cannon = new CannonGun(xx,yy,16,16,0,null);
			
			if(Game.gun) {
				curGun = gun;
			}else if(Game.ice) {
				curGun = ice;
			}else if(Game.fire) {
				curGun = fire;
			}else if(Game.storm) {
				curGun = storm;
			}else if(Game.cannon) {
				curGun = cannon;
			}
			
			for(countGun = 0; countGun < Game.entities.size(); countGun++) {
				e  = Game.entities.get(countGun);
				if(e instanceof GunP) {
					if(Entity.isColidding(e, curGun)) {
						createGun(curGun);
					}
				}else if(e instanceof Gun) {
					if(Game.gun) {
						if(Entity.isColidding(e, curGun)) {
							System.out.println("teste");
						}
					}else {
						if(Entity.isColidding(e, curGun)) {
							createGun(curGun);
						}
					}
				}else if(e instanceof IceGun) {
					if(Game.ice) {
						if(Entity.isColidding(e, curGun)) {
							System.out.println("teste");
						}
					}else {
						if(Entity.isColidding(e, curGun)) {
							createGun(curGun);
						}
					}
				}else if(e instanceof FireGun) {
					if(Game.fire) {
						if(Entity.isColidding(e, curGun)) {
							System.out.println("teste");
						}
					}else {
						if(Entity.isColidding(e, curGun)) {
							createGun(curGun);
						}
					}
				}else if(e instanceof StormGun) {
					if(Game.storm) {
						if(Entity.isColidding(e, curGun)) {
							System.out.println("teste");
						}
					}else {
						if(Entity.isColidding(e, curGun)) {
							createGun(curGun);
						}
					}
				}else if(e instanceof CannonGun) {
					if(Game.cannon) {
						if(Entity.isColidding(e, curGun)) {
							System.out.println("teste");
						}
					}else {
						if(Entity.isColidding(e, curGun)) {
							createGun(curGun);
						}
					}
				}
			}
		}
	}
	
	public void createGun(Entity typeGun) {
		
		if(Game.gun){
			if(Game.money >= 10) {
				Game.entities.add(curGun);
				Game.money-=10;
				Game.entities.remove(countGun);
			}
		}else if(Game.ice) {
			if(Game.money >= 100) {
				Game.entities.add(curGun);
				Game.entities.remove(countGun);
				Game.money-=100;
			}
		}else if(Game.fire) {
			if(Game.money >= 200) {
				Game.entities.add(curGun);
				Game.entities.remove(countGun);
				Game.money-=200;
			}
		}else if(Game.storm) {
			if(Game.money >= 500) {
				Game.entities.add(curGun);
				Game.entities.remove(countGun);
				Game.money-=500;
			}
		}else if(Game.cannon) {
			if(Game.money >= 1000) {
				Game.entities.add(curGun);
				Game.entities.remove(countGun);
				Game.money-=1000;
			}
		}
	}

}
