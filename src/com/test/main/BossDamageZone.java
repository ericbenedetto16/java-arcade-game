package com.test.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public class BossDamageZone extends GameObject {
	
	@SuppressWarnings("unused")
	private Handler handler;

	public BossDamageZone(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, Game.WIDTH, 50);
	}

	public void tick() {

	}

	public void render(Graphics g) {
	
	}
}
