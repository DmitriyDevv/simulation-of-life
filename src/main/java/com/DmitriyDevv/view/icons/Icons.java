package com.DmitriyDevv.view.icons;

public enum Icons {
    EMPTY("empty.png"),
    GRASS("grass.png"),
    ROCK("rock.png"),
    SHEEP("sheep.png"),
    TREE("tree.png"),
    WOLF("wolf.png");

    private final String iconName;

    Icons(String iconName) {
        this.iconName = iconName;
    }

    public String getIconName() {
        return iconName;
    }
}
