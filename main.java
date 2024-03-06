import java.util.Scanner;
import java.util.Random;

/*
 * @Author Simeon Milic
 * @version 1.2
 * @since 2024
 * this is a basic CLI turn-based fighting game. The player selects a weapon to use with various stats
 * Enemies are chosen by random for the player to fight by shuffling their index in an array.
 * Combat is simple turn based action where the goal is to get the enemies hp to 0 to win,
 * if your hp is lowered to 0 however you will lose the match, various actions exist to perform.
 * Such as attacking or guarding.
 */

class Main {
    // basic player stats, all but the player hp are assigned later depending on weapon
    static int playerHP = 50;

    static int playerATK;
    static int playerSpecial;
    static int playerGuard;

    static String[] weapons = {"Sword", "Axe", "Bow"};

    static int[] enemies = {1, 2, 3, 4, 5}; // 1: slime, 2: zombie, 3: knight, 4: monster 5: wraith
    static String[] enemiesStr = {"slime", "zombie", "knight", "monster", "wraith"};

    static String currentWeapon; // assigned to weapon chosen
    static int currentEnemy; // assigned to random enemy

    // basic enemy stats, values assigned depending on randomly chosen enemy
    static int enemyHP;
    static int enemyATK;
    static int enemyGuard;

    // this variable is used for the various render loops in the game. When conditions are met it is set to true to close the loop.
    static boolean windowShouldClose = false;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to game");
        System.out.print("Press X to start: ");
        String ans = keyboard.nextLine(); // the variable used for all Scanner inputs in the program
        if (ans.equalsIgnoreCase("x")) {
        // render loop
        while (!windowShouldClose) {
            System.out.println("\n\n\n\n\n"); // clear terminal is lacking in java so i have to newline spam
            System.out.println("Select weapon, press key next to name to select");
            System.out.println("Sword: 1");
            System.out.println("Axe: 2");
            System.out.println("Bow: 3");
            System.out.print("Input:");
            ans = keyboard.nextLine();

            // sword stats
            if (ans.equals("1")) {
                    System.out.println("You have selected the sword");
                    currentWeapon = weapons[0];
                    // set stats
                    playerATK = 20;
                    playerSpecial = 50;
                    playerGuard = 30;
                    System.out.println("Do you want to switch? Press X to switch if not press any key.");
                    System.out.print("Input:");
                    ans = keyboard.nextLine();
                    // closes the loop if condition is met
                    if (ans.equalsIgnoreCase("x")) {
                        continue;
                    } else {
                        windowShouldClose = true;
                    }
            // axe stats
            } else if (ans.equals("2")) {
                    System.out.println("You have selected the axe");
                    currentWeapon = weapons[1];
                    // set stats
                    playerATK = 30;
                    playerSpecial = 40;
                    playerGuard = 20;
                    System.out.println("Do you want to switch? Press X to switch if not press any key.");
                    System.out.print("Input:");
                    ans = keyboard.nextLine();
                    // closes the loop if condition is met
                    if (ans.equalsIgnoreCase("x")) {
                        continue;
                    } else {
                        windowShouldClose = true;
                    }
            // bow stats
            } else if (ans.equals("3")) {
                    System.out.println("You have selected the bow");
                    currentWeapon = weapons[2];
                    // set stats
                    playerATK = 20;
                    playerSpecial = 70;
                    playerGuard = 10;
                    System.out.println("Do you want to switch? Press X to switch if not press any key.");
                    System.out.print("Input:");
                    ans = keyboard.nextLine();
                    // closes the loop if condition is met
                    if (ans.equalsIgnoreCase("x")) {
                        continue;
                    } else {
                        windowShouldClose = true;
                    }
            } else {
                System.err.println("Invalid Input!");
            }

        } // end loop
    }

    windowShouldClose = false; // reset to be used again

    System.out.println("\n\n\n\n\n\n");
    System.out.println("Do you wish to check the weapon stats?");
    System.out.print("Press X to check or any other key to continue:");
    ans = keyboard.nextLine();
    
    // displays the weapons stats
    if (ans.equalsIgnoreCase("x")) {
        displayWeaponStat(currentWeapon);
        System.out.println("\n\n\n");
        ans = keyboard.nextLine();
    }
    
    // render loop
    while (!windowShouldClose) {
        shuffle(enemies);
        currentEnemy = enemies[0];
        switch (currentEnemy) {
            case 1:
                System.out.println("You will be fighting the slime!");
                enemyHP = 400;
                enemyATK = 20;
                enemyGuard = 5;
                break;
            case 2:
                System.out.println("You will be fighting the zombie!");
                enemyHP = 300;
                enemyATK = 30;
                enemyGuard = 5;
                break;
            case 3:
                System.out.println("You will be fighting the knight!");
                enemyHP = 500;
                enemyATK = 25;
                enemyGuard = 5;
                break;
            case 4:
                System.out.println("You will be fighting the monster!");
                enemyHP = 300;
                enemyATK = 25;
                enemyGuard = 5;
                break;
            case 5:
                System.out.println("You will be fighting the wraith!");
                enemyHP = 450;
                enemyATK = 20;
                enemyGuard = 5;
                break;
            default:
                System.err.println("what");
                break;
        }
        // sorts the shuffled array back to display the enemy list
        insertionSort(enemies);

        System.out.println("Here is a list of all the enemies");
        for (int i = 0; i < enemies.length; i++) {
            System.out.println(enemies[i] + " : " + enemiesStr[i]);
        }
        System.out.println("\n");
        System.out.println("Press any key to proceed");
        System.out.print(":");
        ans = keyboard.nextLine();
        windowShouldClose = true;
    } // end loop

        windowShouldClose = false; // reset to be used again

        System.out.println("Let's see if you get a bonus turn");

        Random rand = new Random();
        int turn = rand.nextInt(2);
        if (turn == 1) {
            System.out.println("Bonus success!");
            attackMenuPlayer(currentWeapon, playerATK, playerSpecial, playerGuard, enemyHP, playerHP);
        } else {
            System.out.println("Bonus failed " + enemiesStr[currentEnemy] + "goes first instead");
            attackMenuEnemy(enemyATK, enemyGuard, enemyHP, playerHP);
        }
        int counter = 0;
        // game loop
        while (!windowShouldClose) {
            counter++;
            System.out.println("Turn " + counter);
            attackMenuPlayer(currentWeapon, playerATK, playerSpecial, playerGuard, enemyHP, playerHP); // player
            System.out.print("continue: ");
            ans = keyboard.nextLine(); 
            attackMenuEnemy(enemyATK, enemyGuard, enemyHP, playerHP); // enemy
            System.out.print("continue: ");
            ans = keyboard.nextLine();

            System.out.println("You have " + playerHP + "hitpoints remaining\n");
            System.out.println("The enemy has " + enemyHP + "hitpoints remaining");

            if (playerHP <= 0) {
                System.out.println("You lose!");
                break;
            } else if (enemyHP <= 0) {
                System.out.println("You win");
                break;
            } else {
                continue;
            }

        } // end loop

        keyboard.close();
    }

    /*
     * Function which dicates enemy choices, takes in various parameters required for action, unlike the player all choices are random
     * and it lacks a special option to use.
     * @param enemyATK the damage the enemy deals
     * @param enemyGuard the hp the enemy will regain from guarding
     * @param enemyHP the hitpoints the current enemy has
     * @param playerHP the hitpoints the player currently has
     */
    static void attackMenuEnemy(int enemyATK, int enemyGuard, int enemyHP, int playerHP) {
        System.out.println("Enemy attacks!\n");
        Random rand = new Random();
        int choice = rand.nextInt(2);

        if (choice == 1) {
            System.out.println("Enemy attacks you! You take " + enemyATK + " damage from the attack");
        } else {
            System.out.println("Enemy guards, they gain " + enemyGuard + " HP!");
        }
        System.out.println("\n");
    }
    
    /*
     * Function which dictates plater choice, almost all basic stats regarding the enemy and player are taken as parameters for the function
     * Choices are made using a Scanner to recieve input, depending on the String inputed various actions are performed.
     * @param currentWeapon the weapon the player has chosen
     * @param playerATK the damage the player deals
     * @param playerSpecial the special attack the player has
     * @param playerGuard the hp the player will regain from guarding
     * @param enemyHP the hitpoints the current enemy has
     * @param playerHP the hitpoints the player currently has
     */
    static void attackMenuPlayer(String currentWeapon, int playerATK, int playerSpecial, int playerGuard, int enemyHP, int playerHP) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Combat Options");
        System.out.println("Attack: 1");
        System.out.println("Special: 2");
        System.out.println("Guard: 3");
        System.out.print("select: ");
        String ans = keyboard.nextLine();

        if (ans.equalsIgnoreCase("1")) {
            System.out.println("you attack with " + currentWeapon + " and you deal " + playerATK + " damage!");
            enemyHP -= playerATK;
        } else if (ans.equalsIgnoreCase("2")) {
            System.out.println("you use a special attack with " + currentWeapon + " and you deal " + playerSpecial + " damage!");
            enemyHP -= playerSpecial;
        } else if (ans.equalsIgnoreCase("3")){
            System.out.println("you use " + currentWeapon + " to guard! Gained " + playerGuard + " amount of extra HP");
            playerHP += playerGuard;
        } else {
            System.err.println("ERRROR EXCEPTION");
        }
        System.out.println("\n");
    }
    /*
     * takes in the current weapon which the player has chosen and prints the corrosponding stats of the weapon
     * @param currentWeapon the weapon the player has chosen
     */
    static void displayWeaponStat(String currentWeapon) {
        if (currentWeapon == weapons[0]) {
            System.out.println("Sword");
            System.out.println("Attack: 20dmg");
            System.out.println("Guard: 30");
            System.out.println("Special: 50dmg");
        } else if (currentWeapon == weapons[1]) {
            System.out.println("Axe");
            System.out.println("Attack: 30dmg");
            System.out.println("Guard: 20");
            System.out.println("Special: 40dmg");
        } else {
            System.out.println("Bow");
            System.out.println("Attack: 20dmg");
            System.out.println("Guard: 10");
            System.out.println("Special: 70dmg");
        }
    }

    // shuffle the enemy array
    /*
     * @param arr The array to be shuffled
     * @return arr The newly shuffled array
     */
    static int[] shuffle(int[] arr) {
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            // random index
            int j = rand.nextInt(arr.length );

            // swapping arr[i] with random index
            int temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    // sorts the enemies
    /*
     * @param arr The array to be sorted
     * @return arr The newly sorted array
     */
    static int[] insertionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && temp <= arr[j]) {
                // swap
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = temp;
        }
        return arr;
    }

}