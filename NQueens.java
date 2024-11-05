package RecPrac;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {

    public static void main(String[] args) {
        int n = 4;
        nQueens(n);
    }

    // Time complexity:O(n!).
    // Space complexity:O(n*n).
    public static void nQueens(int n) {
        char ch[][] = new char[n][n];
        for (char temp[] : ch) {
            Arrays.fill(temp, '.');
        }
        List<List<String>> res = new ArrayList<>();
        helper(0, ch, res);
        System.out.println(res);
    }

    public static void helper(int row, char ch[][], List<List<String>> res) {
        if (row == ch.length) {
            List<String> temp = new ArrayList<>();
            for (char c[] : ch) {
                temp.add(new String(c));
            }
            res.add(temp);
            return;
        }

        for (int col = 0; col < ch.length; col++) {
            if (isValid(row, col, ch)) {
                ch[row][col] = 'Q';
                helper(row + 1, ch, res);
                ch[row][col] = '.';
            }
        }
    }

    public static boolean isValid(int row, int col, char ch[][]) {
        int i = row, j = col;
        while (i >= 0 && j >= 0) {
            if (ch[i][j] == 'Q') {
                return false;
            }
            i--;
            j--;
        }
        i = row;
        j = col;
        while (i >= 0) {
            if (ch[i][j] == 'Q') {
                return false;
            }
            i--;
        }
        i = row;
        j = col;
        while (j < ch.length && i >= 0) {
            if (ch[i][j] == 'Q') {
                return false;
            }
            i--;
            j++;
        }
        return true;
    }
}