import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Provides recursive implementation of a generic binary search tree/AVL tree
 * 
 * @author tburns
 * 
 * @param <E>
 */
public class AVLTree<E extends Comparable<E>> {
	protected AVLTreeNode<E> root;
	private int numOfElements;
	private Queue<E> queue;

	/**
	 * Creates an empty BST.
	 */
	public AVLTree() {
		this.root = null;
		numOfElements = 0;

	}

	/**
	 * Add an item to this tree using a recursive implementation. Null ignored
	 * 
	 * @param item
	 *            to be added to the tree
	 */
	public void insert(E item) {

		if (item != null) {
			// root = reference of the tree after item has been inserted
			root = recursiveInsert(item, root);

		}

	}

	/**
	 * Add an item to this tree using a recursive implementation. Null ignored
	 * 
	 * @param item
	 *            item to be added
	 * @param tree
	 *            root of the subtree into which the node will be added
	 * @return reference to the tree after the item was inserted
	 */
	private AVLTreeNode<E> recursiveInsert(E item, AVLTreeNode<E> tree) {
		if (tree == null) {
			// add a new place
			tree = new AVLTreeNode<E>(item);		
			updateHeight(tree);
			numOfElements++;
		}
		// determine whether to add to the left or right subtree

		// left
		else if (item.compareTo(tree.getData()) <= 0) {
			tree.setLeft(recursiveInsert(item, tree.getLeft()));

		}
		// right
		else {
			tree.setRight(recursiveInsert(item, tree.getRight()));

		}

		// update the height AFTER insertion
		if (tree != null) {
			// update the height
			updateHeight(tree);

			// if the tree is unbalanced (as determined by balanceFactor),
			// balance it
			if ((balanceFactor(tree) < -1) || (balanceFactor(tree) > 1)) {
				if (balanceFactor(tree) == -2
						&& balanceFactor(tree.getLeft()) == -1) {

					tree = LLbalance(tree);
				}
				// LR
				if (balanceFactor(tree) == -2
						&& balanceFactor(tree.getRight()) == 1) {

					tree = LRbalance(tree);
				}
				// RL
				if (balanceFactor(tree) == 2
						&& balanceFactor(tree.getLeft()) == -1) {

					tree = RLbalance(tree);
				}
				// RR
				else {

					tree = RRbalance(tree);
				}
			}
		}
		return tree;
	}

	/**
	 * Remove an item from this tree using a recursive implementation. Null
	 * ignored
	 * 
	 * @param item
	 *            an element to be removed.
	 */
	public void remove(E item) {
		if (item != null)
			root = recursiveRemove(item, root);
	}

	/**
	 * Recursively remove an item from this BST.
	 * 
	 * @param item
	 *            item to be removed
	 * @param tree
	 *            root of the subtree from which the item will be removed
	 * @return reference to this BST after the item was removed
	 */
	private AVLTreeNode<E> recursiveRemove(E item, AVLTreeNode<E> tree) {

		if (tree == null)
			; // don't do anything
		else if (item.compareTo(tree.getData()) < 0)
			tree.setLeft(recursiveRemove(item, tree.getLeft()));
		else if (item.compareTo(tree.getData()) > 0)
			tree.setRight(recursiveRemove(item, tree.getRight()));
		else {
			tree = removeNode(tree);
		}
		return tree;
	}

	/**
	 * Remove a specific node (depends on number of node children)
	 * 
	 * @param tree
	 *            the node to be removed
	 * @return reference to the tree after node removal
	 */
	private AVLTreeNode<E> removeNode(AVLTreeNode<E> tree) {
		E data;

		if (tree.getLeft() == null) {
			numOfElements--;
			return tree.getRight();
		} else if (tree.getRight() == null) {
			numOfElements--;
			return tree.getLeft();
		} else {
			data = getParent(tree.getLeft());
			tree.setData(data);
			tree.setLeft(recursiveRemove(data, tree.getLeft()));
			return tree;
		}
	}

	/**
	 * Obtains the parent(predecessor) of a node
	 * 
	 * @param tree
	 *            node whose parent we are after
	 * @return the data contained in the parent node
	 */
	private E getParent(AVLTreeNode<E> tree) {
		while (tree.getRight() != null)
			tree = tree.getRight();
		return tree.getData();
	}

	/**
	 * Determines whether or not the the tree contains a reference or value
	 * equal to the value of the parameter
	 * 
	 * @param item
	 * @return false if not found, true if found
	 */
	public boolean Contains(E item) {
		if (get(item) != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns a reference to the item stored in the tree whose value is equal
	 * to the value of the parameter.
	 * 
	 * @param item
	 *            the value whose reference in the tree we are after
	 * @return null, if the node with value equal to item was not found, or a
	 *         reference to that value if found
	 */
	public E get(E item) {
		System.out.println("item " + item);
		return recursiveGet(item, root);
	}

	/**
	 * Get the reference to the item stored in the tree whose value is equal to
	 * the value of the parameter.
	 * 
	 * @param item
	 *            the value whose reference in the three we are after
	 * @param tree
	 *            root of the current subtree in which we are looking for the
	 *            item
	 * @return null, if the node with value equal to item was not found, or a
	 *         reference to that value if found
	 */
	private E recursiveGet(E item, AVLTreeNode<E> tree) {
		// System.out.println(item + " " + tree.getData());
		if (tree == null)
			return null; // element is not found
		// determine if getting from left or right subtree
		else if (item.compareTo(tree.getData()) < 0)
			return recursiveGet(item, tree.getLeft()); // left
		else if (item.compareTo(tree.getData()) > 0)
			return recursiveGet(item, tree.getRight()); // right
		else
			return tree.getData();
	}

	/**
	 * Returns next element from the tree according to its inorder traversal.
	 * 
	 * @return a reference to the next element in the tree or null if end of the
	 *         tree is reached
	 */
	public E getNext() {
		if (queue == null || queue.isEmpty())
			return null;
		else
			return queue.remove();
	}

	/**
	 * Resets the next item to the first item in the tree according to its
	 * inorder traversal.
	 */
	public void resetNext() {
		queue = new LinkedList<E>();
		inOrder(root);
	}

	/**
	 * Performs inorder traversal of the three and fills the queue with the
	 * references to data in appropriate order.
	 * 
	 * @param tree
	 *            root of the current subtree
	 */
	private void inOrder(AVLTreeNode<E> tree)
	// Initializes inOrderQueue with tree elements in inOrder order.
	{
		if (tree != null) {
			inOrder(tree.getLeft());
			queue.add(tree.getData());
			inOrder(tree.getRight());
		}
	}

	/**
	 * Returns the number of elements stored in the tree.
	 * 
	 * @return number of elements in the tree
	 */
	public int size() {
		return numOfElements;
	}

	/**
	 * Returns a string representation of the the tree using its inorder
	 * traversal.
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		// postOrderPrint(root, 0, s);
		return s.toString();
	}

	/**
	 * Returns a string representation of the the tree using its inorder
	 * traversal.
	 * 
	 * @param tree
	 *            the root of the current subtree
	 * @param s
	 *            the string that accumulated the string representation of this
	 *            BST
	 */
	private void inOrderPrint(AVLTreeNode<E> tree, StringBuilder s)
	// Initializes inOrderQueue with tree elements in inOrder order.
	{
		if (tree != null) {
			inOrderPrint(tree.getLeft(), s);
			s.append(tree.getData().toString() + "\n");
			inOrderPrint(tree.getRight(), s);
		}
	}

	/**
	 * @param tree
	 *            the root of the current subtree
	 * @param level
	 *            level (depth) of the current recursive call in the tree to
	 *            determine the indentation of each item
	 * @param output
	 *            the string that accumulated the string representation of this
	 *            BST
	 */
	private void postOrderPrint(AVLTreeNode<E> tree, int level,
			StringBuilder output) {
		if (tree != null) {
			String spaces = "\n";
			if (level > 0) {
				for (int i = 0; i < level - 1; i++)
					spaces += "   ";
				spaces += "|--";
			}
			output.append(spaces);
			output.append(tree.getData());
			postOrderPrint(tree.getLeft(), level + 1, output);
			postOrderPrint(tree.getRight(), level + 1, output);
		}
	}

	// THE FOLLOWING METHODS ARE FOR THE AVL IMPLEMENTATION
	/**
	 * updates the height of the binary search tree
	 * 
	 * @param node
	 */
	private int updateHeight(AVLTreeNode<E> node) {
		// if node is a leaf (if children are null)
		if (node.left == null && node.right == null) {
			// System.out.println("node is a leaf");
			node.height = 0;
		} else if (node.left == null) {
			// System.out.println("left node is null");
			// node.right.height++;
			node.height = node.right.height + 1;

		} else if (node.right == null) {
			// System.out.println("right node is null");
			// node.left.height++;
			node.height = node.left.height + 1;

		} else {
			// System.out.println("neither node is null");
			node.height = max(node.right.height, node.left.height) + 1;
		}
		// System.out.println(node.height);
		return node.height;
	}

	/**
	 * Determines whether or not right height or left height is larger
	 * 
	 * @param rightHeight
	 *            the height of the right side of the tree
	 * @param leftHeight
	 *            the height of the left side of the tree
	 * @return
	 */
	private int max(int rightHeight, int leftHeight) {
		if (rightHeight > leftHeight) {
			return rightHeight;
		} else {
			return leftHeight;
		}
	}

	/**
	 * Determines the balance factor of the tree, which will be used to figure
	 * out which rotation is necessary
	 * 
	 * @param node
	 *            specific node(s) of the three we are looking at
	 * @return
	 */
	private int balanceFactor(AVLTreeNode<E> node) {
		if (node == null) {
			return 0;
		}

		if (node.right == null) {
			// System.out.println("shouldnt get here");
			return (node.height) * (-1);
		}
		if (node.left == null) {
			// System.out.println("shouldnt get here");
			return node.height;
		}

		return node.right.height - node.left.height;
	}

	/**
	 * LL rotation of the BST
	 * 
	 * @param A
	 *            the specific node we are looking at
	 * @return B, a reference to the new root of the subtree
	 */
	private AVLTreeNode<E> LLbalance(AVLTreeNode<E> A) {
		AVLTreeNode<E> B = A.left;// B is 12

		A.left = B.right;
		B.right = A; // 45

		updateHeight(A);
		updateHeight(B);

		return B;
	}

	/**
	 * RR rotation of the BST
	 * 
	 * @param A
	 *            the specific node we are looking at
	 * @return B, a reference to the new root of the subtree
	 */
	private AVLTreeNode<E> RRbalance(AVLTreeNode A) {
		AVLTreeNode<E> B = A.right;
		A.right = B.left;
		B.left = A;

		updateHeight(A);
		updateHeight(B);

		return B;
	}

	/**
	 * LL balance of the BST
	 * 
	 * @param A
	 *            the specific node we are looking at
	 * @return B, a reference to the new root of the subtree
	 */
	private AVLTreeNode<E> LRbalance(AVLTreeNode<E> A) {
		AVLTreeNode<E> B = A.left;
		AVLTreeNode<E> C = B.right;

		A.left = C.right;
		B.right = C.left;
		C.left = B;
		C.right = A;

		updateHeight(A);
		updateHeight(B);
		updateHeight(C);

		return C;

	}

	/**
	 * RL balance of the BTS
	 * 
	 * @param A
	 *            the specific node we are looking at
	 * @return B, a reference to the new root of the subtree
	 */
	private AVLTreeNode<E> RLbalance(AVLTreeNode<E> A) {
		AVLTreeNode<E> B = A.right;
		AVLTreeNode<E> C = B.left;

		A.right = C.left;
		B.left = C.right;
		C.right = B;
		C.left = A;

		updateHeight(A);
		updateHeight(B);
		updateHeight(C);

		return C;

	}

}
