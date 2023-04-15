package main.java.mylib.datastructures.linear;

import java.util.NoSuchElementException;

import main.java.mylib.datastructures.nodes.DNode;

public class DLL {
    public DNode head;
    public DNode tail;
    public int size;

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
    }

    // Inserts node object in its proper position in a sorted list
    // Must check for list sort validity
    // If list is found to be out of order, it must call the sort function first before inserting
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
    	if (head == null || head.getNext() == null) {
            return; // An empty or single-node list is considered sorted
        }

        DNode current = head.getNext();
        while (current != null) {
            DNode insertionPos = current.getPrev();
            while (insertionPos != null && insertionPos.getData() > current.getData()) {
                insertionPos = insertionPos.getPrev();
            }

            if (insertionPos != current.getPrev()) {
                // Remove current from its current position
                current.getPrev().setNext(current.getNext());
                if (current.getNext() != null) {
                    current.getNext().setPrev(current.getPrev());
                }

                // Insert current into its new position
                if (insertionPos != null) {
                    current.setNext(insertionPos.getNext());
                    insertionPos.setNext(current);
                } else {
                    current.setNext(head);
                    head = current;
                }
                current.setPrev(insertionPos);
                if (current.getNext() != null) {
                    current.getNext().setPrev(current);
                }
            }

            current = current.getNext();
        }
        
        tail = head;
        while (tail.getNext() != null) {
            tail = tail.getNext();
        }
    }
    
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
    
    public void print() {
    	if(size == 0) {
    		System.out.println("List is empty");
    		return;
    	}
    	
        // Print the length of the list
        System.out.println("List Length: " + size);
        
        boolean sorted = true;
        DNode currentSort = head;
        
        while (currentSort != null &&currentSort.getNext() != null) {
          if (currentSort.getData() > currentSort.getNext().getData()) {
            sorted = false;
          }
          currentSort = currentSort.getNext();
        }
        
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
		
		DLL linkedList = new DLL(node1);
		
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
		
		System.out.println("Testing delete(node4):");
		linkedList.delete(node4);
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