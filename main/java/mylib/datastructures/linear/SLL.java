package main.java.mylib.datastructures.linear;

import java.util.NoSuchElementException;

import main.java.mylib.datastructures.nodes.DNode;

public class SLL {
    public DNode head;
    public DNode tail;
    public int size;

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
    }
    
    public void sortedInsert(DNode node) {
        
    	sort();

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
    	
        System.out.println("List length: " + size);
        
        boolean sorted = true;
        DNode currentSort = head;
        
        while (currentSort != null &&currentSort.getNext() != null) {
          if (currentSort.getData() > currentSort.getNext().getData()) {
            sorted = false;
          }
          currentSort = currentSort.getNext();
        }
        
        
        if (sorted) {
            System.out.println("List is sorted.");
        } else {
            System.out.println("List is not sorted.");
        }

        if (head == null) {
            System.out.println("List is empty");
        } else {
            System.out.print("List content: ");

            DNode current = head;
            while (current != null) {
                System.out.print(current.getData() + " ");
                current = current.getNext();
            }
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
		
		SLL linkedList = new SLL(node1);
		
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
		
		System.out.println("Testing insert(node9, 5):");
		linkedList.insert(node9, 5);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing insertTail(node10):");
		linkedList.insertTail(node10);
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