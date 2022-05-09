
public class BinaryTreeNode {
	private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode() {
        left = null;
        right = null;
        data = 0;
    }

    /**
	 * Default Constructor
	 */
    public BinaryTreeNode(int data) {
        left = null;
        right = null;
        this.data = data;
    }
    /**
	 * Returns the data stored in this node
	 * @return Data member variable
	 */
    public int getData() {
        return data;
    }
    /**
	 * Returns the left Binary Tree
	 * @return Left binary tree
	 */
    public BinaryTreeNode getLeft() {
        return left;
    }
    /**
	 * Returns the right Binary Tree
	 * @return Right binary tree
	 */
    public BinaryTreeNode getRight() {
        return right;
    }
    /**
	 * This method inserts a node into either the 
	 * left or right tree where appropriate
	 * @param newData Reference to the new data
	 */
    public void insert(int newData) {
        if (newData < data) {
            if (left == null) {
                left = new BinaryTreeNode(newData);
            } else {
                left.insert(newData);
            }
        } else if (newData > data) {
            if (right == null) {
                right = new BinaryTreeNode(newData);
            } else {
                right.insert(newData);
            }
        } else {
            System.out.println("Duplicate - not adding " + newData);
        }
    }
}
