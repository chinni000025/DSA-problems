package RecPrac;

import java.util.Stack;

public class StackSorting {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(11);
        st.push(2);
        st.push(32);
        st.push(3);
        st.push(41);
        System.out.println(st);
        // System.out.println(SsDriver(st, new Stack<>()));
        SsDriver2(st);
        System.out.println(st);

    }

    // Time complexity:O(n*n). n==> number of elements in the stack.
    // Space complexity:O(n*n) --> extra stack space +Auxillary stack space.
    public static Stack<Integer> SsDriver(Stack<Integer> st, Stack<Integer> res) {
        if (st.isEmpty()) {
            return res;
        }
        sorting(st.pop(), res);
        SsDriver(st, res);
        return res;
    }

    public static void sorting(int num, Stack<Integer> res) {
        if (res.isEmpty() || res.peek() < num) {
            res.push(num);
            return;
        }
        int temp = res.pop();
        sorting(num, res);
        res.push(temp);
    }

    // Optimize the space complexity.
    // Time complexity:O(n*n). n==> number of elements in the stack.
    // Space complexity:O(1) --> extra stack space +Auxillary stack space.
    public static void SsDriver2(Stack<Integer> st) {
        if (st.isEmpty()) {
            return;
        }
        int ele = st.pop();
        SsDriver2(st);
        sorting2(st, ele);

    }

    public static void sorting2(Stack<Integer> st, int ele) {
        if (st.isEmpty() || st.peek() < ele) {
            st.push(ele);
            return;
        }
        int temp = st.pop();
        sorting2(st, ele);
        st.push(temp);
    }

}
