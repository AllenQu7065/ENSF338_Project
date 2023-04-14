package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class CSLL extends SLL {
    
	public CSLL() {
        super();
    }

    public CSLL(DNode headNode) {
        super(headNode);
        head.setNext(head);
        tail.setNext(head);
    }

    @Override
    public void insertHead(DNode node) {
        super.insertHead(node);
        tail.setNext(head);
    }

    @Override
    public void insertTail(DNode node) {
        super.insertTail(node);
        tail.setNext(head);
    }

    @Override
    public void insert(DNode node, int position) {
        super.insert(node, position);
        tail.setNext(head);
    }
    @Override
    public void sortedInsert(DNode node) {
        if (!sorted) {
            super.sortedInsert(node);
            head.setNext(head); // make the list circular
            tail = head.getNext(); // update the tail pointer
            return;
        }

        DNode current = head;
        DNode prev = null;
        while (current != tail && current.getData() < node.getData()) {
            prev = current;
            current = current.getNext();
        }

        if (current == tail && current.getData() < node.getData()) {
            // insert after tail
            current.setNext(node);
            node.setNext(head);
            tail = node;
        } else {
            // insert before current
            node.setNext(current);
            if (prev == null) {
                head = node;
            } else {
                prev.setNext(node);
            }
        }

        size++;
    }

    @Override
    public DNode search(DNode node) {
        if (node == null) {
            return null;
        }

        DNode current = head;
        do {
            if (current == node) {
                return current;
            }
            current = current.getNext();
        } while (current != null && current != head);

        return null;
    }

    @Override
    public void deleteHead() {
        super.deleteHead();
        tail.setNext(head);
    }

    @Override
    public void deleteTail() {
        super.deleteTail();
        tail.setNext(head);
    }

    @Override
    public void delete(DNode node) {
        super.delete(node);
        tail.setNext(head);
    }
    
    @Override
    public void sort() {
    	if (head == null || head.getNext() == null) {
            return; // List is already sorted
        }

        DNode sortedList = null;
        DNode unsortedList = head;
        DNode first = head;
        int count = 0;

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

            // Update tail node
            tail = sortedList;
            while (tail.getNext() != null && tail.getNext() != sortedList) {
                tail = tail.getNext();
            }
            if (unsortedList == first) {	
            		break;
            }
        }

        // Make the list circular
        tail.setNext(sortedList);

        // Set the new head of the sorted list
        head = sortedList;

        sorted = true;
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void print() {
        System.out.println("Circular List length: " + size);

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
            do {
                System.out.print(current.getData() + " ");
                current = current.getNext();
            } while (current != head);
            System.out.println();
        }
    }
}