package com.DmitriyDevv;

import com.DmitriyDevv.actions.Action;
import com.DmitriyDevv.actions.IncreaseNumberEntities;
import com.DmitriyDevv.actions.InitWorldMap;
import com.DmitriyDevv.actions.MoveCreatures;
import com.DmitriyDevv.view.SwingWindow;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Action> turnActions = new ArrayList<>();
    private final List<Action> initActions = new ArrayList<>();
    private final WorldMap worldMap = new WorldMap();
    private final int simulationDelay = 500;
    SwingWindow swingWindow = new SwingWindow(worldMap);

    public Simulation() {
        createTurnActions();
        createInitActions();
    }

    public void startSimulation() throws InterruptedException {
        startInitialization();
        while (true) {
            nextTurn();
            Thread.sleep(simulationDelay);
        }
    }

    private void startInitialization() {
        for (Action action : initActions) {
            action.make(worldMap);
            swingWindow.renderMap();
        }
    }

    private void nextTurn() {
        for (Action action : turnActions) {
            action.make(worldMap);
            swingWindow.renderMap();
        }
    }

    private void createInitActions() {
        initActions.add(new InitWorldMap());
    }

    private void createTurnActions() {
        turnActions.add(new MoveCreatures());
        turnActions.add(new IncreaseNumberEntities());
    }
}
