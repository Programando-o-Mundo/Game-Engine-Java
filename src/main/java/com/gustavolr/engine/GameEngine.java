package com.gustavolr.engine;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.gustavolr.engine.game_loop.GameLoop;
import com.gustavolr.engine.game_loop.GameLoopListener;
import com.gustavolr.engine.input.GameInput;
import com.gustavolr.engine.scene.Scene;
import com.gustavolr.engine.window.GameWindow;

public final class GameEngine implements GameLoopListener{
    
    private final GameWindow window;
    private final GameInput input;

    private final List<Scene> scene_manager;

    public GameEngine() {

        this.scene_manager = new ArrayList<>();

        this.input = new GameInput();

        this.window = new GameWindow("Pong");
        this.window.addKeyListener(input);
    }   

    public void addScene(Scene scene) {
        this.scene_manager.add(scene);
    }

    public void start() {
        this.window.toggleFrameVisibility(true);
        this.window.requestFocus();
        new GameLoop(this);
    }

    @Override
    public void update() {

        for(Scene scene : scene_manager) {
            scene.update();
        }
    }

    @Override
    public void render() {

        Graphics g = this.window.getWindowLayer();
        if ( g == null ) 
            return;
        
        this.window.drawBackground(g);
        
        // Game Rendering goes here

        for(Scene scene : scene_manager) {
            scene.render(g);
        }

        // Game Rendering stops here

        this.window.drawFinalScene(g);
    }
}
