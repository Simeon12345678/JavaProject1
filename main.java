import java.util.Scanner;
import java.util.Random;

class Main {

    static int playerHP;
    static int playerATK;
    static int playerSpecial;
    static int playerGuard;
    static String[] weapons = {"Sword", "Axe", "Bow"};
    static int[] enemies = {1, 2, 3, 4, 5}; // 1: slime, 2: zombie, 3: knight, 4: monster 5: wraith
    static String currentWeapon;
    static int currentEnemy;

    static int enemyHP;
    static int enemyATK;
    static int enemyGuard;

    static boolean windowShouldClose = false;

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Welcome to game");
        System.out.print("Press X to start: ");
        String ans = keyboard.nextLine();
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

            if (ans.equals("1")) {
                    System.out.println("You have selected the sword");
                    currentWeapon = weapons[0];
                    playerATK = 20;
                    playerSpecial = 50;
                    playerGuard = 30;
                    System.out.println("Do you want to switch? Press X to switch if not press any key.");
                    System.out.print("Input:");
                    ans = keyboard.nextLine();
                    if (ans.equalsIgnoreCase("x")) {
                        continue;
                    } else {
                        windowShouldClose = true;
                    }
            } else if (ans.equals("2")) {
                    System.out.println("You have selected the axe");
                    currentWeapon = weapons[1];
                    playerATK = 30;
                    playerSpecial = 40;
                    playerGuard = 20;
                    System.out.println("Do you want to switch? Press X to switch if not press any key.");
                    System.out.print("Input:");
                    ans = keyboard.nextLine();
                    if (ans.equalsIgnoreCase("x")) {
                        continue;
                    } else {
                        windowShouldClose = true;
                    }
            } else if (ans.equals("3")) {
                    System.out.println("You have selected the bow");
                    currentWeapon = weapons[2];
                    playerATK = 20;
                    playerSpecial = 70;
                    playerGuard = 10;
                    System.out.println("Do you want to switch? Press X to switch if not press any key.");
                    System.out.print("Input:");
                    ans = keyboard.nextLine();
                    if (ans.equalsIgnoreCase("x")) {
                        continue;
                    } else {
                        windowShouldClose = true;
                    }
            } else {
                System.err.println("Invalid Input!");
            }

        }
    }

    windowShouldClose = false;

    System.out.println("\n\n\n\n\n\n");
    System.out.println("Do you wish to check the weapon stats?");
    System.out.print("Press X to check or any other key to continue:");
    ans = keyboard.nextLine();
    
    if (ans.equalsIgnoreCase("x")) {
        displayWeaponStat(currentWeapon);
        System.out.println("\n\n\n");
        ans = keyboard.nextLine();
    }
    
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
        System.out.println("\n\n");
        System.out.println("Press any key to proceed");
        System.out.print(":");
        ans = keyboard.nextLine();
        windowShouldClose = true;
    }

        windowShouldClose = false;

        Random rand = new Random();
        int turn = rand.nextInt(2);
        if (turn == 1) {
            System.out.println("you go first");
            attackMenuPlayer(currentWeapon, playerATK, playerSpecial, playerGuard, enemyHP, playerHP);
        } else {
            System.out.println(currentEnemy + "goes first");
            attackMenuEnemy(enemyATK, enemyGuard, enemyHP, playerHP);
        }
        int counter = 0;

        while (!windowShouldClose) {
            attackMenuPlayer(currentWeapon, playerATK, playerSpecial, playerGuard, enemyHP, playerHP);

            attackMenuEnemy(enemyATK, enemyGuard, enemyHP, playerHP);

            counter++;
            System.out.println("Turn " + counter);

            if (playerHP <= 0) {
                System.out.println("You lose!");
                break;
            } else if (enemyHP <= 0) {
                System.out.println("You win");
                break;
            } else {
                continue;
            }

        }

        keyboard.close();
    }

    static void attackMenuEnemy(int enemyATK, int enemyGuard, int enemyHP, int playerHP) {
        System.out.println("Enemy attacks!\n");
        Random rand = new Random();
        int choice = rand.nextInt(2);

        switch (choice) {
            case 1:
                System.out.println("Enemy attacks you! You take " + enemyATK + " damage from the attack");
                break;
            case 2:
                System.out.println("Enemy guards, they gain " + enemyGuard + " HP!");
                break;
            default:
                break;
        }
        System.out.println("\n");
    }
    
    static void attackMenuPlayer(String currentWeapon, int playerATK, int playerSpecial, int playerGuard, int enemyHP, int playerHP) {
        Scanner tempAns = new Scanner(System.in);
        System.out.println("Combat Options");
        System.out.println("Attack: 1");
        System.out.println("Special: 2");
        System.out.println("Guard: 3");
        System.out.print("select: ");
        String ans = tempAns.nextLine();
        
        switch (ans) {
            case "1":
                System.out.println("you attack with " + currentWeapon + " and you deal " + playerATK + " damage!");
                enemyHP -= playerATK;
                break;
            case "2":
                System.out.println("you use a special attack with " + currentWeapon + " and you deal " + playerSpecial + " damage!");
                enemyHP -= playerSpecial;
                break;
            case "3":
                System.out.println("you use " + currentWeapon + " to guard! Gained " + playerGuard + " amount of extra HP");
                playerHP += playerGuard;
            default:
                break;
        }
        tempAns.close();
        System.out.println("\n");
    }

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
    static int[] insertionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i];
            int j = i - 1;
            while (j >= 0 && temp <= arr[j]) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            arr[j + 1] = temp;
        }
        return arr;
    }

}