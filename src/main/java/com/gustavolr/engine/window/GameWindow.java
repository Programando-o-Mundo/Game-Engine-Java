package com.gustavolr.engine.window;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.image.BufferedImage;
import java.awt.image.BufferStrategy;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Graphics;

public final class GameWindow extends Frame implements WindowListener{

    private static short width;
    private static short height;
    private static byte scale;

    public static BufferedImage bufferLayer;

    public boolean playerPressXtoQuit;

    public GameWindow(String frameName) {
        this(GameWindowConstants.DEFAULT_WINDOW_WIDTH, 
             GameWindowConstants.DEFAULT_WINDOW_HEIGHT, 
             GameWindowConstants.DEFAULT_WINDOW_SCALE, 
             frameName);
    }

    public GameWindow(int width, int height, int scale, String frameName) {
        super(frameName);
        GameWindow.width = (short)width;
        GameWindow.height = (short)height;
        GameWindow.scale = (byte)scale;

        playerPressXtoQuit = false;

        this.initWindowFrame(frameName);
    }

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

    public void initWindowFrame(String frameName) {

        setResizable(false);

        setPreferredSize(new Dimension(width*scale,height*scale));

        pack(); // The pack method sizes the frame so that all its contents are at or above their preferred sizes
        addWindowListener(this);
        // Centralize the window to the center of the user screen
        setLocationRelativeTo(null); 
        setLayout(null);

        bufferLayer = new BufferedImage((int)width,(int)height, BufferedImage.TYPE_INT_RGB);

        // Set the icon for the frame
		//Image icon = ImageIO.read(getClass().getResource("/icon.png"));
		//windowFrame.setIconImage(icon);
    }

    public void toggleFrameVisibility(boolean visibility) {
        this.setVisible(visibility);
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
        
        g.setColor(GameWindowConstants.BACKGROUND_COLOR);
        g.fillRect(0, 0, GameWindow.getWindowWidth(), GameWindow.getWindowHeight());
    }

    public void drawFinalScene(Graphics g) {

        BufferStrategy bs = this.getBufferStrategy();

        g.dispose();

        try {
            g = bs.getDrawGraphics();

            g.drawImage(bufferLayer, 5, 29, width * scale, height * scale, null);
        
            bs.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void windowActivated(WindowEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowClosed(WindowEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowClosing(WindowEvent arg0) {
        setVisible(false);
        dispose();
        System.exit(0);
    }

    @Override
    public void windowDeactivated(WindowEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowDeiconified(WindowEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowIconified(WindowEvent arg0) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void windowOpened(WindowEvent arg0) {
        // TODO Auto-generated method stub
        
    }
}
