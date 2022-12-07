package com.gustavolr.engine.window;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.image.BufferStrategy;

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

    public static short getRealWindowWidth() {
        return (short)(width * (short)scale);
    }
    
    public static short getRealWindowHeight() {
        return (short)(height * (short)scale);
    }

    public static byte getWindowScale() {
        return scale;
    }

    public BufferedImage getBufferedImage() {
        return bufferLayer;
    }

    public GameWindow(String frameName) {
        this(GameWindowConstants.DEFAULT_WINDOW_WIDTH, 
             GameWindowConstants.DEFAULT_WINDOW_HEIGHT, 
             GameWindowConstants.DEFAULT_WINDOW_SCALE, 
             frameName);
    }

    public GameWindow(int width, int height, int scale, String frameName) {
        GameWindow.width = (short)width;
        System.out.println(height);
        GameWindow.height = (short)height;
        GameWindow.scale = (byte)scale;

        this.initWindowFrame(frameName);
    }

    public void initWindowFrame(String frameName) {

        windowFrame = new JFrame(frameName);

        windowFrame.setResizable(false);

        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setPreferredSize(new Dimension(width*scale,height*scale));
        windowFrame.add(this);
        windowFrame.pack(); // The pack method sizes the frame so that all its contents are at or above their preferred sizes

        // Centralize the window to the center of the user screen
        windowFrame.setLocationRelativeTo(null); 
        
        windowFrame.setVisible(true);

        bufferLayer = new BufferedImage((int)width,(int)height, BufferedImage.TYPE_INT_RGB);

        // Set the icon for the frame
		//Image icon = ImageIO.read(getClass().getResource("/icon.png"));
		//windowFrame.setIconImage(icon);
    }

    public Graphics getWindowLayer() {
        BufferStrategy bs = this.getBufferStrategy();
        if ( bs == null ) {
            this.createBufferStrategy(3);
            return null;
        }

        return bufferLayer.getGraphics();
    }

    public void drawBackground(Graphics g) {
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, GameWindow.getWindowWidth(), GameWindow.getWindowHeight());
    }

    public void drawFinalScene(Graphics g) {

        BufferStrategy bs = this.getBufferStrategy();

        g.dispose();
        g = bs.getDrawGraphics();

        g.drawImage(bufferLayer, 0, 0, width * scale, height * scale, null);
        
        bs.show();
    }
    
}
