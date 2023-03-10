import java.util.Random;
import java.util.Scanner;

class ResourceCollector {
    // Variables to store the amounts of resources
    static int wood = 0;
    static int stone = 0;
    static int plantFibre = 0;
    // Variables to store the amounts of crafted items
    static int pickaxe = 0;
    static int sword = 0;
    static int axe = 0;
    // Variables to store the current level of the Wood Cutting, Mining skills and Intelligence
    static int miningSkill = 1;
    static int woodCuttingSkill = 1;
    static double intelligenceSkill = 0.0;
    static int health = 100;
    static int fuel = 0;
    static int rocket = 0;
    static int wires = 0;
    static int day = 1;
    // Scanner and Random objects for user input and random resource generation
    static Scanner sc = new Scanner(System.in);
    static Random rand = new Random();
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static void main(String[] args) {
        System.out.println("Survival Evolution " + ANSI_GREEN + "\n Welcome to 'Survival Evolution'! \n A game where you will evolve from being a small, \nunwitting human to a space explorer!\n Enjoy!" + ANSI_RESET);
            // An infinite loop to keep the game running until the user chooses to exit
            while (true) {
                // Display the main menu
                System.out.println(""+ ANSI_CYAN + "\nMenu:\n1. Gather resources\n2. Craft\n3. View inventory\n4. Skills\n5. Study\n6. Sleep \n7. Exit" + ANSI_RESET);
                System.out.println("" + ANSI_GREEN + "Day " + day);
                System.out.println("Health: " + health + "%"+ ANSI_RESET);
                System.out.print("Choose an option: ");
                // Get the user's choice from the menu
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1 -> gatherResources();  // Call the gatherResources method if the user chooses to gather resources
                    case 2 -> craft();           // Call the craft method if the user chooses to craft items
                    case 3 -> viewInventory();   // Call the viewInventory method if the user chooses to view their inventory
                    case 4 -> viewSkills();     // Call the viewSkills method if the user chooses to view their skills
                    case 5 -> study();           // Call the study method if the user chooses to study
                    case 6 -> sleep();           // Call the sleep method taking the player to a new day.
                    case 7 -> System.exit(0);   // Exit the program if the user chooses to exit
                    default -> System.out.println("Invalid choice.");  // Display an error message if the user inputs an invalid choice
                }
            }

    }

    private static void sleep() {
        day++;
        System.out.println("You had some good sleep. Get ready for day " + day);
        System.out.println("Your health regenerated by 20%");
        health += 20;

            if (health >=101) {
                health = 100;
            }
        System.out.println("\n You gained full health. Congrats!");

    }

    private static void randomlegevent() {
        int event = rand.nextInt(100);  // Generate a random number between 0 and 99, if the number is less or equal to 5, your health decreases.
        if (event <= 5) {
            System.out.println("You broke your leg. Health decreased by 5%");
            health -= 5;
            if (health <= 0) {
                health = 0;
            }
            {
                if (health <1) {
                    System.out.println("You are now dead. Please restart!");
                }
            }


        }
    }

    private static void randomwolvesevent() {
        int event = rand.nextInt(100);  // Generate a random number between 0 and 99
        if (event <= 3) {
            System.out.println("You got bitten by a wolf! Health decreased by 15%");
            health -= 15;
            if (health < 0) {
                health = 0;
                if(sword <= 1) randomwolvesevent(); {
                        health += 13;
                        System.out.println(" A wolf tried attacking you. Thanks god you have a sword \n You only got a scratch, health decreased to 5%");

                }
            }
            {
                if (health <1) {
                System.out.println("You are now dead. Please restart!");
                }
            }
        }
    }

    private static void lootingevent() {
        int event = rand.nextInt(300); // Generate a random number between 0 and 300
        if (event <= 3) {
            System.out.println("You have found an abandoned SpaceX lab. See what you can find!");
            System.out.println("\n1. Go left...\n2. Go right...");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            if (choice == 1) {
                rocketfuel();
                wires();
            } else if (choice == 2) {
                spaceship();
            } else {
                System.out.println("Invalid choice, please choose 1 or 2.");
            }


        }
    }

    private static void wires() {
        System.out.println("\n You have found 2x Wires. It looks like you'll need more in the future!");
        wires++;
    }


    // Increases the Intelligence skill by 1
    private static void study() {
        intelligenceSkill++;
        System.out.println("Intelligence skill increased to " + intelligenceSkill);
    }

    //Displays each skill
    private static void viewSkills() {
        System.out.println("Mining: " + miningSkill);
        System.out.println("Wood Cutting: " + woodCuttingSkill);
        System.out.println("Intelligence: " + (int) (intelligenceSkill));
    }

    // Increase the amounts of wood, stone, and plant fibre by a random value between 1 and 8 times the level of the Mining or Wood Cutting skill
    static void gatherResources() {
        wood += rand.nextInt(8 * miningSkill) + 1;
        stone += rand.nextInt(8 * miningSkill) + 1;
        plantFibre += rand.nextInt(8 * woodCuttingSkill) + 1;
        // Display the amount of resources gathered
        System.out.println("\nResources gathered:\nWood: " + wood + "\nStone: " + stone + "\nPlant Fibre: " + plantFibre);
        // Activates the events
        gatherResourcesevents();
    }
    static void gatherResourcesevents() {

        randomlegevent();
        randomwolvesevent();
        lootingevent();

    }

    // Display the crafting menu
    static void craft() {
        System.out.println("\nCrafting menu:\n1. Pickaxe\n2. Axe\n3. Sword");
        System.out.print("Choose an item to craft: ");
        // Get the user's choice of item to craft
        int craftChoice = sc.nextInt();
        sc.nextLine();
        // Check if the user has enough resources to craft a pick
        switch (craftChoice) {
            case 1 -> {
                if (wood >= 1 && plantFibre >= 5 && stone >= 3 && intelligenceSkill >= 2) {
                    wood -= 1;
                    plantFibre -= 5;
                    stone -= 3;
                    intelligenceSkill += 0;
                    pickaxe += 1;
                    miningSkill++;
                    System.out.println("Pickaxe crafted. Mining skill increased to " + miningSkill + ".");
                } else {
                    System.out.println("Not enough resources or intelligence to craft pickaxe.");
                }
            }
            case 2 -> {
                if (wood >= 1 && plantFibre >= 3 && stone >= 2 && intelligenceSkill >= 2) {
                    wood -= 1;
                    plantFibre -= 3;
                    stone -= 2;
                    intelligenceSkill += 0;
                    axe += 1;
                    woodCuttingSkill++;
                    System.out.println("Axe crafted. Wood cutting skill increased to " + woodCuttingSkill + ".");
                } else {
                    System.out.println("Not enough resources or intelligence to craft axe.");
                }
            }
            case 3 -> {
                if (stone >= 10 && plantFibre >= 10 && wood >= 2 && intelligenceSkill >= 15) {

                    stone -= 10;
                    plantFibre -= 10;
                    wood -= 2;
                    intelligenceSkill += 0;
                    sword += 1;
                    System.out.println("" + ANSI_GREEN + "Sword Crafted. You will now take less damage from enemies!" + ANSI_RESET);
                } else { System.out.println("" + ANSI_GREEN + "Not enough resource or intelligence to craft a sword. " + ANSI_RESET);

                }
                if (sword > 1) {
                    System.out.println("" + ANSI_GREEN + "\n You already got a sword. You can't carry more!" + ANSI_RESET);
                }

            }
            default -> System.out.println("Invalid choice.");
        }
    }

    static void viewInventory() {
        System.out.println("\nInventory:\nWood: " + wood + "\nStone: " + stone + "\nPlant Fibre: " + plantFibre);
    }


        public static void spaceship() {
            rocket++;
            System.out.println("You have found a spaceship! Try fixing it and find some fuel");
            {
                System.out.println("\nYou got tired. Go back home\n1. Ok");

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("\nMenu:\n1. Gather resources\n2. Craft\n3. View inventory\n4. Skills\n5. Study\n6. Sleep \n7. Exit");
                } else {
                    System.out.println("You really have to go home -_- ");
                }
            }
        }


        private static void rocketfuel() {
            fuel++;
            System.out.println("You have found " + fuel + " fuel. Gather 500 to launch your spaceship! ");
            {
                System.out.println("\nYou got tired. Go back home\n1. Ok\n");

                Scanner scanner = new Scanner(System.in);
                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("\nMenu:\n1. Gather resources\n2. Craft\n3. View inventory\n4. Skills\n5. Study\n6. Sleep \n7. Exit");
                } else {
                    System.out.println("You really have to go home -_- ");
                }
            }
        }

}










