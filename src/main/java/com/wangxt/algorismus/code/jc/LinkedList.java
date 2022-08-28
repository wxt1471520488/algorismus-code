package com.wangxt.algorismus.code.jc;

import lombok.Getter;
import lombok.Setter;

public class LinkedList {

    @Getter
    @Setter
    static class Node {
        private Integer value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    @Getter
    @Setter
    static class DoubleNode {
        private Integer value;
        private DoubleNode next;
        private DoubleNode last;

        public DoubleNode(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);

        print(n1);
        Node revert = singleRevert(n1);
        print(revert);
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    private static Node singleRevert(Node head) {
        Node pre = null;
        Node next = null;
        Node cur = head;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }

    private static DoubleNode doubleRevert(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }

        return pre;
    }

    ////////////////////////////////////// 单链表实现栈和队列
    @Getter
    @Setter
    public static class StackAndQueueNode<V> {
        private V value;
        private StackAndQueueNode<V> next;

        public StackAndQueueNode(V value) {
            this.value = value;
        }
    }

    public static class MySingleNodeQueue<V> {
        private StackAndQueueNode<V> head;
        private StackAndQueueNode<V> tail;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(V value) {
            StackAndQueueNode<V> cur = new StackAndQueueNode<>(value);
            if (tail == null) {
                head = cur;
                tail = cur;
            } else {
                // 从尾部加
                tail.next = cur;
                // node之间关联起来
                tail = cur;
            }
            size++;
        }

        public V poll() {
            // 从头部取
            if (head == null) {
                tail = null;
                return null;
            }

            V res = head.value;
            head = head.next;
            size--;
            return res;
        }

        public V peek() {
            return head == null ? null : head.value;
        }
    }


    public static class MySingleNodeStack<V> {
        private StackAndQueueNode<V> head;
        private int size;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void offer(V value) {
            StackAndQueueNode<V> cur = new StackAndQueueNode<>(value);
            cur.next = head;
            head = cur;
            size++;
        }

        public V poll() {
            if (head == null) {
                return null;
            }

            V res = head.value;
            head = head.next;
            size--;
            return res;
        }

        public V peek() {
            if (head == null) {
                return null;
            }
            return head.value;
        }
    }

    //////////////////////// 双链表实现双端队列
    @Getter
    @Setter
    public static class DoubleStackAndQueueNode<V> {
        private V value;
        private DoubleStackAndQueueNode<V> next;
        private DoubleStackAndQueueNode<V> last;

        public DoubleStackAndQueueNode(V value) {
            this.value = value;
        }
    }

    public static class MyDoubleQueue<V> {
        private int size;
        private DoubleStackAndQueueNode<V> head;
        private DoubleStackAndQueueNode<V> tail;

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public void pushHead(V value) {
            DoubleStackAndQueueNode<V> cur = new DoubleStackAndQueueNode<>(value);
            cur.next = head;
            head.last = cur;
            head = cur;
            size++;
        }

        public void pushTail(V value) {
            DoubleStackAndQueueNode<V> cur = new DoubleStackAndQueueNode<>(value);
            tail.next = cur;
            cur.last = tail;
            tail = cur;
            size++;
        }

        public V popHead() {
            if (head == null) {

            }

            if (head == tail) {

            }

            return null;
        }

        public V popTail() {
            return null;
        }

        public V peekHead() {
            return null;
        }

        public V peekTail() {
            return null;
        }
    }

    ///////////////////////////// k个节点的组内逆序调整
    // https://leetcode.cn/problems/reverse-nodes-in-k-group/submissions/
    // 截取链表固定长度，并返回尾节点
    private Node split(Node head, int len) {
        while (--len > 0 && head != null) {
            head = head.next;
        }

        return head;
    }

    private void revert(Node start, Node end) {
        end = end.next;
        Node pre = null;
        Node next = null;
        while (start.next != null) {
            next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        start.next = end;
    }

    private Node groupAndSort(Node head, int len) {
        Node start = head;
        Node end = split(start, len);
        if (end == null) {
            return head;
        }

        head = end;
        revert(start, end);
        Node listEnd = start;
        while (listEnd.next != null) {
            start = listEnd.next;
            end = split(start, len);
            if (end == null) {
                return head;
            }

            revert(start, end);
            listEnd.next = end;
            listEnd = start;
        }

        return head;
    }
}















































