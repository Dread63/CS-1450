import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
import java.io.File;
import java.io.IOException;

public class DouglasJoshuaAssignment10 {
    
    public static void main(String[] args) throws IOException{
        
    	BinaryTree parrotTree = new BinaryTree();
    	
    	File parrots = new File("parrots.txt");
    	Scanner parrotReader = new Scanner(parrots);
    	
    	while (parrotReader.hasNext()) {
    		
    		int id = parrotReader.nextInt();
    		String name = parrotReader.next();
    		String songWord = parrotReader.next();
    		
    		Parrot tempParrot = new Parrot(id, name, songWord);
    		
    		parrotTree.insert(tempParrot);
    	}
    	
    	System.out.println("Parrot's Song");
    	System.out.println("-------------");
    	parrotTree.levelOrder();
    	System.out.println();
    	
    	System.out.println("Parrots on Leaf Nodes");
    	System.out.println("---------------------");
    	parrotTree.visitLeaves();
    	
    	parrotReader.close();
    }
}

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

class BinaryTree {
	
	private TreeNode root;
	
	public BinaryTree() {
		root = null;
	}
	
	public boolean insert(Parrot parrotToAdd) {
		
		if (root == null) {
			
			root = new TreeNode(parrotToAdd);
		}
		
		else {
			
			TreeNode parent = null;
			TreeNode current = root;
			
			while (current != null) {
				
				if (parrotToAdd.compareTo(current.parrot) < 0) {
					
					parent = current;
					current = current.left;
				}
				
				else if (parrotToAdd.compareTo(current.parrot) > 0) {
					
					parent = current;
					current = current.right;
				}
				
				else {
					return false;
				}
			}
			
			if (parrotToAdd.compareTo(parent.parrot) < 0) {
				
				parent.left = new TreeNode(parrotToAdd);
			}
			
			else {
				
				parent.right = new TreeNode(parrotToAdd);
			}
		}
		
		return true;
	}
	
	public void levelOrder() {
		
		if (root != null) {
			
			Queue<TreeNode> nodeQueue = new LinkedList<>();
			
			nodeQueue.offer(root);
			
			while (!nodeQueue.isEmpty()) {
				
				TreeNode tempNode = nodeQueue.remove();
				
				System.out.print(tempNode.parrot.sing() + " ");
				
				if (tempNode.left != null) {
					
					nodeQueue.offer(tempNode.left);
				}
				
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
	
	private void visitLeaves(TreeNode aNode) {
		
		if (aNode == null) {
			return;
		}
		
		else if (aNode.left == null && aNode.right == null) {
			System.out.println(aNode.parrot.getName());
		}
		
		else {
			
			if (aNode.left != null) {
				visitLeaves(aNode.left);
			}
			
			if (aNode.right != null) {
				visitLeaves(aNode.right);
			}
		}
	}
	
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
