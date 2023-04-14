package main.java.mylib.datastructures.linear;

import java.util.NoSuchElementException;

import main.java.mylib.datastructures.nodes.DNode;

public class DLL {
    public DNode head;
    public DNode tail;
    public int size;
    public Boolean sorted;

    public DLL() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public DLL(DNode node) {
        this.head = node;
        this.tail = node;
        this.size = 1;
    }

    public void insertHead(DNode node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            head.setPrev(node);
            node.setNext(head);
            head = node;
        }
        size++;
        sorted = false;
    }

    // Insert node at tail of the list
    public void insertTail(DNode node) {
        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            node.setPrev(tail);
            tail = node;
        }
        size++;
        sorted = false;
    }

 // Insert node in the specified position
    public void insert(DNode node, int position) {
        if (position < 1 || position > size + 1) {
        	throw new IndexOutOfBoundsException();
        }
        if (position == 1) {
            insertHead(node);
        } else if (position == size + 1) {
            insertTail(node);
        } else {
            DNode current = head;
            for (int i = 1; i < position; i++) {
                current = current.getNext();
            }
            node.setNext(current);
            node.setPrev(current.getPrev());
            current.getPrev().setNext(node);
            current.setPrev(node);
            size++;
        }
        sorted = false;
    }

    // Inserts node object in its proper position in a sorted list
    // Must check for list sort validity
    // If list is found to be out of order, it must call the sort function first before inserting
    public void sortedInsert(DNode node) {
    	if (!sorted) {
            sort();
        }
        if (head == null || node.getData() <= head.getData()) {
            insertHead(node);
        } else if (node.getData() >= tail.getData()) {
            insertTail(node);
        } else {
            DNode current = head.getNext();
            while (current != null && current.getData() < node.getData()) {
                current = current.getNext();
            }
            node.setPrev(current.getPrev());
            node.setNext(current);
            current.getPrev().setNext(node);
            current.setPrev(node);
            size++;
        }
    }

    // Looks up node in the list
    // If found it returns the object, otherwise returns null
    public DNode search(DNode node) {
        DNode current = head;
        int count = 0;
        while (current != null && count <= size) {
            if (current == node) {
                return current;
            }
            current = current.getNext();
            count++;
        }
        return null;
    }

    // Delete head node
    public void deleteHead() {
        if (head == null) {
        	throw new NoSuchElementException();
        } else if (head == tail) {
            head = null;
            tail = null;
        } else {
            head.getNext().setPrev(null);
            head = head.getNext();
        }
        size--;
    }
    
    public void deleteTail() {
        if (tail == null) {
        	throw new NoSuchElementException();
        }
        if (size == 1) {
            head = null;
            tail = null;
            size = 0;
            return;
        }else {
		    tail = tail.getPrev();
		    tail.setNext(null);
		    size--;
        }
    }
    
    public void delete(DNode node) {
        if (head == null) { // List is empty
            return;
        }
        if (head == node) { // Node to delete is the head
            head = head.getNext();
            if (head != null) {
                head.setPrev(null);
            } else {
                tail = null;
            }
            size--;
            return;
        }
        if (tail == node) { // Node to delete is the tail
            tail = tail.getPrev();
            tail.setNext(null);
            size--;
            return;
        }
        // Node to delete is in the middle
        DNode current = head.getNext();
        while (current != null) {
            if (current == node) {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                size--;
                return;
            }
            current = current.getNext();
        }
    }
    
    public void sort() {
        if (head == null || head == tail) {
            // list is empty or has only one node
            return;
        }

        DNode current = head.getNext(); // start from the second node
        while (current != null) {
            DNode nodeToInsert = current;
            current = current.getNext();
            
            // find the proper position to insert nodeToInsert
            DNode prev = nodeToInsert.getPrev();
            while (prev != null && nodeToInsert.getData() < prev.getData()) {
                prev = prev.getPrev();
            }
            DNode next = nodeToInsert.getNext();
            prev = nodeToInsert.getPrev();
            prev.setNext(next);
            if (next != null) {
                next.setPrev(prev);
            } else {
                tail = prev;
            }
            DNode nextNode = prev.getNext();
			prev.setNext(nodeToInsert);
			nodeToInsert.setPrev(prev);
			nodeToInsert.setNext(nextNode);
			if (nextNode != null) {
			    nextNode.setPrev(nodeToInsert);
			} else {
			    tail = nodeToInsert;
			}
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
        // Print the length of the list
        System.out.println("List Length: " + size);

        // Print the sorted status of the list
        if (sorted) {
            System.out.println("List is sorted.");
        } else {
            System.out.println("List is not sorted.");
        }

        // Print the contents of the list
        System.out.print("List Contents: ");
        DNode currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.getData() + " ");
            currentNode = currentNode.getNext();
        }
        System.out.println();
    }
    
}