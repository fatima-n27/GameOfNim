import java.util.Scanner;
public class LastBiscuit {
    //Constants representing the number of biscuits initially in the barrels
    public static final int BISCUITS_IN_BARREL_ONE_START = 6;
    public static final int BISCUITS_IN_BARREL_TWO_START = 8;

    //Message to prompt user to choose the barrel they want to take biscuits from
    public static final String CHOOSE_A_BARREL_OUTPUT_MESSAGE = "Choose a barrel: barrel1 (one), "
            + "barrel2 (two), or both (both), or skip turn (skip)? ";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //Player identifiers & Current player is set as player 2
        String playerOne = "1";
        String playerTwo = "2";
        String currentPlayer = playerTwo;

        //Variables to represent number of biscuits in each barrel
        int barrelOne = BISCUITS_IN_BARREL_ONE_START;
        int barrelTwo = BISCUITS_IN_BARREL_TWO_START;

        //Loop runs every player's turn until winner declared
        while (barrelOne > 0 || barrelTwo > 0) {
            // Standard message to represent biscuits in a barrel
            System.out.println("Biscuits Left - Barrel 1: " + barrelOne);
            System.out.println("Biscuits Left - Barrel 2: " + barrelTwo);

            //Player is switched every turn
            if (currentPlayer.equals(playerOne)) {
                currentPlayer = playerTwo;
            } else {
                currentPlayer = playerOne;
            }
            System.out.println("Player Turn: " + currentPlayer);

            //Loop to check validity of inputs
            int numberBiscuitsTaken;
            String chosenBarrel;

            do {
                System.out.print(CHOOSE_A_BARREL_OUTPUT_MESSAGE);
                chosenBarrel = in.nextLine();

                //Checks validity of chosen barrel input
                while (!(chosenBarrel.equalsIgnoreCase("one")
                        || (chosenBarrel.equalsIgnoreCase("two"))
                        || (chosenBarrel.equalsIgnoreCase("both")))) {
                    System.out.print(CHOOSE_A_BARREL_OUTPUT_MESSAGE);
                    chosenBarrel = in.nextLine();
                }

                //Prompt for biscuits taken by user
                System.out.print("How many biscuits are you taking? ");

                //Checks for legal input type
                while (!in.hasNextInt()) {
                    System.out.print("Please input an integer: ");
                    in.nextLine();
                }
                numberBiscuitsTaken = in.nextInt();
                in.nextLine();

                // Checks for legal biscuit count in each barrel
                if ((chosenBarrel.equalsIgnoreCase("one") && barrelOne < numberBiscuitsTaken)
                        || (chosenBarrel.equalsIgnoreCase("two") && barrelTwo < numberBiscuitsTaken)
                        || (chosenBarrel.equalsIgnoreCase("both")
                        && (barrelOne < numberBiscuitsTaken)
                        || (barrelTwo < numberBiscuitsTaken))) {

                    System.out.println("Sorry, that's not a legal number of biscuits for "
                            + "that/those barrel(s)");

                } else if (numberBiscuitsTaken < 1) {

                    System.out.println("Sorry, that's not a legal number of biscuits "
                            + "for that/those barrel(s)");
                } else {
                    break; //When input is valid, validity loop is exited
                }
            } while (true);

            //Based on player choice, number of biscuits is adjusted in each barrel
            if (chosenBarrel.equalsIgnoreCase("one")) {
                barrelOne -= numberBiscuitsTaken;
            } else if (chosenBarrel.equalsIgnoreCase("two")) {
                barrelTwo -= numberBiscuitsTaken;
            } else if (chosenBarrel.equalsIgnoreCase("both")) {
                barrelOne -= numberBiscuitsTaken;
                barrelTwo -= numberBiscuitsTaken;
            }

            //When biscuits are 0, winner is declared
            if ((barrelOne == 0) && (barrelTwo == 0)) {
                System.out.println("Biscuits Left - Barrel 1: " + barrelOne);
                System.out.println("Biscuits Left - Barrel 2: " + barrelTwo);
                System.out.println("Winner is player " + currentPlayer);
            }
        }
    }
}
