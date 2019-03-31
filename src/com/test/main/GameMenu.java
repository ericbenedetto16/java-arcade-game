package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class GameMenu extends MouseAdapter {
	@SuppressWarnings("unused")
	private Handler handler;
	@SuppressWarnings("unused")
	private Game game;
	
	public GameMenu(Game game, Handler handler) {
		this.game = game;
		this.handler = handler;
	}
	
	public void tick() {

	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("Pause", (Game.WIDTH / 2) - 87, (Game.HEIGHT / 2) - 124);
		
		g.setFont(fnt2);
		g.setColor(Color.white);
		g.drawRect((Game.WIDTH / 2) - 140, (Game.HEIGHT / 2) - 64, 250, 64);
		g.drawString("Resume", (Game.WIDTH / 2) - 78, (Game.HEIGHT / 2) - 22);
		
		g.setColor(Color.white);
		g.drawRect((Game.WIDTH / 2) - 140, (Game.HEIGHT / 2), 250, 64);
		g.drawString("Quit", (Game.WIDTH / 2) - 52, (Game.HEIGHT / 2) + 42);
		
		g.setColor(Color.white);
		g.drawRect((Game.WIDTH / 2) - 140, (Game.HEIGHT / 2) + 64, 250, 64);
		g.drawString("Help", (Game.WIDTH / 2) - 52, (Game.HEIGHT / 2) + 106);
	}
}
