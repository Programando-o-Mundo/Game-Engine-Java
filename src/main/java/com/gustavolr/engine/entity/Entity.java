package com.gustavolr.engine.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    
    protected Vector position;
    protected int width;
    protected int height;

    public Entity(Vector position, int width, int height) {
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Vector getPosition() {
        return position;
    }
    public void setPosition(Vector position) {
        this.position = position;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public void update() {
    }

    public void render(Graphics g) {

    }

	public boolean isColidding(Entity e1) {   


		Rectangle e1Mask = new Rectangle((int)e1.position.x, (int)e1.position.y, e1.width, e1.height);
		Rectangle e2Mask = new Rectangle((int)this.position.x, (int)this.position.y, this.width, this.height);
		
        boolean response = e1Mask.intersects(e2Mask);

		return response;
	}
}
