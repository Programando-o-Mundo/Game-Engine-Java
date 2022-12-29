package com.gustavolr.game_scenes;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.gustavolr.engine.entity.Entity;
import com.gustavolr.engine.entity.Vector;
import com.gustavolr.engine.scene.Scene;
import com.gustavolr.engine.sound.SoundPlayer;
import com.gustavolr.engine.ui.Text.Text;
import com.gustavolr.engine.window.GameWindow;
import com.gustavolr.game_entities.*;
import com.gustavolr.utils.OsUtils;

import static com.gustavolr.game_scenes.MainSceneConstants.ENEMY_START_POSITION;
import static com.gustavolr.game_scenes.MainSceneConstants.PLAYER_START_POSITION;
import static com.gustavolr.game_scenes.MainSceneConstants.BALL_START_POSITION;

public final class MainScene implements Scene {

    private Player p;
    private Enemy e;
    private Ball b;

    private Text enemyScoreText;
    private Text playerScoreText;
    
    private Text whoWon;

    private int enemyScore;
    private int playerScore;

    private final List<Entity> entities;

    private final static SoundPlayer scoreSFX = new SoundPlayer(OsUtils.getResourceAsInputStream(OsUtils.join("sfx","score.wav")));

    private final static int MAX_SCORE = 3;
    
    public MainScene() {

        this.initScores();

        entities = new ArrayList<>();
        this.initEntities();
    }

    public void initScores() {

        this.enemyScore = 0;
        int enemyScorePositionX = (int)(GameWindow.getWindowWidth() - (GameWindow.getWindowWidth()*0.15));
        int enemyScorePositionY = (int)(GameWindow.getWindowWidth()*0.1);
        enemyScoreText = new Text(new Vector(enemyScorePositionX,enemyScorePositionY), "0");

        this.playerScore = 0;
        int playerScorePositionX = (int)(GameWindow.getWindowWidth() - (GameWindow.getWindowWidth()*0.87));
        int playerScorePositionY = (int)(GameWindow.getWindowWidth()*0.1);
        playerScoreText = new Text(new Vector(playerScorePositionX,playerScorePositionY), "0");
    }

    public void initEntities() {

        p = new Player(PLAYER_START_POSITION.clone(), 4, 35);
        e = new Enemy(ENEMY_START_POSITION.clone(), 4, 35);
        b = new Ball(BALL_START_POSITION.clone(),7,7);

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

        endGame();
    }   

    public void endGame() {

        boolean gameEnded = false;
        Vector winnerPositionScore = null;
        String text = null;

        if (enemyScore == MAX_SCORE || playerScore == MAX_SCORE) {

            gameEnded = true;

            if (enemyScore == MAX_SCORE) {
                winnerPositionScore = enemyScoreText.getPosition().clone();
                winnerPositionScore.x -= 50;
                text = "You Lose";
                whoWon = new Text(winnerPositionScore, text);
                whoWon.setTextColor(Color.RED);
                

            } else { 
                winnerPositionScore = playerScoreText.getPosition().clone();
                winnerPositionScore.x -= 15;
                text = "You Win";
                whoWon = new Text(winnerPositionScore, text);
                whoWon.setTextColor(Color.GREEN);
            }
        }
    
        if (gameEnded) {

            
            entities.clear();
            
            entities.add(whoWon);
        }
    }

    public void checkIfBallCollidedWithPaddles() {

        Entity collidedEntity = null;

        if(b.isColidding(e)) {
            collidedEntity = e;
        } else if (b.isColidding(p)) {
            collidedEntity = p;
        }

        if (collidedEntity != null)
            b.calculateAngleAfterColisionWith(collidedEntity);
        
    }

    public void checkForScoring() {
        Vector ballPosition = b.getPosition();
        int width = b.getWidth();

        if (enemyScored((int)ballPosition.x, width)) {
            enemyScore += 1;
            enemyScoreText.setLabel(Integer.toString(enemyScore));
            updateBallPosition(e);
            scoreSFX.play();

        } else if(playerScored((int)ballPosition.x)) {
            playerScore += 1;
            playerScoreText.setLabel(Integer.toString(playerScore));
            updateBallPosition(p);
            scoreSFX.play();
        }
    }

    public boolean playerScored(int ball_x) {
        return ball_x > GameWindow.getWindowWidth();
    }

    public boolean enemyScored(int ball_x, int ball_width) {
        return ball_x < -ball_width;
    } 

    public void updateBallPosition(Entity whoScored) {

        b.speed = 2;

        b.setPosition(BALL_START_POSITION.clone());
        b.dx = (whoScored instanceof Player ? 1 : -1) * b.speed;
        b.dy = 0;
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
