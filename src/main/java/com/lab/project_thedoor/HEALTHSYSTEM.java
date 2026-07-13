package com.lab.project_thedoor;
import java.util.*;

public class HEALTHSYSTEM {
    
    double headHealth;
    double chestHealth;
    double limbsHealth;
    double legsHealth;
    static ArrayList<HEALTHSYSTEM> entityHealth = new ArrayList<>();
    
    public HEALTHSYSTEM(double headHealth, double chestHealth, double limbsHealth, double legsHealth) {
        //Health system for the entities, divided into body parts
        //Head, Chest, Limbs, Legs
        /*Zero (or below) HP in head or chest means DEATH
        * Zero (or below) HP in limbs or legs means incapacitation, lower accuracy and etc.
        * Each part has its own HP, which can be reduced by damage from events or other entities.
        * ...And can be healed with items or rest.
        */
        this.headHealth = headHealth;
        this.chestHealth = chestHealth;
        this.limbsHealth = limbsHealth;
        this.legsHealth = legsHealth;
    }

    public void addEntityHealth(HEALTHSYSTEM healthSystem) {
        entityHealth.add(healthSystem);
    }
    
    public void changeHealth(String bodyPart, double changeAmount) {
        switch (bodyPart.toLowerCase()) {
            case "head":
                this.headHealth += changeAmount;
                break;
            case "chest":
                this.chestHealth += changeAmount;
                break;
            case "limbs":
                this.limbsHealth += changeAmount;
                break;
            case "legs":
                this.legsHealth += changeAmount;
                break;
            default:
        }
    }

}