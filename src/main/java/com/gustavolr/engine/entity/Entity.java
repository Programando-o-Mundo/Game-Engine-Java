package com.gustavolr.engine.entity;

import java.awt.Graphics;

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
}
