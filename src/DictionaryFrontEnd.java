import java.io.File;
import java.io.FileNotFoundException;
/**
 * Find Words Game
 * This program produces all possible words given a set of
 * letters and a dictionary
 * 
 * TODO:
 * -add J docs
 * 
 * 
 * Homework assignment from Joanna Klukowska:
 * http://cs.nyu.edu/~joannakl/cs102_f14/assignments/proj4.pdf
 * 
 * @author: Terri Burns, NYU
 * 
 */

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import processing.core.*;

public class DictionaryFrontEnd extends PApplet {
	// int count = 0;
	int windowWidth = 700;
	int windowHeight = 700;
	int keycount = 0;
	int count = 0;
	String randomLetters = "";
	PFont font;
	ArrayList<String> word = new ArrayList<String>();
	static AVLTree populatedTree = new AVLTree();

	// STEP ONE: BUILD THE FRONT END
	public void setup() {
		size(windowWidth, windowHeight);
		background(255);
		font = createFont("AvenirNext-UltraLightItalic", 100);
	}

	public void draw() {
		// note - if statements allow words to disappear when printWords() is
		// called

		textFont(font, 30);
		if (count == 0) {
			fill(212, 155, 215);
		} else {
			fill(255);
		}

		text("W e l c o m e", 200, 150);

		if (count == 0) {
			fill(155, 215, 199);
		} else {
			// fill(255);
			tint(255, 255);
		}

		text("t o ", 220, 180);
		if (count == 0) {
			fill(201, 243, 150);
		} else {
			tint(255, 255);
		}

		text("F i n d  ", 240, 210);
		if (count == 0) {

			fill(243, 150, 192);
		} else {
			tint(255, 255);
		}

		text("W o r d s", 260, 240);
		textFont(font, 25);

		if (count == 0) {
			fill(0);
		} else {
			tint(255, 255);
		}
		text("Begin typing your word.", 200, 400);
	}

	/**
	 * Called when keys are pressed. (See Processing documentation.) Collects
	 * input form user = the word we are comparing
	 */
	public void keyPressed() {
		keycount+=2;
		// text that the user types
		textFont(font, 24);
		fill(0);
		textSize(50);
		text(key, 300 + keycount * 20, 500);
		textFont(font, 18);
		if (count == 0) {
			fill(212, 155, 215);
		} else {
			tint(255, 255);
		}
		text("Press Enter when you're done.", 190, 650);
		// add each keyPressed to arraylist
		if (key >= 65 && key <= 90 ^ key >= 97 && key <= 122) {
			String name = Character.toString(key); // convert to a
													// letter
			word.add(name); // add each letter to an
							// arraylist

			randomLetters = userInput(word);

		}
		// when user most likely selects "Enter"
		else {
			count++;
			FindWords();
		}
	}

	/**
	 * This method converts the ArrayList to a string.
	 * 
	 * @param list
	 *            , an Arraylist which will be a string
	 * @return theString, the string representation
	 */
	public String userInput(ArrayList list) {
		String theString = "";
		// convert arraylist to string
		for (String s : word) {
			theString += s;

		}
		return theString;
	}

	/**
	 * This method prints all of the possible words to the user.
	 * 
	 * @param theWord
	 *            , and ArrayList filled with all the words to be printed
	 */
	public void printWords(ArrayList<String> theWord) {

		if (theWord.size() == 0) {
			fill(0);
			textSize(28);
			text("None. Awkward.", 220, 220);
		} else {
			for (int i = 0; i < theWord.size(); i++) {
				// randomize word colors
				int pastel = (int) (Math.random() * 4);
				int red = (int) (Math.random() * 256);
				int green = (int) (Math.random() * 256);
				int blue = (int) (Math.random() * 256);

				// one value should be 180, this gives it a pastel look
				if (pastel == 1) {
					red = 180;
				} else if (pastel == 2) {
					green = 180;
				} else {
					blue = 180;
				}

				// randomize word location
				int xCoordinate = (int) (Math.random() * 500);
				int yCoordinate = (int) (Math.random() * 680);

				// print in random location with random color
				textSize(68);
				fill(red, green, blue);
				text(theWord.get(i), xCoordinate, yCoordinate);
			}
		}
	}

	/**
	 * Reads the dictionary file, implements methods to find all the possible
	 * words, calls the printWords method to print all of the available words!
	 */
	public void FindWords() {
		// STEP TWO: Read the dictionary file and add to the AVL tree
		Dictionary dictionaryObject = new Dictionary();

		try {

			// this line of code actually populates populatedTree
			populatedTree = dictionaryObject
					.addDictionarytoTree("dictionary.txt");

		} catch (FileNotFoundException e) {
			System.out.println("sup");
			System.exit(1);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("yo");
			System.exit(1);
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

		printWords(words);

	}

}
