package com.gustavolr.game_entities;

import java.awt.Graphics;
import java.awt.Color;

import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;
import com.gustavolr.engine.window.GameWindow;

public class Ball extends Entity {

    private Color color = Color.WHITE;

    public double dx,dy,speed;

    public Ball(Vector position, int width, int height) {
        super(position, width, height);
        speed = 3;
        dx = 1;
        dy = 1;
    }

    @Override
    public void update() {

        if(position.y < 0 || position.y > GameWindow.getWindowHeight() - this.height)
            dy *= -1;

        position.x += dx;
        position.y += dy;
        super.update();
    }

    public void calculateAngleAfterColisionWith(Entity paddle) {
        double relativeInsersectY = (paddle.getPosition().y + (paddle.getHeight()/2.0)) - (this.getPosition().y + (this.getHeight() / 2.0));
        double normalIntersectY = relativeInsersectY / (paddle.getHeight() / 2.0);
        double theta = Math.toRadians(normalIntersectY * 45);

        double newDx = Math.abs(Math.cos(theta)) * this.speed;
        double newDy = (-Math.sin(theta)) * this.speed;

        double oldSign = Math.signum(this.dx);
        this.dx = newDx * (-1.0 * oldSign);
        this.dy = newDy;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(position.x, position.y, width, height);
    }
    
}
