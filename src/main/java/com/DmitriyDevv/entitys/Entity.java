package com.DmitriyDevv.entitys;

import com.DmitriyDevv.Coords;

public abstract class Entity {
    private final Coords coords;

    public Entity(Coords coords) {
        this.coords = coords;
    }

    public int getX() {
        return coords.getX();
    }

    public void setX(int x) {
        this.coords.setX(x);
    }

    public int getY() {
        return coords.getY();
    }

    public void setY(int y) {
        this.coords.setY(y);
    }
}
