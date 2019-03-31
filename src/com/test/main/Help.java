package com.test.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;

public class Help extends MouseAdapter{

	@SuppressWarnings("unused")
	private Handler handler;
	@SuppressWarnings("unused")
	private Game game;
	
	public Help(Game game, Handler handler) {
		
		this.handler = handler;
		this.game = game;
		
		
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("Arial", 1, 30);
		Font fnt2 = new Font("Arial", 1, 20);
		
		g.setFont(fnt);
		g.setColor(Color.white);
		g.drawString("To Control the Character", 40, 100);
		g.drawString("Use WASD or the Arrow Keys", 40, 150);
		g.drawString("to move the character.", 40, 200);
		g.drawString("Your main objective is to avoid the other", 40, 250);
		g.drawString("objects in the game.", 40, 300);
		
		g.setColor(Color.red);
		g.drawRect(20, 20, 70, 30);
		g.setFont(fnt2);
		g.drawString("Back", 33, 43);
	}

}
