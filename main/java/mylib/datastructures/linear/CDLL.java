package main.java.mylib.datastructures.linear;

import java.util.NoSuchElementException;

import main.java.mylib.datastructures.nodes.DNode;

public class CDLL extends DLL {
    public CDLL() {
        super();
    }

    public CDLL(DNode node) {
        super(node);
        node.setNext(node);
        node.setPrev(node);
    }

    // Override the insertHead method to update the prev of the tail node
    @Override
    public void insertHead(DNode node) {
        super.insertHead(node);
        if (tail != null) {
            tail.setNext(head);
            head.setPrev(tail);
        }
    }

    // Override the insertTail method to update the next of the head node
    @Override
    public void insertTail(DNode node) {
        super.insertTail(node);
        if (head != null) {
            tail.setNext(head);
            head.setPrev(tail);
        }
    }
    
    @Override
    public void insert(DNode node, int position) {
        super.insert(node, position);
        // adjust links to make the list circular
        head.setPrev(tail);
        tail.setNext(head);
    }
    
    @Override
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
            tail.setNext(head);
            head.setPrev(tail);
        }
    }
    
    @Override
    public DNode search(DNode node) {
        DNode current = head;
        if (head == null) {
            return null;
        }
        do {
            if (current == node) {
                return current;
            }
            current = current.getNext();
        } while (current != head);
        return null;
    }

    // Override the deleteHead method to update the prev of the tail node
    @Override
    public void deleteHead() {
        super.deleteHead();
        if (tail != null) {
            tail.setNext(head);
            head.setPrev(tail);
        }
    }

    // Override the deleteTail method to update the next of the head node
    @Override
    public void deleteTail() {
        super.deleteTail();
        if (head != null) {
            tail.setNext(head);
            head.setPrev(tail);
        }
    }
    
    @Override
    public void delete(DNode node) {
        if (head == null) { // List is empty
            return;
        }
        if (head == node) { // Node to delete is the head
        	deleteHead();
            return;
        }
        if (tail == node) { // Node to delete is the tail
            deleteTail();
            return;
        }
        // Node to delete is in the middle
        DNode current = head.getNext();
        while (current != head) {
            if (current == node) {
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());
                size--;
                return;
            }
            current = current.getNext();
        }
    }
    
    @Override
    public void sort() {
        if (head == null || head == tail) {
            // list is empty or has only one node
            return;
        }

        // create a temporary head node to simplify sorting
        DNode tempHead = new DNode(0);
        tempHead.setNext(head);
        head.setPrev(tempHead);
        tail.setNext(tempHead);
        tempHead.setPrev(tail);

        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            DNode current = tempHead.getNext().getNext();
            while (current != tempHead) {
                DNode nodeToInsert = current;
                current = current.getNext();
                if (nodeToInsert.getData() < nodeToInsert.getPrev().getData()) {
                    // find the proper position to insert nodeToInsert
                    DNode prev = nodeToInsert.getPrev().getPrev();
                    while (prev != tempHead.getPrev() && nodeToInsert.getData() < prev.getData()) {
                        prev = prev.getPrev();
                    }
                    // remove nodeToInsert from the list
                    nodeToInsert.getPrev().setNext(nodeToInsert.getNext());
                    nodeToInsert.getNext().setPrev(nodeToInsert.getPrev());
                    // insert nodeToInsert into its proper position
                    nodeToInsert.setPrev(prev);
                    nodeToInsert.setNext(prev.getNext());
                    prev.getNext().setPrev(nodeToInsert);
                    prev.setNext(nodeToInsert);
                    sorted = false;
                }
            }
        }
        
        // update head and tail references to reflect new order
        head = tempHead.getNext();
        tail = tempHead.getPrev();
        head.setPrev(tail);
        tail.setNext(head);
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
