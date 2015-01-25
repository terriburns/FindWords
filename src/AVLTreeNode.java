/**
 * This is a generic node implementation of a binary search/AVL tree
 * @author tburns
 *
 * @param <E>
 */
public class AVLTreeNode<E extends Comparable<E>> implements Comparable <AVLTreeNode<E>>{
	private E data;
	protected AVLTreeNode <E> left; //protected so that i am able to use elsewhere
	protected AVLTreeNode <E> right; //protected so that i am able to use elsewhere
	protected int height;
	
	public AVLTreeNode (E data) {
		this.data = data;
	}
	
	public AVLTreeNode (E data, AVLTreeNode <E> left, AVLTreeNode <E> right) {
		this.data = data;
		this.left =left ;
		this.right = right;
	}
	
	
	/**
	 * Left subtree . 
	 * @return 
	 *    reference to the left subtree of a node
	 */
	public AVLTreeNode<E> getLeft() {
		return left;
	}
	
	/**
	 * Changes the reference to the left subtree to the one 
	 * specified in the parameter.
	 * @param 
	 *    reference to the new left subtree of the node.
	 */
	public void setLeft(AVLTreeNode<E> left) {
		this.left = left;
	}
	
	/**
	 * Right subtree . 
	 * @return 
	 *    reference to the right subtree of a node
	 */
	public AVLTreeNode<E> getRight() {
		return right;
	}
	
	/**
	 * Changes the reference to the right subtree to the one 
	 * specified in the parameter.
	 * @param 
	 *    reference to the new right subtree of the node.
	 */
	public void setRight(AVLTreeNode<E> right) {
		this.right = right;
	}
	
	/**
	 * Returns a reference to the data stored in the node. 
	 * @return 
	 *    reference to the data stored in the node
	 */
	public E getData() {
		return data;
	}
	/**
	 * Changes the data stored in the node to the one 
	 * specified in the parameter.
	 * @param data
	 *    reference to the new data of the node
	 */
	public void setData(E data) {
		this.data = data;
	}

	/**
	 * compares nodes from one side to another
	 * @param other the data being compared to
	 */
	public int compareTo(AVLTreeNode<E> other) {
		return this.data.compareTo(other.data);
	} 

	/**
	 * returns a string object
	 */
	@Override
	public String toString() {
		return data.toString();
	}
}
