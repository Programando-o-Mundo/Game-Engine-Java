package com.gustavolr;

import com.gustavolr.engine.GameEngine;
import com.gustavolr.game_scenes.MainScene;

public final class Main  {
    
    public static void main( String[] args ) {
        GameEngine gameEngine = new GameEngine();
        
        MainScene ms = new MainScene();
        gameEngine.addScene(ms);

        gameEngine.start();
    }

}
