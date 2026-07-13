package com.lab.project_thedoor;
import java.util.*;

public class PLAYER {

    //Player attributes, not much tho lol
    private String name;
    private WEAPONS weaponHolding;
    private int sanity;
    private HEALTHSYSTEM playerHealth;
    public ArrayList<ITEMS> inventory_ITEMS = new ArrayList<ITEMS>();
    public ArrayList<WEAPONS> inventory_Weapons = new ArrayList<WEAPONS>();

    public PLAYER(String name, WEAPONS weaponHolding, int sanity, int entityCode, HEALTHSYSTEM playerHealth, ArrayList<ITEMS> inventory_ITEMS, ArrayList<WEAPONS> inventory_Weapons) {
        this.name = name;
        this.weaponHolding = weaponHolding;
        this.sanity = sanity;
        this.playerHealth = playerHealth;
        this.inventory_ITEMS = inventory_ITEMS;
        this.inventory_Weapons = inventory_Weapons;
    }

    public PLAYER() {
        //debug
    }

    public void initializeInventory(ITEMS EMPTY_ITEMS, WEAPONS EMPTY_WEAPONS) {
        //Items inventory
        for (int i = 0; i < 6; i++) {
            inventory_ITEMS.add(EMPTY_ITEMS);
        }
        //Weapons inventory
        for (int o = 0; o < 3; o++) {
            inventory_Weapons.add(EMPTY_WEAPONS);
        }
    }

    //still to be improved
    public boolean addInventory_Item(ITEMS item, ITEMS EMPTY_ITEMS, PLAYER player) {
        int amountToAdd = item.getItemStackAmount(item);
        int space = 404, toStack = 945;
        // First, try to stack onto existing stacks
        for (ITEMS ele : player.inventory_ITEMS) {
            if (ele.getItemName(ele).equals(item.getItemName(item)) && ele.getItemStackAmount(ele) < ele.getItemMaximumStackAmount(ele)) {
                space = ele.getItemMaximumStackAmount(ele) - ele.getItemStackAmount(ele);
                toStack = Math.min(space, amountToAdd);
                ele.addStackAmount(ele, toStack);
                amountToAdd -= toStack;
                if (amountToAdd == 0) {
                    return true;
                }
            }
        }
        // Then, add to empty slots
        while (amountToAdd > 0) {
            int toAdd = Math.min(item.getItemMaximumStackAmount(item), amountToAdd);
            int emptyIndex = player.inventory_ITEMS.indexOf(EMPTY_ITEMS);
            if (emptyIndex == -1) {
                return false; // No space left
            }
            ITEMS newItem = new ITEMS(item.getItemName(item), item.getItemDescription(item), item.getItemCategory(item), toAdd, item.getItemMaximumStackAmount(item));
            player.inventory_ITEMS.set(emptyIndex, newItem);
            amountToAdd -= toAdd;
        }
        return true;
    }

    public boolean addInventory_Weapon(WEAPONS weapon, WEAPONS EMPTY_WEAPONS, PLAYER player) {
        if (player.inventory_Weapons.indexOf(EMPTY_WEAPONS) != -1) {
            player.inventory_Weapons.set(player.inventory_Weapons.indexOf(EMPTY_WEAPONS), weapon);
            return true;
        } else {
            return false;
        }
    }

    public WEAPONS getHoldingWeapon(PLAYER player) {
        return player.weaponHolding;
    }

    public void changeHoldingWeapon(PLAYER player, WEAPONS weapon) {
        player.weaponHolding = weapon;
    }

    public void showInventory(PLAYER player, WEAPONS EMPTY_WEAPONS, ITEMS EMPTY_ITEMS) {

        Scanner input = new Scanner(System.in);
        String userChoice = "hello";
        boolean valid = false, breakOut = false, breakoutInner = false;

        while (!breakOut) {

            valid = false;
            breakoutInner = false;
            System.out.println("\n" + "^".repeat(10) + "Inventory" + "^".repeat(10));
            System.out.println("[1] Status");
            System.out.println("[2] Weapons");
            System.out.println("[3] Items");
            System.out.println("[4] Exit");

            while (!valid) {
                System.out.print("\nEnter your choice: ");
                userChoice = input.next();
                switch (userChoice) {

                    case "1":
                        
                        //Developing

                        break;
                
                    case "2":

                        while (!breakoutInner) {
                            //weapon
                            int capacityWeapons = 0;
                            for (WEAPONS ele : player.inventory_Weapons) {
                                if (!ele.getWeaponName(ele).equals("")) {
                                    capacityWeapons ++;
                                }
                            }
                    
                            int weaponCount = 1;
                            System.out.println("\n" + "-".repeat(10) + "Weapons" + "-".repeat(10));
                            System.out.printf("%s%s [%d/%d]%s\n", "=".repeat(5), "Slot capacity", capacityWeapons, 3, "=".repeat(5));
                            for (WEAPONS ele : player.inventory_Weapons) {
                                if (ele.getWeaponName(ele).equals("")) {
                                    break;
                                }
                                ele.getWeaponDescription(ele, weaponCount);
                                weaponCount ++;
                            }

                            //action on weapon?
                            System.out.print("Choose a weapon for further action (or \"!\" to quit): ");
                            userChoice = input.next();
                            switch (userChoice) {
                                case "1":
                                    if (player.inventory_Weapons.get(0).getWeaponName(player.inventory_Weapons.get(0)).isEmpty()) {
                                        System.out.println("This weapon slot is empty.\n");
                                        breakoutInner = true;
                                        valid = true;
                                    } else {
                                        player.inventory_Weapons.get(0).getWeaponInfo(player.inventory_Weapons.get(0), EMPTY_WEAPONS, player);
                                        breakoutInner = true;
                                        valid = true;
                                    }
                                    break;
                                case "2":
                                    if (player.inventory_Weapons.get(1).getWeaponName(player.inventory_Weapons.get(0)).isEmpty()) {
                                        System.out.println("This weapon slot is empty.\n");
                                        breakoutInner = true;
                                        valid = true;
                                    } else {
                                        player.inventory_Weapons.get(1).getWeaponInfo(player.inventory_Weapons.get(0), EMPTY_WEAPONS, player);
                                        breakoutInner = true;
                                        valid = true;
                                    }
                                    break;
                                case "3":
                                    if (player.inventory_Weapons.get(2).getWeaponName(player.inventory_Weapons.get(0)).isEmpty()) {
                                        System.out.println("This weapon slot is empty.\n");
                                        breakoutInner = true;
                                        valid = true;
                                    } else {
                                        player.inventory_Weapons.get(2).getWeaponInfo(player.inventory_Weapons.get(0), EMPTY_WEAPONS, player);
                                        breakoutInner = true;
                                        valid = true;
                                    }
                                    break;
                                case "!":
                                    breakoutInner = true;
                                    valid = true;
                                default:
                                    break;
                            }
                        }

                        break;

                    case "3":

                        //item
                        int capacityItems = 0;
                        for (ITEMS ele : player.inventory_ITEMS) {
                            if (!ele.getItemName(ele).equals("")) {
                                capacityItems ++;
                            }
                        }
                
                        int itemCount = 1;
                        System.out.println("\n" + "-".repeat(10) + "Items" + "-".repeat(10));
                        System.out.printf("%s%s [%d/%d]%s\n", "=".repeat(5), "Slot capacity", capacityItems, 6, "=".repeat(5));
                        for (ITEMS ele : player.inventory_ITEMS) {
                            if (ele.getItemName(ele).equals("")) {
                                break;
                            }
                            ele.getItemDescription(ele, itemCount);
                            itemCount ++;
                        }
                        valid = true;

                        break;

                    case "4":
                        breakOut = true;
                        valid = true;
                        break;
                    default:
                        System.out.println("\n/////!/////Invalid choice./////!/////\n"); 
                    break;

                }
            }

        }

    }

}