package com.DmitriyDevv.view;

import com.DmitriyDevv.WorldMap;
import com.DmitriyDevv.view.icons.Icons;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class SwingWindow {
    private final int HEIGHT = 600;
    private final int WIDTH = 600;
    private final String title = "Simulation of Life";
    private final WorldMap worldMap;
    private final String pathIcons = "src/main/java/com/DmitriyDevv/view/icons/";
    private JFrame frame;
    private Map<String, ImageIcon> iconsMap;

    public SwingWindow(WorldMap worldMap) {
        this.worldMap = worldMap;
        checkResourcesExists();
        initIcons();
        initWindow();
    }

    public void renderMap() {
        JPanel panel =
                new JPanel(new GridLayout(worldMap.getHEIGHT() + 1, worldMap.getWIDTH() + 1));

        for (int y = 0; y <= worldMap.getHEIGHT(); y++) {
            for (int x = 0; x <= worldMap.getWIDTH(); x++) {
                JLabel label = new JLabel();

                if (!worldMap.containsEntity(x, y)) {
                    label.setIcon(iconsMap.get("Empty"));
                } else {
                    String entityClassName = worldMap.getEntity(x, y).getClass().getSimpleName();
                    label.setIcon(iconsMap.getOrDefault(entityClassName, iconsMap.get("Empty")));
                }

                panel.add(label);
            }
        }

        frame.getContentPane().removeAll();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private void initWindow() {
        frame = new JFrame(title);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void initIcons() {
        iconsMap =
                new HashMap<>() {
                    {
                        put("Empty", new ImageIcon(pathIcons + Icons.EMPTY.getIconName()));
                        put("Grass", new ImageIcon(pathIcons + Icons.GRASS.getIconName()));
                        put("Herbivore", new ImageIcon(pathIcons + Icons.SHEEP.getIconName()));
                        put("Tree", new ImageIcon(pathIcons + Icons.TREE.getIconName()));
                        put("Predator", new ImageIcon(pathIcons + Icons.WOLF.getIconName()));
                        put("Rock", new ImageIcon(pathIcons + Icons.ROCK.getIconName()));
                    }
                };
    }

    private void checkResourcesExists() {
        try {
            checkDirectoryExists();
            checkIconsExists();
        } catch (NoSuchFileException e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private void checkDirectoryExists() throws NoSuchFileException {
        if (!Files.isDirectory(Path.of(pathIcons))) {
            throw new NoSuchFileException("Directory does not exist: " + pathIcons);
        }
    }

    private void checkIconsExists() throws NoSuchFileException {
        for (Icons icon : Icons.values()) {
            if (!Files.exists(Path.of(pathIcons + "/" + icon.getIconName()))) {
                throw new NoSuchFileException("Icon does not exist: " + icon.getIconName());
            }
        }
    }
}
