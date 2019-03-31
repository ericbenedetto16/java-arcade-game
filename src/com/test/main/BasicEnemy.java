package com.test.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	private Handler handler;
	@SuppressWarnings("unused")
	private Game game;
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;

		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 16, 16);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(x <= 0 || x >= Game.WIDTH-32) velX *= -1;
		if(y <= 0 || y >= Game.HEIGHT-32) velY *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.red, 16, 16, 0.02f, handler));
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, 16, 16);
	}

}
