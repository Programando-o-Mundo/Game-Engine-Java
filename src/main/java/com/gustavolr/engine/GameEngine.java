package com.gustavolr.engine;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import com.gustavolr.engine.game_loop.GameLoop;
import com.gustavolr.engine.game_loop.GameLoopListener;
import com.gustavolr.engine.input.GameInput;
import com.gustavolr.engine.window.GameWindow;

public final class GameEngine implements GameLoopListener{
    
    private final GameWindow window;
    private final GameInput input;
    private final GameLoop gameLoop;

    public GameEngine() {

        this.input = new GameInput();

        this.window = new GameWindow("Pong");
        this.window.addKeyListener(input);
        this.gameLoop = new GameLoop(this);
    }   

    @Override
    public void update() {

        if (GameInput.isKeyPressed(KeyEvent.VK_LEFT)) {
            System.out.println("Ola");
        }
    }

    @Override
    public void render() {

        BufferStrategy bs = this.window.getBufferStrategy();
        if ( bs == null ) {
            this.window.createBufferStrategy(3);
            return;
        }

        Graphics layer = this.window.getGraphics();

        layer.dispose();
        layer = bs.getDrawGraphics();

        layer.fillRect(50, 50, 100, 100);
        
        bs.show();
    }
}
