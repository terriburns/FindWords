import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * This class represents a dictionary of words
 * 
 * @author tburns
 * 
 */
public class Dictionary extends AVLTree {
	// storage of the words in the dictionary.txt file
	private AVLTree tree;

	// empty dictionary object (of type AVLTree)
	public Dictionary() {
		// default constructor
		// make AVL tree object
		tree = new AVLTree();
	}

	/**
	 * Creates a Dictionary object containing all words from the listOfWords
	 * passed as a parameter.
	 * 
	 * @param listOfWords
	 *            the list of words to be stored in the newly created Dictionary
	 *            object
	 */
	public Dictionary(AVLTree listOfWords) {
		tree = listOfWords;
		if (null == tree) {
			tree = new AVLTree();
		}
	}

	/**
	 * This method reads the dictionary file, checks for exceptions, and adds it
	 * to an AVL Tree.
	 * 
	 * @param dictionaryFile
	 * @return "tree" - an AVL tree of all the words in the dictionary.txt file
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public AVLTree addDictionarytoTree(String dictionaryFile)
			throws IOException, FileNotFoundException {
		// read the text file
		final String PATH = dictionaryFile;
		File Dictionary = new File(PATH);
		// Open it with a scanner
		Scanner lineScanner = null;
		Scanner s = null;

		try {
			s = new Scanner(Dictionary);
		}
		// if we cannot find the file
		catch (FileNotFoundException e) {
			System.out
					.println("Cannot find the file. Please make sure your file is"
							+ " in the proper location on your computer.");
			System.exit(1);
		}

		// add values from dictionary to AVL tree
		while (s.hasNext()) {
			tree.insert(s.nextLine());
		}
		
		System.out.println(tree.toString());
		return tree;
	}

	/**
	 * The isAPrefix method determines whether or not the given input
	 * (implemented as the string input from the user) shares the same prefix or
	 * not
	 * 
	 * @param input input from the user
	 * @return boolean - true if found, false if not
	 */
	public boolean isAPrefix(String input) {
		// System.out.println(tree.root.toString());
		return isAPrefixRecursive(input, tree.root);

	}
/**
 * determines whether or not the given input shares the same prefix or not 
 * @param input input from the user
 * @param node the node we are looking at
 * @return
 */
	private boolean isAPrefixRecursive(String input, AVLTreeNode node) {
		// base case - string not found
		if (node == null) {
			
			return false;
		}

		boolean comparison = ((String) node.getData()).startsWith(input);
		if (comparison == true) {
			return true;
		} else {
			int secondComparison = ((String) node.getData())
					.compareToIgnoreCase(input);
			// right
			if (secondComparison < 0)
				return isAPrefixRecursive(input, node.getRight());
			// left
			else if (secondComparison > 0)
				return isAPrefixRecursive(input, node.getLeft());

			else
				return true;
		}

	}

	/**
	 * Performs (binary) search in this Dictionary object for a given word.
	 * 
	 * @param word
	 *            the word to look for in this Dictionary object.
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	public boolean isWordInDictionary(String word) {
		return isWordInDictionaryRecursive(word, tree.root);
	}

	/**
	 * The actual method providing recursive implementation of the binary
	 * search.
	 * 
	 * @param word
	 *            the word to look for in this Dictionary object
	 * @param begin
	 *            start of the range for the current iteration
	 * @param end
	 *            end of the range for the current iteration
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	private boolean isWordInDictionaryRecursive(String word, AVLTreeNode node) {
		// base case - string not found
		if (node == null) {
			
			return false;
		}

		int comparison = ((String) node.getData()).compareToIgnoreCase(word);
		// right
		if (comparison < 0)
			return isWordInDictionaryRecursive(word, node.getRight());
		// left
		else if (comparison > 0)
			return isWordInDictionaryRecursive(word, node.getLeft());

		else
			
		return true;
	}

	/**
	 * 
	 * @param data
	 */
	private Dictionary(Comparable data) {

		// TODO Auto-generated constructor stub
	}

}
