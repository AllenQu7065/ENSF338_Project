package main.java.mylib.datastructures.nodes;

public class DNode {
    private int data;
    private DNode next;
    private DNode prev;
    
    public DNode(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
    
    public int getData() {
        return data;
    }
    
    public void setData(int data) {
        this.data = data;
    }
    
    public DNode getNext() {
        return next;
    }
    
    public void setNext(DNode next) {
        this.next = next;
    }
    
    public DNode getPrev() {
        return prev;
    }
    
    public void setPrev(DNode prev) {
        this.prev = prev;
    }
}

