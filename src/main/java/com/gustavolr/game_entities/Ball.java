package com.gustavolr.game_entities;

import java.awt.Graphics;
import java.awt.Color;

import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;
import com.gustavolr.listeners.BallListener;

public class Ball extends Entity {

    private int speed;
    private Color color;
    private BallListener listener;

    float dx = 1;
    float dy = 1;

    public Ball(Vector position, int width, int height) {
        super(position, width, height);
        speed = 1;
    }

    public void addListener(BallListener b) {
        this.listener = b;
    }

    @Override
    public void update() {

        position.x += dx * speed;
        position.y += dy * speed;

        if (listener != null) {
            this.listener.ballMoved(position);
        }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(position.x, position.y, width, height);
    }
    
}
