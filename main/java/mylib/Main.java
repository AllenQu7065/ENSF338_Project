package main.java.mylib;

import main.java.mylib.datastructures.linear.*;
import main.java.mylib.datastructures.nodes.DNode;

public class Main {

	public static void main(String args[]) {  
		DNode node1 = new DNode(11);
		DNode node2 = new DNode(12);
		DNode node3 = new DNode(13);
		DNode node4 = new DNode(14);
		DNode node5 = new DNode(15);
		DNode node6 = new DNode(16);
		
		CDLL linkedList = new CDLL(node1);
		linkedList.insertHead(node2);
		linkedList.insertTail(node4);
		linkedList.print();
		System.out.println(" ");
		linkedList.sort();
		linkedList.print();
		System.out.println(" ");
		linkedList.sortedInsert(node3);
		linkedList.insertTail(node5);
		linkedList.print();
		System.out.println(" ");
		if (null == linkedList.search(node3)) {
			System.out.println("Failed");
		}else {
			System.out.println("Success");
		}
		if (null == linkedList.search(node6)) {
			System.out.println("Failed");
		}else {
			System.out.println("Success");
		}
		System.out.println(" ");
		linkedList.deleteHead();
		linkedList.deleteTail();
		linkedList.print();
		System.out.println(" ");
		linkedList.delete(node3);
		linkedList.print();
		System.out.println(" ");
		linkedList.clear();
		linkedList.print();
		System.out.println(" ");
		
		
		
		System.out.println("done");
		
	}
}
