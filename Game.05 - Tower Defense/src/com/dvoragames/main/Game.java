package com.dvoragames.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import com.dvoragames.entities.CannonGun;
import com.dvoragames.entities.Castle;
import com.dvoragames.entities.Entity;
import com.dvoragames.entities.FireGun;
import com.dvoragames.entities.Gun;
import com.dvoragames.entities.GunController;
import com.dvoragames.entities.IceGun;
import com.dvoragames.entities.StormGun;
import com.dvoragames.graficos.Spritesheet;
import com.dvoragames.graficos.UI;
import com.dvoragames.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 240;
	public static final int HEIGHT = 160;
	public static final int SCALE = 3;
	
	private BufferedImage image;

	public static World world;
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	public static GunController controller;
	
	public static Castle castle;
	
	public static boolean gun = true,ice,fire,storm,cannon;

	public UI ui;
	
	public static int money = 1000;
		
	public Game(){
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//Inicializando objetos.
		spritesheet = new Spritesheet("/spritesheet.png");
		entities = new ArrayList<Entity>();
		castle = new Castle(0,0,48,48,1, spritesheet.getSprite(80, 80, 48, 48));
		entities.add(castle);
		world = new World("/level1.png");
		ui = new UI();
		controller = new GunController(0,0,0,0,0, null);
	}
	
	public void initFrame(){
		frame = new JFrame("Tower Defense");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void tick(){
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}
		controller.tick();
		
		
		if(money < IceGun.priceI) {
			gun = true;
		}else if(money < FireGun.priceF) {
			ice = true;
		}else if(money < StormGun.priceS) {
			fire = true;
		}else if(money < CannonGun.priceC) {
			storm = true;
		}
	}
	


	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(122,102,255));
		g.fillRect(0, 0,WIDTH,HEIGHT);
		
		/*Renderização do jogo*/
		//Graphics2D g2 = (Graphics2D) g;
		world.render(g);
		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		ui.render(g);
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1 && money >= Gun.priceG) {
			gun = true;
			
			ice = false;
			fire = false;
			storm = false;
			cannon = false;
		}else if(e.getKeyCode() == KeyEvent.VK_2 || e.getKeyCode() == KeyEvent.VK_NUMPAD2 && money >= IceGun.priceI) {
			ice = true;
			
			gun = false;
			fire = false;
			storm = false;
			cannon = false;
		}else if(e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3 && money >= FireGun.priceF) {
			fire = true;
			
			gun = false;
			ice = false;
			storm = false;
			cannon = false;
		}else if(e.getKeyCode() == KeyEvent.VK_4 || e.getKeyCode() == KeyEvent.VK_NUMPAD4 && money >= StormGun.priceS) {
			storm = true;
			
			gun = false;
			ice = false;
			fire = false;
			cannon = false;
		}else if(e.getKeyCode() == KeyEvent.VK_5 || e.getKeyCode() == KeyEvent.VK_NUMPAD5 && money >= CannonGun.priceC) {
			cannon = true;
			
			gun = false;
			ice = false;
			fire = false;
			storm = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {	
		controller.isPressed = true;
		controller.xTarget = e.getX()/3;
		controller.yTarget = e.getY()/3;
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

	
}
