import java.util.Scanner;

class DiceGame {
    private static final int MIN_THROW = 1;
    private static final int MAX_THROW = 6;
    private static final int NUMBER_OF_TURNS = 5;
    private static final int MAX_NUMBER_OF_THROWS = 10;
    private static final int FIRST_THROW = 1;
    private static final int WINNING_ROLLS_SUM_7 = 7;
    private static final int WINNING_ROLLS_SUM_11 = 11;
    private static final int WINNING_ROLLS_SUM_5 = 5;
    private static final int LOOSING_ROLLS_SUM_2 = 2;
    private static final int LOOSING_ROLLS_SUM_12 = 12;

    /**
     * The method check correctness of input.
     * Method throwing exception when data is incorrect.
     *
     * @param int roll - numbers of points on the one dice (1-6)
     * @throws IllegalArgumentException - exception when given number is out of range (1-6).
     */
    private void checkRollCorrectness(int roll) throws IllegalArgumentException {
        if (roll < MIN_THROW || roll > MAX_THROW)
            throw new IllegalArgumentException("Incorrect input! You can use only numbers in range (1-6).");
    }

    /**
     * The method handling single round in game, taking input from player (number of points on the dice).
     * If data are incorrect program throw exception and asking about enter data again.
     * Adds player points, informs about number of points and about possible losing or winning.
     *
     * @param Player player - object of Class Player, containt player name.
     * @return float sumOfPlayerPoints - returns number of player points obtained in round.
     * @throws Exception
     */
    private float round(Player player) {
        int rollNumber = 1;
        int firstDice, secondDice;
        float sumOfPointsOnDices = 0f, sumOfPlayerPoints = 0f;
        Scanner roll = new Scanner(System.in);
        while (rollNumber <= MAX_NUMBER_OF_THROWS) {
            System.out.println("\nPlayer " + player.getName() + " enter the number of points on the first dice (1-6):");
            firstDice = roll.nextInt();
            System.out.println("\nPlayer " + player.getName() + " enter the number of points on the second dice (1-6):");
            secondDice = roll.nextInt();
            try {
                checkRollCorrectness(firstDice);
            } catch (Exception e) {
                System.out.println("\nIncorrect roll player " + player.getName() + " enter the number of points on the first dice once more(1-6):");
                firstDice = roll.nextInt();
            }
            try {
                checkRollCorrectness(secondDice);

            } catch (Exception e) {
                System.out.println("\nIncorrect roll player " + player.getName() + " enter the number of points on the second dice once more(1-6):");
                secondDice = roll.nextInt();
            }

            sumOfPointsOnDices = firstDice + secondDice;
            System.out.println("Player " + player.getName() + " made " + rollNumber + " roll: " + firstDice + " " + secondDice);
            System.out.println("Sum of the player's points in this roll: " + sumOfPointsOnDices);

            if (((rollNumber == FIRST_THROW) && (sumOfPointsOnDices == WINNING_ROLLS_SUM_7)) || (sumOfPointsOnDices == WINNING_ROLLS_SUM_11)) {
                System.out.println("Player " + player.getName() + "won the round.");
                break;
            } else if (((rollNumber == FIRST_THROW) && (sumOfPointsOnDices == LOOSING_ROLLS_SUM_2)) || (sumOfPointsOnDices == LOOSING_ROLLS_SUM_12)) {
                System.out.println("Player " + player.getName() + " lost the round.");
                sumOfPlayerPoints += rollNumber;
                break;
            } else if (sumOfPointsOnDices == WINNING_ROLLS_SUM_5) {
                System.out.println("Player " + player.getName() + " won the round.");
                break;
            } else {
                sumOfPlayerPoints += sumOfPointsOnDices / rollNumber;
            }
            rollNumber++;
        }
        return sumOfPlayerPoints;
    }

    /**
     * The most important method of the game.
     * The method handling course of the game in all five rounds, concludes results and informs about win, lost or draw.
     */
    public void startGame() {
        Player player1 = new Player("First");
        Player player2 = new Player("Second");
        String currentPlayer = player1.getName(); //Player "First" starting.
        float sumOfPlayer1Points = 0f, sumOfPlayer2Points = 0f;
        int numberOfRounds = 1;
        while (numberOfRounds <= NUMBER_OF_TURNS) {
            System.out.println("\nRound: " + numberOfRounds);
            sumOfPlayer1Points += round(player1);
            sumOfPlayer2Points += round(player2);
            System.out.println("\nSummary - results: ");
            System.out.println("Player " + player1.getName() + " has : " + sumOfPlayer1Points + "points.");
            System.out.println("Player " + player2.getName() + " has : " + sumOfPlayer2Points + "points.");
            numberOfRounds++;
        }
        System.out.println("\nEnd results: ");
        System.out.println("Sum of First player points: " + sumOfPlayer1Points + ".");
        System.out.println("Sum of Second player points: " + sumOfPlayer2Points + ".");

        if (sumOfPlayer1Points < sumOfPlayer2Points)
            System.out.println("First player has won!");
        else if (sumOfPlayer1Points == sumOfPlayer2Points)
            System.out.println("It is a draw!");
        else
            System.out.println("Second player has won!");
    }
}