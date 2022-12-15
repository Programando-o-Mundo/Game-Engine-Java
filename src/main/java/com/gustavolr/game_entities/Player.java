package com.gustavolr.game_entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Color;

import com.gustavolr.engine.input.GameInput;
import com.gustavolr.engine.window.GameWindow;
import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;

public class Player extends Entity {

    private int speed = 1;
    private Color color = new Color(65,105,225);

    public Player(Vector position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void update() {

        Vector direction = new Vector();

        if (GameInput.isKeyPressed(KeyEvent.VK_UP) && position.y > 0) {
            direction.y -= speed;
        } 
        else if (GameInput.isKeyPressed(KeyEvent.VK_DOWN) && position.y < GameWindow.getWindowHeight() - this.height) {
            direction.y += speed;
        }

        this.position = this.position.add(direction);
        super.update();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.position.x, this.position.y, this.width, this.height);
    }
    
}
