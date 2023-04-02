package com.wangxt.algorismus.code.jc;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LinkedList {

    @Getter
    @Setter
    @NoArgsConstructor
    static class SingleNode {
        private int value;
        private SingleNode next;

        public SingleNode(int value) {
            this.value = value;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    static class DoubleNode {
        private int value;
        private DoubleNode last;
        private DoubleNode next;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        SingleNode node = new SingleNode(1);
        node.next = new SingleNode(2);
        node.next.next = new SingleNode(3);
        print(node);
        SingleNode res = reverseSort(node);
        print(res);
        System.out.println("---------------");

        DoubleNode doubleNode = new DoubleNode(1);
        DoubleNode doubleNode1 = new DoubleNode(2);
        DoubleNode doubleNode2 = new DoubleNode(3);
        DoubleNode doubleNode3 = new DoubleNode(4);

        doubleNode.next = doubleNode1;
        doubleNode.last = null;

        doubleNode1.last = doubleNode;
        doubleNode1.next = doubleNode2;

        doubleNode2.last = doubleNode1;
        doubleNode2.next = doubleNode3;

        doubleNode3.last = doubleNode2;
        doubleNode3.next = null;
        print(doubleNode);
        DoubleNode result = reverseDoubleSort(doubleNode);
        print(result);
        System.out.println("-----------------------");

        Collection<Integer> queue = new SingleQueue<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);

        System.out.println(queue.size());
        System.out.println(queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.peek());
        System.out.println("-------------------------");

        Collection<Integer> stack = new SingleStack<>();
        stack.offer(1);
        stack.offer(2);
        stack.offer(3);

        System.out.println(stack.size());
        System.out.println(stack.peek());
        System.out.println(stack.poll());
        System.out.println(stack.peek());
    }

    /**
     * 单链表逆序
     */
    private static SingleNode reverseSort(SingleNode head) {
        SingleNode pre = null;
        SingleNode next = null;
        while (head != null) {
            // 先将 head 的 next 抓住
            next = head.next;
            // 然后将 head 的 next 指向 pre,这时候 head 和 next 没有任何指向（循环到第二次将）
            head.next = pre;
            // 然后将 pre 往后移，叫 pre 指向 head
            pre = head;
            // head 也往后移，这时 head 指向 第一步抓到的 next,往后循环
            head = next;
        }

        return pre;
    }

    /**
     * 双链表逆序
     */
    private static DoubleNode reverseDoubleSort(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            // 一样，还是先将 head 的 next 抓住
            next = head.next;
            // 然后将 head 的 next 指向 pre
            head.next = pre;
            // 将 head 的 last 指向 next
            head.last = next;
            pre = head;
            head = next;
        }

        return pre;
    }

    private static void print(SingleNode head) {
        while (head != null) {
            System.out.print(head.value + " , ");
            head = head.next;
        }
        System.out.println();
    }

    private static void print(DoubleNode head) {
        while (head != null) {
            System.out.print(head.value + " , ");
            head = head.next;
        }
        System.out.println();
    }

    @Getter
    @Setter
    static class SingleStackNode<T> {
        private T value;
        private SingleStackNode<T> next;

        public SingleStackNode(T value) {
            this.value = value;
        }
    }
}


class SingleQueue<T> implements Collection<T> {
    // 记录 head ，因为出队需要从 head 往外出
    private LinkedList.SingleStackNode<T> head;
    // 记录 tail ，因为添加元素需要从 tail 添加
    private LinkedList.SingleStackNode<T> tail;

    private int size;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        if (head != null) {
            return head.getValue();
        }
        return null;
    }

    @Override
    public T poll() {
        T t = null;
        if (head != null) {
            size--;
            t = head.getValue();
            head = head.getNext();
        }
        // 如果 head 来到空了，说明队列里已经没有值了，那么将 tail 置为空
        if (head == null) {
            tail = null;
        }
        return t;
    }

    @Override
    public void offer(T value) {
        LinkedList.SingleStackNode<T> node = new LinkedList.SingleStackNode<>(value);
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
    }
}

class SingleStack<T> implements Collection<T> {
    private int size;
    private LinkedList.SingleStackNode<T> head;

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.getValue();
    }

    @Override
    public T poll() {
        if (head == null) {
            return null;
        }
        T t = head.getValue();
        head = head.getNext();
        size--;
        return t;
    }

    @Override
    public void offer(T value) {
        LinkedList.SingleStackNode<T> node = new LinkedList.SingleStackNode<>(value);
        if (head == null) {
            head = node;
        } else {
            node.setNext(head);
            head = node;
        }
        size++;
    }
}

interface Collection<T> {

    boolean isEmpty();

    int size();

    T peek();

    T poll();

    void offer(T value);
}

















































