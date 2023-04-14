package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class QueueLL extends SLL {
	
	public QueueLL(){ // initialize an empty queue
        super();
    }
    public QueueLL(DNode myNode){
        head= myNode;
        tail = myNode;
        size++;
    }
	
    public void enqueue(DNode node) {
        insertTail(node);
    }

    public DNode dequeue() {
        if (head == null) {
            return null;
        }
        DNode dequeuedNode = head;
        deleteHead();
        return dequeuedNode;
    }
    
    public DNode Peek() { 
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
}
