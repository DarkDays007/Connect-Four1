package ics22063;
import java.util.Random;
import java.util.Scanner;


public class ConnectFourGame {
   private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private final Scanner scanner = new Scanner(System.in);

    public ConnectFourGame() {
        System.out.println("Welcome to Connect Four!");

        // Player 1
        String player1Name = askPlayerName(1);
        char player1Chip = askPlayerChip(player1Name);
        player1 = new Player(player1Name, player1Chip);

    
        /// Player  2
        String player2Name = askPlayerName(2);
        char player2Chip = (player1Chip == 'x') ? 'o' : 'x';
        player2 = new Player(player2Name, player2Chip);
        System.out.println(player2.getName() + ", your chip is: " + player2.getChip());

        // Αρχικοποιηση Board
        int rows = askBoardSize("rows");
        int columns = askBoardSize("columns");
        board = new Board(rows, columns);

        // Random choose
        currentPlayer = new Random().nextBoolean() ? player1 : player2;
        System.out.println("The first player is: " + currentPlayer.getName());
    }

    public void start() {
        while (true) {
             board.print();
            System.out.print(currentPlayer.getName() + ", choose a column (1-" + board.getColumns() + "): ");
            int column = scanner.nextInt() - 1;

            if (board.placeChip(column, currentPlayer.getChip())) {
                if (board.checkWin(currentPlayer.getChip())) {
                    board.print();
                    System.out.println("GAME OVER. THE WINNER IS " + currentPlayer.getName());
                    break;
                } else if (board.isFull()) {
                     board.print();
                     System.out.println("GAME OVER. WE HAVE A DRAW.");
                     break;
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            } else {
                System.out.println("Column " + (column + 1) + " is full. Please select another column.");
            }
        }
    }

    private String askPlayerName(int playerNumber) {
        System.out.print("Enter the name of player " + playerNumber + ": ");
        return scanner.next();
    }

    private char askPlayerChip(String playerName) {
        while (true) {
            System.out.print(playerName + ", select your chip (x or o): ");
             char chip = scanner.next().charAt(0);
             if (chip == 'x' || chip == 'o') {return chip;}
             
            System.out.println("Invalid chip. Please choose 'x' or 'o'.");
        }
    }

    private int askBoardSize(String dimension) {
        int size;
        while (true) {
            System.out.print("Enter the number of " + dimension + " (4-15): ");
             size = scanner.nextInt();
             if (size >= 4 && size <= 15) {
                 break;
            }
            System.out.println("Invalid size. Must be between 4 and 15.");
        }
         return size;
    }
}