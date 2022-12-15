package com.gustavolr.game_entities;

import java.awt.Graphics;
import java.awt.Color;

import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;
import com.gustavolr.engine.window.GameWindow;

public class Enemy extends Entity {

    private int speed = 1;
    private Color color = new Color(196,30,58);

    public Enemy(Vector position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.position.x, this.position.y, this.width, this.height);
        super.update();
    }

    public void ballMoved(Vector ballPosition) {
        
        Vector direction = new Vector();

        if (ballPosition.y < position.y && position.y > 0) {
            direction.y -= speed;
            
        } else if (ballPosition.y > position.y && position.y < GameWindow.getWindowHeight() - this.height ) {
            direction.y += speed;
        }

        position = position.add(direction);
        
    }
    
}
