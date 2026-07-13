// Copyright (c) 1979-1991, The Kino Works, Inc.
//All rights (are not) reserved.

package com.lab.project_thedoor;
import java.io.PrintStream;
import java.util.*;

public class GAMEMAIN {

    //rounds/difficulty settings
    private final int ROUND_LIMIT_01 = 30;
    private final int ROUND_LIMIT_02 = 60;
    private final int ROUND_LIMIT_03 = 90;
    private boolean isGameOver = false;
    private int currentRound = 1; 
    private String[] difficulty = {"Easy", "Medium", "Hard"};

    //game initialization
    Random generator = new Random();
    final int THECODE = generator.nextInt(9999 - 1000 + 1) + 1000; //For the deepcharged explosive
    private int playerCode = 0, debugDummyCode = 1;
    ArrayList<ITEMS> inventory_ITEMS = new ArrayList<ITEMS>();
    ArrayList<WEAPONS> inventory_Weapons = new ArrayList<WEAPONS>();

    ArrayList<Integer> entitiesCode = new ArrayList<>();
    ArrayList entities = new ArrayList();

    //-----------------------------------------
    // Fields for enemies, items, weapons, etc.
    //-----------------------------------------

    //To represent empty slots
    public ITEMS EMPTY_ITEMS;
    public WEAPONS EMPTY_WEAPONS;
    public ENEMIES currentEnemy; // enemy you're fighting

    //Events
    public EVENTS GodenFreeMan;

    //Enemies
    ENEMIES DEBUG_DUMMY;
    ENEMIES CRAB;
    ENEMIES HUSK;
    ENEMIES ELID_MELEE;
    ENEMIES ELID_RANGE;
    ENEMIES PRISONER;

    //Items
    ITEMS A_12_MEDKIT;
    ITEMS Salewa_first_aid_kit;
    ITEMS bandage;
    ITEMS anti_radiation_pill;
    ITEMS Alyonka_chocolate_bar;
    ITEMS can_of_condensed_milk;
    ITEMS adur;
    ITEMS OX_energy_drink;
    ITEMS ammo_9_18mm_PMM;
    ITEMS ammo_7_62_38mm;
    ITEMS ammo_5_45x39mm;
    ITEMS ammo_12gauge;
    ITEMS rusty_key;
    ITEMS deepcharged_explosive;
    ITEMS rusty_note;

    //Weapons
    WEAPONS fist;
    WEAPONS Crowbar;
    WEAPONS NR_40;
    WEAPONS Machete;
    WEAPONS Makarov_pistol;
    WEAPONS Nagant_revolver;
    WEAPONS AK_74U;
    WEAPONS MP_133_Shotgun;
    WEAPONS TOZ_34;
    WEAPONS SUPERFISH;

    //Player
    PLAYER player;
    HEALTHSYSTEM playerHealth;

    //Game
    GAMEMAIN game;

    public GAMEMAIN(String difficultyLevel, int roundLimit, int currentRound, boolean isGameOver) {
        this.difficulty = new String[]{difficultyLevel};
        this.currentRound = currentRound;
        this.isGameOver = isGameOver;
    }

    public GAMEMAIN() {
        //debug
    }

    public void justSleep(boolean pause) {
        //sleep method but main
        if (pause == true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                 e.printStackTrace();
            }
        }
    }

    public void initialization(String playerName, String gameDifficulty, boolean pause) {

        //Initialize enemies, items, events and player

        //--------------------------
        //Events
        //--------------------------
        GodenFreeMan = new EVENTS("GodenFreeMan", "GodenFreeMan", "Good");
        
        //--------------------------
        //Enemy Types
        //--------------------------

        //Debug Dummy
        DEBUG_DUMMY = new ENEMIES("Debug Dummy", "A dummy entity for debugging purposes. Maybe try to feed it some fish?", 100, 100, 100, 100);
        // DEBUG_DUMMY.addEnemyTypes(DEBUG_DUMMY);

        //Crab
        CRAB = new ENEMIES("Crab", "A kind of parasitic creature that has infested the fort. The cause of Husks.", 20, 20, 5, 5);
        CRAB.addEnemyTypes(CRAB);
        //Husk
        HUSK = new ENEMIES("Husk", "Infected human beings in the fort. They used to be soldiers or prisoners like you.", 30, 50, 20, 20);
        HUSK.addEnemyTypes(HUSK);
        //E.L.I.D. Melee
        ELID_MELEE = new ENEMIES("ELID_Melee", "A Husk that has developed the fourth state of E.L.I.D. (Eurosky Low-Emission Infectious Disease). At least they are not smart enough to use range weapons.", 40, 70, 30, 30);
        ELID_MELEE.addEnemyTypes(ELID_MELEE);
        //E.L.I.D. Range
        ELID_RANGE = new ENEMIES("ELID_Range", "A Husk that has developed the third state of E.L.I.D. (Eurosky Low-Emission Infectious Disease), who are still conscious enough to use weapons. Be careful -- they used to be soldiers, after all.", fist, 50, 80, 40, 40);
        ELID_RANGE.addEnemyTypes(ELID_RANGE);
        //Stalker
        PRISONER = new ENEMIES("Prisoner", "One of the prisoners fighting for their own good on their way to \"The Room\", some where in the fort. Remember: they are not here to save you.", fist, 35, 70, 25, 25);
        PRISONER.addEnemyTypes(PRISONER);

        //debug
        // DEBUG_DUMMY.getEnemyDescription(ELID_RANGE);

        //--------------------------
        //Items
        //--------------------------

        EMPTY_ITEMS = new ITEMS("", null, "", 1, 1);

        //Med
        A_12_MEDKIT = new ITEMS("A-12 Medkit", "A standard service first aid kit, widely used in USSR. Includes narcotic opioid syringes to recover a small amount of HP.", "Med", 1, 4);
        A_12_MEDKIT.addItem(A_12_MEDKIT);
        Salewa_first_aid_kit = new ITEMS("Salewa first aid kit", "A first aid kit contains almost all you can need. Recover a large amount of HP in all the body parts and stop bleeding.", "Med", 1, 1);
        Salewa_first_aid_kit.addItem(Salewa_first_aid_kit);
        bandage = new ITEMS("Bandage", "A simple bandage to stop bleeding and recover a small amount of HP.", "Med", 1, 4);
        bandage.addItem(bandage);
        anti_radiation_pill = new ITEMS("Anti-radiation pill", "A pill to reduce radiation poisoning. Recover a little bit of the decrease of your  HP caused by the radiation.", "Med", 1, 8);
        anti_radiation_pill.addItem(anti_radiation_pill);
        //Sanity/Provisions
        Alyonka_chocolate_bar = new ITEMS("Alyonka chocolate bar", "A classic Russian chocolate bar, yum yum. Recover a small amount of sanity.", "Provisions", 1, 4);
        Alyonka_chocolate_bar.addItem(Alyonka_chocolate_bar);
        can_of_condensed_milk = new ITEMS("Can of condensed milk", "Condensed sweet. Recover a medium amount of sanity.", "Provisions", 1, 1);
        can_of_condensed_milk.addItem(can_of_condensed_milk);
        adur = new ITEMS("Adur", "A potent mix of vodka and herbs, used to fully recover sanity. Not recommended for use in large amounts.", "Provisions", 1, 1);
        adur.addItem(adur);
        OX_energy_drink = new ITEMS("OX Energy Drink", "A can of OX energy drink. Not from OX. Recover a large amount of sanity.", "Provisions", 1, 1);
        OX_energy_drink.addItem(OX_energy_drink);
        //Ammunition
        ammo_9_18mm_PMM = new ITEMS("9x18mm PMM", "A 9x18mm PMM cartridge, used in Makarov pistols. A common ammunition type.", "Ammunition", 1, 20);
        ammo_9_18mm_PMM.addItem(ammo_9_18mm_PMM);
        ammo_7_62_38mm = new ITEMS("7.62x38mm", "A 7.62x38mm cartridge, used in Nagant revolvers. Bang * 7.", "Ammunition", 1, 20);
        ammo_7_62_38mm.addItem(ammo_7_62_38mm);
        ammo_5_45x39mm = new ITEMS("5.45x39mm", "A 5.45x39mm cartridge, used in AK-74 and other Soviet rifles. Reliable.", "Ammunition", 1, 30);
        ammo_5_45x39mm.addItem(ammo_5_45x39mm);
        ammo_12gauge = new ITEMS("12 Gauge", "A 12 Gauge cartridge, used in shotguns. It's time to give them a big BOOM.", "Ammunition", 1, 10);
        ammo_12gauge.addItem(ammo_12gauge);
        //Special
        rusty_key = new ITEMS("Rusty key", "A rusty key, probably for a random door. Who knows?", "Special", 1, 4);
        rusty_key.addItem(rusty_key);
        //SPECIAL
        deepcharged_explosive = new ITEMS("Deepcharged explosive", "An explosive found in \"The Room\", need password to access. It has \"Bunker-4\" carved on its bottom.", "Special", 1, 1); //For one of the endings
        rusty_note = new ITEMS("Rusty note", "A note written in Russian. It says: \"The code is " + THECODE + ".\"", "Special", 1, 1); //For one of the endings

        //debug
        // A_12_MEDKIT.getItemDescription(rusty_note);

        //---------------------------
        //Weapons
        //---------------------------

        EMPTY_WEAPONS = new WEAPONS("", null, "", 0, 0, 0, 0, 0, 0, false, null);
        
        //Melee
        fist = new WEAPONS("Fist", "Your own fist.", "Melee", 1, 1, 1, 0, 0, 0.5, false, null);
        fist.addWeapon(fist);
        Crowbar = new WEAPONS("Crowbar", "A solid crowbar. Ideal for keeping crabs away.", "Melee", 3, 1, 1, 0, 0, 0.55, false, null);
        Crowbar.addWeapon(Crowbar);
        NR_40 = new WEAPONS("NR-40", "A classic Soviet combat knife. Cut the throat.", "Melee", 5, 1, 1, 0, 0, 0.6, false, null);
        NR_40.addWeapon(NR_40);
        Machete = new WEAPONS("Machete", "A sharp machete. A good choice for a melee weapon.", "Melee", 8, 1, 1, 0, 0, 0.65, false, null);
        Machete.addWeapon(Machete);
        //Range
        Makarov_pistol = new WEAPONS("Makarov pistol", "A standard Soviet pistol, reliable and easy to use. Uses 9x18mm PMM cartridges.", "Range", 10, 1, 1, 8, 8, 0.65, false, "9x18mm PMM");
        Makarov_pistol.addWeapon(Makarov_pistol);
        Nagant_revolver = new WEAPONS("Nagant revolver", "A classic revolver, uses 7.62x38mm cartridges. Now take a lesson from the senior!", "Range", 15, 1, 1, 7, 7, 0.7, false, "7.62x38mm");
        Nagant_revolver.addWeapon(Nagant_revolver);
        AK_74U = new WEAPONS("AK-74U", "A Soviet assault rifle, uses 5.45x39mm cartridges. Good partner of a German AR.", "Range", 20, 3, 1, 30, 30, 0.65, false, "5.45x39mm");
        AK_74U.addWeapon(AK_74U);
        MP_133_Shotgun = new WEAPONS("MP-133 Shotgun", "A pump-action shotgun, uses 12 Gauge cartridges. \"Modify your weapon!\"", "Range", 2.5, 1, 10, 3, 5, 0.6, true, "12 Gauge");
        MP_133_Shotgun.addWeapon(MP_133_Shotgun);
        TOZ_34 = new WEAPONS("TOZ-34", "A double-barrel shotgun, uses 12 Gauge cartridges. BOOM BOOM.", "Range", 5, 1, 5, 2, 2, 0.65, true, "12 Gauge");
        TOZ_34.addWeapon(TOZ_34);

        //???
        SUPERFISH = new WEAPONS("SUPERFISH", "fish.", "Range", Double.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1.00, true, null);

        //debug
        // Makarov_pistol.getWeaponDescription(TOZ_34);

        //---------------------------
        //Player
        //---------------------------

        switch (gameDifficulty) {

            case "Easy":

                playerHealth = new HEALTHSYSTEM(60, 100, 50, 50);
                game = new GAMEMAIN(gameDifficulty, ROUND_LIMIT_01, currentRound, isGameOver);
                break;

            default:
                break;

        }

        player = new PLAYER(playerName, fist, 100, playerCode, playerHealth, inventory_ITEMS, inventory_Weapons);
        player.initializeInventory(EMPTY_ITEMS, EMPTY_WEAPONS);

        //if nothing went wrong then the game should start here
        System.out.println("\nYou wake up in a dark cell surrounded with concrete walls.");
        justSleep(pause);
        System.out.println("You move around in the cell, looking for a switch for the light...");
        justSleep(pause);
        System.out.println("Click. A halogen lamp on the celling emits a beam of dim light.");
        justSleep(pause);
        System.out.println("Something useful can be seen under the light... You pick them up.");
        justSleep(pause);
        System.out.println("You look at the door. It is half-covered.");
        justSleep(pause);
        System.out.println("You feel determined.");
        justSleep(pause);

        gameRound(game, player);

    }

    //-------------------------------------------------------------------------------------
    //Technically speaking the random events are triggered here, so do the change of rounds
    //-------------------------------------------------------------------------------------
    public void gameRound(GAMEMAIN game, PLAYER player) {

        Scanner input = new Scanner(System.in);
        String userChoice = "egg";
        boolean valid = false;

        System.out.println();
        System.out.println("/".repeat(10));
        System.out.println("Round " + game.currentRound);
        System.out.println("/".repeat(10));

        System.out.println("[1] Choose the door");
        System.out.println("[2] Open inventory");
        System.out.println("[3] Go back"); //you are not going back since this is "The Zone", where things are changing every second :)
        System.out.println("[4] Surrender");

        while (!valid) {
            System.out.print("\nEnter your choice: ");
            userChoice = input.next();
            switch (userChoice) {

                case "1":
                    
                    break;
            
                case "2":

                    //-----debug-----
                    player.addInventory_Weapon(MP_133_Shotgun, EMPTY_WEAPONS, player);
                    player.addInventory_Weapon(Makarov_pistol, EMPTY_WEAPONS, player);
                    player.addInventory_Item(can_of_condensed_milk, EMPTY_ITEMS, player);
                    player.addInventory_Item(Salewa_first_aid_kit, EMPTY_ITEMS, player);
                    player.addInventory_Item(ammo_12gauge, EMPTY_ITEMS, player);
                    player.addInventory_Item(ammo_12gauge, EMPTY_ITEMS, player);
                    player.addInventory_Item(ammo_12gauge, EMPTY_ITEMS, player);
                    //---------------

                    player.showInventory(player, EMPTY_WEAPONS, EMPTY_ITEMS);
                    valid = true;
                    break;

                case "3":

                    break;

                case "4":

                    break;

                default:
                    break;
            }
        }

    }

    //"The Door", a text-based, rouge-like and round-based game, inspired by classic RPGs & Stalker the film.
    public static void main(String[] args) {
        
        var S = System.out;
        Scanner input = new Scanner(System.in);
        String userChoice = "fish", playerName = "RFB", gameDifficulty = "Easy";
        boolean valid = false, validInner = false, pause = false;
        GAMEMAIN rf = new GAMEMAIN();

        S.println("--------------------The Door--------------------");
        S.printf("%28s%10s\n\n", "Demo v0.1", "");

        while (!valid) {

            S.println("\t\t[1] Game Start");
            S.println("\t\t[2] Settings");
            S.println("\t\t[3] Notebook");
            S.println("\t\t[4] Quit");
            
            S.print("\nEnter your choice: ");
            userChoice = input.next();
            validInner = false;
            switch (userChoice) {

                case "1":

                    while (!validInner) {

                        S.println("Choose the difficulty:\n");
                        S.println("\t[1] Easy");
                        S.println("\t[2] Normal -- Stay tuned");
                        S.println("\t[3] Hard -- Stay tuned");
                        S.println("\t[4] Infinite -- Stay tuned");

                        S.print("\nEnter your choice: ");
                        userChoice = input.next();
                        switch (userChoice) {
                            case "1":
                                S.print("\nNow, prisoner, what is your name: ");
                                playerName = input.next();
                                S.println("Let's begin...");
                                rf.initialization(playerName, "Easy", pause);
                                validInner = true;
                                valid = true;
                                break;
                            default:
                                S.println("\n/////!/////Invalid choice./////!/////\n");
                                validInner = false;
                            break;
                        }

                    }    
                
                    break;
                
                case "2":

                    while (!validInner) {
                        S.println("\nEnable pause between logs during events: " + pause);
                        S.print("Toggle state (Y = true, N = false): ");
                        userChoice = input.next();
                        switch (userChoice.toUpperCase()) {
                            case "Y":
                                pause = true;
                                validInner = true;
                                S.println("Log pause is enabled.\n");
                                break;
                            case "N":
                                pause = false;
                                validInner = true;
                                S.println("Log pause is disabled.\n");
                                break;
                            default:
                                S.println("\n/////!/////Invalid choice./////!/////\n");
                                validInner = false;
                            break;
                        }
                    }
                    break;

                case "3":

                    S.println("\nStay tuned.\n");
                    
                    break;

                case "4":

                    System.exit(0);

                    break;

                default:

                    S.println("\n/////!/////Invalid choice./////!/////\n");    

                break;
            }
            
        }
        
    }
    
}