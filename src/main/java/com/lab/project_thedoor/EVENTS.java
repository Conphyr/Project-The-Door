package com.lab.project_thedoor;
import java.util.*;

public class EVENTS {

    public String eventType;
    public String eventDescription;
    public String eventEntry;
    //Good, Bad, Neutral
    public String[] EVENT_TYPES = {"Good", "Bad", "Neutral"};
    public static ArrayList<EVENTS> eventList = new ArrayList<>();
    Scanner input = new Scanner(System.in);
    public String userChoice;

    public EVENTS(String eventEntry, String eventDescription, String eventType) {
        this.eventEntry = eventEntry;
        this.eventDescription = eventDescription;
        this.EVENT_TYPES = new String[]{eventType};
    }

    public void justSleep(boolean logPause) {
        //yeah just sleep my boy/girl
        if (logPause == true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                 e.printStackTrace();
            }
        }
    }

    public void addEvent(EVENTS event) {
        eventList.add(event);
    }

    public String getEventEntry(EVENTS event) {
        return event.eventEntry;
    }

    public void triggerEvent(String eventEntry, PLAYER player, WEAPONS weaponToBePassed, WEAPONS EMPTY_WEAPONS, boolean logPause) {
        switch (eventEntry) {
            case "GodenFreeMan":
                GodenFreeMan(player, weaponToBePassed, EMPTY_WEAPONS, logPause);
                // System.out.println("egg");
                break;
            default:
                break;
        }
    }

    public void GodenFreeMan(PLAYER player, WEAPONS Crowbar, WEAPONS EMPTY_WEAPONS, boolean logPause) {
        //Yes this event is a reference to the Half-Life game series (and Metro too)
        var S = System.out;
        boolean takeCrowbar = false, valid = false;
        
        S.println("You found a power distribution room.");
        justSleep(logPause);
        S.println("You searched around -- since the facilities were old enough, the power switch would not work.");
        justSleep(logPause);
        S.println("When you were about to leave, you saw a body lying on the ground.");
        justSleep(logPause);
        S.println("You walked closer -- that was a man in a strange, high-tech protective suit, with a crowbar in his hand.");
        justSleep(logPause);
        S.println("Poor physician, you thought. But anyway, why not take the crowbar?");
        justSleep(logPause);
        while (!valid) {
            System.out.print("Take the crowbar? (Y/N) ");
            userChoice = input.next();
            switch (userChoice.toUpperCase()) {
                case "Y":
                    S.println("You took the crowbar.");
                    takeCrowbar = true;
                    valid = true;
                    break;
                case "N":
                    S.println("Never mind. It shall forever stay with its owner.");
                    justSleep(logPause);
                    S.println("You left the power distribution room.");
                    valid = true;
                    break;
                default:
                    S.println("/////!/////Invalid choice./////!/////\n");
                break;
            }
            if (takeCrowbar == true) {
                takeCrowbar = player.addInventory_Weapon(Crowbar, EMPTY_WEAPONS, player);
            }
        }
    }

    public void encounterEnemy(PLAYER player, ArrayList<WEAPONS> weaponsList, ArrayList<ENEMIES> enemyTypesList, boolean logPause, String difficulty) {

        Random generator = new Random();
        double enemyStatsMultiplier = 1;
        int enemyRoll = generator.nextInt(enemyTypesList.size() - 1 + 0 + 1) + 0;
        int messageRoll = generator.nextInt(3 - 0 + 1) + 0;
        ENEMIES currentEnemy = new ENEMIES("", null, 0, 0, 0, 0);
        ENEMIES currentEnemy_Armed = new ENEMIES("", null, null, 0, 0, 0, 0);

        //roll an enemy
        switch (enemyTypesList.get(enemyRoll).enemyName) {
            case "Crab":
                currentEnemy = enemyTypesList.get(enemyRoll);
                break;
            case "Husk":
                currentEnemy = enemyTypesList.get(enemyRoll);
                break;
            case "ELID_Melee":
                currentEnemy = enemyTypesList.get(enemyRoll);
                break;
            case "ELID_Range":
                currentEnemy_Armed = enemyTypesList.get(enemyRoll);
                break;
            case "Stalker":
                currentEnemy_Armed = enemyTypesList.get(enemyRoll);
                break;
            default:
                break;
        }

        switch (difficulty) {
            case "Easy":
                enemyStatsMultiplier = 0.75;
                break;
            case "Hard":
                enemyStatsMultiplier = 1.50;
                break;
            case "Infinite":
                enemyStatsMultiplier = 1.25;
                break;
            default:
                break;
        }

        //initialize enemy health, it's worth to note that enemy's health changes upon the difficulty by using the factor called "enemyStatsMultiplier"
        if (!currentEnemy.enemyName.isEmpty()) {
            HEALTHSYSTEM enemyHealth = new HEALTHSYSTEM(currentEnemy_Armed.headHP(currentEnemy_Armed) * enemyStatsMultiplier, currentEnemy_Armed.chestHP(currentEnemy_Armed) * enemyStatsMultiplier, currentEnemy_Armed.limbsHP(currentEnemy_Armed) * enemyStatsMultiplier, currentEnemy_Armed.legsHP(currentEnemy_Armed) * enemyStatsMultiplier);
        } else {
            HEALTHSYSTEM enemyHealth = new HEALTHSYSTEM(currentEnemy.headHP(currentEnemy) * enemyStatsMultiplier, currentEnemy.chestHP(currentEnemy) * enemyStatsMultiplier, currentEnemy.limbsHP(currentEnemy) * enemyStatsMultiplier, currentEnemy.legsHP(currentEnemy) * enemyStatsMultiplier);
        } 

        //engage an enemy
        switch (messageRoll) {
            case 0:
                System.out.println("A wild " + currentEnemy.enemyName + currentEnemy_Armed.enemyName + " appeared!");
                break;
            case 1:
                System.out.println("A " + currentEnemy.enemyName + currentEnemy_Armed.enemyName + "came out from the dark.");
                break;
            case 2:
                System.out.println("You heard some noise. A " + currentEnemy.enemyName + currentEnemy_Armed.enemyName + " had blocked your way.");
                break;
            case 3:
                System.out.println("A " + currentEnemy.enemyName + currentEnemy_Armed.enemyName + "could be seen at the end of the hallway.");
                break;
            default:
                //debug
                // S.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeegggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg");
                break;
        }
        

    }

}