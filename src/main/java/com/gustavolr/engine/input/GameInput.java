package com.gustavolr.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public final class GameInput implements KeyListener {

    private static final HashMap<Integer, Boolean> inputMap = new HashMap<>();

    public static final Boolean isKeyPressed(int key) {
        Boolean resp = false;

        if (!inputMap.containsKey(key)) 
            return resp;

        return inputMap.get(key);
    }

    @Override
    public void keyPressed(KeyEvent k) {
        
        if (inputMap.containsKey(k.getKeyCode())) 
            inputMap.replace(k.getKeyCode(), GameInputConstants.KEY_PRESSED_VALUE);

        else 
            inputMap.put(k.getKeyCode(), GameInputConstants.KEY_PRESSED_VALUE);
    }

    @Override
    public void keyReleased(KeyEvent k) {

        if (inputMap.containsKey(k.getKeyCode())) 
            inputMap.replace(k.getKeyCode(), GameInputConstants.KEY_RELEASED_VALUE);

        else 
            inputMap.put(k.getKeyCode(), GameInputConstants.KEY_RELEASED_VALUE);
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // TODO Auto-generated method stub
        System.out.println(arg0);
    }
    
}
