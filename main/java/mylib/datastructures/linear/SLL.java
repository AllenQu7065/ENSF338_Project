package main.java.mylib.datastructures.linear;

import java.util.NoSuchElementException;

import main.java.mylib.datastructures.nodes.DNode;

public class SLL {
    public DNode head;
    public DNode tail;
    public int size;
    public boolean sorted = false;

    public SLL() {
        head = null;
        tail = null;
        size = 0;
    }

    public SLL(DNode headNode) {
        head = headNode;
        tail = headNode;
        size = 1;
    }

    public void insertHead(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            node.setNext(head);
            head = node;
        }
        size++;
        sorted = false;
    }

    public void insertTail(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
        size++;
        sorted = false;
    }

    public void insert(DNode node, int position) {
        if (position < 1 || position > size + 1) {
        	throw new IndexOutOfBoundsException();
        }
        if (position == 1) {
            insertHead(node);
            return;
        }
        if (position == size + 1) {
            insertTail(node);
            return;
        }
        DNode current = head;
        int count = 1;
        while (count < position - 1) {
            current = current.getNext();
            count++;
        }
        node.setNext(current.getNext());
        current.setNext(node);
        size++;
        sorted = false;
    }
    
    public void sortedInsert(DNode node) {
        if (!sorted) {
            sort();
        }
        DNode current = head;
        DNode prev = null;
        while (current != null && current.getData() < node.getData()) {
            prev = current;
            current = current.getNext();
        }
        if (prev == null) {
            insertHead(node);
        } else {
            node.setNext(current);
            prev.setNext(node);
            size++;
        }
    }

    
    public DNode search(DNode node) {
    	DNode current = head;
        while (current != null) {
            if (current == node) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    
    public void deleteHead() {
        if (head == null) {
        	throw new NoSuchElementException();
        }
        head = head.getNext();
        size--;
    }

    
    public void deleteTail() {
        if (head == null) {
        	throw new NoSuchElementException();
        }
        if (head.getNext() == null) {
            head = null;
            tail = null;
            size--;
            return;
        }
        DNode current = head;
        while (current.getNext() != tail) {
            current = current.getNext();
        }
        current.setNext(null);
        tail = current;
        size--;
    }
    
    public void delete(DNode node) {
        if (head == null) {
        	throw new NoSuchElementException();
        }
        if (head == node) {
            deleteHead();
            return;
        }
        DNode current = head;
        DNode prev = null;
        while (current != null) {
            if (current == node) {
                prev.setNext(current.getNext());
                size--;
                return;
            }
            prev = current;
            current = current.getNext();
        }
        throw new NoSuchElementException();
    }
    
    public void sort() {
        if (head == null || head.getNext() == null) {
            return; // List is already sorted
        }

        DNode sortedList = null;
        DNode unsortedList = head;

        while (unsortedList != null) {
        	DNode node = unsortedList;
            unsortedList = unsortedList.getNext();

            if (sortedList == null || node.getData() < sortedList.getData()) {
                node.setNext(sortedList);
                sortedList = node;
            } else {
            	DNode current = sortedList;
                while (current.getNext() != null && node.getData() >= current.getNext().getData()) {
                    current = current.getNext();
                }
                node.setNext(current.getNext());
                current.setNext(node);
            }
        }

        head = sortedList;
        tail = sortedList;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
        sorted = true;
    }
    
    public void clear() {
        head = null;
        tail = null;
        size = 0;
        sorted = false;
    }
    
    
    public void print() {
        System.out.println("List length: " + size);

        if (sorted) {
            System.out.println("Sorted: Yes");
        } else {
            System.out.println("Sorted: No");
        }

        if (head == null) {
            System.out.println("List is empty");
        } else {
            System.out.println("List content:");

            DNode current = head;
            while (current != null) {
                System.out.print(current.getData() + " ");
                current = current.getNext();
            }
            System.out.println();
        }
    }
}