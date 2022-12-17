package com.gustavolr.game_entities;

import java.awt.Graphics;
import java.awt.Color;

import com.gustavolr.engine.GameEngine;
import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;
import com.gustavolr.engine.sound.SoundPlayer;
import com.gustavolr.engine.window.GameWindow;
import com.gustavolr.utils.OsUtils;

public class Ball extends Entity {

    private Color color = Color.WHITE;

    public double dx,dy,speed;

    private final static SoundPlayer wallSFX = new SoundPlayer(OsUtils.getResource(OsUtils.join("sfx","wall.wav")).getFile());
    private final static SoundPlayer paddleSFX = new SoundPlayer(OsUtils.getResource(OsUtils.join("sfx","paddle.wav")).getFile());

    public Ball(Vector position, int width, int height) {
        super(position, width, height);
        speed = 2;
        dx = (GameEngine.rng.nextInt(1) == 1 ? -1 : 1) * speed;
        dy = 0;
    }

    @Override
    public void update() {

        if((position.y <= 0 && dy < 0) || position.y >= GameWindow.getWindowHeight() - height) {
            dy *= -1;
            wallSFX.play();
        }

        position.x += dx;
        position.y += dy;

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

        speed += 0.1;
        paddleSFX.play();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval((int)position.x, (int)position.y, width, height);
    }
    
}
