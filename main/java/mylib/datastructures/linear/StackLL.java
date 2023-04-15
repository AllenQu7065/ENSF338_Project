package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class StackLL extends SLL {
    public StackLL() {
        super();
    }
    
    public StackLL(DNode node){
        head = node;
        
        tail = null;
        size = 1;
    }

    public void push(DNode node) {
        super.insertHead(node);
    }

    public DNode pop() {
    	DNode node = head;
        super.deleteHead();
        return node;
    }

    public DNode peek() {
    	if (head == null) {
            return null;
        }
        return head;
    }
    
    @Override
    public void insertHead(DNode node) {
 
    }
    @Override
    public void insertTail(DNode node) {
       
    }
    
    @Override
    public void insert(DNode node, int position) {

    }
    @Override 
    public void sortedInsert(DNode node) { 
    
    }
    @Override
    public void deleteHead() {
       
    }
   
    @Override
    public void delete(DNode node) {
   
    }
    @Override
    public void deleteTail() {
        
    }
     @Override
    public void sort() {

    }
     
     
     public static void main(String args[]) {
    	System.out.println("Testing Start");
		System.out.println("\n");
		
		DNode node1 = new DNode(1);
		DNode node2 = new DNode(2);
		DNode node3 = new DNode(3);
		DNode node4 = new DNode(4);
		DNode node5 = new DNode(5);
		
		StackLL linkedList = new StackLL(node1);
		
		System.out.println("Testing push(node2):");
		linkedList.push(node2);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing push(node3):");
		linkedList.push(node3);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing push(node4):");
		linkedList.push(node4);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing peek():");
		System.out.println(linkedList.peek().getData());
		System.out.println("\n");
		
		System.out.println("Testing pop():");
		linkedList.pop();
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing peek():");
		System.out.println(linkedList.peek().getData());
		System.out.println("\n");
		
		System.out.println("Testing push(node5):");
		linkedList.push(node5);
		linkedList.print();
		System.out.println("\n");
		
		System.out.println("Testing clear():");
		linkedList.clear();
		linkedList.print();
		System.out.println("\n");
	
		System.out.println("Testing Finished");
		
	}
}

