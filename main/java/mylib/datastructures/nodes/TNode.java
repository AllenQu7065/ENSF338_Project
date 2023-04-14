package main.java.mylib.datastructures.nodes;

public class TNode {
    //Member variables of class
    private int data;
    private TNode left;
    private TNode right;
    private TNode parent;
    private int balance;
    //Default constructor
    public TNode() {

        data = -1;
        left = null;
        right = null;
        parent = null;
        balance = 0;

    }
    //Overload constructor
    public TNode(int data, int balance, TNode P, TNode L, TNode R) {

        this.data = data;
        this.balance = balance;
        this.parent = P;
        this.left = L;
        this.right = R;

    }
    //Setters and Getters
    public void setData(int data) {
        this.data = data;
    }
    public int getData() {
        return this.data;
    }
    public void setLeft(TNode left) {
        this.left = left;
    }
    public TNode getLeft() {
        return this.left;
    }
    public void setRight(TNode right) {
        this.right = right;
    }
    public TNode getRight() {
        return this.right;
    }
    public void setParent(TNode parent) {
        this.parent = parent;
    }
    public TNode getParent() {
        return this.parent;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getBalance() {
        return this.balance;
    }
    //Print function to output node information in a user friendly format
    public void print() {
        System.out.println(String.format("Data        |%1$d\nBalance     |%2$d\nParent Node |%3$s\nLeft Node   |%4$s\nRight Node  |%5$s\n", data, balance, parent, left, right));
    }
    @Override
    //toString() returns int data as a String
    public String toString() {
        return String.format("%d", data);
    }
    //main test method
    public static void main( String[] args ) {

        TNode nullNode = new TNode();

        System.out.println("Test print() function on a null constructor:\n");
        nullNode.print();

        nullNode.setData(3);
        nullNode.setBalance(1);
        nullNode.setParent(nullNode);
        nullNode.setLeft(nullNode);
        nullNode.setRight(nullNode);

        System.out.println("Test print() function after setters:\n");
        nullNode.print();

        TNode node = new TNode(44, 0, null, null, null);

        System.out.println("Test print() function on an overload constructor:\n");
        node.print();

        System.out.println("Test toString() on a TNode object:\n");
        System.out.println(node + "\n");

    }

}