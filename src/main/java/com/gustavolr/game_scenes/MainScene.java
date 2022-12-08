package com.gustavolr.game_scenes;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;
import com.gustavolr.engine.scene.Scene;
import com.gustavolr.engine.window.GameWindow;
import com.gustavolr.game_entities.*;

public class MainScene implements Scene {

    private Player p;
    private Enemy e;
    private Ball b;

    private final List<Entity> entities;
    
    public MainScene() {
        p = new Player(new Vector(), 10, 50);
        e = new Enemy(new Vector(GameWindow.getWindowWidth()-10,0), 10, 50);
        b = new Ball(new Vector(GameWindow.getWindowWidth()/2-3,GameWindow.getWindowHeight()/2-3),7,7);
        b.addListener(e);

        entities = new ArrayList<>();
        entities.add(p);
        entities.add(e);
        entities.add(b);
    }

    @Override
    public void update() {
        for( Entity e : entities) {
            e.update();
        }

        if (b.isColidding(e)) {
            b.ballCollidedWithPaddle();
        } else if(b.isColidding(p)) {
            b.ballCollidedWithPaddle();
        }
    }   

    @Override
    public void render(Graphics g) {
        for( Entity e : entities) {
            e.render(g);
        }
    }
}
