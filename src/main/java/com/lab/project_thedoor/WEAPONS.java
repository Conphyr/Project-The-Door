package com.lab.project_thedoor;
import java.util.*;

public class WEAPONS {

    private String weaponName;
    private String weaponDescription;
    private String weaponType; //Melee, Range, etc.
    private double damage;
    private int attackCount; //How many times can the weapon fire/attack in a round
    private int bulletsCount;
    private int rounds;
    private int capacity; //For range weapons only (i.e. magazine capacity)
    //Accuracy is a percentage, 0.0 - 1.0
    private double accuracy;
    private int weaponCode; //For the item list
    private boolean canSpread; //If the weapon can spread damage (i.e. shotgun)
    private String caliber;
    private static ArrayList<WEAPONS> weaponsList = new ArrayList<>();

    public WEAPONS(String weaponName, String weaponDescription, String weaponType, double damage, int attackCount, int bulletsCount, int rounds, int capacity, double accuracy, boolean canSpread, String caliber) {

        this.weaponName = weaponName;
        this.weaponDescription = weaponDescription;
        this.weaponType = weaponType;
        this.damage = damage;
        this.attackCount = attackCount;
        this.bulletsCount = bulletsCount;
        this.rounds = rounds;
        this.capacity = capacity;
        this.accuracy = accuracy;
        this.canSpread = canSpread;
        this.caliber = caliber;

    }

    public String getWeaponName(WEAPONS weapon) {
        return weapon.weaponName;
    }

    public String getWeaponCaliber(WEAPONS weapon) {
        if (weapon.weaponType.equals("Range")) {
            return weapon.caliber;
        } else {
            return "N/A";
        }
    }

    public void getWeaponDescription(WEAPONS weapon, int weaponCount) {
        System.out.printf("%d) [%s]  --  %s\n", weaponCount, weapon.weaponName, weapon.weaponDescription);
    }

    public void getWeaponInfo(WEAPONS weapon, WEAPONS EMPTY_WEAPONS, PLAYER player) {

        Scanner input = new Scanner(System.in);
        String userChoice = "aaa";
        WEAPONS[] egg = new WEAPONS[1];
        if (weapon.weaponType.equals("Melee")) {
            System.out.printf("\n\\\\%s\\\\ -- DMG: %.2f -- ACC: %.2f", weapon.weaponName, weapon.damage, weapon.accuracy);
        } else {
            System.out.printf("\n\\\\%s\\\\ -- DMG: %.2f -- ACC: %.2f -- MAG: %d -- Spread?: %b", weapon.weaponName, weapon.damage, weapon.accuracy, weapon.rounds, weapon.canSpread);
        }

        System.out.println("\n[1] Equip\n[2] Reload\n[3] Discard");
        System.out.print("\nEnter your choice: ");
        userChoice = input.next();
        switch (userChoice) {
            case "1":
                if (player.getHoldingWeapon(player).weaponName.equals("Fist")) {
                    player.changeHoldingWeapon(player, weapon);
                    player.inventory_Weapons.remove(player.inventory_Weapons.get(0));
                    player.inventory_Weapons.add(EMPTY_WEAPONS);
                } else {
                    egg[0] = player.getHoldingWeapon(player);
                    player.changeHoldingWeapon(player, weapon);
                    player.inventory_Weapons.set(player.inventory_Weapons.indexOf(weapon), egg[0]);
                }
                break;
            case "2":
                if (weapon.weaponType.equals("Melee")) {
                    System.out.println("Cannot reload melee weapon.\n");
                } else {
                    for (ITEMS item : player.inventory_ITEMS) {
                        if (item.getItemName(item).equals(weapon.getWeaponCaliber(weapon))) {
                            if (weapon.capacity - weapon.rounds < item.getItemStackAmount(item)) {
                                weapon.rounds = weapon.capacity;
                                item.reduceStackAmount(item, weapon.capacity - weapon.rounds);
                            } else {
                                weapon.rounds = weapon.capacity;
                                player.inventory_ITEMS.remove(item);
                            }
                        }
                        break;
                    }
                }
                break;
            case "3":
                player.inventory_Weapons.remove(weapon);
                break;
            default:
                break;
        }

    }

    public void weaponUsage(WEAPONS weapon, int targetEntityCode, String bodyPart) {

    }

    public void addWeapon(WEAPONS weapon) {
        weaponsList.add(weapon);
    }

}