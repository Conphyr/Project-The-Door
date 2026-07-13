package com.lab.project_thedoor;
import java.util.*;

public class ENEMIES {

    public String enemyName;
    public String enemyDescription;
    HEALTHSYSTEM entityHealth;
    private WEAPONS weaponsHolding;
    public static ArrayList<ENEMIES> enemyTypesList = new ArrayList<>();

    //Remove this part
    public ENEMIES(String enemyName, String enemyDescription, double headHealth, double chestHealth, double limbsHealth, double legsHealth) {
        this.enemyName = enemyName;
        this.enemyDescription = enemyDescription;
        this.entityHealth = new HEALTHSYSTEM(headHealth, chestHealth, limbsHealth, legsHealth);
    }

    public ENEMIES(String enemyName, String enemyDescription, WEAPONS weaponHolding, double headHealth, double chestHealth, double LimbsHealth, double LegsHealth) {
        this.enemyName = enemyName;
        this.enemyDescription = enemyDescription;
        this.weaponsHolding = weaponHolding;
        this.entityHealth = new HEALTHSYSTEM(headHealth, chestHealth, LimbsHealth, LegsHealth);
    }

    public ENEMIES getEnemyEntity(String enemyName, ArrayList<ENEMIES> enemyTypesListMain) {   
        //Returns the enemy entity type by its name to construct a new enemy
        for (ENEMIES enemyType : enemyTypesListMain) {
            if (enemyType.enemyName.equals(enemyName)) {
                return enemyType;
            }
        }
        return null; // If not found, return null (probably not the case tho)
    }

    public void getEnemyDescription(ENEMIES enemyType) {
        System.out.println(enemyType.enemyDescription);
    }

    public void addEnemyTypes(ENEMIES enemyType) {
        enemyTypesList.add(enemyType);
    }

    public double headHP(ENEMIES enemy) {
        return enemy.entityHealth.headHealth;
    }

    public double chestHP(ENEMIES enemy) {
        return enemy.entityHealth.chestHealth;
    }

    public double limbsHP(ENEMIES enemy) {
        return enemy.entityHealth.limbsHealth;
    }

    public double legsHP(ENEMIES enemy) {
        return enemy.entityHealth.legsHealth;
    }
    
}