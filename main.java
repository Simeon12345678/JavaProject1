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

    static int enemyHP;
    static int enemyATK;

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

    while (!windowShouldClose) {
        System.out.println("\n\n\n\n\n\n");
        System.out.println("Do you wish to check the weapon stats?");
        System.out.print("Press X to check or any other key to continue:");
        ans = keyboard.nextLine();
    
        if (ans.equalsIgnoreCase("x")) {
            displayWeaponStat(currentWeapon);
            System.out.println("\n\n\n");
            System.out.print("check again? : ");
            ans = keyboard.nextLine();
            if (ans.equalsIgnoreCase("x")) {
                continue;
            } else {
                windowShouldClose = true;
            }
        } 
    }

    windowShouldClose = false;
    while (!windowShouldClose) {
        
    }

        keyboard.close();
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
        for (int i = arr.length - 1; i > 0; i++) {
            // random index
            int j = rand.nextInt(i + 1);

            // swapping arr[i] with random index
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
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