package com.DmitriyDevv.actions;

import com.DmitriyDevv.WorldMap;
import com.DmitriyDevv.entitys.Entity;
import com.DmitriyDevv.entitys.movingEntities.Creature;

import java.util.ArrayList;
import java.util.List;

public class MoveCreatures extends Action {
    @Override
    public void make(WorldMap worldMap) {
        List<Entity> whoMadeStep = new ArrayList<>();
        for (int y = 0; y <= worldMap.getHEIGHT(); y++) {
            for (int x = 0; x <= worldMap.getWIDTH(); x++) {
                if (worldMap.containsEntity(x, y)) {
                    Entity entity = worldMap.getEntity(x, y);
                    if (entity instanceof Creature creature) {
                        if (!whoMadeStep.contains(entity)) {
                            creature.move();
                            whoMadeStep.add(entity);
                        }
                    }
                }
            }
        }
        whoMadeStep.clear();
    }
}
