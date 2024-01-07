package com.DmitriyDevv.entitys.movingEntities;

import com.DmitriyDevv.Coords;
import com.DmitriyDevv.WorldMap;

public class Predator extends Creature {
    public Predator(WorldMap worldMap, Coords coords) {
        super(worldMap, Herbivore.class, new CreatureParameters(15, 5, 2), coords);
    }
}
