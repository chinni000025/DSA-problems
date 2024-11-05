package RecPrac;

import java.util.ArrayList;

public class RatInMaze {
    public static void main(String[] args) {
        int mat[][] = { { 1, 0, 0, 0 },
                { 1, 1, 0, 1 },
                { 1, 1, 0, 0 },
                { 0, 1, 1, 1 }
        };
        ratMaze(mat);
    }
    // Time complexity:O(4*n^2).
    // Space complexity:O(m*n).
    public static ArrayList<String> ratMaze(int mat[][]) {
        ArrayList<String> res = new ArrayList<>();
        if (mat[0][0] == 0) {
            return res;
        }
        boolean visited[][] = new boolean[mat.length][mat[0].length];
        helper(mat, visited, 0, 0, "", res);
        return res;
    }

    public static void helper(int mat[][], boolean visited[][], int row, int col, String str, ArrayList<String> res) {
        if (row == mat.length - 1 && col == mat[0].length - 1) {
            res.add(str);
            return;
        }
        visited[row][col] = true;
        int rowar[] = { 0, 1, 0, -1 };
        int colar[] = { 1, 0, -1, 0 };
        for (int i = 0; i < 4; i++) {
            if (row + rowar[i] < mat.length && row + rowar[i] >= 0 && col + colar[i] < mat[0].length
                    && col + colar[i] >= 0 &&
                    mat[row + rowar[i]][col + colar[i]] == 1 && !visited[row + rowar[i]][col + colar[i]]) {
                if (i == 0) {
                    str += "R";
                } else if (i == 1) {
                    str += "D";
                } else if (i == 2) {
                    str += "L";
                } else {
                    str += "U";
                }
                helper(mat, visited, row + rowar[i], col + colar[i], str, res);
                visited[row + rowar[i]][col + colar[i]] = false;
                str = str.substring(0, str.length() - 1);
            }
        }
    }
}