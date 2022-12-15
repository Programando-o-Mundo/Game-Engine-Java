package com.gustavolr.game_scenes;

import com.gustavolr.engine.entity.Vector;
import com.gustavolr.engine.window.GameWindow;

public final class MainSceneConstants {
    
    public static final Vector PLAYER_START_POSITION = new Vector(5,GameWindow.getWindowHeight()/2);
    public static final Vector ENEMY_START_POSITION = new Vector(GameWindow.getWindowWidth()-10,0);
    public static final Vector BALL_START_POSITION = new Vector(GameWindow.getWindowWidth()/2-3,GameWindow.getWindowHeight()/2-3);
}
