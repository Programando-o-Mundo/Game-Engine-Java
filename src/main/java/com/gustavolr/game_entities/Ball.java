package com.gustavolr.game_entities;

import java.awt.Graphics;
import java.awt.Color;

import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;
import com.gustavolr.engine.window.GameWindow;

public class Ball extends Entity {

    private int speed;
    private Color color = Color.WHITE;

    float dx = 1;
    float dy = 1;

    public Ball(Vector position, int width, int height) {
        super(position, width, height);
        speed = 1;
    }

    public void invertDX() {
        dx *= -1;
    }

    @Override
    public void update() {

        position.x += dx * speed;
        position.y += dy * speed;

        if(position.y < 0 || position.y > GameWindow.getWindowHeight())
            dy *= -1;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(position.x, position.y, width, height);
    }
    
}
