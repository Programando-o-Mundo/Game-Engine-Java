package com.gustavolr.engine.entity;

public class Vector {
    
    public int x;
    public int y;

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector v) {
        Vector tmp = new Vector(this.x, this.y);
        
        tmp.x += v.x;
        tmp.y += v.y;

        return tmp;
    }

}
