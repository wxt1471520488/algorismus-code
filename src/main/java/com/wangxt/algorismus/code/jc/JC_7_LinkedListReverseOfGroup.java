package com.wangxt.algorismus.code.jc;

import lombok.Getter;
import lombok.Setter;

public class JC_7_LinkedListReverseOfGroup {

    @Getter
    @Setter
    public static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node splitAndReturnEndNode(Node head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        Node end = null;
        for (int i = 0; i < n; i++) {
            if (head != null) {
                end = head;
                head = head.next;
            }
        }

        return end;
    }

    public static void reverse(Node head, Node end) {
        end = end.next;

        Node pre = null;
        Node start = head;
        while (head != end) {
            Node next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        start.next = end;
    }

    public static Node reverseInGroup(Node head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        Node start = head;

        // 先看看能不能凑成一组，如果不能直接返回head
        Node end = splitAndReturnEndNode(start, n);
        if (end == null) {
            return head;
        }

        // 说明可以凑成一组，那么先逆序当前组
        reverse(start, end);
        // 逆序完的头节点一定时结果head,先抓住
        Node result = end;
        // 记一下上轮的尾节点
        Node lastEnd = start;
        // 开始循环
        while (lastEnd.next != null) {
            start = lastEnd.next;
            end = splitAndReturnEndNode(start, n);
            if (end == null) {
                return result;
            }

            reverse(start, end);

            lastEnd.next = end;
            lastEnd = start;
        }

        return result;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        n1.next = new Node(2);
        n1.next.next = new Node(3);
        n1.next.next.next = new Node(4);
        n1.next.next.next.next = new Node(5);
        n1.next.next.next.next.next = new Node(6);
        //n1.next.next.next.next.next.next = new Node(7);

        Node node = reverseInGroup(n1, 3);
        print(node);
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.value + " , ");
            head = head.next;
        }
        System.out.println();
    }
}
