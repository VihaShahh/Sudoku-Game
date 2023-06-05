import java.util.Random;

public class sudoku {

    private static final int SIZE = 9;
    private static final int EMPTY_CELL = 0;

    public static void main(String[] args) {
        int[][] board = generateBoard();
        printBoard(board);
    }

    public static int[][] generateBoard() {
        int[][] board = new int[SIZE][SIZE];


        solveBoard(board);


        Random random = new Random();
        int emptyCells = 40; // Adjust the number of empty cells for difficulty
        while (emptyCells > 0) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (board[row][col] != EMPTY_CELL) {
                board[row][col] = EMPTY_CELL;
                emptyCells--;
            }
        }

        return board;
    }

    public static boolean solveBoard(int[][] board) {
        int row = 0;
        int col = 0;


        boolean isEmpty = false;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == EMPTY_CELL) {
                    row = i;
                    col = j;
                    isEmpty = true;
                    break;
                }
            }
            if (isEmpty) {
                break;
            }
        }


        if (!isEmpty) {
            return true;
        }

        // Try each digit from 1 to 9
        for (int num = 1; num <= SIZE; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                if (solveBoard(board)) {
                    return true;
                }

                board[row][col] = EMPTY_CELL;
            }
        }


        return false;
    }

    public static boolean isValid(int[][] board, int row, int col, int num) {
        // Check if the number already exists in the row
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }

        // Check if the number already exists in the column
        for (int i = 0; i < SIZE; i++) {
            if (board[i][col] == num) {
                return false;
            }
        }

        // Check if the number already exists in the 3x3 grid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("---------------------");
            }
            for (int j = 0; j < SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                if (board[i][j] == EMPTY_CELL) {
                    System.out.print("_ ");
                } else {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}

