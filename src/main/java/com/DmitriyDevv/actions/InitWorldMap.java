package com.DmitriyDevv.actions;

import com.DmitriyDevv.WorldMap;

public class InitWorldMap extends Action {
    @Override
    public void make(WorldMap worldMap) {
        worldMap.initEntities();
    }
}
