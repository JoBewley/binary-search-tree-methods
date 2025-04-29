import java.util.Scanner;
import java.util.Stack;

class IntTreeNode {
    public int data;            // data stored at this node
    public IntTreeNode left;    // reference to left subtree
    public IntTreeNode right;   // reference to right subtree
        
    // Constructs a leaf node with the given data.
    public IntTreeNode(int data) {
        this(data, null, null);
    }
                
    // Constructs a branch node with the given data and links.
    public IntTreeNode(int data, IntTreeNode left,
                                 IntTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }
}

class IntSearchTree{
	private IntTreeNode overallRoot;
	
	public IntSearchTree() {
		overallRoot = null;
	}
	
	public void add(int value) {
		overallRoot = add(overallRoot, value);
	}
	
	private IntTreeNode add(IntTreeNode root, int value) {
		if(root == null) {
			root = new IntTreeNode(value);
		}
		else if(value<=root.data) {
			root.left = add(root.left,value);
		}
		else {
			root.right = add(root.right,value);
		}
		return root;
	}
	
	 public boolean contains(int value) {
		 return contains(overallRoot, value);
	 }
	 
	 private boolean contains(IntTreeNode root, int value) {	
		 //base - if node's root is null, exit i.e. value not found
		 if(root == null) {
			 return false;
		 }
		 //if node's data == value, return true, found
		 if(value == root.data) {
			 return true;
		 }
		 //BST - if value is less than node's data, branch left and recurse
		 else if(value < root.data) {
			 return contains(root.left, value);
		 }
		 //else, value is larger, recurse right
		 else {
			 return contains(root.right, value);
		 }
		// return false;
	 }
	 
	 public int sum() {
		 return sum(overallRoot);
	 }
	 
	 private int sum(IntTreeNode root) {
		 //base case - node empty. stop recursion - this base case is in several methods, does/means the same thing
		 if(root == null) {
			 return 0;
		 }
		 //hold the value of root we're in (in the call stack) and recurse down left and right branches
		 else {
			 return root.data + sum(root.left) + sum(root.right);
		 }
	 }
	 
	 //similar logic to sum() method...
	 public int countLevels() {
		 return countLevels(overallRoot);
	 }
	 private int countLevels(IntTreeNode root) {
		 //if root is empty, stop recursion
		 if(root == null) {
			 return 0;
		 }else {
			 //count the depth of both right and left paths
			 int depthLeft = countLevels(root.left);
			 int depthRight = countLevels(root.right);
			 //if-else statements look at which path was ultimately deeper starting from overallRoot, returns depth of whichever is deeper
			 if(depthLeft > depthRight) {
				 return depthLeft + 1;
			 }else {
				 return depthRight+1;
			 }
		 }
	 }
	 
	 public int countLeaves(){
		 return countLeaves(overallRoot);
	 }
	 private int countLeaves(IntTreeNode root) {
		 if(root == null) {
			 return 0;
			 //if a node has a non-null root, not a leaf, keep recursing
		 }else if(root.left != null || root.right!= null) {
			 return countLeaves(root.left)+ countLeaves(root.right); 
		 }else {	//else, both are empty, return 1 to add to the count of leaves
			 return 1;
		 }
	 }
	 
	 public int min() {
		 return min(overallRoot);
	 }
	 private int min(IntTreeNode root) {
		 if (root == null) {
			 return 0;
		 }
		 //smallest val should be leftmost node...just keep recursing left until you can't recurse left anymore
		 if(root.left == null) {
			 return root.data;
		 }else {
			 return min(root.left);
		 }
	 }
	 
	 public int max() {
		 return max(overallRoot);
	 }
	 private int max(IntTreeNode root) {
		 if (root == null) {
			 return 0;
		 }
		 //opposite of min()... go right until you can't anymore
		 if(root.right == null) {
			 return root.data;
		 }else {
			 return max(root.right);
		 }
	 }
	 
	 public int size() {
		 return size(overallRoot);
	 }
	 private int size(IntTreeNode root) {
		 if(root == null) {
			 return 0;
		 }	//if root isn't empty, return 1 to the size count and recurse to both possible branches
		 else {
			 return 1 + size(root.left)+size(root.right);
		 }
	 }
	 
	 public int countLeftNodes() {	 
		 return countLeftNodes(overallRoot);
	 }
	 private int countLeftNodes(IntTreeNode root) {
		 if (root == null) {
			 return 0;
			 //if both roots null, end recurstion
		 }else if(root.left == null && root.right == null) {
			 return 0;
			 //if left root isn't null, left node exits, return 1 and recurse both branches
		 }else if (root.left != null) {
			 return 1 + countLeftNodes(root.left) + countLeftNodes(root.right);
		 }else {	//else, recurse both branches (recursing only right gives wrong leftnode count?)
			 return countLeftNodes(root.left) + countLeftNodes(root.right);
		 }
	 }
	 
	// public boolean isFull() {
		// return isFull(overallRoot);
//	 }
	 public boolean isFull() {
		 int levels = countLevels();
		 int nodeCount = size();
		 boolean logCheck = false;
		 //full tree grows exponentially per level -1. 
		 //log(base 2)nodeCount - 1 should equal the amount of levels if the tree is full
		 //if statement above comment in opposite manner - if (2^levels)-1 = number of nodes, tree must be full
		 if((power(levels))-1 == nodeCount) {
			 logCheck = true;
		 }
		 return logCheck;
	 }
	 //method for helping with isFull(), used for exponent. 
	 private int power(int expo) {
		 if(expo == 0) {
			 return 1;
		 }else {
			 return 2* power(expo - 1);
		 }
	 }
	 
	 public void printDescendingInorder() {
		 System.out.println("Printing in Descending Order::");
		 printDescendingInorder(overallRoot);
	 }
	 
	 //just swap the order we recurse - focus on right first, then root, then left
	 private void printDescendingInorder(IntTreeNode root) {
		 if(root!=null) {
			 printDescendingInorder(root.right);
			 System.out.print(root.data + " ");
			 printDescendingInorder(root.left);
		 }
	 }
	
	public void printInorder() {
		 System.out.println("Printing in Preorder::");
		 printInorder(overallRoot);
		}

	 private void printInorder(IntTreeNode root) {
		// (base case is implicitly to do nothing on null)
		 if(root!=null) {
			 printInorder(root.left);
			 System.out.print(root.data+" ");
			 printInorder(root.right);
		 }
	}	 
	 
}//end of IntSearchTree


public class BinarySearchTree {
	public static void main(String[] args) {
		Scanner console = new Scanner(System.in);
		IntSearchTree bst_1 = new IntSearchTree();
		
		System.out.print("Next int (0 to quit)? ");
		int number = console.nextInt();
		while(number!=0) {
			bst_1.add(number);
			number = console.nextInt();
		}
		//console.close();

		System.out.println();
		bst_1.printInorder();
		
		//REMOVE
		System.out.println(bst_1.contains(10));
		
		System.out.println("Sum :"+bst_1.sum());
		System.out.println("Levels: "+bst_1.countLevels());
		System.out.println("Leaves: "+bst_1.countLeaves());
		System.out.println("Min: "+bst_1.min());
		System.out.println("Max: "+bst_1.max());
		System.out.println("Size: "+bst_1.size());
		System.out.println("Left Nodes: "+bst_1.countLeftNodes());
		System.out.println("Is Tree Full?: "+bst_1.isFull());
		bst_1.printDescendingInorder();
		
		
	}
}