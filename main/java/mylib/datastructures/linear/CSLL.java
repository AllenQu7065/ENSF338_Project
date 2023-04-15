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
        //super.sortedInsert(node);
        //head.setNext(head); // make the list circular
        ///tail = head.getNext(); // update the tail pointer
    	sort();

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
            System.out.print("List content: ");

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
		
		CSLL linkedList = new CSLL(node1);
		
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