package RecPrac;

import java.util.Queue;
import java.util.Stack;

public class ReverseAStack {
    public static void main(String[] args) {
        Stack<Integer> st = new Stack<>();
        st.push(11);
        st.push(2);
        st.push(32);
        st.push(3);
        st.push(41);
        System.out.println(st);
        iterative(st);
        System.out.println(st);

    }

    public static void reversing(Stack<Integer> st, Queue<Integer> temp) {
        if (!st.isEmpty()) {
            temp.add(st.pop());
            reversing(st, temp);
            st.push(temp.poll());
        }
    }

    // Reverse a stack using a optimal approach.
    // Time complexity:O(n*n).
    // Space complexity:O(1).
    // Auxillary stack space:O(n).
    public static void iterative(Stack<Integer> st) {
        if (st.isEmpty())
            return;
        int temp = st.pop();
        iterative(st);
        insertAtBottom(st, temp);

    }

    public static void insertAtBottom(Stack<Integer> st, int ele) {
        if (st.isEmpty()) {
            st.push(ele);
            return;
        }
        int temp = st.pop();
        insertAtBottom(st, ele);
        st.push(temp);

    }

}

