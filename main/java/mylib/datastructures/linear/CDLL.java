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

        sort();
        
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
    	if (head == null || head.getNext() == head) {
            return;
        }
    	
    	DNode current = head.getNext();
        do {
            DNode insertionPos = current.getPrev();
            while (insertionPos != head.getPrev() && insertionPos.getData() > current.getData()) {
                insertionPos = insertionPos.getPrev();
            }

            if (insertionPos != current.getPrev()) {
                // Remove current from its current position
                current.getPrev().setNext(current.getNext());
                current.getNext().setPrev(current.getPrev());

                // Insert current into its new position
                if (insertionPos == head.getPrev() && insertionPos.getData() > current.getData()) {
                    head = current;
                }
                current.setNext(insertionPos.getNext());
                insertionPos.getNext().setPrev(current);
                insertionPos.setNext(current);
                current.setPrev(insertionPos);
            } 

            current = current.getNext();
            
        } while  (current != head);
        
        int counter = 0;
        tail = head;
        while (counter < size -1) {
            tail = tail.getNext();
            counter++;
        }
    }


    
    @Override
    public void clear() {
        super.clear();
    }
    
    @Override
    public void print() {
    	if(size == 0) {
    		System.out.println("List is empty");
    		return;
    	}
    	
    	System.out.println("Circular List length: " + size);
    	 
    	boolean sorted = true;
        DNode currentSort = head;
        int counter = 0;
         
        while (currentSort != null && currentSort.getNext() != null && counter < size-1) {
           if (currentSort.getData() > currentSort.getNext().getData()) {
        	   sorted = false;
           }
           currentSort = currentSort.getNext();
           counter++;
        }
    	 
        if (sorted) {
        	System.out.println("List is sorted.");
        } else {
        	System.out.println("List is not sorted.");
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
    
    public static void main(String args[]) {
		
		System.out.println("Testing Start");
		System.out.println("\n");
		
		DNode node1 = new DNode(1);
		DNode node2 = new DNode(2);
		DNode node3 = new DNode(3);
		DNode node4 = new DNode(4);
		DNode node5 = new DNode(5);
		DNode node6 = new DNode(6);
		DNode node7 = new DNode(7);
		DNode node8 = new DNode(8);
		DNode node9 = new DNode(9);
		DNode node10 = new DNode(10);
		
		CDLL linkedList = new CDLL(node1);
		
		System.out.println("Testing insertHead(node2):");
		linkedList.insertHead(node2);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing insertTail(node4):");
		linkedList.insertTail(node4);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing sort():");
		linkedList.sort();
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing sortedInsert(node3):");
		linkedList.sortedInsert(node3);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing insertTail(node5):");
		linkedList.insertTail(node5);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing search(node3) **in the list**:");
		if (null == linkedList.search(node3)) {
			System.out.println("Search Failed");
		}else {
			System.out.println("Search Success");
		}
		System.out.println("\n");
		
		System.out.println("Testing search(node6) **not in list**:");
		if (null == linkedList.search(node6)) {
			System.out.println("Search Failed");
		}else {
			System.out.println("Search Success");
		}
		System.out.println("\n");
		
		System.out.println("Testing insert(node6, 3):");
		linkedList.insert(node6, 3);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing insertHead(node7):");
		linkedList.insertHead(node7);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing insert(node10, 5):");
		linkedList.insert(node10, 5);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing insertTail(node9):");
		linkedList.insertTail(node9);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing sortedInsert(node8):");
		linkedList.sortedInsert(node8);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing deleteHead():");
		linkedList.deleteHead();
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing deleteTail():");
		linkedList.deleteTail();
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing delete(node3):");
		linkedList.delete(node3);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing delete(node9):");
		linkedList.delete(node9);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing delete(node7):");
		linkedList.delete(node7);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing clear():");
		linkedList.clear();
		linkedList.print();
		System.out.println("\n");
		
	
		System.out.println("Testing Finished");
		
	}
}
