package com.wangxt.algorismus.code.jc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class JC_6_LinkedList {

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
        // 总体思想就是从头开始，一个个往外摘，为了把摘出来的节点抓住，所以先定义一个pre，实际是作为新的head
        SingleNode pre = null;
        // 节点被一个个往外摘，那剩下的节点也得抓住，不然就丢在内存里了
        SingleNode next = null;
        while (head != null) {
            // 先将 head 的 next 抓住，这样就可以随便玩head了
            next = head.next;
            // 开始转置，head原来指向next，转置后指向pre
            head.next = pre;
            // 当前节点转置完了，得开始遍历下一个，但是得先把当前的节点抓住，所以叫pre把head抓住，这时相当于左手是新的head，右手是剩余的节点了
            pre = head;
            // 然后开始右手倒左手，遍历下一个节点
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
            next = head.next;
            // 和单向链表转置逻辑一样，只不过双向链表需要同时转置前后指针
            head.next = pre;
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
    private JC_6_LinkedList.SingleStackNode<T> head;
    // 记录 tail ，因为添加元素需要从 tail 添加
    private JC_6_LinkedList.SingleStackNode<T> tail;

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
        JC_6_LinkedList.SingleStackNode<T> node = new JC_6_LinkedList.SingleStackNode<>(value);
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
    private JC_6_LinkedList.SingleStackNode<T> head;

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
        JC_6_LinkedList.SingleStackNode<T> node = new JC_6_LinkedList.SingleStackNode<>(value);
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

















































