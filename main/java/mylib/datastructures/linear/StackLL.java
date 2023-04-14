package main.java.mylib.datastructures.linear;

import main.java.mylib.datastructures.nodes.DNode;

public class StackLL extends SLL {
    public StackLL() {
        super();
    }
    
    public StackLL(DNode myNode){
        head = myNode;
        
        tail = null;
        size = 1;
    }

    public void push(DNode node) {
        insertHead(node);
    }

    public DNode pop() {
    	DNode node = head;
        deleteHead();
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
}

