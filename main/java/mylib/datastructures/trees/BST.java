package main.java.mylib.datastructures.trees;
import main.java.mylib.datastructures.nodes.TNode;

import java.util.*;

public class BST {
    //Member variables of class
    TNode root;
    //Default constructor
    BST() {
        root = null;
    }
    //Overload constructor for int val
    BST(int val) {
        this.root = new TNode(val, 0, null, null, null);
    }
    //Overload constructor for TNode obj
    BST(TNode obj) {
        this.root = obj;
    }
    //Setters and Getters
    public void setRoot(TNode root) {
        this.root = root;
    }
    public TNode getRoot() {
        return this.root;
    }
    //insert int val
    public void insert(int val) {
        //creates a node that needs to be inserted with the data value being the val passed into the function
        TNode nodeToInsert = new TNode(val, 0, null, null, null);
        //if the root is null, the root will become the node to be inserted since the tree was initially empty
        if(root == null) {

            root = nodeToInsert;
            return;
        //otherwise there is a root
        } else {
            //set current node to root
            TNode currentNode = root;
            //loop indefinetely until a return is called
            while(true) {
                //if val is less than the current nodes data, go to the left, otherwise go right
                if(val < currentNode.getData()) {
                    //if the left node is null, it can be inserted here
                    if(currentNode.getLeft() == null) {

                        currentNode.setLeft(nodeToInsert);
                        currentNode.getLeft().setParent(currentNode);
                        return;
                    //otherwise set the current node to the left node
                    } else {

                        currentNode = currentNode.getLeft();

                    }

                } else {
                    //if the right node is null, it can be inserted here
                    if(currentNode.getRight() == null) {

                        currentNode.setRight(nodeToInsert);
                        currentNode.getRight().setParent(currentNode);
                        return;
                    //otherwise set the current node to the right node
                    } else {

                        currentNode = currentNode.getRight();

                    }
                }
            }
        }
    }
    //insert TNode node
    public void insert(TNode node) {
        //nodeToInsert is just the node passed into the function
        //the rest of the code is functionally the same as the other insert function
        TNode nodeToInsert = node;
        //if the root is null, the root will become the node to be inserted since the tree was initially empty
        if(root == null) {

            root = nodeToInsert;
            return;
        //otherwise there is a root
        } else {
            //set current node to root
            TNode currentNode = root;
            //loop indefinetely until a return is called
            while(true) {
                //if the node's data value is less than the current nodes data, go to the left, otherwise go right
                if(nodeToInsert.getData() < currentNode.getData()) {
                    //if the left node is null, it can be inserted here
                    if(currentNode.getLeft() == null) {

                        currentNode.setLeft(nodeToInsert);
                        currentNode.getLeft().setParent(currentNode);
                        return;
                    //otherwise set the current node to the left node
                    } else {

                        currentNode = currentNode.getLeft();

                    }

                } else {
                    //if the right node is null, it can be inserted here
                    if(currentNode.getRight() == null) {

                        currentNode.setRight(nodeToInsert);
                        currentNode.getRight().setParent(currentNode);
                        return;
                    //otherwise set the current node to the right node
                    } else {

                        currentNode = currentNode.getRight();

                    }
                }
            }
        }
    }
    //delete node with int val
    public void delete(int val) {

        root = deleteThisNode(root, val);

    }

    private TNode deleteThisNode(TNode currentNode, int data) {

        if(root == null) {

            System.out.println("Could not delete node with given value because the tree is empty.");
            return null;

        }

        if(currentNode == null) {

            System.out.println("Could not find " + data + " in the tree.");
            return null;

        } else {

            TNode nodeToRemove = new TNode(data, 0, null, null, null);        

            if(nodeToRemove.getData() < currentNode.getData()) {

                currentNode.setLeft(deleteThisNode(currentNode.getLeft(), data));
                
            } else if(nodeToRemove.getData() > currentNode.getData()) {

                currentNode.setRight(deleteThisNode(currentNode.getRight(), data));

            } else {

                if(currentNode.getLeft() == null) {

                    return currentNode.getRight();

                } else if(currentNode.getRight() == null) {

                    return currentNode.getLeft();

                }

                currentNode.setData(minVal(currentNode.getRight()));
                currentNode.setRight(deleteThisNode(currentNode.getRight(), currentNode.getData()));

            }
        }

        return currentNode;

    }

    private int minVal(TNode root) {

        int val = root.getData();

        while (root.getLeft() != null) {

            val = root.getLeft().getData();
            root = root.getLeft();

        }

        return val;

    }

    public TNode search(int val) {

        TNode currentNode = root;

        while(currentNode != null) {

            if(currentNode.getData() == val) {

                return currentNode;

            } else if(currentNode.getData() > val) {

                currentNode = currentNode.getLeft();

            } else {
                
                currentNode = currentNode.getRight();
            
            }

        }

        return null;

    }

    public void printInOrder(TNode node) {

        if(node == null) {

            return;

        } else {

            printInOrder(node.getLeft());
            System.out.println(String.format("%s ", node.getData()));
            printInOrder(node.getRight());

        }
    }

    public void printBF() {

        if(this.root == null) {

            return;

        } else {

            Queue<TNode> queueForNodes = new LinkedList<>();
            queueForNodes.offer(this.root);

            while(!queueForNodes.isEmpty()) {

                int size = queueForNodes.size();

                for(int i = 0; i < size; i++) {

                    TNode node = queueForNodes.poll();
                    System.out.print(node.getData() + " ");
    
                    if(node.getLeft() != null) {
    
                        queueForNodes.offer(node.getLeft());
    
                    }
    
                    if (node.getRight() != null) {
    
                        queueForNodes.offer(node.getRight());
    
                    }
                }

                System.out.println("");

            }
        }
    }

    //main test method
    public static void main( String[] args ) {

        TNode node = new TNode(10, 0, null, null, null);
        TNode nodeToInsert = new TNode(15, 0, null, null, null);

        BST nullBST = new BST();
        BST intBST = new BST(5);
        BST objBST = new BST(node);

        System.out.println("Test for getRoot() function on a null BST (Should be null):\n");
        System.out.println(nullBST.getRoot() + "\n");
        System.out.println("Test for getRoot() function on an int val BST constructor (Should be 5):\n");
        System.out.println(intBST.getRoot() + "\n");
        System.out.println("Test for getRoot() function on a TNode obj BST constructor (Should be 10):\n");
        System.out.println(objBST.getRoot() + "\n");

        System.out.println("Test for setRoot() function (Should be 10):\n");
        nullBST.setRoot(node);
        System.out.println(nullBST.getRoot() + "\n");

        System.out.println("Test for insert(int val), insert(TNode obj), printInOrder() and printBF():\n");
        nullBST.insert(4);
        nullBST.insert(nodeToInsert);
        System.out.println("Print in order:\n");
        nullBST.printInOrder(nullBST.getRoot());
        System.out.println("");
        System.out.println("Print in breadth-first:\n");
        nullBST.printBF();
        System.out.println("");

        System.out.println("Test for search(int val):\n");
        System.out.println(nullBST.search(4) + "\n");
        System.out.println(nullBST.search(100) + "\n");

        System.out.println("Test for delete(int val) by trying to remove 100 then removing 4:\n");
        nullBST.delete(100);
        nullBST.delete(4);
        nullBST.printBF();
        System.out.println("");

        System.out.println("Test for when all items are deleted:\n");
        nullBST.delete(10);
        nullBST.delete(15);
        nullBST.delete(15);
        System.out.println("");

        System.out.println("Test for insertion after complete deletion:\n");
        nullBST.insert(4);
        nullBST.printBF();
        System.out.println("");
        nullBST.delete(4);
        TNode nodeTest = new TNode(4, 0, null, null, null);
        nullBST.insert(nodeTest);
        nullBST.printBF();
        System.out.println("");

    }
}