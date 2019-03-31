package com.test.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

public class Player extends GameObject{
	Random r = new Random();
	Handler handler;
	Game game;
	HUD hud;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	public void getXLocation() {
		getX();
	}
	
	public void getYLocation() {
		getY();
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-47);
		y = Game.clamp(y, 0, Game.HEIGHT-70);

			collision();
	}

	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
		
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy || tempObject.getId() == ID.EnemyBoss || tempObject.getId() == ID.BossDamageZone) {
				if(getBounds().intersects(tempObject.getBounds())) {
					//Collision code
					HUD.HEALTH-=2;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2d= (Graphics2D) g;
		g.setColor(Color.yellow);
		g2d.draw(getBounds());
	}
}
