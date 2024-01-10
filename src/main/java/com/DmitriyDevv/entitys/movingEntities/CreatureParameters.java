package com.DmitriyDevv.entitys.movingEntities;

public class CreatureParameters {
    private final int ATTACK;
    private final int STEP_COST;
    private int HP;

    public CreatureParameters(int HP, int ATTACK, int stepCost) {
        this.HP = HP;
        this.ATTACK = ATTACK;
        STEP_COST = stepCost;
    }

    public int getSTEP_COST() {
        return STEP_COST;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        if (HP < 0) {
            throw new IllegalArgumentException("HP must be greater than 0");
        }
        this.HP = HP;
    }

    public int getATTACK() {
        return ATTACK;
    }
}
