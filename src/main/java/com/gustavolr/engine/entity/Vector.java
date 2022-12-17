package com.gustavolr.engine.entity;

public class Vector {
    
    public float x;
    public float y;

    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector v) {
        Vector tmp = new Vector(this.x, this.y);
        
        tmp.x += v.x;
        tmp.y += v.y;

        return tmp;
    }

    public Vector clone() {
        return new Vector(x,y);
    }

    @Override
    public String toString() {
        return "Vector(x:" + x + ", y:" + y + ");";
    }

}
