package com.lab.project_thedoor;
import java.util.*;

public class ENTITIES {

    private ENEMIES enemyType;
    private PLAYER player;
    private int entityCode;
    private static ArrayList<Object> entities = new ArrayList<>();

    public ENTITIES(ENEMIES enemyType, int entityCode) {
        this.enemyType = enemyType;
        this.entityCode = entityCode;
        entities.add(this);
    }

    public ENTITIES(PLAYER player, int entityCode) {
        this.player = player;
        this.entityCode = entityCode;
        entities.add(this);
    }

    public void addEntity(ENTITIES entity, int entityCode) {
        entities.add(entity);
    }
    
}