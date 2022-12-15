package com.gustavolr.game_scenes;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;
import com.gustavolr.engine.scene.Scene;
import com.gustavolr.engine.ui.Text.Text;
import com.gustavolr.engine.window.GameWindow;
import com.gustavolr.game_entities.*;

import static com.gustavolr.game_scenes.MainSceneConstants.ENEMY_START_POSITION;
import static com.gustavolr.game_scenes.MainSceneConstants.PLAYER_START_POSITION;
import static com.gustavolr.game_scenes.MainSceneConstants.BALL_START_POSITION;

public final class MainScene implements Scene {

    private Player p;
    private Enemy e;
    private Ball b;

    private Text enemyScoreText;
    private Text playerScoreText;

    private int enemyScore;
    private int playerScore;

    private final List<Entity> entities;
    
    public MainScene() {
        p = new Player(PLAYER_START_POSITION.clone(), 5, 25);
        e = new Enemy(ENEMY_START_POSITION.clone(), 5, 25);
        b = new Ball(BALL_START_POSITION.clone(),7,7);

        this.enemyScore = 0;
        int enemyScorePositionX = (int)(GameWindow.getWindowWidth() - (GameWindow.getWindowWidth()*0.15));
        int enemyScorePositionY = (int)(GameWindow.getWindowWidth()*0.1);
        enemyScoreText = new Text(new Vector(enemyScorePositionX,enemyScorePositionY), "0");

        this.playerScore = 0;
        int playerScorePositionX = (int)(GameWindow.getWindowWidth() - (GameWindow.getWindowWidth()*0.87));
        int playerScorePositionY = (int)(GameWindow.getWindowWidth()*0.1);
        playerScoreText = new Text(new Vector(playerScorePositionX,playerScorePositionY), "0");

        entities = new ArrayList<>();
        entities.add(p);
        entities.add(e);
        entities.add(b);
        entities.add(enemyScoreText);
        entities.add(playerScoreText);
    }

    @Override
    public void update() {
        for( Entity e : entities) {
            e.update();
        }

        e.ballMoved(b.getPosition());

        checkIfBallCollidedWithPaddles();

        checkForScoring();
    }   

    public void checkIfBallCollidedWithPaddles() {
        if (b.isColidding(e)) {
            b.invertDX();
        } else if(b.isColidding(p)) {
            b.invertDX();
        }
    }

    public void checkForScoring() {
        Vector ballPosition = b.getPosition();

        if (enemyScored(ballPosition.x)) {
            enemyScore += 1;
            enemyScoreText.setLabel(Integer.toString(enemyScore));
            updateBallPosition();

        } else if(playerScored(ballPosition.x)) {
            playerScore += 1;
            playerScoreText.setLabel(Integer.toString(playerScore));
            updateBallPosition();
        }
    }

    public boolean playerScored(int ball_x) {
        return ball_x > GameWindow.getWindowWidth();
    }

    public boolean enemyScored(int ball_x) {
        return ball_x < 0;
    } 

    public void updateBallPosition() {
        b.setPosition(BALL_START_POSITION.clone());
        b.setSpeed(1);
        b.invertDX();
    }

    @Override
    public void render(Graphics g) {

        drawNet(g, 
                GameWindow.getWindowWidth()/2, 
                5, GameWindow.getWindowWidth()/2, 
                GameWindow.getWindowHeight()+5, 
                5);

        for( Entity e : entities) {
            e.render(g);
        }
    }

    public static void drawNet(Graphics g, int x0, int y0, int x1, int y1, int interval) {
        g.setColor(Color.WHITE);
        if (y0 == y1) {
            for (int i = x0; i < x1; i += interval) {
                g.drawLine(i, y0, i, y1);
            }
        } else {
            for (int i = y0; i < y1; i += interval) {
                g.drawLine(x0, i, x1, i);
            }
        }
    }
}
