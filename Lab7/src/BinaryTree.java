
public class BinaryTree {
	//the root Node of the tree set to null
	BinaryTreeNode root = null;

	/**
	 * Insert the data into the tree
	 * @param newData New int to store in the tree
	 */
    public void insertInTree(int newData) {
        if (root == null) {
            root = new BinaryTreeNode(newData);
        } else {
            root.insert(newData);
        }
    }
  
    public int getTreeHeight(){
        return treeHeight(root);
    }
    /**
	 * this method recursive getHeight for 
	 * finding height/depth of binary tree
	 * @param subRoot
	 * @return
	 */
    private int treeHeight(BinaryTreeNode root){
        if(root == null)
            return 0;
        int left = treeHeight(root.getLeft());	//get height of the left branch
        int right = treeHeight(root.getRight());//get height of the right branch
        if(left > right){
            return left + 1;
        }else{
            return right + 1;
        }
    }
    
    /**
	 * This method to display the contents of the tree
	 */
    public void displayInOrder() {
        displayInOrder(root);
    }
    
    /**
	 * Traverse the tree using InOrder traversal 
	 * and display the content to the console
	 * @param subRoot The node to start with
	 */
    private void displayInOrder(BinaryTreeNode subRoot) {
        if (subRoot == null) {
            return;
        }				//in order = left, self, right
        displayInOrder(subRoot.getLeft());
        System.out.print(" " + subRoot.getData() + " ");
        displayInOrder(subRoot.getRight());
    }
}
