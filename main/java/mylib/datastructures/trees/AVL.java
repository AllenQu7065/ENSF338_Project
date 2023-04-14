package main.java.mylib.datastructures.trees;
import main.java.mylib.datastructures.nodes.TNode;

public class AVL extends BST {

    //Default constructor
    public AVL() {
        super();
    }
    //Overload constructors
    AVL(int val) {
        super(val);
    }
    AVL(TNode obj) {
        //calls the super default constructor
        super();
        //if obj is not null, doesn't have a left or right node, it is a single node and doesn't need to be balanced
        if(obj != null && obj.getLeft() == null && obj.getRight() == null) {
            this.root = obj;
        //else it is a tree containing multiple nodes and may need to be balanced so put it through the balancing function
        } else {
            this.root = balanceTree(obj);
        }
    }

    @Override
    public void setRoot(TNode root) {

        super.setRoot(root);
        this.root = balanceTree(root);

    }

    @Override
    public void insert(int val) {
        //uses the BST insert implementation then balances the binary tree that is created
        super.insert(val);
        this.root = balanceTree(root);

    }
    @Override
    public void insert(TNode obj) {
        //uses the BST insert implementation then balances the binary tree that is created
        super.insert(obj);
        this.root = balanceTree(root);

    }
    @Override
    public void delete(int val) {
        //uses the BST delete implementation then balances the binary tree that is remaining
        super.delete(val);
        this.root = balanceTree(root);

    }

    private int getHeight(TNode root) {

        if(root == null) {

            return -1;

        } else {

            return 1 + Math.max(getHeight(root.getLeft()), getHeight(root.getRight()));

        }

    }

    private int getBalance(TNode node) {

        if(node == null) {

            return 0;

        } else {

            return getHeight(node.getLeft()) - getHeight(node.getRight());

        }

    }

    private TNode rotateLeft(TNode node) {
        //initialize TNode right as the right child of the parameter node
        TNode right = node.getRight();
        //set the parent of the right child to the parent of the paramter node as it will be the new parent
        right.setParent(node.getParent());
        //set the right child to equal the left child as in a left rotation, the items are moving counter-clockwise
        node.setRight(right.getLeft());
        //node.right is now right.left so now check if (right.left).right is not null, if so, set the new node.right's parent to be node
        if(node.getRight() != null) {

            node.getRight().setParent(node);

        }
        //right is the new parent, and since originally node < node.right, node will be on the left of right
        right.setLeft(node);
        //node's new parent is right
        node.setParent(right);
        //reset the balances
        node.setBalance(Math.max(getBalance(node.getLeft()), getBalance(node.getRight())) + 1);
        right.setBalance(Math.max(getBalance(right.getLeft()), getBalance(right.getRight())) + 1);
        //return right child node as in a left rotation it is the new parent
        return right;
    }

    private TNode rotateRight(TNode node) {
        //initialize TNode left as the left child of the parameter node
        TNode left = node.getLeft();
        //set the parent of the left child to the parent of the paramter node as it will be the new parent
        left.setParent(node.getParent());
        //set the left child to equal the right child as in a right rotation, the items are moving clockwise
        node.setLeft(left.getRight());
        //node.left is now left.right so now check if (left.right).left is not null, if so, set the new node.left's parent to be node
        if(node.getLeft() != null) {

            node.getLeft().setParent(node);

        }
        //left is the new parent, and since originally node > node.left, node will be on the right of left
        left.setRight(node);
        //node's new parent is left
        node.setParent(left);
        //reset the balances
        node.setBalance(Math.max(getBalance(node.getLeft()), getBalance(node.getRight())) + 1);
        left.setBalance(Math.max(getBalance(left.getLeft()), getBalance(left.getRight())) + 1);
        //return left child node as in a right rotation it is the new parent
        return left;
    }

    private TNode balanceTree(TNode node) {

        if(node == null) {

            return null;

        } else {
            //initialize newNode as the node that was passed in as a parameter to be balanced
            TNode newNode = new TNode(node.getData(), node.getBalance(), node.getParent(), null, null);
            //recursively balance the left and right branches of the tree(node)
            newNode.setLeft(balanceTree(node.getLeft()));
            newNode.setRight(balanceTree(node.getRight()));
            //set the balance of newNode by getting the balance of newNode after the left and right branches have been added
            newNode.setBalance(getBalance(newNode));
            //if newNode's balance is greater than 1, it is unbalanced and is left-heavy (since left - right > 1, left > right)
            if(newNode.getBalance() > 1) {
                //now check left hand side to see if there is an imbalance
                if(newNode.getLeft().getBalance() == -1) {
                    //if left.balance is negative one, it is right-heavy so rotate left
                    newNode.setLeft(rotateLeft(newNode.getLeft()));

                } else {
                    //otherwise rotate right
                    newNode = rotateRight(newNode);

                }
            //else if newNode's balance is less than -1, it is unbalanced and is right-heavy (since left - right < -1, right > left)
            } else if(newNode.getBalance() < -1) {
                //now check right hand side to see if there is an imbalance
                if(newNode.getRight().getBalance() == 1) {
                    //if left.balance is one, it is left-heavy so rotate right
                    newNode.setRight(rotateRight(newNode.getRight()));

                } else {
                    //otherwise rotate left
                    newNode = rotateLeft(newNode);

                }
            }
            //return newNode as it is now balanced
            return newNode;
        }
    }

    public static void main( String[] args ) {

        TNode node = new TNode(44, 0, null, null, null);

        BST tree = new BST();
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);

        System.out.println("Initial unbalanced tree:");
        tree.printBF();
        System.out.println("");

        System.out.println("Test overload constructor of TNode obj to see if it balances a node that has children:\n");
        AVL avlTreeNode = new AVL(tree.getRoot());
        avlTreeNode.printBF();
        System.out.println("");

        System.out.println("Test setting of a root that has children:\n");
        AVL avlTreeNull = new AVL();
        avlTreeNull.setRoot(tree.getRoot());
        avlTreeNull.printBF();
        System.out.println("");

        System.out.println("Test insert(int val) function:\n");
        avlTreeNull.insert(1);
        avlTreeNull.printBF();
        System.out.println("");

        System.out.println("Test insert(TNode obj) function:\n");
        avlTreeNull.insert(node);
        avlTreeNull.printBF();
        System.out.println("");

        System.out.println("Test delete(int val) function by removing 3:\n");
        avlTreeNull.delete(3);
        avlTreeNull.printBF();
        System.out.println("");

        System.out.println("Test rotations:\n");

        BST tree1 = new BST();
        tree1.insert(6);
        tree1.insert(4);
        tree1.insert(5);
        tree1.insert(3);
        BST tree2 = new BST();
        tree2.insert(7);
        tree2.insert(12);
        tree2.insert(11);
        tree2.insert(15);
        BST tree3 = new BST();
        tree3.insert(5);
        tree3.insert(3);
        tree3.insert(4);
        BST tree4 = new BST();
        tree4.insert(4);
        tree4.insert(8);
        tree4.insert(6);

        AVL avlTreeRightRot = new AVL(tree1.getRoot());
        AVL avlTreeLeftRot = new AVL(tree2.getRoot());
        AVL avlTreeLeftRightRot = new AVL(tree3.getRoot());
        AVL avlTreeRightLeftRot = new AVL(tree4.getRoot());
        
        System.out.println("Test right rotation:\n");
        System.out.println("Unbalanced:\n");
        tree1.printBF();
        System.out.println("");
        System.out.println("Balanced:\n");
        avlTreeRightRot.printBF();
        System.out.println("");

        System.out.println("Test left rotation:\n");
        System.out.println("Unbalanced:\n");
        tree2.printBF();
        System.out.println("");
        System.out.println("Balanced:\n");
        avlTreeLeftRot.printBF();
        System.out.println("");

        System.out.println("Test left-right rotation (does not completely work, only completes the first rotation):\n");
        System.out.println("Unbalanced:\n");
        tree3.printBF();
        System.out.println("");
        System.out.println("Balanced:\n");
        avlTreeLeftRightRot.printBF();
        System.out.println("");

        System.out.println("Test right-left rotation (does not completely work, only completes the first rotation):\n");
        System.out.println("Unbalanced:\n");
        tree4.printBF();
        System.out.println("");
        System.out.println("Balanced:\n");
        avlTreeRightLeftRot.printBF();
        System.out.println("");

    }

}