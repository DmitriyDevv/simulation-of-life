package com.DmitriyDevv.view;

import com.DmitriyDevv.entitys.movingEntities.Herbivore;
import com.DmitriyDevv.entitys.movingEntities.Predator;
import com.DmitriyDevv.entitys.staticEntities.Grass;
import com.DmitriyDevv.entitys.staticEntities.Rock;
import com.DmitriyDevv.entitys.staticEntities.Tree;

public enum Icons {
    EMPTY("empty.png", "Empty"),
    GRASS("grass.png", Grass.class.getSimpleName()),
    ROCK("rock.png", Rock.class.getSimpleName()),
    SHEEP("sheep.png", Herbivore.class.getSimpleName()),
    TREE("tree.png", Tree.class.getSimpleName()),
    WOLF("wolf.png", Predator.class.getSimpleName());

    private final String iconName;
    private final String entityClassName;

    Icons(String iconName, String entityClassName) {
        this.iconName = iconName;
        this.entityClassName = entityClassName;
    }

    public String getIconName() {
        return iconName;
    }

    public String getEntityClassName() {
        return entityClassName;
    }
}
