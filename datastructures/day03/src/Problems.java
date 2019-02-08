import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        Stack<Integer> s = new Stack<>();
        List<Integer> l = new LinkedList<>();
        int rem = A.length-k;
        int temp;
        if (rem == 0) {
            l.add(0);
            return l;
        }
        for (int i=0; i<A.length; i++) {
            if (k>0) {
                if (i == 0) {
                    s.push(A[i]);
                }
                else {
                    while (!s.isEmpty() && k>0 && A[i] < s.peek()) {
                        s.pop();
                        k--;
                    }
                    s.push(A[i]);
                }
            }
            else {
                s.push(A[i]);
            }
        }
        while (s.size() > rem) {
            temp = s.pop();
            if (temp < s.peek()) {
                s.pop();
                s.push(temp);
            }
        }
        l.addAll(s);
        return l;
    }




    public static boolean isPalindrome(Node n) {
        Node curr = n;
        int size = 0;
        int midI;
        int i = 0;
        if (curr == null) {
            size = 0;
        }
        else {
            while (curr.next != null) {
                size++;
                curr = curr.next;
            }
            size++;
        }
        if (size == 1 || size == 0) {
            return true;
        }
        if ((size % 2) == 0) {
            midI = size/2 - 1;
        }
        else {
            midI = size/2;
        }
        curr = n;
        while (i < midI) {
            curr = curr.next;
            i++;
        }
        Node midN = curr;
        Node prev = null;
        curr = curr.next;
        Node next;
        while (curr.next != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        curr.next = prev;
        midN.next = curr;
        Node curr2 = n;
        i = 0;
        while (i < midI) {
            if (curr.val == curr2.val) {
                curr = curr.next;
                curr2 = curr2.next;
                i++;
            }
            else {
                return false;
            }
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        Stack<String> ss = new Stack<>();
        Stack<String> so = new Stack<>();
        StringBuilder fin = new StringBuilder();
        Character test;
        for (int i = 0; i < s.length(); i++) {
            test = s.charAt(i);
            if (Character.isDigit(test)) {
                ss.push(Character.toString(test));
                }
            else if (test=='+' || test=='-' || test=='*' || test=='/') {
                so.push(Character.toString(test));
            }
            else if (test==')') {
                while (!so.isEmpty()) {
                    ss.push(so.pop());
                }
            }
        }
        while (!ss.isEmpty()) {
            fin.append(ss.pop());
            fin.append(' ');
        }
        fin.reverse();
        fin.deleteCharAt(0);
        return fin.toString();
    }

}
