package com.gustavolr.engine;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.gustavolr.engine.game_loop.GameLoop;
import com.gustavolr.engine.game_loop.GameLoopListener;
import com.gustavolr.engine.input.GameInput;
import com.gustavolr.engine.window.GameWindow;

public final class GameEngine implements GameLoopListener{
    
    private final GameWindow window;
    private final GameInput input;

    int x = 0;
    int y = 0;

    public GameEngine() {

        this.input = new GameInput();

        this.window = new GameWindow("Pong");
        this.window.addKeyListener(input);
        new GameLoop(this);
    }   

    @Override
    public void update() {

        if (GameInput.isKeyPressed(KeyEvent.VK_LEFT)) {
            x--;
        }
        else if (GameInput.isKeyPressed(KeyEvent.VK_RIGHT)) {
            x++;
        }

        if (GameInput.isKeyPressed(KeyEvent.VK_UP)) {
            y--;
        } else if (GameInput.isKeyPressed(KeyEvent.VK_DOWN)) {
            y++;
        }


        System.out.println(x);
    }

    @Override
    public void render() {

        Graphics g = this.window.getWindowLayer();
        if ( g == null ) 
            return;
        
        this.window.drawBackground(g);
        
        // Game Rendering goes here

        g.setColor(Color.GREEN);
        g.fillRect(x, y, 50, 50);

        // Game Rendering stops here

        this.window.drawFinalScene(g);
    }
}
