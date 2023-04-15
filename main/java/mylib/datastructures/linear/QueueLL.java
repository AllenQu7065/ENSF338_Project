package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class QueueLL extends SLL {
	
	public QueueLL(){ // initialize an empty queue
        super();
    }
    public QueueLL(DNode node){
        head = node;
        tail = node;
        size++;
    }
	
    public void enqueue(DNode node) {
        super.insertTail(node);
    }

    public DNode dequeue() {
        if (head == null) {
            return null;
        }
        DNode dequeuedNode = head;
        super.deleteHead();
        return dequeuedNode;
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
 		
 		QueueLL linkedList = new QueueLL(node1);
 		
 		System.out.println("Testing enqueue(node2):");
 		linkedList.enqueue(node2);
 		linkedList.print();
 		System.out.println("\n");
 		
 		System.out.println("Testing enqueue(node3):");
 		linkedList.enqueue(node3);
 		linkedList.print();
 		System.out.println("\n");
 		
 		System.out.println("Testing enqueue(node4):");
 		linkedList.enqueue(node4);
 		linkedList.print();
 		System.out.println("\n");
 		
 		System.out.println("Testing peek():");
 		System.out.println(linkedList.peek().getData());
 		System.out.println("\n");
 		
 		System.out.println("Testing dequeue():");
 		linkedList.dequeue();
 		linkedList.print();
 		System.out.println("\n");
 		
 		System.out.println("Testing peek():");
 		System.out.println(linkedList.peek().getData());
 		System.out.println("\n");
 		
 		System.out.println("Testing enqueue(node5):");
 		linkedList.enqueue(node5);
 		linkedList.print();
 		System.out.println("\n");
 		
 		System.out.println("Testing clear():");
 		linkedList.clear();
 		linkedList.print();
 		System.out.println("\n");
 	
 		System.out.println("Testing Finished");
 		
 	}
}
