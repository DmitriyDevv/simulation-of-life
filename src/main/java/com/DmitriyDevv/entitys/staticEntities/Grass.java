package com.DmitriyDevv.entitys.staticEntities;

import com.DmitriyDevv.Coords;
import com.DmitriyDevv.entitys.Entity;
import com.DmitriyDevv.entitys.movingEntities.Attacked;

public class Grass extends Entity implements Attacked {
    private int HP = 5;

    public Grass(Coords coords) {
        super(coords);
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
