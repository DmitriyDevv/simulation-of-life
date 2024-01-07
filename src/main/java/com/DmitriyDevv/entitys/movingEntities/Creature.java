package com.DmitriyDevv.entitys.movingEntities;

import com.DmitriyDevv.Coords;
import com.DmitriyDevv.WorldMap;
import com.DmitriyDevv.entitys.Entity;
import com.DmitriyDevv.utils.findingPath.Astar;

import java.util.List;

public abstract class Creature extends Entity {
    protected final CreatureParameters creatureParameters;
    private final Class<?> goal;
    private final WorldMap worldMap;

    public Creature(
            WorldMap worldMap,
            Class<?> goal,
            CreatureParameters creatureParameters,
            Coords coords) {
        super(coords);
        this.worldMap = worldMap;
        this.goal = goal;
        this.creatureParameters = creatureParameters;
    }

    public void move() {
        Astar aStar = new Astar(worldMap, this);
        List<Coords> pathToGoal = aStar.getPath();

        if (pathToGoal != null && pathToGoal.size() > 1) {
            int secondsIndexInPathToGoal = 1;
            int xToGoal = pathToGoal.get(secondsIndexInPathToGoal).getX();
            int yToGoal = pathToGoal.get(secondsIndexInPathToGoal).getY();

            if (pathToGoal.size() == 2) {
                attackGoal(xToGoal, yToGoal);
            } else {
                moveToGoal(xToGoal, yToGoal);
            }
        }
    }

    public Class<?> getGoal() {
        return goal;
    }

    private void attackGoal(int xToGoal, int yToGoal) {
        Entity entity = worldMap.getEntity(xToGoal, yToGoal);

        if (entity instanceof Attacked currentGoal) {
            if ((currentGoal.getHP() - this.getATTACK()) >= 0) {
                currentGoal.setHP(currentGoal.getHP() - this.getATTACK());
                this.setHP(this.getHP() + this.getATTACK());
            } else {
                this.setHP(this.getHP() + (Math.abs(currentGoal.getHP() - this.getATTACK())));
                worldMap.removeEntity(xToGoal, yToGoal, true);
            }
        }
    }

    private void moveToGoal(int xToGoal, int yToGoal) {
        if (this.getHP() > this.getSTEP_COST()) {
            worldMap.removeEntity(this.getX(), this.getY(), false);
            worldMap.putEntity(xToGoal, yToGoal, this);
            this.setX(xToGoal);
            this.setY(yToGoal);
            this.setHP(this.getHP() - creatureParameters.getSTEP_COST());
        } else {
            int x = worldMap.getCoordsCreature(this).getX();
            int y = worldMap.getCoordsCreature(this).getY();
            worldMap.removeEntity(x, y, true);
        }
    }

    private int getHP() {
        return this.creatureParameters.getHP();
    }

    private void setHP(int HP) {
        this.creatureParameters.setHP(HP);
    }

    private int getSTEP_COST() {
        return this.creatureParameters.getSTEP_COST();
    }

    private int getATTACK() {
        return this.creatureParameters.getATTACK();
    }
}
