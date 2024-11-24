package DP;

import java.util.Stack;

public class MaximalRectangle {
    public static void main(String[] args) {
        char mat[][] = { { '1', '0', '1', '0', '0' }, { '1', '0', '1', '1', '1' }, { '1', '1', '1', '1', '1' },
                { '1', '0', '0', '1', '0' } };
        System.out.println(maxRectangle(mat));
    }

    public static int maxRectangle(char mat[][]) {
        int res[][] = new int[mat.length][mat[0].length];
        for (int col = 0; col < mat[0].length; col++) {
            int count = 0;
            for (int row = 0; row < mat.length; row++) {
                if (mat[row][col] == '1') {
                    count++;
                    res[row][col] = count;
                } else {
                    count = 0;
                    res[row][col] = count;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < res.length; i++) {
            max = Math.max(max, histogram(res[i]));
        }
        return max;
    }

    public static int histogram(int ar[]) {
        int total = 0;
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < ar.length; i++) {
            while (!st.isEmpty() && ar[i] < ar[st.peek()]) {
                int nse = i;
                int ele = st.pop();
                int pse = st.isEmpty() ? -1 : st.peek();
                total = Math.max(total, ar[ele] * (nse - pse - 1));
            }
            st.push(i);
        }
        while (!st.isEmpty()) {
            int nse = ar.length;
            int ele = st.pop();
            int pse = st.isEmpty() ? -1 : st.peek();
            total = Math.max(total, ar[ele] * (nse - pse - 1));
        }
        return total;
    }
}
