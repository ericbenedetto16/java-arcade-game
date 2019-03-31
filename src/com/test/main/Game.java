package com.test.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 5348695609807625849L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	public static boolean paused = false;
	@SuppressWarnings("unused")
	private Random r;
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Help help;
	private GameMenu gameMenu;
	private End end;
	
	public enum STATE {
		Menu,
		Game,
		Quit,
		Help,
		GameMenu,
		End
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game(){
		handler = new Handler();
		hud = new HUD();
		spawner = new Spawn(handler, hud);
		menu = new Menu(this, handler, hud, spawner);
		help = new Help(this, handler);
		gameMenu = new GameMenu(this, handler);
		end = new End(this, hud, handler, spawner);
		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);
		this.addMouseListener(help);
		this.addMouseListener(gameMenu);
		this.addMouseListener(end);
		
		
		new Window(WIDTH, HEIGHT, "Arcade DodgeBall", this);
		r = new Random();
		
		/*if(gameState == STATE.Game) {
			handler.addObject(new Player(WIDTH/2-32, HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		}*/
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
				gameOver();
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() {
		if(gameState == STATE.Game) {
				if(!paused) {
				hud.tick();
				spawner.tick();
				handler.tick();
			}else if(gameState == STATE.Menu) {
				menu.tick();
			}else if(gameState == STATE.End) {
				end.tick();
			}
			
			if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				handler.endGame();
				gameState = STATE.End;
			}
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}else if(gameState == STATE.Menu) {
			menu.render(g);
		}else if(gameState == STATE.Help) {
			help.render(g);
		}else if(paused || gameState == STATE.GameMenu) {
			gameMenu.render(g);
		}else if(gameState == STATE.End) {
			end.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max)
			return (var = max);
		else if(var <= min)
			return (var = min);
		else
			return var;
	}
	
	public void gameOver() {
		//checkRunning();
	}
	
	public void checkRunning() {
		if(Handler.getGameEnded() == true) {
			running = false;
		}
	}

	public static void main(String[] args) {
		new Game();
	}

}
