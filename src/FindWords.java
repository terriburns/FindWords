import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * Find Words Game
 * This program produces all possible words given a set of
 * letters and a dictionary
 * 
 * Homework assignment from Johanna Klukowska:
 * http://cs.nyu.edu/~joannakl/cs102_f14/assignments/proj4.pdf
 * 
 * @author: Terri Burns, NYU
 * 
 */
public class FindWords {
	static AVLTree populatedTree = new AVLTree();

	/**
	 * The main method gets input from the user, reads the dictionary file, and
	 * determines which words can be made from the user input.It is called
	 * immediately once the program is run and directly/indirectly calls all the
	 * other methods in the program.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// STEP ONE: Get input from the user
		System.out
				.println("Welcome to Find Words! With this program, you can enter \n"
						+ "any combination of letters, and you will be met with all of the possible word \n"
						+ "combinations you can make based on the provided dictionary. Please enter a minimum \n"
						+ "of two letters and a maximum of ten letters. Do not use spaces or commas."
						+ "\n");
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your letters: ");
		String randomLetters = input.next();			

		// STEP TWO: Read the dictionary file and add to the AVL tree
		if (args.length > 0) {
			Dictionary dictionaryObject = new Dictionary();
			
			try {
				
				// this line of code actually populates populatedTree
				populatedTree = dictionaryObject.addDictionarytoTree(args[0]);
				

			} catch (FileNotFoundException e) {
				System.out
						.println("Cannot find the file. Please make sure your file is in the proper location on your computer.");
				System.exit(1);
				e.printStackTrace();
			} catch (IOException e) {
				System.out
						.println("Cannot find the file. Please make sure your file is in the proper location on your computer.");
				System.exit(1);
			}
			
		}else{
				System.out.println("Not on the command line.");
			}
		
		// STEP THREE: implement methods to find all possible words

		// create a LetterSet Object, will also run checkValidity
		LetterSet letterSetObject = new LetterSet(randomLetters);

		// create a new dictionary object populated w letters. dictionaryTree
		// becomes our AVLTree Dictionary
		Dictionary dictionaryTree = new Dictionary(populatedTree);

		// Find the Words! get a list of all words from the dictionary
		// consisting of the letters from the user input
		ArrayList<String> words = letterSetObject.getAllWords(dictionaryTree);

		// print the words!
		System.out.println("All words containing your letters: ");
		if (words.size() == 0) {
			System.out.println("None. Awkward.");
		} else {
			for (int i = 0; i < words.size(); i++) {

				System.out.printf("\t%s%n", words.get(i));
			}
		}
	}

}
