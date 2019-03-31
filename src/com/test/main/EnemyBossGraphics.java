package com.test.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyBossGraphics extends GameObject{
	
	public EnemyBossGraphics(float x, float y, ID id) {
		super(x, y, id);
	
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, Game.WIDTH, 20);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, Game.WIDTH, 70);
	}
}
