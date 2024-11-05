package RecPrac;

public class WordSearch {
    public static void main(String[] args) {
        char board[][] = { { 'C', 'A', 'A' }, { 'A', 'A', 'A' }, { 'B', 'C', 'D' } };
        String word = "AAB";
        System.out.println(wordSearching(board, word));
    }

    // Time complexity:O(n*n)+O(4*m) --> n board ,m word
    // Space complexity:O(n*m) --> visited array
    public static boolean wordSearching(char board[][], String word) {
        boolean visited[][] = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (helper(board, visited, word, i, j, 1)) {
                        return true;
                    }
                    visited[i][j] = false;
                }
            }
        }
        return false;
    }

    public static boolean helper(char board[][], boolean visited[][], String word, int row, int col, int wordIndex) {
        if (wordIndex == word.length()) {
            return true;
        }
        visited[row][col] = true;
        int rowar[] = { 0, 1, 0, -1 };
        int colar[] = { 1, 0, -1, 0 };
        for (int i = 0; i < 4; i++) {
            if (row + rowar[i] < board.length && row + rowar[i] >= 0 && col + colar[i] < board[0].length
                    && col + colar[i] >= 0 &&
                    board[row + rowar[i]][col + colar[i]] == word.charAt(wordIndex)
                    && !visited[row + rowar[i]][col + colar[i]]) {

                if (helper(board, visited, word, row + rowar[i], col + colar[i], wordIndex + 1)) {
                    return true;
                }

                visited[row + rowar[i]][col + colar[i]] = false;
            }
        }
        return false;
    }

}
