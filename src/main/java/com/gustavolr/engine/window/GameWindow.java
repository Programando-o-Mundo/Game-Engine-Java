package com.gustavolr.engine.window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public final class GameWindow extends Canvas{
    
    private static final long serialVersionUID = 1L;

    private static JFrame windowFrame;

    private static short width;
    private static short height;
    private static byte scale;

    public static BufferedImage bufferLayer;

    public static short getWindowWidth() {
        return width;
    }
    
    public static short getWindowHeight() {
        return height;
    }

    public static byte getWindowScale() {
        return scale;
    }

    public static Graphics getGraphicsLayer() {
        return bufferLayer.getGraphics();
    }

    public GameWindow(String frameName) {
        this(GameWindowConstants.DEFAULT_WINDOW_WIDTH, 
             GameWindowConstants.DEFAULT_WINDOW_HEIGHT, 
             GameWindowConstants.DEFAULT_WINDOW_SCALE, 
             frameName);
    }

    public GameWindow(int width, int height, int scale, String frameName) {
        GameWindow.width = (short)width;
        GameWindow.height = (short)height;
        GameWindow.scale = (byte)scale;

        this.initWindowFrame(frameName);
    }

    public void initWindowFrame(String frameName) {

        windowFrame = new JFrame(frameName);

        windowFrame.setResizable(false);

        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(height*scale,width*scale));
        windowFrame.add(this);
        windowFrame.pack(); // The pack method sizes the frame so that all its contents are at or above their preferred sizes

        // Centralize the window to the center of the user screen
        windowFrame.setLocationRelativeTo(null); 
        
        windowFrame.setVisible(true);

        // Set the icon for the frame
		//Image icon = ImageIO.read(getClass().getResource("/icon.png"));
		//windowFrame.setIconImage(icon);
    }

    
}
