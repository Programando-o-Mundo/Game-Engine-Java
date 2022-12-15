package com.gustavolr.engine.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {
    
    protected Vector position;
    protected int width;
    protected int height;
    int collision_timer = 0;
    int max_timer = 60;

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
        if(collision_timer < max_timer) {
            collision_timer++;
        }
        System.out.println("collision " + collision_timer);
    }

    public void render(Graphics g) {

    }

	public boolean isColidding(Entity e1) {   
        if (collision_timer < max_timer) {
            return false;
        }

		Rectangle e1Mask = new Rectangle(e1.position.x, e1.position.y, e1.width, e1.height);
		Rectangle e2Mask = new Rectangle(this.position.x, this.position.y, this.width, this.height);
		
        boolean response = e1Mask.intersects(e2Mask);
        if(response)
            collision_timer = 0;

		return response;
	}
}
