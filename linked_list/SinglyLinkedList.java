package org.example.lindkedlist;

import java.util.Objects;

public class SinglyLinkedList<T> {
  
    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node(T data) {
            this.data = data;
        }

        public Node() {
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public void setPrev(Node<T> prev) {
            this.prev = prev;
        }
    }

    private Node<T> first;
    private Node<T> last;
    private int size;

    public SinglyLinkedList() {
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return Objects.isNull(first);
    }

    private void addFirstNode(T data) {
        last = first = new Node<>(data);
    }

    void addFirst(T data) {
        ++size;
        if (isEmpty()) {
            addFirstNode(data);
        } else {
            final Node<T> node = new Node<>(data);
            node.setNext(first);
            first = node;
        }
    }

    void addLast(T data) {
        ++size;
        if (isEmpty()) {
            addFirstNode(data);
        } else {
            final Node<T> node = new Node<>(data);
            last.setNext(node);
            last = node;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("List size : " + size + ", index : " + index);
        }
        int count = 0;
        Node<T> node = first;
        for (; count < index; ++count, node = node.getNext()) ;
        return node.getData();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (isEmpty()) {
            builder.append("[]");
        } else {
            builder.append('[');
            for (Node<T> node = first; node != null; node = node.getNext()) {
                builder.append(node.getData());
                if (node != last) {
                    builder.append(", ");
                }
            }
            builder.append(']');
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();

        linkedList.addFirst(1);
        System.out.println(linkedList.get(0)); // 1
        linkedList.addFirst(2);
        System.out.println(linkedList.get(0)); // 2
        System.out.println(linkedList.get(1)); // 1
        linkedList.addFirst(3);
        System.out.println(linkedList.get(0)); // 3
        System.out.println(linkedList.get(1)); // 2
        System.out.println(linkedList.get(2)); // 1

        linkedList.addLast(4);
        System.out.println(linkedList.get(0)); // 3
        System.out.println(linkedList.get(1)); // 2
        System.out.println(linkedList.get(2)); // 1
        System.out.println(linkedList.get(3)); // 4
        
        System.out.println();
        System.out.println(linkedList); // [3, 2, 1, 4]
        
    }
}
