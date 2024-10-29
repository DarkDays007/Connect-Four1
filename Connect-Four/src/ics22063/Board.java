package ics22063;


public class Board {
	
	
     private final char[][] grid;
     private final int rows;
     private final int columns;

    public Board(int rows, int columns) {
         this.rows = rows;
         this.columns = columns;
         grid = new char[rows][columns];
         initializeBoard();//to make null borad i mean whit - - - 
                                                         //- - - 
                                                         //- - - 
      }

    private void initializeBoard()  {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                grid[i][j] = '-';
            }
        }
    }

    
    public boolean placeChip(int column, char chip) {
        if (column < 0 || column >= columns) {
            System.out.println("Invalid column. Please select a column within range.");//ελεγχος για στηλες 
            return false;
        }
        for (int row = rows - 1; row >= 0; row--) {  //αρχιζει απο κατω μερος row(max )και ανεβαινει προσ τα πανω και οταν βρει - θεσει βαζει το chıp 
            if (grid[row][column] == '-') {
                 grid[row][column] = chip; // place the chip that insert player 
                    return true;
            }
        }
return false; // Η στηλη  εεναι γεματη//full column
    }

    public boolean isFull() {// ελεγχει το τελυταιο row αλλα εινα ιτο πιο πανω row και αν υπαρχει θεση με - δεν ειναι γεματη αλλιως γεματη και επιστρεφει false 
        for (int col = 0; col < columns; col++) {
            if (grid[0][col] == '-') {
                return false;
            }
        }
        return true; // if is full with chip 
    }

    public boolean checkWin(char chip) {
        // Ελεγχος οριζοντιας  νικης ,ελεγχος για col col+1 +2 +3 
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col <= columns - 4; col++) {
                if  (grid[row][col] == chip && grid[row][col + 1] == chip &&
                      grid[row][col + 2] == chip && grid[row][col + 3] == chip) {
                        return true;
                }
            }
        }

        // Ελεγχος καθετης  νικης,  check for row row+1 +2 +3 
        for (int col = 0; col < columns; col++) {
            for (int row = 0; row <= rows - 4; row++) {
                if (grid[row][col] == chip && grid[row + 1][col] == chip &&
                    grid[row + 2][col] == chip && grid[row + 3][col] == chip) {
                   return true;
                }
            }
        }

        // Ελεγχος διαγωνιας νικης , row col ,row+1 col +1 ,row+2 col+2
        for (int row = 0; row <= rows - 4; row++) {
            for (int col = 0; col <= columns - 4; col++)
                     {
                if (grid[row][col] == chip && grid[row + 1][col + 1] == chip &&
                    grid[row + 2][col + 2] == chip && grid[row + 3][col + 3] == chip) {
                	
                     return true;
                }
            }
        }
        for (int row = 3; row < rows; row++) {
            for (int col = 0; col <= columns - 4; col++) {
                if (grid[row][col] == chip && grid[row - 1][col + 1] == chip &&
                    grid[row - 2][col + 2] == chip && grid[row - 3][col + 3] == chip) {
                    return true;
                }
            }
        }

         return false;
    }

    public void print() {
         for (int i = 0; i < rows; i++) {
            System.out.print("| ");
            for (int j = 0; j < columns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
         }
           System.out.println("-".repeat(columns * 2 + 3));
        for (int i = 1; i <= columns; i++) {
             System.out.print(" " + i);
        }
     System.out.println();
    }

    public int getColumns(){
         return columns;  }
}