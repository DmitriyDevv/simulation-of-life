package com.DmitriyDevv.entitys.movingEntities;

import com.DmitriyDevv.Coords;
import com.DmitriyDevv.WorldMap;
import com.DmitriyDevv.entitys.staticEntities.Grass;

public class Herbivore extends Creature implements Attacked {
    public Herbivore(WorldMap worldMap, Coords coords) {
        super(worldMap, Grass.class, new CreatureParameters(10, 2, 1), coords);
    }

    @Override
    public int getHP() {
        return creatureParameters.getHP();
    }

    @Override
    public void setHP(int hp) {
        creatureParameters.setHP(hp);
    }
}
