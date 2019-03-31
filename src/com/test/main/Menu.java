package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.test.main.Game.STATE;

public class Menu extends MouseAdapter{
	private Game game;
	private Handler handler;
	private Random r = new Random();
	@SuppressWarnings("unused")
	private Player player;
	private HUD hud;
	private Spawn spawn;
	
	public Menu(Game game, Handler handler, HUD hud, Spawn spawn) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
		this.spawn = spawn;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Menu && mouseOver(mx, my, (Game.WIDTH / 2) - 140, (Game.HEIGHT / 2) - 64, 250, 64)){
			hud.setLevel(1);
			hud.score(0);
			spawn.setScoreKeep(0);
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		}
		
		if(game.gameState == STATE.Menu && mouseOver(mx, my, (Game.WIDTH / 2) - 140, (Game.HEIGHT / 2), 250, 64)){
				System.exit(1);
		}
		
		if(game.gameState == STATE.Menu && mouseOver(mx, my, (Game.WIDTH / 2) - 140, (Game.HEIGHT / 2) + 64, 250, 64)){
			game.gameState = STATE.Help;
		}
		
		if(game.gameState == STATE.Help && mouseOver(mx, my, 20, 20, 70, 30)){
			game.gameState = STATE.Menu;
		}
		
		if(game.gameState == STATE.Game && mouseOver(mx, my, 590 , 10, 25, 20)) {
			game.gameState = STATE.GameMenu;
			Game.paused = true;
		}
		
		if(game.gameState == STATE.GameMenu && mouseOver(mx, my, (Game.WIDTH / 2) - 140, (Game.HEIGHT / 2) - 64, 250, 64)){
			game.gameState = STATE.Game;
			Game.paused = false;
		}
		
		if(game.gameState == STATE.GameMenu && mouseOver(mx, my, (Game.WIDTH / 2) - 140, (Game.HEIGHT / 2), 250, 64)){
			game.gameState = STATE.Menu;
			Game.paused = false;
			HUD.HEALTH = 100;
			handler.endGame();
		}
		
		if(game.gameState == STATE.GameMenu && mouseOver(mx, my, (Game.WIDTH / 2) - 140, (Game.HEIGHT / 2) + 64, 250, 64)){
			game.gameState = STATE.Help;
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Menu", (Game.WIDTH / 2) - 85, (Game.HEIGHT / 2) - 124);
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawRect((Game.WIDTH / 2) - 140, (Game.HEIGHT / 2) - 64, 250, 64);
		g.drawString("Play", (Game.WIDTH / 2) - 52, (Game.HEIGHT / 2) - 22);
		
		g.setColor(Color.white);
		g.drawRect((Game.WIDTH / 2) - 140, (Game.HEIGHT / 2), 250, 64);
		g.drawString("Quit", (Game.WIDTH / 2) - 52, (Game.HEIGHT / 2) + 42);
		
		g.setColor(Color.white);
		g.drawRect((Game.WIDTH / 2) - 140, (Game.HEIGHT / 2) + 64, 250, 64);
		g.drawString("Help", (Game.WIDTH / 2) - 52, (Game.HEIGHT / 2) + 106);
	}
}
