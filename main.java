import java.util.Scanner;

class Main {

    int playerHP;
    int playerATK;
    static String[] weapons = {"Sword", "Axe", "Bow"};
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
                while (true) {
                    System.out.println("You have selected the sword");
                    currentWeapon = weapons[0];
                    System.out.println("Do you want to switch? Press X to switch if not press any key.");
                    System.out.print("Input:");
                    ans = keyboard.nextLine();
                    if (ans.equalsIgnoreCase("x")) {
                        continue;
                    } else {
                        break;
                    }
                }
            } else if (ans.equals("2")) {
                System.out.println("You have selected the axe");
                currentWeapon = weapons[1];
            } else if (ans.equals("3")) {
                System.out.println("You have selected the bow");
                currentWeapon = weapons[2];
            } else {
                System.err.println("Invalid Input!");
            }

            System.out.println("Do you wish to check the weapon stats?");
            System.out.print("Press X to check or any other key to continue:");

            if (ans.equalsIgnoreCase("x")) {
                displayWeaponStat(currentWeapon);
            }
        }
    }


        keyboard.close();
    }

    static void displayWeaponStat(String currentWeapon) {
        if (currentWeapon == weapons[0]) {
            System.out.println("Attack: 20dmg");
            System.out.println("Guard: 30");
            System.out.println("Special: 50dmg");
        } else if (currentWeapon == weapons[1]) {
            System.out.println("Attack: 30dmg");
            System.out.println("Guard: 20");
            System.out.println("Special: 40dmg");
        } else {
            System.out.println("Attack: 20dmg");
            System.out.println("Guard: 10");
            System.out.println("Special: 70dmg");
        }
    }

}