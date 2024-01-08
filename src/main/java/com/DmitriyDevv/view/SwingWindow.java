package com.DmitriyDevv.view;

import com.DmitriyDevv.WorldMap;

import java.awt.*;
import java.net.URL;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class SwingWindow {
    private final int HEIGHT = 600;
    private final int WIDTH = 600;
    private final String title = "Simulation of Life";
    private final WorldMap worldMap;
    private final String pathIcons = "com/DmitriyDevv/view/icons/";
    private final Map<String, ImageIcon> iconsMap = new HashMap<>();
    private JFrame frame;

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
        ClassLoader classLoader = getClass().getClassLoader();
        for (Icons icon : Icons.values()) {
            URL iconURL = classLoader.getResource(pathIcons + icon.getIconName());
            iconsMap.put(icon.getEntityClassName(), new ImageIcon(iconURL));
        }
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
        URL directoryURL = getClass().getClassLoader().getResource(pathIcons);
        if (directoryURL == null) {
            throw new NoSuchFileException("Directory does not exist: " + pathIcons);
        }
    }

    private void checkIconsExists() throws NoSuchFileException {
        for (Icons icon : Icons.values()) {
            String iconPath = pathIcons + icon.getIconName();
            URL iconURL = getClass().getClassLoader().getResource(iconPath);
            if (iconURL == null) {
                throw new NoSuchFileException("Icon does not exist: " + icon.getIconName());
            }
        }
    }
}
