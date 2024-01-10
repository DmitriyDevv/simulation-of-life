package com.DmitriyDevv.actions;

import com.DmitriyDevv.WorldMap;
import com.DmitriyDevv.entitys.Entity;
import com.DmitriyDevv.entitys.movingEntities.Creature;
import com.DmitriyDevv.entitys.staticEntities.Grass;
import com.DmitriyDevv.utils.workingWithNumbers.RandomNumberGenerator;

import java.util.Map;

public class IncreaseNumberEntities extends Action {
    @Override
    public void make(WorldMap worldMap) {
        int thresholdEntities = 1;
        for (Map.Entry<Class<? extends Entity>, Integer> entityCounts :
                worldMap.getEntityCounts().entrySet()) {
            Class<? extends Entity> classEntity = entityCounts.getKey();
            Integer numberEntities = entityCounts.getValue();

            if (Creature.class.isAssignableFrom(classEntity) || classEntity.equals(Grass.class)) {
                if (numberEntities <= thresholdEntities) {
                    int randomNumberCreature = RandomNumberGenerator.getRandomNumber(1, 10);
                    worldMap.initFixedNumberOfEntities(classEntity, randomNumberCreature);
                }
            }
        }
    }
}
