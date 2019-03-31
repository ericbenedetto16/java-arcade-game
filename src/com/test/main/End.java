package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import com.test.main.Game.STATE;

public class End extends MouseAdapter{
	Game game;
	Handler handler;
	HUD hud;
	Random r = new Random();
	Spawn spawn;
	
	public End(Game game, HUD hud, Handler handler, Spawn spawn) {
		this.handler = handler;
		this.game = game;
		this.hud = hud;
		this.spawn = spawn;
	}
	
	public void mousePressed(MouseEvent e) {
		if(game.gameState == STATE.End) {
			hud.setLevel(1);
			hud.score(0);
			spawn.setScoreKeep(0);
			game.gameState = STATE.Game;
			handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("Arial", 1, 16);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("You died with a score of: " + hud.getScore(), (Game.WIDTH / 2) - 120, (Game.HEIGHT / 2) - 55);
		g.drawString("Click Anywhere to Play Again", (Game.WIDTH / 2) - 122, (Game.HEIGHT / 2) - 32);
	}

	public void tick() {
		
	}
}
