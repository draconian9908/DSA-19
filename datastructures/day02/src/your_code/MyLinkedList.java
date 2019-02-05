package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    // Start of (most of) the code I've edited

    public void addLast(Chicken c) {
        if (size == 0) {
            head = new Node(c);
            tail = head;
        }
        else {
            Node temp = new Node(c);
            tail.next = temp;
            temp.prev = tail;
            tail = temp;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        if (size == 0) {
            head = new Node (c);
            tail = head;
        }
        else {
            Node temp = new Node(c);
            head.prev = temp;
            temp.next = head;
            head = temp;
        }
        size++;
    }

    public Chicken get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        else if (index == 0) {
            return head.val;
        }
        else if (index == size-1) {
            return tail.val;
        }
        int count = 0;
        Node curr = head;
        while (count != index) {
            curr = curr.next;
            count++;

        }
        return curr.val;
    }

    public Chicken remove(int index) {
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            Chicken c = head.val;
            head = null;
            tail = null;
            return c;
        }
        else {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            } else if (index == 0) {
                Chicken c = head.val;
                head = head.next;
                head.prev = null;
                size--;
                return c;
            } else if (index == size - 1) {
                Chicken c = tail.val;
                tail = tail.prev;
                tail.next = null;
                size--;
                return c;
            }
            int count = 0;
            Node curr = head;
            while (count != index) {
                curr = curr.next;
                count++;

            }
            Chicken c = curr.val;
            curr.prev.next = curr.next;
            curr.next.prev = curr.prev;
            size--;
            return c;
        }
    }

    public Chicken removeFirst() {
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            Chicken c = head.val;
            head = null;
            tail = null;
            size--;
            return c;
        }
        else {
            Chicken c = head.val;
            head = head.next;
            head.prev = null;
            size--;
            return c;
        }
    }

    public Chicken removeLast() {
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            Chicken c = tail.val;
            head = null;
            tail = null;
            size--;
            return c;
        }
        else {
            Chicken c = tail.val;
            tail = tail.prev;
            tail.next = null;
            size--;
            return c;
        }
    }
}