package RecPrac;

import java.util.Arrays;

@SuppressWarnings("InitializerMayBeStatic")
public class SudokoSlover {

    public static void main(String[] args) {
        char board[][] = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };
        sudokoSlover(board);
        for (char ch[] : board) {
            System.out.println(Arrays.toString(ch));
        }
    }

    // Time complexity:O(9*(n*m)).
    // Space complexity:O(n*m).
    public static void sudokoSlover(char board[][]) {
        helper(0, 0, board);
    }

    public static boolean helper(int row, int col, char board[][]) {
        if (row == board.length) {
            return true;
        }
        if (col == board[0].length) {
            return helper(row + 1, 0, board);
        }
        if (board[row][col] != '.') {
            return helper(row, col + 1, board);
        } else {
            for (int num = 1; num <= 9; num++) {
                if (check(row, col, num, board)) {
                    board[row][col] = (char) (num + '0');
                    if (helper(row, col + 1, board)) {
                        return true;
                    }
                    board[row][col] = '.';
                }

            }
        }
        return false;
    }

    public static boolean check(int row, int col, int num, char board[][]) {
        // checking row and coloumn.
        for (int i = 0; i < 9; i++) {
            if (board[i][col] - '0' == num || board[row][i] - '0' == num) {
                return false;
            }
        }
        // checking with in the matrix.
        int rowstart = (row) / 3;
        int colstar = (col) / 3;
        rowstart *= 3;
        colstar *= 3;
        for (int rs = rowstart; rs < rowstart + 3; rs++) {
            for (int cs = colstar; cs < colstar + 3; cs++) {
                if (num == board[rs][cs] - '0') {
                    return false;
                }
            }
        }
        return true;

    }
}
