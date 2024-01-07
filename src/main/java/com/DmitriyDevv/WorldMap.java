package com.DmitriyDevv;

import com.DmitriyDevv.entitys.Entity;
import com.DmitriyDevv.entitys.movingEntities.Herbivore;
import com.DmitriyDevv.entitys.movingEntities.Predator;
import com.DmitriyDevv.entitys.staticEntities.Grass;
import com.DmitriyDevv.entitys.staticEntities.Rock;
import com.DmitriyDevv.entitys.staticEntities.Tree;
import com.DmitriyDevv.utils.workingWithNumbers.RandomNumberGenerator;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {
    private final int HEIGHT = 20;
    private final int WIDTH = (int) (HEIGHT * 1.5);
    private final Map<Coords, Entity> worldMap = new HashMap<>();
    private final Map<Class<? extends Entity>, Integer> thresholdsEntities = new HashMap<>();
    private final Map<Class<? extends Entity>, Integer> entityCounts = new HashMap<>();

    public WorldMap() {
        initNumbersEntity();
        initThresholdEntity();
    }

    public Map<Class<? extends Entity>, Integer> getEntityCounts() {
        return entityCounts;
    }

    public boolean containsEntity(int x, int y) {
        return worldMap.containsKey(new Coords(x, y));
    }

    public void initEntities() {
        initEntities(Herbivore.class);
        initEntities(Predator.class);
        initEntities(Grass.class);
        initEntities(Tree.class);
        initEntities(Rock.class);
    }

    public Entity getEntity(int x, int y) {
        return worldMap.get(new Coords(x, y));
    }

    public Coords getCoordsCreature(Entity entity) {
        for (Map.Entry<Coords, Entity> worldMap : worldMap.entrySet()) {
            if (worldMap.getValue().equals(entity)) {
                return worldMap.getKey();
            }
        }
        return null;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void putEntity(int x, int y, Entity entity) {
        worldMap.put(new Coords(x, y), entity);
    }

    public void putEntityAtRandomCoords(Entity entity) {
        boolean inserted = false;
        while (!inserted) {
            int x = RandomNumberGenerator.getRandomNumber(0, WIDTH - 1);
            int y = RandomNumberGenerator.getRandomNumber(0, HEIGHT - 1);

            if (!containsEntity(x, y)) {
                entity.setX(x);
                entity.setY(y);
                putEntity(x, y, entity);
                inserted = true;
            }
        }
    }

    private <T extends Entity> void initEntities(Class<T> entityClass) {
        while (entityCounts.get(entityClass) < thresholdsEntities.get(entityClass)) {
            putEntityAtRandomCoords(createEntity(entityClass));
            entityCounts.put(entityClass, entityCounts.get(entityClass) + 1);
        }
    }

    public <T extends Entity> void initFixedNumberOfEntities(Class<T> entityClass, int count) {
        for (int i = 0; i < count; i++) {
            if (entityCounts.get(entityClass) < thresholdsEntities.get(entityClass)) {
                putEntityAtRandomCoords(createEntity(entityClass));
                updateNumbersEntity(entityClass, CreatureState.ADD);
            }
        }
    }

    private <T extends Entity> Entity createEntity(Class<T> entityClass) {
        String className = entityClass.getSimpleName();
        Coords initCoords = new Coords(0, 0);
        return switch (className) {
            case "Herbivore" -> new Herbivore(this, initCoords);
            case "Predator" -> new Predator(this, initCoords);
            case "Grass" -> new Grass(initCoords);
            case "Tree" -> new Tree(initCoords);
            case "Rock" -> new Rock(initCoords);
            default -> throw new IllegalArgumentException("Unknown entity class");
        };
    }

    public void removeEntity(int x, int y, boolean isDead) {
        if (isDead) {
            updateNumbersEntity(worldMap.get(new Coords(x, y)).getClass(), CreatureState.REMOVE);
        }
        worldMap.remove(new Coords(x, y));
    }

    private void initThresholdEntity() {
        thresholdsEntities.put(Herbivore.class, 15);
        thresholdsEntities.put(Predator.class, 5);
        thresholdsEntities.put(Grass.class, 20);
        thresholdsEntities.put(Tree.class, 10);
        thresholdsEntities.put(Rock.class, 10);
    }

    private void initNumbersEntity() {
        entityCounts.put(Herbivore.class, 0);
        entityCounts.put(Predator.class, 0);
        entityCounts.put(Grass.class, 0);
        entityCounts.put(Tree.class, 0);
        entityCounts.put(Rock.class, 0);
    }

    private void updateNumbersEntity(
            Class<? extends Entity> entityClass, CreatureState creatureState) {
        switch (creatureState) {
            case REMOVE -> entityCounts.put(entityClass, entityCounts.get(entityClass) - 1);
            case ADD -> entityCounts.put(entityClass, entityCounts.get(entityClass) + 1);
        }
    }
}
