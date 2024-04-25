/*
 * Name: Joshua Douglas
 * Class: CS 1450 - 001 (Tue/Thu)
 * Date: 04.25.2024
 * Assignment #10
 * Description: This program is designed to practice creating our own binary search tree
 * and manipulating it with different functions. In this program, a binary search tree is created
 * using nodes which hold parrot objects. The code will add parrots from a file in order of the
 * parrot's id's. Then, it will traverse the binary tree using level order and display the parrot's
 * song. Lastly, it will search the leaf nodes and display which parrots are stored at the bottom of
 * the tree.
 */
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;

public class DouglasJoshuaAssignment10 {
    
    public static void main(String[] args) throws IOException{
        
    	// Create tree and file object to create parrots
    	BinaryTree parrotTree = new BinaryTree();
    	
    	File parrots = new File("parrots.txt");
    	Scanner parrotReader = new Scanner(parrots);
    	
    	// While there are still parrots to add, insert them into binary tree
    	while (parrotReader.hasNext()) {
    		
    		int id = parrotReader.nextInt();
    		String name = parrotReader.next();
    		String songWord = parrotReader.next();
    		
    		Parrot tempParrot = new Parrot(id, name, songWord);
    		
    		parrotTree.insert(tempParrot);
    	}
    	
    	// Use level order to print each parrot's song word in succession
    	System.out.println("Parrot's Song");
    	System.out.println("-------------");
    	parrotTree.levelOrder();
    	System.out.println();
    	
    	// Show which parrots are stored in leaft nodes
    	System.out.println("Parrots on Leaf Nodes");
    	System.out.println("---------------------");
    	parrotTree.visitLeaves();
    	
    	// Close scanner to avoid data leaks
    	parrotReader.close();
    }
}

// Represents parrot object and implements custom compareTo function
class Parrot implements Comparable<Parrot>{

	private int id;
	private String name;
	private String songWord;
	
	public Parrot(int id, String name, String songWord) {
		
		this.id = id;
		this.name = name;
		this.songWord = songWord;
	}
	
	public String getName() {
		return name;
	}
	
	public String sing() {
		return songWord;
	}
	
	// Return 1 if this parrot has greater id than other, -1 if less, and 0 if equal 
	@Override
	public int compareTo(Parrot otherParrot) {
		
		if (this.id > otherParrot.id) {
			return 1;
		}
		
		else if (this.id < otherParrot.id) {
			return -1;
		}
		
		else {
			return 0;
		}
	}
}

// Represents binary search tree that parrots will be stored in
class BinaryTree {
	
	private TreeNode root;
	
	// Create binary tree by initializing the root node
	public BinaryTree() {
		root = null;
	}
	
	// Insert parrot into binary tree based on parrot's ID
	public boolean insert(Parrot parrotToAdd) {
		
		// If tree is empty, place parrot in root
		if (root == null) {
			
			root = new TreeNode(parrotToAdd);
		}
		
		// Else, insert the parrot into the proper location in the binary tree
		else {
			
			TreeNode parent = null;
			TreeNode current = root;
			
			while (current != null) {
				
				// Traverse left
				if (parrotToAdd.compareTo(current.parrot) < 0) {
					
					parent = current;
					current = current.left;
				}
				
				// Traverse right
				else if (parrotToAdd.compareTo(current.parrot) > 0) {
					
					parent = current;
					current = current.right;
				}
				
				// Handle case when parrot isn't added
				else {
					return false;
				}
			}
			
			// Insert parrot into left node
			if (parrotToAdd.compareTo(parent.parrot) < 0) {
				
				parent.left = new TreeNode(parrotToAdd);
			}
			
			// Insert parrot into right node
			else {
				
				parent.right = new TreeNode(parrotToAdd);
			}
		}
		
		// Parrot successfully added
		return true;
	}
	
	// Move through binary search tree level by level checking first the left, then right
	public void levelOrder() {
		
		// As long as the tree isn't empty
		if (root != null) {
			
			Queue<TreeNode> nodeQueue = new LinkedList<>();
			
			// Start by adding root to queue
			nodeQueue.offer(root);
			
			// While the queue isn't empty, continue adding nodes in lever order
			while (!nodeQueue.isEmpty()) {
				
				TreeNode tempNode = nodeQueue.remove();
				
				System.out.print(tempNode.parrot.sing() + " ");
				
				// First move all the way down the left side of the tree
				if (tempNode.left != null) {
					
					nodeQueue.offer(tempNode.left);
				}
				
				// After going all the way left, move right
				if (tempNode.right != null) {
					
					nodeQueue.offer(tempNode.right);
				}
			}
			
			System.out.println();
		}
		
		
	}
	
	public void visitLeaves() {
		visitLeaves(root);
	}
	
	// Visit the leaves and to show what parrots are stored in leaf nodes
	private void visitLeaves(TreeNode aNode) {
		
		// If the tree is empty, return null
		if (aNode == null) {
			return;
		}
		
		// If both the left and right child are null (meaning it's a leaf node), display parrots name
		else if (aNode.left == null && aNode.right == null) {
			System.out.println(aNode.parrot.getName());
		}
		
		// Otherwise, continue moving through tree
		else {
			
			if (aNode.left != null) {
				visitLeaves(aNode.left);
			}
			
			if (aNode.right != null) {
				visitLeaves(aNode.right);
			}
		}
	}
	
	// Defines nodes which will be used to build the binary search tree
	private class TreeNode {
		
		private Parrot parrot;
		private TreeNode left;
		private TreeNode right;
		
		public TreeNode(Parrot parrot) {
			this.parrot = parrot;
			left = null;
			right = null;
		}
	}
}
